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

    @Inject(method = "isEyeInFluid", at = @At("RETURN"), cancellable = true)
    private void coloredWater$isEyeInFluid(TagKey<Fluid> tag, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (!callbackInfoReturnable.getReturnValueZ() && tag == FluidTags.WATER) {
            Entity self = (Entity) (Object) this;
            if (self.isEyeInFluidType(ModFluidTypes.COLORED_WATER_TYPE.get())) {
                callbackInfoReturnable.setReturnValue(true);
            }
        }
    }
}
