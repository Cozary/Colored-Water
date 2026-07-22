package com.cozary.colored_water.init;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.fluids.BaseColorWater;
import com.cozary.colored_water.fluids.ColoredWaterFluid;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.material.Fluid;

import java.util.function.Supplier;

public class ModFluids {

    public static final RegistrationProvider<Fluid> FLUIDS = RegistrationProvider.get(Registries.FLUID, ColoredWater.MOD_ID);

    public static final Supplier<BaseColorWater> STILL_COLORED_WATER = FLUIDS.register("colored_water", ColoredWaterFluid.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_COLORED_WATER = FLUIDS.register("flowing_colored_water", ColoredWaterFluid.Flowing::new);

    public static void loadClass() {
    }
}
