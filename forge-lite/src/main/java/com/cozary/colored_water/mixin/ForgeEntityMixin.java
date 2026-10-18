package com.cozary.colored_water.mixin;

import com.cozary.colored_water.init.ModFluidTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.material.Fluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class ForgeEntityMixin {

    @Inject(method = "updateFluidHeightAndDoFluidPushing", at = @At("RETURN"), cancellable = true)
    private void coloredWater$updateFluidHeight(TagKey<Fluid> tag, double pushFactor, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValueZ() && tag == FluidTags.WATER) {
            Entity self = (Entity) (Object) this;
            if (self.isInFluidType((type, height) -> ModFluidTypes.isModFluidType(type))) {
                cir.setReturnValue(true);
            }
        }
    }

    @Inject(method = "isEyeInFluid", at = @At("RETURN"), cancellable = true)
    private void coloredWater$isEyeInFluid(TagKey<Fluid> tag, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValueZ() && tag == FluidTags.WATER) {
            Entity self = (Entity) (Object) this;
            if (ModFluidTypes.isModFluidType(self.getEyeInFluidType())) {
                cir.setReturnValue(true);
            }
        }
    }
}
