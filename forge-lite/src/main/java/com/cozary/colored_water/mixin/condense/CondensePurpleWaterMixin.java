package com.cozary.colored_water.mixin.condense;

import com.cozary.colored_water.fluids.condense.CondensePurpleWater;
import com.cozary.colored_water.init.ModFluidTypes;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(CondensePurpleWater.class)
public abstract class CondensePurpleWaterMixin extends FlowingFluid {

    @Override
    public @NotNull FluidType getFluidType() {
        return ModFluidTypes.CONDENSE_PURPLE_WATER_TYPE.get();
    }
}