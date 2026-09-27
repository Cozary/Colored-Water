package com.cozary.colored_water.mixin.color;

import com.cozary.colored_water.fluids.color.PurpleWater;
import com.cozary.colored_water.init.ModFluidTypes;
import net.minecraft.world.level.material.FlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PurpleWater.class)
public abstract class PurpleWaterMixin extends FlowingFluid {

    @Override
    public @NotNull FluidType getFluidType() {
        return ModFluidTypes.PURPLE_WATER_TYPE.get();
    }
}