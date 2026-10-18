package com.cozary.colored_water.util;

import com.cozary.colored_water.fluids.ColorWaterFluid;

public class CapturedFluidInfo {

    private static final ThreadLocal<ColorWaterFluid> HOLDER = new ThreadLocal<>();

    public static void set(ColorWaterFluid fluid) {
        HOLDER.set(fluid);
    }

    public static boolean hasInfo() {
        return HOLDER.get() != null;
    }

    public static ColorWaterFluid get() {
        return HOLDER.get();
    }

    public static void clear() {
        HOLDER.remove();
    }
}
