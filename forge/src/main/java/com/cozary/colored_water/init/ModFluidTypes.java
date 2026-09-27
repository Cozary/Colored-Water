package com.cozary.colored_water.init;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.block.entity.ColoredWaterBlockEntity;
import com.cozary.colored_water.client.ColoredWaterFog;
import com.cozary.colored_water.client.ColoredWaterOverlayRenderer;
import com.cozary.colored_water.util.ColoredWaterUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.FogType;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.function.Consumer;

public class ModFluidTypes {
    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, ColoredWater.MOD_ID);

    public static final RegistryObject<FluidType> COLORED_WATER_TYPE = FLUID_TYPES.register("colored_water_type",
            () -> new FluidType(FluidType.Properties.create()
                    .descriptionId("block.colored_water.colored_water_block")
                    .fallDistanceModifier(0F)
                    .canExtinguish(true)
                    .supportsBoating(true)
                    .canHydrate(true)
                    .canSwim(true)
                    .canDrown(true)
                    .canConvertToSource(true)) {

                @Override
                public boolean canDrownIn(LivingEntity entity) {
                    return !entity.canBreatheUnderwater();
                }

                @Override
                public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                    consumer.accept(new IClientFluidTypeExtensions() {
                        private static final Identifier STILL_TEXTURE = Identifier.fromNamespaceAndPath(ColoredWater.MOD_ID, "block/water_still");
                        private static final Identifier FLOWING_TEXTURE = Identifier.fromNamespaceAndPath(ColoredWater.MOD_ID, "block/water_flowing");

                        @Override
                        public Identifier getStillTexture() {
                            return STILL_TEXTURE;
                        }

                        @Override
                        public Identifier getFlowingTexture() {
                            return FLOWING_TEXTURE;
                        }

                        @Override
                        public int getTintColor(FluidState state, BlockAndTintGetter getter, BlockPos pos) {
                            if (getter != null && pos != null) {
                                BlockEntity blockEntity = getter.getBlockEntity(pos);
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

                        @Override
                        public void renderOverlay(Minecraft mc, PoseStack poseStack, MultiBufferSource buffer) {
                            ColoredWaterOverlayRenderer.renderOverlay(mc, poseStack, buffer);
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

                    });
                }
            });
}
