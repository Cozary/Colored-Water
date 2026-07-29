package com.cozary.colored_water.client;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.init.ModBlocks;
import com.cozary.colored_water.init.ModCauldrons;
import com.cozary.colored_water.init.ModFluids;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = ColoredWater.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ColoredWaterClient {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.COLORED_WATER_BLOCK.get(), ChunkSectionLayer.TRANSLUCENT);
            ItemBlockRenderTypes.setRenderLayer(ModCauldrons.COLORED_WATER_CAULDRON.get(), ChunkSectionLayer.TRANSLUCENT);
            ItemBlockRenderTypes.setRenderLayer(ModFluids.STILL_COLORED_WATER.get(), ChunkSectionLayer.TRANSLUCENT);
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_COLORED_WATER.get(), ChunkSectionLayer.TRANSLUCENT);
        });
    }
}
