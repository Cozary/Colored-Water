package com.cozary.colored_water.mixin;

import com.cozary.colored_water.fluids.BaseColorWater;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.LilyPadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LilyPadBlock.class)
public abstract class WaterlilyBlockMixin {

    @Unique
    private static final TagKey<Fluid> COLORED_WATER$SUPPORTS_LILY_PAD =
            TagKey.create(Registries.FLUID, Identifier.fromNamespaceAndPath("minecraft", "supports_lily_pad"));

    @Inject(method = "mayPlaceOn", at = @At("RETURN"), cancellable = true)
    private void coloredWater$mayPlaceOn(BlockState state, BlockGetter level, BlockPos blockPosition, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (!callbackInfoReturnable.getReturnValue()) {
            FluidState fluidState = level.getFluidState(blockPosition);
            FluidState aboveFluidState = level.getFluidState(blockPosition.above());
            if (aboveFluidState.isEmpty() && (fluidState.getType() instanceof BaseColorWater || fluidState.is(COLORED_WATER$SUPPORTS_LILY_PAD))) {
                callbackInfoReturnable.setReturnValue(true);
            }
        }
    }
}
