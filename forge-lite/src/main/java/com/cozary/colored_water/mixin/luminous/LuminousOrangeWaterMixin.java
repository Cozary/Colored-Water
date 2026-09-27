package com.cozary.colored_water.mixin.luminous;

import com.cozary.colored_water.fluids.luminous.LuminousOrangeWater;
import com.cozary.colored_water.init.ModFluidTypes;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LuminousOrangeWater.class)
public abstract class LuminousOrangeWaterMixin extends FlowingFluid {

    @Override
    public @NotNull FluidType getFluidType() {
        return ModFluidTypes.LUMINOUS_ORANGE_WATER_TYPE.get();
    }
}