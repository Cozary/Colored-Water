package com.cozary.colored_water.mixin;

import com.cozary.colored_water.block.ColoredWaterBlock;
import com.cozary.colored_water.block.entity.ColoredWaterBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.chunk.LevelChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LevelChunk.class)
public abstract class LevelChunkMixin {

    @Shadow public abstract BlockState getBlockState(BlockPos pos);

    @Inject(
        method = "getBlockEntity(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/chunk/LevelChunk$EntityCreationType;)Lnet/minecraft/world/level/block/entity/BlockEntity;",
        at = @At("HEAD"),
        cancellable = true
    )
    private void coloredWater$getWaterloggedBlockEntity(BlockPos pos, LevelChunk.EntityCreationType creationType, CallbackInfoReturnable<BlockEntity> cir) {
        LevelChunk chunk = (LevelChunk) (Object) this;
        BlockPos immutablePos = pos.immutable();
        BlockEntity existingBe = chunk.getBlockEntities().get(immutablePos);
        if (existingBe != null) {
            if (!existingBe.isRemoved()) {
                cir.setReturnValue(existingBe);
                return;
            } else {
                chunk.getBlockEntities().remove(immutablePos);
            }
        }

        BlockState state = this.getBlockState(immutablePos);
        if (state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED)) {
            if (creationType == LevelChunk.EntityCreationType.IMMEDIATE) {
                ColoredWaterBlockEntity newBe = new ColoredWaterBlockEntity(immutablePos, state);
                newBe.setLevel(chunk.getLevel());
                chunk.getBlockEntities().put(immutablePos, newBe);
                cir.setReturnValue(newBe);
            }
        }
    }

    @Inject(method = "setBlockEntity", at = @At("HEAD"), cancellable = true)
    private void coloredWater$allowWaterloggedBlockEntity(BlockEntity blockEntity, CallbackInfo ci) {
        if (blockEntity instanceof ColoredWaterBlockEntity) {
            BlockPos pos = blockEntity.getBlockPos().immutable();
            BlockState state = this.getBlockState(pos);
            if (state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED)) {
                LevelChunk chunk = (LevelChunk) (Object) this;
                blockEntity.setLevel(chunk.getLevel());
                BlockEntity oldBe = chunk.getBlockEntities().put(pos, blockEntity);
                if (oldBe != null && oldBe != blockEntity) {
                    oldBe.setRemoved();
                }
                ci.cancel();
            }
        }
    }

    @Inject(method = "setBlockState", at = @At("HEAD"))
    private void coloredWater$onSetBlockState(BlockPos pos, BlockState state, int flags, CallbackInfoReturnable<BlockState> cir) {
        BlockPos immutablePos = pos.immutable();
        LevelChunk chunk = (LevelChunk) (Object) this;
        BlockEntity be = chunk.getBlockEntities().get(immutablePos);

        if (be instanceof ColoredWaterBlockEntity) {
            boolean isNewWaterlogged = state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED);
            boolean isNewColoredWater = state.getBlock() instanceof ColoredWaterBlock;
            if (isNewWaterlogged || isNewColoredWater) {
                // Preserve existing ColoredWaterBlockEntity across state transitions!
                return;
            }
            chunk.getBlockEntities().remove(immutablePos);
            be.setRemoved();
        }
    }
}
