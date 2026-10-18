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
    protected abstract void beforeDestroyingBlock(LevelAccessor level, BlockPos pos, BlockState state);

    @Inject(method = "spreadTo", at = @At("HEAD"), cancellable = true)
    private void coloredWater$interceptWaterloggedSpread(LevelAccessor level, BlockPos pos, BlockState blockState, Direction direction, FluidState fluidState, CallbackInfo ci) {
        if (!(level instanceof ServerLevel serverLevel)) return;

        BlockPos sourcePos = pos.relative(direction.getOpposite());
        BlockEntity sourceBlockEntity = serverLevel.getBlockEntity(sourcePos);

        if (sourceBlockEntity instanceof WaterloggedColorBlockEntity sourceBe && sourceBe.hasFluid()) {
            ColorWaterFluid fluid = sourceBe.getFluid();

            FluidState targetFluidState = level.getFluidState(pos);
            if (targetFluidState.is(FluidTags.LAVA)) {
                if (blockState.getBlock() instanceof LiquidBlock) {
                    if (targetFluidState.isSource()) {
                        level.setBlock(pos, Blocks.OBSIDIAN.defaultBlockState(), 3);
                    } else {
                        level.setBlock(pos, Blocks.COBBLESTONE.defaultBlockState(), 3);
                    }
                }
                level.levelEvent(1501, pos, 0);
                ci.cancel();
                return;
            }

            if (fluidState.isEmpty()) {
                serverLevel.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                ci.cancel();
                return;
            }

            if (blockState.getBlock() instanceof LiquidBlockContainer liquidblockcontainer) {
                FluidState customFluidState = fluidState.isSource()
                        ? fluid.getSource(false)
                        : ((FlowingFluid) fluid.getFlowing()).getFlowing(fluidState.getAmount(), fluidState.hasProperty(FlowingFluid.FALLING) && fluidState.getValue(FlowingFluid.FALLING));
                liquidblockcontainer.placeLiquid(level, pos, blockState, customFluidState);
                WaterloggedColorBlockEntity targetBe = WaterloggedColorBlockEntity.getOrCreate(serverLevel, pos, serverLevel.getBlockState(pos));
                if (targetBe != null) {
                    targetBe.setFluid(fluid);
                }
            } else {
                if (!blockState.isAir()) {
                    this.beforeDestroyingBlock(level, pos, blockState);
                }

                int legacyLevel;
                if (fluidState.isSource()) {
                    legacyLevel = 0;
                } else {
                    boolean isFalling = fluidState.hasProperty(FlowingFluid.FALLING) && fluidState.getValue(FlowingFluid.FALLING);
                    legacyLevel = 8 - Math.min(fluidState.getAmount(), 8) + (isFalling ? 8 : 0);
                }

                BlockState targetState = fluid.defaultFluidState().createLegacyBlock().setValue(LiquidBlock.LEVEL, legacyLevel);
                serverLevel.setBlock(pos, targetState, 3);
            }

            ci.cancel();
        }
    }
}
