package com.cozary.colored_water.client;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.block.entity.ColoredWaterCauldronBlockEntity;
import com.cozary.colored_water.init.ModBlocks;
import com.cozary.colored_water.init.ModCauldrons;
import com.cozary.colored_water.init.ModFluids;
import com.cozary.colored_water.util.ColoredWaterUtil;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@EventBusSubscriber(modid = ColoredWater.MOD_ID, value = Dist.CLIENT)
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

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register((state, view, pos, tintIndex) -> {
            if (view != null && pos != null) {
                BlockEntity be = view.getBlockEntity(pos);
                if (be instanceof ColoredWaterCauldronBlockEntity coloredBe) {
                    return coloredBe.getColor();
                }
            }
            return ColoredWaterUtil.DEFAULT_ARGB_NORMAL;
        }, ModCauldrons.COLORED_WATER_CAULDRON.get());
    }
}
