package com.cozary.colored_water.mixin.luminousCondense;

import com.cozary.colored_water.fluids.luminousCondense.LuminousCondenseLimeWater;
import com.cozary.colored_water.init.ModFluidTypes;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LuminousCondenseLimeWater.class)
public abstract class LuminousCondenseLimeWaterMixin extends FlowingFluid {

    @Override
    public @NotNull FluidType getFluidType() {
        return ModFluidTypes.LUMINOUS_CONDENSE_LIME_WATER_TYPE.get();
    }
}