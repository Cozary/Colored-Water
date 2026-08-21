package com.cozary.colored_water.mixin;

import com.cozary.colored_water.block.ColoredWaterBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoulSandBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SoulSandBlock.class)
public abstract class SoulSandBlockMixin {

    @Inject(method = "updateShape", at = @At("HEAD"))
    private void coloredWater$onUpdateShape(BlockState state, LevelReader level, ScheduledTickAccess scheduledTicks, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random, CallbackInfoReturnable<BlockState> cir) {
        if (direction == Direction.UP && neighborState.getBlock() instanceof ColoredWaterBlock) {
            scheduledTicks.scheduleTick(pos, (Block) (Object) this, 20);
        }
    }
}
