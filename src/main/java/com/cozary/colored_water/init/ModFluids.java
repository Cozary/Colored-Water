package com.cozary.colored_water.init;

import com.cozary.colored_water.fluids.color.*;
import com.cozary.colored_water.fluids.condense.*;
import com.cozary.colored_water.fluids.luminous.*;
import com.cozary.colored_water.fluids.luminousCondense.*;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModFluids {

    public static final FlowableFluid STILL_WHITE_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "white_water"), new WhiteWater.Still());
    public static final FlowableFluid FLOWING_WHITE_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_white_water"), new WhiteWater.Flowing());

    public static final FlowableFluid STILL_CONDENSE_WHITE_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "condense_white_water"), new CondenseWhiteWater.Still());
    public static final FlowableFluid FLOWING_CONDENSE_WHITE_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_condense_white_water"), new CondenseWhiteWater.Flowing());

    public static final FlowableFluid STILL_LUMINOUS_WHITE_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "luminous_white_water"), new LuminousWhiteWater.Still());
    public static final FlowableFluid FLOWING_LUMINOUS_WHITE_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_luminous_white_water"), new LuminousWhiteWater.Flowing());

    public static final FlowableFluid STILL_LUMINOUS_CONDENSE_WHITE_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "luminous_condense_white_water"), new LuminousCondenseWhiteWater.Still());
    public static final FlowableFluid FLOWING_LUMINOUS_CONDENSE_WHITE_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_luminous_condense_white_water"), new LuminousCondenseWhiteWater.Flowing());


    public static final FlowableFluid STILL_ORANGE_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "orange_water"), new OrangeWater.Still());
    public static final FlowableFluid FLOWING_ORANGE_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_orange_water"), new OrangeWater.Flowing());

    public static final FlowableFluid STILL_CONDENSE_ORANGE_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "condense_orange_water"), new CondenseOrangeWater.Still());
    public static final FlowableFluid FLOWING_CONDENSE_ORANGE_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_condense_orange_water"), new CondenseOrangeWater.Flowing());

    public static final FlowableFluid STILL_LUMINOUS_ORANGE_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "luminous_orange_water"), new LuminousOrangeWater.Still());
    public static final FlowableFluid FLOWING_LUMINOUS_ORANGE_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_luminous_orange_water"), new LuminousOrangeWater.Flowing());

    public static final FlowableFluid STILL_LUMINOUS_CONDENSE_ORANGE_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "luminous_condense_orange_water"), new LuminousCondenseOrangeWater.Still());
    public static final FlowableFluid FLOWING_LUMINOUS_CONDENSE_ORANGE_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_luminous_condense_orange_water"), new LuminousCondenseOrangeWater.Flowing());


    public static final FlowableFluid STILL_MAGENTA_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "magenta_water"), new MagentaWater.Still());
    public static final FlowableFluid FLOWING_MAGENTA_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_magenta_water"), new MagentaWater.Flowing());

    public static final FlowableFluid STILL_CONDENSE_MAGENTA_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "condense_magenta_water"), new CondenseMagentaWater.Still());
    public static final FlowableFluid FLOWING_CONDENSE_MAGENTA_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_condense_magenta_water"), new CondenseMagentaWater.Flowing());

    public static final FlowableFluid STILL_LUMINOUS_MAGENTA_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "luminous_magenta_water"), new LuminousMagentaWater.Still());
    public static final FlowableFluid FLOWING_LUMINOUS_MAGENTA_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_luminous_magenta_water"), new LuminousMagentaWater.Flowing());

    public static final FlowableFluid STILL_LUMINOUS_CONDENSE_MAGENTA_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "luminous_condense_magenta_water"), new LuminousCondenseMagentaWater.Still());
    public static final FlowableFluid FLOWING_LUMINOUS_CONDENSE_MAGENTA_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_luminous_condense_magenta_water"), new LuminousCondenseMagentaWater.Flowing());


    public static final FlowableFluid STILL_LIGHT_BLUE_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "light_blue_water"), new LightBlueWater.Still());
    public static final FlowableFluid FLOWING_LIGHT_BLUE_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_light_blue_water"), new LightBlueWater.Flowing());

    public static final FlowableFluid STILL_CONDENSE_LIGHT_BLUE_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "condense_light_blue_water"), new CondenseLightBlueWater.Still());
    public static final FlowableFluid FLOWING_CONDENSE_LIGHT_BLUE_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_condense_light_blue_water"), new CondenseLightBlueWater.Flowing());

    public static final FlowableFluid STILL_LUMINOUS_LIGHT_BLUE_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "luminous_light_blue_water"), new LuminousLightBlueWater.Still());
    public static final FlowableFluid FLOWING_LUMINOUS_LIGHT_BLUE_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_luminous_light_blue_water"), new LuminousLightBlueWater.Flowing());

    public static final FlowableFluid STILL_LUMINOUS_CONDENSE_LIGHT_BLUE_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "luminous_condense_light_blue_water"), new LuminousCondenseLightBlueWater.Still());
    public static final FlowableFluid FLOWING_LUMINOUS_CONDENSE_LIGHT_BLUE_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_luminous_condense_light_blue_water"), new LuminousCondenseLightBlueWater.Flowing());


    public static final FlowableFluid STILL_YELLOW_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "yellow_water"), new YellowWater.Still());
    public static final FlowableFluid FLOWING_YELLOW_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_yellow_water"), new YellowWater.Flowing());

    public static final FlowableFluid STILL_CONDENSE_YELLOW_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "condense_yellow_water"), new CondenseYellowWater.Still());
    public static final FlowableFluid FLOWING_CONDENSE_YELLOW_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_condense_yellow_water"), new CondenseYellowWater.Flowing());

    public static final FlowableFluid STILL_LUMINOUS_YELLOW_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "luminous_yellow_water"), new LuminousYellowWater.Still());
    public static final FlowableFluid FLOWING_LUMINOUS_YELLOW_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_luminous_yellow_water"), new LuminousYellowWater.Flowing());

    public static final FlowableFluid STILL_LUMINOUS_CONDENSE_YELLOW_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "luminous_condense_yellow_water"), new LuminousCondenseYellowWater.Still());
    public static final FlowableFluid FLOWING_LUMINOUS_CONDENSE_YELLOW_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_luminous_condense_yellow_water"), new LuminousCondenseYellowWater.Flowing());


    public static final FlowableFluid STILL_LIME_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "lime_water"), new LimeWater.Still());
    public static final FlowableFluid FLOWING_LIME_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_lime_water"), new LimeWater.Flowing());

    public static final FlowableFluid STILL_CONDENSE_LIME_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "condense_lime_water"), new CondenseLimeWater.Still());
    public static final FlowableFluid FLOWING_CONDENSE_LIME_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_condense_lime_water"), new CondenseLimeWater.Flowing());

    public static final FlowableFluid STILL_LUMINOUS_LIME_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "luminous_lime_water"), new LuminousLimeWater.Still());
    public static final FlowableFluid FLOWING_LUMINOUS_LIME_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_luminous_lime_water"), new LuminousLimeWater.Flowing());

    public static final FlowableFluid STILL_LUMINOUS_CONDENSE_LIME_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "luminous_condense_lime_water"), new LuminousCondenseLimeWater.Still());
    public static final FlowableFluid FLOWING_LUMINOUS_CONDENSE_LIME_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_luminous_condense_lime_water"), new LuminousCondenseLimeWater.Flowing());


    public static final FlowableFluid STILL_PINK_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "pink_water"), new PinkWater.Still());
    public static final FlowableFluid FLOWING_PINK_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_pink_water"), new PinkWater.Flowing());

    public static final FlowableFluid STILL_CONDENSE_PINK_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "condense_pink_water"), new CondensePinkWater.Still());
    public static final FlowableFluid FLOWING_CONDENSE_PINK_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_condense_pink_water"), new CondensePinkWater.Flowing());

    public static final FlowableFluid STILL_LUMINOUS_PINK_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "luminous_pink_water"), new LuminousPinkWater.Still());
    public static final FlowableFluid FLOWING_LUMINOUS_PINK_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_luminous_pink_water"), new LuminousPinkWater.Flowing());

    public static final FlowableFluid STILL_LUMINOUS_CONDENSE_PINK_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "luminous_condense_pink_water"), new LuminousCondensePinkWater.Still());
    public static final FlowableFluid FLOWING_LUMINOUS_CONDENSE_PINK_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_luminous_condense_pink_water"), new LuminousCondensePinkWater.Flowing());


    public static final FlowableFluid STILL_GRAY_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "gray_water"), new GrayWater.Still());
    public static final FlowableFluid FLOWING_GRAY_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_gray_water"), new GrayWater.Flowing());

    public static final FlowableFluid STILL_CONDENSE_GRAY_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "condense_gray_water"), new CondenseGrayWater.Still());
    public static final FlowableFluid FLOWING_CONDENSE_GRAY_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_condense_gray_water"), new CondenseGrayWater.Flowing());

    public static final FlowableFluid STILL_LUMINOUS_GRAY_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "luminous_gray_water"), new LuminousGrayWater.Still());
    public static final FlowableFluid FLOWING_LUMINOUS_GRAY_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_luminous_gray_water"), new LuminousGrayWater.Flowing());

    public static final FlowableFluid STILL_LUMINOUS_CONDENSE_GRAY_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "luminous_condense_gray_water"), new LuminousCondenseGrayWater.Still());
    public static final FlowableFluid FLOWING_LUMINOUS_CONDENSE_GRAY_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_luminous_condense_gray_water"), new LuminousCondenseGrayWater.Flowing());


    public static final FlowableFluid STILL_LIGHT_GRAY_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "light_gray_water"), new LightGrayWater.Still());
    public static final FlowableFluid FLOWING_LIGHT_GRAY_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_light_gray_water"), new LightGrayWater.Flowing());

    public static final FlowableFluid STILL_CONDENSE_LIGHT_GRAY_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "condense_light_gray_water"), new CondenseLightGrayWater.Still());
    public static final FlowableFluid FLOWING_CONDENSE_LIGHT_GRAY_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_condense_light_gray_water"), new CondenseLightGrayWater.Flowing());

    public static final FlowableFluid STILL_LUMINOUS_LIGHT_GRAY_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "luminous_light_gray_water"), new LuminousLightGrayWater.Still());
    public static final FlowableFluid FLOWING_LUMINOUS_LIGHT_GRAY_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_luminous_light_gray_water"), new LuminousLightGrayWater.Flowing());

    public static final FlowableFluid STILL_LUMINOUS_CONDENSE_LIGHT_GRAY_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "luminous_condense_light_gray_water"), new LuminousCondenseLightGrayWater.Still());
    public static final FlowableFluid FLOWING_LUMINOUS_CONDENSE_LIGHT_GRAY_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_luminous_condense_light_gray_water"), new LuminousCondenseLightGrayWater.Flowing());


    public static final FlowableFluid STILL_CYAN_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "cyan_water"), new CyanWater.Still());
    public static final FlowableFluid FLOWING_CYAN_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_cyan_water"), new CyanWater.Flowing());

    public static final FlowableFluid STILL_CONDENSE_CYAN_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "condense_cyan_water"), new CondenseCyanWater.Still());
    public static final FlowableFluid FLOWING_CONDENSE_CYAN_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_condense_cyan_water"), new CondenseCyanWater.Flowing());

    public static final FlowableFluid STILL_LUMINOUS_CYAN_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "luminous_cyan_water"), new LuminousCondenseCyanWater.Still());
    public static final FlowableFluid FLOWING_LUMINOUS_CYAN_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_luminous_cyan_water"), new LuminousCondenseCyanWater.Flowing());

    public static final FlowableFluid STILL_LUMINOUS_CONDENSE_CYAN_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "luminous_condense_cyan_water"), new LuminousCondenseCyanWater.Still());
    public static final FlowableFluid FLOWING_LUMINOUS_CONDENSE_CYAN_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_luminous_condense_cyan_water"), new LuminousCondenseCyanWater.Flowing());


    public static final FlowableFluid STILL_PURPLE_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "purple_water"), new PurpleWater.Still());
    public static final FlowableFluid FLOWING_PURPLE_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_purple_water"), new PurpleWater.Flowing());

    public static final FlowableFluid STILL_CONDENSE_PURPLE_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "condense_purple_water"), new CondensePurpleWater.Still());
    public static final FlowableFluid FLOWING_CONDENSE_PURPLE_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_condense_purple_water"), new CondensePurpleWater.Flowing());

    public static final FlowableFluid STILL_LUMINOUS_PURPLE_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "luminous_purple_water"), new LuminousPurpleWater.Still());
    public static final FlowableFluid FLOWING_LUMINOUS_PURPLE_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_luminous_purple_water"), new LuminousPurpleWater.Flowing());

    public static final FlowableFluid STILL_LUMINOUS_CONDENSE_PURPLE_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "luminous_condense_purple_water"), new LuminousCondensePurpleWater.Still());
    public static final FlowableFluid FLOWING_LUMINOUS_CONDENSE_PURPLE_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_luminous_condense_purple_water"), new LuminousCondensePurpleWater.Flowing());


    public static final FlowableFluid STILL_BLUE_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "blue_water"), new BlueWater.Still());
    public static final FlowableFluid FLOWING_BLUE_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_blue_water"), new BlueWater.Flowing());

    public static final FlowableFluid STILL_CONDENSE_BLUE_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "condense_blue_water"), new CondenseBlueWater.Still());
    public static final FlowableFluid FLOWING_CONDENSE_BLUE_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_condense_blue_water"), new CondenseBlueWater.Flowing());

    public static final FlowableFluid STILL_LUMINOUS_BLUE_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "luminous_blue_water"), new LuminousBlueWater.Still());
    public static final FlowableFluid FLOWING_LUMINOUS_BLUE_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_luminous_blue_water"), new LuminousBlueWater.Flowing());

    public static final FlowableFluid STILL_LUMINOUS_CONDENSE_BLUE_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "luminous_condense_blue_water"), new LuminousCondenseBlueWater.Still());
    public static final FlowableFluid FLOWING_LUMINOUS_CONDENSE_BLUE_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_luminous_condense_blue_water"), new LuminousCondenseBlueWater.Flowing());


    public static final FlowableFluid STILL_BROWN_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "brown_water"), new BrownWater.Still());
    public static final FlowableFluid FLOWING_BROWN_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_brown_water"), new BrownWater.Flowing());

    public static final FlowableFluid STILL_CONDENSE_BROWN_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "condense_brown_water"), new CondenseBrownWater.Still());
    public static final FlowableFluid FLOWING_CONDENSE_BROWN_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_condense_brown_water"), new CondenseBrownWater.Flowing());

    public static final FlowableFluid STILL_LUMINOUS_BROWN_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "luminous_brown_water"), new LuminousBrownWater.Still());
    public static final FlowableFluid FLOWING_LUMINOUS_BROWN_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_luminous_brown_water"), new LuminousBrownWater.Flowing());

    public static final FlowableFluid STILL_LUMINOUS_CONDENSE_BROWN_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "luminous_condense_brown_water"), new LuminousCondenseBrownWater.Still());
    public static final FlowableFluid FLOWING_LUMINOUS_CONDENSE_BROWN_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_luminous_condense_brown_water"), new LuminousCondenseBrownWater.Flowing());


    public static final FlowableFluid STILL_GREEN_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "green_water"), new GreenWater.Still());
    public static final FlowableFluid FLOWING_GREEN_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_green_water"), new GreenWater.Flowing());

    public static final FlowableFluid STILL_CONDENSE_GREEN_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "condense_green_water"), new CondenseGreenWater.Still());
    public static final FlowableFluid FLOWING_CONDENSE_GREEN_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_condense_green_water"), new CondenseGreenWater.Flowing());

    public static final FlowableFluid STILL_LUMINOUS_GREEN_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "luminous_green_water"), new LuminousGreenWater.Still());
    public static final FlowableFluid FLOWING_LUMINOUS_GREEN_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_luminous_green_water"), new LuminousGreenWater.Flowing());

    public static final FlowableFluid STILL_LUMINOUS_CONDENSE_GREEN_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "luminous_condense_green_water"), new LuminousCondenseGreenWater.Still());
    public static final FlowableFluid FLOWING_LUMINOUS_CONDENSE_GREEN_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_luminous_condense_green_water"), new LuminousCondenseGreenWater.Flowing());


    public static final FlowableFluid STILL_RED_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "red_water"), new RedWater.Still());
    public static final FlowableFluid FLOWING_RED_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_red_water"), new RedWater.Flowing());

    public static final FlowableFluid STILL_CONDENSE_RED_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "condense_red_water"), new CondenseRedWater.Still());
    public static final FlowableFluid FLOWING_CONDENSE_RED_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_condense_red_water"), new CondenseRedWater.Flowing());

    public static final FlowableFluid STILL_LUMINOUS_RED_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "luminous_red_water"), new LuminousRedWater.Still());
    public static final FlowableFluid FLOWING_LUMINOUS_RED_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_luminous_red_water"), new LuminousRedWater.Flowing());

    public static final FlowableFluid STILL_LUMINOUS_CONDENSE_RED_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "luminous_condense_red_water"), new LuminousCondenseRedWater.Still());
    public static final FlowableFluid FLOWING_LUMINOUS_CONDENSE_RED_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_luminous_condense_red_water"), new LuminousCondenseRedWater.Flowing());


    public static final FlowableFluid STILL_BLACK_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "black_water"), new BlackWater.Still());
    public static final FlowableFluid FLOWING_BLACK_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_black_water"), new BlackWater.Flowing());

    public static final FlowableFluid STILL_CONDENSE_BLACK_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "condense_black_water"), new CondenseBlackWater.Still());
    public static final FlowableFluid FLOWING_CONDENSE_BLACK_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_condense_black_water"), new CondenseBlackWater.Flowing());

    public static final FlowableFluid STILL_LUMINOUS_BLACK_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "luminous_black_water"), new LuminousBlackWater.Still());
    public static final FlowableFluid FLOWING_LUMINOUS_BLACK_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_luminous_black_water"), new LuminousBlackWater.Flowing());

    public static final FlowableFluid STILL_LUMINOUS_CONDENSE_BLACK_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "luminous_condense_black_water"), new LuminousCondenseBlackWater.Still());
    public static final FlowableFluid FLOWING_LUMINOUS_CONDENSE_BLACK_WATER = Registry.register(Registries.FLUID, new Identifier("colored_water", "flowing_luminous_condense_black_water"), new LuminousCondenseBlackWater.Flowing());

    public static void loadClass() {
    }


}
