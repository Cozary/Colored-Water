package com.cozary.colored_water.cauldrons.registry;


import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.cauldrons.block.baseFluid.*;
import com.cozary.colored_water.cauldrons.block.comboFluid.*;
import com.cozary.colored_water.cauldrons.block.condenseFluid.*;
import com.cozary.colored_water.cauldrons.block.luminousFluid.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ColoredWater.MOD_ID);

    public static final RegistryObject<Block> MAGENTA_WATER_CAULDRON = BLOCKS.register("magenta_water_cauldron", MagentaCauldronBlock::new);
    public static final RegistryObject<Block> PURPLE_WATER_CAULDRON = BLOCKS.register("purple_water_cauldron", PurpleCauldronBlock::new);
    public static final RegistryObject<Block> GREEN_WATER_CAULDRON = BLOCKS.register("green_water_cauldron", GreenCauldronBlock::new);
    public static final RegistryObject<Block> YELLOW_WATER_CAULDRON = BLOCKS.register("yellow_water_cauldron", YellowCauldronBlock::new);
    public static final RegistryObject<Block> LIME_WATER_CAULDRON = BLOCKS.register("lime_water_cauldron", LimeCauldronBlock::new);
    public static final RegistryObject<Block> PINK_WATER_CAULDRON = BLOCKS.register("pink_water_cauldron", PinkCauldronBlock::new);
    public static final RegistryObject<Block> RED_WATER_CAULDRON = BLOCKS.register("red_water_cauldron", RedCauldronBlock::new);
    public static final RegistryObject<Block> BLACK_WATER_CAULDRON = BLOCKS.register("black_water_cauldron", BlackCauldronBlock::new);
    public static final RegistryObject<Block> BROWN_WATER_CAULDRON = BLOCKS.register("brown_water_cauldron", BrownCauldronBlock::new);
    public static final RegistryObject<Block> BLUE_WATER_CAULDRON = BLOCKS.register("blue_water_cauldron", BlueCauldronBlock::new);
    public static final RegistryObject<Block> CYAN_WATER_CAULDRON = BLOCKS.register("cyan_water_cauldron", CyanCauldronBlock::new);
    public static final RegistryObject<Block> LIGHT_GRAY_WATER_CAULDRON = BLOCKS.register("light_gray_water_cauldron", LightGrayCauldronBlock::new);
    public static final RegistryObject<Block> GRAY_WATER_CAULDRON = BLOCKS.register("gray_water_cauldron", GrayCauldronBlock::new);
    public static final RegistryObject<Block> LIGHT_BLUE_WATER_CAULDRON = BLOCKS.register("light_blue_water_cauldron", LightBlueCauldronBlock::new);
    public static final RegistryObject<Block> ORANGE_WATER_CAULDRON = BLOCKS.register("orange_water_cauldron", OrangeCauldronBlock::new);
    public static final RegistryObject<Block> WHITE_WATER_CAULDRON = BLOCKS.register("white_water_cauldron", WhiteCauldronBlock::new);

    public static final RegistryObject<Block> CONDENSE_MAGENTA_WATER_CAULDRON = BLOCKS.register("condense_magenta_water_cauldron", CondenseMagentaCauldronBlock::new);
    public static final RegistryObject<Block> CONDENSE_PURPLE_WATER_CAULDRON = BLOCKS.register("condense_purple_water_cauldron", CondensePurpleCauldronBlock::new);
    public static final RegistryObject<Block> CONDENSE_GREEN_WATER_CAULDRON = BLOCKS.register("condense_green_water_cauldron", CondenseGreenCauldronBlock::new);
    public static final RegistryObject<Block> CONDENSE_YELLOW_WATER_CAULDRON = BLOCKS.register("condense_yellow_water_cauldron", CondenseYellowCauldronBlock::new);
    public static final RegistryObject<Block> CONDENSE_LIME_WATER_CAULDRON = BLOCKS.register("condense_lime_water_cauldron", CondenseLimeCauldronBlock::new);
    public static final RegistryObject<Block> CONDENSE_PINK_WATER_CAULDRON = BLOCKS.register("condense_pink_water_cauldron", CondensePinkCauldronBlock::new);
    public static final RegistryObject<Block> CONDENSE_RED_WATER_CAULDRON = BLOCKS.register("condense_red_water_cauldron", CondenseRedCauldronBlock::new);
    public static final RegistryObject<Block> CONDENSE_BLACK_WATER_CAULDRON = BLOCKS.register("condense_black_water_cauldron", CondenseBlackCauldronBlock::new);
    public static final RegistryObject<Block> CONDENSE_BROWN_WATER_CAULDRON = BLOCKS.register("condense_brown_water_cauldron", CondenseBrownCauldronBlock::new);
    public static final RegistryObject<Block> CONDENSE_BLUE_WATER_CAULDRON = BLOCKS.register("condense_blue_water_cauldron", CondenseBlueCauldronBlock::new);
    public static final RegistryObject<Block> CONDENSE_CYAN_WATER_CAULDRON = BLOCKS.register("condense_cyan_water_cauldron", CondenseCyanCauldronBlock::new);
    public static final RegistryObject<Block> CONDENSE_LIGHT_GRAY_WATER_CAULDRON = BLOCKS.register("condense_light_gray_water_cauldron", CondenseLightGrayCauldronBlock::new);
    public static final RegistryObject<Block> CONDENSE_GRAY_WATER_CAULDRON = BLOCKS.register("condense_gray_water_cauldron", CondenseGrayCauldronBlock::new);
    public static final RegistryObject<Block> CONDENSE_LIGHT_BLUE_WATER_CAULDRON = BLOCKS.register("condense_light_blue_water_cauldron", CondenseLightBlueCauldronBlock::new);
    public static final RegistryObject<Block> CONDENSE_ORANGE_WATER_CAULDRON = BLOCKS.register("condense_orange_water_cauldron", CondenseOrangeCauldronBlock::new);
    public static final RegistryObject<Block> CONDENSE_WHITE_WATER_CAULDRON = BLOCKS.register("condense_white_water_cauldron", CondenseWhiteCauldronBlock::new);

    public static final RegistryObject<Block> LUMINOUS_MAGENTA_WATER_CAULDRON = BLOCKS.register("luminous_magenta_water_cauldron", LuminousMagentaCauldronBlock::new);
    public static final RegistryObject<Block> LUMINOUS_PURPLE_WATER_CAULDRON = BLOCKS.register("luminous_purple_water_cauldron", LuminousPurpleCauldronBlock::new);
    public static final RegistryObject<Block> LUMINOUS_GREEN_WATER_CAULDRON = BLOCKS.register("luminous_green_water_cauldron", LuminousGreenCauldronBlock::new);
    public static final RegistryObject<Block> LUMINOUS_YELLOW_WATER_CAULDRON = BLOCKS.register("luminous_yellow_water_cauldron", LuminousYellowCauldronBlock::new);
    public static final RegistryObject<Block> LUMINOUS_LIME_WATER_CAULDRON = BLOCKS.register("luminous_lime_water_cauldron", LuminousLimeCauldronBlock::new);
    public static final RegistryObject<Block> LUMINOUS_PINK_WATER_CAULDRON = BLOCKS.register("luminous_pink_water_cauldron", LuminousPinkCauldronBlock::new);
    public static final RegistryObject<Block> LUMINOUS_RED_WATER_CAULDRON = BLOCKS.register("luminous_red_water_cauldron", LuminousRedCauldronBlock::new);
    public static final RegistryObject<Block> LUMINOUS_BLACK_WATER_CAULDRON = BLOCKS.register("luminous_black_water_cauldron", LuminousBlackCauldronBlock::new);
    public static final RegistryObject<Block> LUMINOUS_BROWN_WATER_CAULDRON = BLOCKS.register("luminous_brown_water_cauldron", LuminousBrownCauldronBlock::new);
    public static final RegistryObject<Block> LUMINOUS_BLUE_WATER_CAULDRON = BLOCKS.register("luminous_blue_water_cauldron", LuminousBlueCauldronBlock::new);
    public static final RegistryObject<Block> LUMINOUS_CYAN_WATER_CAULDRON = BLOCKS.register("luminous_cyan_water_cauldron", LuminousCyanCauldronBlock::new);
    public static final RegistryObject<Block> LUMINOUS_LIGHT_GRAY_WATER_CAULDRON = BLOCKS.register("luminous_light_gray_water_cauldron", LuminousLightGrayCauldronBlock::new);
    public static final RegistryObject<Block> LUMINOUS_GRAY_WATER_CAULDRON = BLOCKS.register("luminous_gray_water_cauldron", LuminousGrayCauldronBlock::new);
    public static final RegistryObject<Block> LUMINOUS_LIGHT_BLUE_WATER_CAULDRON = BLOCKS.register("luminous_light_blue_water_cauldron", LuminousLightBlueCauldronBlock::new);
    public static final RegistryObject<Block> LUMINOUS_ORANGE_WATER_CAULDRON = BLOCKS.register("luminous_orange_water_cauldron", LuminousOrangeCauldronBlock::new);
    public static final RegistryObject<Block> LUMINOUS_WHITE_WATER_CAULDRON = BLOCKS.register("luminous_white_water_cauldron", LuminousWhiteCauldronBlock::new);

    public static final RegistryObject<Block> LUMINOUS_CONDENSE_MAGENTA_WATER_CAULDRON = BLOCKS.register("luminous_condense_magenta_water_cauldron", LuminousCondenseMagentaCauldronBlock::new);
    public static final RegistryObject<Block> LUMINOUS_CONDENSE_PURPLE_WATER_CAULDRON = BLOCKS.register("luminous_condense_purple_water_cauldron", LuminousCondensePurpleCauldronBlock::new);
    public static final RegistryObject<Block> LUMINOUS_CONDENSE_GREEN_WATER_CAULDRON = BLOCKS.register("luminous_condense_green_water_cauldron", LuminousCondenseGreenCauldronBlock::new);
    public static final RegistryObject<Block> LUMINOUS_CONDENSE_YELLOW_WATER_CAULDRON = BLOCKS.register("luminous_condense_yellow_water_cauldron", LuminousCondenseYellowCauldronBlock::new);
    public static final RegistryObject<Block> LUMINOUS_CONDENSE_LIME_WATER_CAULDRON = BLOCKS.register("luminous_condense_lime_water_cauldron", LuminousCondenseLimeCauldronBlock::new);
    public static final RegistryObject<Block> LUMINOUS_CONDENSE_PINK_WATER_CAULDRON = BLOCKS.register("luminous_condense_pink_water_cauldron", LuminousCondensePinkCauldronBlock::new);
    public static final RegistryObject<Block> LUMINOUS_CONDENSE_RED_WATER_CAULDRON = BLOCKS.register("luminous_condense_red_water_cauldron", LuminousCondenseRedCauldronBlock::new);
    public static final RegistryObject<Block> LUMINOUS_CONDENSE_BLACK_WATER_CAULDRON = BLOCKS.register("luminous_condense_black_water_cauldron", LuminousCondenseBlackCauldronBlock::new);
    public static final RegistryObject<Block> LUMINOUS_CONDENSE_BROWN_WATER_CAULDRON = BLOCKS.register("luminous_condense_brown_water_cauldron", LuminousCondenseBrownCauldronBlock::new);
    public static final RegistryObject<Block> LUMINOUS_CONDENSE_BLUE_WATER_CAULDRON = BLOCKS.register("luminous_condense_blue_water_cauldron", LuminousCondenseBlueCauldronBlock::new);
    public static final RegistryObject<Block> LUMINOUS_CONDENSE_CYAN_WATER_CAULDRON = BLOCKS.register("luminous_condense_cyan_water_cauldron", LuminousCondenseCyanCauldronBlock::new);
    public static final RegistryObject<Block> LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_CAULDRON = BLOCKS.register("luminous_condense_light_gray_water_cauldron", LuminousCondenseLightGrayCauldronBlock::new);
    public static final RegistryObject<Block> LUMINOUS_CONDENSE_GRAY_WATER_CAULDRON = BLOCKS.register("luminous_condense_gray_water_cauldron", LuminousCondenseGrayCauldronBlock::new);
    public static final RegistryObject<Block> LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_CAULDRON = BLOCKS.register("luminous_condense_light_blue_water_cauldron", LuminousCondenseLightBlueCauldronBlock::new);
    public static final RegistryObject<Block> LUMINOUS_CONDENSE_ORANGE_WATER_CAULDRON = BLOCKS.register("luminous_condense_orange_water_cauldron", LuminousCondenseOrangeCauldronBlock::new);
    public static final RegistryObject<Block> LUMINOUS_CONDENSE_WHITE_WATER_CAULDRON = BLOCKS.register("luminous_condense_white_water_cauldron", LuminousCondenseWhiteCauldronBlock::new);
}