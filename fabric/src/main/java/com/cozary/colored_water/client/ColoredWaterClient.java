package com.cozary.colored_water.client;

import com.cozary.colored_water.init.ModCauldrons;
import com.cozary.colored_water.init.ModFluids;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class ColoredWaterClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_BLACK_WATER.get(),
                ModFluids.FLOWING_BLACK_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xff1D1D21
                ));

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_BLUE_WATER.get(),
                ModFluids.FLOWING_BLUE_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xff3C44AA
                ));

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_BROWN_WATER.get(),
                ModFluids.FLOWING_BROWN_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xff835432
                ));

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_CYAN_WATER.get(),
                ModFluids.FLOWING_CYAN_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xff169C9C
                ));

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_GRAY_WATER.get(),
                ModFluids.FLOWING_GRAY_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xff474F52
                ));

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_GREEN_WATER.get(),
                ModFluids.FLOWING_GREEN_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xff5E7C16
                ));

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LIGHT_BLUE_WATER.get(),
                ModFluids.FLOWING_LIGHT_BLUE_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xff3AB3DA
                ));

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LIGHT_GRAY_WATER.get(),
                ModFluids.FLOWING_LIGHT_GRAY_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xff9D9D97
                ));

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LIME_WATER.get(),
                ModFluids.FLOWING_LIME_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xff80C71F
                ));

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_MAGENTA_WATER.get(),
                ModFluids.FLOWING_MAGENTA_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xffC74EBD
                ));

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_ORANGE_WATER.get(),
                ModFluids.FLOWING_ORANGE_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xffF9801D
                ));

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_PINK_WATER.get(),
                ModFluids.FLOWING_PINK_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xffF38BAA
                ));

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_PURPLE_WATER.get(),
                ModFluids.FLOWING_PURPLE_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xff8932B8
                ));

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_RED_WATER.get(),
                ModFluids.FLOWING_RED_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xffB02E26
                ));

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_WHITE_WATER.get(),
                ModFluids.FLOWING_WHITE_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xffF9FFFE
                ));

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_YELLOW_WATER.get(),
                ModFluids.FLOWING_YELLOW_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xffFED83D
                ));

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_CONDENSE_BLACK_WATER.get(),
                ModFluids.FLOWING_CONDENSE_BLACK_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xff1D1D21
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_CONDENSE_BLUE_WATER.get(),
                ModFluids.FLOWING_CONDENSE_BLUE_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xff3C44AA
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_CONDENSE_BROWN_WATER.get(),
                ModFluids.FLOWING_CONDENSE_BROWN_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xff835432
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_CONDENSE_CYAN_WATER.get(),
                ModFluids.FLOWING_CONDENSE_CYAN_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xff169C9C
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_CONDENSE_GRAY_WATER.get(),
                ModFluids.FLOWING_CONDENSE_GRAY_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xff474F52
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_CONDENSE_GREEN_WATER.get(),
                ModFluids.FLOWING_CONDENSE_GREEN_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xff5E7C16
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_CONDENSE_LIGHT_BLUE_WATER.get(),
                ModFluids.FLOWING_CONDENSE_LIGHT_BLUE_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xff3AB3DA
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_CONDENSE_LIGHT_GRAY_WATER.get(),
                ModFluids.FLOWING_CONDENSE_LIGHT_GRAY_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xff9D9D97
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_CONDENSE_LIME_WATER.get(),
                ModFluids.FLOWING_CONDENSE_LIME_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xff80C71F
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_CONDENSE_MAGENTA_WATER.get(),
                ModFluids.FLOWING_CONDENSE_MAGENTA_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xffC74EBD
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_CONDENSE_ORANGE_WATER.get(),
                ModFluids.FLOWING_CONDENSE_ORANGE_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xffF9801D
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_CONDENSE_PINK_WATER.get(),
                ModFluids.FLOWING_CONDENSE_PINK_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xffF38BAA
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_CONDENSE_PURPLE_WATER.get(),
                ModFluids.FLOWING_CONDENSE_PURPLE_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xff8932B8
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_CONDENSE_RED_WATER.get(),
                ModFluids.FLOWING_CONDENSE_RED_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xffB02E26
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_CONDENSE_WHITE_WATER.get(),
                ModFluids.FLOWING_CONDENSE_WHITE_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xffF9FFFE
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_CONDENSE_YELLOW_WATER.get(),
                ModFluids.FLOWING_CONDENSE_YELLOW_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xffFED83D
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_BLACK_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_BLACK_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xff1D1D21
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_BLUE_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_BLUE_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xff3C44AA
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_BROWN_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_BROWN_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xff835432
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_CYAN_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_CYAN_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xff169C9C
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_GRAY_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_GRAY_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xff474F52
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_GREEN_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_GREEN_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xff5E7C16
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_LIGHT_BLUE_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_LIGHT_BLUE_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xff3AB3DA
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_LIGHT_GRAY_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_LIGHT_GRAY_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xff9D9D97
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_LIME_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_LIME_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xff80C71F
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_MAGENTA_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_MAGENTA_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xffC74EBD
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_ORANGE_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_ORANGE_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xffF9801D
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_PINK_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_PINK_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xffF38BAA
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_PURPLE_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_PURPLE_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xff8932B8
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_RED_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_RED_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xffB02E26
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_WHITE_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_WHITE_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xffF9FFFE
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_YELLOW_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_YELLOW_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xffFED83D
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_CONDENSE_BLACK_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_CONDENSE_BLACK_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xff1D1D21
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_CONDENSE_BLUE_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_CONDENSE_BLUE_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xff3C44AA
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_CONDENSE_BROWN_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_CONDENSE_BROWN_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xff835432
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_CONDENSE_CYAN_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_CONDENSE_CYAN_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xff169C9C
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_CONDENSE_GRAY_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_CONDENSE_GRAY_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xff474F52
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_CONDENSE_GREEN_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_CONDENSE_GREEN_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xff5E7C16
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_CONDENSE_LIGHT_BLUE_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_CONDENSE_LIGHT_BLUE_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xff3AB3DA
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_CONDENSE_LIGHT_GRAY_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_CONDENSE_LIGHT_GRAY_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xff9D9D97
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_CONDENSE_LIME_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_CONDENSE_LIME_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xff80C71F
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_CONDENSE_MAGENTA_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_CONDENSE_MAGENTA_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xffC74EBD
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_CONDENSE_ORANGE_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_CONDENSE_ORANGE_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xffF9801D
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_CONDENSE_PINK_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_CONDENSE_PINK_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xffF38BAA
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_CONDENSE_PURPLE_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_CONDENSE_PURPLE_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xff8932B8
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_CONDENSE_RED_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_CONDENSE_RED_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xffB02E26
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_CONDENSE_WHITE_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_CONDENSE_WHITE_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xffF9FFFE
                )
        );

        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LUMINOUS_CONDENSE_YELLOW_WATER.get(),
                ModFluids.FLOWING_LUMINOUS_CONDENSE_YELLOW_WATER.get(),
                new SimpleFluidRenderHandler(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still"),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow"),
                        0xffFED83D
                )
        );

        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.translucent(), ModFluids.STILL_BLACK_WATER.get(), ModFluids.FLOWING_BLACK_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.translucent(), ModFluids.STILL_BLUE_WATER.get(), ModFluids.FLOWING_BLUE_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.translucent(), ModFluids.STILL_BROWN_WATER.get(), ModFluids.FLOWING_BROWN_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.translucent(), ModFluids.STILL_CYAN_WATER.get(), ModFluids.FLOWING_CYAN_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.translucent(), ModFluids.STILL_GRAY_WATER.get(), ModFluids.FLOWING_GRAY_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.translucent(), ModFluids.STILL_GREEN_WATER.get(), ModFluids.FLOWING_GREEN_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.translucent(), ModFluids.STILL_LIGHT_BLUE_WATER.get(), ModFluids.FLOWING_LIGHT_BLUE_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.translucent(), ModFluids.STILL_LIGHT_GRAY_WATER.get(), ModFluids.FLOWING_LIGHT_GRAY_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.translucent(), ModFluids.STILL_LIME_WATER.get(), ModFluids.FLOWING_LIME_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.translucent(), ModFluids.STILL_MAGENTA_WATER.get(), ModFluids.FLOWING_MAGENTA_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.translucent(), ModFluids.STILL_ORANGE_WATER.get(), ModFluids.FLOWING_ORANGE_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.translucent(), ModFluids.STILL_PINK_WATER.get(), ModFluids.FLOWING_PINK_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.translucent(), ModFluids.STILL_PURPLE_WATER.get(), ModFluids.FLOWING_PURPLE_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.translucent(), ModFluids.STILL_RED_WATER.get(), ModFluids.FLOWING_RED_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.translucent(), ModFluids.STILL_WHITE_WATER.get(), ModFluids.FLOWING_WHITE_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.translucent(), ModFluids.STILL_YELLOW_WATER.get(), ModFluids.FLOWING_YELLOW_WATER.get());

        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.solid(), ModFluids.STILL_CONDENSE_BLACK_WATER.get(), ModFluids.FLOWING_CONDENSE_BLACK_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.solid(), ModFluids.STILL_CONDENSE_BLUE_WATER.get(), ModFluids.FLOWING_CONDENSE_BLUE_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.solid(), ModFluids.STILL_CONDENSE_BROWN_WATER.get(), ModFluids.FLOWING_CONDENSE_BROWN_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.solid(), ModFluids.STILL_CONDENSE_CYAN_WATER.get(), ModFluids.FLOWING_CONDENSE_CYAN_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.solid(), ModFluids.STILL_CONDENSE_GRAY_WATER.get(), ModFluids.FLOWING_CONDENSE_GRAY_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.solid(), ModFluids.STILL_CONDENSE_GREEN_WATER.get(), ModFluids.FLOWING_CONDENSE_GREEN_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.solid(), ModFluids.STILL_CONDENSE_LIGHT_BLUE_WATER.get(), ModFluids.FLOWING_CONDENSE_LIGHT_BLUE_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.solid(), ModFluids.STILL_CONDENSE_LIGHT_GRAY_WATER.get(), ModFluids.FLOWING_CONDENSE_LIGHT_GRAY_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.solid(), ModFluids.STILL_CONDENSE_LIME_WATER.get(), ModFluids.FLOWING_CONDENSE_LIME_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.solid(), ModFluids.STILL_CONDENSE_MAGENTA_WATER.get(), ModFluids.FLOWING_CONDENSE_MAGENTA_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.solid(), ModFluids.STILL_CONDENSE_ORANGE_WATER.get(), ModFluids.FLOWING_CONDENSE_ORANGE_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.solid(), ModFluids.STILL_CONDENSE_PINK_WATER.get(), ModFluids.FLOWING_CONDENSE_PINK_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.solid(), ModFluids.STILL_CONDENSE_PURPLE_WATER.get(), ModFluids.FLOWING_CONDENSE_PURPLE_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.solid(), ModFluids.STILL_CONDENSE_RED_WATER.get(), ModFluids.FLOWING_CONDENSE_RED_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.solid(), ModFluids.STILL_CONDENSE_WHITE_WATER.get(), ModFluids.FLOWING_CONDENSE_WHITE_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.solid(), ModFluids.STILL_CONDENSE_YELLOW_WATER.get(), ModFluids.FLOWING_CONDENSE_YELLOW_WATER.get());

        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.translucent(), ModFluids.STILL_LUMINOUS_BLACK_WATER.get(), ModFluids.FLOWING_LUMINOUS_BLACK_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.translucent(), ModFluids.STILL_LUMINOUS_BLUE_WATER.get(), ModFluids.FLOWING_LUMINOUS_BLUE_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.translucent(), ModFluids.STILL_LUMINOUS_BROWN_WATER.get(), ModFluids.FLOWING_LUMINOUS_BROWN_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.translucent(), ModFluids.STILL_LUMINOUS_CYAN_WATER.get(), ModFluids.FLOWING_LUMINOUS_CYAN_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.translucent(), ModFluids.STILL_LUMINOUS_GRAY_WATER.get(), ModFluids.FLOWING_LUMINOUS_GRAY_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.translucent(), ModFluids.STILL_LUMINOUS_GREEN_WATER.get(), ModFluids.FLOWING_LUMINOUS_GREEN_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.translucent(), ModFluids.STILL_LUMINOUS_LIGHT_BLUE_WATER.get(), ModFluids.FLOWING_LUMINOUS_LIGHT_BLUE_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.translucent(), ModFluids.STILL_LUMINOUS_LIGHT_GRAY_WATER.get(), ModFluids.FLOWING_LUMINOUS_LIGHT_GRAY_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.translucent(), ModFluids.STILL_LUMINOUS_LIME_WATER.get(), ModFluids.FLOWING_LUMINOUS_LIME_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.translucent(), ModFluids.STILL_LUMINOUS_MAGENTA_WATER.get(), ModFluids.FLOWING_LUMINOUS_MAGENTA_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.translucent(), ModFluids.STILL_LUMINOUS_ORANGE_WATER.get(), ModFluids.FLOWING_LUMINOUS_ORANGE_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.translucent(), ModFluids.STILL_LUMINOUS_PINK_WATER.get(), ModFluids.FLOWING_LUMINOUS_PINK_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.translucent(), ModFluids.STILL_LUMINOUS_PURPLE_WATER.get(), ModFluids.FLOWING_LUMINOUS_PURPLE_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.translucent(), ModFluids.STILL_LUMINOUS_RED_WATER.get(), ModFluids.FLOWING_LUMINOUS_RED_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.translucent(), ModFluids.STILL_LUMINOUS_WHITE_WATER.get(), ModFluids.FLOWING_LUMINOUS_WHITE_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.translucent(), ModFluids.STILL_LUMINOUS_YELLOW_WATER.get(), ModFluids.FLOWING_LUMINOUS_YELLOW_WATER.get());

        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.solid(), ModFluids.STILL_LUMINOUS_CONDENSE_BLACK_WATER.get(), ModFluids.FLOWING_LUMINOUS_CONDENSE_BLACK_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.solid(), ModFluids.STILL_LUMINOUS_CONDENSE_BLUE_WATER.get(), ModFluids.FLOWING_LUMINOUS_CONDENSE_BLUE_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.solid(), ModFluids.STILL_LUMINOUS_CONDENSE_BROWN_WATER.get(), ModFluids.FLOWING_LUMINOUS_CONDENSE_BROWN_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.solid(), ModFluids.STILL_LUMINOUS_CONDENSE_CYAN_WATER.get(), ModFluids.FLOWING_LUMINOUS_CONDENSE_CYAN_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.solid(), ModFluids.STILL_LUMINOUS_CONDENSE_GRAY_WATER.get(), ModFluids.FLOWING_LUMINOUS_CONDENSE_GRAY_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.solid(), ModFluids.STILL_LUMINOUS_CONDENSE_GREEN_WATER.get(), ModFluids.FLOWING_LUMINOUS_CONDENSE_GREEN_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.solid(), ModFluids.STILL_LUMINOUS_CONDENSE_LIGHT_BLUE_WATER.get(), ModFluids.FLOWING_LUMINOUS_CONDENSE_LIGHT_BLUE_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.solid(), ModFluids.STILL_LUMINOUS_CONDENSE_LIGHT_GRAY_WATER.get(), ModFluids.FLOWING_LUMINOUS_CONDENSE_LIGHT_GRAY_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.solid(), ModFluids.STILL_LUMINOUS_CONDENSE_LIME_WATER.get(), ModFluids.FLOWING_LUMINOUS_CONDENSE_LIME_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.solid(), ModFluids.STILL_LUMINOUS_CONDENSE_MAGENTA_WATER.get(), ModFluids.FLOWING_LUMINOUS_CONDENSE_MAGENTA_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.solid(), ModFluids.STILL_LUMINOUS_CONDENSE_ORANGE_WATER.get(), ModFluids.FLOWING_LUMINOUS_CONDENSE_ORANGE_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.solid(), ModFluids.STILL_LUMINOUS_CONDENSE_PINK_WATER.get(), ModFluids.FLOWING_LUMINOUS_CONDENSE_PINK_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.solid(), ModFluids.STILL_LUMINOUS_CONDENSE_PURPLE_WATER.get(), ModFluids.FLOWING_LUMINOUS_CONDENSE_PURPLE_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.solid(), ModFluids.STILL_LUMINOUS_CONDENSE_RED_WATER.get(), ModFluids.FLOWING_LUMINOUS_CONDENSE_RED_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.solid(), ModFluids.STILL_LUMINOUS_CONDENSE_WHITE_WATER.get(), ModFluids.FLOWING_LUMINOUS_CONDENSE_WHITE_WATER.get());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderType.solid(), ModFluids.STILL_LUMINOUS_CONDENSE_YELLOW_WATER.get(), ModFluids.FLOWING_LUMINOUS_CONDENSE_YELLOW_WATER.get());

        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.MAGENTA_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.PURPLE_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.GREEN_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.YELLOW_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.LIME_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.PINK_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.RED_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.BLACK_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.BROWN_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.BLUE_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.CYAN_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.LIGHT_GRAY_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.GRAY_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.LIGHT_BLUE_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.ORANGE_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.WHITE_WATER_CAULDRON.get(), RenderType.translucent());

        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.CONDENSE_MAGENTA_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.CONDENSE_PURPLE_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.CONDENSE_GREEN_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.CONDENSE_YELLOW_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.CONDENSE_LIME_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.CONDENSE_PINK_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.CONDENSE_RED_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.CONDENSE_BLACK_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.CONDENSE_BROWN_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.CONDENSE_BLUE_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.CONDENSE_CYAN_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.CONDENSE_LIGHT_GRAY_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.CONDENSE_GRAY_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.CONDENSE_LIGHT_BLUE_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.CONDENSE_ORANGE_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.CONDENSE_WHITE_WATER_CAULDRON.get(), RenderType.translucent());

        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.LUMINOUS_MAGENTA_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.LUMINOUS_PURPLE_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.LUMINOUS_GREEN_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.LUMINOUS_YELLOW_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.LUMINOUS_LIME_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.LUMINOUS_PINK_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.LUMINOUS_RED_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.LUMINOUS_BLACK_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.LUMINOUS_BROWN_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.LUMINOUS_BLUE_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.LUMINOUS_CYAN_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.LUMINOUS_LIGHT_GRAY_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.LUMINOUS_GRAY_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.LUMINOUS_LIGHT_BLUE_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.LUMINOUS_ORANGE_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.LUMINOUS_WHITE_WATER_CAULDRON.get(), RenderType.translucent());

        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.LUMINOUS_CONDENSE_MAGENTA_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.LUMINOUS_CONDENSE_PURPLE_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.LUMINOUS_CONDENSE_GREEN_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.LUMINOUS_CONDENSE_YELLOW_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.LUMINOUS_CONDENSE_LIME_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.LUMINOUS_CONDENSE_PINK_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.LUMINOUS_CONDENSE_RED_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.LUMINOUS_CONDENSE_BLACK_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.LUMINOUS_CONDENSE_BROWN_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.LUMINOUS_CONDENSE_BLUE_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.LUMINOUS_CONDENSE_CYAN_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.LUMINOUS_CONDENSE_GRAY_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.LUMINOUS_CONDENSE_ORANGE_WATER_CAULDRON.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModCauldrons.LUMINOUS_CONDENSE_WHITE_WATER_CAULDRON.get(), RenderType.translucent());

    }
}