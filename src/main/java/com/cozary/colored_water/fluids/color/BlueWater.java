package com.cozary.colored_water.fluids.color;

import com.cozary.colored_water.fluids.BaseColorWater;
import com.cozary.colored_water.init.ModBlocks;
import com.cozary.colored_water.init.ModFluidTypes;
import com.cozary.colored_water.init.ModFluids;
import com.cozary.colored_water.init.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.fluids.FluidType;


public abstract class BlueWater extends BaseColorWater {
    public BlueWater() {
    }

    @Override
    public FluidType getFluidType() {
        return ModFluidTypes.BLUE_WATER_TYPE.get();
    }

    @Override
    public Fluid getSource() {
        return ModFluids.STILL_BLUE_WATER.get();
    }

    @Override
    public Fluid getFlowing() {
        return ModFluids.FLOWING_BLUE_WATER.get();
    }

    @Override
    public Item getBucket() {
        return ModItems.BLUE_WATER_BUCKET.get();
    }

    @Override
    protected BlockState createLegacyBlock(FluidState pState) {
        // getBlockStateLevel converts the LEVEL_1_8 of the fluid state to the LEVEL_15 the fluid block uses
        return ModBlocks.BLUE_WATER_BLOCK.get().defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(pState));
    }

    public static class Source extends BlueWater {
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

    public static class Flowing extends BlueWater {
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
