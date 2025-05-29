package com.cozary.colored_water.mixin.condense;

import com.cozary.colored_water.fluids.condense.CondenseYellowWater;
import com.cozary.colored_water.init.ModFluidTypes;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(CondenseYellowWater.class)
public abstract class CondenseYellowWaterMixin extends FlowingFluid {

    @Override
    public @NotNull FluidType getFluidType() {
        return ModFluidTypes.CONDENSE_YELLOW_WATER_TYPE.get();
    }
}