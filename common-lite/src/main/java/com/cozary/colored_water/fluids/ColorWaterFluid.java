package com.cozary.colored_water.fluids;

import com.cozary.colored_water.init.ModParticles;
import com.cozary.colored_water.particles.ColorParticleOptions;
import com.cozary.colored_water.particles.SparkleParticleOptions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;

import java.util.function.Supplier;

public abstract class ColorWaterFluid extends BaseColorWater {

    protected final Supplier<? extends Fluid> sourceSupplier;
    protected final Supplier<? extends Fluid> flowingSupplier;
    protected final Supplier<? extends Item> bucketSupplier;
    protected final Supplier<? extends Block> blockSupplier;
    protected final net.minecraft.world.item.DyeColor dyeColor;
    protected final int tintColor;
    protected final int sparkleColor;
    protected final boolean condensed;

    public ColorWaterFluid(Supplier<? extends Fluid> sourceSupplier, Supplier<? extends Fluid> flowingSupplier, Supplier<? extends Item> bucketSupplier, Supplier<? extends Block> blockSupplier, net.minecraft.world.item.DyeColor dyeColor, int tintColor, int sparkleColor, boolean condensed) {
        this.sourceSupplier = sourceSupplier;
        this.flowingSupplier = flowingSupplier;
        this.bucketSupplier = bucketSupplier;
        this.blockSupplier = blockSupplier;
        this.dyeColor = dyeColor;
        this.tintColor = tintColor;
        this.sparkleColor = sparkleColor;
        this.condensed = condensed;
    }

    @Override
    public Fluid getSource() {
        return this.sourceSupplier.get();
    }

    @Override
    public Fluid getFlowing() {
        return this.flowingSupplier.get();
    }

    @Override
    public Item getBucket() {
        return this.bucketSupplier.get();
    }

    @Override
    protected BlockState createLegacyBlock(FluidState state) {
        return this.blockSupplier.get().defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(state));
    }

    @Override
    public ParticleOptions getDripParticle() {
        return new ColorParticleOptions(ModParticles.DRIPPING_WATER.get(), this.tintColor);
    }

    @Override
    public void animateTick(Level level, BlockPos blockPos, FluidState fluidState, RandomSource random) {
        if (!fluidState.isSource() && !fluidState.getValue(FALLING)) {
            if (random.nextInt(64) == 0) {
                level.playLocalSound((double) blockPos.getX() + 0.5, (double) blockPos.getY() + 0.5, (double) blockPos.getZ() + 0.5, SoundEvents.WATER_AMBIENT, SoundSource.BLOCKS, random.nextFloat() * 0.25F + 0.75F, random.nextFloat() + 0.5F, false);
            }
        } else if (random.nextInt(10) == 0) {
            level.addParticle(new ColorParticleOptions(ModParticles.UNDERWATER.get(), this.tintColor),
                    (double) blockPos.getX() + random.nextDouble(),
                    (double) blockPos.getY() + random.nextDouble(),
                    (double) blockPos.getZ() + random.nextDouble(),
                    0.0, 0.0, 0.0);
        }

        if (this.sparkleColor != -1 && level.random.nextFloat() < 0.4F) {
            level.addParticle(SparkleParticleOptions.of(this.sparkleColor),
                    (double) blockPos.getX() + random.nextDouble(),
                    (double) blockPos.getY() + random.nextDouble(),
                    (double) blockPos.getZ() + random.nextDouble(),
                    0.01, 0.01, 0.01);
        }
    }

    public net.minecraft.world.item.DyeColor getDyeColor() {
        return this.dyeColor;
    }

    public int getTintColor() {
        return this.tintColor;
    }

    public boolean isCondensed() {
        return this.condensed;
    }

    public int getSparkleColor() {
        return this.sparkleColor;
    }

    public boolean isLuminous() {
        return this.sparkleColor != -1;
    }

    @Override
    protected void spreadTo(LevelAccessor level, BlockPos pos, BlockState blockState, Direction direction, FluidState fluidState) {
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
            return;
        }

        super.spreadTo(level, pos, blockState, direction, fluidState);
    }

    public static class Source extends ColorWaterFluid {
        public Source(Supplier<? extends Fluid> sourceSupplier, Supplier<? extends Fluid> flowingSupplier, Supplier<? extends Item> bucketSupplier, Supplier<? extends Block> blockSupplier, net.minecraft.world.item.DyeColor dyeColor, int tintColor, int sparkleColor, boolean condensed) {
            super(sourceSupplier, flowingSupplier, bucketSupplier, blockSupplier, dyeColor, tintColor, sparkleColor, condensed);
        }

        @Override
        public int getAmount(FluidState state) {
            return 8;
        }

        @Override
        public boolean isSource(FluidState state) {
            return true;
        }
    }

    public static class Flowing extends ColorWaterFluid {
        public Flowing(Supplier<? extends Fluid> sourceSupplier, Supplier<? extends Fluid> flowingSupplier, Supplier<? extends Item> bucketSupplier, Supplier<? extends Block> blockSupplier, net.minecraft.world.item.DyeColor dyeColor, int tintColor, int sparkleColor, boolean condensed) {
            super(sourceSupplier, flowingSupplier, bucketSupplier, blockSupplier, dyeColor, tintColor, sparkleColor, condensed);
        }

        @Override
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getAmount(FluidState state) {
            return state.getValue(LEVEL);
        }

        @Override
        public boolean isSource(FluidState state) {
            return false;
        }
    }
}
