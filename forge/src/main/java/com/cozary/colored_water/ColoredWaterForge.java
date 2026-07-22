package com.cozary.colored_water;

import com.cozary.colored_water.init.ModFluidTypes;
import com.cozary.colored_water.init.ModTabs;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ColoredWater.MOD_ID)
public class ColoredWaterForge {

    public ColoredWaterForge(FMLJavaModLoadingContext context) {
        var eventBus = context.getModBusGroup();
        ColoredWater.init();

        ModFluidTypes.FLUID_TYPES.register(eventBus);
        ModTabs.CREATIVE_MODE_TABS.register(eventBus);

        RegisterCommandsEvent.BUS.addListener(this::registerCommands);
    }

    @SubscribeEvent
    private void registerCommands(RegisterCommandsEvent event) {
        ColoredWater.registerCommands(event.getDispatcher());
    }
}
