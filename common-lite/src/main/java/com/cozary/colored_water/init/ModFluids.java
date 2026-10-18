package com.cozary.colored_water.init;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.fluids.BaseColorWater;
import com.cozary.colored_water.fluids.ColorWaterFluid;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

import java.util.function.Supplier;

public class ModFluids {

    public static final RegistrationProvider<Fluid> FLUIDS = RegistrationProvider.get(Registries.FLUID, ColoredWater.MOD_ID);

    private static class FluidPair {
        Supplier<BaseColorWater> still;
        Supplier<BaseColorWater> flowing;

        FluidPair(String baseName, Supplier<Item> bucket, Supplier<Block> block, DyeColor dyeColor, int tintColor, int sparkleColor, boolean condensed) {
            this.still = FLUIDS.register(baseName, () -> new ColorWaterFluid.Source(
                    this::getStill, this::getFlowing, bucket, block, dyeColor, tintColor, sparkleColor, condensed
            ));
            this.flowing = FLUIDS.register("flowing_" + baseName, () -> new ColorWaterFluid.Flowing(
                    this::getStill, this::getFlowing, bucket, block, dyeColor, tintColor, sparkleColor, condensed
            ));
        }

        private BaseColorWater getStill() {
            return this.still.get();
        }

        private BaseColorWater getFlowing() {
            return this.flowing.get();
        }
    }

    private static final FluidPair WHITE = new FluidPair("white_water", () -> ModItems.WHITE_WATER_BUCKET.get(), () -> ModBlocks.WHITE_WATER_BLOCK.get(), DyeColor.WHITE, 0xF9FFFE, -1, false);
    public static final Supplier<BaseColorWater> STILL_WHITE_WATER = WHITE.still;
    public static final Supplier<BaseColorWater> FLOWING_WHITE_WATER = WHITE.flowing;
    private static final FluidPair CONDENSE_WHITE = new FluidPair("condense_white_water", () -> ModItems.CONDENSE_WHITE_WATER_BUCKET.get(), () -> ModBlocks.CONDENSE_WHITE_WATER_BLOCK.get(), DyeColor.WHITE, 0xF9FFFE, -1, true);
    public static final Supplier<BaseColorWater> STILL_CONDENSE_WHITE_WATER = CONDENSE_WHITE.still;
    public static final Supplier<BaseColorWater> FLOWING_CONDENSE_WHITE_WATER = CONDENSE_WHITE.flowing;
    private static final FluidPair LUMINOUS_WHITE = new FluidPair("luminous_white_water", () -> ModItems.LUMINOUS_WHITE_WATER_BUCKET.get(), () -> ModBlocks.LUMINOUS_WHITE_WATER_BLOCK.get(), DyeColor.WHITE, 0xF9FFFE, 0xF9FFFE, false);
    public static final Supplier<BaseColorWater> STILL_LUMINOUS_WHITE_WATER = LUMINOUS_WHITE.still;
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_WHITE_WATER = LUMINOUS_WHITE.flowing;
    private static final FluidPair LUMINOUS_CONDENSE_WHITE = new FluidPair("luminous_condense_white_water", () -> ModItems.LUMINOUS_CONDENSE_WHITE_WATER_BUCKET.get(), () -> ModBlocks.LUMINOUS_CONDENSE_WHITE_WATER_BLOCK.get(), DyeColor.WHITE, 0xF9FFFE, 0xF9FFFE, true);
    public static final Supplier<BaseColorWater> STILL_LUMINOUS_CONDENSE_WHITE_WATER = LUMINOUS_CONDENSE_WHITE.still;
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_CONDENSE_WHITE_WATER = LUMINOUS_CONDENSE_WHITE.flowing;

    private static final FluidPair ORANGE = new FluidPair("orange_water", () -> ModItems.ORANGE_WATER_BUCKET.get(), () -> ModBlocks.ORANGE_WATER_BLOCK.get(), DyeColor.ORANGE, 0xF9801D, -1, false);
    public static final Supplier<BaseColorWater> STILL_ORANGE_WATER = ORANGE.still;
    public static final Supplier<BaseColorWater> FLOWING_ORANGE_WATER = ORANGE.flowing;
    private static final FluidPair CONDENSE_ORANGE = new FluidPair("condense_orange_water", () -> ModItems.CONDENSE_ORANGE_WATER_BUCKET.get(), () -> ModBlocks.CONDENSE_ORANGE_WATER_BLOCK.get(), DyeColor.ORANGE, 0xF9801D, -1, true);
    public static final Supplier<BaseColorWater> STILL_CONDENSE_ORANGE_WATER = CONDENSE_ORANGE.still;
    public static final Supplier<BaseColorWater> FLOWING_CONDENSE_ORANGE_WATER = CONDENSE_ORANGE.flowing;
    private static final FluidPair LUMINOUS_ORANGE = new FluidPair("luminous_orange_water", () -> ModItems.LUMINOUS_ORANGE_WATER_BUCKET.get(), () -> ModBlocks.LUMINOUS_ORANGE_WATER_BLOCK.get(), DyeColor.ORANGE, 0xF9801D, 0xF9801D, false);
    public static final Supplier<BaseColorWater> STILL_LUMINOUS_ORANGE_WATER = LUMINOUS_ORANGE.still;
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_ORANGE_WATER = LUMINOUS_ORANGE.flowing;
    private static final FluidPair LUMINOUS_CONDENSE_ORANGE = new FluidPair("luminous_condense_orange_water", () -> ModItems.LUMINOUS_CONDENSE_ORANGE_WATER_BUCKET.get(), () -> ModBlocks.LUMINOUS_CONDENSE_ORANGE_WATER_BLOCK.get(), DyeColor.ORANGE, 0xF9801D, 0xF9801D, true);
    public static final Supplier<BaseColorWater> STILL_LUMINOUS_CONDENSE_ORANGE_WATER = LUMINOUS_CONDENSE_ORANGE.still;
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_CONDENSE_ORANGE_WATER = LUMINOUS_CONDENSE_ORANGE.flowing;

    private static final FluidPair MAGENTA = new FluidPair("magenta_water", () -> ModItems.MAGENTA_WATER_BUCKET.get(), () -> ModBlocks.MAGENTA_WATER_BLOCK.get(), DyeColor.MAGENTA, 0xC74EBD, -1, false);
    public static final Supplier<BaseColorWater> STILL_MAGENTA_WATER = MAGENTA.still;
    public static final Supplier<BaseColorWater> FLOWING_MAGENTA_WATER = MAGENTA.flowing;
    private static final FluidPair CONDENSE_MAGENTA = new FluidPair("condense_magenta_water", () -> ModItems.CONDENSE_MAGENTA_WATER_BUCKET.get(), () -> ModBlocks.CONDENSE_MAGENTA_WATER_BLOCK.get(), DyeColor.MAGENTA, 0xC74EBD, -1, true);
    public static final Supplier<BaseColorWater> STILL_CONDENSE_MAGENTA_WATER = CONDENSE_MAGENTA.still;
    public static final Supplier<BaseColorWater> FLOWING_CONDENSE_MAGENTA_WATER = CONDENSE_MAGENTA.flowing;
    private static final FluidPair LUMINOUS_MAGENTA = new FluidPair("luminous_magenta_water", () -> ModItems.LUMINOUS_MAGENTA_WATER_BUCKET.get(), () -> ModBlocks.LUMINOUS_MAGENTA_WATER_BLOCK.get(), DyeColor.MAGENTA, 0xC74EBD, 0xC74EBD, false);
    public static final Supplier<BaseColorWater> STILL_LUMINOUS_MAGENTA_WATER = LUMINOUS_MAGENTA.still;
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_MAGENTA_WATER = LUMINOUS_MAGENTA.flowing;
    private static final FluidPair LUMINOUS_CONDENSE_MAGENTA = new FluidPair("luminous_condense_magenta_water", () -> ModItems.LUMINOUS_CONDENSE_MAGENTA_WATER_BUCKET.get(), () -> ModBlocks.LUMINOUS_CONDENSE_MAGENTA_WATER_BLOCK.get(), DyeColor.MAGENTA, 0xC74EBD, 0xC74EBD, true);
    public static final Supplier<BaseColorWater> STILL_LUMINOUS_CONDENSE_MAGENTA_WATER = LUMINOUS_CONDENSE_MAGENTA.still;
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_CONDENSE_MAGENTA_WATER = LUMINOUS_CONDENSE_MAGENTA.flowing;

    private static final FluidPair LIGHT_BLUE = new FluidPair("light_blue_water", () -> ModItems.LIGHT_BLUE_WATER_BUCKET.get(), () -> ModBlocks.LIGHT_BLUE_WATER_BLOCK.get(), DyeColor.LIGHT_BLUE, 0x3AB3DA, -1, false);
    public static final Supplier<BaseColorWater> STILL_LIGHT_BLUE_WATER = LIGHT_BLUE.still;
    public static final Supplier<BaseColorWater> FLOWING_LIGHT_BLUE_WATER = LIGHT_BLUE.flowing;
    private static final FluidPair CONDENSE_LIGHT_BLUE = new FluidPair("condense_light_blue_water", () -> ModItems.CONDENSE_LIGHT_BLUE_WATER_BUCKET.get(), () -> ModBlocks.CONDENSE_LIGHT_BLUE_WATER_BLOCK.get(), DyeColor.LIGHT_BLUE, 0x3AB3DA, -1, true);
    public static final Supplier<BaseColorWater> STILL_CONDENSE_LIGHT_BLUE_WATER = CONDENSE_LIGHT_BLUE.still;
    public static final Supplier<BaseColorWater> FLOWING_CONDENSE_LIGHT_BLUE_WATER = CONDENSE_LIGHT_BLUE.flowing;
    private static final FluidPair LUMINOUS_LIGHT_BLUE = new FluidPair("luminous_light_blue_water", () -> ModItems.LUMINOUS_LIGHT_BLUE_WATER_BUCKET.get(), () -> ModBlocks.LUMINOUS_LIGHT_BLUE_WATER_BLOCK.get(), DyeColor.LIGHT_BLUE, 0x3AB3DA, 0x3AB3DA, false);
    public static final Supplier<BaseColorWater> STILL_LUMINOUS_LIGHT_BLUE_WATER = LUMINOUS_LIGHT_BLUE.still;
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_LIGHT_BLUE_WATER = LUMINOUS_LIGHT_BLUE.flowing;
    private static final FluidPair LUMINOUS_CONDENSE_LIGHT_BLUE = new FluidPair("luminous_condense_light_blue_water", () -> ModItems.LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_BUCKET.get(), () -> ModBlocks.LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_BLOCK.get(), DyeColor.LIGHT_BLUE, 0x3AB3DA, 0x3AB3DA, true);
    public static final Supplier<BaseColorWater> STILL_LUMINOUS_CONDENSE_LIGHT_BLUE_WATER = LUMINOUS_CONDENSE_LIGHT_BLUE.still;
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_CONDENSE_LIGHT_BLUE_WATER = LUMINOUS_CONDENSE_LIGHT_BLUE.flowing;

    private static final FluidPair YELLOW = new FluidPair("yellow_water", () -> ModItems.YELLOW_WATER_BUCKET.get(), () -> ModBlocks.YELLOW_WATER_BLOCK.get(), DyeColor.YELLOW, 0xFED83D, -1, false);
    public static final Supplier<BaseColorWater> STILL_YELLOW_WATER = YELLOW.still;
    public static final Supplier<BaseColorWater> FLOWING_YELLOW_WATER = YELLOW.flowing;
    private static final FluidPair CONDENSE_YELLOW = new FluidPair("condense_yellow_water", () -> ModItems.CONDENSE_YELLOW_WATER_BUCKET.get(), () -> ModBlocks.CONDENSE_YELLOW_WATER_BLOCK.get(), DyeColor.YELLOW, 0xFED83D, -1, true);
    public static final Supplier<BaseColorWater> STILL_CONDENSE_YELLOW_WATER = CONDENSE_YELLOW.still;
    public static final Supplier<BaseColorWater> FLOWING_CONDENSE_YELLOW_WATER = CONDENSE_YELLOW.flowing;
    private static final FluidPair LUMINOUS_YELLOW = new FluidPair("luminous_yellow_water", () -> ModItems.LUMINOUS_YELLOW_WATER_BUCKET.get(), () -> ModBlocks.LUMINOUS_YELLOW_WATER_BLOCK.get(), DyeColor.YELLOW, 0xFED83D, 0xFED83D, false);
    public static final Supplier<BaseColorWater> STILL_LUMINOUS_YELLOW_WATER = LUMINOUS_YELLOW.still;
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_YELLOW_WATER = LUMINOUS_YELLOW.flowing;
    private static final FluidPair LUMINOUS_CONDENSE_YELLOW = new FluidPair("luminous_condense_yellow_water", () -> ModItems.LUMINOUS_CONDENSE_YELLOW_WATER_BUCKET.get(), () -> ModBlocks.LUMINOUS_CONDENSE_YELLOW_WATER_BLOCK.get(), DyeColor.YELLOW, 0xFED83D, 0xFED83D, true);
    public static final Supplier<BaseColorWater> STILL_LUMINOUS_CONDENSE_YELLOW_WATER = LUMINOUS_CONDENSE_YELLOW.still;
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_CONDENSE_YELLOW_WATER = LUMINOUS_CONDENSE_YELLOW.flowing;

    private static final FluidPair LIME = new FluidPair("lime_water", () -> ModItems.LIME_WATER_BUCKET.get(), () -> ModBlocks.LIME_WATER_BLOCK.get(), DyeColor.LIME, 0x80C71F, -1, false);
    public static final Supplier<BaseColorWater> STILL_LIME_WATER = LIME.still;
    public static final Supplier<BaseColorWater> FLOWING_LIME_WATER = LIME.flowing;
    private static final FluidPair CONDENSE_LIME = new FluidPair("condense_lime_water", () -> ModItems.CONDENSE_LIME_WATER_BUCKET.get(), () -> ModBlocks.CONDENSE_LIME_WATER_BLOCK.get(), DyeColor.LIME, 0x80C71F, -1, true);
    public static final Supplier<BaseColorWater> STILL_CONDENSE_LIME_WATER = CONDENSE_LIME.still;
    public static final Supplier<BaseColorWater> FLOWING_CONDENSE_LIME_WATER = CONDENSE_LIME.flowing;
    private static final FluidPair LUMINOUS_LIME = new FluidPair("luminous_lime_water", () -> ModItems.LUMINOUS_LIME_WATER_BUCKET.get(), () -> ModBlocks.LUMINOUS_LIME_WATER_BLOCK.get(), DyeColor.LIME, 0x80C71F, 0x80C71F, false);
    public static final Supplier<BaseColorWater> STILL_LUMINOUS_LIME_WATER = LUMINOUS_LIME.still;
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_LIME_WATER = LUMINOUS_LIME.flowing;
    private static final FluidPair LUMINOUS_CONDENSE_LIME = new FluidPair("luminous_condense_lime_water", () -> ModItems.LUMINOUS_CONDENSE_LIME_WATER_BUCKET.get(), () -> ModBlocks.LUMINOUS_CONDENSE_LIME_WATER_BLOCK.get(), DyeColor.LIME, 0x80C71F, 0x80C71F, true);
    public static final Supplier<BaseColorWater> STILL_LUMINOUS_CONDENSE_LIME_WATER = LUMINOUS_CONDENSE_LIME.still;
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_CONDENSE_LIME_WATER = LUMINOUS_CONDENSE_LIME.flowing;

    private static final FluidPair PINK = new FluidPair("pink_water", () -> ModItems.PINK_WATER_BUCKET.get(), () -> ModBlocks.PINK_WATER_BLOCK.get(), DyeColor.PINK, 0xF38BAA, -1, false);
    public static final Supplier<BaseColorWater> STILL_PINK_WATER = PINK.still;
    public static final Supplier<BaseColorWater> FLOWING_PINK_WATER = PINK.flowing;
    private static final FluidPair CONDENSE_PINK = new FluidPair("condense_pink_water", () -> ModItems.CONDENSE_PINK_WATER_BUCKET.get(), () -> ModBlocks.CONDENSE_PINK_WATER_BLOCK.get(), DyeColor.PINK, 0xF38BAA, -1, true);
    public static final Supplier<BaseColorWater> STILL_CONDENSE_PINK_WATER = CONDENSE_PINK.still;
    public static final Supplier<BaseColorWater> FLOWING_CONDENSE_PINK_WATER = CONDENSE_PINK.flowing;
    private static final FluidPair LUMINOUS_PINK = new FluidPair("luminous_pink_water", () -> ModItems.LUMINOUS_PINK_WATER_BUCKET.get(), () -> ModBlocks.LUMINOUS_PINK_WATER_BLOCK.get(), DyeColor.PINK, 0xF38BAA, 0xF38BAA, false);
    public static final Supplier<BaseColorWater> STILL_LUMINOUS_PINK_WATER = LUMINOUS_PINK.still;
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_PINK_WATER = LUMINOUS_PINK.flowing;
    private static final FluidPair LUMINOUS_CONDENSE_PINK = new FluidPair("luminous_condense_pink_water", () -> ModItems.LUMINOUS_CONDENSE_PINK_WATER_BUCKET.get(), () -> ModBlocks.LUMINOUS_CONDENSE_PINK_WATER_BLOCK.get(), DyeColor.PINK, 0xF38BAA, 0xF38BAA, true);
    public static final Supplier<BaseColorWater> STILL_LUMINOUS_CONDENSE_PINK_WATER = LUMINOUS_CONDENSE_PINK.still;
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_CONDENSE_PINK_WATER = LUMINOUS_CONDENSE_PINK.flowing;

    private static final FluidPair GRAY = new FluidPair("gray_water", () -> ModItems.GRAY_WATER_BUCKET.get(), () -> ModBlocks.GRAY_WATER_BLOCK.get(), DyeColor.GRAY, 0x474F52, -1, false);
    public static final Supplier<BaseColorWater> STILL_GRAY_WATER = GRAY.still;
    public static final Supplier<BaseColorWater> FLOWING_GRAY_WATER = GRAY.flowing;
    private static final FluidPair CONDENSE_GRAY = new FluidPair("condense_gray_water", () -> ModItems.CONDENSE_GRAY_WATER_BUCKET.get(), () -> ModBlocks.CONDENSE_GRAY_WATER_BLOCK.get(), DyeColor.GRAY, 0x474F52, -1, true);
    public static final Supplier<BaseColorWater> STILL_CONDENSE_GRAY_WATER = CONDENSE_GRAY.still;
    public static final Supplier<BaseColorWater> FLOWING_CONDENSE_GRAY_WATER = CONDENSE_GRAY.flowing;
    private static final FluidPair LUMINOUS_GRAY = new FluidPair("luminous_gray_water", () -> ModItems.LUMINOUS_GRAY_WATER_BUCKET.get(), () -> ModBlocks.LUMINOUS_GRAY_WATER_BLOCK.get(), DyeColor.GRAY, 0x474F52, 0x474F52, false);
    public static final Supplier<BaseColorWater> STILL_LUMINOUS_GRAY_WATER = LUMINOUS_GRAY.still;
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_GRAY_WATER = LUMINOUS_GRAY.flowing;
    private static final FluidPair LUMINOUS_CONDENSE_GRAY = new FluidPair("luminous_condense_gray_water", () -> ModItems.LUMINOUS_CONDENSE_GRAY_WATER_BUCKET.get(), () -> ModBlocks.LUMINOUS_CONDENSE_GRAY_WATER_BLOCK.get(), DyeColor.GRAY, 0x474F52, 0x474F52, true);
    public static final Supplier<BaseColorWater> STILL_LUMINOUS_CONDENSE_GRAY_WATER = LUMINOUS_CONDENSE_GRAY.still;
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_CONDENSE_GRAY_WATER = LUMINOUS_CONDENSE_GRAY.flowing;

    private static final FluidPair LIGHT_GRAY = new FluidPair("light_gray_water", () -> ModItems.LIGHT_GRAY_WATER_BUCKET.get(), () -> ModBlocks.LIGHT_GRAY_WATER_BLOCK.get(), DyeColor.LIGHT_GRAY, 0x9D9D97, -1, false);
    public static final Supplier<BaseColorWater> STILL_LIGHT_GRAY_WATER = LIGHT_GRAY.still;
    public static final Supplier<BaseColorWater> FLOWING_LIGHT_GRAY_WATER = LIGHT_GRAY.flowing;
    private static final FluidPair CONDENSE_LIGHT_GRAY = new FluidPair("condense_light_gray_water", () -> ModItems.CONDENSE_LIGHT_GRAY_WATER_BUCKET.get(), () -> ModBlocks.CONDENSE_LIGHT_GRAY_WATER_BLOCK.get(), DyeColor.LIGHT_GRAY, 0x9D9D97, -1, true);
    public static final Supplier<BaseColorWater> STILL_CONDENSE_LIGHT_GRAY_WATER = CONDENSE_LIGHT_GRAY.still;
    public static final Supplier<BaseColorWater> FLOWING_CONDENSE_LIGHT_GRAY_WATER = CONDENSE_LIGHT_GRAY.flowing;
    private static final FluidPair LUMINOUS_LIGHT_GRAY = new FluidPair("luminous_light_gray_water", () -> ModItems.LUMINOUS_LIGHT_GRAY_WATER_BUCKET.get(), () -> ModBlocks.LUMINOUS_LIGHT_GRAY_WATER_BLOCK.get(), DyeColor.LIGHT_GRAY, 0x9D9D97, 0x9D9D97, false);
    public static final Supplier<BaseColorWater> STILL_LUMINOUS_LIGHT_GRAY_WATER = LUMINOUS_LIGHT_GRAY.still;
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_LIGHT_GRAY_WATER = LUMINOUS_LIGHT_GRAY.flowing;
    private static final FluidPair LUMINOUS_CONDENSE_LIGHT_GRAY = new FluidPair("luminous_condense_light_gray_water", () -> ModItems.LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_BUCKET.get(), () -> ModBlocks.LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_BLOCK.get(), DyeColor.LIGHT_GRAY, 0x9D9D97, 0x9D9D97, true);
    public static final Supplier<BaseColorWater> STILL_LUMINOUS_CONDENSE_LIGHT_GRAY_WATER = LUMINOUS_CONDENSE_LIGHT_GRAY.still;
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_CONDENSE_LIGHT_GRAY_WATER = LUMINOUS_CONDENSE_LIGHT_GRAY.flowing;

    private static final FluidPair CYAN = new FluidPair("cyan_water", () -> ModItems.CYAN_WATER_BUCKET.get(), () -> ModBlocks.CYAN_WATER_BLOCK.get(), DyeColor.CYAN, 0x169C9C, -1, false);
    public static final Supplier<BaseColorWater> STILL_CYAN_WATER = CYAN.still;
    public static final Supplier<BaseColorWater> FLOWING_CYAN_WATER = CYAN.flowing;
    private static final FluidPair CONDENSE_CYAN = new FluidPair("condense_cyan_water", () -> ModItems.CONDENSE_CYAN_WATER_BUCKET.get(), () -> ModBlocks.CONDENSE_CYAN_WATER_BLOCK.get(), DyeColor.CYAN, 0x169C9C, -1, true);
    public static final Supplier<BaseColorWater> STILL_CONDENSE_CYAN_WATER = CONDENSE_CYAN.still;
    public static final Supplier<BaseColorWater> FLOWING_CONDENSE_CYAN_WATER = CONDENSE_CYAN.flowing;
    private static final FluidPair LUMINOUS_CYAN = new FluidPair("luminous_cyan_water", () -> ModItems.LUMINOUS_CYAN_WATER_BUCKET.get(), () -> ModBlocks.LUMINOUS_CYAN_WATER_BLOCK.get(), DyeColor.CYAN, 0x169C9C, 0x169C9C, false);
    public static final Supplier<BaseColorWater> STILL_LUMINOUS_CYAN_WATER = LUMINOUS_CYAN.still;
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_CYAN_WATER = LUMINOUS_CYAN.flowing;
    private static final FluidPair LUMINOUS_CONDENSE_CYAN = new FluidPair("luminous_condense_cyan_water", () -> ModItems.LUMINOUS_CONDENSE_CYAN_WATER_BUCKET.get(), () -> ModBlocks.LUMINOUS_CONDENSE_CYAN_WATER_BLOCK.get(), DyeColor.CYAN, 0x169C9C, 0x169C9C, true);
    public static final Supplier<BaseColorWater> STILL_LUMINOUS_CONDENSE_CYAN_WATER = LUMINOUS_CONDENSE_CYAN.still;
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_CONDENSE_CYAN_WATER = LUMINOUS_CONDENSE_CYAN.flowing;

    private static final FluidPair PURPLE = new FluidPair("purple_water", () -> ModItems.PURPLE_WATER_BUCKET.get(), () -> ModBlocks.PURPLE_WATER_BLOCK.get(), DyeColor.PURPLE, 0x8932B8, -1, false);
    public static final Supplier<BaseColorWater> STILL_PURPLE_WATER = PURPLE.still;
    public static final Supplier<BaseColorWater> FLOWING_PURPLE_WATER = PURPLE.flowing;
    private static final FluidPair CONDENSE_PURPLE = new FluidPair("condense_purple_water", () -> ModItems.CONDENSE_PURPLE_WATER_BUCKET.get(), () -> ModBlocks.CONDENSE_PURPLE_WATER_BLOCK.get(), DyeColor.PURPLE, 0x8932B8, -1, true);
    public static final Supplier<BaseColorWater> STILL_CONDENSE_PURPLE_WATER = CONDENSE_PURPLE.still;
    public static final Supplier<BaseColorWater> FLOWING_CONDENSE_PURPLE_WATER = CONDENSE_PURPLE.flowing;
    private static final FluidPair LUMINOUS_PURPLE = new FluidPair("luminous_purple_water", () -> ModItems.LUMINOUS_PURPLE_WATER_BUCKET.get(), () -> ModBlocks.LUMINOUS_PURPLE_WATER_BLOCK.get(), DyeColor.PURPLE, 0x8932B8, 0x8932B8, false);
    public static final Supplier<BaseColorWater> STILL_LUMINOUS_PURPLE_WATER = LUMINOUS_PURPLE.still;
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_PURPLE_WATER = LUMINOUS_PURPLE.flowing;
    private static final FluidPair LUMINOUS_CONDENSE_PURPLE = new FluidPair("luminous_condense_purple_water", () -> ModItems.LUMINOUS_CONDENSE_PURPLE_WATER_BUCKET.get(), () -> ModBlocks.LUMINOUS_CONDENSE_PURPLE_WATER_BLOCK.get(), DyeColor.PURPLE, 0x8932B8, 0x8932B8, true);
    public static final Supplier<BaseColorWater> STILL_LUMINOUS_CONDENSE_PURPLE_WATER = LUMINOUS_CONDENSE_PURPLE.still;
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_CONDENSE_PURPLE_WATER = LUMINOUS_CONDENSE_PURPLE.flowing;

    private static final FluidPair BLUE = new FluidPair("blue_water", () -> ModItems.BLUE_WATER_BUCKET.get(), () -> ModBlocks.BLUE_WATER_BLOCK.get(), DyeColor.BLUE, 0x3C44AA, -1, false);
    public static final Supplier<BaseColorWater> STILL_BLUE_WATER = BLUE.still;
    public static final Supplier<BaseColorWater> FLOWING_BLUE_WATER = BLUE.flowing;
    private static final FluidPair CONDENSE_BLUE = new FluidPair("condense_blue_water", () -> ModItems.CONDENSE_BLUE_WATER_BUCKET.get(), () -> ModBlocks.CONDENSE_BLUE_WATER_BLOCK.get(), DyeColor.BLUE, 0x3C44AA, -1, true);
    public static final Supplier<BaseColorWater> STILL_CONDENSE_BLUE_WATER = CONDENSE_BLUE.still;
    public static final Supplier<BaseColorWater> FLOWING_CONDENSE_BLUE_WATER = CONDENSE_BLUE.flowing;
    private static final FluidPair LUMINOUS_BLUE = new FluidPair("luminous_blue_water", () -> ModItems.LUMINOUS_BLUE_WATER_BUCKET.get(), () -> ModBlocks.LUMINOUS_BLUE_WATER_BLOCK.get(), DyeColor.BLUE, 0x3C44AA, 0x3C44AA, false);
    public static final Supplier<BaseColorWater> STILL_LUMINOUS_BLUE_WATER = LUMINOUS_BLUE.still;
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_BLUE_WATER = LUMINOUS_BLUE.flowing;
    private static final FluidPair LUMINOUS_CONDENSE_BLUE = new FluidPair("luminous_condense_blue_water", () -> ModItems.LUMINOUS_CONDENSE_BLUE_WATER_BUCKET.get(), () -> ModBlocks.LUMINOUS_CONDENSE_BLUE_WATER_BLOCK.get(), DyeColor.BLUE, 0x3C44AA, 0x3C44AA, true);
    public static final Supplier<BaseColorWater> STILL_LUMINOUS_CONDENSE_BLUE_WATER = LUMINOUS_CONDENSE_BLUE.still;
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_CONDENSE_BLUE_WATER = LUMINOUS_CONDENSE_BLUE.flowing;

    private static final FluidPair BROWN = new FluidPair("brown_water", () -> ModItems.BROWN_WATER_BUCKET.get(), () -> ModBlocks.BROWN_WATER_BLOCK.get(), DyeColor.BROWN, 0x835432, -1, false);
    public static final Supplier<BaseColorWater> STILL_BROWN_WATER = BROWN.still;
    public static final Supplier<BaseColorWater> FLOWING_BROWN_WATER = BROWN.flowing;
    private static final FluidPair CONDENSE_BROWN = new FluidPair("condense_brown_water", () -> ModItems.CONDENSE_BROWN_WATER_BUCKET.get(), () -> ModBlocks.CONDENSE_BROWN_WATER_BLOCK.get(), DyeColor.BROWN, 0x835432, -1, true);
    public static final Supplier<BaseColorWater> STILL_CONDENSE_BROWN_WATER = CONDENSE_BROWN.still;
    public static final Supplier<BaseColorWater> FLOWING_CONDENSE_BROWN_WATER = CONDENSE_BROWN.flowing;
    private static final FluidPair LUMINOUS_BROWN = new FluidPair("luminous_brown_water", () -> ModItems.LUMINOUS_BROWN_WATER_BUCKET.get(), () -> ModBlocks.LUMINOUS_BROWN_WATER_BLOCK.get(), DyeColor.BROWN, 0x835432, 0x835432, false);
    public static final Supplier<BaseColorWater> STILL_LUMINOUS_BROWN_WATER = LUMINOUS_BROWN.still;
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_BROWN_WATER = LUMINOUS_BROWN.flowing;
    private static final FluidPair LUMINOUS_CONDENSE_BROWN = new FluidPair("luminous_condense_brown_water", () -> ModItems.LUMINOUS_CONDENSE_BROWN_WATER_BUCKET.get(), () -> ModBlocks.LUMINOUS_CONDENSE_BROWN_WATER_BLOCK.get(), DyeColor.BROWN, 0x835432, 0x835432, true);
    public static final Supplier<BaseColorWater> STILL_LUMINOUS_CONDENSE_BROWN_WATER = LUMINOUS_CONDENSE_BROWN.still;
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_CONDENSE_BROWN_WATER = LUMINOUS_CONDENSE_BROWN.flowing;

    private static final FluidPair GREEN = new FluidPair("green_water", () -> ModItems.GREEN_WATER_BUCKET.get(), () -> ModBlocks.GREEN_WATER_BLOCK.get(), DyeColor.GREEN, 0x5E7C16, -1, false);
    public static final Supplier<BaseColorWater> STILL_GREEN_WATER = GREEN.still;
    public static final Supplier<BaseColorWater> FLOWING_GREEN_WATER = GREEN.flowing;
    private static final FluidPair CONDENSE_GREEN = new FluidPair("condense_green_water", () -> ModItems.CONDENSE_GREEN_WATER_BUCKET.get(), () -> ModBlocks.CONDENSE_GREEN_WATER_BLOCK.get(), DyeColor.GREEN, 0x5E7C16, -1, true);
    public static final Supplier<BaseColorWater> STILL_CONDENSE_GREEN_WATER = CONDENSE_GREEN.still;
    public static final Supplier<BaseColorWater> FLOWING_CONDENSE_GREEN_WATER = CONDENSE_GREEN.flowing;
    private static final FluidPair LUMINOUS_GREEN = new FluidPair("luminous_green_water", () -> ModItems.LUMINOUS_GREEN_WATER_BUCKET.get(), () -> ModBlocks.LUMINOUS_GREEN_WATER_BLOCK.get(), DyeColor.GREEN, 0x5E7C16, 0x5E7C16, false);
    public static final Supplier<BaseColorWater> STILL_LUMINOUS_GREEN_WATER = LUMINOUS_GREEN.still;
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_GREEN_WATER = LUMINOUS_GREEN.flowing;
    private static final FluidPair LUMINOUS_CONDENSE_GREEN = new FluidPair("luminous_condense_green_water", () -> ModItems.LUMINOUS_CONDENSE_GREEN_WATER_BUCKET.get(), () -> ModBlocks.LUMINOUS_CONDENSE_GREEN_WATER_BLOCK.get(), DyeColor.GREEN, 0x5E7C16, 0x5E7C16, true);
    public static final Supplier<BaseColorWater> STILL_LUMINOUS_CONDENSE_GREEN_WATER = LUMINOUS_CONDENSE_GREEN.still;
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_CONDENSE_GREEN_WATER = LUMINOUS_CONDENSE_GREEN.flowing;

    private static final FluidPair RED = new FluidPair("red_water", () -> ModItems.RED_WATER_BUCKET.get(), () -> ModBlocks.RED_WATER_BLOCK.get(), DyeColor.RED, 0xB02E26, -1, false);
    public static final Supplier<BaseColorWater> STILL_RED_WATER = RED.still;
    public static final Supplier<BaseColorWater> FLOWING_RED_WATER = RED.flowing;
    private static final FluidPair CONDENSE_RED = new FluidPair("condense_red_water", () -> ModItems.CONDENSE_RED_WATER_BUCKET.get(), () -> ModBlocks.CONDENSE_RED_WATER_BLOCK.get(), DyeColor.RED, 0xB02E26, -1, true);
    public static final Supplier<BaseColorWater> STILL_CONDENSE_RED_WATER = CONDENSE_RED.still;
    public static final Supplier<BaseColorWater> FLOWING_CONDENSE_RED_WATER = CONDENSE_RED.flowing;
    private static final FluidPair LUMINOUS_RED = new FluidPair("luminous_red_water", () -> ModItems.LUMINOUS_RED_WATER_BUCKET.get(), () -> ModBlocks.LUMINOUS_RED_WATER_BLOCK.get(), DyeColor.RED, 0xB02E26, 0xB02E26, false);
    public static final Supplier<BaseColorWater> STILL_LUMINOUS_RED_WATER = LUMINOUS_RED.still;
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_RED_WATER = LUMINOUS_RED.flowing;
    private static final FluidPair LUMINOUS_CONDENSE_RED = new FluidPair("luminous_condense_red_water", () -> ModItems.LUMINOUS_CONDENSE_RED_WATER_BUCKET.get(), () -> ModBlocks.LUMINOUS_CONDENSE_RED_WATER_BLOCK.get(), DyeColor.RED, 0xB02E26, 0xB02E26, true);
    public static final Supplier<BaseColorWater> STILL_LUMINOUS_CONDENSE_RED_WATER = LUMINOUS_CONDENSE_RED.still;
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_CONDENSE_RED_WATER = LUMINOUS_CONDENSE_RED.flowing;

    private static final FluidPair BLACK = new FluidPair("black_water", () -> ModItems.BLACK_WATER_BUCKET.get(), () -> ModBlocks.BLACK_WATER_BLOCK.get(), DyeColor.BLACK, 0x1D1D21, -1, false);
    public static final Supplier<BaseColorWater> STILL_BLACK_WATER = BLACK.still;
    public static final Supplier<BaseColorWater> FLOWING_BLACK_WATER = BLACK.flowing;
    private static final FluidPair CONDENSE_BLACK = new FluidPair("condense_black_water", () -> ModItems.CONDENSE_BLACK_WATER_BUCKET.get(), () -> ModBlocks.CONDENSE_BLACK_WATER_BLOCK.get(), DyeColor.BLACK, 0x1D1D21, -1, true);
    public static final Supplier<BaseColorWater> STILL_CONDENSE_BLACK_WATER = CONDENSE_BLACK.still;
    public static final Supplier<BaseColorWater> FLOWING_CONDENSE_BLACK_WATER = CONDENSE_BLACK.flowing;
    private static final FluidPair LUMINOUS_BLACK = new FluidPair("luminous_black_water", () -> ModItems.LUMINOUS_BLACK_WATER_BUCKET.get(), () -> ModBlocks.LUMINOUS_BLACK_WATER_BLOCK.get(), DyeColor.BLACK, 0x1D1D21, 0x1D1D21, false);
    public static final Supplier<BaseColorWater> STILL_LUMINOUS_BLACK_WATER = LUMINOUS_BLACK.still;
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_BLACK_WATER = LUMINOUS_BLACK.flowing;
    private static final FluidPair LUMINOUS_CONDENSE_BLACK = new FluidPair("luminous_condense_black_water", () -> ModItems.LUMINOUS_CONDENSE_BLACK_WATER_BUCKET.get(), () -> ModBlocks.LUMINOUS_CONDENSE_BLACK_WATER_BLOCK.get(), DyeColor.BLACK, 0x1D1D21, 0x1D1D21, true);
    public static final Supplier<BaseColorWater> STILL_LUMINOUS_CONDENSE_BLACK_WATER = LUMINOUS_CONDENSE_BLACK.still;
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_CONDENSE_BLACK_WATER = LUMINOUS_CONDENSE_BLACK.flowing;

    public static void loadClass() {
    }

}
