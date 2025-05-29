package com.cozary.colored_water.mixin.condense;

import com.cozary.colored_water.fluids.condense.CondenseOrangeWater;
import com.cozary.colored_water.init.ModFluidTypes;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(CondenseOrangeWater.class)
public abstract class CondenseOrangeWaterMixin extends FlowingFluid {

    @Override
    public @NotNull FluidType getFluidType() {
        return ModFluidTypes.CONDENSE_ORANGE_WATER_TYPE.get();
    }
}