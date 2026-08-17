package com.cozary.colored_water.util;

public class CapturedFluidInfo {
    private static final ThreadLocal<Info> HOLDER = new ThreadLocal<>();

    public static void set(int color, boolean condensed, int luminosity) {
        HOLDER.set(new Info(color, condensed, luminosity));
    }

    public static boolean hasInfo() {
        return HOLDER.get() != null;
    }

    public static Info get() {
        return HOLDER.get();
    }

    public static void clear() {
        HOLDER.remove();
    }

    public record Info(int color, boolean condensed, int luminosity) {}
}
