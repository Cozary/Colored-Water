package com.cozary.colored_water.mixin;

import com.cozary.colored_water.block.entity.WaterloggedColorBlockEntity;
import com.cozary.colored_water.fluids.BaseColorWater;
import com.cozary.colored_water.fluids.ColorWaterFluid;
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
            if (blockEntity instanceof WaterloggedColorBlockEntity waterloggedBe && waterloggedBe.hasFluid()) {
                FluidRenderHandler handler = FluidRenderHandlerRegistry.INSTANCE.get(waterloggedBe.getFluid().getSource());
                if (handler != null) {
                    FluidState customFluidState = waterloggedBe.getFluid().getSource(false);
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
        boolean isCondensed = false;
        if (fluidState.getType() instanceof ColorWaterFluid cwf) {
            isCondensed = cwf.isCondensed();
        }

        if (!isColored && level != null && pos != null) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof WaterloggedColorBlockEntity waterloggedBe && waterloggedBe.hasFluid()) {
                isColored = true;
                isCondensed = waterloggedBe.getFluid().isCondensed();
            }
        }

        if (isColored) {
            int alpha = isCondensed ? ColoredWaterUtil.CONDENSED_ALPHA : ColoredWaterUtil.DEFAULT_ALPHA;
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
