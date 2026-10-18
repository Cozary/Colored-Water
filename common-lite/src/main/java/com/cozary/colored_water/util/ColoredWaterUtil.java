package com.cozary.colored_water.util;

import com.cozary.colored_water.block.entity.WaterloggedColorBlockEntity;
import com.cozary.colored_water.fluids.BaseColorWater;
import com.cozary.colored_water.fluids.ColorWaterFluid;
import com.cozary.colored_water.init.ModCauldrons;
import com.google.common.collect.Sets;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import org.jetbrains.annotations.Nullable;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public final class ColoredWaterUtil {

    public static final int DEFAULT_COLOR = 0x3F76E4;
    public static final int DEFAULT_ALPHA = 180;
    public static final int CONDENSED_ALPHA = 255;

    public static int getRed(int rgb) {
        return (rgb >> 16) & 0xFF;
    }

    public static int getGreen(int rgb) {
        return (rgb >> 8) & 0xFF;
    }

    public static int getBlue(int rgb) {
        return rgb & 0xFF;
    }

    public static int withAlpha(int rgb, int alpha) {
        return ((alpha & 0xFF) << 24) | (rgb & 0x00FFFFFF);
    }

    private static final Map<Block, Integer> CAULDRON_COLORS = new IdentityHashMap<>();
    private static final Set<Block> CONDENSED_CAULDRONS = Sets.newIdentityHashSet();

    private ColoredWaterUtil() {
    }

    @SafeVarargs
    private static void mapCauldrons(int color, Supplier<Block>... cauldrons) {
        for (Supplier<Block> supplier : cauldrons) {
            CAULDRON_COLORS.put(supplier.get(), color);
        }
    }

    private static synchronized void initCauldrons() {
        if (!CAULDRON_COLORS.isEmpty()) return;
        mapCauldrons(0x1D1D21, ModCauldrons.BLACK_WATER_CAULDRON, ModCauldrons.CONDENSE_BLACK_WATER_CAULDRON, ModCauldrons.LUMINOUS_BLACK_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_BLACK_WATER_CAULDRON);
        mapCauldrons(0x3C44AA, ModCauldrons.BLUE_WATER_CAULDRON, ModCauldrons.CONDENSE_BLUE_WATER_CAULDRON, ModCauldrons.LUMINOUS_BLUE_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_BLUE_WATER_CAULDRON);
        mapCauldrons(0x835432, ModCauldrons.BROWN_WATER_CAULDRON, ModCauldrons.CONDENSE_BROWN_WATER_CAULDRON, ModCauldrons.LUMINOUS_BROWN_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_BROWN_WATER_CAULDRON);
        mapCauldrons(0x169C9C, ModCauldrons.CYAN_WATER_CAULDRON, ModCauldrons.CONDENSE_CYAN_WATER_CAULDRON, ModCauldrons.LUMINOUS_CYAN_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_CYAN_WATER_CAULDRON);
        mapCauldrons(0x474F52, ModCauldrons.GRAY_WATER_CAULDRON, ModCauldrons.CONDENSE_GRAY_WATER_CAULDRON, ModCauldrons.LUMINOUS_GRAY_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_GRAY_WATER_CAULDRON);
        mapCauldrons(0x5E7C16, ModCauldrons.GREEN_WATER_CAULDRON, ModCauldrons.CONDENSE_GREEN_WATER_CAULDRON, ModCauldrons.LUMINOUS_GREEN_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_GREEN_WATER_CAULDRON);
        mapCauldrons(0x3AB3DA, ModCauldrons.LIGHT_BLUE_WATER_CAULDRON, ModCauldrons.CONDENSE_LIGHT_BLUE_WATER_CAULDRON, ModCauldrons.LUMINOUS_LIGHT_BLUE_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_CAULDRON);
        mapCauldrons(0x9D9D97, ModCauldrons.LIGHT_GRAY_WATER_CAULDRON, ModCauldrons.CONDENSE_LIGHT_GRAY_WATER_CAULDRON, ModCauldrons.LUMINOUS_LIGHT_GRAY_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_CAULDRON);
        mapCauldrons(0x80C71F, ModCauldrons.LIME_WATER_CAULDRON, ModCauldrons.CONDENSE_LIME_WATER_CAULDRON, ModCauldrons.LUMINOUS_LIME_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_LIME_WATER_CAULDRON);
        mapCauldrons(0xC74EBD, ModCauldrons.MAGENTA_WATER_CAULDRON, ModCauldrons.CONDENSE_MAGENTA_WATER_CAULDRON, ModCauldrons.LUMINOUS_MAGENTA_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_MAGENTA_WATER_CAULDRON);
        mapCauldrons(0xF9801D, ModCauldrons.ORANGE_WATER_CAULDRON, ModCauldrons.CONDENSE_ORANGE_WATER_CAULDRON, ModCauldrons.LUMINOUS_ORANGE_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_ORANGE_WATER_CAULDRON);
        mapCauldrons(0xF38BAA, ModCauldrons.PINK_WATER_CAULDRON, ModCauldrons.CONDENSE_PINK_WATER_CAULDRON, ModCauldrons.LUMINOUS_PINK_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_PINK_WATER_CAULDRON);
        mapCauldrons(0x8932B8, ModCauldrons.PURPLE_WATER_CAULDRON, ModCauldrons.CONDENSE_PURPLE_WATER_CAULDRON, ModCauldrons.LUMINOUS_PURPLE_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_PURPLE_WATER_CAULDRON);
        mapCauldrons(0xB02E26, ModCauldrons.RED_WATER_CAULDRON, ModCauldrons.CONDENSE_RED_WATER_CAULDRON, ModCauldrons.LUMINOUS_RED_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_RED_WATER_CAULDRON);
        mapCauldrons(0xF9FFFE, ModCauldrons.WHITE_WATER_CAULDRON, ModCauldrons.CONDENSE_WHITE_WATER_CAULDRON, ModCauldrons.LUMINOUS_WHITE_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_WHITE_WATER_CAULDRON);
        mapCauldrons(0xFED83D, ModCauldrons.YELLOW_WATER_CAULDRON, ModCauldrons.CONDENSE_YELLOW_WATER_CAULDRON, ModCauldrons.LUMINOUS_YELLOW_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_YELLOW_WATER_CAULDRON);
    }

    private static synchronized void initCondensedCauldrons() {
        if (!CONDENSED_CAULDRONS.isEmpty()) return;
        Supplier<Block>[] condensedSuppliers = new Supplier[]{
                ModCauldrons.CONDENSE_BLACK_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_BLACK_WATER_CAULDRON,
                ModCauldrons.CONDENSE_BLUE_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_BLUE_WATER_CAULDRON,
                ModCauldrons.CONDENSE_BROWN_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_BROWN_WATER_CAULDRON,
                ModCauldrons.CONDENSE_CYAN_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_CYAN_WATER_CAULDRON,
                ModCauldrons.CONDENSE_GRAY_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_GRAY_WATER_CAULDRON,
                ModCauldrons.CONDENSE_GREEN_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_GREEN_WATER_CAULDRON,
                ModCauldrons.CONDENSE_LIGHT_BLUE_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_CAULDRON,
                ModCauldrons.CONDENSE_LIGHT_GRAY_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_CAULDRON,
                ModCauldrons.CONDENSE_LIME_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_LIME_WATER_CAULDRON,
                ModCauldrons.CONDENSE_MAGENTA_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_MAGENTA_WATER_CAULDRON,
                ModCauldrons.CONDENSE_ORANGE_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_ORANGE_WATER_CAULDRON,
                ModCauldrons.CONDENSE_PINK_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_PINK_WATER_CAULDRON,
                ModCauldrons.CONDENSE_PURPLE_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_PURPLE_WATER_CAULDRON,
                ModCauldrons.CONDENSE_RED_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_RED_WATER_CAULDRON,
                ModCauldrons.CONDENSE_WHITE_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_WHITE_WATER_CAULDRON,
                ModCauldrons.CONDENSE_YELLOW_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_YELLOW_WATER_CAULDRON
        };
        for (Supplier<Block> supplier : condensedSuppliers) {
            CONDENSED_CAULDRONS.add(supplier.get());
        }
    }

    public static boolean isModCauldron(Block block) {
        if (CAULDRON_COLORS.isEmpty()) {
            initCauldrons();
        }
        return CAULDRON_COLORS.containsKey(block);
    }

    public static boolean isCondensedCauldron(Block block) {
        if (CONDENSED_CAULDRONS.isEmpty()) {
            initCondensedCauldrons();
        }
        return CONDENSED_CAULDRONS.contains(block);
    }

    public static boolean isCondensedFluid(Fluid fluid) {
        return fluid instanceof ColorWaterFluid cwf && cwf.isCondensed();
    }

    public static boolean isCondensedBlock(Block block) {
        if (isCondensedCauldron(block)) return true;
        if (block instanceof LiquidBlock liquidBlock && liquidBlock.defaultBlockState().getFluidState().getType() instanceof ColorWaterFluid cwf) {
            return cwf.isCondensed();
        }
        return false;
    }

    public static int getCauldronColor(Block block) {
        if (CAULDRON_COLORS.isEmpty()) {
            initCauldrons();
        }
        Integer color = CAULDRON_COLORS.get(block);
        return color != null ? color : -1;
    }

    public static int blendRgb(int rgb1, int weight1, int rgb2, int weight2) {
        int totalWeight = weight1 + weight2;
        if (totalWeight <= 0) return rgb1;
        int r = (getRed(rgb1) * weight1 + getRed(rgb2) * weight2) / totalWeight;
        int g = (getGreen(rgb1) * weight1 + getGreen(rgb2) * weight2) / totalWeight;
        int b = (getBlue(rgb1) * weight1 + getBlue(rgb2) * weight2) / totalWeight;
        return (r << 16) | (g << 8) | b;
    }

    public static int getColoredWaterAt(@Nullable Level level, @Nullable BlockPos pos) {
        if (level == null || pos == null) return -1;
        FluidState fluid = level.getFluidState(pos);
        if (fluid.getType() instanceof ColorWaterFluid cwf) {
            return cwf.getTintColor();
        }
        if (fluid.getType() instanceof BaseColorWater) {
            return DEFAULT_COLOR;
        }

        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof WaterloggedColorBlockEntity waterloggedBe && waterloggedBe.hasFluid()) {
            return waterloggedBe.getFluid().getTintColor();
        }

        BlockState state = level.getBlockState(pos);
        Block block = state.getBlock();
        if (block instanceof LiquidBlock liquidBlock && liquidBlock.defaultBlockState().getFluidState().getType() instanceof ColorWaterFluid cwf) {
            return cwf.getTintColor();
        }
        return getCauldronColor(block);
    }
}
