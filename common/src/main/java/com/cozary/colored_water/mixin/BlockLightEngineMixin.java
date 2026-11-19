package com.cozary.colored_water.mixin;

import com.cozary.colored_water.block.entity.ColoredWaterBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.lighting.BlockLightEngine;
import net.minecraft.world.level.lighting.LightEngine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Emits dynamic block light for luminous waterlogged blocks and bubble columns.
 */
@Mixin(BlockLightEngine.class)
public abstract class BlockLightEngineMixin extends LightEngine {

    protected BlockLightEngineMixin() {
        super(null, null);
    }

    @Inject(method = "getEmission", at = @At("HEAD"), cancellable = true)
    private void coloredWater$getWaterloggedEmission(long blockPos, BlockState state, CallbackInfoReturnable<Integer> callbackInfoReturnable) {
        boolean isWaterlogged = state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED);
        boolean isBubbleColumn = state.is(Blocks.BUBBLE_COLUMN);
        if (isWaterlogged || isBubbleColumn) {
            BlockPos blockPosition = BlockPos.of(blockPos);
            if (this.chunkSource != null) {
                BlockGetter chunk = this.chunkSource.getChunkForLighting(SectionPos.blockToSectionCoord(blockPosition.getX()), SectionPos.blockToSectionCoord(blockPosition.getZ()));
                if (chunk != null) {
                    BlockEntity blockEntity = chunk.getBlockEntity(blockPosition);
                    if (blockEntity instanceof ColoredWaterBlockEntity coloredWaterBlockEntity && coloredWaterBlockEntity.hasCustomProperties()) {
                        callbackInfoReturnable.setReturnValue(coloredWaterBlockEntity.getLuminosity());
                    }
                }
            }
        }
    }
}
