package com.cozary.colored_water.mixin.color;

import com.cozary.colored_water.fluids.color.LimeWater;
import com.cozary.colored_water.init.ModFluidTypes;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LimeWater.class)
public abstract class LimeWaterMixin extends FlowingFluid {

    @Override
    public @NotNull FluidType getFluidType() {
        return ModFluidTypes.LIME_WATER_TYPE.get();
    }
}