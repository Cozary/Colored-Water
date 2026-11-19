package com.cozary.colored_water.block;

import com.cozary.colored_water.block.entity.ColoredWaterBlockEntity;
import com.cozary.colored_water.util.ColoredWaterUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.redstone.Orientation;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * LiquidBlock implementation for colored water fluid.
 * <p>
 * - Manages water physics, light levels, and condensed states.
 * - Handles lava collisions to generate obsidian or cobblestone.
 * - Updates bubble column mechanics for Soul Sand and Magma blocks.
 * - Preserves color, luminosity, and condensed properties when picked up with a bucket.
 */
public class ColoredWaterBlock extends LiquidBlock implements EntityBlock {

    public static final BooleanProperty CONDENSED = BooleanProperty.create("condensed");
    public static final IntegerProperty LIGHT_LEVEL = IntegerProperty.create("luminosity", 0, 15);

    private final Supplier<? extends FlowingFluid> fluidSupplier;

    public ColoredWaterBlock(Supplier<? extends FlowingFluid> fluid, Properties properties) {
        super(fluid.get(), properties);
        this.fluidSupplier = fluid;
        this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, 0).setValue(CONDENSED, false).setValue(LIGHT_LEVEL, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(CONDENSED, LIGHT_LEVEL);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        FluidState fluidState = super.getFluidState(state);
        if (fluidState.hasProperty(CONDENSED) && state.hasProperty(CONDENSED)) {
            return fluidState.setValue(CONDENSED, state.getValue(CONDENSED));
        }
        return fluidState;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPosition, BlockState state) {
        return new ColoredWaterBlockEntity(blockPosition, state);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos blockPosition, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, blockPosition, oldState, isMoving);
        handleFluidPlacementOrNeighborUpdate(state, level, blockPosition);
    }

    @Override
    protected void neighborChanged(BlockState blockState, Level level, BlockPos blockPos, Block block, @Nullable Orientation orientation, boolean bol) {
        super.neighborChanged(blockState, level, blockPos, block, orientation, bol);
        handleFluidPlacementOrNeighborUpdate(blockState, level, blockPos);
    }

    private void handleFluidPlacementOrNeighborUpdate(BlockState state, Level level, BlockPos pos) {
        checkLavaInteraction(level, pos);
        if (level.isClientSide()) {
            return;
        }
        level.scheduleTick(pos, state.getFluidState().getType(), ColoredWaterUtil.UPDATE_DELAY);
        if (state.getValue(LEVEL) == 0) {
            BlockState below = level.getBlockState(pos.below());
            if (below.is(BlockTags.ENABLES_BUBBLE_COLUMN_PUSH_UP) || below.is(BlockTags.ENABLES_BUBBLE_COLUMN_DRAG_DOWN) || below.is(Blocks.BUBBLE_COLUMN)) {
                BubbleColumnBlock.updateColumn(Blocks.BUBBLE_COLUMN, level, pos, below);
            }
        }
    }

    private void checkLavaInteraction(Level level, BlockPos blockPosition) {
        if (level.isClientSide()) return;
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            BlockPos neighborPosition = blockPosition.relative(direction);
            FluidState neighborFluid = level.getFluidState(neighborPosition);
            if (neighborFluid.is(FluidTags.LAVA)) {
                if (neighborFluid.isSource()) {
                    level.setBlockAndUpdate(neighborPosition, Blocks.OBSIDIAN.defaultBlockState());
                    level.levelEvent(1501, neighborPosition, 0);
                } else if (neighborFluid.getHeight(level, neighborPosition) >= 0.44444445F) {
                    level.setBlockAndUpdate(neighborPosition, Blocks.COBBLESTONE.defaultBlockState());
                    level.levelEvent(1501, neighborPosition, 0);
                }
            }
        }
    }

    @Override
    public boolean triggerEvent(BlockState state, Level level, BlockPos blockPosition, int id, int param) {
        if (level.isClientSide() && id == 1) {
            level.setBlocksDirty(blockPosition, state, state);
            return true;
        }
        return super.triggerEvent(state, level, blockPosition, id, param);
    }

    @Override
    public ItemStack pickupBlock(@Nullable LivingEntity player, LevelAccessor level, BlockPos blockPosition, BlockState state) {
        if (state.getValue(LEVEL) != 0) {
            return ItemStack.EMPTY;
        }

        ItemStack stack = ItemStack.EMPTY;
        BlockEntity blockEntity = level.getBlockEntity(blockPosition);
        if (blockEntity instanceof ColoredWaterBlockEntity coloredWaterBlockEntity) {
            stack = ColoredWaterUtil.createBucketStack(coloredWaterBlockEntity);
        }

        level.setBlock(blockPosition, Blocks.AIR.defaultBlockState(), 11);

        return stack.isEmpty() ? new ItemStack(this.fluidSupplier.get().getBucket()) : stack;
    }
}
