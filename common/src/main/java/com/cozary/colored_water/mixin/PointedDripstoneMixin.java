package com.cozary.colored_water.mixin;

import com.cozary.colored_water.block.ColoredWaterBlock;
import com.cozary.colored_water.block.ColoredWaterCauldronBlock;
import com.cozary.colored_water.block.entity.ColoredWaterBlockEntity;
import com.cozary.colored_water.block.entity.ColoredWaterCauldronBlockEntity;
import com.cozary.colored_water.fluids.BaseColorWater;
import com.cozary.colored_water.init.ModCauldrons;
import com.cozary.colored_water.init.ModParticles;
import com.cozary.colored_water.particles.ColorParticleOptions;
import com.cozary.colored_water.particles.SparkleParticleOptions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.PointedDripstoneBlock;
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
    private static void colored_water$maybeTransferFluid(BlockState state, ServerLevel level, BlockPos pos, float randChance, CallbackInfo ci) {
        if (state.getValue(PointedDripstoneBlock.TIP_DIRECTION) != Direction.DOWN) {
            return;
        }

        BlockPos sourcePos = findColoredWaterSourcePos(level, pos);
        if (sourcePos == null) {
            return;
        }

        ci.cancel();

        if (randChance < 0.17578125F) {
            BlockPos tipPos = findStalactiteTipPos(level, pos);

            int color = 0x3F76E4;
            int luminosity = 0;
            boolean condensed = false;
            int alpha = 180;

            if (level.getBlockEntity(sourcePos) instanceof ColoredWaterBlockEntity sourceBe) {
                color = sourceBe.getColor();
                luminosity = sourceBe.getLuminosity();
                condensed = sourceBe.isCondensed();
                alpha = (color >>> 24) & 0xFF;
                if (alpha == 0) alpha = condensed ? 255 : 180;
            }

            for (int i = 1; i <= 11; i++) {
                BlockPos cauldronPos = tipPos.below(i);
                BlockState cauldronState = level.getBlockState(cauldronPos);

                if (cauldronState.getBlock() instanceof AbstractCauldronBlock) {
                    if (cauldronState.is(Blocks.CAULDRON)) {
                        int fullColor = (alpha << 24) | (color & 0xFFFFFF);
                        level.setBlock(cauldronPos, ModCauldrons.COLORED_WATER_CAULDRON.get().defaultBlockState()
                                .setValue(LayeredCauldronBlock.LEVEL, 1)
                                .setValue(ColoredWaterCauldronBlock.CONDENSED, condensed)
                                .setValue(ColoredWaterCauldronBlock.LIGHT_LEVEL, luminosity), 3);

                        if (level.getBlockEntity(cauldronPos) instanceof ColoredWaterCauldronBlockEntity cauldronBe) {
                            cauldronBe.setCondensed(condensed);
                            cauldronBe.setLuminosity(luminosity);
                            cauldronBe.setAlpha(alpha);
                            cauldronBe.setColor(fullColor);
                        }
                        level.levelEvent(1047, cauldronPos, 0);
                    } else if (cauldronState.is(Blocks.WATER_CAULDRON)) {
                        int currentLevel = cauldronState.getValue(LayeredCauldronBlock.LEVEL);
                        int newLevel = Math.min(3, currentLevel + 1);

                        int cColor = 0x3F76E4;
                        int cAlpha = 180;
                        int cLuminosity = 0;

                        int cWeight = currentLevel * 3;
                        int dWeight = 1;
                        int totalWeight = cWeight + dWeight;

                        int mixedAlpha = (cAlpha * cWeight + alpha * dWeight) / totalWeight;
                        int mixedRed = (((cColor >> 16) & 0xFF) * cWeight + ((color >> 16) & 0xFF) * dWeight) / totalWeight;
                        int mixedGreen = (((cColor >> 8) & 0xFF) * cWeight + ((color >> 8) & 0xFF) * dWeight) / totalWeight;
                        int mixedBlue = ((cColor & 0xFF) * cWeight + (color & 0xFF) * dWeight) / totalWeight;
                        int mixedLuminosity = (cLuminosity * cWeight + luminosity * dWeight) / totalWeight;
                        boolean mixedCondensed = mixedAlpha >= 220;

                        int mixedColor = (mixedAlpha << 24) | (mixedRed << 16) | (mixedGreen << 8) | mixedBlue;

                        level.setBlock(cauldronPos, ModCauldrons.COLORED_WATER_CAULDRON.get().defaultBlockState()
                                .setValue(LayeredCauldronBlock.LEVEL, newLevel)
                                .setValue(ColoredWaterCauldronBlock.CONDENSED, mixedCondensed)
                                .setValue(ColoredWaterCauldronBlock.LIGHT_LEVEL, mixedLuminosity), 3);

                        if (level.getBlockEntity(cauldronPos) instanceof ColoredWaterCauldronBlockEntity cauldronBe) {
                            cauldronBe.setCondensed(mixedCondensed);
                            cauldronBe.setLuminosity(mixedLuminosity);
                            cauldronBe.setAlpha(mixedAlpha);
                            cauldronBe.setColor(mixedColor);
                        }
                        level.levelEvent(1047, cauldronPos, 0);
                    } else if (cauldronState.getBlock() instanceof ColoredWaterCauldronBlock) {
                        if (level.getBlockEntity(cauldronPos) instanceof ColoredWaterCauldronBlockEntity cauldronBe) {
                            int currentLevel = cauldronState.getValue(LayeredCauldronBlock.LEVEL);
                            int newLevel = Math.min(3, currentLevel + 1);

                            int cColor = cauldronBe.getColor();
                            int cAlpha = cauldronBe.getAlpha();
                            int cLuminosity = cauldronBe.getLuminosity();

                            int cWeight = currentLevel * 3;
                            int dWeight = 1;
                            int totalWeight = cWeight + dWeight;

                            int mixedAlpha = (cAlpha * cWeight + alpha * dWeight) / totalWeight;
                            int mixedRed = (((cColor >> 16) & 0xFF) * cWeight + ((color >> 16) & 0xFF) * dWeight) / totalWeight;
                            int mixedGreen = (((cColor >> 8) & 0xFF) * cWeight + ((color >> 8) & 0xFF) * dWeight) / totalWeight;
                            int mixedBlue = ((cColor & 0xFF) * cWeight + (color & 0xFF) * dWeight) / totalWeight;
                            int mixedLuminosity = (cLuminosity * cWeight + luminosity * dWeight) / totalWeight;
                            boolean mixedCondensed = mixedAlpha >= 220;

                            int mixedColor = (mixedAlpha << 24) | (mixedRed << 16) | (mixedGreen << 8) | mixedBlue;

                            cauldronBe.setCondensed(mixedCondensed);
                            cauldronBe.setLuminosity(mixedLuminosity);
                            cauldronBe.setAlpha(mixedAlpha);
                            cauldronBe.setColor(mixedColor);

                            level.setBlock(cauldronPos, cauldronState.setValue(LayeredCauldronBlock.LEVEL, newLevel)
                                    .setValue(ColoredWaterCauldronBlock.CONDENSED, mixedCondensed)
                                    .setValue(ColoredWaterCauldronBlock.LIGHT_LEVEL, mixedLuminosity), 3);

                            level.levelEvent(1047, cauldronPos, 0);
                        }
                    }
                    break;
                }

                if (!cauldronState.isAir() && !(cauldronState.getBlock() instanceof PointedDripstoneBlock)) {
                    break;
                }
            }
        }
    }

    @Inject(method = "animateTick", at = @At("HEAD"))
    private void colored_water$animateTick(BlockState state, Level level, BlockPos pos, RandomSource random, CallbackInfo ci) {
        if (state.getValue(PointedDripstoneBlock.TIP_DIRECTION) != Direction.DOWN) {
            return;
        }
        if (state.getValue(PointedDripstoneBlock.THICKNESS) != DripstoneThickness.TIP && state.getValue(PointedDripstoneBlock.THICKNESS) != DripstoneThickness.TIP_MERGE) {
            return;
        }

        BlockPos sourcePos = findColoredWaterSourcePos(level, pos);
        if (sourcePos != null) {
            if (random.nextFloat() < 0.12F) {
                double x = (double) pos.getX() + 0.5D + (random.nextDouble() - 0.5D) * 0.2D;
                double y = (double) pos.getY() + 0.1D;
                double z = (double) pos.getZ() + 0.5D + (random.nextDouble() - 0.5D) * 0.2D;

                int color = 0x3F76E4;
                int luminosity = 0;
                if (level.getBlockEntity(sourcePos) instanceof ColoredWaterBlockEntity sourceBe) {
                    int c = sourceBe.getColor();
                    if (c != -1) color = c;
                    luminosity = sourceBe.getLuminosity();
                } else if (level.getBlockEntity(sourcePos) instanceof ColoredWaterCauldronBlockEntity cauldronBe) {
                    color = cauldronBe.getColor();
                    luminosity = cauldronBe.getLuminosity();
                }

                level.addParticle(new ColorParticleOptions(ModParticles.DRIPPING_DRIPSTONE_WATER.get(), color), x, y, z, 0.0, 0.0, 0.0);

                if (luminosity > 0) {
                    level.addParticle(new SparkleParticleOptions(color), x, y, z, 0.01, -0.02, 0.01);
                }
            }
        }
    }

    private static BlockPos findColoredWaterSourcePos(Level level, BlockPos pos) {
        BlockPos current = pos;
        while (level.getBlockState(current).is(Blocks.POINTED_DRIPSTONE)) {
            current = current.above();
        }
        if (isColoredWaterAt(level, current)) {
            return current;
        }
        BlockPos above = current.above();
        if (isColoredWaterAt(level, above)) {
            return above;
        }
        return null;
    }

    private static boolean isColoredWaterAt(Level level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        FluidState fluid = level.getFluidState(pos);
        return state.getBlock() instanceof ColoredWaterBlock
                || fluid.getType() instanceof BaseColorWater
                || level.getBlockEntity(pos) instanceof ColoredWaterBlockEntity;
    }

    private static BlockPos findStalactiteTipPos(Level level, BlockPos pos) {
        BlockPos current = pos;
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
}
