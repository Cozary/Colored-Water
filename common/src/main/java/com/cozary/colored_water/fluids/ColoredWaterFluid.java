package com.cozary.colored_water.fluids;

import com.cozary.colored_water.block.ColoredWaterBlock;
import com.cozary.colored_water.block.entity.ColoredWaterBlockEntity;
import com.cozary.colored_water.init.ModBlocks;
import com.cozary.colored_water.init.ModFluids;
import com.cozary.colored_water.init.ModItems;
import com.cozary.colored_water.util.ColoredWaterUtil;
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
        if (state.isEmpty()) {
            return Blocks.AIR.defaultBlockState();
        }
        boolean isCondensed = state.hasProperty(CONDENSED) && state.getValue(CONDENSED);
        int legacyLevel = 0;
        if (!state.isSource()) {
            boolean isFalling = state.hasProperty(FALLING) && state.getValue(FALLING);
            legacyLevel = 8 - Math.min(state.getAmount(), 8) + (isFalling ? 8 : 0);
        }
        return ModBlocks.COLORED_WATER_BLOCK.get().defaultBlockState()
                .setValue(LiquidBlock.LEVEL, legacyLevel)
                .setValue(CONDENSED, isCondensed);
    }

    @Override
    protected FluidState getNewLiquid(ServerLevel level, BlockPos pos, BlockState state) {
        FluidState newLiquid = super.getNewLiquid(level, pos, state);
        boolean isCondensed = false;
        if (state.hasProperty(CONDENSED)) {
            isCondensed = state.getValue(CONDENSED);
        } else {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof ColoredWaterBlockEntity coloredBe) {
                isCondensed = coloredBe.isCondensed();
            }
        }
        if (newLiquid.hasProperty(CONDENSED)) {
            return newLiquid.setValue(CONDENSED, isCondensed);
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
        if (level.isClientSide() || fluidState.isEmpty()) return;

        if (level instanceof ServerLevel serverLevel) {
            BlockState currentState = serverLevel.getBlockState(pos);
            if (currentState.isAir()) return;

            ColoredWaterBlockEntity tBe = ColoredWaterBlockEntity.getOrCreate(serverLevel, pos, currentState);
            if (tBe == null) return;

            BlockPos sourcePos = pos.relative(direction.getOpposite());
            BlockEntity sourceBe = serverLevel.getBlockEntity(sourcePos);

            if (sourceBe instanceof ColoredWaterBlockEntity sBe) {
                ColoredWaterUtil.transferProperties(sBe, tBe);
            }
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