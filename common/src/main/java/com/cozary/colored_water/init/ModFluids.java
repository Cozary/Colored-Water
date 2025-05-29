package com.cozary.colored_water.init;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.fluids.BaseColorWater;
import com.cozary.colored_water.fluids.color.*;
import com.cozary.colored_water.fluids.condense.*;
import com.cozary.colored_water.fluids.luminous.*;
import com.cozary.colored_water.fluids.luminousCondense.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.material.Fluid;

import java.util.function.Supplier;

public class ModFluids {

    public static final RegistrationProvider<Fluid> FLUIDS = RegistrationProvider.get(Registries.FLUID, ColoredWater.MOD_ID);

    public static final Supplier<BaseColorWater> STILL_WHITE_WATER = FLUIDS.register("white_water", WhiteWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_WHITE_WATER = FLUIDS.register("flowing_white_water", WhiteWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_CONDENSE_WHITE_WATER = FLUIDS.register("condense_white_water", CondenseWhiteWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_CONDENSE_WHITE_WATER = FLUIDS.register("flowing_condense_white_water", CondenseWhiteWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_LUMINOUS_WHITE_WATER = FLUIDS.register("luminous_white_water", LuminousWhiteWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_WHITE_WATER = FLUIDS.register("flowing_luminous_white_water", LuminousWhiteWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_LUMINOUS_CONDENSE_WHITE_WATER = FLUIDS.register("luminous_condense_white_water", LuminousCondenseWhiteWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_CONDENSE_WHITE_WATER = FLUIDS.register("flowing_luminous_condense_white_water", LuminousCondenseWhiteWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_ORANGE_WATER = FLUIDS.register("orange_water", OrangeWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_ORANGE_WATER = FLUIDS.register("flowing_orange_water", OrangeWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_CONDENSE_ORANGE_WATER = FLUIDS.register("condense_orange_water", CondenseOrangeWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_CONDENSE_ORANGE_WATER = FLUIDS.register("flowing_condense_orange_water", CondenseOrangeWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_LUMINOUS_ORANGE_WATER = FLUIDS.register("luminous_orange_water", LuminousOrangeWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_ORANGE_WATER = FLUIDS.register("flowing_luminous_orange_water", LuminousOrangeWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_LUMINOUS_CONDENSE_ORANGE_WATER = FLUIDS.register("luminous_condense_orange_water", LuminousCondenseOrangeWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_CONDENSE_ORANGE_WATER = FLUIDS.register("flowing_luminous_condense_orange_water", LuminousCondenseOrangeWater.Flowing::new);


    public static final Supplier<BaseColorWater> STILL_MAGENTA_WATER = FLUIDS.register("magenta_water", MagentaWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_MAGENTA_WATER = FLUIDS.register("flowing_magenta_water", MagentaWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_CONDENSE_MAGENTA_WATER = FLUIDS.register("condense_magenta_water", CondenseMagentaWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_CONDENSE_MAGENTA_WATER = FLUIDS.register("flowing_condense_magenta_water", CondenseMagentaWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_LUMINOUS_MAGENTA_WATER = FLUIDS.register("luminous_magenta_water", LuminousMagentaWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_MAGENTA_WATER = FLUIDS.register("flowing_luminous_magenta_water", LuminousMagentaWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_LUMINOUS_CONDENSE_MAGENTA_WATER = FLUIDS.register("luminous_condense_magenta_water", LuminousCondenseMagentaWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_CONDENSE_MAGENTA_WATER = FLUIDS.register("flowing_luminous_condense_magenta_water", LuminousCondenseMagentaWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_LIGHT_BLUE_WATER = FLUIDS.register("light_blue_water", LightBlueWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_LIGHT_BLUE_WATER = FLUIDS.register("flowing_light_blue_water", LightBlueWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_CONDENSE_LIGHT_BLUE_WATER = FLUIDS.register("condense_light_blue_water", CondenseLightBlueWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_CONDENSE_LIGHT_BLUE_WATER = FLUIDS.register("flowing_condense_light_blue_water", CondenseLightBlueWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_LUMINOUS_LIGHT_BLUE_WATER = FLUIDS.register("luminous_light_blue_water", LuminousLightBlueWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_LIGHT_BLUE_WATER = FLUIDS.register("flowing_luminous_light_blue_water", LuminousLightBlueWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_LUMINOUS_CONDENSE_LIGHT_BLUE_WATER = FLUIDS.register("luminous_condense_light_blue_water", LuminousCondenseLightBlueWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_CONDENSE_LIGHT_BLUE_WATER = FLUIDS.register("flowing_luminous_condense_light_blue_water", LuminousCondenseLightBlueWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_YELLOW_WATER = FLUIDS.register("yellow_water", YellowWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_YELLOW_WATER = FLUIDS.register("flowing_yellow_water", YellowWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_CONDENSE_YELLOW_WATER = FLUIDS.register("condense_yellow_water", CondenseYellowWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_CONDENSE_YELLOW_WATER = FLUIDS.register("flowing_condense_yellow_water", CondenseYellowWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_LUMINOUS_YELLOW_WATER = FLUIDS.register("luminous_yellow_water", LuminousYellowWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_YELLOW_WATER = FLUIDS.register("flowing_luminous_yellow_water", LuminousYellowWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_LUMINOUS_CONDENSE_YELLOW_WATER = FLUIDS.register("luminous_condense_yellow_water", LuminousCondenseYellowWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_CONDENSE_YELLOW_WATER = FLUIDS.register("flowing_luminous_condense_yellow_water", LuminousCondenseYellowWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_LIME_WATER = FLUIDS.register("lime_water", LimeWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_LIME_WATER = FLUIDS.register("flowing_lime_water", LimeWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_CONDENSE_LIME_WATER = FLUIDS.register("condense_lime_water", CondenseLimeWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_CONDENSE_LIME_WATER = FLUIDS.register("flowing_condense_lime_water", CondenseLimeWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_LUMINOUS_LIME_WATER = FLUIDS.register("luminous_lime_water", LuminousLimeWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_LIME_WATER = FLUIDS.register("flowing_luminous_lime_water", LuminousLimeWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_LUMINOUS_CONDENSE_LIME_WATER = FLUIDS.register("luminous_condense_lime_water", LuminousCondenseLimeWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_CONDENSE_LIME_WATER = FLUIDS.register("flowing_luminous_condense_lime_water", LuminousCondenseLimeWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_PINK_WATER = FLUIDS.register("pink_water", PinkWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_PINK_WATER = FLUIDS.register("flowing_pink_water", PinkWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_CONDENSE_PINK_WATER = FLUIDS.register("condense_pink_water", CondensePinkWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_CONDENSE_PINK_WATER = FLUIDS.register("flowing_condense_pink_water", CondensePinkWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_LUMINOUS_PINK_WATER = FLUIDS.register("luminous_pink_water", LuminousPinkWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_PINK_WATER = FLUIDS.register("flowing_luminous_pink_water", LuminousPinkWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_LUMINOUS_CONDENSE_PINK_WATER = FLUIDS.register("luminous_condense_pink_water", LuminousCondensePinkWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_CONDENSE_PINK_WATER = FLUIDS.register("flowing_luminous_condense_pink_water", LuminousCondensePinkWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_GRAY_WATER = FLUIDS.register("gray_water", GrayWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_GRAY_WATER = FLUIDS.register("flowing_gray_water", GrayWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_CONDENSE_GRAY_WATER = FLUIDS.register("condense_gray_water", CondenseGrayWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_CONDENSE_GRAY_WATER = FLUIDS.register("flowing_condense_gray_water", CondenseGrayWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_LUMINOUS_GRAY_WATER = FLUIDS.register("luminous_gray_water", LuminousGrayWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_GRAY_WATER = FLUIDS.register("flowing_luminous_gray_water", LuminousGrayWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_LUMINOUS_CONDENSE_GRAY_WATER = FLUIDS.register("luminous_condense_gray_water", LuminousCondenseGrayWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_CONDENSE_GRAY_WATER = FLUIDS.register("flowing_luminous_condense_gray_water", LuminousCondenseGrayWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_LIGHT_GRAY_WATER = FLUIDS.register("light_gray_water", LightGrayWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_LIGHT_GRAY_WATER = FLUIDS.register("flowing_light_gray_water", LightGrayWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_CONDENSE_LIGHT_GRAY_WATER = FLUIDS.register("condense_light_gray_water", CondenseLightGrayWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_CONDENSE_LIGHT_GRAY_WATER = FLUIDS.register("flowing_condense_light_gray_water", CondenseLightGrayWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_LUMINOUS_LIGHT_GRAY_WATER = FLUIDS.register("luminous_light_gray_water", LuminousLightGrayWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_LIGHT_GRAY_WATER = FLUIDS.register("flowing_luminous_light_gray_water", LuminousLightGrayWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_LUMINOUS_CONDENSE_LIGHT_GRAY_WATER = FLUIDS.register("luminous_condense_light_gray_water", LuminousCondenseLightGrayWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_CONDENSE_LIGHT_GRAY_WATER = FLUIDS.register("flowing_luminous_condense_light_gray_water", LuminousCondenseLightGrayWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_CYAN_WATER = FLUIDS.register("cyan_water", CyanWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_CYAN_WATER = FLUIDS.register("flowing_cyan_water", CyanWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_CONDENSE_CYAN_WATER = FLUIDS.register("condense_cyan_water", CondenseCyanWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_CONDENSE_CYAN_WATER = FLUIDS.register("flowing_condense_cyan_water", CondenseCyanWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_LUMINOUS_CYAN_WATER = FLUIDS.register("luminous_cyan_water", LuminousCyanWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_CYAN_WATER = FLUIDS.register("flowing_luminous_cyan_water", LuminousCyanWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_LUMINOUS_CONDENSE_CYAN_WATER = FLUIDS.register("luminous_condense_cyan_water", LuminousCondenseCyanWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_CONDENSE_CYAN_WATER = FLUIDS.register("flowing_luminous_condense_cyan_water", LuminousCondenseCyanWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_PURPLE_WATER = FLUIDS.register("purple_water", PurpleWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_PURPLE_WATER = FLUIDS.register("flowing_purple_water", PurpleWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_CONDENSE_PURPLE_WATER = FLUIDS.register("condense_purple_water", CondensePurpleWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_CONDENSE_PURPLE_WATER = FLUIDS.register("flowing_condense_purple_water", CondensePurpleWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_LUMINOUS_PURPLE_WATER = FLUIDS.register("luminous_purple_water", LuminousPurpleWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_PURPLE_WATER = FLUIDS.register("flowing_luminous_purple_water", LuminousPurpleWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_LUMINOUS_CONDENSE_PURPLE_WATER = FLUIDS.register("luminous_condense_purple_water", LuminousCondensePurpleWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_CONDENSE_PURPLE_WATER = FLUIDS.register("flowing_luminous_condense_purple_water", LuminousCondensePurpleWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_BLUE_WATER = FLUIDS.register("blue_water", BlueWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_BLUE_WATER = FLUIDS.register("flowing_blue_water", BlueWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_CONDENSE_BLUE_WATER = FLUIDS.register("condense_blue_water", CondenseBlueWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_CONDENSE_BLUE_WATER = FLUIDS.register("flowing_condense_blue_water", CondenseBlueWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_LUMINOUS_BLUE_WATER = FLUIDS.register("luminous_blue_water", LuminousBlueWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_BLUE_WATER = FLUIDS.register("flowing_luminous_blue_water", LuminousBlueWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_LUMINOUS_CONDENSE_BLUE_WATER = FLUIDS.register("luminous_condense_blue_water", LuminousCondenseBlueWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_CONDENSE_BLUE_WATER = FLUIDS.register("flowing_luminous_condense_blue_water", LuminousCondenseBlueWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_BROWN_WATER = FLUIDS.register("brown_water", BrownWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_BROWN_WATER = FLUIDS.register("flowing_brown_water", BrownWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_CONDENSE_BROWN_WATER = FLUIDS.register("condense_brown_water", CondenseBrownWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_CONDENSE_BROWN_WATER = FLUIDS.register("flowing_condense_brown_water", CondenseBrownWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_LUMINOUS_BROWN_WATER = FLUIDS.register("luminous_brown_water", LuminousBrownWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_BROWN_WATER = FLUIDS.register("flowing_luminous_brown_water", LuminousBrownWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_LUMINOUS_CONDENSE_BROWN_WATER = FLUIDS.register("luminous_condense_brown_water", LuminousCondenseBrownWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_CONDENSE_BROWN_WATER = FLUIDS.register("flowing_luminous_condense_brown_water", LuminousCondenseBrownWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_GREEN_WATER = FLUIDS.register("green_water", GreenWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_GREEN_WATER = FLUIDS.register("flowing_green_water", GreenWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_CONDENSE_GREEN_WATER = FLUIDS.register("condense_green_water", CondenseGreenWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_CONDENSE_GREEN_WATER = FLUIDS.register("flowing_condense_green_water", CondenseGreenWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_LUMINOUS_GREEN_WATER = FLUIDS.register("luminous_green_water", LuminousGreenWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_GREEN_WATER = FLUIDS.register("flowing_luminous_green_water", LuminousGreenWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_LUMINOUS_CONDENSE_GREEN_WATER = FLUIDS.register("luminous_condense_green_water", LuminousCondenseGreenWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_CONDENSE_GREEN_WATER = FLUIDS.register("flowing_luminous_condense_green_water", LuminousCondenseGreenWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_RED_WATER = FLUIDS.register("red_water", RedWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_RED_WATER = FLUIDS.register("flowing_red_water", RedWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_CONDENSE_RED_WATER = FLUIDS.register("condense_red_water", CondenseRedWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_CONDENSE_RED_WATER = FLUIDS.register("flowing_condense_red_water", CondenseRedWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_LUMINOUS_RED_WATER = FLUIDS.register("luminous_red_water", LuminousRedWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_RED_WATER = FLUIDS.register("flowing_luminous_red_water", LuminousRedWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_LUMINOUS_CONDENSE_RED_WATER = FLUIDS.register("luminous_condense_red_water", LuminousCondenseRedWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_CONDENSE_RED_WATER = FLUIDS.register("flowing_luminous_condense_red_water", LuminousCondenseRedWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_BLACK_WATER = FLUIDS.register("black_water", BlackWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_BLACK_WATER = FLUIDS.register("flowing_black_water", BlackWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_CONDENSE_BLACK_WATER = FLUIDS.register("condense_black_water", CondenseBlackWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_CONDENSE_BLACK_WATER = FLUIDS.register("flowing_condense_black_water", CondenseBlackWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_LUMINOUS_BLACK_WATER = FLUIDS.register("luminous_black_water", LuminousBlackWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_BLACK_WATER = FLUIDS.register("flowing_luminous_black_water", LuminousBlackWater.Flowing::new);

    public static final Supplier<BaseColorWater> STILL_LUMINOUS_CONDENSE_BLACK_WATER = FLUIDS.register("luminous_condense_black_water", LuminousCondenseBlackWater.Source::new);
    public static final Supplier<BaseColorWater> FLOWING_LUMINOUS_CONDENSE_BLACK_WATER = FLUIDS.register("flowing_luminous_condense_black_water", LuminousCondenseBlackWater.Flowing::new);

    public static void loadClass() {
    }

}
