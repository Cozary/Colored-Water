package com.cozary.colored_water.mixin.color;

import com.cozary.colored_water.fluids.color.GreenWater;
import com.cozary.colored_water.init.ModFluidTypes;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GreenWater.class)
public abstract class GreenWaterMixin extends FlowingFluid {

    @Override
    public @NotNull FluidType getFluidType() {
        return ModFluidTypes.GREEN_WATER_TYPE.get();
    }
}