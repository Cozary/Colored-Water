package com.cozary.colored_water.mixin;

import com.cozary.colored_water.block.entity.WaterloggedColorBlockEntity;
import com.cozary.colored_water.fluids.ColorWaterFluid;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
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

    @Shadow
    public abstract BlockState getBlockState(BlockPos blockPosition);

    @Inject(method = "getBlockEntity(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/chunk/LevelChunk$EntityCreationType;)Lnet/minecraft/world/level/block/entity/BlockEntity;", at = @At("HEAD"), cancellable = true)
    private void coloredWater$getWaterloggedBlockEntity(BlockPos blockPosition, LevelChunk.EntityCreationType creationType, CallbackInfoReturnable<BlockEntity> callbackInfoReturnable) {
        LevelChunk chunk = (LevelChunk) (Object) this;
        BlockPos immutablePosition = blockPosition.immutable();
        BlockEntity existingBlockEntity = chunk.getBlockEntities().get(immutablePosition);
        if (existingBlockEntity != null) {
            if (!existingBlockEntity.isRemoved()) {
                callbackInfoReturnable.setReturnValue(existingBlockEntity);
                return;
            } else {
                chunk.getBlockEntities().remove(immutablePosition);
            }
        }
    }

    @Inject(method = "createBlockEntity", at = @At("RETURN"), cancellable = true)
    private void coloredWater$createWaterloggedBlockEntity(BlockPos blockPosition, CallbackInfoReturnable<BlockEntity> callbackInfoReturnable) {
        if (callbackInfoReturnable.getReturnValue() == null) {
            BlockState state = this.getBlockState(blockPosition);
            boolean isWaterlogged = state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED);
            if (isWaterlogged || state.is(Blocks.BUBBLE_COLUMN) || state.is(Blocks.FROSTED_ICE)) {
                callbackInfoReturnable.setReturnValue(new WaterloggedColorBlockEntity(blockPosition.immutable(), state));
            }
        }
    }

    @Inject(method = "setBlockEntity", at = @At("HEAD"), cancellable = true)
    private void coloredWater$allowWaterloggedBlockEntity(BlockEntity blockEntity, CallbackInfo callbackInfo) {
        if (blockEntity instanceof WaterloggedColorBlockEntity) {
            BlockPos blockPosition = blockEntity.getBlockPos().immutable();
            BlockState state = this.getBlockState(blockPosition);
            boolean isWaterlogged = state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED);
            if (isWaterlogged || state.is(Blocks.BUBBLE_COLUMN) || state.is(Blocks.FROSTED_ICE)) {
                LevelChunk chunk = (LevelChunk) (Object) this;
                blockEntity.setLevel(chunk.getLevel());
                BlockEntity oldBlockEntity = chunk.getBlockEntities().put(blockPosition, blockEntity);
                if (oldBlockEntity != null && oldBlockEntity != blockEntity) {
                    oldBlockEntity.setRemoved();
                }
                callbackInfo.cancel();
            }
        }
    }

    @Inject(method = "setBlockState", at = @At("HEAD"))
    private void coloredWater$onSetBlockState(BlockPos blockPosition, BlockState state, int flags,
                                              CallbackInfoReturnable<BlockState> callbackInfoReturnable) {
        BlockPos immutablePosition = blockPosition.immutable();
        LevelChunk chunk = (LevelChunk) (Object) this;
        BlockEntity existingBlockEntity = chunk.getBlockEntities().get(immutablePosition);

        if (existingBlockEntity instanceof WaterloggedColorBlockEntity) {
            boolean isNewWaterlogged = state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED);
            boolean isNewBubbleColumn = state.is(Blocks.BUBBLE_COLUMN);
            boolean isNewFrostedIce = state.is(Blocks.FROSTED_ICE);
            if (isNewWaterlogged || isNewBubbleColumn || isNewFrostedIce) {
                existingBlockEntity.setBlockState(state);
                return;
            }
            chunk.getBlockEntities().remove(immutablePosition);
            existingBlockEntity.setRemoved();
        } else {
            boolean isNewWaterlogged = state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED);
            if (isNewWaterlogged || state.is(Blocks.FROSTED_ICE) || state.is(Blocks.BUBBLE_COLUMN)) {
                BlockState oldState = chunk.getBlockState(immutablePosition);
                if (oldState.getFluidState().getType() instanceof ColorWaterFluid colorWaterFluid) {
                    WaterloggedColorBlockEntity newBlockEntity = new WaterloggedColorBlockEntity(immutablePosition, state);
                    newBlockEntity.setLevel(chunk.getLevel());
                    newBlockEntity.setFluid((ColorWaterFluid) colorWaterFluid.getSource());
                    chunk.getBlockEntities().put(immutablePosition, newBlockEntity);
                }
            }
        }
    }

    @Inject(method = "removeBlockEntity", at = @At("HEAD"), cancellable = true)
    private void coloredWater$preventRemovalOnPreservedBlocks(BlockPos blockPosition, CallbackInfo callbackInfo) {
        BlockState state = this.getBlockState(blockPosition);
        if (state.is(Blocks.BUBBLE_COLUMN) || state.is(Blocks.FROSTED_ICE) || (state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED))) {
            BlockEntity existingBlockEntity = ((LevelChunk) (Object) this).getBlockEntities().get(blockPosition.immutable());
            if (existingBlockEntity instanceof WaterloggedColorBlockEntity) {
                callbackInfo.cancel();
            }
        }
    }
}
