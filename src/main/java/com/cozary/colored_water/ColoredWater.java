package com.cozary.colored_water;

import com.cozary.colored_water.client.ColoredWaterClient;
import com.cozary.colored_water.init.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("colored_water")
@Mod.EventBusSubscriber(modid = ColoredWater.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ColoredWater {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "colored_water";

    public ColoredWater() {
        final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModFluids.FLUIDS.register(eventBus);
        ModBlocks.BLOCKS.register(eventBus);
        ModItems.ITEMS.register(eventBus);
        ModRecipe.RECIPES.register(eventBus);
        ModCauldrons.BLOCKS.register(eventBus);
        ModTabs.CREATIVE_MODE_TABS.register(eventBus);
        ModFluidTypes.FLUID_TYPES.register(eventBus);


        if (FMLEnvironment.dist == Dist.CLIENT) {
            eventBus.addListener(ColoredWaterClient::doClientStuff);
        }        eventBus.addListener(ColoredWaterClient::setup);

        MinecraftForge.EVENT_BUS.register(this);
    }
}
