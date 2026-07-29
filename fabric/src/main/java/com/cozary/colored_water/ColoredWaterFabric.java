package com.cozary.colored_water;

import com.cozary.colored_water.init.ModTabs;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class ColoredWaterFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ColoredWater.init();

        ModTabs.loadClass();

        ColoredWater.postInit();

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            ColoredWater.registerCommands(dispatcher);
        });
    }
}
