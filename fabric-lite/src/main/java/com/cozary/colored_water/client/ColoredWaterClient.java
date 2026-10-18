package com.cozary.colored_water.client;

import com.cozary.colored_water.ColoredWater;

import com.cozary.colored_water.block.entity.WaterloggedColorBlockEntity;
import com.cozary.colored_water.fluids.ColorWaterFluid;
import com.cozary.colored_water.init.ModBlocks;
import com.cozary.colored_water.init.ModCauldrons;
import com.cozary.colored_water.init.ModFluids;
import com.cozary.colored_water.util.ColoredWaterUtil;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.material.FluidState;

@Environment(EnvType.CLIENT)
public class ColoredWaterClient implements ClientModInitializer {

    private static final Identifier STILL_TEXTURE = Identifier.fromNamespaceAndPath(ColoredWater.MOD_ID, "block/water_still");
    private static final Identifier FLOWING_TEXTURE = Identifier.fromNamespaceAndPath(ColoredWater.MOD_ID, "block/water_flowing");

    private static SimpleFluidRenderHandler createFluidHandler(int defaultColor) {
        return new SimpleFluidRenderHandler(STILL_TEXTURE, FLOWING_TEXTURE, defaultColor) {
            @Override
            public int getFluidColor(BlockAndTintGetter view, BlockPos pos, FluidState state) {
                if (view != null && pos != null) {
                    BlockEntity blockEntity = view.getBlockEntity(pos);
                    if (blockEntity instanceof WaterloggedColorBlockEntity waterloggedBe && waterloggedBe.hasFluid()) {
                        int rgb = waterloggedBe.getFluid().getTintColor();
                        int alpha = waterloggedBe.getFluid().isCondensed() ? ColoredWaterUtil.CONDENSED_ALPHA : ColoredWaterUtil.DEFAULT_ALPHA;
                        return ColoredWaterUtil.withAlpha(rgb, alpha);
                    }
                }
                if (state != null && state.getType() instanceof ColorWaterFluid cwf) {
                    int rgb = cwf.getTintColor();
                    int alpha = cwf.isCondensed() ? ColoredWaterUtil.CONDENSED_ALPHA : ColoredWaterUtil.DEFAULT_ALPHA;
                    return ColoredWaterUtil.withAlpha(rgb, alpha);
                }
                return defaultColor;
            }
        };
    }


    @Override
    public void onInitializeClient() {

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_BLACK_WATER.get(),
                ModFluids.FLOWING_BLACK_WATER.get(),
                createFluidHandler(0xb41D1D21));

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_BLUE_WATER.get(),
                ModFluids.FLOWING_BLUE_WATER.get(),
                createFluidHandler(0xb43C44AA));

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_BROWN_WATER.get(),
                ModFluids.FLOWING_BROWN_WATER.get(),
                createFluidHandler(0xb4835432));

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_CYAN_WATER.get(),
                ModFluids.FLOWING_CYAN_WATER.get(),
                createFluidHandler(0xb4169C9C));

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_GRAY_WATER.get(),
                ModFluids.FLOWING_GRAY_WATER.get(),
                createFluidHandler(0xb4474F52));

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_GREEN_WATER.get(),
                ModFluids.FLOWING_GREEN_WATER.get(),
                createFluidHandler(0xb45E7C16));

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LIGHT_BLUE_WATER.get(),
                ModFluids.FLOWING_LIGHT_BLUE_WATER.get(),
                createFluidHandler(0xb43AB3DA));

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LIGHT_GRAY_WATER.get(),
                ModFluids.FLOWING_LIGHT_GRAY_WATER.get(),
                createFluidHandler(0xb49D9D97));

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LIME_WATER.get(),
                ModFluids.FLOWING_LIME_WATER.get(),
                createFluidHandler(0xb480C71F));

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_MAGENTA_WATER.get(),
                ModFluids.FLOWING_MAGENTA_WATER.get(),
                createFluidHandler(0xb4C74EBD));

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_ORANGE_WATER.get(),
                ModFluids.FLOWING_ORANGE_WATER.get(),
                createFluidHandler(0xb4F9801D));

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_PINK_WATER.get(),
                ModFluids.FLOWING_PINK_WATER.get(),
                createFluidHandler(0xb4F38BAA));

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_PURPLE_WATER.get(),
                ModFluids.FLOWING_PURPLE_WATER.get(),
                createFluidHandler(0xb48932B8));

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_RED_WATER.get(),
                ModFluids.FLOWING_RED_WATER.get(),
                createFluidHandler(0xb4B02E26));

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_WHITE_WATER.get(),
                ModFluids.FLOWING_WHITE_WATER.get(),
                createFluidHandler(0xb4F9FFFE));

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_YELLOW_WATER.get(),
                ModFluids.FLOWING_YELLOW_WATER.get(),
                createFluidHandler(0xb4FED83D));

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_CONDENSE_BLACK_WATER.get(),
                ModFluids.FLOWING_CONDENSE_BLACK_WATER.get(),
                createFluidHandler(0xff1D1D21)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_CONDENSE_BLUE_WATER.get(),
                ModFluids.FLOWING_CONDENSE_BLUE_WATER.get(),
                createFluidHandler(0xff3C44AA)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_CONDENSE_BROWN_WATER.get(),
                ModFluids.FLOWING_CONDENSE_BROWN_WATER.get(),
                createFluidHandler(0xff835432)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_CONDENSE_CYAN_WATER.get(),
                ModFluids.FLOWING_CONDENSE_CYAN_WATER.get(),
                createFluidHandler(0xff169C9C)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_CONDENSE_GRAY_WATER.get(),
                ModFluids.FLOWING_CONDENSE_GRAY_WATER.get(),
                createFluidHandler(0xff474F52)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_CONDENSE_GREEN_WATER.get(),
                ModFluids.FLOWING_CONDENSE_GREEN_WATER.get(),
                createFluidHandler(0xff5E7C16)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_CONDENSE_LIGHT_BLUE_WATER.get(),
                ModFluids.FLOWING_CONDENSE_LIGHT_BLUE_WATER.get(),
                createFluidHandler(0xff3AB3DA)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_CONDENSE_LIGHT_GRAY_WATER.get(),
                ModFluids.FLOWING_CONDENSE_LIGHT_GRAY_WATER.get(),
                createFluidHandler(0xff9D9D97)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_CONDENSE_LIME_WATER.get(),
                ModFluids.FLOWING_CONDENSE_LIME_WATER.get(),
                createFluidHandler(0xff80C71F)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_CONDENSE_MAGENTA_WATER.get(),
                ModFluids.FLOWING_CONDENSE_MAGENTA_WATER.get(),
                createFluidHandler(0xffC74EBD)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_CONDENSE_ORANGE_WATER.get(),
                ModFluids.FLOWING_CONDENSE_ORANGE_WATER.get(),
                createFluidHandler(0xffF9801D)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_CONDENSE_PINK_WATER.get(),
                ModFluids.FLOWING_CONDENSE_PINK_WATER.get(),
                createFluidHandler(0xffF38BAA)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_CONDENSE_PURPLE_WATER.get(),
                ModFluids.FLOWING_CONDENSE_PURPLE_WATER.get(),
                createFluidHandler(0xff8932B8)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_CONDENSE_RED_WATER.get(),
                ModFluids.FLOWING_CONDENSE_RED_WATER.get(),
                createFluidHandler(0xffB02E26)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_CONDENSE_WHITE_WATER.get(),
                ModFluids.FLOWING_CONDENSE_WHITE_WATER.get(),
                createFluidHandler(0xffF9FFFE)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_CONDENSE_YELLOW_WATER.get(),
                ModFluids.FLOWING_CONDENSE_YELLOW_WATER.get(),
                createFluidHandler(0xffFED83D)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_BLACK_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_BLACK_WATER.get(),
                createFluidHandler(0xb41D1D21)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_BLUE_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_BLUE_WATER.get(),
                createFluidHandler(0xb43C44AA)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_BROWN_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_BROWN_WATER.get(),
                createFluidHandler(0xb4835432)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_CYAN_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_CYAN_WATER.get(),
                createFluidHandler(0xb4169C9C)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_GRAY_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_GRAY_WATER.get(),
                createFluidHandler(0xb4474F52)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_GREEN_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_GREEN_WATER.get(),
                createFluidHandler(0xb45E7C16)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_LIGHT_BLUE_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_LIGHT_BLUE_WATER.get(),
                createFluidHandler(0xb43AB3DA)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_LIGHT_GRAY_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_LIGHT_GRAY_WATER.get(),
                createFluidHandler(0xb49D9D97)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_LIME_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_LIME_WATER.get(),
                createFluidHandler(0xb480C71F)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_MAGENTA_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_MAGENTA_WATER.get(),
                createFluidHandler(0xb4C74EBD)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_ORANGE_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_ORANGE_WATER.get(),
                createFluidHandler(0xb4F9801D)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_PINK_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_PINK_WATER.get(),
                createFluidHandler(0xb4F38BAA)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_PURPLE_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_PURPLE_WATER.get(),
                createFluidHandler(0xb48932B8)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_RED_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_RED_WATER.get(),
                createFluidHandler(0xb4B02E26)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_WHITE_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_WHITE_WATER.get(),
                createFluidHandler(0xb4F9FFFE)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_YELLOW_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_YELLOW_WATER.get(),
                createFluidHandler(0xb4FED83D)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_CONDENSE_BLACK_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_CONDENSE_BLACK_WATER.get(),
                createFluidHandler(0xff1D1D21)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_CONDENSE_BLUE_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_CONDENSE_BLUE_WATER.get(),
                createFluidHandler(0xff3C44AA)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_CONDENSE_BROWN_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_CONDENSE_BROWN_WATER.get(),
                createFluidHandler(0xff835432)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_CONDENSE_CYAN_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_CONDENSE_CYAN_WATER.get(),
                createFluidHandler(0xff169C9C)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_CONDENSE_GRAY_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_CONDENSE_GRAY_WATER.get(),
                createFluidHandler(0xff474F52)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_CONDENSE_GREEN_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_CONDENSE_GREEN_WATER.get(),
                createFluidHandler(0xff5E7C16)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_CONDENSE_LIGHT_BLUE_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_CONDENSE_LIGHT_BLUE_WATER.get(),
                createFluidHandler(0xff3AB3DA)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_CONDENSE_LIGHT_GRAY_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_CONDENSE_LIGHT_GRAY_WATER.get(),
                createFluidHandler(0xff9D9D97)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_CONDENSE_LIME_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_CONDENSE_LIME_WATER.get(),
                createFluidHandler(0xff80C71F)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_CONDENSE_MAGENTA_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_CONDENSE_MAGENTA_WATER.get(),
                createFluidHandler(0xffC74EBD)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_CONDENSE_ORANGE_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_CONDENSE_ORANGE_WATER.get(),
                createFluidHandler(0xffF9801D)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_CONDENSE_PINK_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_CONDENSE_PINK_WATER.get(),
                createFluidHandler(0xffF38BAA)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_CONDENSE_PURPLE_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_CONDENSE_PURPLE_WATER.get(),
                createFluidHandler(0xff8932B8)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_CONDENSE_RED_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_CONDENSE_RED_WATER.get(),
                createFluidHandler(0xffB02E26)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_CONDENSE_WHITE_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_CONDENSE_WHITE_WATER.get(),
                createFluidHandler(0xffF9FFFE)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_CONDENSE_YELLOW_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_CONDENSE_YELLOW_WATER.get(),
                createFluidHandler(0xffFED83D)
        );

        for (var fluid : ModFluids.FLUIDS.getEntries()) {
            ChunkSectionLayer layer = ColoredWaterUtil.isCondensedFluid(fluid.get()) ? ChunkSectionLayer.SOLID : ChunkSectionLayer.TRANSLUCENT;
            BlockRenderLayerMap.putFluids(layer, fluid.get());
        }
        for (var block : ModBlocks.BLOCKS.getEntries()) {
            ChunkSectionLayer layer = ColoredWaterUtil.isCondensedBlock(block.get()) ? ChunkSectionLayer.SOLID : ChunkSectionLayer.TRANSLUCENT;
            BlockRenderLayerMap.putBlocks(layer, block.get());
        }
        for (Block cauldron : ModCauldrons.getAllCauldronBlocks()) {
            ChunkSectionLayer layer = ColoredWaterUtil.isCondensedCauldron(cauldron) ? ChunkSectionLayer.SOLID : ChunkSectionLayer.TRANSLUCENT;
            BlockRenderLayerMap.putBlock(cauldron, layer);
        }
 
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> {
            if (tintIndex == 0) {
                return ColoredWaterUtil.getCauldronColor(state.getBlock());
            }
            return -1;
        }, ModCauldrons.getAllCauldronBlocks());
    }
}