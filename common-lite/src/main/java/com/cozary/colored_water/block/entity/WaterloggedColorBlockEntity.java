package com.cozary.colored_water.block.entity;

import com.cozary.colored_water.fluids.ColorWaterFluid;
import com.cozary.colored_water.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class WaterloggedColorBlockEntity extends BlockEntity {

    @Nullable
    private ColorWaterFluid fluid;
    @Nullable
    private ColorWaterFluid lastClientFluid;

    public WaterloggedColorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.WATERLOGGED_BE.get(), pos, state);
    }

    @Nullable
    public static WaterloggedColorBlockEntity getOrCreate(Level level, BlockPos pos, BlockState state) {
        if (state.isAir()) {
            return null;
        }
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof WaterloggedColorBlockEntity waterloggedBe) {
            return waterloggedBe;
        }
        boolean isWaterlogged = state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED);
        if (!isWaterlogged && !state.is(Blocks.BUBBLE_COLUMN) && !state.is(Blocks.FROSTED_ICE)) {
            return null;
        }
        WaterloggedColorBlockEntity newBlockEntity = new WaterloggedColorBlockEntity(pos, state);
        level.setBlockEntity(newBlockEntity);
        return newBlockEntity;
    }

    @Nullable
    public ColorWaterFluid getFluid() {
        return this.fluid;
    }

    public void setFluid(@Nullable ColorWaterFluid fluid) {
        this.fluid = fluid;
        markUpdated();
    }

    public boolean hasFluid() {
        return this.fluid != null;
    }

    public void markUpdated() {
        setChanged();
        if (level != null) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
            level.getChunkSource().getLightEngine().checkBlock(worldPosition);
        }
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        String fluidId = input.getStringOr("Fluid", "");
        if (!fluidId.isEmpty()) {
            Identifier id = Identifier.tryParse(fluidId);
            if (id != null) {
                Optional<Holder.Reference<Fluid>> ref = BuiltInRegistries.FLUID.get(id);
                if (ref.isPresent() && ref.get().value() instanceof ColorWaterFluid cwf) {
                    this.fluid = cwf;
                } else {
                    this.fluid = null;
                }
            }
        } else {
            this.fluid = null;
        }

        if (this.level != null && this.level.isClientSide()) {
            if (this.fluid != this.lastClientFluid) {
                this.lastClientFluid = this.fluid;
                BlockState state = getBlockState();
                this.level.sendBlockUpdated(this.worldPosition, state, state, 11);
                this.level.getChunkSource().getLightEngine().checkBlock(this.worldPosition);
            }
        }
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        if (this.fluid != null) {
            Identifier id = BuiltInRegistries.FLUID.getKey(this.fluid);
            output.putString("Fluid", id.toString());
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
