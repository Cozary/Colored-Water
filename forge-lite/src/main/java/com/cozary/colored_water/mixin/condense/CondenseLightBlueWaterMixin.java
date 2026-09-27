package com.cozary.colored_water.mixin.condense;

import com.cozary.colored_water.fluids.condense.CondenseLightBlueWater;
import com.cozary.colored_water.init.ModFluidTypes;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(CondenseLightBlueWater.class)
public abstract class CondenseLightBlueWaterMixin extends FlowingFluid {

    @Override
    public @NotNull FluidType getFluidType() {
        return ModFluidTypes.CONDENSE_LIGHT_BLUE_WATER_TYPE.get();
    }
}