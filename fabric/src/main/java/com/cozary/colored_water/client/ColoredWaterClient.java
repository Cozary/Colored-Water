package com.cozary.colored_water.client;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.block.entity.ColoredWaterBlockEntity;
import com.cozary.colored_water.init.ModBlocks;
import com.cozary.colored_water.init.ModFluids;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.material.FluidState;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class ColoredWaterClient implements ClientModInitializer {

    private static final Identifier STILL_TEXTURE = Identifier.fromNamespaceAndPath(ColoredWater.MOD_ID, "block/water_still");
    private static final Identifier FLOWING_TEXTURE = Identifier.fromNamespaceAndPath(ColoredWater.MOD_ID, "block/water_flowing");

    @Override
    public void onInitializeClient() {
        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_COLORED_WATER.get(),
                ModFluids.FLOWING_COLORED_WATER.get(),
                new SimpleFluidRenderHandler(STILL_TEXTURE, FLOWING_TEXTURE, 0x3F76E4) {
                    @Override
                    public int getFluidColor(@Nullable BlockAndTintGetter view, @Nullable BlockPos pos, FluidState state) {
                        if (view != null && pos != null) {
                            BlockEntity be = view.getBlockEntity(pos);
                            if (be instanceof ColoredWaterBlockEntity coloredBe) {
                                int color = coloredBe.getColor();
                                int alpha = (color >> 24) & 0xFF;
                                if (alpha == 0) {
                                    alpha = coloredBe.isCondensed() ? 255 : 180;
                                    color = (alpha << 24) | (color & 0x00FFFFFF);
                                }
                                return color;
                            }
                        }
                        return 0xB43F76E4;
                    }
                }
        );

        BlockRenderLayerMap.putBlocks(ChunkSectionLayer.TRANSLUCENT, ModBlocks.COLORED_WATER_BLOCK.get());
        BlockRenderLayerMap.putFluids(ChunkSectionLayer.TRANSLUCENT, ModFluids.STILL_COLORED_WATER.get(), ModFluids.FLOWING_COLORED_WATER.get());
    }
}