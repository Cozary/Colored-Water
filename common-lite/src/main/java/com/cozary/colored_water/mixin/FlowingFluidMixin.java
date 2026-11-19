package com.cozary.colored_water.mixin;

import com.cozary.colored_water.block.entity.WaterloggedColorBlockEntity;
import com.cozary.colored_water.fluids.ColorWaterFluid;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FlowingFluid.class)
public abstract class FlowingFluidMixin {

    @Shadow
    protected abstract void beforeDestroyingBlock(LevelAccessor level, BlockPos blockPosition, BlockState state);

    @Inject(method = "spreadTo", at = @At("HEAD"), cancellable = true)
    private void coloredWater$interceptWaterloggedSpread(LevelAccessor level, BlockPos blockPosition, BlockState blockState, Direction direction, FluidState fluidState, CallbackInfo callbackInfo) {
        if (!(level instanceof ServerLevel serverLevel)) return;

        BlockPos sourcePosition = blockPosition.relative(direction.getOpposite());
        BlockEntity sourceBlockEntity = serverLevel.getBlockEntity(sourcePosition);

        if (sourceBlockEntity instanceof WaterloggedColorBlockEntity sourceWaterloggedBlockEntity && sourceWaterloggedBlockEntity.hasFluid()) {
            ColorWaterFluid fluid = sourceWaterloggedBlockEntity.getFluid();

            FluidState targetFluidState = level.getFluidState(blockPosition);
            if (targetFluidState.is(FluidTags.LAVA)) {
                if (blockState.getBlock() instanceof LiquidBlock) {
                    if (targetFluidState.isSource()) {
                        level.setBlock(blockPosition, Blocks.OBSIDIAN.defaultBlockState(), 3);
                    } else {
                        level.setBlock(blockPosition, Blocks.COBBLESTONE.defaultBlockState(), 3);
                    }
                }
                level.levelEvent(1501, blockPosition, 0);
                callbackInfo.cancel();
                return;
            }

            if (fluidState.isEmpty()) {
                serverLevel.setBlock(blockPosition, Blocks.AIR.defaultBlockState(), 3);
                callbackInfo.cancel();
                return;
            }

            if (blockState.getBlock() instanceof LiquidBlockContainer liquidblockcontainer) {
                FluidState customFluidState = fluidState.isSource()
                        ? fluid.getSource(false)
                        : ((FlowingFluid) fluid.getFlowing()).getFlowing(fluidState.getAmount(), fluidState.hasProperty(FlowingFluid.FALLING) && fluidState.getValue(FlowingFluid.FALLING));
                liquidblockcontainer.placeLiquid(level, blockPosition, blockState, customFluidState);
                WaterloggedColorBlockEntity targetBlockEntity = WaterloggedColorBlockEntity.getOrCreate(serverLevel, blockPosition, serverLevel.getBlockState(blockPosition));
                if (targetBlockEntity != null) {
                    targetBlockEntity.setFluid(fluid);
                }
            } else {
                if (!blockState.isAir()) {
                    this.beforeDestroyingBlock(level, blockPosition, blockState);
                }

                int legacyLevel;
                if (fluidState.isSource()) {
                    legacyLevel = 0;
                } else {
                    boolean isFalling = fluidState.hasProperty(FlowingFluid.FALLING) && fluidState.getValue(FlowingFluid.FALLING);
                    legacyLevel = 8 - Math.min(fluidState.getAmount(), 8) + (isFalling ? 8 : 0);
                }

                BlockState targetState = fluid.defaultFluidState().createLegacyBlock().setValue(LiquidBlock.LEVEL, legacyLevel);
                serverLevel.setBlock(blockPosition, targetState, 3);
            }

            callbackInfo.cancel();
        }
    }
}
