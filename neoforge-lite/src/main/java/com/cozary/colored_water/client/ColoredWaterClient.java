package com.cozary.colored_water.client;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.block.entity.WaterloggedColorBlockEntity;
import com.cozary.colored_water.fluids.ColorWaterFluid;
import com.cozary.colored_water.init.ModCauldrons;
import com.cozary.colored_water.init.ModFluidTypes;
import com.cozary.colored_water.init.ModFluids;
import com.cozary.colored_water.init.RegistryObject;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterFluidModelsEvent;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import org.joml.Vector4f;

import java.util.List;

public class ColoredWaterClient {

    private static final Identifier STILL_TEXTURE = Identifier.fromNamespaceAndPath(ColoredWater.MOD_ID, "block/water_still");
    private static final Identifier FLOWING_TEXTURE = Identifier.fromNamespaceAndPath(ColoredWater.MOD_ID, "block/water_flowing");
    private static final Identifier OVERLAY_TEXTURE = Identifier.withDefaultNamespace("block/water_overlay");

    private static final Material STILL_MATERIAL = new Material(STILL_TEXTURE, true);
    private static final Material FLOWING_MATERIAL = new Material(FLOWING_TEXTURE, true);
    private static final Material OVERLAY_MATERIAL = new Material(OVERLAY_TEXTURE, true);

    private static final IClientFluidTypeExtensions FLUID_CLIENT_EXTENSIONS = new IClientFluidTypeExtensions() {
        @Override
        public void renderOverlay(Minecraft minecraft, PoseStack poseStack, MultiBufferSource buffer) {
            ColoredWaterOverlayRenderer.renderOverlay(minecraft, poseStack, buffer);
        }

        @Override
        public void modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistanceChunks, float bossColorModifier, Vector4f defaultColor) {
            Vector4f customColor = ColoredWaterFog.getFogColor(camera, partialTick, level, defaultColor);
            if (customColor != null) {
                defaultColor.set(customColor.x(), customColor.y(), customColor.z(), customColor.w());
            }
        }

        @Override
        public void modifyFogRender(Camera camera, FogEnvironment fogEnvironment, float renderDistance, float partialTick, FogData fogData) {
            ColoredWaterFog.modifyFogRender(camera, (ClientLevel) camera.entity().level(), renderDistance, fogData);
        }
    };

    public static void onClientSetup(final FMLClientSetupEvent event) {
    }

    public static void registerBlockColors(RegisterColorHandlersEvent.BlockTintSources event) {
        for (Block cauldronBlock : ModCauldrons.getAllCauldronBlocks()) {
            int rgb = ColoredWaterUtil.getCauldronColor(cauldronBlock);
            int color = rgb != -1 ? ColoredWaterUtil.withAlpha(rgb, 255) : (ColoredWaterUtil.DEFAULT_COLOR | 0xFF000000);
            event.register(List.of(new BlockTintSource() {
                @Override
                public int color(BlockState blockState) {
                    return color;
                }

                @Override
                public int colorInWorld(BlockState blockState, BlockAndTintGetter blockAndTintGetter, BlockPos blockPosition) {
                    return color;
                }
            }), cauldronBlock);
        }
    }

    public static void registerFluidModels(RegisterFluidModelsEvent event) {
        for (RegistryObject<Fluid> registryObject : ModFluids.FLUIDS.getEntries()) {
            Fluid fluid = registryObject.get();
            if (fluid instanceof ColorWaterFluid.Source source) {
                Fluid stillFluid = source;
                Fluid flowingFluid = source.getFlowing();
                int rgbColor = source.getTintColor();
                int alphaValue = source.isCondensed() ? ColoredWaterUtil.CONDENSED_ALPHA : ColoredWaterUtil.DEFAULT_ALPHA;
                int defaultArgbColor = ColoredWaterUtil.withAlpha(rgbColor, alphaValue);

                event.register(
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
                        ),
                        stillFluid,
                        flowingFluid
                );
            }
        }
    }

    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        for (var entry : ModFluidTypes.FLUID_TYPES.getEntries()) {
            event.registerFluidType(FLUID_CLIENT_EXTENSIONS, entry.get());
        }
    }

}
