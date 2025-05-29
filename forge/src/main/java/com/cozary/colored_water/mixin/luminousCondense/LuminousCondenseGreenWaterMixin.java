package com.cozary.colored_water.mixin.luminousCondense;

import com.cozary.colored_water.fluids.luminousCondense.LuminousCondenseGreenWater;
import com.cozary.colored_water.init.ModFluidTypes;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LuminousCondenseGreenWater.class)
public abstract class LuminousCondenseGreenWaterMixin extends FlowingFluid {

    @Override
    public @NotNull FluidType getFluidType() {
        return ModFluidTypes.LUMINOUS_CONDENSE_GREEN_WATER_TYPE.get();
    }
}