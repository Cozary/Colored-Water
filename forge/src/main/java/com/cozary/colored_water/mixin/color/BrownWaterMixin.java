package com.cozary.colored_water.mixin.color;

import com.cozary.colored_water.fluids.color.BrownWater;
import com.cozary.colored_water.init.ModFluidTypes;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BrownWater.class)
public abstract class BrownWaterMixin extends FlowingFluid {

    @Override
    public @NotNull FluidType getFluidType() {
        return ModFluidTypes.BROWN_WATER_TYPE.get();
    }
}