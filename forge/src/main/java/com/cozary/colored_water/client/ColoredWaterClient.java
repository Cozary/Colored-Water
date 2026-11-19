package com.cozary.colored_water.client;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.block.entity.ColoredWaterBlockEntity;
import com.cozary.colored_water.block.entity.ColoredWaterCauldronBlockEntity;
import com.cozary.colored_water.init.ModCauldrons;
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
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FogType;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.List;

public class ColoredWaterClient {

    private static final Identifier STILL_TEXTURE = Identifier.fromNamespaceAndPath(ColoredWater.MOD_ID, "block/water_still");
    private static final Identifier FLOWING_TEXTURE = Identifier.fromNamespaceAndPath(ColoredWater.MOD_ID, "block/water_flowing");
    private static final Identifier OVERLAY_TEXTURE = Identifier.withDefaultNamespace("block/water_overlay");

    public static void init() {
        RegisterColorHandlersEvent.Block.BUS.addListener(ColoredWaterClient::registerBlockColors);
        RegisterParticleProvidersEvent.BUS.addListener(ParticleRegister::registerFactories);
        ModelEvent.BakeFluidModels.BUS.addListener(ColoredWaterClient::bakeFluidModels);
    }

    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register(List.of(new BlockTintSource() {
            @Override
            public int color(BlockState blockState) {
                return ColoredWaterUtil.DEFAULT_ARGB_NORMAL;
            }

            @Override
            public int colorInWorld(BlockState blockState, BlockAndTintGetter blockAndTintGetter, BlockPos blockPosition) {
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

    public static void bakeFluidModels(ModelEvent.BakeFluidModels event) {
        FluidModel.Unbaked unbaked = new FluidModel.Unbaked(
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
        );
        FluidModel model = unbaked.bake(event.materials(), () -> "colored_water");
        event.register(ModFluids.STILL_COLORED_WATER.get(), model);
        event.register(ModFluids.FLOWING_COLORED_WATER.get(), model);
    }

    public static IClientFluidTypeExtensions createFluidClientExtensions() {
        return new IClientFluidTypeExtensions() {
            @Override
            public Identifier getStillTexture() {
                return STILL_TEXTURE;
            }

            @Override
            public Identifier getFlowingTexture() {
                return FLOWING_TEXTURE;
            }

            @Override
            public void renderOverlay(Minecraft minecraftInstance, PoseStack poseStack, MultiBufferSource buffer) {
                ColoredWaterOverlayRenderer.renderOverlay(minecraftInstance, poseStack, buffer);
            }

            @Override
            public void modifyFogRender(Camera camera, FogType type, float partialTick, FogData data, Vector4f color) {
                ClientLevel level = (ClientLevel) camera.entity().level();
                float renderDistance = Minecraft.getInstance().options.getEffectiveRenderDistance();
                if (ColoredWaterFog.modifyFogRender(camera, level, renderDistance, data)) {
                    Vector4f customColor = ColoredWaterFog.getFogColor(camera, partialTick, level, color);
                    if (customColor != null) {
                        color.set(customColor.x, customColor.y, customColor.z, color.w);
                    }
                }
            }

            @Override
            public Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
                Vector4f customColor = ColoredWaterFog.getFogColor(camera, partialTick, level, new Vector4f(fluidFogColor.x(), fluidFogColor.y(), fluidFogColor.z(), 1.0F));
                if (customColor != null) {
                    return new Vector3f(customColor.x, customColor.y, customColor.z);
                }
                return fluidFogColor;
            }
        };
    }

    public static void onClientSetup(final FMLClientSetupEvent event) {
    }
}
