package com.cozary.colored_water.mixin.luminousCondense;

import com.cozary.colored_water.fluids.luminousCondense.LuminousCondenseLightBlueWater;
import com.cozary.colored_water.init.ModFluidTypes;
import net.minecraft.world.level.material.FlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LuminousCondenseLightBlueWater.class)
public abstract class LuminousCondenseLightBlueWaterMixin extends FlowingFluid {

    @Override
    public @NotNull FluidType getFluidType() {
        return ModFluidTypes.LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_TYPE.get();
    }
}