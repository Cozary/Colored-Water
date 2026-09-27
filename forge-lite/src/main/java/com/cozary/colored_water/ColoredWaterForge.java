package com.cozary.colored_water;

import com.cozary.colored_water.client.ColoredWaterClient;
import com.cozary.colored_water.init.ModFluidTypes;
import com.cozary.colored_water.init.ModTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod(ColoredWater.MOD_ID)
public class ColoredWaterForge {

    public ColoredWaterForge(FMLJavaModLoadingContext context) {
        var eventBus = context.getModBusGroup();
        ColoredWater.init();

        ModFluidTypes.FLUID_TYPES.register(eventBus);
        ModTabs.CREATIVE_MODE_TABS.register(eventBus);

        if (FMLEnvironment.dist == Dist.CLIENT) {
            FMLClientSetupEvent.getBus(eventBus).addListener(ColoredWaterClient::doClientStuff);
        }

        FMLCommonSetupEvent.getBus(eventBus).addListener(ColoredWaterClient::setup);

    }
}