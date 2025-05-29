package com.cozary.colored_water.client;

import com.cozary.colored_water.cauldrons.behaviour.ColorCauldronBehavior;
import com.cozary.colored_water.cauldrons.behaviour.CondenseCauldronBehavior;
import com.cozary.colored_water.cauldrons.behaviour.LuminousCauldronBehavior;
import com.cozary.colored_water.cauldrons.behaviour.LuminousCondenseCauldronBehavior;
import com.cozary.colored_water.init.ModFluids;
import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

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
}
