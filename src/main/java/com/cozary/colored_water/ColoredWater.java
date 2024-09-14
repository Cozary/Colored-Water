package com.cozary.colored_water;

import com.cozary.colored_water.cauldrons.registry.*;
import com.cozary.colored_water.fluids.ModFluids;
import com.cozary.colored_water.fluids.ModFluidsCondense;
import com.cozary.colored_water.fluids.ModFluidsLight;
import com.cozary.colored_water.fluids.ModFluidsLightCondense;
import com.cozary.colored_water.items.ModItems;
import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

@Mod("colored_water")
@Mod.EventBusSubscriber(modid = ColoredWater.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ColoredWater {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "colored_water";

    public ColoredWater() {
        final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        eventBus.addListener(this::setup);
        eventBus.addListener(this::doClientStuff);

        ModTabs.CREATIVE_MODE_TABS.register(eventBus);

        ModBlocks.BLOCKS.register(eventBus);

        ModItems.ITEMS.register(eventBus);
        ModItems.RECIPES.register(eventBus);

        ModFluids.BLOCKS.register(eventBus);
        ModFluids.FLUIDS.register(eventBus);
        ModFluids.FLUID_TYPES.register(eventBus);

        ModFluidsCondense.BLOCKS.register(eventBus);
        ModFluidsCondense.FLUIDS.register(eventBus);
        ModFluidsCondense.FLUID_TYPES.register(eventBus);

        ModFluidsLight.BLOCKS.register(eventBus);
        ModFluidsLight.FLUIDS.register(eventBus);
        ModFluidsLight.FLUID_TYPES.register(eventBus);

        ModFluidsLightCondense.BLOCKS.register(eventBus);
        ModFluidsLightCondense.FLUIDS.register(eventBus);
        ModFluidsLightCondense.FLUID_TYPES.register(eventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {

            CauldronBehavior.init();
            CondenseCauldronBehavior.init();
            LuminousCauldronBehavior.init();
            LuminousCondenseCauldronBehavior.init();

        });

    }

    private void doClientStuff(final FMLClientSetupEvent event) {

        final Map<Fluid, RenderType> TYPE_BY_FLUID = Util.make(Maps.newHashMap(), (map) -> {
            final RenderType translucent = RenderType.translucent();

            map.put(ModFluids.MAGENTA_FLUID_FLOWING.get(), translucent);
            map.put(ModFluids.MAGENTA_FLUID.get(), translucent);
            map.put(ModFluids.PURPLE_FLUID_FLOWING.get(), translucent);
            map.put(ModFluids.PURPLE_FLUID.get(), translucent);
            map.put(ModFluids.GREEN_FLUID_FLOWING.get(), translucent);
            map.put(ModFluids.GREEN_FLUID.get(), translucent);
            map.put(ModFluids.YELLOW_FLUID_FLOWING.get(), translucent);
            map.put(ModFluids.YELLOW_FLUID.get(), translucent);
            map.put(ModFluids.LIME_FLUID_FLOWING.get(), translucent);
            map.put(ModFluids.LIME_FLUID.get(), translucent);
            map.put(ModFluids.PINK_FLUID_FLOWING.get(), translucent);
            map.put(ModFluids.PINK_FLUID.get(), translucent);
            map.put(ModFluids.RED_FLUID_FLOWING.get(), translucent);
            map.put(ModFluids.RED_FLUID.get(), translucent);
            map.put(ModFluids.BLACK_FLUID_FLOWING.get(), translucent);
            map.put(ModFluids.BLACK_FLUID.get(), translucent);
            map.put(ModFluids.BROWN_FLUID_FLOWING.get(), translucent);
            map.put(ModFluids.BROWN_FLUID.get(), translucent);
            map.put(ModFluids.BLUE_FLUID_FLOWING.get(), translucent);
            map.put(ModFluids.BLUE_FLUID.get(), translucent);
            map.put(ModFluids.CYAN_FLUID_FLOWING.get(), translucent);
            map.put(ModFluids.CYAN_FLUID.get(), translucent);
            map.put(ModFluids.LIGHT_GRAY_FLUID_FLOWING.get(), translucent);
            map.put(ModFluids.LIGHT_GRAY_FLUID.get(), translucent);
            map.put(ModFluids.GRAY_FLUID_FLOWING.get(), translucent);
            map.put(ModFluids.GRAY_FLUID.get(), translucent);
            map.put(ModFluids.LIGHT_BLUE_FLUID_FLOWING.get(), translucent);
            map.put(ModFluids.LIGHT_BLUE_FLUID.get(), translucent);
            map.put(ModFluids.ORANGE_FLUID_FLOWING.get(), translucent);
            map.put(ModFluids.ORANGE_FLUID.get(), translucent);
            map.put(ModFluids.WHITE_FLUID_FLOWING.get(), translucent);
            map.put(ModFluids.WHITE_FLUID.get(), translucent);

            map.put(ModFluidsLight.LUMINOUS_MAGENTA_FLUID_FLOWING.get(), translucent);
            map.put(ModFluidsLight.LUMINOUS_MAGENTA_FLUID.get(), translucent);
            map.put(ModFluidsLight.LUMINOUS_PURPLE_FLUID_FLOWING.get(), translucent);
            map.put(ModFluidsLight.LUMINOUS_PURPLE_FLUID.get(), translucent);
            map.put(ModFluidsLight.LUMINOUS_GREEN_FLUID_FLOWING.get(), translucent);
            map.put(ModFluidsLight.LUMINOUS_GREEN_FLUID.get(), translucent);
            map.put(ModFluidsLight.LUMINOUS_YELLOW_FLUID_FLOWING.get(), translucent);
            map.put(ModFluidsLight.LUMINOUS_YELLOW_FLUID.get(), translucent);
            map.put(ModFluidsLight.LUMINOUS_LIME_FLUID_FLOWING.get(), translucent);
            map.put(ModFluidsLight.LUMINOUS_LIME_FLUID.get(), translucent);
            map.put(ModFluidsLight.LUMINOUS_PINK_FLUID_FLOWING.get(), translucent);
            map.put(ModFluidsLight.LUMINOUS_PINK_FLUID.get(), translucent);
            map.put(ModFluidsLight.LUMINOUS_RED_FLUID_FLOWING.get(), translucent);
            map.put(ModFluidsLight.LUMINOUS_RED_FLUID.get(), translucent);
            map.put(ModFluidsLight.LUMINOUS_BLACK_FLUID_FLOWING.get(), translucent);
            map.put(ModFluidsLight.LUMINOUS_BLACK_FLUID.get(), translucent);
            map.put(ModFluidsLight.LUMINOUS_BROWN_FLUID_FLOWING.get(), translucent);
            map.put(ModFluidsLight.LUMINOUS_BROWN_FLUID.get(), translucent);
            map.put(ModFluidsLight.LUMINOUS_BLUE_FLUID_FLOWING.get(), translucent);
            map.put(ModFluidsLight.LUMINOUS_BLUE_FLUID.get(), translucent);
            map.put(ModFluidsLight.LUMINOUS_CYAN_FLUID_FLOWING.get(), translucent);
            map.put(ModFluidsLight.LUMINOUS_CYAN_FLUID.get(), translucent);
            map.put(ModFluidsLight.LUMINOUS_LIGHT_GRAY_FLUID_FLOWING.get(), translucent);
            map.put(ModFluidsLight.LUMINOUS_LIGHT_GRAY_FLUID.get(), translucent);
            map.put(ModFluidsLight.LUMINOUS_GRAY_FLUID_FLOWING.get(), translucent);
            map.put(ModFluidsLight.LUMINOUS_GRAY_FLUID.get(), translucent);
            map.put(ModFluidsLight.LUMINOUS_LIGHT_BLUE_FLUID_FLOWING.get(), translucent);
            map.put(ModFluidsLight.LUMINOUS_LIGHT_BLUE_FLUID.get(), translucent);
            map.put(ModFluidsLight.LUMINOUS_ORANGE_FLUID_FLOWING.get(), translucent);
            map.put(ModFluidsLight.LUMINOUS_ORANGE_FLUID.get(), translucent);
            map.put(ModFluidsLight.LUMINOUS_WHITE_FLUID_FLOWING.get(), translucent);
            map.put(ModFluidsLight.LUMINOUS_WHITE_FLUID.get(), translucent);
        });

        TYPE_BY_FLUID.forEach(ItemBlockRenderTypes::setRenderLayer);

    }

}
