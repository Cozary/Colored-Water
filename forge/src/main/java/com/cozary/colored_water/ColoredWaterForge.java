package com.cozary.colored_water;

import com.cozary.colored_water.client.ColoredWaterClient;
import com.cozary.colored_water.init.ModFluidTypes;
import com.cozary.colored_water.init.ModTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod(ColoredWater.MOD_ID)
public class ColoredWaterForge {

    public ColoredWaterForge() {
        final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ColoredWater.init();

        ModFluidTypes.FLUID_TYPES.register(eventBus);
        ModTabs.CREATIVE_MODE_TABS.register(eventBus);

        if (FMLEnvironment.dist == Dist.CLIENT) {
            eventBus.addListener(ColoredWaterClient::doClientStuff);
        }
        eventBus.addListener(ColoredWaterClient::setup);

    }
}