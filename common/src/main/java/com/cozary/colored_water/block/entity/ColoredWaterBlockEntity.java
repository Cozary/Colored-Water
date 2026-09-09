package com.cozary.colored_water.block.entity;

import com.cozary.colored_water.block.ColoredWaterBlock;
import com.cozary.colored_water.init.ModBlockEntities;
import com.cozary.colored_water.util.ColoredWaterUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
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

    private int color = -1;
    private int luminosity = -1;
    private boolean condensed = false;
    private int baseColor = -1;
    private int baseLuminosity = -1;
    private boolean baseCondensed = false;
    private int lastClientColor = -1;
    private int lastClientLuminosity = -1;
    private int lastSyncedColor = -1;
    private int lastSyncedLuminosity = -1;
    private boolean lastSyncedCondensed = false;
    private boolean isPlacedByBucket = false;
    private BlockPos sourcePos = null;
    private boolean mixedAsSource = false;
    private boolean isCalculating = false;

    public ColoredWaterBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.COLORED_WATER_BE.get(), pos, blockState);
    }

    public void setBaseProperties(int color, boolean condensed, int luminosity) {
        this.baseColor = color;
        this.baseCondensed = condensed;
        this.baseLuminosity = luminosity;
    }

    public int getBaseColor() {
        return this.baseColor;
    }

    public boolean hasCustomProperties() {
        if (getBlockState().getBlock() instanceof ColoredWaterBlock) {
            return true;
        }
        return this.color != -1 || this.luminosity > 0 || this.condensed || this.isPlacedByBucket || this.mixedAsSource || this.baseColor != -1;
    }

    public boolean hasChangedSinceLastSync() {
        return this.color != this.lastSyncedColor
                || this.luminosity != this.lastSyncedLuminosity
                || this.condensed != this.lastSyncedCondensed;
    }

    public void markSynced() {
        this.lastSyncedColor = this.color;
        this.lastSyncedLuminosity = this.luminosity;
        this.lastSyncedCondensed = this.condensed;
    }

    public BlockPos getSourcePos() {
        return this.sourcePos;
    }

    public int getLuminosity() {
        return this.luminosity != -1 ? this.luminosity : ColoredWaterUtil.MIN_LUMINOSITY;
    }

    public void setLuminosity(int luminosity) {
        int clamped = Mth.clamp(luminosity, ColoredWaterUtil.MIN_LUMINOSITY, ColoredWaterUtil.MAX_LUMINOSITY);
        if (this.luminosity != clamped) {
            this.luminosity = clamped;
            updateBlockStateProps();
            markUpdated();
            propagateToNeighbors();
        }
    }

    public boolean isCondensed() {
        if (this.color != -1) {
            int a = ColoredWaterUtil.getAlpha(this.color);
            if (a > 0)
                return ColoredWaterUtil.isCondensedAlpha(a);
        }
        return condensed;
    }

    public void setCondensed(boolean condensed) {
        this.condensed = condensed;
        int alpha = condensed ? ColoredWaterUtil.CONDENSED_ALPHA : ColoredWaterUtil.DEFAULT_ALPHA;
        int currentRgb = this.color == -1 ? ColoredWaterUtil.DEFAULT_COLOR : ColoredWaterUtil.getRgb(this.color);
        this.color = ColoredWaterUtil.withAlpha(currentRgb, alpha);
        updateBlockStateProps();
        markUpdated();
    }

    public void updateBlockStateProps() {
        if (level != null && !level.isClientSide()) {
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
                level.setBlock(worldPosition, currentState, Block.UPDATE_CLIENTS | Block.UPDATE_KNOWN_SHAPE);
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
        int defaultAlpha = condensed ? ColoredWaterUtil.CONDENSED_ALPHA : ColoredWaterUtil.DEFAULT_ALPHA;
        return ColoredWaterUtil.withAlpha(ColoredWaterUtil.DEFAULT_COLOR, defaultAlpha);
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
                TargetProperties target = calculateTargetProperties(level, worldPosition, state);
                int result = target.color() == -1 ? getStoredColor() : target.color();

                if (isSource && !isPlacedByBucket) {
                    if (this.color != result) {
                        this.color = result;
                    }
                    if (this.luminosity != target.luminosity()) {
                        this.luminosity = target.luminosity();
                    }
                    this.mixedAsSource = true;
                } else if (!isSource && result != -1) {
                    this.color = result;
                    if (this.luminosity == -1) {
                        this.luminosity = target.luminosity();
                    }
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
        this.baseColor = this.color;
        this.baseLuminosity = this.luminosity;
        this.baseCondensed = this.condensed;
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
        level.scheduleTick(worldPosition, getBlockState().getFluidState().getType(), ColoredWaterUtil.UPDATE_DELAY);
    }

    private boolean isValidSource(BlockPos pos) {
        if (level == null || pos == null)
            return false;
        if (!level.isLoaded(pos))
            return true;
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

        if (force && isSource) {
            this.baseColor = color;
        }

        boolean colorChanged = false;

        if (this.color == -1 || force || ColoredWaterUtil.isColorDiffSignificant(this.color, color)) {
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
     * Pushes the current color state to valid neighboring water blocks by
     * scheduling fluid ticks.
     */
    private void propagateToNeighbors() {
        if (level == null || level.isClientSide())
            return;
        notifyNeighborsToTick();
    }


    private boolean isPropagating = false;

    /**
     * Schedules ticks for all water neighbors to ensure the fluid engine processes
     * changes.
     */
    private void notifyNeighborsToTick() {
        if (level == null || level.isClientSide())
            return;
        for (Direction dir : Direction.values()) {
            BlockPos neighborPos = worldPosition.relative(dir);
            if (!level.isLoaded(neighborPos)) {
                continue;
            }
            FluidState neighborFluid = level.getFluidState(neighborPos);
            if (!neighborFluid.isEmpty() && neighborFluid.is(FluidTags.WATER)) {
                BlockEntity be = level.getBlockEntity(neighborPos);
                if (be instanceof ColoredWaterBlockEntity neighborBe) {
                    boolean colorDiff = ColoredWaterUtil.isColorDiffSignificant(this.color, neighborBe.getColor());
                    boolean lumDiff = this.getLuminosity() != neighborBe.getLuminosity();
                    boolean condDiff = this.isCondensed() != neighborBe.isCondensed();
                    if (colorDiff || lumDiff || condDiff) {
                        level.scheduleTick(neighborPos, neighborFluid.getType(), ColoredWaterUtil.UPDATE_DELAY);
                    }
                } else {
                    level.scheduleTick(neighborPos, neighborFluid.getType(), ColoredWaterUtil.UPDATE_DELAY);
                }
            }
        }
    }

    private record TargetProperties(int color, int luminosity) {}

    /**
     * Public entry point to trigger color & luminosity propagation/mixing from the block class.
     * Smoothly interpolates towards the calculated target color and luminosity until fully
     * converged.
     */
    public void propagateColor() {
        if (level == null || level.isClientSide() || isPropagating) {
            return;
        }
        isPropagating = true;
        try {
            boolean isSource = isSourceBlock(getBlockState());
            TargetProperties targetProps = calculateTargetProperties(level, worldPosition, getBlockState());
            int targetColor = targetProps.color();
            int targetLuminosity = targetProps.luminosity();

            boolean colorChanged = false;
            boolean lumChanged = false;
            boolean needsColorUpdate = false;
            boolean needsLuminosityUpdate = false;

            if (targetColor == -1) {
                if (this.color != -1) {
                    this.color = -1;
                    this.luminosity = -1;
                    colorChanged = true;
                    lumChanged = true;
                }
            } else if (this.color == -1) {
                this.color = targetColor;
                this.luminosity = targetLuminosity;
                colorChanged = true;
                lumChanged = true;
            } else if (ColoredWaterUtil.isColorDiffSignificant(this.color, targetColor)) {
                int nextColor = ColoredWaterUtil.lerpColor(this.color, targetColor, 0.35F);
                if (this.color != nextColor) {
                    this.color = nextColor;
                    colorChanged = true;
                }

                // Schedule next tick if still interpolating towards target color
                if (ColoredWaterUtil.isColorDiffSignificant(nextColor, targetColor)) {
                    needsColorUpdate = true;
                }
            } else if (this.color != targetColor) {
                this.color = targetColor;
                colorChanged = true;
            }

            int currentLum = getLuminosity();
            if (targetLuminosity != -1 && currentLum != targetLuminosity && (!isSource || !isPlacedByBucket)) {
                int nextLum = ColoredWaterUtil.lerpLuminosity(currentLum, targetLuminosity, 0.20F);
                int clamped = Mth.clamp(nextLum, ColoredWaterUtil.MIN_LUMINOSITY, ColoredWaterUtil.MAX_LUMINOSITY);
                if (this.luminosity != clamped) {
                    this.luminosity = clamped;
                    lumChanged = true;
                }
                if (clamped != targetLuminosity) {
                    needsLuminosityUpdate = true;
                }
            }

            if (colorChanged || lumChanged) {
                updateBlockStateProps();
                markUpdated();
                propagateToNeighbors();
            }

            // Schedule next tick if still interpolating towards target color or luminosity
            if (needsColorUpdate || needsLuminosityUpdate) {
                level.scheduleTick(worldPosition, getBlockState().getFluidState().getType(), ColoredWaterUtil.UPDATE_DELAY);
            }
        } finally {
            isPropagating = false;
        }
    }

    /**
     * Calculates the weighted average color and luminosity based on surrounding blocks.
     */
    private TargetProperties calculateTargetProperties(Level level, BlockPos pos, BlockState state) {
        boolean isSource = isSourceBlock(state);
        int myBaseColor = this.baseColor != -1 ? this.baseColor : (this.isPlacedByBucket ? this.color : (state.getBlock() instanceof ColoredWaterBlock ? getStoredColor() : -1));
        int myBaseLum = this.baseLuminosity != -1 ? this.baseLuminosity : (this.isPlacedByBucket ? getLuminosity() : 0);

        // 1. Check UP (Source or Flowing water falling into/above this block)
        BlockPos upPos = pos.above();
        BlockState upState = level.getBlockState(upPos);
        if (isCompatibleFluid(upState)) {
            int upColor = getBlockColor(level, upPos, upState);
            int upLum = getBlockLuminosity(level, upPos, upState);
            if (upColor != -1) {
                if (isSource) {
                    if (myBaseColor != -1) {
                        int mixedColor = ColoredWaterUtil.blend(myBaseColor, 1, upColor, 1);
                        int mixedLum = Math.round((myBaseLum + upLum) / 2.0f);
                        return new TargetProperties(mixedColor, mixedLum);
                    } else {
                        return new TargetProperties(upColor, upLum);
                    }
                }
                return new TargetProperties(upColor, upLum);
            }
        }

        // 2. If it's a source block, check adjacent horizontal sources that are receiving water from above
        if (isSource) {
            long adjR = 0, adjG = 0, adjB = 0, adjA = 0, adjLum = 0;
            int impactNeighbors = 0;

            for (Direction dir : Direction.Plane.HORIZONTAL) {
                BlockPos neighborPos = pos.relative(dir);
                BlockState neighborState = level.getBlockState(neighborPos);

                if (isCompatibleFluid(neighborState) && isSourceBlock(neighborState)) {
                    BlockPos nUpPos = neighborPos.above();
                    BlockState nUpState = level.getBlockState(nUpPos);
                    if (isCompatibleFluid(nUpState)) {
                        int nUpColor = getBlockColor(level, nUpPos, nUpState);
                        if (nUpColor != -1) {
                            int nColor = getBlockColor(level, neighborPos, neighborState);
                            int nLum = getBlockLuminosity(level, neighborPos, neighborState);
                            if (nColor != -1) {
                                impactNeighbors++;
                                int a = ColoredWaterUtil.getAlpha(nColor);
                                if (a == 0) a = isCondensed() ? 255 : 180;
                                adjA += a;
                                adjR += ColoredWaterUtil.getRed(nColor);
                                adjG += ColoredWaterUtil.getGreen(nColor);
                                adjB += ColoredWaterUtil.getBlue(nColor);
                                adjLum += nLum;
                            }
                        }
                    }
                }
            }

            if (impactNeighbors > 0) {
                int avgA = (int) (adjA / impactNeighbors);
                int avgR = (int) (adjR / impactNeighbors);
                int avgG = (int) (adjG / impactNeighbors);
                int avgB = (int) (adjB / impactNeighbors);
                int impactAvgColor = ColoredWaterUtil.packArgb(avgA, avgR, avgG, avgB);
                int impactAvgLum = Math.round((float) adjLum / impactNeighbors);

                if (myBaseColor != -1) {
                    int blended = ColoredWaterUtil.blend(myBaseColor, 1, impactAvgColor, 1);
                    int blendedLum = Math.round((myBaseLum + impactAvgLum) / 2.0f);
                    return new TargetProperties(blended, blendedLum);
                } else {
                    return new TargetProperties(impactAvgColor, impactAvgLum);
                }
            }

            if (this.baseColor != -1) {
                int targetLum = this.baseLuminosity != -1 ? this.baseLuminosity : (this.isPlacedByBucket ? getLuminosity() : 0);
                return new TargetProperties(this.baseColor, targetLum);
            } else if (this.isPlacedByBucket && this.color != -1) {
                return new TargetProperties(this.color, getLuminosity());
            } else if (state.getBlock() instanceof ColoredWaterBlock) {
                int defColor = this.color != -1 ? this.color : getStoredColor();
                int defLum = this.luminosity != -1 ? this.luminosity : getLuminosity();
                return new TargetProperties(defColor, defLum);
            } else {
                return new TargetProperties(-1, -1);
            }
        }

        long rSum = 0, gSum = 0, bSum = 0, aSum = 0, lumSum = 0, totalWeight = 0;

        // 2. Check DOWN (Flowing fluid mixing with water below)
        BlockPos downPos = pos.below();
        BlockState downState = level.getBlockState(downPos);
        if (isCompatibleFluid(downState) && isSourceBlock(downState)) {
            int downColor = getBlockColor(level, downPos, downState);
            int downLum = getBlockLuminosity(level, downPos, downState);
            if (downColor != -1) {
                int a = (downColor >>> 24) & 0xFF;
                if (a == 0)
                    a = isCondensed() ? 255 : 180;
                totalWeight += 200;
                aSum += a * 200L;
                rSum += ((downColor >> 16) & 0xFF) * 200L;
                gSum += ((downColor >> 8) & 0xFF) * 200L;
                bSum += (downColor & 0xFF) * 200L;
                lumSum += (long) downLum * 200L;
            }
        }

        // 3. Check SIDES (Horizontal mixing for flowing fluid)
        int myLevel = state.hasProperty(LiquidBlock.LEVEL) ? state.getValue(LiquidBlock.LEVEL) : 0;
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            BlockPos neighborPos = pos.relative(dir);
            BlockState neighborState = level.getBlockState(neighborPos);

            if (isCompatibleFluid(neighborState)) {
                int neighborLevel = getEffectiveLevel(level, neighborPos, neighborState);
                int weight = calculateSideWeight(isSource, myLevel, neighborLevel);

                if (weight > 0) {
                    int neighborColor = getBlockColor(level, neighborPos, neighborState);
                    int neighborLum = getBlockLuminosity(level, neighborPos, neighborState);
                    if (neighborColor != -1) {
                        int a = (neighborColor >>> 24) & 0xFF;
                        if (a == 0)
                            a = isCondensed() ? 255 : 180;
                        totalWeight += weight;
                        aSum += a * (long) weight;
                        rSum += ((neighborColor >> 16) & 0xFF) * (long) weight;
                        gSum += ((neighborColor >> 8) & 0xFF) * (long) weight;
                        bSum += (neighborColor & 0xFF) * (long) weight;
                        lumSum += (long) neighborLum * (long) weight;
                    }
                }
            }
        }

        if (totalWeight > 0) {
            int aRes = (int) (aSum / totalWeight);
            int rRes = (int) (rSum / totalWeight);
            int gRes = (int) (gSum / totalWeight);
            int bRes = (int) (bSum / totalWeight);
            int targetColor = ((aRes & 0xFF) << 24) | ((rRes & 0xFF) << 16) | ((gRes & 0xFF) << 8) | (bRes & 0xFF);
            int targetLum = Math.round((float) lumSum / totalWeight);
            targetLum = Mth.clamp(targetLum, ColoredWaterUtil.MIN_LUMINOSITY, ColoredWaterUtil.MAX_LUMINOSITY);
            return new TargetProperties(targetColor, targetLum);
        }

        int defColor = this.color != -1 ? this.color : ColoredWaterUtil.withAlpha(ColoredWaterUtil.DEFAULT_COLOR, isCondensed() ? 255 : 180);
        int defLum = this.luminosity != -1 ? this.luminosity : (isSource ? getLuminosity() : 0);
        return new TargetProperties(defColor, defLum);
    }

    private int calculateTargetColor(Level level, BlockPos pos, BlockState state) {
        return calculateTargetProperties(level, pos, state).color();
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

    @Nullable
    public static ColoredWaterBlockEntity getOrCreate(Level level, BlockPos pos, BlockState state) {
        if (state.isAir()) {
            return null;
        }
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof ColoredWaterBlockEntity coloredBe) {
            return coloredBe;
        }
        if (!(state.getBlock() instanceof ColoredWaterBlock)
                && !(state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED))
                && !state.is(Blocks.BUBBLE_COLUMN)) {
            return null;
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
            if (coloredWaterBE.hasCustomProperties()) {
                return coloredWaterBE.getStoredColor();
            }
            return -1;
        }
        if (state.getBlock() instanceof ColoredWaterBlock) {
            return ColoredWaterUtil.DEFAULT_COLOR;
        }
        return -1;
    }

    private int getBlockLuminosity(Level level, BlockPos pos, BlockState state) {
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof ColoredWaterBlockEntity coloredWaterBE) {
            if (coloredWaterBE.hasCustomProperties()) {
                return coloredWaterBE.getLuminosity();
            }
            return 0;
        }
        return 0;
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.color = input.getIntOr("Color", -1);
        this.luminosity = input.getIntOr("Luminosity", -1);
        this.condensed = input.getBooleanOr("Condensed", false);
        this.baseColor = input.getIntOr("BaseColor", this.color);
        this.baseLuminosity = input.getIntOr("BaseLuminosity", this.luminosity);
        this.baseCondensed = input.getBooleanOr("BaseCondensed", this.condensed);
        this.isPlacedByBucket = input.getBooleanOr("PlacedByBucket", false);
        this.mixedAsSource = input.getBooleanOr("MixedAsSource", false);

        int sx = input.getIntOr("SourceX", Integer.MIN_VALUE);
        int sy = input.getIntOr("SourceY", Integer.MIN_VALUE);
        int sz = input.getIntOr("SourceZ", Integer.MIN_VALUE);
        this.sourcePos = (sx != Integer.MIN_VALUE) ? new BlockPos(sx, sy, sz) : null;

        if (this.level != null && this.level.isClientSide()) {
            if (this.color != this.lastClientColor || this.luminosity != this.lastClientLuminosity) {
                this.lastClientColor = this.color;
                this.lastClientLuminosity = this.luminosity;
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
        output.putInt("BaseColor", baseColor);
        output.putInt("BaseLuminosity", baseLuminosity);
        output.putBoolean("BaseCondensed", baseCondensed);
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
