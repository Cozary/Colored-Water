package com.cozary.colored_water.init;


import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.cozary.colored_water.ColoredWater.MOD_ID;
import static com.cozary.colored_water.init.ModFluids.*;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.createBlocks(MOD_ID);

    public static Supplier<LiquidBlock> WHITE_WATER_BLOCK = BLOCKS.register("white_water_block", () -> new LiquidBlock(STILL_WHITE_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static Supplier<LiquidBlock> CONDENSE_WHITE_WATER_BLOCK = BLOCKS.register("condense_white_water_block", () -> new LiquidBlock(STILL_CONDENSE_WHITE_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static Supplier<LiquidBlock> LUMINOUS_WHITE_WATER_BLOCK = BLOCKS.register("luminous_white_water_block", () -> new LiquidBlock(STILL_LUMINOUS_WHITE_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).lightLevel(state -> 15)));
    public static Supplier<LiquidBlock> LUMINOUS_CONDENSE_WHITE_WATER_BLOCK = BLOCKS.register("luminous_condense_white_water_block", () -> new LiquidBlock(STILL_LUMINOUS_CONDENSE_WHITE_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).lightLevel(state -> 15)));

    public static Supplier<LiquidBlock> ORANGE_WATER_BLOCK = BLOCKS.register("orange_water_block", () -> new LiquidBlock(STILL_ORANGE_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static Supplier<LiquidBlock> CONDENSE_ORANGE_WATER_BLOCK = BLOCKS.register("condense_orange_water_block", () -> new LiquidBlock(STILL_CONDENSE_ORANGE_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static Supplier<LiquidBlock> LUMINOUS_ORANGE_WATER_BLOCK = BLOCKS.register("luminous_orange_water_block", () -> new LiquidBlock(STILL_LUMINOUS_ORANGE_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).lightLevel(state -> 15)));
    public static Supplier<LiquidBlock> LUMINOUS_CONDENSE_ORANGE_WATER_BLOCK = BLOCKS.register("luminous_condense_orange_water_block", () -> new LiquidBlock(STILL_LUMINOUS_CONDENSE_ORANGE_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).lightLevel(state -> 15)));

    public static Supplier<LiquidBlock> MAGENTA_WATER_BLOCK = BLOCKS.register("magenta_water_block", () -> new LiquidBlock(STILL_MAGENTA_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static Supplier<LiquidBlock> CONDENSE_MAGENTA_WATER_BLOCK = BLOCKS.register("condense_magenta_water_block", () -> new LiquidBlock(STILL_CONDENSE_MAGENTA_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static Supplier<LiquidBlock> LUMINOUS_MAGENTA_WATER_BLOCK = BLOCKS.register("luminous_magenta_water_block", () -> new LiquidBlock(STILL_LUMINOUS_MAGENTA_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).lightLevel(state -> 15)));
    public static Supplier<LiquidBlock> LUMINOUS_CONDENSE_MAGENTA_WATER_BLOCK = BLOCKS.register("luminous_condense_magenta_water_block", () -> new LiquidBlock(STILL_LUMINOUS_CONDENSE_MAGENTA_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).lightLevel(state -> 15)));

    public static Supplier<LiquidBlock> LIGHT_BLUE_WATER_BLOCK = BLOCKS.register("light_blue_water_block", () -> new LiquidBlock(STILL_LIGHT_BLUE_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static Supplier<LiquidBlock> CONDENSE_LIGHT_BLUE_WATER_BLOCK = BLOCKS.register("condense_light_blue_water_block", () -> new LiquidBlock(STILL_CONDENSE_LIGHT_BLUE_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static Supplier<LiquidBlock> LUMINOUS_LIGHT_BLUE_WATER_BLOCK = BLOCKS.register("luminous_light_blue_water_block", () -> new LiquidBlock(STILL_LUMINOUS_LIGHT_BLUE_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).lightLevel(state -> 15)));
    public static Supplier<LiquidBlock> LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_BLOCK = BLOCKS.register("luminous_condense_light_blue_water_block", () -> new LiquidBlock(STILL_LUMINOUS_CONDENSE_LIGHT_BLUE_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).lightLevel(state -> 15)));

    public static Supplier<LiquidBlock> YELLOW_WATER_BLOCK = BLOCKS.register("yellow_water_block", () -> new LiquidBlock(STILL_YELLOW_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static Supplier<LiquidBlock> CONDENSE_YELLOW_WATER_BLOCK = BLOCKS.register("condense_yellow_water_block", () -> new LiquidBlock(STILL_CONDENSE_YELLOW_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static Supplier<LiquidBlock> LUMINOUS_YELLOW_WATER_BLOCK = BLOCKS.register("luminous_yellow_water_block", () -> new LiquidBlock(STILL_LUMINOUS_YELLOW_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).lightLevel(state -> 15)));
    public static Supplier<LiquidBlock> LUMINOUS_CONDENSE_YELLOW_WATER_BLOCK = BLOCKS.register("luminous_condense_yellow_water_block", () -> new LiquidBlock(STILL_LUMINOUS_CONDENSE_YELLOW_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).lightLevel(state -> 15)));

    public static Supplier<LiquidBlock> LIME_WATER_BLOCK = BLOCKS.register("lime_water_block", () -> new LiquidBlock(STILL_LIME_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static Supplier<LiquidBlock> CONDENSE_LIME_WATER_BLOCK = BLOCKS.register("condense_lime_water_block", () -> new LiquidBlock(STILL_CONDENSE_LIME_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static Supplier<LiquidBlock> LUMINOUS_LIME_WATER_BLOCK = BLOCKS.register("luminous_lime_water_block", () -> new LiquidBlock(STILL_LUMINOUS_LIME_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).lightLevel(state -> 15)));
    public static Supplier<LiquidBlock> LUMINOUS_CONDENSE_LIME_WATER_BLOCK = BLOCKS.register("luminous_condense_lime_water_block", () -> new LiquidBlock(STILL_LUMINOUS_CONDENSE_LIME_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).lightLevel(state -> 15)));

    public static Supplier<LiquidBlock> PINK_WATER_BLOCK = BLOCKS.register("pink_water_block", () -> new LiquidBlock(STILL_PINK_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static Supplier<LiquidBlock> CONDENSE_PINK_WATER_BLOCK = BLOCKS.register("condense_pink_water_block", () -> new LiquidBlock(STILL_CONDENSE_PINK_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static Supplier<LiquidBlock> LUMINOUS_PINK_WATER_BLOCK = BLOCKS.register("luminous_pink_water_block", () -> new LiquidBlock(STILL_LUMINOUS_PINK_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).lightLevel(state -> 15)));
    public static Supplier<LiquidBlock> LUMINOUS_CONDENSE_PINK_WATER_BLOCK = BLOCKS.register("luminous_condense_pink_water_block", () -> new LiquidBlock(STILL_LUMINOUS_CONDENSE_PINK_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).lightLevel(state -> 15)));

    public static Supplier<LiquidBlock> GRAY_WATER_BLOCK = BLOCKS.register("gray_water_block", () -> new LiquidBlock(STILL_GRAY_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static Supplier<LiquidBlock> CONDENSE_GRAY_WATER_BLOCK = BLOCKS.register("condense_gray_water_block", () -> new LiquidBlock(STILL_CONDENSE_GRAY_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static Supplier<LiquidBlock> LUMINOUS_GRAY_WATER_BLOCK = BLOCKS.register("luminous_gray_water_block", () -> new LiquidBlock(STILL_LUMINOUS_GRAY_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).lightLevel(state -> 15)));
    public static Supplier<LiquidBlock> LUMINOUS_CONDENSE_GRAY_WATER_BLOCK = BLOCKS.register("luminous_condense_gray_water_block", () -> new LiquidBlock(STILL_LUMINOUS_CONDENSE_GRAY_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).lightLevel(state -> 15)));

    public static Supplier<LiquidBlock> LIGHT_GRAY_WATER_BLOCK = BLOCKS.register("light_gray_water_block", () -> new LiquidBlock(STILL_LIGHT_GRAY_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static Supplier<LiquidBlock> CONDENSE_LIGHT_GRAY_WATER_BLOCK = BLOCKS.register("condense_light_gray_water_block", () -> new LiquidBlock(STILL_CONDENSE_LIGHT_GRAY_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static Supplier<LiquidBlock> LUMINOUS_LIGHT_GRAY_WATER_BLOCK = BLOCKS.register("luminous_light_gray_water_block", () -> new LiquidBlock(STILL_LUMINOUS_LIGHT_GRAY_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).lightLevel(state -> 15)));
    public static Supplier<LiquidBlock> LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_BLOCK = BLOCKS.register("luminous_condense_light_gray_water_block", () -> new LiquidBlock(STILL_LUMINOUS_CONDENSE_LIGHT_GRAY_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).lightLevel(state -> 15)));

    public static Supplier<LiquidBlock> CYAN_WATER_BLOCK = BLOCKS.register("cyan_water_block", () -> new LiquidBlock(STILL_CYAN_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static Supplier<LiquidBlock> CONDENSE_CYAN_WATER_BLOCK = BLOCKS.register("condense_cyan_water_block", () -> new LiquidBlock(STILL_CONDENSE_CYAN_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static Supplier<LiquidBlock> LUMINOUS_CYAN_WATER_BLOCK = BLOCKS.register("luminous_cyan_water_block", () -> new LiquidBlock(STILL_LUMINOUS_CYAN_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).lightLevel(state -> 15)));
    public static Supplier<LiquidBlock> LUMINOUS_CONDENSE_CYAN_WATER_BLOCK = BLOCKS.register("luminous_condense_cyan_water_block", () -> new LiquidBlock(STILL_LUMINOUS_CONDENSE_CYAN_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).lightLevel(state -> 15)));

    public static Supplier<LiquidBlock> BLUE_WATER_BLOCK = BLOCKS.register("blue", () -> new LiquidBlock(STILL_BLUE_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static Supplier<LiquidBlock> CONDENSE_BLUE_WATER_BLOCK = BLOCKS.register("condense_blue_water_block", () -> new LiquidBlock(STILL_CONDENSE_BLUE_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static Supplier<LiquidBlock> LUMINOUS_BLUE_WATER_BLOCK = BLOCKS.register("luminous_blue_water_block", () -> new LiquidBlock(STILL_LUMINOUS_BLUE_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).lightLevel(state -> 15)));
    public static Supplier<LiquidBlock> LUMINOUS_CONDENSE_BLUE_WATER_BLOCK = BLOCKS.register("luminous_condense_blue_water_block", () -> new LiquidBlock(STILL_LUMINOUS_CONDENSE_BLUE_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).lightLevel(state -> 15)));

    public static Supplier<LiquidBlock> PURPLE_WATER_BLOCK = BLOCKS.register("purple_water_block", () -> new LiquidBlock(STILL_PURPLE_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static Supplier<LiquidBlock> CONDENSE_PURPLE_WATER_BLOCK = BLOCKS.register("condense_purple_water_block", () -> new LiquidBlock(STILL_CONDENSE_PURPLE_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static Supplier<LiquidBlock> LUMINOUS_PURPLE_WATER_BLOCK = BLOCKS.register("luminous_purple_water_block", () -> new LiquidBlock(STILL_LUMINOUS_PURPLE_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).lightLevel(state -> 15)));
    public static Supplier<LiquidBlock> LUMINOUS_CONDENSE_PURPLE_WATER_BLOCK = BLOCKS.register("luminous_condense_purple_water_block", () -> new LiquidBlock(STILL_LUMINOUS_CONDENSE_PURPLE_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).lightLevel(state -> 15)));

    public static Supplier<LiquidBlock> BLACK_WATER_BLOCK = BLOCKS.register("black_water_block", () -> new LiquidBlock(STILL_BLACK_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static Supplier<LiquidBlock> CONDENSE_BLACK_WATER_BLOCK = BLOCKS.register("condense_black_water_block", () -> new LiquidBlock(STILL_CONDENSE_BLACK_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static Supplier<LiquidBlock> LUMINOUS_BLACK_WATER_BLOCK = BLOCKS.register("luminous_black_water_block", () -> new LiquidBlock(STILL_LUMINOUS_BLACK_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).lightLevel(state -> 15)));
    public static Supplier<LiquidBlock> LUMINOUS_CONDENSE_BLACK_WATER_BLOCK = BLOCKS.register("luminous_condense_black_water_block", () -> new LiquidBlock(STILL_LUMINOUS_CONDENSE_BLACK_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).lightLevel(state -> 15)));

    public static Supplier<LiquidBlock> GREEN_WATER_BLOCK = BLOCKS.register("green_water_block", () -> new LiquidBlock(STILL_GREEN_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static Supplier<LiquidBlock> CONDENSE_GREEN_WATER_BLOCK = BLOCKS.register("condense_green_water_block", () -> new LiquidBlock(STILL_CONDENSE_GREEN_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static Supplier<LiquidBlock> LUMINOUS_GREEN_WATER_BLOCK = BLOCKS.register("luminous_green_water_block", () -> new LiquidBlock(STILL_LUMINOUS_GREEN_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).lightLevel(state -> 15)));
    public static Supplier<LiquidBlock> LUMINOUS_CONDENSE_GREEN_WATER_BLOCK = BLOCKS.register("luminous_condense_green_water_block", () -> new LiquidBlock(STILL_LUMINOUS_CONDENSE_GREEN_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).lightLevel(state -> 15)));

    public static Supplier<LiquidBlock> RED_WATER_BLOCK = BLOCKS.register("red_water_block", () -> new LiquidBlock(STILL_RED_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static Supplier<LiquidBlock> CONDENSE_RED_WATER_BLOCK = BLOCKS.register("condense_red_water_block", () -> new LiquidBlock(STILL_CONDENSE_RED_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static Supplier<LiquidBlock> LUMINOUS_RED_WATER_BLOCK = BLOCKS.register("luminous_red_water_block", () -> new LiquidBlock(STILL_LUMINOUS_RED_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).lightLevel(state -> 15)));
    public static Supplier<LiquidBlock> LUMINOUS_CONDENSE_RED_WATER_BLOCK = BLOCKS.register("luminous_condense_red_water_block", () -> new LiquidBlock(STILL_LUMINOUS_CONDENSE_RED_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).lightLevel(state -> 15)));

    public static Supplier<LiquidBlock> BROWN_WATER_BLOCK = BLOCKS.register("brown_water_block", () -> new LiquidBlock(STILL_BROWN_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static Supplier<LiquidBlock> CONDENSE_BROWN_WATER_BLOCK = BLOCKS.register("condense_brown_water_block", () -> new LiquidBlock(STILL_CONDENSE_BROWN_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
    public static Supplier<LiquidBlock> LUMINOUS_BROWN_WATER_BLOCK = BLOCKS.register("luminous_brown_water_block", () -> new LiquidBlock(STILL_LUMINOUS_BROWN_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).lightLevel(state -> 15)));
    public static Supplier<LiquidBlock> LUMINOUS_CONDENSE_BROWN_WATER_BLOCK = BLOCKS.register("luminous_condense_brown_water_block", () -> new LiquidBlock(STILL_LUMINOUS_CONDENSE_BROWN_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).lightLevel(state -> 15)));

}
