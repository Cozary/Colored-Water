package com.cozary.colored_water.fluids.luminousCondense;

import com.cozary.colored_water.fluids.BaseColorWater;
import com.cozary.colored_water.init.ModBlocks;
import com.cozary.colored_water.init.ModFluids;
import com.cozary.colored_water.init.ModItems;
import com.cozary.colored_water.particles.SparkleParticleOptions;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;

public abstract class LuminousCondenseOrangeWater extends BaseColorWater {
    public LuminousCondenseOrangeWater() {
    }

    @Override
    public void animateTick(Level level, BlockPos blockPos, FluidState fluidState, RandomSource random) {
        super.animateTick(level, blockPos, fluidState, random);
        if (level.random.nextFloat() < 0.4F) {
            level.addParticle(new SparkleParticleOptions(0xF9801D), (double) blockPos.getX() + random.nextDouble(), (double) blockPos.getY() + random.nextDouble(), (double) blockPos.getZ() + random.nextDouble(), 0.01, 0.01, 0.01);
        }
    }

    @Override
    public Fluid getSource() {
        return ModFluids.STILL_LUMINOUS_CONDENSE_ORANGE_WATER.get();
    }

    @Override
    public Fluid getFlowing() {
        return ModFluids.FLOWING_LUMINOUS_CONDENSE_ORANGE_WATER.get();
    }

    @Override
    public Item getBucket() {
        return ModItems.LUMINOUS_CONDENSE_ORANGE_WATER_BUCKET.get();
    }

    @Override
    protected BlockState createLegacyBlock(FluidState pState) {

        return ModBlocks.LUMINOUS_CONDENSE_ORANGE_WATER_BLOCK.get().defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(pState));
    }

    public static class Source extends LuminousCondenseOrangeWater {
        public Source() {
        }

        @Override
        public int getAmount(FluidState fluidState) {
            return 8;
        }

        @Override
        public boolean isSource(FluidState fluidState) {
            return true;
        }
    }

    public static class Flowing extends LuminousCondenseOrangeWater {
        public Flowing() {
        }

        @Override
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> pBuilder) {
            super.createFluidStateDefinition(pBuilder);
            pBuilder.add(LEVEL);
        }

        @Override
        public int getAmount(FluidState pState) {
            return pState.getValue(LEVEL);
        }

        @Override
        public boolean isSource(FluidState pState) {
            return false;
        }
    }
}