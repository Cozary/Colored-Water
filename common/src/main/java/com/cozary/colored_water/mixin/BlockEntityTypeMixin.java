package com.cozary.colored_water.mixin;

import com.cozary.colored_water.init.ModBlockEntities;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockEntityType.class)
public abstract class BlockEntityTypeMixin {

    @Inject(method = "isValid", at = @At("HEAD"), cancellable = true)
    private void coloredWater$allowWaterloggedBlocks(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if ((Object) this == ModBlockEntities.COLORED_WATER_BE.get()) {
            if (state.hasProperty(BlockStateProperties.WATERLOGGED) || state.is(Blocks.FROSTED_ICE)) {
                cir.setReturnValue(true);
            }
        }
    }
}
