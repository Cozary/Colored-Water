package com.cozary.colored_water.util;

import com.cozary.colored_water.block.ColoredWaterBlock;
import com.cozary.colored_water.block.ColoredWaterCauldronBlock;
import com.cozary.colored_water.block.entity.ColoredWaterBlockEntity;
import com.cozary.colored_water.block.entity.ColoredWaterCauldronBlockEntity;
import com.cozary.colored_water.fluids.BaseColorWater;
import com.cozary.colored_water.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import org.jetbrains.annotations.Nullable;

/**
 * Centralized utility class containing shared color mathematics, bucket data-component handling,
 * block entity property transfers, and world fluid color lookups across loaders.
 */
public final class ColoredWaterUtil {

    private ColoredWaterUtil() {}

    // Constants
    public static final int DEFAULT_COLOR = 0x3F76E4;
    public static final int DEFAULT_ALPHA = 180;
    public static final int CONDENSED_ALPHA = 255;
    public static final int CONDENSED_ALPHA_THRESHOLD = 220;
    public static final int DEFAULT_ARGB_NORMAL = (DEFAULT_ALPHA << 24) | DEFAULT_COLOR;
    public static final int DEFAULT_ARGB_CONDENSED = (CONDENSED_ALPHA << 24) | DEFAULT_COLOR;
    public static final int MAX_LUMINOSITY = 15;
    public static final int MIN_LUMINOSITY = 0;
    public static final int UPDATE_DELAY = 3;

    // Color Extraction

    public static int getAlpha(int argb) {
        return (argb >>> 24) & 0xFF;
    }

    public static int getRed(int rgb) {
        return (rgb >> 16) & 0xFF;
    }

    public static int getGreen(int rgb) {
        return (rgb >> 8) & 0xFF;
    }

    public static int getBlue(int rgb) {
        return rgb & 0xFF;
    }

    public static int getRgb(int argb) {
        return argb & 0x00FFFFFF;
    }

    public static int packArgb(int a, int r, int g, int b) {
        return ((a & 0xFF) << 24) | ((r & 0xFF) << 16) | ((g & 0xFF) << 8) | (b & 0xFF);
    }

    public static int withAlpha(int rgb, int alpha) {
        return ((alpha & 0xFF) << 24) | (rgb & 0x00FFFFFF);
    }

    public static boolean isCondensedAlpha(int alpha) {
        return alpha >= CONDENSED_ALPHA_THRESHOLD;
    }

    // Color Comparison & Blending

    public static boolean isColorDiffSignificant(int c1, int c2) {
        int aDiff = Math.abs(getAlpha(c1) - getAlpha(c2));
        int rDiff = Math.abs(getRed(c1) - getRed(c2));
        int gDiff = Math.abs(getGreen(c1) - getGreen(c2));
        int bDiff = Math.abs(getBlue(c1) - getBlue(c2));
        return (aDiff + rDiff + gDiff + bDiff) >= 3;
    }

    public static int lerpColor(int current, int target, float step) {
        int aCur = getAlpha(current);
        int rCur = getRed(current);
        int gCur = getGreen(current);
        int bCur = getBlue(current);

        int aTgt = getAlpha(target);
        int rTgt = getRed(target);
        int gTgt = getGreen(target);
        int bTgt = getBlue(target);

        int aNew = Math.abs(aCur - aTgt) <= 4 ? aTgt : (int) Mth.lerp(step, aCur, aTgt);
        int rNew = Math.abs(rCur - rTgt) <= 4 ? rTgt : (int) Mth.lerp(step, rCur, rTgt);
        int gNew = Math.abs(gCur - gTgt) <= 4 ? gTgt : (int) Mth.lerp(step, gCur, gTgt);
        int bNew = Math.abs(bCur - bTgt) <= 4 ? bTgt : (int) Mth.lerp(step, bCur, bTgt);

        return packArgb(aNew, rNew, gNew, bNew);
    }

    public static int lerpLuminosity(int current, int target, float step) {
        int clampedTarget = Mth.clamp(target, MIN_LUMINOSITY, MAX_LUMINOSITY);
        int clampedCurrent = Mth.clamp(current, MIN_LUMINOSITY, MAX_LUMINOSITY);
        int diff = clampedTarget - clampedCurrent;
        if (diff == 0) {
            return clampedTarget;
        }
        int stepAmount = Math.max(1, Math.round(Math.abs(diff) * step));
        int next = clampedCurrent + (diff > 0 ? stepAmount : -stepAmount);
        return Mth.clamp(next, MIN_LUMINOSITY, MAX_LUMINOSITY);
    }

    public static int blend(int color1, int weight1, int color2, int weight2) {
        int totalWeight = weight1 + weight2;
        if (totalWeight <= 0) return color1;
        int a = (getAlpha(color1) * weight1 + getAlpha(color2) * weight2) / totalWeight;
        int r = (getRed(color1) * weight1 + getRed(color2) * weight2) / totalWeight;
        int g = (getGreen(color1) * weight1 + getGreen(color2) * weight2) / totalWeight;
        int b = (getBlue(color1) * weight1 + getBlue(color2) * weight2) / totalWeight;
        return packArgb(a, r, g, b);
    }

    public static DyeColor getClosestDyeColor(int rgb) {
        int r = getRed(rgb);
        int g = getGreen(rgb);
        int b = getBlue(rgb);

        DyeColor closest = DyeColor.WHITE;
        double minDistance = Double.MAX_VALUE;

        for (DyeColor color : DyeColor.values()) {
            int dyeRgb = color.getTextureDiffuseColor() & 0x00FFFFFF;
            int dr = r - getRed(dyeRgb);
            int dg = g - getGreen(dyeRgb);
            int db = b - getBlue(dyeRgb);
            double dist = (double) dr * dr + (double) dg * dg + (double) db * db;
            if (dist < minDistance) {
                minDistance = dist;
                closest = color;
            }
        }
        return closest;
    }

    // Bucket ItemStack Serialization & Helpers

    public static ItemStack createBucketStack(int argbColor, boolean condensed, int luminosity) {
        ItemStack stack = new ItemStack(ModItems.COLORED_WATER_BUCKET.get());
        int rgb = getRgb(argbColor);
        int alpha = getAlpha(argbColor);
        if (alpha == 0) {
            alpha = condensed ? CONDENSED_ALPHA : DEFAULT_ALPHA;
        }

        if (argbColor != -1) {
            stack.set(DataComponents.DYED_COLOR, new DyedItemColor(rgb));
        }

        CompoundTag tag = new CompoundTag();
        tag.putBoolean("Condensed", condensed);
        tag.putInt("Luminosity", Mth.clamp(luminosity, MIN_LUMINOSITY, MAX_LUMINOSITY));
        if (alpha > 0) {
            tag.putInt("Alpha", alpha);
        }
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
        return stack;
    }

    public static ItemStack createBucketStack(ColoredWaterBlockEntity be) {
        return createBucketStack(be.getColor(), be.isCondensed(), be.getLuminosity());
    }

    public static ItemStack createBucketStack(ColoredWaterCauldronBlockEntity be) {
        return createBucketStack(be.getColor(), be.isCondensed(), be.getLuminosity());
    }

    public static int getBucketRgb(ItemStack stack) {
        DyedItemColor dyedColor = stack.get(DataComponents.DYED_COLOR);
        return dyedColor != null ? (dyedColor.rgb() & 0x00FFFFFF) : DEFAULT_COLOR;
    }

    public static boolean getBucketCondensed(ItemStack stack) {
        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        if (customData != null) {
            return customData.copyTag().getBooleanOr("Condensed", false);
        }
        return false;
    }

    public static int getBucketLuminosity(ItemStack stack) {
        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        if (customData != null) {
            return customData.copyTag().getIntOr("Luminosity", 0);
        }
        return 0;
    }

    public static int getBucketAlpha(ItemStack stack) {
        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        if (customData != null) {
            CompoundTag tag = customData.copyTag();
            if (tag.contains("Alpha")) {
                return tag.getIntOr("Alpha", 0);
            }
        }
        return getBucketCondensed(stack) ? CONDENSED_ALPHA : DEFAULT_ALPHA;
    }

    public static int getBucketFullColor(ItemStack stack) {
        int rgb = getBucketRgb(stack);
        int alpha = getBucketAlpha(stack);
        if (alpha == 0) {
            alpha = getBucketCondensed(stack) ? CONDENSED_ALPHA : DEFAULT_ALPHA;
        }
        return withAlpha(rgb, alpha);
    }

    public static void applyBucketProperties(ItemStack stack, ColoredWaterBlockEntity coloredBe) {
        int fullColor = getBucketFullColor(stack);
        boolean condensed = getBucketCondensed(stack);
        int luminosity = getBucketLuminosity(stack);

        coloredBe.markAsPlacedByBucket();
        coloredBe.setCondensed(condensed);
        coloredBe.setLuminosity(luminosity);
        coloredBe.setColor(fullColor, null, true);
        coloredBe.setBaseProperties(fullColor, condensed, luminosity);
        coloredBe.updateBlockStateProps();
        coloredBe.propagateColor();
        coloredBe.markUpdated();
    }

    public static void applyBucketProperties(ItemStack stack, ColoredWaterCauldronBlockEntity coloredBe) {
        int fullColor = getBucketFullColor(stack);
        boolean condensed = getBucketCondensed(stack);
        int luminosity = getBucketLuminosity(stack);
        coloredBe.setProperties(fullColor, condensed, luminosity);
    }

    public static void transferProperties(ColoredWaterBlockEntity source, ColoredWaterBlockEntity target) {
        if (source == null || target == null) return;
        int color = source.getColor();
        int lum = source.getLuminosity();
        boolean cond = source.isCondensed();

        target.setLuminosity(lum);
        target.setCondensed(cond);
        target.setColor(color, source.getBlockPos(), true);
        target.setBaseProperties(color, cond, lum);
        target.updateBlockStateProps();
        target.propagateColor();
        target.markUpdated();
    }

    // World Color Lookups

    public static int getColoredWaterAt(@Nullable Level level, @Nullable BlockPos pos) {
        if (level == null || pos == null) return -1;
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof ColoredWaterBlockEntity coloredBe) {
            if (coloredBe.hasCustomProperties()) {
                int c = coloredBe.getColor();
                return c != -1 ? c : DEFAULT_COLOR;
            }
            return -1;
        }
        if (be instanceof ColoredWaterCauldronBlockEntity cauldronBe) {
            return cauldronBe.getColor();
        }
        FluidState fluid = level.getFluidState(pos);
        if (fluid.getType() instanceof BaseColorWater) {
            return DEFAULT_COLOR;
        }
        BlockState state = level.getBlockState(pos);
        if (state.getBlock() instanceof ColoredWaterBlock || state.getBlock() instanceof ColoredWaterCauldronBlock) {
            return DEFAULT_COLOR;
        }
        return -1;
    }
}
