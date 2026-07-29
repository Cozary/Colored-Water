package com.cozary.colored_water.block.entity;

import com.cozary.colored_water.block.ColoredWaterCauldronBlock;
import com.cozary.colored_water.init.ModBlockEntities;
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

    private static final int DEFAULT_COLOR = 0x3F76E4;
    private int color = -1;
    private boolean condensed = false;
    private int luminosity = 0;
    private int lastClientColor = -1;

    public ColoredWaterCauldronBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.COLORED_WATER_CAULDRON_BE.get(), pos, state);
    }

    public int getStoredColor() {
        if (this.color != -1) return this.color;
        int defaultAlpha = condensed ? 255 : 180;
        return (defaultAlpha << 24) | (DEFAULT_COLOR & 0x00FFFFFF);
    }

    public int getColor() {
        if (this.color != -1) {
            int a = (this.color >>> 24) & 0xFF;
            if (a == 0) {
                int defaultAlpha = condensed ? 255 : 180;
                return (defaultAlpha << 24) | (this.color & 0x00FFFFFF);
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
            int a = (this.color >>> 24) & 0xFF;
            if (a > 0) return a >= 220;
        }
        return condensed;
    }

    public void setCondensed(boolean condensed) {
        this.condensed = condensed;
        int alpha = condensed ? 255 : 180;
        int currentRgb = this.color == -1 ? (DEFAULT_COLOR & 0x00FFFFFF) : (this.color & 0x00FFFFFF);
        this.color = (alpha << 24) | currentRgb;
        markUpdated();
    }

    public int getLuminosity() {
        return luminosity;
    }

    public void setLuminosity(int luminosity) {
        int clamped = Mth.clamp(luminosity, 0, 15);
        if (this.luminosity != clamped) {
            this.luminosity = clamped;
            updateBlockStateProps();
            markUpdated();
        }
    }

    public int getAlpha() {
        if (this.color != -1) {
            int a = (this.color >>> 24) & 0xFF;
            if (a > 0) return a;
        }
        return condensed ? 255 : 180;
    }

    public void setAlpha(int alpha) {
        int currentRgb = this.color == -1 ? (DEFAULT_COLOR & 0x00FFFFFF) : (this.color & 0x00FFFFFF);
        this.color = ((alpha & 0xFF) << 24) | currentRgb;
        if (alpha >= 220) this.condensed = true;
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
            this.lastClientColor = this.color;
            BlockState state = getBlockState();
            this.level.setBlocksDirty(this.worldPosition, state, state);
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
        CompoundTag tag = super.getUpdateTag(registries);
        tag.putInt("Color", this.color);
        tag.putBoolean("Condensed", this.condensed);
        tag.putInt("Luminosity", this.luminosity);
        return tag;
    }
}
