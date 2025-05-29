package com.cozary.colored_water.mixin.luminous;

import com.cozary.colored_water.fluids.luminous.LuminousBrownWater;
import com.cozary.colored_water.init.ModFluidTypes;
import net.minecraft.world.level.material.FlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LuminousBrownWater.class)
public abstract class LuminousBrownWaterMixin extends FlowingFluid {

    @Override
    public @NotNull FluidType getFluidType() {
        return ModFluidTypes.LUMINOUS_BROWN_WATER_TYPE.get();
    }
}