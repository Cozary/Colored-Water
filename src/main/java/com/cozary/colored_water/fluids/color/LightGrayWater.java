package com.cozary.colored_water.fluids.color;

import com.cozary.colored_water.fluids.BaseColorWater;
import com.cozary.colored_water.init.ModBlocks;
import com.cozary.colored_water.init.ModFluids;
import com.cozary.colored_water.init.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;

public abstract class LightGrayWater extends BaseColorWater {
    @Override
    public net.minecraft.fluid.Fluid getStill() {
        return ModFluids.STILL_LIGHT_GRAY_WATER;
    }

    @Override
    public net.minecraft.fluid.Fluid getFlowing() {
        return ModFluids.FLOWING_LIGHT_GRAY_WATER;
    }

    @Override
    public Item getBucketItem() {
        return ModItems.LIGHT_GRAY_WATER_BUCKET;
    }

    @Override
    protected BlockState toBlockState(FluidState fluidState) {
        // getBlockStateLevel converts the LEVEL_1_8 of the fluid state to the LEVEL_15 the fluid block uses
        return ModBlocks.LIGHT_GRAY_WATER_BLOCK.getDefaultState().with(Properties.LEVEL_15, getBlockStateLevel(fluidState));
    }

    public static class Flowing extends LightGrayWater {
        @Override
        protected void appendProperties(StateManager.Builder<net.minecraft.fluid.Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getLevel(FluidState fluidState) {
            return fluidState.get(LEVEL);
        }

        @Override
        public boolean isStill(FluidState fluidState) {
            return false;
        }
    }

    public static class Still extends LightGrayWater {
        @Override
        public int getLevel(FluidState fluidState) {
            return 8;
        }

        @Override
        public boolean isStill(FluidState fluidState) {
            return true;
        }
    }
}
