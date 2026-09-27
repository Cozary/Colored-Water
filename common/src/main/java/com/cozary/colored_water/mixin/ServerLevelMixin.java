package com.cozary.colored_water.mixin;

import com.cozary.colored_water.block.entity.ColoredWaterBlockEntity;
import com.cozary.colored_water.block.entity.ColoredWaterCauldronBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Synchronizes BlockEntity packet updates with tracking players when colored water or cauldrons change.
 */
@Mixin(ServerLevel.class)
public abstract class ServerLevelMixin {

    @Inject(method = "sendBlockUpdated", at = @At("HEAD"))
    private void coloredWater$sendBlockEntityUpdate(BlockPos pos, BlockState oldState, BlockState newState, int flags, CallbackInfo ci) {
        if ((flags & Block.UPDATE_CLIENTS) == 0) {
            return;
        }

        ServerLevel level = (ServerLevel) (Object) this;
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof ColoredWaterBlockEntity coloredWaterBlockEntity && coloredWaterBlockEntity.hasCustomProperties()) {
            boolean stateChanged = oldState != newState;
            if (coloredWaterBlockEntity.hasChangedSinceLastSync() || stateChanged) {
                Packet<ClientGamePacketListener> packet = blockEntity.getUpdatePacket();
                if (packet != null) {
                    level.getChunkSource().chunkMap.getPlayers(new ChunkPos(pos), false).forEach(player -> {
                        player.connection.send(packet);
                    });
                }
                coloredWaterBlockEntity.markSynced();
            }
        } else if (blockEntity instanceof ColoredWaterCauldronBlockEntity cauldronBlockEntity) {
            boolean stateChanged = oldState != newState;
            if (cauldronBlockEntity.hasChangedSinceLastSync() || stateChanged) {
                Packet<ClientGamePacketListener> packet = blockEntity.getUpdatePacket();
                if (packet != null) {
                    level.getChunkSource().chunkMap.getPlayers(new ChunkPos(pos), false).forEach(player -> {
                        player.connection.send(packet);
                    });
                }
                cauldronBlockEntity.markSynced();
            }
        }
    }
}

