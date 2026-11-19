package com.cozary.colored_water.client;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.block.entity.WaterloggedColorBlockEntity;
import com.cozary.colored_water.fluids.ColorWaterFluid;
import com.cozary.colored_water.init.ModCauldrons;
import com.cozary.colored_water.init.ModFluids;
import com.cozary.colored_water.init.RegistryObject;
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
import net.minecraft.world.level.material.Fluid;

@Environment(EnvType.CLIENT)
public class ColoredWaterClient implements ClientModInitializer {

    private static final Identifier STILL_TEXTURE = Identifier.fromNamespaceAndPath(ColoredWater.MOD_ID, "block/water_still");
    private static final Identifier FLOWING_TEXTURE = Identifier.fromNamespaceAndPath(ColoredWater.MOD_ID, "block/water_flowing");
    private static final Identifier OVERLAY_TEXTURE = Identifier.withDefaultNamespace("block/water_overlay");

    private static final Material STILL_MATERIAL = new Material(STILL_TEXTURE, true);
    private static final Material FLOWING_MATERIAL = new Material(FLOWING_TEXTURE, true);
    private static final Material OVERLAY_MATERIAL = new Material(OVERLAY_TEXTURE, true);

    @Override
    public void onInitializeClient() {
        for (RegistryObject<Fluid> entry : ModFluids.FLUIDS.getEntries()) {
            Fluid fluid = entry.get();
            if (fluid instanceof ColorWaterFluid.Source source) {
                Fluid stillFluid = source;
                Fluid flowingFluid = source.getFlowing();
                int rgbColor = source.getTintColor();
                int alphaValue = source.isCondensed() ? ColoredWaterUtil.CONDENSED_ALPHA : ColoredWaterUtil.DEFAULT_ALPHA;
                int defaultArgbColor = ColoredWaterUtil.withAlpha(rgbColor, alphaValue);

                FluidRenderingRegistry.register(
                        stillFluid,
                        flowingFluid,
                        new FluidModel.Unbaked(
                                STILL_MATERIAL,
                                FLOWING_MATERIAL,
                                OVERLAY_MATERIAL,
                                new BlockTintSource() {
                                    @Override
                                    public int color(BlockState blockState) {
                                        return defaultArgbColor;
                                    }

                                    @Override
                                    public int colorInWorld(BlockState blockState, BlockAndTintGetter blockAndTintGetter, BlockPos blockPosition) {
                                        if (blockAndTintGetter != null && blockPosition != null) {
                                            BlockEntity blockEntity = blockAndTintGetter.getBlockEntity(blockPosition);
                                            if (blockEntity instanceof WaterloggedColorBlockEntity waterloggedBlockEntity && waterloggedBlockEntity.hasFluid()) {
                                                ColorWaterFluid waterloggedFluid = waterloggedBlockEntity.getFluid();
                                                int waterloggedRgb = waterloggedFluid.getTintColor();
                                                int waterloggedAlpha = waterloggedFluid.isCondensed() ? ColoredWaterUtil.CONDENSED_ALPHA : ColoredWaterUtil.DEFAULT_ALPHA;
                                                return ColoredWaterUtil.withAlpha(waterloggedRgb, waterloggedAlpha);
                                            }
                                        }
                                        return defaultArgbColor;
                                    }
                                }
                        )
                );
            }
        }

        BlockColorRegistry.register((blockState, blockAndTintGetter, blockPosition, colorCollector) -> {
            int cauldronRgb = ColoredWaterUtil.getCauldronColor(blockState.getBlock());
            if (cauldronRgb != -1) {
                colorCollector.add(ColoredWaterUtil.withAlpha(cauldronRgb, 255));
            } else {
                colorCollector.add(ColoredWaterUtil.DEFAULT_COLOR | 0xFF000000);
            }
        }, ModCauldrons.getAllCauldronBlocks());
    }
}
