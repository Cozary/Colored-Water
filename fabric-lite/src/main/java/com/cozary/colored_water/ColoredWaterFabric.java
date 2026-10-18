package com.cozary.colored_water;

import com.cozary.colored_water.init.ModTabs;
import net.fabricmc.api.ModInitializer;

public class ColoredWaterFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ColoredWater.init();

        ModTabs.loadClass();

        ColoredWater.postInit();
    }
}
