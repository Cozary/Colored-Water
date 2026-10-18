package com.cozary.colored_water.client;

import com.cozary.colored_water.init.ModBlocks;
import com.cozary.colored_water.init.ModCauldrons;
import com.cozary.colored_water.init.ModFluidTypes;
import com.cozary.colored_water.init.ModFluids;
import com.cozary.colored_water.util.ColoredWaterUtil;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

public class ColoredWaterClient {

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

    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register((state, view, pos, tintIndex) -> {
            if (tintIndex == 0) {
                return ColoredWaterUtil.getCauldronColor(state.getBlock());
            }
            return -1;
        }, ModCauldrons.getAllCauldronBlocks());
    }

    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {

        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xb41D1D21), ModFluidTypes.BLACK_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xb43C44AA), ModFluidTypes.BLUE_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xb4835432), ModFluidTypes.BROWN_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xb4169C9C), ModFluidTypes.CYAN_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xb4474F52), ModFluidTypes.GRAY_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xb45E7C16), ModFluidTypes.GREEN_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xb43AB3DA), ModFluidTypes.LIGHT_BLUE_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xb49D9D97), ModFluidTypes.LIGHT_GRAY_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xb480C71F), ModFluidTypes.LIME_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xb4C74EBD), ModFluidTypes.MAGENTA_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xb4F9801D), ModFluidTypes.ORANGE_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xb4F38BAA), ModFluidTypes.PINK_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xb48932B8), ModFluidTypes.PURPLE_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xb4B02E26), ModFluidTypes.RED_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xb4F9FFFE), ModFluidTypes.WHITE_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xb4FED83D), ModFluidTypes.YELLOW_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff1D1D21), ModFluidTypes.CONDENSE_BLACK_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff3C44AA), ModFluidTypes.CONDENSE_BLUE_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff835432), ModFluidTypes.CONDENSE_BROWN_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff169C9C), ModFluidTypes.CONDENSE_CYAN_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff474F52), ModFluidTypes.CONDENSE_GRAY_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff5E7C16), ModFluidTypes.CONDENSE_GREEN_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff3AB3DA), ModFluidTypes.CONDENSE_LIGHT_BLUE_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff9D9D97), ModFluidTypes.CONDENSE_LIGHT_GRAY_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff80C71F), ModFluidTypes.CONDENSE_LIME_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xffC74EBD), ModFluidTypes.CONDENSE_MAGENTA_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xffF9801D), ModFluidTypes.CONDENSE_ORANGE_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xffF38BAA), ModFluidTypes.CONDENSE_PINK_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff8932B8), ModFluidTypes.CONDENSE_PURPLE_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xffB02E26), ModFluidTypes.CONDENSE_RED_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xffF9FFFE), ModFluidTypes.CONDENSE_WHITE_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xffFED83D), ModFluidTypes.CONDENSE_YELLOW_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xb41D1D21), ModFluidTypes.LUMINOUS_BLACK_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xb43C44AA), ModFluidTypes.LUMINOUS_BLUE_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xb4835432), ModFluidTypes.LUMINOUS_BROWN_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xb4169C9C), ModFluidTypes.LUMINOUS_CYAN_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xb4474F52), ModFluidTypes.LUMINOUS_GRAY_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xb45E7C16), ModFluidTypes.LUMINOUS_GREEN_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xb43AB3DA), ModFluidTypes.LUMINOUS_LIGHT_BLUE_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xb49D9D97), ModFluidTypes.LUMINOUS_LIGHT_GRAY_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xb480C71F), ModFluidTypes.LUMINOUS_LIME_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xb4C74EBD), ModFluidTypes.LUMINOUS_MAGENTA_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xb4F9801D), ModFluidTypes.LUMINOUS_ORANGE_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xb4F38BAA), ModFluidTypes.LUMINOUS_PINK_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xb48932B8), ModFluidTypes.LUMINOUS_PURPLE_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xb4B02E26), ModFluidTypes.LUMINOUS_RED_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xb4F9FFFE), ModFluidTypes.LUMINOUS_WHITE_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xb4FED83D), ModFluidTypes.LUMINOUS_YELLOW_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff1D1D21), ModFluidTypes.LUMINOUS_CONDENSE_BLACK_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff3C44AA), ModFluidTypes.LUMINOUS_CONDENSE_BLUE_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff835432), ModFluidTypes.LUMINOUS_CONDENSE_BROWN_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff169C9C), ModFluidTypes.LUMINOUS_CONDENSE_CYAN_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff474F52), ModFluidTypes.LUMINOUS_CONDENSE_GRAY_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff5E7C16), ModFluidTypes.LUMINOUS_CONDENSE_GREEN_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff3AB3DA), ModFluidTypes.LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff9D9D97), ModFluidTypes.LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff80C71F), ModFluidTypes.LUMINOUS_CONDENSE_LIME_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xffC74EBD), ModFluidTypes.LUMINOUS_CONDENSE_MAGENTA_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xffF9801D), ModFluidTypes.LUMINOUS_CONDENSE_ORANGE_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xffF38BAA), ModFluidTypes.LUMINOUS_CONDENSE_PINK_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xff8932B8), ModFluidTypes.LUMINOUS_CONDENSE_PURPLE_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xffB02E26), ModFluidTypes.LUMINOUS_CONDENSE_RED_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xffF9FFFE), ModFluidTypes.LUMINOUS_CONDENSE_WHITE_WATER_TYPE.get());
        event.registerFluidType(new ModFluidTypes.FluidClientExtensions(0xffFED83D), ModFluidTypes.LUMINOUS_CONDENSE_YELLOW_WATER_TYPE.get());
    }

}
