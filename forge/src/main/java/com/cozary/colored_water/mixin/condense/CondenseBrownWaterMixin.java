package com.cozary.colored_water.mixin.condense;

import com.cozary.colored_water.fluids.condense.CondenseBrownWater;
import com.cozary.colored_water.init.ModFluidTypes;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(CondenseBrownWater.class)
public abstract class CondenseBrownWaterMixin extends FlowingFluid {

    @Override
    public @NotNull FluidType getFluidType() {
        return ModFluidTypes.CONDENSE_BROWN_WATER_TYPE.get();
    }
}