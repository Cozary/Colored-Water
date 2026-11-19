package com.cozary.colored_water.client;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.block.entity.ColoredWaterBlockEntity;
import com.cozary.colored_water.block.entity.ColoredWaterCauldronBlockEntity;
import com.cozary.colored_water.init.ModCauldrons;
import com.cozary.colored_water.init.ModFluids;
import com.cozary.colored_water.util.ColoredWaterUtil;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

@Environment(EnvType.CLIENT)
public class ColoredWaterClient implements ClientModInitializer {

    private static final Identifier STILL_TEXTURE = Identifier.fromNamespaceAndPath(ColoredWater.MOD_ID, "block/water_still");
    private static final Identifier FLOWING_TEXTURE = Identifier.fromNamespaceAndPath(ColoredWater.MOD_ID, "block/water_flowing");
    private static final Identifier OVERLAY_TEXTURE = Identifier.withDefaultNamespace("block/water_overlay");

    @Override
    public void onInitializeClient() {
        FluidRenderingRegistry.register(
                ModFluids.STILL_COLORED_WATER.get(),
                ModFluids.FLOWING_COLORED_WATER.get(),
                new FluidModel.Unbaked(
                        new Material(STILL_TEXTURE, true),
                        new Material(FLOWING_TEXTURE, true),
                        new Material(OVERLAY_TEXTURE, true),
                        new BlockTintSource() {
                            @Override
                            public int color(BlockState blockState) {
                                return ColoredWaterUtil.DEFAULT_ARGB_NORMAL;
                            }

                            @Override
                            public int colorInWorld(BlockState blockState, BlockAndTintGetter blockAndTintGetter, BlockPos blockPosition) {
                                if (blockAndTintGetter != null && blockPosition != null) {
                                    BlockEntity blockEntity = blockAndTintGetter.getBlockEntity(blockPosition);
                                    if (blockEntity instanceof ColoredWaterBlockEntity coloredWaterBlockEntity && coloredWaterBlockEntity.hasCustomProperties()) {
                                        int color = coloredWaterBlockEntity.getColor();
                                        int alpha = ColoredWaterUtil.getAlpha(color);
                                        if (alpha == 0) {
                                            alpha = coloredWaterBlockEntity.isCondensed() ? ColoredWaterUtil.CONDENSED_ALPHA : ColoredWaterUtil.DEFAULT_ALPHA;
                                            color = ColoredWaterUtil.withAlpha(color, alpha);
                                        }
                                        return color;
                                    }
                                }
                                return ColoredWaterUtil.DEFAULT_ARGB_NORMAL;
                            }
                        }
                )
        );

        BlockColorRegistry.register((blockState, blockAndTintGetter, blockPosition, colorCollector) -> {
            if (blockAndTintGetter != null && blockPosition != null) {
                BlockEntity blockEntity = blockAndTintGetter.getBlockEntity(blockPosition);
                if (blockEntity instanceof ColoredWaterCauldronBlockEntity cauldronBlockEntity) {
                    colorCollector.add(cauldronBlockEntity.getColor());
                    return;
                }
            }
            colorCollector.add(ColoredWaterUtil.DEFAULT_ARGB_NORMAL);
        }, ModCauldrons.COLORED_WATER_CAULDRON.get());
    }
}
