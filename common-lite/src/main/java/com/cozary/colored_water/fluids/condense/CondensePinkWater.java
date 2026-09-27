package com.cozary.colored_water.fluids.condense;

import com.cozary.colored_water.fluids.BaseColorWater;
import com.cozary.colored_water.init.ModBlocks;
import com.cozary.colored_water.init.ModFluids;
import com.cozary.colored_water.init.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;

public abstract class CondensePinkWater extends BaseColorWater {
    public CondensePinkWater() {
    }


    @Override
    public Fluid getSource() {
        return ModFluids.STILL_CONDENSE_PINK_WATER.get();
    }

    @Override
    public Fluid getFlowing() {
        return ModFluids.FLOWING_CONDENSE_PINK_WATER.get();
    }

    @Override
    public Item getBucket() {
        return ModItems.CONDENSE_PINK_WATER_BUCKET.get();
    }

    @Override
    protected BlockState createLegacyBlock(FluidState pState) {

        return ModBlocks.CONDENSE_PINK_WATER_BLOCK.get().defaultBlockState().setValue(LiquidBlock.LEVEL, FlowingFluid.getLegacyLevel(pState));
    }

    public static class Source extends CondensePinkWater {
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

    public static class Flowing extends CondensePinkWater {
        public Flowing() {
        }

        @Override
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> pBuilder) {
            super.createFluidStateDefinition(pBuilder);
            pBuilder.add(FlowingFluid.LEVEL);
        }

        @Override
        public int getAmount(FluidState pState) {
            return pState.getValue(FlowingFluid.LEVEL);
        }

        @Override
        public boolean isSource(FluidState pState) {
            return false;
        }
    }
}