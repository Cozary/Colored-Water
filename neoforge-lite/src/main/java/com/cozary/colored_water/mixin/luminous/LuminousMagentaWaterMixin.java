package com.cozary.colored_water.mixin.luminous;

import com.cozary.colored_water.fluids.luminous.LuminousMagentaWater;
import com.cozary.colored_water.init.ModFluidTypes;
import net.minecraft.world.level.material.FlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LuminousMagentaWater.class)
public abstract class LuminousMagentaWaterMixin extends FlowingFluid {

    @Override
    public @NotNull FluidType getFluidType() {
        return ModFluidTypes.LUMINOUS_MAGENTA_WATER_TYPE.get();
    }
}