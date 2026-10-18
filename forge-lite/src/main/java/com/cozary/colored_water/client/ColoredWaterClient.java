package com.cozary.colored_water.client;

import com.cozary.colored_water.init.ModBlocks;
import com.cozary.colored_water.init.ModCauldrons;
import com.cozary.colored_water.init.ModFluids;
import com.cozary.colored_water.util.ColoredWaterUtil;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ColoredWaterClient {

    public static void init() {
        RegisterColorHandlersEvent.Block.BUS.addListener(ColoredWaterClient::registerBlockColors);
        RegisterParticleProvidersEvent.BUS.addListener(ParticleRegister::registerFactories);
    }

    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register((state, view, pos, tintIndex) -> {
            if (tintIndex == 0) {
                return ColoredWaterUtil.getCauldronColor(state.getBlock());
            }
            return -1;
        }, ModCauldrons.getAllCauldronBlocks());
    }

    public static void onClientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            for (var fluid : ModFluids.FLUIDS.getEntries()) {
                ChunkSectionLayer layer = ColoredWaterUtil.isCondensedFluid(fluid.get()) ? ChunkSectionLayer.SOLID : ChunkSectionLayer.TRANSLUCENT;
                ItemBlockRenderTypes.setRenderLayer(fluid.get(), layer);
            }
            for (var block : ModBlocks.BLOCKS.getEntries()) {
                ChunkSectionLayer layer = ColoredWaterUtil.isCondensedBlock(block.get()) ? ChunkSectionLayer.SOLID : ChunkSectionLayer.TRANSLUCENT;
                ItemBlockRenderTypes.setRenderLayer(block.get(), layer);
            }
            for (Block cauldron : ModCauldrons.getAllCauldronBlocks()) {
                ChunkSectionLayer layer = ColoredWaterUtil.isCondensedCauldron(cauldron) ? ChunkSectionLayer.SOLID : ChunkSectionLayer.TRANSLUCENT;
                ItemBlockRenderTypes.setRenderLayer(cauldron, layer);
            }
        });
    }
}
