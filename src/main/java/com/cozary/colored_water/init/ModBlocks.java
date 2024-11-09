package com.cozary.colored_water.init;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static com.cozary.colored_water.init.ModFluids.*;

public class ModBlocks {

    public static Block WHITE_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "white_water_block"), new FluidBlock(STILL_WHITE_WATER, FabricBlockSettings.copy(Blocks.WATER)) {
    });
    public static Block CONDENSE_WHITE_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "condense_white_water_block"), new FluidBlock(STILL_CONDENSE_WHITE_WATER, FabricBlockSettings.copy(Blocks.WATER)) {
    });
    public static Block LUMINOUS_WHITE_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "luminous_white_water_block"), new FluidBlock(STILL_LUMINOUS_WHITE_WATER, FabricBlockSettings.copy(Blocks.WATER).luminance(state -> 15)) {
    });
    public static Block LUMINOUS_CONDENSE_WHITE_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "luminous_condense_white_water_block"), new FluidBlock(STILL_LUMINOUS_CONDENSE_WHITE_WATER, FabricBlockSettings.copy(Blocks.WATER).luminance(state -> 15)) {
    });

    public static Block ORANGE_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "orange_water_block"), new FluidBlock(STILL_ORANGE_WATER, FabricBlockSettings.copy(Blocks.WATER)) {
    });
    public static Block CONDENSE_ORANGE_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "condense_orange_water_block"), new FluidBlock(STILL_CONDENSE_ORANGE_WATER, FabricBlockSettings.copy(Blocks.WATER)) {
    });
    public static Block LUMINOUS_ORANGE_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "luminous_orange_water_block"), new FluidBlock(STILL_LUMINOUS_ORANGE_WATER, FabricBlockSettings.copy(Blocks.WATER).luminance(state -> 15)) {
    });
    public static Block LUMINOUS_CONDENSE_ORANGE_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "luminous_condense_orange_water_block"), new FluidBlock(STILL_LUMINOUS_CONDENSE_ORANGE_WATER, FabricBlockSettings.copy(Blocks.WATER).luminance(state -> 15)) {
    });

    public static Block MAGENTA_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "magenta_water_block"), new FluidBlock(STILL_MAGENTA_WATER, FabricBlockSettings.copy(Blocks.WATER)) {
    });
    public static Block CONDENSE_MAGENTA_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "condense_magenta_water_block"), new FluidBlock(STILL_CONDENSE_MAGENTA_WATER, FabricBlockSettings.copy(Blocks.WATER)) {
    });
    public static Block LUMINOUS_MAGENTA_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "luminous_magenta_water_block"), new FluidBlock(STILL_LUMINOUS_MAGENTA_WATER, FabricBlockSettings.copy(Blocks.WATER).luminance(state -> 15)) {
    });
    public static Block LUMINOUS_CONDENSE_MAGENTA_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "luminous_condense_magenta_water_block"), new FluidBlock(STILL_LUMINOUS_CONDENSE_MAGENTA_WATER, FabricBlockSettings.copy(Blocks.WATER).luminance(state -> 15)) {
    });

    public static Block LIGHT_BLUE_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "light_blue_water_block"), new FluidBlock(STILL_LIGHT_BLUE_WATER, FabricBlockSettings.copy(Blocks.WATER)) {
    });
    public static Block CONDENSE_LIGHT_BLUE_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "condense_light_blue_water_block"), new FluidBlock(STILL_CONDENSE_LIGHT_BLUE_WATER, FabricBlockSettings.copy(Blocks.WATER)) {
    });
    public static Block LUMINOUS_LIGHT_BLUE_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "luminous_light_blue_water_block"), new FluidBlock(STILL_LUMINOUS_LIGHT_BLUE_WATER, FabricBlockSettings.copy(Blocks.WATER).luminance(state -> 15)) {
    });
    public static Block LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "luminous_condense_light_blue_water_block"), new FluidBlock(STILL_LUMINOUS_CONDENSE_LIGHT_BLUE_WATER, FabricBlockSettings.copy(Blocks.WATER).luminance(state -> 15)) {
    });

    public static Block YELLOW_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "yellow_water_block"), new FluidBlock(STILL_YELLOW_WATER, FabricBlockSettings.copy(Blocks.WATER)) {
    });
    public static Block CONDENSE_YELLOW_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "condense_yellow_water_block"), new FluidBlock(STILL_CONDENSE_YELLOW_WATER, FabricBlockSettings.copy(Blocks.WATER)) {
    });
    public static Block LUMINOUS_YELLOW_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "luminous_yellow_water_block"), new FluidBlock(STILL_LUMINOUS_YELLOW_WATER, FabricBlockSettings.copy(Blocks.WATER).luminance(state -> 15)) {
    });
    public static Block LUMINOUS_CONDENSE_YELLOW_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "luminous_condense_yellow_water_block"), new FluidBlock(STILL_LUMINOUS_CONDENSE_YELLOW_WATER, FabricBlockSettings.copy(Blocks.WATER).luminance(state -> 15)) {
    });

    public static Block LIME_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "lime_water_block"), new FluidBlock(STILL_LIME_WATER, FabricBlockSettings.copy(Blocks.WATER)) {
    });
    public static Block CONDENSE_LIME_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "condense_lime_water_block"), new FluidBlock(STILL_CONDENSE_LIME_WATER, FabricBlockSettings.copy(Blocks.WATER)) {
    });
    public static Block LUMINOUS_LIME_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "luminous_lime_water_block"), new FluidBlock(STILL_LUMINOUS_LIME_WATER, FabricBlockSettings.copy(Blocks.WATER).luminance(state -> 15)) {
    });
    public static Block LUMINOUS_CONDENSE_LIME_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "luminous_condense_lime_water_block"), new FluidBlock(STILL_LUMINOUS_CONDENSE_LIME_WATER, FabricBlockSettings.copy(Blocks.WATER).luminance(state -> 15)) {
    });

    public static Block PINK_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "pink_water_block"), new FluidBlock(STILL_PINK_WATER, FabricBlockSettings.copy(Blocks.WATER)) {
    });
    public static Block CONDENSE_PINK_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "condense_pink_water_block"), new FluidBlock(STILL_CONDENSE_PINK_WATER, FabricBlockSettings.copy(Blocks.WATER)) {
    });
    public static Block LUMINOUS_PINK_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "luminous_pink_water_block"), new FluidBlock(STILL_LUMINOUS_PINK_WATER, FabricBlockSettings.copy(Blocks.WATER).luminance(state -> 15)) {
    });
    public static Block LUMINOUS_CONDENSE_PINK_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "luminous_condense_pink_water_block"), new FluidBlock(STILL_LUMINOUS_CONDENSE_PINK_WATER, FabricBlockSettings.copy(Blocks.WATER).luminance(state -> 15)) {
    });

    public static Block GRAY_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "gray_water_block"), new FluidBlock(STILL_GRAY_WATER, FabricBlockSettings.copy(Blocks.WATER)) {
    });
    public static Block CONDENSE_GRAY_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "condense_gray_water_block"), new FluidBlock(STILL_CONDENSE_GRAY_WATER, FabricBlockSettings.copy(Blocks.WATER)) {
    });
    public static Block LUMINOUS_GRAY_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "luminous_gray_water_block"), new FluidBlock(STILL_LUMINOUS_GRAY_WATER, FabricBlockSettings.copy(Blocks.WATER).luminance(state -> 15)) {
    });
    public static Block LUMINOUS_CONDENSE_GRAY_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "luminous_condense_gray_water_block"), new FluidBlock(STILL_LUMINOUS_CONDENSE_GRAY_WATER, FabricBlockSettings.copy(Blocks.WATER).luminance(state -> 15)) {
    });

    public static Block LIGHT_GRAY_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "light_gray_water_block"), new FluidBlock(STILL_LIGHT_GRAY_WATER, FabricBlockSettings.copy(Blocks.WATER)) {
    });
    public static Block CONDENSE_LIGHT_GRAY_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "condense_light_gray_water_block"), new FluidBlock(STILL_CONDENSE_LIGHT_GRAY_WATER, FabricBlockSettings.copy(Blocks.WATER)) {
    });
    public static Block LUMINOUS_LIGHT_GRAY_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "luminous_light_gray_water_block"), new FluidBlock(STILL_LUMINOUS_LIGHT_GRAY_WATER, FabricBlockSettings.copy(Blocks.WATER).luminance(state -> 15)) {
    });
    public static Block LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "luminous_condense_light_gray_water_block"), new FluidBlock(STILL_LUMINOUS_CONDENSE_LIGHT_GRAY_WATER, FabricBlockSettings.copy(Blocks.WATER).luminance(state -> 15)) {
    });

    public static Block CYAN_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "cyan_water_block"), new FluidBlock(STILL_CYAN_WATER, FabricBlockSettings.copy(Blocks.WATER)) {
    });
    public static Block CONDENSE_CYAN_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "condense_cyan_water_block"), new FluidBlock(STILL_CONDENSE_CYAN_WATER, FabricBlockSettings.copy(Blocks.WATER)) {
    });
    public static Block LUMINOUS_CYAN_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "luminous_cyan_water_block"), new FluidBlock(STILL_LUMINOUS_CYAN_WATER, FabricBlockSettings.copy(Blocks.WATER).luminance(state -> 15)) {
    });
    public static Block LUMINOUS_CONDENSE_CYAN_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "luminous_condense_cyan_water_block"), new FluidBlock(STILL_LUMINOUS_CONDENSE_CYAN_WATER, FabricBlockSettings.copy(Blocks.WATER).luminance(state -> 15)) {
    });

    public static Block BLUE_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "blue"), new FluidBlock(STILL_BLUE_WATER, FabricBlockSettings.copy(Blocks.WATER)) {
    });
    public static Block CONDENSE_BLUE_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "condense_blue_water_block"), new FluidBlock(STILL_CONDENSE_BLUE_WATER, FabricBlockSettings.copy(Blocks.WATER)) {
    });
    public static Block LUMINOUS_BLUE_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "luminous_blue_water_block"), new FluidBlock(STILL_LUMINOUS_BLUE_WATER, FabricBlockSettings.copy(Blocks.WATER).luminance(state -> 15)) {
    });
    public static Block LUMINOUS_CONDENSE_BLUE_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "luminous_condense_blue_water_block"), new FluidBlock(STILL_LUMINOUS_CONDENSE_BLUE_WATER, FabricBlockSettings.copy(Blocks.WATER).luminance(state -> 15)) {
    });

    public static Block PURPLE_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "purple_water_block"), new FluidBlock(STILL_PURPLE_WATER, FabricBlockSettings.copy(Blocks.WATER)) {
    });
    public static Block CONDENSE_PURPLE_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "condense_purple_water_block"), new FluidBlock(STILL_CONDENSE_PURPLE_WATER, FabricBlockSettings.copy(Blocks.WATER)) {
    });
    public static Block LUMINOUS_PURPLE_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "luminous_purple_water_block"), new FluidBlock(STILL_LUMINOUS_PURPLE_WATER, FabricBlockSettings.copy(Blocks.WATER).luminance(state -> 15)) {
    });
    public static Block LUMINOUS_CONDENSE_PURPLE_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "luminous_condense_purple_water_block"), new FluidBlock(STILL_LUMINOUS_CONDENSE_PURPLE_WATER, FabricBlockSettings.copy(Blocks.WATER).luminance(state -> 15)) {
    });

    public static Block BLACK_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "black_water_block"), new FluidBlock(STILL_BLACK_WATER, FabricBlockSettings.copy(Blocks.WATER)) {
    });
    public static Block CONDENSE_BLACK_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "condense_black_water_block"), new FluidBlock(STILL_CONDENSE_BLACK_WATER, FabricBlockSettings.copy(Blocks.WATER)) {
    });
    public static Block LUMINOUS_BLACK_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "luminous_black_water_block"), new FluidBlock(STILL_LUMINOUS_BLACK_WATER, FabricBlockSettings.copy(Blocks.WATER).luminance(state -> 15)) {
    });
    public static Block LUMINOUS_CONDENSE_BLACK_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "luminous_condense_black_water_block"), new FluidBlock(STILL_LUMINOUS_CONDENSE_BLACK_WATER, FabricBlockSettings.copy(Blocks.WATER).luminance(state -> 15)) {
    });

    public static Block GREEN_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "green_water_block"), new FluidBlock(STILL_GREEN_WATER, FabricBlockSettings.copy(Blocks.WATER)) {
    });
    public static Block CONDENSE_GREEN_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "condense_green_water_block"), new FluidBlock(STILL_CONDENSE_GREEN_WATER, FabricBlockSettings.copy(Blocks.WATER)) {
    });
    public static Block LUMINOUS_GREEN_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "luminous_green_water_block"), new FluidBlock(STILL_LUMINOUS_GREEN_WATER, FabricBlockSettings.copy(Blocks.WATER).luminance(state -> 15)) {
    });
    public static Block LUMINOUS_CONDENSE_GREEN_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "luminous_condense_green_water_block"), new FluidBlock(STILL_LUMINOUS_CONDENSE_GREEN_WATER, FabricBlockSettings.copy(Blocks.WATER).luminance(state -> 15)) {
    });

    public static Block RED_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "red_water_block"), new FluidBlock(STILL_RED_WATER, FabricBlockSettings.copy(Blocks.WATER)) {
    });
    public static Block CONDENSE_RED_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "condense_red_water_block"), new FluidBlock(STILL_CONDENSE_RED_WATER, FabricBlockSettings.copy(Blocks.WATER)) {
    });
    public static Block LUMINOUS_RED_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "luminous_red_water_block"), new FluidBlock(STILL_LUMINOUS_RED_WATER, FabricBlockSettings.copy(Blocks.WATER).luminance(state -> 15)) {
    });
    public static Block LUMINOUS_CONDENSE_RED_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "luminous_condense_red_water_block"), new FluidBlock(STILL_LUMINOUS_CONDENSE_RED_WATER, FabricBlockSettings.copy(Blocks.WATER).luminance(state -> 15)) {
    });

    public static Block BROWN_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "brown_water_block"), new FluidBlock(STILL_BROWN_WATER, FabricBlockSettings.copy(Blocks.WATER)) {
    });
    public static Block CONDENSE_BROWN_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "condense_brown_water_block"), new FluidBlock(STILL_CONDENSE_BROWN_WATER, FabricBlockSettings.copy(Blocks.WATER)) {
    });
    public static Block LUMINOUS_BROWN_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "luminous_brown_water_block"), new FluidBlock(STILL_LUMINOUS_BROWN_WATER, FabricBlockSettings.copy(Blocks.WATER).luminance(state -> 15)) {
    });
    public static Block LUMINOUS_CONDENSE_BROWN_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of("colored_water", "luminous_condense_brown_water_block"), new FluidBlock(STILL_LUMINOUS_CONDENSE_BROWN_WATER, FabricBlockSettings.copy(Blocks.WATER).luminance(state -> 15)) {
    });

    public static void loadClass() {

    }

}
