package com.cozary.colored_water.mixin;

import com.cozary.colored_water.fluids.BaseColorWater;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.world.level.levelgen.blockpredicates.MatchingFluidsPredicate")
public abstract class MatchingFluidsPredicateMixin {

    @Shadow @Final private HolderSet<Fluid> fluids;

    @Inject(method = "test(Lnet/minecraft/world/level/block/state/BlockState;)Z", at = @At("HEAD"), cancellable = true)
    private void coloredWater$matchColoredWaterFluid(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        FluidState fluidState = state.getFluidState();
        if (fluidState.getType() instanceof BaseColorWater && fluidState.isSource()) {
            if (this.fluids.contains(Fluids.WATER.builtInRegistryHolder())) {
                cir.setReturnValue(true);
            }
        }
    }
}
