package com.cozary.colored_water;


import com.cozary.colored_water.client.ColoredWaterClient;
import com.cozary.colored_water.init.ModFluidTypes;
import com.cozary.colored_water.init.ModTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;

@Mod(ColoredWater.MOD_ID)
public class ColoredWaterNeoForge {

    public ColoredWaterNeoForge(IEventBus eventBus) {

        ColoredWater.init();

        ModFluidTypes.FLUID_TYPES.register(eventBus);
        ModTabs.CREATIVE_MODE_TABS.register(eventBus);

        if (FMLEnvironment.dist == Dist.CLIENT) {
            eventBus.addListener(ColoredWaterClient::doClientStuff);
        }

        eventBus.addListener(ColoredWaterClient::setup);
        eventBus.addListener(ColoredWaterClient::registerClientExtensions);
    }
}