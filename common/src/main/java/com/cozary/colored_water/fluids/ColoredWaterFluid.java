package com.cozary.colored_water.fluids;

import com.cozary.colored_water.block.ColoredWaterBlock;
import com.cozary.colored_water.block.entity.ColoredWaterBlockEntity;
import com.cozary.colored_water.init.ModBlocks;
import com.cozary.colored_water.init.ModFluids;
import com.cozary.colored_water.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;

public abstract class ColoredWaterFluid extends BaseColorWater {

    public static final BooleanProperty CONDENSED = ColoredWaterBlock.CONDENSED;

    @Override
    protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
        super.createFluidStateDefinition(builder);
        builder.add(CONDENSED);
    }

    @Override
    public Fluid getSource() {
        return ModFluids.STILL_COLORED_WATER.get();
    }

    @Override
    public Fluid getFlowing() {
        return ModFluids.FLOWING_COLORED_WATER.get();
    }

    @Override
    public Item getBucket() {
        return ModItems.COLORED_WATER_BUCKET.get();
    }

    @Override
    protected BlockState createLegacyBlock(FluidState state) {
        boolean isCondensed = state.hasProperty(CONDENSED) && state.getValue(CONDENSED);
        return ModBlocks.COLORED_WATER_BLOCK.get().defaultBlockState()
                .setValue(LiquidBlock.LEVEL, state.isSource() ? 0 : Math.max(0, 8 - getAmount(state)))
                .setValue(CONDENSED, isCondensed);
    }

    @Override
    protected FluidState getNewLiquid(ServerLevel level, BlockPos pos, BlockState state) {
        FluidState newLiquid = super.getNewLiquid(level, pos, state);
        if (newLiquid.hasProperty(CONDENSED) && state.hasProperty(CONDENSED)) {
            return newLiquid.setValue(CONDENSED, state.getValue(CONDENSED));
        }
        return newLiquid;
    }

    @Override
    public void tick(ServerLevel serverLevel, BlockPos blockPos, BlockState blockState, FluidState fluidState) {
        super.tick(serverLevel, blockPos, blockState, fluidState);
        if (!serverLevel.isClientSide()) {
            BlockEntity be = serverLevel.getBlockEntity(blockPos);
            if (be instanceof ColoredWaterBlockEntity coloredBe) {
                coloredBe.propagateColor();
            }
        }
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
        if (level.isClientSide()) return;

        BlockPos sourcePos = pos.relative(direction.getOpposite());
        BlockEntity sourceBe = level.getBlockEntity(sourcePos);
        BlockEntity targetBe = level.getBlockEntity(pos);

        if (sourceBe instanceof ColoredWaterBlockEntity sBe && targetBe instanceof ColoredWaterBlockEntity tBe) {
            tBe.setLuminosity(sBe.getLuminosity());
            tBe.setCondensed(sBe.isCondensed());
            tBe.setColor(sBe.getStoredColor(), sourcePos, false);
            tBe.propagateColor();
        }
    }

    public static class Source extends ColoredWaterFluid {
        @Override
        public int getAmount(FluidState state) {
            return 8;
        }

        @Override
        public boolean isSource(FluidState state) {
            return true;
        }
    }

    public static class Flowing extends ColoredWaterFluid {
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