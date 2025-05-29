package com.cozary.colored_water.client;

import com.cozary.colored_water.cauldrons.behaviour.ColorCauldronBehavior;
import com.cozary.colored_water.cauldrons.behaviour.CondenseCauldronBehavior;
import com.cozary.colored_water.cauldrons.behaviour.LuminousCauldronBehavior;
import com.cozary.colored_water.cauldrons.behaviour.LuminousCondenseCauldronBehavior;
import com.cozary.colored_water.init.ModFluidTypes;
import com.cozary.colored_water.init.ModFluids;
import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

import java.util.Map;

public class ColoredWaterClient {

    public static void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {

            ColorCauldronBehavior.init();
            CondenseCauldronBehavior.init();
            LuminousCauldronBehavior.init();
            LuminousCondenseCauldronBehavior.init();

        });

    }

    public static void doClientStuff(final FMLClientSetupEvent event) {

        final Map<Fluid, RenderType> TYPE_BY_FLUID = Util.make(Maps.newHashMap(), (map) -> {
            final RenderType translucent = RenderType.translucent();

            map.put(ModFluids.STILL_MAGENTA_WATER.get(), translucent);
            map.put(ModFluids.FLOWING_MAGENTA_WATER.get(), translucent);
            map.put(ModFluids.STILL_PURPLE_WATER.get(), translucent);
            map.put(ModFluids.FLOWING_PURPLE_WATER.get(), translucent);
            map.put(ModFluids.STILL_GREEN_WATER.get(), translucent);
            map.put(ModFluids.FLOWING_GREEN_WATER.get(), translucent);
            map.put(ModFluids.STILL_YELLOW_WATER.get(), translucent);
            map.put(ModFluids.FLOWING_YELLOW_WATER.get(), translucent);
            map.put(ModFluids.STILL_LIME_WATER.get(), translucent);
            map.put(ModFluids.FLOWING_LIME_WATER.get(), translucent);
            map.put(ModFluids.STILL_PINK_WATER.get(), translucent);
            map.put(ModFluids.FLOWING_PINK_WATER.get(), translucent);
            map.put(ModFluids.STILL_RED_WATER.get(), translucent);
            map.put(ModFluids.FLOWING_RED_WATER.get(), translucent);
            map.put(ModFluids.STILL_BLACK_WATER.get(), translucent);
            map.put(ModFluids.FLOWING_BLACK_WATER.get(), translucent);
            map.put(ModFluids.STILL_BROWN_WATER.get(), translucent);
            map.put(ModFluids.FLOWING_BROWN_WATER.get(), translucent);
            map.put(ModFluids.STILL_BLUE_WATER.get(), translucent);
            map.put(ModFluids.FLOWING_BLUE_WATER.get(), translucent);
            map.put(ModFluids.STILL_CYAN_WATER.get(), translucent);
            map.put(ModFluids.FLOWING_CYAN_WATER.get(), translucent);
            map.put(ModFluids.STILL_LIGHT_GRAY_WATER.get(), translucent);
            map.put(ModFluids.FLOWING_LIGHT_GRAY_WATER.get(), translucent);
            map.put(ModFluids.STILL_GRAY_WATER.get(), translucent);
            map.put(ModFluids.FLOWING_GRAY_WATER.get(), translucent);
            map.put(ModFluids.STILL_LIGHT_BLUE_WATER.get(), translucent);
            map.put(ModFluids.FLOWING_LIGHT_BLUE_WATER.get(), translucent);
            map.put(ModFluids.STILL_ORANGE_WATER.get(), translucent);
            map.put(ModFluids.FLOWING_ORANGE_WATER.get(), translucent);
            map.put(ModFluids.STILL_WHITE_WATER.get(), translucent);
            map.put(ModFluids.FLOWING_WHITE_WATER.get(), translucent);

            map.put(ModFluids.STILL_LUMINOUS_MAGENTA_WATER.get(), translucent);
            map.put(ModFluids.FLOWING_LUMINOUS_MAGENTA_WATER.get(), translucent);
            map.put(ModFluids.STILL_LUMINOUS_PURPLE_WATER.get(), translucent);
            map.put(ModFluids.FLOWING_LUMINOUS_PURPLE_WATER.get(), translucent);
            map.put(ModFluids.STILL_LUMINOUS_GREEN_WATER.get(), translucent);
            map.put(ModFluids.FLOWING_LUMINOUS_GREEN_WATER.get(), translucent);
            map.put(ModFluids.STILL_LUMINOUS_YELLOW_WATER.get(), translucent);
            map.put(ModFluids.FLOWING_LUMINOUS_YELLOW_WATER.get(), translucent);
            map.put(ModFluids.STILL_LUMINOUS_LIME_WATER.get(), translucent);
            map.put(ModFluids.FLOWING_LUMINOUS_LIME_WATER.get(), translucent);
            map.put(ModFluids.STILL_LUMINOUS_PINK_WATER.get(), translucent);
            map.put(ModFluids.FLOWING_LUMINOUS_PINK_WATER.get(), translucent);
            map.put(ModFluids.STILL_LUMINOUS_RED_WATER.get(), translucent);
            map.put(ModFluids.FLOWING_LUMINOUS_RED_WATER.get(), translucent);
            map.put(ModFluids.STILL_LUMINOUS_BLACK_WATER.get(), translucent);
            map.put(ModFluids.FLOWING_LUMINOUS_BLACK_WATER.get(), translucent);
            map.put(ModFluids.STILL_LUMINOUS_BROWN_WATER.get(), translucent);
            map.put(ModFluids.FLOWING_LUMINOUS_BROWN_WATER.get(), translucent);
            map.put(ModFluids.STILL_LUMINOUS_BLUE_WATER.get(), translucent);
            map.put(ModFluids.FLOWING_LUMINOUS_BLUE_WATER.get(), translucent);
            map.put(ModFluids.STILL_LUMINOUS_CYAN_WATER.get(), translucent);
            map.put(ModFluids.FLOWING_LUMINOUS_CYAN_WATER.get(), translucent);
            map.put(ModFluids.STILL_LUMINOUS_LIGHT_GRAY_WATER.get(), translucent);
            map.put(ModFluids.FLOWING_LUMINOUS_LIGHT_GRAY_WATER.get(), translucent);
            map.put(ModFluids.STILL_LUMINOUS_GRAY_WATER.get(), translucent);
            map.put(ModFluids.FLOWING_LUMINOUS_GRAY_WATER.get(), translucent);
            map.put(ModFluids.STILL_LUMINOUS_LIGHT_BLUE_WATER.get(), translucent);
            map.put(ModFluids.FLOWING_LUMINOUS_LIGHT_BLUE_WATER.get(), translucent);
            map.put(ModFluids.STILL_LUMINOUS_ORANGE_WATER.get(), translucent);
            map.put(ModFluids.FLOWING_LUMINOUS_ORANGE_WATER.get(), translucent);
            map.put(ModFluids.STILL_LUMINOUS_WHITE_WATER.get(), translucent);
            map.put(ModFluids.FLOWING_LUMINOUS_WHITE_WATER.get(), translucent);

        });

        TYPE_BY_FLUID.forEach(ItemBlockRenderTypes::setRenderLayer);
    }

    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {

        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff1D1D21), ModFluidTypes.BLACK_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff3C44AA), ModFluidTypes.BLUE_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff835432), ModFluidTypes.BROWN_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff169C9C), ModFluidTypes.CYAN_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff474F52), ModFluidTypes.GRAY_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff5E7C16), ModFluidTypes.GREEN_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff3AB3DA), ModFluidTypes.LIGHT_BLUE_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff9D9D97), ModFluidTypes.LIGHT_GRAY_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff80C71F), ModFluidTypes.LIME_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xffC74EBD), ModFluidTypes.MAGENTA_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xffF9801D), ModFluidTypes.ORANGE_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xffF38BAA), ModFluidTypes.PINK_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff8932B8), ModFluidTypes.PURPLE_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xffB02E26), ModFluidTypes.RED_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xffF9FFFE), ModFluidTypes.WHITE_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xffFED83D), ModFluidTypes.YELLOW_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff1D1D21), ModFluidTypes.CONDENSE_BLACK_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff3C44AA), ModFluidTypes.CONDENSE_BLUE_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff835432), ModFluidTypes.CONDENSE_BROWN_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff169C9C), ModFluidTypes.CONDENSE_CYAN_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff474F52), ModFluidTypes.CONDENSE_GRAY_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff5E7C16), ModFluidTypes.CONDENSE_GREEN_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff3AB3DA), ModFluidTypes.CONDENSE_LIGHT_BLUE_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff9D9D97), ModFluidTypes.CONDENSE_LIGHT_GRAY_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff80C71F), ModFluidTypes.CONDENSE_LIME_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xffC74EBD), ModFluidTypes.CONDENSE_MAGENTA_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xffF9801D), ModFluidTypes.CONDENSE_ORANGE_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xffF38BAA), ModFluidTypes.CONDENSE_PINK_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff8932B8), ModFluidTypes.CONDENSE_PURPLE_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xffB02E26), ModFluidTypes.CONDENSE_RED_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xffF9FFFE), ModFluidTypes.CONDENSE_WHITE_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xffFED83D), ModFluidTypes.CONDENSE_YELLOW_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff1D1D21), ModFluidTypes.LUMINOUS_BLACK_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff3C44AA), ModFluidTypes.LUMINOUS_BLUE_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff835432), ModFluidTypes.LUMINOUS_BROWN_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff169C9C), ModFluidTypes.LUMINOUS_CYAN_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff474F52), ModFluidTypes.LUMINOUS_GRAY_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff5E7C16), ModFluidTypes.LUMINOUS_GREEN_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff3AB3DA), ModFluidTypes.LUMINOUS_LIGHT_BLUE_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff9D9D97), ModFluidTypes.LUMINOUS_LIGHT_GRAY_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff80C71F), ModFluidTypes.LUMINOUS_LIME_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xffC74EBD), ModFluidTypes.LUMINOUS_MAGENTA_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xffF9801D), ModFluidTypes.LUMINOUS_ORANGE_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xffF38BAA), ModFluidTypes.LUMINOUS_PINK_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff8932B8), ModFluidTypes.LUMINOUS_PURPLE_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xffB02E26), ModFluidTypes.LUMINOUS_RED_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xffF9FFFE), ModFluidTypes.LUMINOUS_WHITE_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xffFED83D), ModFluidTypes.LUMINOUS_YELLOW_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff1D1D21), ModFluidTypes.LUMINOUS_CONDENSE_BLACK_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff3C44AA), ModFluidTypes.LUMINOUS_CONDENSE_BLUE_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff835432), ModFluidTypes.LUMINOUS_CONDENSE_BROWN_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff169C9C), ModFluidTypes.LUMINOUS_CONDENSE_CYAN_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff474F52), ModFluidTypes.LUMINOUS_CONDENSE_GRAY_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff5E7C16), ModFluidTypes.LUMINOUS_CONDENSE_GREEN_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff3AB3DA), ModFluidTypes.LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff9D9D97), ModFluidTypes.LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff80C71F), ModFluidTypes.LUMINOUS_CONDENSE_LIME_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xffC74EBD), ModFluidTypes.LUMINOUS_CONDENSE_MAGENTA_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xffF9801D), ModFluidTypes.LUMINOUS_CONDENSE_ORANGE_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xffF38BAA), ModFluidTypes.LUMINOUS_CONDENSE_PINK_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff8932B8), ModFluidTypes.LUMINOUS_CONDENSE_PURPLE_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xffB02E26), ModFluidTypes.LUMINOUS_CONDENSE_RED_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xffF9FFFE), ModFluidTypes.LUMINOUS_CONDENSE_WHITE_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xffFED83D), ModFluidTypes.LUMINOUS_CONDENSE_YELLOW_WATER_TYPE.get());
    }

}
