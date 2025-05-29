package com.cozary.colored_water.mixin.condense;

import com.cozary.colored_water.fluids.condense.CondensePinkWater;
import com.cozary.colored_water.init.ModFluidTypes;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(CondensePinkWater.class)
public abstract class CondensePinkWaterMixin extends FlowingFluid {

    @Override
    public @NotNull FluidType getFluidType() {
        return ModFluidTypes.CONDENSE_PINK_WATER_TYPE.get();
    }
}