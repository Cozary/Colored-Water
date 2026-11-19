package com.cozary.colored_water.mixin;

import com.cozary.colored_water.block.entity.WaterloggedColorBlockEntity;
import com.cozary.colored_water.fluids.BaseColorWater;
import com.cozary.colored_water.fluids.ColorWaterFluid;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
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
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Handles bubble column updates and lava interactions for colored water LiquidBlocks.
 */
@Mixin(LiquidBlock.class)
public abstract class LiquidBlockMixin {

    @Inject(method = "onPlace", at = @At("RETURN"))
    private void coloredWater$onPlace(BlockState state, Level level, BlockPos blockPosition, BlockState oldState, boolean isMoving, CallbackInfo callbackInfo) {
        coloredWater$handleFluidUpdate(state, level, blockPosition);
    }

    @Inject(method = "neighborChanged", at = @At("RETURN"))
    private void coloredWater$neighborChanged(BlockState state, Level level, BlockPos blockPosition, Block block, @Nullable Orientation orientation, boolean bol, CallbackInfo callbackInfo) {
        coloredWater$handleFluidUpdate(state, level, blockPosition);
    }

    @Unique
    private static void coloredWater$handleFluidUpdate(BlockState state, Level level, BlockPos blockPosition) {
        if (level.isClientSide() || !(state.getBlock() instanceof LiquidBlock) || !(state.getFluidState().getType() instanceof BaseColorWater)) {
            return;
        }
        checkLavaInteraction(level, blockPosition);
        if (state.getFluidState().isSource()) {
            BlockState below = level.getBlockState(blockPosition.below());
            if (below.is(BlockTags.ENABLES_BUBBLE_COLUMN_PUSH_UP) || below.is(BlockTags.ENABLES_BUBBLE_COLUMN_DRAG_DOWN) || below.is(Blocks.BUBBLE_COLUMN)) {
                BubbleColumnBlock.updateColumn(Blocks.BUBBLE_COLUMN, level, blockPosition, below);
            }
        }
    }

    private static void checkLavaInteraction(Level level, BlockPos blockPosition) {
        if (level.isClientSide()) return;
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            BlockPos neighborPosition = blockPosition.relative(direction);
            FluidState neighborFluid = level.getFluidState(neighborPosition);
            if (neighborFluid.is(FluidTags.LAVA)) {
                if (neighborFluid.isSource()) {
                    level.setBlockAndUpdate(neighborPosition, Blocks.OBSIDIAN.defaultBlockState());
                    level.levelEvent(1501, neighborPosition, 0);
                } else if (neighborFluid.getHeight(level, neighborPosition) >= 0.44444445F) {
                    level.setBlockAndUpdate(neighborPosition, Blocks.COBBLESTONE.defaultBlockState());
                    level.levelEvent(1501, neighborPosition, 0);
                }
            }
        }
    }
}
