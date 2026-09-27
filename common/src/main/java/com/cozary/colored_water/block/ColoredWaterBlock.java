package com.cozary.colored_water.block;

import com.cozary.colored_water.block.entity.ColoredWaterBlockEntity;
import com.cozary.colored_water.util.ColoredWaterUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ColoredWaterBlockEntity(pos, state);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);
        checkLavaInteraction(level, pos);
        if (!level.isClientSide()) {
            level.scheduleTick(pos, state.getFluidState().getType(), ColoredWaterUtil.UPDATE_DELAY);
        }
        if (state.getValue(LEVEL) == 0) {
            BubbleColumnBlock.updateColumn(level, pos, state);
        }
    }

    @Override
    protected void neighborChanged(BlockState blockState, Level level, BlockPos blockPos, Block block, @Nullable Orientation orientation, boolean bol) {
        super.neighborChanged(blockState, level, blockPos, block, orientation, bol);
        checkLavaInteraction(level, blockPos);
        if (!level.isClientSide()) {
            level.scheduleTick(blockPos, blockState.getFluidState().getType(), ColoredWaterUtil.UPDATE_DELAY);
        }
        if (blockState.getValue(LEVEL) == 0) {
            BubbleColumnBlock.updateColumn(level, blockPos, blockState);
        }
    }

    private void checkLavaInteraction(Level level, BlockPos pos) {
        if (level.isClientSide()) return;
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            BlockPos neighborPos = pos.relative(direction);
            FluidState neighborFluid = level.getFluidState(neighborPos);
            if (neighborFluid.is(FluidTags.LAVA)) {
                if (neighborFluid.isSource()) {
                    level.setBlockAndUpdate(neighborPos, Blocks.OBSIDIAN.defaultBlockState());
                    level.levelEvent(1501, neighborPos, 0);
                } else if (neighborFluid.getHeight(level, neighborPos) >= 0.44444445F) {
                    level.setBlockAndUpdate(neighborPos, Blocks.COBBLESTONE.defaultBlockState());
                    level.levelEvent(1501, neighborPos, 0);
                }
            }
        }
    }

    @Override
    public boolean triggerEvent(BlockState state, Level level, BlockPos pos, int id, int param) {
        if (level.isClientSide() && id == 1) {
            level.setBlocksDirty(pos, state, state);
            return true;
        }
        return super.triggerEvent(state, level, pos, id, param);
    }

    @Override
    public ItemStack pickupBlock(@Nullable LivingEntity player, LevelAccessor level, BlockPos pos, BlockState state) {
        if (state.getValue(LEVEL) != 0) {
            return ItemStack.EMPTY;
        }

        ItemStack stack = ItemStack.EMPTY;
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof ColoredWaterBlockEntity coloredWaterBlockEntity) {
            stack = ColoredWaterUtil.createBucketStack(coloredWaterBlockEntity);
        }

        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);

        return stack.isEmpty() ? new ItemStack(this.fluidSupplier.get().getBucket()) : stack;
    }
}
