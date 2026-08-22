package com.cozary.colored_water.mixin;

import com.cozary.colored_water.block.entity.ColoredWaterBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerLevel.class)
public abstract class ServerLevelMixin {

    @Inject(method = "sendBlockUpdated", at = @At("HEAD"))
    private void coloredWater$sendBlockEntityUpdate(BlockPos pos, BlockState oldState, BlockState newState, int flags, CallbackInfo ci) {
        ServerLevel level = (ServerLevel) (Object) this;
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof ColoredWaterBlockEntity coloredBe) {
            Packet<ClientGamePacketListener> packet = be.getUpdatePacket();
            if (packet != null) {
                level.getChunkSource().chunkMap.getPlayers(new ChunkPos(pos), false).forEach(player -> {
                    player.connection.send(packet);
                });
            }
        }
    }
}
