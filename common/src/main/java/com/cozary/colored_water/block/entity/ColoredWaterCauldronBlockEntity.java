package com.cozary.colored_water.block.entity;

import com.cozary.colored_water.block.ColoredWaterCauldronBlock;
import com.cozary.colored_water.init.ModBlockEntities;
import com.cozary.colored_water.util.ColoredWaterUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;

public class ColoredWaterCauldronBlockEntity extends BlockEntity {

    private int color = -1;
    private boolean condensed = false;
    private int luminosity = 0;
    private int lastClientColor = -1;
    private int lastClientLuminosity = -1;
    private int lastSyncedColor = -1;
    private int lastSyncedLuminosity = -1;
    private boolean lastSyncedCondensed = false;

    public ColoredWaterCauldronBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.COLORED_WATER_CAULDRON_BE.get(), pos, state);
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

    public void setProperties(int color, boolean condensed, int luminosity) {
        this.condensed = condensed;
        this.luminosity = Mth.clamp(luminosity, ColoredWaterUtil.MIN_LUMINOSITY, ColoredWaterUtil.MAX_LUMINOSITY);
        int alpha = condensed ? ColoredWaterUtil.CONDENSED_ALPHA : ColoredWaterUtil.DEFAULT_ALPHA;
        int rgb = color == -1 ? ColoredWaterUtil.DEFAULT_COLOR : ColoredWaterUtil.getRgb(color);
        int customAlpha = ColoredWaterUtil.getAlpha(color);
        if (customAlpha > 0) {
            this.color = color;
        } else {
            this.color = ColoredWaterUtil.withAlpha(rgb, alpha);
        }
        markUpdated();
    }

    public int getStoredColor() {
        if (this.color != -1) return this.color;
        int defaultAlpha = condensed ? ColoredWaterUtil.CONDENSED_ALPHA : ColoredWaterUtil.DEFAULT_ALPHA;
        return ColoredWaterUtil.withAlpha(ColoredWaterUtil.DEFAULT_COLOR, defaultAlpha);
    }

    public int getColor() {
        if (this.color != -1) {
            int a = ColoredWaterUtil.getAlpha(this.color);
            if (a == 0) {
                int defaultAlpha = condensed ? ColoredWaterUtil.CONDENSED_ALPHA : ColoredWaterUtil.DEFAULT_ALPHA;
                return ColoredWaterUtil.withAlpha(this.color, defaultAlpha);
            }
            return this.color;
        }
        return getStoredColor();
    }

    public void setColor(int color) {
        this.color = color;
        markUpdated();
    }

    public boolean isCondensed() {
        if (this.color != -1) {
            int a = ColoredWaterUtil.getAlpha(this.color);
            if (a > 0) return ColoredWaterUtil.isCondensedAlpha(a);
        }
        return condensed;
    }

    public void setCondensed(boolean condensed) {
        this.condensed = condensed;
        int alpha = condensed ? ColoredWaterUtil.CONDENSED_ALPHA : ColoredWaterUtil.DEFAULT_ALPHA;
        int currentRgb = this.color == -1 ? ColoredWaterUtil.DEFAULT_COLOR : ColoredWaterUtil.getRgb(this.color);
        this.color = ColoredWaterUtil.withAlpha(currentRgb, alpha);
        markUpdated();
    }

    public int getLuminosity() {
        return luminosity;
    }

    public void setLuminosity(int luminosity) {
        int clamped = Mth.clamp(luminosity, ColoredWaterUtil.MIN_LUMINOSITY, ColoredWaterUtil.MAX_LUMINOSITY);
        if (this.luminosity != clamped) {
            this.luminosity = clamped;
            updateBlockStateProps();
            markUpdated();
        }
    }

    public int getAlpha() {
        if (this.color != -1) {
            int a = ColoredWaterUtil.getAlpha(this.color);
            if (a > 0) return a;
        }
        return condensed ? ColoredWaterUtil.CONDENSED_ALPHA : ColoredWaterUtil.DEFAULT_ALPHA;
    }

    public void setAlpha(int alpha) {
        int currentRgb = this.color == -1 ? ColoredWaterUtil.DEFAULT_COLOR : ColoredWaterUtil.getRgb(this.color);
        this.color = ColoredWaterUtil.withAlpha(currentRgb, alpha);
        if (ColoredWaterUtil.isCondensedAlpha(alpha)) this.condensed = true;
        markUpdated();
    }

    public void markUpdated() {
        setChanged();
        if (level != null) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
            updateBlockStateProps();
        }
    }

    private void updateBlockStateProps() {
        if (level != null && !level.isClientSide()) {
            BlockState currentState = getBlockState();
            boolean stateChanged = false;
            BlockState newState = currentState;

            if (currentState.hasProperty(ColoredWaterCauldronBlock.CONDENSED)) {
                if (currentState.getValue(ColoredWaterCauldronBlock.CONDENSED) != this.isCondensed()) {
                    newState = newState.setValue(ColoredWaterCauldronBlock.CONDENSED, this.isCondensed());
                    stateChanged = true;
                }
            }

            if (currentState.hasProperty(ColoredWaterCauldronBlock.LIGHT_LEVEL)) {
                if (currentState.getValue(ColoredWaterCauldronBlock.LIGHT_LEVEL) != this.luminosity) {
                    newState = newState.setValue(ColoredWaterCauldronBlock.LIGHT_LEVEL, this.luminosity);
                    stateChanged = true;
                }
            }

            if (stateChanged) {
                level.setBlock(worldPosition, newState, 3);
            }
        }
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.color = input.getIntOr("Color", -1);
        this.condensed = input.getBooleanOr("Condensed", false);
        this.luminosity = input.getIntOr("Luminosity", 0);

        if (this.level != null && this.level.isClientSide()) {
            if (this.color != this.lastClientColor || this.luminosity != this.lastClientLuminosity) {
                this.lastClientColor = this.color;
                this.lastClientLuminosity = this.luminosity;
                BlockState state = getBlockState();
                this.level.sendBlockUpdated(this.worldPosition, state, state, 11);
                this.level.getChunkSource().getLightEngine().checkBlock(this.worldPosition);
            }
        }
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("Color", color);
        output.putBoolean("Condensed", condensed);
        output.putInt("Luminosity", luminosity);
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
