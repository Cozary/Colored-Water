package com.cozary.colored_water.client;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.block.entity.ColoredWaterBlockEntity;
import com.cozary.colored_water.block.entity.ColoredWaterCauldronBlockEntity;
import com.cozary.colored_water.init.ModCauldrons;
import com.cozary.colored_water.init.ModFluidTypes;
import com.cozary.colored_water.init.ModFluids;
import com.cozary.colored_water.util.ColoredWaterUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.fog.environment.FogEnvironment;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterFluidModelsEvent;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import org.joml.Vector4f;

import java.util.List;

public class ColoredWaterClient {

    private static final Identifier STILL_TEXTURE = Identifier.fromNamespaceAndPath(ColoredWater.MOD_ID,
            "block/water_still");
    private static final Identifier FLOWING_TEXTURE = Identifier.fromNamespaceAndPath(ColoredWater.MOD_ID,
            "block/water_flowing");
    private static final Identifier OVERLAY_TEXTURE = Identifier.withDefaultNamespace("block/water_overlay");

    public static void onClientSetup(FMLClientSetupEvent event) {
    }

    public static void registerBlockColors(RegisterColorHandlersEvent.BlockTintSources event) {
        event.register(List.of(new BlockTintSource() {
            @Override
            public int color(BlockState blockState) {
                return ColoredWaterUtil.DEFAULT_ARGB_NORMAL;
            }

            @Override
            public int colorInWorld(BlockState blockState, BlockAndTintGetter blockAndTintGetter,
                    BlockPos blockPosition) {
                if (blockAndTintGetter != null && blockPosition != null) {
                    BlockEntity blockEntity = blockAndTintGetter.getBlockEntity(blockPosition);
                    if (blockEntity instanceof ColoredWaterCauldronBlockEntity cauldronBlockEntity) {
                        return cauldronBlockEntity.getColor();
                    }
                }
                return ColoredWaterUtil.DEFAULT_ARGB_NORMAL;
            }
        }), ModCauldrons.COLORED_WATER_CAULDRON.get());
    }

    public static void registerFluidModels(RegisterFluidModelsEvent event) {
        event.register(
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
                            public int colorInWorld(BlockState blockState, BlockAndTintGetter blockAndTintGetter,
                                    BlockPos blockPosition) {
                                if (blockAndTintGetter != null && blockPosition != null) {
                                    BlockEntity blockEntity = blockAndTintGetter.getBlockEntity(blockPosition);
                                    if (blockEntity instanceof ColoredWaterBlockEntity coloredWaterBlockEntity
                                            && coloredWaterBlockEntity.hasCustomProperties()) {
                                        int color = coloredWaterBlockEntity.getColor();
                                        int alpha = ColoredWaterUtil.getAlpha(color);
                                        if (alpha == 0) {
                                            alpha = coloredWaterBlockEntity.isCondensed()
                                                    ? ColoredWaterUtil.CONDENSED_ALPHA
                                                    : ColoredWaterUtil.DEFAULT_ALPHA;
                                            color = ColoredWaterUtil.withAlpha(color, alpha);
                                        }
                                        return color;
                                    }
                                }
                                return ColoredWaterUtil.DEFAULT_ARGB_NORMAL;
                            }
                        }),
                ModFluids.STILL_COLORED_WATER.get(),
                ModFluids.FLOWING_COLORED_WATER.get());
    }

    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerFluidType(new IClientFluidTypeExtensions() {
            @Override
            public void renderOverlay(Minecraft minecraft, PoseStack poseStack, MultiBufferSource buffer) {
                ColoredWaterOverlayRenderer.renderOverlay(minecraft, poseStack, buffer);
            }

            @Override
            public void modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistanceChunks,
                    float bossColorModifier, Vector4f defaultColor) {
                Vector4f customColor = ColoredWaterFog.getFogColor(camera, partialTick, level, defaultColor);
                if (customColor != null) {
                    defaultColor.set(customColor.x(), customColor.y(), customColor.z(), customColor.w());
                }
            }

            @Override
            public void modifyFogRender(Camera camera, FogEnvironment fogEnvironment, float renderDistance,
                    float partialTick, FogData fogData) {
                ColoredWaterFog.modifyFogRender(camera, (ClientLevel) camera.entity().level(), renderDistance, fogData);
            }
        }, ModFluidTypes.COLORED_WATER_TYPE.get());
    }
}
