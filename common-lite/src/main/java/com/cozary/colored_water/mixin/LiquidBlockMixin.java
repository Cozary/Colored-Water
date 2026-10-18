package com.cozary.colored_water.mixin;

import com.cozary.colored_water.block.entity.WaterloggedColorBlockEntity;
import com.cozary.colored_water.fluids.BaseColorWater;
import com.cozary.colored_water.fluids.ColorWaterFluid;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BubbleColumnBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.redstone.Orientation;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Handles bubble column updates and lava interactions for colored water LiquidBlocks.
 */
@Mixin(LiquidBlock.class)
public abstract class LiquidBlockMixin {

    @Inject(method = "onPlace", at = @At("RETURN"))
    private void coloredWater$onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving, CallbackInfo ci) {
        if (!level.isClientSide() && state.getBlock() instanceof LiquidBlock && state.getFluidState().getType() instanceof BaseColorWater) {
            checkLavaInteraction(level, pos);
            if (state.getFluidState().isSource()) {
                BlockState below = level.getBlockState(pos.below());
                if (below.is(Blocks.SOUL_SAND) || below.is(Blocks.MAGMA_BLOCK) || below.is(Blocks.BUBBLE_COLUMN)) {
                    BubbleColumnBlock.updateColumn(level, pos, below);
                }
            }
        }
    }

    @Inject(method = "neighborChanged", at = @At("RETURN"))
    private void coloredWater$neighborChanged(BlockState state, Level level, BlockPos pos, Block block, @Nullable Orientation orientation, boolean bol, CallbackInfo ci) {
        if (!level.isClientSide() && state.getBlock() instanceof LiquidBlock && state.getFluidState().getType() instanceof BaseColorWater) {
            checkLavaInteraction(level, pos);
            if (state.getFluidState().isSource()) {
                BlockState below = level.getBlockState(pos.below());
                if (below.is(Blocks.SOUL_SAND) || below.is(Blocks.MAGMA_BLOCK) || below.is(Blocks.BUBBLE_COLUMN)) {
                    BubbleColumnBlock.updateColumn(level, pos, below);
                }
            }
        }
    }

    private static void checkLavaInteraction(Level level, BlockPos pos) {
        if (level.isClientSide()) return;
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            BlockPos neighborPos = pos.relative(direction);
            FluidState neighborFluid = level.getFluidState(neighborPos);
            if (neighborFluid.is(FluidTags.LAVA)) {
                if (neighborFluid.isSource()) {
                    level.setBlockAndUpdate(neighborPos, Blocks.OBSIDIAN.defaultBlockState());
                    level.levelEvent(1501, neighborPos, 0);
                } else if (neighborFluid.getHeight(level, neighborPos) >= 0.44444445F) {
                    level.setBlockAndUpdate(neighborPos, Blocks.COBBLESTONE.defaultBlockState());
                    level.levelEvent(1501, neighborPos, 0);
                }
            }
        }
    }
}

