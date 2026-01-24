package com.cozary.colored_water.mixin;

import com.cozary.colored_water.fluids.ColoredWaterFluid;
import com.cozary.colored_water.init.ModFluidTypes;
import net.minecraft.world.level.material.FlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ColoredWaterFluid.class)
public abstract class ColoredWaterMixin extends FlowingFluid {

    @Override
    public @NotNull FluidType getFluidType() {
        return ModFluidTypes.COLORED_WATER_TYPE.get();
    }
}