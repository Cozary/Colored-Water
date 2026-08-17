package com.cozary.colored_water.mixin;

import com.cozary.colored_water.block.entity.ColoredWaterBlockEntity;
import com.cozary.colored_water.fluids.ColoredWaterFluid;
import com.cozary.colored_water.init.ModFluids;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.block.LiquidBlockRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LiquidBlockRenderer.class)
public class LiquidBlockRendererMixin {

    /**
     * Replaces the FluidState passed to tesselate() for waterlogged blocks that have a
     * ColoredWaterBlockEntity, so the LiquidBlockRenderer uses colored_water textures/tint
     * instead of vanilla water. This ONLY affects rendering game logic uses LevelMixin.
     */
    @ModifyVariable(
        method = {"tesselate", "render"},
        at = @At("HEAD"),
        argsOnly = true,
        require = 0
    )
    private FluidState coloredWater$modifyWaterloggedFluidState(FluidState fluidState, BlockAndTintGetter level, BlockPos pos, VertexConsumer buffer, BlockState blockState) {
        if (blockState != null && blockState.hasProperty(BlockStateProperties.WATERLOGGED) && blockState.getValue(BlockStateProperties.WATERLOGGED)) {
            if (level != null && pos != null) {
                BlockEntity be = level.getBlockEntity(pos);
                if (be instanceof ColoredWaterBlockEntity coloredBe) {
                    FluidState newFluidState = ModFluids.STILL_COLORED_WATER.get().getSource(false);
                    if (newFluidState.hasProperty(ColoredWaterFluid.CONDENSED)) {
                        newFluidState = newFluidState.setValue(ColoredWaterFluid.CONDENSED, coloredBe.isCondensed());
                    }
                    return newFluidState;
                }
            }
        }
        return fluidState;
    }
}

