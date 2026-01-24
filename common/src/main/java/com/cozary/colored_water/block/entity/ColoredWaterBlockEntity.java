package com.cozary.colored_water.block.entity;

import com.cozary.colored_water.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;

/**
 * BlockEntity responsible for managing the color state and mixing logic of Colored Water blocks.
 * <p>
 * This entity handles:
 * <ul>
 *     <li>Storing the current RGB color of the water block.</li>
 *     <li>Calculating color mixing based on neighboring blocks (up, down, sides).</li>
 *     <li>Propagating color changes to adjacent water blocks to simulate fluid mixing.</li>
 *     <li>Tracking the source of the water flow to prevent infinite loops or incorrect upstream propagation.</li>
 *     <li>Persisting state (color, source position, flags).</li>
 * </ul>
 */
public class ColoredWaterBlockEntity extends BlockEntity {

    private static final int UPDATE_DELAY = 3; // Delay in ticks before processing a scheduled update to allow neighbors to settle.
    private static final float LERP_SPEED = 0.5F; // Speed at which the color interpolates towards the target color (0.0 to 1.0).
    private static final int DEFAULT_COLOR = 0x3F76E4;
    private int color = -1;
    private int lastClientColor = -1;
    private boolean isPlacedByBucket = false;
    private BlockPos sourcePos = null;
    private boolean mixedAsSource = false;
    private boolean isCalculating = false;

    public ColoredWaterBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.COLORED_WATER_BE.get(), pos, blockState);
    }

    /**
     * Retrieves the current color of the water.
     * <p>
     * If the color is uninitialized or needs recalculation (e.g., it's a new source),
     * this method triggers a calculation based on the environment.
     *
     * @return The integer RGB color.
     */
    public int getColor() {
        if (level == null) return color;

        BlockState state = getBlockState();
        boolean isSource = isSourceBlock(state);
        // Recalculate if not set, or if it's a natural source that hasn't mixed yet.
        boolean needsCalculation = (color == -1) || (isSource && !isPlacedByBucket && !mixedAsSource);

        if (needsCalculation) {
            if (isCalculating) return -1; // Prevent recursion
            isCalculating = true;
            try {
                int target = calculateTargetColor(level, worldPosition, state);
                int result = target == -1 ? DEFAULT_COLOR : target;

                if (isSource && !isPlacedByBucket) {
                    if (this.color != result) {
                        this.color = result;
                    }
                    this.mixedAsSource = true;
                }
                return result;
            } finally {
                isCalculating = false;
            }
        }
        return color;
    }

    /**
     * Sets the color of this block, assuming it's a self-sourced change (like a bucket placement).
     *
     * @param color The new RGB color.
     */
    public void setColor(int color) {
        setColor(color, this.sourcePos, true);
    }

    public boolean isInitialized() {
        return color != -1;
    }

    public BlockPos getSourcePos() {
        return sourcePos;
    }

    public void markAsPlacedByBucket() {
        this.isPlacedByBucket = true;
        this.sourcePos = null;
        markUpdated();
    }

    protected void markUpdated() {
        setChanged();
        if (level != null) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    /**
     * Handles an update signal from a specific neighbor position.
     * <p>
     * This is typically called when a neighbor changes and might need to update this block's
     * flow source hierarchy.
     *
     * @param incomingSource The position of the neighbor triggering the update.
     */
    public void handleUpdateFrom(BlockPos incomingSource) {
        if (level == null) return;
        boolean adopted = false;

        // If we aren't a bucket-placed source, we might adopt this new neighbor as our source
        if (!this.isPlacedByBucket && incomingSource != null) {
            if (this.sourcePos == null) {
                this.sourcePos = incomingSource;
                adopted = true;
            } else if (!this.sourcePos.equals(incomingSource) && !isValidSource(this.sourcePos)) {
                // If our current source is invalid (e.g. removed), switch to the new one
                this.sourcePos = incomingSource;
                adopted = true;
            }
        }

        if (adopted) markUpdated();
        level.scheduleTick(worldPosition, getBlockState().getFluidState().getType(), UPDATE_DELAY);
    }

    /**
     * Core logic for updating the block's color.
     *
     * @param color          The proposed new color.
     * @param incomingSource The source of this color change.
     * @param force          If true, overrides checks like small color differences or bucket locks.
     */
    public void setColor(int color, BlockPos incomingSource, boolean force) {
        if (level == null) return;

        boolean isSource = isSourceBlock(getBlockState());

        // Reset mixing flag if we are no longer a source or if our source parent changed
        if (!isSource) {
            mixedAsSource = false;
        } else if (!isPlacedByBucket && incomingSource != null && sourcePos != null && !sourcePos.equals(incomingSource)) {
            if (mixedAsSource) {
                mixedAsSource = false;
                level.scheduleTick(worldPosition, getBlockState().getFluidState().getType(), UPDATE_DELAY);
            }
        }

        boolean acceptChange = shouldAcceptChange(incomingSource, force);
        boolean colorChanged = false;

        if (acceptChange) {
            boolean needsMixing = (this.color == -1) || (isSource && !isPlacedByBucket);

            if (needsMixing) {
                // Natural sources mix colors from surroundings
                if (isSource && !isPlacedByBucket) {
                    int target = calculateTargetColor(level, worldPosition, getBlockState());
                    int newColor = (target != -1) ? target : color;
                    if (this.color != newColor) {
                        this.color = newColor;
                        colorChanged = true;
                    }
                    mixedAsSource = true;
                } else if (this.color != color) {
                    // Flowing blocks just take the color
                    this.color = color;
                    colorChanged = true;
                }
            } else if (isPlacedByBucket) {
                // Bucket sources only change if explicitly set (usually via force=true)
                if (this.color != color) {
                    this.color = color;
                    colorChanged = true;
                }
            } else {
                // Ignore negligible color differences to prevent constant updates
                if (force || isColorDiffSmall(this.color, color)) {
                    if (this.color != color) {
                        this.color = color;
                        colorChanged = true;
                    }
                } else {
                    // Even if color didn't change enough, ensure we tick to keep flow alive. TODO idk if rly necessary
                    level.scheduleTick(worldPosition, getBlockState().getFluidState().getType(), UPDATE_DELAY);
                }
            }
        }

        // If the color changed, or we accepted a new source hierarchy, propagate to neighbors
        if (colorChanged || acceptChange || force) {
            markUpdated();
            propagateToNeighbors();
        }
    }

    /**
     * Determines if this block should accept a color/state change from the given source.
     * <p>
     * Also handles "adopting" a new source parent if the current one is missing or invalid.
     */
    private boolean shouldAcceptChange(BlockPos incomingSource, boolean force) {
        if (force) {
            // Force updates can re-parent the block
            if (!isPlacedByBucket && incomingSource != null) {
                if (sourcePos == null || !sourcePos.equals(incomingSource)) {
                    sourcePos = incomingSource;
                    return true; // Adopted
                }
            }
            return true;
        }

        // Bucket sources don't accept changes from neighbors
        if (isPlacedByBucket) {
            return incomingSource == null;
        }

        if (incomingSource != null) {
            if (sourcePos == null) {
                sourcePos = incomingSource;
                return true; // Adopted (was orphan)
            } else if (sourcePos.equals(incomingSource)) {
                return true; // Update from current parent
            } else if (!isValidSource(sourcePos)) {
                sourcePos = incomingSource;
                return true; // Adopted (old parent invalid)
            }
        } else {
            // Internal update or no specific source
            if (sourcePos == null || !isValidSource(sourcePos)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the difference between two colors is small enough to be ignored.
     */
    private boolean isColorDiffSmall(int c1, int c2) {
        int rDiff = Math.abs(((c1 >> 16) & 0xFF) - ((c2 >> 16) & 0xFF));
        int gDiff = Math.abs(((c1 >> 8) & 0xFF) - ((c2 >> 8) & 0xFF));
        int bDiff = Math.abs((c1 & 0xFF) - (c2 & 0xFF));
        return (rDiff + gDiff + bDiff) < 100;
    }

    /**
     * Pushes the current color state to valid neighboring water blocks.
     * <p>
     * Follows fluid physics: flows down, and flows horizontally if level permits.
     */
    private void propagateToNeighbors() {
        if (level == null) return;
        level.scheduleTick(worldPosition, getBlockState().getFluidState().getType(), UPDATE_DELAY);

        int myLevel = getBlockState().getValue(LiquidBlock.LEVEL);
        // If I am a source (level 0), I am the parent. Otherwise, pass my parent along.
        BlockPos parentToPass = (myLevel == 0) ? worldPosition : this.sourcePos;

        for (Direction dir : Direction.values()) {
            BlockPos neighborPos = worldPosition.relative(dir);
            BlockState neighborState = level.getBlockState(neighborPos);

            if (neighborState.getBlock() == getBlockState().getBlock()) {
                int neighborLevel = neighborState.getValue(LiquidBlock.LEVEL);
                if (neighborLevel == 0) continue; // Don't overwrite other source blocks

                // Determine if fluid physically flows to this neighbor
                boolean shouldPush = (myLevel == 0 || myLevel == 8) || // Source or falling full block
                        (dir == Direction.DOWN) ||        // Gravity
                        (neighborLevel > myLevel);        // Flowing downhill (higher level value = lower fluid height)

                if (shouldPush) {
                    BlockEntity be = level.getBlockEntity(neighborPos);
                    if (be instanceof ColoredWaterBlockEntity coloredWaterBE) {
                        if (myLevel == 0) {
                            // Source pushing to neighbor: update color if different or if hierarchy needs fixing
                            if (coloredWaterBE.color != this.color || (coloredWaterBE.sourcePos != null && !coloredWaterBE.sourcePos.equals(parentToPass))) {
                                coloredWaterBE.setColor(this.color, parentToPass, false);
                            }
                        } else {
                            // Flowing block pushing: just notify
                            coloredWaterBE.handleUpdateFrom(parentToPass);
                        }
                    }
                }
            }
        }
        notifyNeighborsToTick();
    }

    /**
     * Verifies if a position contains a valid source block for this entity.
     */
    private boolean isValidSource(BlockPos pos) {
        if (level == null) return false;
        if (!level.isLoaded(pos)) return true; // Assume valid if unloaded to prevent breaking on chunk borders
        BlockState state = level.getBlockState(pos);
        // Must be same block type and a source (level 0)
        return state.getBlock() == getBlockState().getBlock() && state.getValue(LiquidBlock.LEVEL) == 0;
    }

    /**
     * Schedules ticks for all water neighbors to ensure the fluid engine processes changes.
     */
    private void notifyNeighborsToTick() {
        if (level == null) return;
        level.scheduleTick(worldPosition, getBlockState().getFluidState().getType(), UPDATE_DELAY);
        for (Direction dir : Direction.values()) {
            BlockPos neighborPos = worldPosition.relative(dir);
            FluidState neighborFluid = level.getFluidState(neighborPos);
            if (neighborFluid.is(FluidTags.WATER)) {
                level.scheduleTick(neighborPos, neighborFluid.getType(), UPDATE_DELAY);
            }
        }
    }

    /**
     * Public entry point to trigger color propagation/mixing from the block class.
     */
    public void propagateColor() {
        if (level != null && !level.isClientSide()) {
            int targetColor = calculateTargetColor(level, worldPosition, getBlockState());

            if (this.color == -1) {
                // First time initialization
                if (targetColor == -1 && isSourceBlock(getBlockState())) {
                    targetColor = DEFAULT_COLOR;
                }
                if (targetColor != -1) {
                    this.setColor(targetColor);
                }
                return;
            }

            // Smoothly transition to new target color
            if (this.color != targetColor) {
                int rNew = (int) Mth.lerp(LERP_SPEED, (this.color >> 16) & 0xFF, (targetColor >> 16) & 0xFF);
                int gNew = (int) Mth.lerp(LERP_SPEED, (this.color >> 8) & 0xFF, (targetColor >> 8) & 0xFF);
                int bNew = (int) Mth.lerp(LERP_SPEED, this.color & 0xFF, targetColor & 0xFF);
                this.setColor((rNew << 16) | (gNew << 8) | bNew);
            }
        }
    }

    /**
     * Calculates the weighted average color based on surrounding blocks.
     * <p>
     * Weights:
     * <ul>
     *     <li>Up (Falling water): High weight (1000)</li>
     *     <li>Down (Source below): Medium weight (200)</li>
     *     <li>Sides: Variable weight based on flow direction</li>
     * </ul>
     *
     * @return The calculated target color, or -1 if no colored neighbors found.
     */
    private int calculateTargetColor(Level level, BlockPos pos, BlockState state) {
        boolean isSource = isSourceBlock(state);
        // If we are a bucket source and already have a color, stick to it.
        if (isSource && isPlacedByBucket && this.color != -1) return this.color;

        long rSum = 0, gSum = 0, bSum = 0, totalWeight = 0;
        boolean hasUp = false;

        // 1. Check UP (Water falling into this block)
        if (!isSource) {
            BlockPos upPos = pos.above();
            BlockState upState = level.getBlockState(upPos);
            if (isCompatibleFluid(upState)) {
                int upColor = getBlockColor(level, upPos, upState);
                if (upColor != -1) {
                    hasUp = true;
                    totalWeight += 1000;
                    rSum += ((upColor >> 16) & 0xFF) * 1000L;
                    gSum += ((upColor >> 8) & 0xFF) * 1000L;
                    bSum += (upColor & 0xFF) * 1000L;
                }
            }
        }

        // 2. Check DOWN (Mixing with water below)
        if (!isSource) {
            BlockPos downPos = pos.below();
            BlockState downState = level.getBlockState(downPos);
            if (isCompatibleFluid(downState) && downState.getFluidState().isSource()) {
                int downColor = getBlockColor(level, downPos, downState);
                if (downColor != -1) {
                    totalWeight += 200;
                    rSum += ((downColor >> 16) & 0xFF) * 200L;
                    gSum += ((downColor >> 8) & 0xFF) * 200L;
                    bSum += (downColor & 0xFF) * 200L;
                }
            }
        }

        // 3. Check SIDES (Horizontal mixing)
        // Only mix sides if not being dominated by water from above
        if (!hasUp) {
            int myLevel = state.getValue(LiquidBlock.LEVEL);
            for (Direction dir : Direction.Plane.HORIZONTAL) {
                BlockPos neighborPos = pos.relative(dir);
                BlockState neighborState = level.getBlockState(neighborPos);

                if (isCompatibleFluid(neighborState)) {
                    int neighborLevel = getEffectiveLevel(level, neighborPos, neighborState);
                    int neighborColor = getBlockColor(level, neighborPos, neighborState);

                    if (neighborColor != -1) {
                        int weight = calculateSideWeight(isSource, myLevel, neighborLevel);
                        if (weight > 0) {
                            totalWeight += weight;
                            rSum += ((neighborColor >> 16) & 0xFF) * (long) weight;
                            gSum += ((neighborColor >> 8) & 0xFF) * (long) weight;
                            bSum += (neighborColor & 0xFF) * (long) weight;
                        }
                    }
                }
            }
        }

        if (totalWeight > 0) {
            return (int) ((rSum / totalWeight) << 16 | (gSum / totalWeight) << 8 | (bSum / totalWeight));
        }

        return this.color != -1 ? this.color : DEFAULT_COLOR;
    }

    private boolean isCompatibleFluid(BlockState state) {
        return state.getBlock() == getBlockState().getBlock() || state.getFluidState().is(FluidTags.WATER);
    }

    private boolean isSourceBlock(BlockState state) {
        return state.hasProperty(LiquidBlock.LEVEL) && state.getValue(LiquidBlock.LEVEL) == 0;
    }

    /**
     * Normalizes fluid levels for calculation.
     * Level 8 is a falling block, which acts like a full block (0) if it's an impact point.
     */
    private int getEffectiveLevel(Level level, BlockPos pos, BlockState state) {
        if (state.hasProperty(LiquidBlock.LEVEL)) {
            int lvl = state.getValue(LiquidBlock.LEVEL);
            if (lvl == 8) {
                // Check if it's an impact point (water above) or start of fall
                return level.getBlockState(pos.above()).getFluidState().is(FluidTags.WATER) ? 0 : 999;
            }
            return lvl;
        }
        return state.getFluidState().isSource() ? 0 : 8 - state.getFluidState().getAmount();
    }

    private int calculateSideWeight(boolean isSource, int myLevel, int neighborLevel) {
        if (isSource) {
            // Sources only care about other sources
            return neighborLevel == 0 ? 200 : 0;
        }
        if (neighborLevel == 0) return 200; // Strong pull from sources
        if (neighborLevel < myLevel) return (8 - neighborLevel) * 20; // Upstream (lower level value = higher fluid)
        if (neighborLevel == myLevel) return (8 - neighborLevel) * 10; // Parallel flow
        return 0;
    }

    private int getBlockColor(Level level, BlockPos pos, BlockState state) {
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof ColoredWaterBlockEntity coloredWaterBE) {
            return coloredWaterBE.getColor();
        }
        return state.getFluidState().is(FluidTags.WATER) ? DEFAULT_COLOR : -1;
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.color = input.getIntOr("Color", -1);
        this.isPlacedByBucket = input.getBooleanOr("PlacedByBucket", false);
        this.mixedAsSource = input.getBooleanOr("MixedAsSource", false);

        int sx = input.getIntOr("SourceX", Integer.MIN_VALUE);
        int sy = input.getIntOr("SourceY", Integer.MIN_VALUE);
        int sz = input.getIntOr("SourceZ", Integer.MIN_VALUE);
        this.sourcePos = (sx != Integer.MIN_VALUE) ? new BlockPos(sx, sy, sz) : null;

        // If on client and color changed, force a render update
        if (this.level != null && this.level.isClientSide() && this.color != this.lastClientColor) {
            markUpdated();
            this.lastClientColor = this.color;
            this.level.setBlocksDirty(this.worldPosition, getBlockState(), getBlockState());
        }
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("Color", color);
        output.putBoolean("PlacedByBucket", isPlacedByBucket);
        output.putBoolean("MixedAsSource", mixedAsSource);
        if (sourcePos != null) {
            output.putInt("SourceX", sourcePos.getX());
            output.putInt("SourceY", sourcePos.getY());
            output.putInt("SourceZ", sourcePos.getZ());
        }
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }
}
