package com.cozary.colored_water.mixin.luminous;

import com.cozary.colored_water.fluids.luminous.LuminousGrayWater;
import com.cozary.colored_water.init.ModFluidTypes;
import net.minecraft.world.level.material.FlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LuminousGrayWater.class)
public abstract class LuminousGrayWaterMixin extends FlowingFluid {

    @Override
    public @NotNull FluidType getFluidType() {
        return ModFluidTypes.LUMINOUS_GRAY_WATER_TYPE.get();
    }
}