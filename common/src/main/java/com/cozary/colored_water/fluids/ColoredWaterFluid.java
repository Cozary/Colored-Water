package com.cozary.colored_water.fluids;

import com.cozary.colored_water.block.entity.ColoredWaterBlockEntity;
import com.cozary.colored_water.init.ModBlocks;
import com.cozary.colored_water.init.ModFluids;
import com.cozary.colored_water.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;

public abstract class ColoredWaterFluid extends BaseColorWater {

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
        return ModBlocks.COLORED_WATER_BLOCK.get().defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(state));
    }

    /**
     * Called every tick. Used here to ensure the BlockEntity keeps propagating color
     * as the fluid exists in the world.
     */
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

    /**
     * Called when fluid spreads to a new block.
     * <p>
     * This is critical for initializing the color of the newly created fluid block
     * based on the source block it flowed from.
     */
    @Override
    protected void spreadTo(LevelAccessor level, BlockPos pos, BlockState blockState, Direction direction, FluidState fluidState) {
        super.spreadTo(level, pos, blockState, direction, fluidState);
        if (level.isClientSide()) return;

        // Identify the source block that caused this spread
        BlockPos sourcePos = pos.relative(direction.getOpposite());
        BlockEntity sourceBe = level.getBlockEntity(sourcePos);
        BlockEntity targetBe = level.getBlockEntity(pos);

        // Transfer color data from source to the new target block
        if (sourceBe instanceof ColoredWaterBlockEntity sBe && targetBe instanceof ColoredWaterBlockEntity tBe) {
            if (sBe.isInitialized() && !tBe.isInitialized()) {
                BlockPos parent = sBe.getSourcePos();
                // If the neighbor is a source block itself, it becomes the parent
                if (level.getBlockState(sourcePos).getValue(LiquidBlock.LEVEL) == 0) {
                    parent = sourcePos;
                }
                tBe.setColor(sBe.getColor(), parent, true);
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
        protected void createFluidStateDefinition(net.minecraft.world.level.block.state.StateDefinition.Builder<Fluid, FluidState> builder) {
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