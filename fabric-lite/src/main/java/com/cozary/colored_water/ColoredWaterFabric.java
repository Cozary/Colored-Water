package com.cozary.colored_water;

import com.cozary.colored_water.cauldrons.behaviour.ColorCauldronBehavior;
import com.cozary.colored_water.cauldrons.behaviour.CondenseCauldronBehavior;
import com.cozary.colored_water.cauldrons.behaviour.LuminousCauldronBehavior;
import com.cozary.colored_water.cauldrons.behaviour.LuminousCondenseCauldronBehavior;
import com.cozary.colored_water.init.ModTabs;
import net.fabricmc.api.ModInitializer;

public class ColoredWaterFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ColoredWater.init();

        ModTabs.loadClass();

        ColorCauldronBehavior.init();
        CondenseCauldronBehavior.init();
        LuminousCauldronBehavior.init();
        LuminousCondenseCauldronBehavior.init();
    }
}
