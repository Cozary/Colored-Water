package com.cozary.colored_water.mixin;

import com.cozary.colored_water.block.entity.ColoredWaterBlockEntity;
import com.cozary.colored_water.fluids.ColoredWaterFluid;
import com.cozary.colored_water.init.ModFluids;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.block.LiquidBlockRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/**
 * Renders custom fluid textures and tinting for waterlogged blocks and bubble columns.
 */
@Mixin(LiquidBlockRenderer.class)
public class LiquidBlockRendererMixin {

    @ModifyVariable(method = {"tesselate", "render"}, at = @At("HEAD"), argsOnly = true, require = 0)
    private FluidState coloredWater$modifyWaterloggedFluidState(FluidState fluidState, BlockAndTintGetter level, BlockPos pos, VertexConsumer buffer, BlockState blockState) {
        boolean isWaterlogged = blockState != null && blockState.hasProperty(BlockStateProperties.WATERLOGGED) && blockState.getValue(BlockStateProperties.WATERLOGGED);
        boolean isBubbleColumn = blockState != null && blockState.is(Blocks.BUBBLE_COLUMN);
        if (isWaterlogged || isBubbleColumn) {
            if (level != null && pos != null) {
                BlockEntity blockEntity = level.getBlockEntity(pos);
                if (blockEntity instanceof ColoredWaterBlockEntity coloredWaterBlockEntity && coloredWaterBlockEntity.hasCustomProperties()) {
                    FluidState newFluidState = ModFluids.STILL_COLORED_WATER.get().getSource(false);
                    if (newFluidState.hasProperty(ColoredWaterFluid.CONDENSED)) {
                        newFluidState = newFluidState.setValue(ColoredWaterFluid.CONDENSED, coloredWaterBlockEntity.isCondensed());
                    }
                    return newFluidState;
                }
            }
        }
        return fluidState;
    }
}

