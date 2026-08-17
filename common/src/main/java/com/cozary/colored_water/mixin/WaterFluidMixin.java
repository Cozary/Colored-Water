package com.cozary.colored_water.mixin;

import com.cozary.colored_water.fluids.ColoredWaterFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.WaterFluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WaterFluid.class)
public abstract class WaterFluidMixin {

    @Inject(method = "isSame", at = @At("HEAD"), cancellable = true)
    private void coloredWater$isSameWater(Fluid fluid, CallbackInfoReturnable<Boolean> cir) {
        if (fluid instanceof ColoredWaterFluid) {
            cir.setReturnValue(true);
        }
    }
}
