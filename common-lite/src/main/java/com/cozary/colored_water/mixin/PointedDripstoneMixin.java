package com.cozary.colored_water.mixin;

import com.cozary.colored_water.block.entity.WaterloggedColorBlockEntity;
import com.cozary.colored_water.cauldrons.behaviour.CauldronBehaviorHelper;
import com.cozary.colored_water.fluids.ColorWaterFluid;
import com.cozary.colored_water.init.ModParticles;
import com.cozary.colored_water.particles.ColorParticleOptions;
import com.cozary.colored_water.particles.SparkleParticleOptions;
import com.cozary.colored_water.util.ColoredWaterUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DripstoneThickness;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PointedDripstoneBlock.class)
public abstract class PointedDripstoneMixin {

    @Inject(method = "maybeTransferFluid", at = @At("HEAD"), cancellable = true)
    private static void colored_water$maybeTransferFluid(BlockState state, ServerLevel level, BlockPos blockPosition, float randChance, CallbackInfo callbackInfo) {
        if (state.getValue(PointedDripstoneBlock.TIP_DIRECTION) != Direction.DOWN) {
            return;
        }

        BlockPos sourcePosition = findColoredWaterSourcePosition(level, blockPosition);
        if (sourcePosition == null) {
            return;
        }

        callbackInfo.cancel();

        if (randChance < 0.17578125F) {
            BlockPos tipPosition = findStalactiteTipPosition(level, blockPosition);
            ColorWaterFluid colorWaterFluid = getColoredWaterAt(level, sourcePosition);
            if (colorWaterFluid == null) return;

            DyeColor dyeColor = colorWaterFluid.getDyeColor();
            CauldronBehaviorHelper.CauldronGroup group = CauldronBehaviorHelper.getGroup(dyeColor);
            if (group == null) return;

            Block targetCauldron = colorWaterFluid.isCondensed() && colorWaterFluid.isLuminous() ? group.luminousCondenseCauldron().get()
                    : colorWaterFluid.isCondensed() ? group.condenseCauldron().get()
                    : colorWaterFluid.isLuminous() ? group.luminousCauldron().get()
                    : group.normalCauldron().get();

            for (int i = 1; i <= 11; i++) {
                BlockPos cauldronPosition = tipPosition.below(i);
                BlockState cauldronState = level.getBlockState(cauldronPosition);

                if (cauldronState.getBlock() instanceof AbstractCauldronBlock) {
                    if (cauldronState.is(Blocks.CAULDRON)) {
                        level.setBlock(cauldronPosition, targetCauldron.defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, 1), 3);
                        level.levelEvent(1047, cauldronPosition, 0);
                    } else if (cauldronState.is(targetCauldron)) {
                        int currentLevel = cauldronState.getValue(LayeredCauldronBlock.LEVEL);
                        if (currentLevel < 3) {
                            level.setBlock(cauldronPosition, cauldronState.setValue(LayeredCauldronBlock.LEVEL, currentLevel + 1), 3);
                            level.levelEvent(1047, cauldronPosition, 0);
                        }
                    }
                    break;
                } else if (!cauldronState.isAir() && !(cauldronState.getBlock() instanceof PointedDripstoneBlock)) {
                    break;
                }
            }
        }
    }

    private static BlockPos findColoredWaterSourcePosition(Level level, BlockPos blockPosition) {
        BlockPos current = blockPosition;
        while (level.getBlockState(current).is(Blocks.POINTED_DRIPSTONE)) {
            current = current.above();
        }

        BlockState rootState = level.getBlockState(current);
        if (rootState.is(Blocks.POINTED_DRIPSTONE) || !rootState.isCollisionShapeFullBlock(level, current)) {
            return null;
        }

        BlockPos above = current.above();
        if (isColoredWaterAt(level, above)) {
            return above;
        }
        return null;
    }

    private static boolean isColoredWaterAt(Level level, BlockPos blockPosition) {
        return getColoredWaterAt(level, blockPosition) != null;
    }

    private static ColorWaterFluid getColoredWaterAt(Level level, BlockPos blockPosition) {
        FluidState fluid = level.getFluidState(blockPosition);
        if (fluid.getType() instanceof ColorWaterFluid colorWaterFluid) {
            return colorWaterFluid;
        }
        BlockEntity blockEntity = level.getBlockEntity(blockPosition);
        if (blockEntity instanceof WaterloggedColorBlockEntity waterloggedBlockEntity && waterloggedBlockEntity.hasFluid()) {
            return waterloggedBlockEntity.getFluid();
        }
        return null;
    }

    private static BlockPos findStalactiteTipPosition(Level level, BlockPos blockPosition) {
        BlockPos current = blockPosition;
        while (level.getBlockState(current).is(Blocks.POINTED_DRIPSTONE)) {
            BlockState state = level.getBlockState(current);
            if (state.getValue(PointedDripstoneBlock.TIP_DIRECTION) == Direction.DOWN &&
                    (state.getValue(PointedDripstoneBlock.THICKNESS) == DripstoneThickness.TIP || state.getValue(PointedDripstoneBlock.THICKNESS) == DripstoneThickness.TIP_MERGE)) {
                return current;
            }
            BlockPos below = current.below();
            if (!level.getBlockState(below).is(Blocks.POINTED_DRIPSTONE)) {
                return current;
            }
            current = below;
        }
        return current;
    }

    @Inject(method = "animateTick", at = @At("HEAD"), cancellable = true)
    private void colored_water$animateTick(BlockState state, Level level, BlockPos blockPosition, RandomSource random, CallbackInfo callbackInfo) {
        if (state.getValue(PointedDripstoneBlock.TIP_DIRECTION) != Direction.DOWN) {
            return;
        }
        if (state.getValue(PointedDripstoneBlock.THICKNESS) != DripstoneThickness.TIP && state.getValue(PointedDripstoneBlock.THICKNESS) != DripstoneThickness.TIP_MERGE) {
            return;
        }

        BlockPos sourcePosition = findColoredWaterSourcePosition(level, blockPosition);
        if (sourcePosition != null) {
            if (random.nextFloat() < 0.12F) {
                double x = (double) blockPosition.getX() + 0.5D + (random.nextDouble() - 0.5D) * 0.2D;
                double y = (double) blockPosition.getY() + 0.1D;
                double z = (double) blockPosition.getZ() + 0.5D + (random.nextDouble() - 0.5D) * 0.2D;

                ColorWaterFluid colorWaterFluid = getColoredWaterAt(level, sourcePosition);
                int color = colorWaterFluid != null ? colorWaterFluid.getTintColor() : 0x3F76E4;
                boolean isLuminous = colorWaterFluid != null && colorWaterFluid.isLuminous();

                level.addParticle(new ColorParticleOptions(ModParticles.DRIPPING_DRIPSTONE_WATER.get(), color), x, y, z, 0.0, 0.0, 0.0);

                if (isLuminous) {
                    level.addParticle(SparkleParticleOptions.of(color), x, y, z, 0.01, -0.02, 0.01);
                }
            }
            callbackInfo.cancel();
        }
    }
}
