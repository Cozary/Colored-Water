package com.cozary.colored_water.mixin;

import com.cozary.colored_water.fluids.BaseColorWater;
import com.cozary.colored_water.init.ModFluidTypes;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BaseColorWater.class)
public abstract class BaseColorWaterMixin extends FlowingFluid {

    @Override
    public @NotNull FluidType getFluidType() {
        return ModFluidTypes.getFluidTypeFor((BaseColorWater) (Object) this);
    }
}
