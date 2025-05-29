package com.cozary.colored_water.mixin.condense;

import com.cozary.colored_water.fluids.condense.CondenseLimeWater;
import com.cozary.colored_water.init.ModFluidTypes;
import net.minecraft.world.level.material.FlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(CondenseLimeWater.class)
public abstract class CondenseLimeWaterMixin extends FlowingFluid {

    @Override
    public @NotNull FluidType getFluidType() {
        return ModFluidTypes.CONDENSE_LIME_WATER_TYPE.get();
    }
}