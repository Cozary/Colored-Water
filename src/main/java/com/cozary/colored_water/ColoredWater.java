package com.cozary.colored_water;

import com.cozary.colored_water.client.ColoredWaterClient;
import com.cozary.colored_water.init.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ColoredWater.MOD_ID)
public class ColoredWater {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "colored_water";

    public ColoredWater(IEventBus modEventBus) {

        ModFluids.FLUIDS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModRecipe.RECIPES.register(modEventBus);
        ModCauldrons.BLOCKS.register(modEventBus);
        ModTabs.CREATIVE_MODE_TABS.register(modEventBus);
        ModFluidTypes.FLUID_TYPES.register(modEventBus);

        if (FMLEnvironment.dist == Dist.CLIENT) {
            modEventBus.addListener(ColoredWaterClient::doClientStuff);
        }

        modEventBus.addListener(ColoredWaterClient::setup);
        modEventBus.addListener(ColoredWaterClient::registerClientExtensions);
    }

}
