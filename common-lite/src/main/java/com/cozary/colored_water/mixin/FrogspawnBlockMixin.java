package com.cozary.colored_water.mixin;

import com.cozary.colored_water.fluids.BaseColorWater;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.FrogspawnBlock;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FrogspawnBlock.class)
public abstract class FrogspawnBlockMixin {

    @Unique
    private static final TagKey<Fluid> COLORED_WATER$SUPPORTS_FROGSPAWN =
            TagKey.create(Registries.FLUID, Identifier.fromNamespaceAndPath("minecraft", "supports_frogspawn"));

    @Inject(method = "mayPlaceOn", at = @At("RETURN"), cancellable = true)
    private static void coloredWater$mayPlaceOn(BlockGetter level, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue()) {
            FluidState fluidState = level.getFluidState(pos);
            FluidState aboveFluidState = level.getFluidState(pos.above());
            if (aboveFluidState.isEmpty() && (fluidState.getType() instanceof BaseColorWater || fluidState.is(COLORED_WATER$SUPPORTS_FROGSPAWN))) {
                cir.setReturnValue(true);
            }
        }
    }
}
