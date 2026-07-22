package com.cozary.colored_water.block;

import com.cozary.colored_water.block.entity.ColoredWaterBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.LiquidBlock;
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
 * Inherits from LiquidBlock for water physics and adds a BlockEntity to store the color and more.
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

    /**
     * Triggered when the block is placed.
     * Ensures the color propagation logic starts immediately.
     */
    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);
        triggerPropagation(level, pos);
    }

    /**
     * Triggered when a neighbor block changes.
     * Used to update color mixing if adjacent water blocks change.
     */
    @Override
    protected void neighborChanged(BlockState blockState, Level level, BlockPos blockPos, Block block, @Nullable Orientation orientation, boolean bol) {
        super.neighborChanged(blockState, level, blockPos, block, orientation, bol);
        triggerPropagation(level, blockPos);
    }

    /**
     * Helper to notify the BlockEntity to recalculate/propagate its color.
     */
    private void triggerPropagation(Level level, BlockPos pos) {
        if (!level.isClientSide()) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof ColoredWaterBlockEntity coloredBe) {
                coloredBe.propagateColor();
            }
        }
    }

    /**
     * Handles custom events, specifically for client-side rendering updates.
     */
    @Override
    public boolean triggerEvent(BlockState state, Level level, BlockPos pos, int id, int param) {
        if (level.isClientSide() && id == 1) {
            level.setBlocksDirty(pos, state, state);
            return true;
        }
        return super.triggerEvent(state, level, pos, id, param);
    }

    /**
     * Handles picking up the block (e.g., with a bucket).
     * <p>
     * If the block is a source (level 0), it returns a Colored Water Bucket
     * preserving the current color of the block.
     */
    @Override
    public ItemStack pickupBlock(@Nullable LivingEntity player, LevelAccessor level, BlockPos pos, BlockState state) {
        if (state.getValue(LEVEL) != 0) {
            return ItemStack.EMPTY;
        }

        int color = -1;
        boolean condensed = false;
        int luminosity = 0;
        int alpha = 0;

        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof ColoredWaterBlockEntity coloredWaterBE) {
            color = coloredWaterBE.getColor();
            condensed = coloredWaterBE.isCondensed();
            luminosity = coloredWaterBE.getLuminosity();
            alpha = (color >> 24) & 0xFF;
        }

        // Remove the block
        level.setBlock(pos, net.minecraft.world.level.block.Blocks.AIR.defaultBlockState(), 11);

        // Create the bucket item with the correct color and properties
        ItemStack stack = new ItemStack(this.fluidSupplier.get().getBucket());
        if (color != -1) {
            stack.set(DataComponents.DYED_COLOR, new DyedItemColor(color));
        }

        net.minecraft.nbt.CompoundTag tag = new net.minecraft.nbt.CompoundTag();
        tag.putBoolean("Condensed", condensed);
        tag.putInt("Luminosity", luminosity);
        if (alpha > 0) {
            tag.putInt("Alpha", alpha);
        }
        stack.set(DataComponents.CUSTOM_DATA, net.minecraft.world.item.component.CustomData.of(tag));

        return stack;
    }
}
