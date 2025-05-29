package com.cozary.colored_water.mixin.condense;

import com.cozary.colored_water.fluids.condense.CondenseMagentaWater;
import com.cozary.colored_water.init.ModFluidTypes;
import net.minecraft.world.level.material.FlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(CondenseMagentaWater.class)
public abstract class CondenseMagentaWaterMixin extends FlowingFluid {

    @Override
    public @NotNull FluidType getFluidType() {
        return ModFluidTypes.CONDENSE_MAGENTA_WATER_TYPE.get();
    }
}