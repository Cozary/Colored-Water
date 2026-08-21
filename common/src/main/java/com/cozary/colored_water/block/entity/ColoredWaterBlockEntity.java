package com.cozary.colored_water.block.entity;

import com.cozary.colored_water.block.ColoredWaterBlock;
import com.cozary.colored_water.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;

/**
 * BlockEntity responsible for managing the color state and mixing logic of
 * Colored Water blocks.
 * <p>
 * This entity handles:
 * <ul>
 * <li>Storing the current RGB color of the water block.</li>
 * <li>Calculating color mixing based on neighboring blocks (up, down,
 * sides).</li>
 * <li>Propagating color changes to adjacent water blocks to simulate fluid
 * mixing.</li>
 * <li>Tracking the source of the water flow to prevent infinite loops or
 * incorrect upstream propagation.</li>
 * <li>Persisting state (color, source position, flags).</li>
 * </ul>
 */
public class ColoredWaterBlockEntity extends BlockEntity {

    private static final int UPDATE_DELAY = 3; // Delay in ticks before processing a scheduled update to allow neighbors
                                               // to settle.
    private static final int DEFAULT_COLOR = 0x3F76E4;
    private int color = -1;
    private int luminosity = -1;
    private boolean condensed = false;
    private int lastClientColor = -1;
    private boolean isPlacedByBucket = false;
    private BlockPos sourcePos = null;
    private boolean mixedAsSource = false;
    private boolean isCalculating = false;

    public ColoredWaterBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.COLORED_WATER_BE.get(), pos, blockState);
    }

    private boolean isFindingSource = false;

    public BlockPos getSourcePos() {
        if (level != null && !isSourceBlock(getBlockState())) {
            if (this.sourcePos == null || !isValidSource(this.sourcePos)) {
                if (this.isFindingSource) {
                    return null;
                }
                this.isFindingSource = true;
                try {
                    this.sourcePos = findUpstreamSourcePos();
                } finally {
                    this.isFindingSource = false;
                }
            }
        }
        return this.sourcePos;
    }

    private BlockPos findUpstreamSourcePos() {
        if (level == null)
            return null;

        // 1. Check UP first (water falling from above)
        BlockPos upPos = worldPosition.above();
        BlockEntity upBe = level.getBlockEntity(upPos);
        if (upBe instanceof ColoredWaterBlockEntity coloredUpBe) {
            BlockPos upSource = coloredUpBe.getSourcePos();
            return upSource != null ? upSource : upPos;
        }

        // 2. Check 4 horizontal directions for lowest LEVEL (highest fluid height)
        int myLevel = getBlockState().hasProperty(LiquidBlock.LEVEL) ? getBlockState().getValue(LiquidBlock.LEVEL) : 7;
        BlockPos bestParent = null;
        int minLevel = myLevel;

        for (Direction dir : Direction.Plane.HORIZONTAL) {
            BlockPos nPos = worldPosition.relative(dir);
            BlockState nState = level.getBlockState(nPos);
            BlockEntity nBe = level.getBlockEntity(nPos);
            if (nBe instanceof ColoredWaterBlockEntity) {
                int nLvl = nState.hasProperty(LiquidBlock.LEVEL) ? nState.getValue(LiquidBlock.LEVEL) : 0;
                if (nLvl < minLevel) {
                    minLevel = nLvl;
                    bestParent = nPos;
                }
            }
        }

        if (bestParent != null) {
            BlockEntity parentBe = level.getBlockEntity(bestParent);
            if (parentBe instanceof ColoredWaterBlockEntity coloredParentBe) {
                BlockPos root = coloredParentBe.getSourcePos();
                return root != null ? root : bestParent;
            }
            return bestParent;
        }

        return null;
    }

    public int getLuminosity() {
        if (level != null && !isSourceBlock(getBlockState())) {
            BlockPos src = getSourcePos();
            if (src != null) {
                BlockEntity sBe = level.getBlockEntity(src);
                if (sBe instanceof ColoredWaterBlockEntity coloredSBe) {
                    return coloredSBe.getLuminosity();
                }
            }
        }
        return this.luminosity != -1 ? this.luminosity : 0;
    }

    public void setLuminosity(int luminosity) {
        int clamped = Mth.clamp(luminosity, 0, 15);
        if (this.luminosity != clamped) {
            this.luminosity = clamped;
            updateBlockStateProps();
            markUpdated();
        }
    }

    public boolean isCondensed() {
        if (this.color != -1) {
            int a = (this.color >>> 24) & 0xFF;
            if (a > 0)
                return a >= 220;
        }
        return condensed;
    }

    public void setCondensed(boolean condensed) {
        this.condensed = condensed;
        int alpha = condensed ? 255 : 180;
        int currentRgb = this.color == -1 ? (DEFAULT_COLOR & 0x00FFFFFF) : (this.color & 0x00FFFFFF);
        this.color = (alpha << 24) | currentRgb;
        updateBlockStateProps();
        markUpdated();
    }

    public void updateBlockStateProps() {
        if (level != null) {
            if (!level.isClientSide()) {
                BlockState currentState = getBlockState();
                boolean stateChanged = false;
                if (currentState.hasProperty(ColoredWaterBlock.LIGHT_LEVEL)) {
                    int targetLight = getLuminosity();
                    int stateLight = currentState.getValue(ColoredWaterBlock.LIGHT_LEVEL);
                    if (stateLight != targetLight) {
                        currentState = currentState.setValue(ColoredWaterBlock.LIGHT_LEVEL, targetLight);
                        stateChanged = true;
                    }
                }
                if (currentState.hasProperty(ColoredWaterBlock.CONDENSED)) {
                    boolean targetCond = isCondensed();
                    boolean stateCond = currentState.getValue(ColoredWaterBlock.CONDENSED);
                    if (stateCond != targetCond) {
                        currentState = currentState.setValue(ColoredWaterBlock.CONDENSED, targetCond);
                        stateChanged = true;
                    }
                }
                if (stateChanged) {
                    level.setBlock(worldPosition, currentState, 3);
                }
            }
            level.getChunkSource().getLightEngine().checkBlock(worldPosition);
        }
    }

    /**
     * Retrieves the current color of the water.
     * <p>
     * If the color is uninitialized or needs recalculation (e.g., it's a new
     * source),
     * this method triggers a calculation based on the environment.
     *
     * @return The integer RGB color.
     */
    public int getStoredColor() {
        if (this.color != -1)
            return this.color;
        if (level != null && level.isClientSide()) {
            int fallback = findNeighborColorOnClient();
            if (fallback != -1) {
                return fallback;
            }
        }
        int defaultAlpha = condensed ? 255 : 180;
        return (defaultAlpha << 24) | (DEFAULT_COLOR & 0x00FFFFFF);
    }

    private int findNeighborColorOnClient() {
        if (level == null) return -1;
        BlockEntity upBe = level.getBlockEntity(worldPosition.above());
        if (upBe instanceof ColoredWaterBlockEntity coloredUp && coloredUp.color != -1) {
            return coloredUp.color;
        }
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            BlockEntity nBe = level.getBlockEntity(worldPosition.relative(dir));
            if (nBe instanceof ColoredWaterBlockEntity coloredN && coloredN.color != -1) {
                return coloredN.color;
            }
        }
        return -1;
    }

    public int getColor() {
        if (level == null || level.isClientSide())
            return getStoredColor();

        BlockState state = getBlockState();
        boolean isSource = isSourceBlock(state);

        // Recalculate if not set, or if it's a natural source that hasn't mixed yet.
        boolean needsCalculation = (color == -1) || (isSource && !isPlacedByBucket && !mixedAsSource);

        if (needsCalculation) {
            if (isCalculating)
                return getStoredColor(); // Prevent recursive calculation dropouts
            isCalculating = true;
            try {
                int target = calculateTargetColor(level, worldPosition, state);
                int result = target == -1 ? getStoredColor() : target;

                if (isSource && !isPlacedByBucket) {
                    if (this.color != result) {
                        this.color = result;
                    }
                    this.mixedAsSource = true;
                } else if (!isSource && result != -1) {
                    this.color = result;
                }
                return result;
            } finally {
                isCalculating = false;
            }
        }
        return color;
    }

    /**
     * Sets the color of this block, assuming it's a self-sourced change (like a
     * bucket placement).
     *
     * @param color The new RGB color.
     */
    public void setColor(int color) {
        setColor(color, this.sourcePos, true);
    }

    public boolean isInitialized() {
        return color != -1;
    }

    public void markAsPlacedByBucket() {
        this.isPlacedByBucket = true;
        this.sourcePos = null;
        markUpdated();
    }

    public void markUpdated() {
        setChanged();
        if (level != null) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    /**
     * Handles an update signal from a specific neighbor position.
     * <p>
     * This is typically called when a neighbor changes and might need to update
     * this block's
     * flow source hierarchy.
     *
     * @param incomingSource The position of the neighbor triggering the update.
     */
    public void handleUpdateFrom(BlockPos incomingSource) {
        if (level == null)
            return;
        boolean adopted = false;

        // If we aren't a bucket-placed source, we might adopt this new neighbor as our
        // source
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

        if (adopted)
            markUpdated();
        level.scheduleTick(worldPosition, getBlockState().getFluidState().getType(), UPDATE_DELAY);
    }

    /**
     * Core logic for updating the block's color.
     *
     * @param color          The proposed new color.
     * @param incomingSource The source of this color change.
     * @param force          If true, overrides bucket locks.
     */
    public void setColor(int color, @Nullable BlockPos incomingSource, boolean force) {
        if (level == null)
            return;

        boolean isSource = isSourceBlock(getBlockState());

        // Bucket-placed source blocks cannot be overridden by neighbor flow unless
        // forced
        if (isSource && isPlacedByBucket && !force) {
            return;
        }

        boolean colorChanged = false;

        if (this.color == -1 || force || isColorDiffSignificant(this.color, color)) {
            if (this.color != color) {
                this.color = color;
                colorChanged = true;
            }
        }

        if (colorChanged) {
            updateBlockStateProps();
            markUpdated();
            propagateToNeighbors();
        }
    }

    /**
     * Checks if the difference between two ARGB colors is significant enough to
     * warrant an update.
     */
    private boolean isColorDiffSignificant(int c1, int c2) {
        int aDiff = Math.abs(((c1 >>> 24) & 0xFF) - ((c2 >>> 24) & 0xFF));
        int rDiff = Math.abs(((c1 >> 16) & 0xFF) - ((c2 >> 16) & 0xFF));
        int gDiff = Math.abs(((c1 >> 8) & 0xFF) - ((c2 >> 8) & 0xFF));
        int bDiff = Math.abs((c1 & 0xFF) - (c2 & 0xFF));
        return (aDiff + rDiff + gDiff + bDiff) >= 3;
    }

    /**
     * Pushes the current color state to valid neighboring water blocks by
     * scheduling fluid ticks.
     */
    private void propagateToNeighbors() {
        if (level == null || level.isClientSide())
            return;
        notifyNeighborsToTick();
    }

    /**
     * Verifies if a position contains a valid source block for this entity.
     */
    private boolean isValidSource(BlockPos pos) {
        if (level == null || pos == null)
            return false;
        if (!level.isLoaded(pos))
            return true; // Assume valid if unloaded to prevent breaking on chunk borders
        BlockState state = level.getBlockState(pos);
        if (state.is(Blocks.BUBBLE_COLUMN)) {
            return true;
        }
        if (state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED)) {
            return true;
        }
        return state.getBlock() == getBlockState().getBlock() && state.hasProperty(LiquidBlock.LEVEL) && state.getValue(LiquidBlock.LEVEL) == 0;
    }

    /**
     * Schedules ticks for all water neighbors to ensure the fluid engine processes
     * changes.
     */
    private void notifyNeighborsToTick() {
        if (level == null)
            return;
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
     * Smoothly interpolates towards the calculated target color until fully
     * converged.
     */
    public void propagateColor() {
        if (level != null && !level.isClientSide()) {
            int targetColor = calculateTargetColor(level, worldPosition, getBlockState());

            if (this.color == -1) {
                // First time initialization
                if (targetColor == -1 && isSourceBlock(getBlockState())) {
                    targetColor = getStoredColor();
                }
                if (targetColor != -1) {
                    this.setColor(targetColor, null, true);
                }
                return;
            }

            if (targetColor != -1 && isColorDiffSignificant(this.color, targetColor)) {
                int nextColor = getNextColor(targetColor);
                this.setColor(nextColor, null, false);

                // Schedule next tick if still interpolating towards target color
                if (isColorDiffSignificant(nextColor, targetColor)) {
                    level.scheduleTick(worldPosition, getBlockState().getFluidState().getType(), UPDATE_DELAY);
                }
            }
        }
    }

    private int getNextColor(int targetColor) {
        int aCur = (this.color >>> 24) & 0xFF;
        int rCur = (this.color >> 16) & 0xFF;
        int gCur = (this.color >> 8) & 0xFF;
        int bCur = this.color & 0xFF;

        int aTgt = (targetColor >>> 24) & 0xFF;
        int rTgt = (targetColor >> 16) & 0xFF;
        int gTgt = (targetColor >> 8) & 0xFF;
        int bTgt = targetColor & 0xFF;

        float step = 0.35F;

        int aNew = Math.abs(aCur - aTgt) <= 2 ? aTgt : (int) Mth.lerp(step, aCur, aTgt);
        int rNew = Math.abs(rCur - rTgt) <= 2 ? rTgt : (int) Mth.lerp(step, rCur, rTgt);
        int gNew = Math.abs(gCur - gTgt) <= 2 ? gTgt : (int) Mth.lerp(step, gCur, gTgt);
        int bNew = Math.abs(bCur - bTgt) <= 2 ? bTgt : (int) Mth.lerp(step, bCur, bTgt);

        int nextColor = (aNew << 24) | (rNew << 16) | (gNew << 8) | bNew;
        return nextColor;
    }

    /**
     * Calculates the weighted average color based on surrounding blocks.
     */
    private int calculateTargetColor(Level level, BlockPos pos, BlockState state) {
        boolean isSource = isSourceBlock(state);
        if (isSource && isPlacedByBucket && this.color != -1)
            return this.color;

        long rSum = 0, gSum = 0, bSum = 0, aSum = 0, totalWeight = 0;
        boolean hasUp = false;

        // 1. Check UP (Water falling into this block)
        if (!isSource) {
            BlockPos upPos = pos.above();
            BlockState upState = level.getBlockState(upPos);
            if (isCompatibleFluid(upState)) {
                int upColor = getBlockColor(level, upPos, upState);
                if (upColor != -1) {
                    hasUp = true;
                    int a = (upColor >>> 24) & 0xFF;
                    if (a == 0)
                        a = isCondensed() ? 255 : 180;
                    totalWeight += 1000;
                    aSum += a * 1000L;
                    rSum += ((upColor >> 16) & 0xFF) * 1000L;
                    gSum += ((upColor >> 8) & 0xFF) * 1000L;
                    bSum += (upColor & 0xFF) * 1000L;
                }
            }
        }

        // 2. Check DOWN (Mixing with water below)
        if (!isSource && !hasUp) {
            BlockPos downPos = pos.below();
            BlockState downState = level.getBlockState(downPos);
            if (isCompatibleFluid(downState) && isSourceBlock(downState)) {
                int downColor = getBlockColor(level, downPos, downState);
                if (downColor != -1) {
                    int a = (downColor >>> 24) & 0xFF;
                    if (a == 0)
                        a = isCondensed() ? 255 : 180;
                    totalWeight += 200;
                    aSum += a * 200L;
                    rSum += ((downColor >> 16) & 0xFF) * 200L;
                    gSum += ((downColor >> 8) & 0xFF) * 200L;
                    bSum += (downColor & 0xFF) * 200L;
                }
            }
        }

        // 3. Check SIDES (Horizontal mixing)
        if (!hasUp) {
            int myLevel = state.hasProperty(LiquidBlock.LEVEL) ? state.getValue(LiquidBlock.LEVEL) : 0;
            for (Direction dir : Direction.Plane.HORIZONTAL) {
                BlockPos neighborPos = pos.relative(dir);
                BlockState neighborState = level.getBlockState(neighborPos);

                if (isCompatibleFluid(neighborState)) {
                    int neighborLevel = getEffectiveLevel(level, neighborPos, neighborState);
                    int weight = calculateSideWeight(isSource, myLevel, neighborLevel);

                    if (weight > 0) {
                        int neighborColor = getBlockColor(level, neighborPos, neighborState);
                        if (neighborColor != -1) {
                            int a = (neighborColor >>> 24) & 0xFF;
                            if (a == 0)
                                a = isCondensed() ? 255 : 180;
                            totalWeight += weight;
                            aSum += a * (long) weight;
                            rSum += ((neighborColor >> 16) & 0xFF) * (long) weight;
                            gSum += ((neighborColor >> 8) & 0xFF) * (long) weight;
                            bSum += (neighborColor & 0xFF) * (long) weight;
                        }
                    }
                }
            }
        }

        if (totalWeight > 0) {
            int aRes = (int) (aSum / totalWeight);
            int rRes = (int) (rSum / totalWeight);
            int gRes = (int) (gSum / totalWeight);
            int bRes = (int) (bSum / totalWeight);
            return ((aRes & 0xFF) << 24) | ((rRes & 0xFF) << 16) | ((gRes & 0xFF) << 8) | (bRes & 0xFF);
        }

        // Retain existing color while draining or isolated, avoiding instant reset to
        // uncolored water
        if (this.color != -1) {
            return this.color;
        }

        int defaultAlpha = isCondensed() ? 255 : 180;
        return (defaultAlpha << 24) | (DEFAULT_COLOR & 0x00FFFFFF);
    }

    private boolean isCompatibleFluid(BlockState state) {
        return state.getBlock() == getBlockState().getBlock() || state.getFluidState().is(FluidTags.WATER);
    }

    private boolean isSourceBlock(BlockState state) {
        if (state.hasProperty(LiquidBlock.LEVEL) && state.getValue(LiquidBlock.LEVEL) == 0) {
            return true;
        }
        if (state.is(Blocks.BUBBLE_COLUMN)) {
            return true;
        }
        return state.hasProperty(BlockStateProperties.WATERLOGGED)
                && state.getValue(BlockStateProperties.WATERLOGGED);
    }

    public static ColoredWaterBlockEntity getOrCreate(Level level, BlockPos pos, BlockState state) {
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof ColoredWaterBlockEntity coloredBe) {
            return coloredBe;
        }
        ColoredWaterBlockEntity newBe = new ColoredWaterBlockEntity(pos, state);
        level.setBlockEntity(newBe);
        return newBe;
    }

    /**
     * Normalizes fluid levels for calculation.
     * Level 8 is a falling block, which acts like a full block (0) if it's an
     * impact point.
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
            // Sources only mix with other source blocks
            return neighborLevel == 0 ? 200 : 0;
        }
        if (neighborLevel == 0)
            return 300; // Strong pull from source blocks
        if (neighborLevel < myLevel)
            return (8 - neighborLevel) * 40; // Upstream (lower level value = higher fluid)
        if (neighborLevel == myLevel)
            return (8 - neighborLevel) * 10; // Parallel flow
        return 0; // Downstream neighbor does not push color backwards
    }

    private int getBlockColor(Level level, BlockPos pos, BlockState state) {
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof ColoredWaterBlockEntity coloredWaterBE) {
            return coloredWaterBE.getStoredColor();
        }
        return state.getFluidState().is(FluidTags.WATER) ? DEFAULT_COLOR : -1;
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.color = input.getIntOr("Color", -1);
        this.luminosity = input.getIntOr("Luminosity", -1);
        this.condensed = input.getBooleanOr("Condensed", false);
        this.isPlacedByBucket = input.getBooleanOr("PlacedByBucket", false);
        this.mixedAsSource = input.getBooleanOr("MixedAsSource", false);

        int sx = input.getIntOr("SourceX", Integer.MIN_VALUE);
        int sy = input.getIntOr("SourceY", Integer.MIN_VALUE);
        int sz = input.getIntOr("SourceZ", Integer.MIN_VALUE);
        this.sourcePos = (sx != Integer.MIN_VALUE) ? new BlockPos(sx, sy, sz) : null;

        if (this.level != null && this.level.isClientSide()) {
            if (this.color != this.lastClientColor) {
                this.lastClientColor = this.color;
                this.level.setBlocksDirty(this.worldPosition, getBlockState(), getBlockState());
                this.level.getChunkSource().getLightEngine().checkBlock(this.worldPosition);
            }
        }
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("Color", color);
        output.putInt("Luminosity", luminosity);
        output.putBoolean("Condensed", condensed);
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
