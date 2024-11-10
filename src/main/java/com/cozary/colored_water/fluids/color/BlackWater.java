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
import net.minecraftforge.fluids.FluidType;


public abstract class BlackWater extends BaseColorWater {

    public BlackWater() {
    }

    @Override
    public FluidType getFluidType() {
        return ModFluidTypes.BLACK_WATER_TYPE.get();
    }

    @Override
    public Fluid getSource() {
        return ModFluids.STILL_BLACK_WATER.get();
    }

    @Override
    public Fluid getFlowing() {
        return ModFluids.FLOWING_BLACK_WATER.get();
    }

    @Override
    public Item getBucket() {
        return ModItems.BLACK_WATER_BUCKET.get();
    }

    @Override
    public BlockState createLegacyBlock(FluidState pState) {
        // getBlockStateLevel converts the LEVEL_1_8 of the fluid state to the LEVEL_15 the fluid block uses
        return ModBlocks.BLACK_WATER_BLOCK.get().defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(pState));
    }

    public static class Source extends BlackWater {
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

    public static class Flowing extends BlackWater {
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
