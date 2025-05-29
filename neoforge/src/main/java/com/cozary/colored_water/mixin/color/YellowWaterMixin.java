package com.cozary.colored_water.mixin.color;

import com.cozary.colored_water.fluids.color.YellowWater;
import com.cozary.colored_water.init.ModFluidTypes;
import net.minecraft.world.level.material.FlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(YellowWater.class)
public abstract class YellowWaterMixin extends FlowingFluid {

    @Override
    public @NotNull FluidType getFluidType() {
        return ModFluidTypes.YELLOW_WATER_TYPE.get();
    }
}