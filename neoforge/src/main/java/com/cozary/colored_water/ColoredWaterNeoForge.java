package com.cozary.colored_water;

import com.cozary.colored_water.init.ModFluidTypes;
import com.cozary.colored_water.init.ModTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@Mod(ColoredWater.MOD_ID)
public class ColoredWaterNeoForge {

    public ColoredWaterNeoForge(IEventBus eventBus) {
        ColoredWater.init();

        ModFluidTypes.FLUID_TYPES.register(eventBus);
        ModTabs.CREATIVE_MODE_TABS.register(eventBus);

        eventBus.addListener(this::commonSetup);
        eventBus.addListener(ModFluidTypes::registerClientExtensions);
        NeoForge.EVENT_BUS.addListener(this::registerCommands);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(ColoredWater::postInit);
    }

    private void registerCommands(RegisterCommandsEvent event) {
        ColoredWater.registerCommands(event.getDispatcher());
    }
}
