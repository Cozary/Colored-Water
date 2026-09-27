package com.cozary.colored_water;

import com.cozary.colored_water.init.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ColoredWater {

    public static final String MOD_ID = "colored_water";
    public static final String MOD_NAME = "Colored Water";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    public static void init() {

        ModBlocks.loadClass();
        ModCauldrons.loadClass();
        ModFluids.loadClass();
        ModItems.loadClass();
        ModItems.loadClass();
        ModRecipe.loadClass();
        ModParticles.loadClass();
    }
}