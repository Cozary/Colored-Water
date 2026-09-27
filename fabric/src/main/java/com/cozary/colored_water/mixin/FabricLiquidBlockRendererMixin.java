package com.cozary.colored_water.mixin;

import com.cozary.colored_water.block.entity.ColoredWaterBlockEntity;
import com.cozary.colored_water.fluids.BaseColorWater;
import com.cozary.colored_water.fluids.ColoredWaterFluid;
import com.cozary.colored_water.init.ModFluids;
import com.cozary.colored_water.util.ColoredWaterUtil;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandler;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.minecraft.client.renderer.block.LiquidBlockRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Fabric fluid rendering mixin for colored water.
 * <p>
 * - Delegates rendering of waterlogged blocks and bubble columns to Fabric's FluidRenderHandler.
 * - Applies custom alpha and opacity to colored water vertices.
 */
@Mixin(value = LiquidBlockRenderer.class, priority = 500)
public class FabricLiquidBlockRendererMixin {

    @Unique
    private static final ThreadLocal<Boolean> coloredWater$isRenderingCustom = ThreadLocal.withInitial(() -> Boolean.FALSE);

    @Unique
    private float coloredWater$currentAlpha = 1.0F;

    @Inject(method = "tesselate", at = @At("HEAD"), cancellable = true)
    private void coloredWater$delegateCustomRendering(
            BlockAndTintGetter level, BlockPos pos, VertexConsumer buffer,
            BlockState blockState, FluidState fluidState, CallbackInfo ci) {

        if (coloredWater$isRenderingCustom.get()) {
            return;
        }

        boolean isWaterlogged = blockState != null && blockState.hasProperty(BlockStateProperties.WATERLOGGED) && blockState.getValue(BlockStateProperties.WATERLOGGED);
        boolean isBubbleColumn = blockState != null && blockState.is(Blocks.BUBBLE_COLUMN);

        if ((isWaterlogged || isBubbleColumn) && level != null && pos != null) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof ColoredWaterBlockEntity coloredWaterBlockEntity && coloredWaterBlockEntity.hasCustomProperties()) {
                FluidRenderHandler handler = FluidRenderHandlerRegistry.INSTANCE.get(ModFluids.STILL_COLORED_WATER.get());
                if (handler != null) {
                    FluidState customFluidState = ModFluids.STILL_COLORED_WATER.get().getSource(false);
                    if (customFluidState.hasProperty(ColoredWaterFluid.CONDENSED)) {
                        customFluidState = customFluidState.setValue(ColoredWaterFluid.CONDENSED, coloredWaterBlockEntity.isCondensed());
                    }
                    coloredWater$isRenderingCustom.set(Boolean.TRUE);
                    try {
                        handler.renderFluid(pos, level, buffer, blockState, customFluidState);
                    } finally {
                        coloredWater$isRenderingCustom.set(Boolean.FALSE);
                    }
                    ci.cancel();
                }
            }
        }
    }

    @Inject(method = "tesselate", at = @At("HEAD"))
    private void coloredWater$captureAlpha(BlockAndTintGetter level, BlockPos pos, VertexConsumer buffer, BlockState blockState, FluidState fluidState, CallbackInfo ci) {
        boolean isColored = fluidState.getType() instanceof BaseColorWater;
        if (!isColored && level != null && pos != null) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof ColoredWaterBlockEntity coloredWaterBlockEntity && coloredWaterBlockEntity.hasCustomProperties()) {
                isColored = true;
            }
        }

        if (isColored) {
            int alpha = ColoredWaterUtil.DEFAULT_ALPHA;
            if (level != null && pos != null) {
                BlockEntity blockEntity = level.getBlockEntity(pos);
                if (blockEntity instanceof ColoredWaterBlockEntity coloredWaterBlockEntity && coloredWaterBlockEntity.hasCustomProperties()) {
                    int customAlpha = ColoredWaterUtil.getAlpha(coloredWaterBlockEntity.getColor());
                    if (customAlpha == 0) {
                        customAlpha = coloredWaterBlockEntity.isCondensed() ? ColoredWaterUtil.CONDENSED_ALPHA : ColoredWaterUtil.DEFAULT_ALPHA;
                    }
                    alpha = customAlpha;
                }
            }
            this.coloredWater$currentAlpha = (float) alpha / 255.0F;
        } else {
            this.coloredWater$currentAlpha = 1.0F;
        }
    }

    @Inject(method = "tesselate", at = @At("RETURN"))
    private void coloredWater$resetAlpha(BlockAndTintGetter level, BlockPos pos, VertexConsumer buffer, BlockState blockState, FluidState fluidState, CallbackInfo ci) {
        this.coloredWater$currentAlpha = 1.0F;
    }

    @ModifyArg(method = "vertex", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/VertexConsumer;setColor(FFFF)Lcom/mojang/blaze3d/vertex/VertexConsumer;"), index = 3)
    private float coloredWater$modifyVertexAlpha(float originalAlpha) {
        return this.coloredWater$currentAlpha;
    }
}
