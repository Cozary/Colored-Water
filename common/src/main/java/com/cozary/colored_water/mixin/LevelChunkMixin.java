package com.cozary.colored_water.mixin;

import com.cozary.colored_water.block.ColoredWaterBlock;
import com.cozary.colored_water.block.entity.ColoredWaterBlockEntity;
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
    public abstract BlockState getBlockState(BlockPos pos);

    @Inject(method = "getBlockEntity(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/chunk/LevelChunk$EntityCreationType;)Lnet/minecraft/world/level/block/entity/BlockEntity;", at = @At("HEAD"), cancellable = true)
    private void coloredWater$getWaterloggedBlockEntity(BlockPos pos, LevelChunk.EntityCreationType creationType,
            CallbackInfoReturnable<BlockEntity> cir) {
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
    }

    @Inject(method = "createBlockEntity", at = @At("RETURN"), cancellable = true)
    private void coloredWater$createWaterloggedBlockEntity(BlockPos pos, CallbackInfoReturnable<BlockEntity> cir) {
        if (cir.getReturnValue() == null) {
            BlockState state = this.getBlockState(pos);
            boolean isWaterlogged = state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED);
            if (isWaterlogged || state.is(Blocks.BUBBLE_COLUMN) || state.is(Blocks.FROSTED_ICE)) {
                cir.setReturnValue(new ColoredWaterBlockEntity(pos.immutable(), state));
            }
        }
    }

    @Inject(method = "setBlockEntity", at = @At("HEAD"), cancellable = true)
    private void coloredWater$allowWaterloggedBlockEntity(BlockEntity blockEntity, CallbackInfo ci) {
        if (blockEntity instanceof ColoredWaterBlockEntity) {
            BlockPos pos = blockEntity.getBlockPos().immutable();
            BlockState state = this.getBlockState(pos);
            boolean isWaterlogged = state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED);
            if (isWaterlogged || state.is(Blocks.BUBBLE_COLUMN) || state.is(Blocks.FROSTED_ICE)) {
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
    private void coloredWater$onSetBlockState(BlockPos pos, BlockState state, int flags,
            CallbackInfoReturnable<BlockState> cir) {
        BlockPos immutablePos = pos.immutable();
        LevelChunk chunk = (LevelChunk) (Object) this;
        BlockEntity be = chunk.getBlockEntities().get(immutablePos);

        if (be instanceof ColoredWaterBlockEntity) {
            boolean isNewWaterlogged = state.hasProperty(BlockStateProperties.WATERLOGGED)
                    && state.getValue(BlockStateProperties.WATERLOGGED);
            boolean isNewColoredWater = state.getBlock() instanceof ColoredWaterBlock;
            boolean isNewFrostedIce = state.is(Blocks.FROSTED_ICE);
            boolean isNewBubbleColumn = state.is(Blocks.BUBBLE_COLUMN);
            if (isNewWaterlogged || isNewColoredWater || isNewFrostedIce || isNewBubbleColumn) {
                // Preserve existing ColoredWaterBlockEntity across state transitions
                return;
            }
            chunk.getBlockEntities().remove(immutablePos);
            be.setRemoved();
        }
    }

    @Inject(method = "removeBlockEntity", at = @At("HEAD"), cancellable = true)
    private void coloredWater$preventRemovalOnPreservedBlocks(BlockPos pos, CallbackInfo ci) {
        BlockState state = this.getBlockState(pos);
        if (state.is(Blocks.FROSTED_ICE)
                || state.is(Blocks.BUBBLE_COLUMN)
                || (state.hasProperty(BlockStateProperties.WATERLOGGED)
                        && state.getValue(BlockStateProperties.WATERLOGGED))
                || state.getBlock() instanceof ColoredWaterBlock) {
            BlockEntity be = ((LevelChunk) (Object) this).getBlockEntities().get(pos.immutable());
            if (be instanceof ColoredWaterBlockEntity) {
                ci.cancel();
            }
        }
    }
}
