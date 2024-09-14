package com.cozary.colored_water.fluids;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.items.ModItems;
import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

import java.util.function.Consumer;

public class ModFluidsLight {

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, ColoredWater.MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, ColoredWater.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ColoredWater.MOD_ID);

    public static final RegistryObject<FluidType> LUMINOUS_MAGENTA_FLUID_TYPE = FLUID_TYPES.register("luminous_magenta_fluid", () ->
            new FluidType(FluidType.Properties.create()
                    .canExtinguish(true)
                    .canConvertToSource(true)
                    .supportsBoating(true)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                    .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
                    .canHydrate(true)
                    .canSwim(true)
                    .canDrown(false)
                    .pathType(BlockPathTypes.WATER)
                    .density(1024)
                    .viscosity(1024)
            ) {
                @Override
                public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                    consumer.accept(new IClientFluidTypeExtensions() {
                        private static final ResourceLocation STILL = new ResourceLocation("block/water_still"),
                                FLOW = new ResourceLocation("block/water_flow"),
                                OVERLAY = new ResourceLocation("block/water_overlay"),
                                VIEW_OVERLAY = new ResourceLocation("textures/block/water_overlay.png");

                        @Override
                        public ResourceLocation getStillTexture() {
                            return STILL;
                        }

                        @Override
                        public ResourceLocation getFlowingTexture() {
                            return FLOW;
                        }

                        @Override
                        public ResourceLocation getOverlayTexture() {
                            return OVERLAY;
                        }

                        @Override
                        public ResourceLocation getRenderOverlayTexture(Minecraft mc) {
                            return VIEW_OVERLAY;
                        }

                        @Override
                        public int getTintColor() {
                            return 0xffC74EBD;
                        }

                        @Override
                        public @NotNull Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
                            int color = this.getTintColor();
                            return new Vector3f((color >> 16 & 0xFF) / 255F, (color >> 8 & 0xFF) / 255F, (color & 0xFF) / 255F);
                        }

                        @Override
                        public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape) {
                            nearDistance = -8F;
                            farDistance = 24F;

                            if (farDistance > renderDistance) {
                                farDistance = renderDistance;
                                shape = FogShape.CYLINDER;
                            }

                            RenderSystem.setShaderFogStart(nearDistance);
                            RenderSystem.setShaderFogEnd(farDistance);
                            RenderSystem.setShaderFogShape(shape);
                        }
                    });
                }
            });
    public static final RegistryObject<FluidType> LUMINOUS_PURPLE_FLUID_TYPE = FLUID_TYPES.register("luminous_purple_fluid", () ->
            new FluidType(FluidType.Properties.create()
                    .canExtinguish(true)
                    .canConvertToSource(true)
                    .supportsBoating(true)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                    .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
                    .canHydrate(true)
                    .canSwim(true)
                    .canDrown(false)
                    .pathType(BlockPathTypes.WATER)
                    .density(1024)
                    .viscosity(1024)
            ) {

                @Override
                public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                    consumer.accept(new IClientFluidTypeExtensions() {
                        private static final ResourceLocation STILL = new ResourceLocation("block/water_still"),
                                FLOW = new ResourceLocation("block/water_flow"),
                                OVERLAY = new ResourceLocation("block/water_overlay"),
                                VIEW_OVERLAY = new ResourceLocation("textures/block/water_overlay.png");


                        @Override
                        public ResourceLocation getStillTexture() {
                            return STILL;
                        }

                        @Override
                        public ResourceLocation getFlowingTexture() {
                            return FLOW;
                        }

                        @Override
                        public ResourceLocation getOverlayTexture() {
                            return OVERLAY;
                        }

                        @Override
                        public ResourceLocation getRenderOverlayTexture(Minecraft mc) {
                            return VIEW_OVERLAY;
                        }

                        @Override
                        public int getTintColor() {
                            return 0xff8932B8;
                        }

                        @Override
                        public @NotNull Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
                            int color = this.getTintColor();
                            return new Vector3f((color >> 16 & 0xFF) / 255F, (color >> 8 & 0xFF) / 255F, (color & 0xFF) / 255F);
                        }

                        @Override
                        public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape) {
                            nearDistance = -8F;
                            farDistance = 24F;

                            if (farDistance > renderDistance) {
                                farDistance = renderDistance;
                                shape = FogShape.CYLINDER;
                            }

                            RenderSystem.setShaderFogStart(nearDistance);
                            RenderSystem.setShaderFogEnd(farDistance);
                            RenderSystem.setShaderFogShape(shape);
                        }
                    });
                }
            });
    public static final RegistryObject<FluidType> LUMINOUS_GREEN_FLUID_TYPE = FLUID_TYPES.register("luminous_green_fluid", () ->
            new FluidType(FluidType.Properties.create()
                    .canExtinguish(true)
                    .canConvertToSource(true)
                    .supportsBoating(true)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                    .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
                    .canHydrate(true)
                    .canSwim(true)
                    .canDrown(false)
                    .pathType(BlockPathTypes.WATER)
                    .density(1024)
                    .viscosity(1024)
            ) {
                @Override
                public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                    consumer.accept(new IClientFluidTypeExtensions() {
                        private static final ResourceLocation STILL = new ResourceLocation("block/water_still"),
                                FLOW = new ResourceLocation("block/water_flow"),
                                OVERLAY = new ResourceLocation("block/water_overlay"),
                                VIEW_OVERLAY = new ResourceLocation("textures/block/water_overlay.png");

                        @Override
                        public ResourceLocation getStillTexture() {
                            return STILL;
                        }

                        @Override
                        public ResourceLocation getFlowingTexture() {
                            return FLOW;
                        }

                        @Override
                        public ResourceLocation getOverlayTexture() {
                            return OVERLAY;
                        }

                        @Override
                        public ResourceLocation getRenderOverlayTexture(Minecraft mc) {
                            return VIEW_OVERLAY;
                        }

                        @Override
                        public int getTintColor() {
                            return 0xff5E7C16;
                        }

                        @Override
                        public @NotNull Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
                            int color = this.getTintColor();
                            return new Vector3f((color >> 16 & 0xFF) / 255F, (color >> 8 & 0xFF) / 255F, (color & 0xFF) / 255F);
                        }

                        @Override
                        public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape) {
                            nearDistance = -8F;
                            farDistance = 24F;

                            if (farDistance > renderDistance) {
                                farDistance = renderDistance;
                                shape = FogShape.CYLINDER;
                            }

                            RenderSystem.setShaderFogStart(nearDistance);
                            RenderSystem.setShaderFogEnd(farDistance);
                            RenderSystem.setShaderFogShape(shape);
                        }
                    });
                }
            });
    public static final RegistryObject<FluidType> LUMINOUS_YELLOW_FLUID_TYPE = FLUID_TYPES.register("luminous_yellow_fluid", () ->
            new FluidType(FluidType.Properties.create()
                    .canExtinguish(true)
                    .canConvertToSource(true)
                    .supportsBoating(true)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                    .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
                    .canHydrate(true)
                    .canSwim(true)
                    .canDrown(false)
                    .pathType(BlockPathTypes.WATER)
                    .density(1024)
                    .viscosity(1024)
            ) {
                @Override
                public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                    consumer.accept(new IClientFluidTypeExtensions() {
                        private static final ResourceLocation STILL = new ResourceLocation("block/water_still"),
                                FLOW = new ResourceLocation("block/water_flow"),
                                OVERLAY = new ResourceLocation("block/water_overlay"),
                                VIEW_OVERLAY = new ResourceLocation("textures/block/water_overlay.png");

                        @Override
                        public ResourceLocation getStillTexture() {
                            return STILL;
                        }

                        @Override
                        public ResourceLocation getFlowingTexture() {
                            return FLOW;
                        }

                        @Override
                        public ResourceLocation getOverlayTexture() {
                            return OVERLAY;
                        }

                        @Override
                        public ResourceLocation getRenderOverlayTexture(Minecraft mc) {
                            return VIEW_OVERLAY;
                        }

                        @Override
                        public int getTintColor() {
                            return 0xffFED83D;
                        }

                        @Override
                        public @NotNull Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
                            int color = this.getTintColor();
                            return new Vector3f((color >> 16 & 0xFF) / 255F, (color >> 8 & 0xFF) / 255F, (color & 0xFF) / 255F);
                        }

                        @Override
                        public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape) {
                            nearDistance = -8F;
                            farDistance = 24F;

                            if (farDistance > renderDistance) {
                                farDistance = renderDistance;
                                shape = FogShape.CYLINDER;
                            }

                            RenderSystem.setShaderFogStart(nearDistance);
                            RenderSystem.setShaderFogEnd(farDistance);
                            RenderSystem.setShaderFogShape(shape);
                        }
                    });
                }
            });
    public static final RegistryObject<FluidType> LUMINOUS_LIME_FLUID_TYPE = FLUID_TYPES.register("luminous_lime_fluid", () ->
            new FluidType(FluidType.Properties.create()
                    .canExtinguish(true)
                    .canConvertToSource(true)
                    .supportsBoating(true)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                    .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
                    .canHydrate(true)
                    .canSwim(true)
                    .canDrown(false)
                    .pathType(BlockPathTypes.WATER)
                    .density(1024)
                    .viscosity(1024)
            ) {
                @Override
                public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                    consumer.accept(new IClientFluidTypeExtensions() {
                        private static final ResourceLocation STILL = new ResourceLocation("block/water_still"),
                                FLOW = new ResourceLocation("block/water_flow"),
                                OVERLAY = new ResourceLocation("block/water_overlay"),
                                VIEW_OVERLAY = new ResourceLocation("textures/block/water_overlay.png");

                        @Override
                        public ResourceLocation getStillTexture() {
                            return STILL;
                        }

                        @Override
                        public ResourceLocation getFlowingTexture() {
                            return FLOW;
                        }

                        @Override
                        public ResourceLocation getOverlayTexture() {
                            return OVERLAY;
                        }

                        @Override
                        public ResourceLocation getRenderOverlayTexture(Minecraft mc) {
                            return VIEW_OVERLAY;
                        }

                        @Override
                        public int getTintColor() {
                            return 0xff80C71F;
                        }

                        @Override
                        public @NotNull Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
                            int color = this.getTintColor();
                            return new Vector3f((color >> 16 & 0xFF) / 255F, (color >> 8 & 0xFF) / 255F, (color & 0xFF) / 255F);
                        }

                        @Override
                        public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape) {
                            nearDistance = -8F;
                            farDistance = 24F;

                            if (farDistance > renderDistance) {
                                farDistance = renderDistance;
                                shape = FogShape.CYLINDER;
                            }

                            RenderSystem.setShaderFogStart(nearDistance);
                            RenderSystem.setShaderFogEnd(farDistance);
                            RenderSystem.setShaderFogShape(shape);
                        }
                    });
                }
            });
    public static final RegistryObject<FluidType> LUMINOUS_PINK_FLUID_TYPE = FLUID_TYPES.register("luminous_pink_fluid", () ->
            new FluidType(FluidType.Properties.create()
                    .canExtinguish(true)
                    .canConvertToSource(true)
                    .supportsBoating(true)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                    .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
                    .canHydrate(true)
                    .canSwim(true)
                    .canDrown(false)
                    .pathType(BlockPathTypes.WATER)
                    .density(1024)
                    .viscosity(1024)
            ) {
                @Override
                public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                    consumer.accept(new IClientFluidTypeExtensions() {
                        private static final ResourceLocation STILL = new ResourceLocation("block/water_still"),
                                FLOW = new ResourceLocation("block/water_flow"),
                                OVERLAY = new ResourceLocation("block/water_overlay"),
                                VIEW_OVERLAY = new ResourceLocation("textures/block/water_overlay.png");

                        @Override
                        public ResourceLocation getStillTexture() {
                            return STILL;
                        }

                        @Override
                        public ResourceLocation getFlowingTexture() {
                            return FLOW;
                        }

                        @Override
                        public ResourceLocation getOverlayTexture() {
                            return OVERLAY;
                        }

                        @Override
                        public ResourceLocation getRenderOverlayTexture(Minecraft mc) {
                            return VIEW_OVERLAY;
                        }

                        @Override
                        public int getTintColor() {
                            return 0xffF38BAA;
                        }

                        @Override
                        public @NotNull Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
                            int color = this.getTintColor();
                            return new Vector3f((color >> 16 & 0xFF) / 255F, (color >> 8 & 0xFF) / 255F, (color & 0xFF) / 255F);
                        }

                        @Override
                        public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape) {
                            nearDistance = -8F;
                            farDistance = 24F;

                            if (farDistance > renderDistance) {
                                farDistance = renderDistance;
                                shape = FogShape.CYLINDER;
                            }

                            RenderSystem.setShaderFogStart(nearDistance);
                            RenderSystem.setShaderFogEnd(farDistance);
                            RenderSystem.setShaderFogShape(shape);
                        }
                    });
                }
            });
    public static final RegistryObject<FluidType> LUMINOUS_RED_FLUID_TYPE = FLUID_TYPES.register("luminous_red_fluid", () ->
            new FluidType(FluidType.Properties.create()
                    .canExtinguish(true)
                    .canConvertToSource(true)
                    .supportsBoating(true)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                    .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
                    .canHydrate(true)
                    .canSwim(true)
                    .canDrown(false)
                    .pathType(BlockPathTypes.WATER)
                    .density(1024)
                    .viscosity(1024)
            ) {
                @Override
                public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                    consumer.accept(new IClientFluidTypeExtensions() {
                        private static final ResourceLocation STILL = new ResourceLocation("block/water_still"),
                                FLOW = new ResourceLocation("block/water_flow"),
                                OVERLAY = new ResourceLocation("block/water_overlay"),
                                VIEW_OVERLAY = new ResourceLocation("textures/block/water_overlay.png");

                        @Override
                        public ResourceLocation getStillTexture() {
                            return STILL;
                        }

                        @Override
                        public ResourceLocation getFlowingTexture() {
                            return FLOW;
                        }

                        @Override
                        public ResourceLocation getOverlayTexture() {
                            return OVERLAY;
                        }

                        @Override
                        public ResourceLocation getRenderOverlayTexture(Minecraft mc) {
                            return VIEW_OVERLAY;
                        }

                        @Override
                        public int getTintColor() {
                            return 0xffB02E26;
                        }

                        @Override
                        public @NotNull Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
                            int color = this.getTintColor();
                            return new Vector3f((color >> 16 & 0xFF) / 255F, (color >> 8 & 0xFF) / 255F, (color & 0xFF) / 255F);
                        }

                        @Override
                        public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape) {
                            nearDistance = -8F;
                            farDistance = 24F;

                            if (farDistance > renderDistance) {
                                farDistance = renderDistance;
                                shape = FogShape.CYLINDER;
                            }

                            RenderSystem.setShaderFogStart(nearDistance);
                            RenderSystem.setShaderFogEnd(farDistance);
                            RenderSystem.setShaderFogShape(shape);
                        }
                    });
                }
            });
    public static final RegistryObject<FluidType> LUMINOUS_BLACK_FLUID_TYPE = FLUID_TYPES.register("luminous_black_fluid", () ->
            new FluidType(FluidType.Properties.create()
                    .canExtinguish(true)
                    .canConvertToSource(true)
                    .supportsBoating(true)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                    .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
                    .canHydrate(true)
                    .canSwim(true)
                    .canDrown(false)
                    .pathType(BlockPathTypes.WATER)
                    .density(1024)
                    .viscosity(1024)
            ) {
                @Override
                public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                    consumer.accept(new IClientFluidTypeExtensions() {
                        private static final ResourceLocation STILL = new ResourceLocation("block/water_still"),
                                FLOW = new ResourceLocation("block/water_flow"),
                                OVERLAY = new ResourceLocation("block/water_overlay"),
                                VIEW_OVERLAY = new ResourceLocation("textures/block/water_overlay.png");

                        @Override
                        public ResourceLocation getStillTexture() {
                            return STILL;
                        }

                        @Override
                        public ResourceLocation getFlowingTexture() {
                            return FLOW;
                        }

                        @Override
                        public ResourceLocation getOverlayTexture() {
                            return OVERLAY;
                        }

                        @Override
                        public ResourceLocation getRenderOverlayTexture(Minecraft mc) {
                            return VIEW_OVERLAY;
                        }

                        @Override
                        public int getTintColor() {
                            return 0xff1D1D21;
                        }

                        @Override
                        public @NotNull Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
                            int color = this.getTintColor();
                            return new Vector3f((color >> 16 & 0xFF) / 255F, (color >> 8 & 0xFF) / 255F, (color & 0xFF) / 255F);
                        }

                        @Override
                        public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape) {
                            nearDistance = -8F;
                            farDistance = 24F;

                            if (farDistance > renderDistance) {
                                farDistance = renderDistance;
                                shape = FogShape.CYLINDER;
                            }

                            RenderSystem.setShaderFogStart(nearDistance);
                            RenderSystem.setShaderFogEnd(farDistance);
                            RenderSystem.setShaderFogShape(shape);
                        }
                    });
                }
            });
    public static final RegistryObject<FluidType> LUMINOUS_BROWN_FLUID_TYPE = FLUID_TYPES.register("luminous_brown_fluid", () ->
            new FluidType(FluidType.Properties.create()
                    .canExtinguish(true)
                    .canConvertToSource(true)
                    .supportsBoating(true)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                    .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
                    .canHydrate(true)
                    .canSwim(true)
                    .canDrown(false)
                    .pathType(BlockPathTypes.WATER)
                    .density(1024)
                    .viscosity(1024)
            ) {
                @Override
                public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                    consumer.accept(new IClientFluidTypeExtensions() {
                        private static final ResourceLocation STILL = new ResourceLocation("block/water_still"),
                                FLOW = new ResourceLocation("block/water_flow"),
                                OVERLAY = new ResourceLocation("block/water_overlay"),
                                VIEW_OVERLAY = new ResourceLocation("textures/block/water_overlay.png");

                        @Override
                        public ResourceLocation getStillTexture() {
                            return STILL;
                        }

                        @Override
                        public ResourceLocation getFlowingTexture() {
                            return FLOW;
                        }

                        @Override
                        public ResourceLocation getOverlayTexture() {
                            return OVERLAY;
                        }

                        @Override
                        public ResourceLocation getRenderOverlayTexture(Minecraft mc) {
                            return VIEW_OVERLAY;
                        }

                        @Override
                        public int getTintColor() {
                            return 0xff835432;
                        }

                        @Override
                        public @NotNull Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
                            int color = this.getTintColor();
                            return new Vector3f((color >> 16 & 0xFF) / 255F, (color >> 8 & 0xFF) / 255F, (color & 0xFF) / 255F);
                        }

                        @Override
                        public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape) {
                            nearDistance = -8F;
                            farDistance = 24F;

                            if (farDistance > renderDistance) {
                                farDistance = renderDistance;
                                shape = FogShape.CYLINDER;
                            }

                            RenderSystem.setShaderFogStart(nearDistance);
                            RenderSystem.setShaderFogEnd(farDistance);
                            RenderSystem.setShaderFogShape(shape);
                        }
                    });
                }
            });
    public static final RegistryObject<FluidType> LUMINOUS_BLUE_FLUID_TYPE = FLUID_TYPES.register("luminous_blue_fluid", () ->
            new FluidType(FluidType.Properties.create()
                    .canExtinguish(true)
                    .canConvertToSource(true)
                    .supportsBoating(true)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                    .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
                    .canHydrate(true)
                    .canSwim(true)
                    .canDrown(false)
                    .pathType(BlockPathTypes.WATER)
                    .density(1024)
                    .viscosity(1024)
            ) {
                @Override
                public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                    consumer.accept(new IClientFluidTypeExtensions() {
                        private static final ResourceLocation STILL = new ResourceLocation("block/water_still"),
                                FLOW = new ResourceLocation("block/water_flow"),
                                OVERLAY = new ResourceLocation("block/water_overlay"),
                                VIEW_OVERLAY = new ResourceLocation("textures/block/water_overlay.png");

                        @Override
                        public ResourceLocation getStillTexture() {
                            return STILL;
                        }

                        @Override
                        public ResourceLocation getFlowingTexture() {
                            return FLOW;
                        }

                        @Override
                        public ResourceLocation getOverlayTexture() {
                            return OVERLAY;
                        }

                        @Override
                        public ResourceLocation getRenderOverlayTexture(Minecraft mc) {
                            return VIEW_OVERLAY;
                        }

                        @Override
                        public int getTintColor() {
                            return 0xff3C44AA;
                        }

                        @Override
                        public @NotNull Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
                            int color = this.getTintColor();
                            return new Vector3f((color >> 16 & 0xFF) / 255F, (color >> 8 & 0xFF) / 255F, (color & 0xFF) / 255F);
                        }

                        @Override
                        public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape) {
                            nearDistance = -8F;
                            farDistance = 24F;

                            if (farDistance > renderDistance) {
                                farDistance = renderDistance;
                                shape = FogShape.CYLINDER;
                            }

                            RenderSystem.setShaderFogStart(nearDistance);
                            RenderSystem.setShaderFogEnd(farDistance);
                            RenderSystem.setShaderFogShape(shape);
                        }
                    });
                }
            });
    public static final RegistryObject<FluidType> LUMINOUS_CYAN_FLUID_TYPE = FLUID_TYPES.register("luminous_cyan_fluid", () ->
            new FluidType(FluidType.Properties.create()
                    .canExtinguish(true)
                    .canConvertToSource(true)
                    .supportsBoating(true)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                    .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
                    .canHydrate(true)
                    .canSwim(true)
                    .canDrown(false)
                    .pathType(BlockPathTypes.WATER)
                    .density(1024)
                    .viscosity(1024)
            ) {
                @Override
                public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                    consumer.accept(new IClientFluidTypeExtensions() {
                        private static final ResourceLocation STILL = new ResourceLocation("block/water_still"),
                                FLOW = new ResourceLocation("block/water_flow"),
                                OVERLAY = new ResourceLocation("block/water_overlay"),
                                VIEW_OVERLAY = new ResourceLocation("textures/block/water_overlay.png");

                        @Override
                        public ResourceLocation getStillTexture() {
                            return STILL;
                        }

                        @Override
                        public ResourceLocation getFlowingTexture() {
                            return FLOW;
                        }

                        @Override
                        public ResourceLocation getOverlayTexture() {
                            return OVERLAY;
                        }

                        @Override
                        public ResourceLocation getRenderOverlayTexture(Minecraft mc) {
                            return VIEW_OVERLAY;
                        }

                        @Override
                        public int getTintColor() {
                            return 0xff169C9C;
                        }

                        @Override
                        public @NotNull Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
                            int color = this.getTintColor();
                            return new Vector3f((color >> 16 & 0xFF) / 255F, (color >> 8 & 0xFF) / 255F, (color & 0xFF) / 255F);
                        }

                        @Override
                        public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape) {
                            nearDistance = -8F;
                            farDistance = 24F;

                            if (farDistance > renderDistance) {
                                farDistance = renderDistance;
                                shape = FogShape.CYLINDER;
                            }

                            RenderSystem.setShaderFogStart(nearDistance);
                            RenderSystem.setShaderFogEnd(farDistance);
                            RenderSystem.setShaderFogShape(shape);
                        }
                    });
                }
            });
    public static final RegistryObject<FluidType> LUMINOUS_LIGHT_GRAY_FLUID_TYPE = FLUID_TYPES.register("luminous_light_gray_fluid", () ->
            new FluidType(FluidType.Properties.create()
                    .canExtinguish(true)
                    .canConvertToSource(true)
                    .supportsBoating(true)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                    .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
                    .canHydrate(true)
                    .canSwim(true)
                    .canDrown(false)
                    .pathType(BlockPathTypes.WATER)
                    .density(1024)
                    .viscosity(1024)
            ) {
                @Override
                public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                    consumer.accept(new IClientFluidTypeExtensions() {
                        private static final ResourceLocation STILL = new ResourceLocation("block/water_still"),
                                FLOW = new ResourceLocation("block/water_flow"),
                                OVERLAY = new ResourceLocation("block/water_overlay"),
                                VIEW_OVERLAY = new ResourceLocation("textures/block/water_overlay.png");

                        @Override
                        public ResourceLocation getStillTexture() {
                            return STILL;
                        }

                        @Override
                        public ResourceLocation getFlowingTexture() {
                            return FLOW;
                        }

                        @Override
                        public ResourceLocation getOverlayTexture() {
                            return OVERLAY;
                        }

                        @Override
                        public ResourceLocation getRenderOverlayTexture(Minecraft mc) {
                            return VIEW_OVERLAY;
                        }

                        @Override
                        public int getTintColor() {
                            return 0xff9D9D97;
                        }

                        @Override
                        public @NotNull Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
                            int color = this.getTintColor();
                            return new Vector3f((color >> 16 & 0xFF) / 255F, (color >> 8 & 0xFF) / 255F, (color & 0xFF) / 255F);
                        }

                        @Override
                        public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape) {
                            nearDistance = -8F;
                            farDistance = 24F;

                            if (farDistance > renderDistance) {
                                farDistance = renderDistance;
                                shape = FogShape.CYLINDER;
                            }

                            RenderSystem.setShaderFogStart(nearDistance);
                            RenderSystem.setShaderFogEnd(farDistance);
                            RenderSystem.setShaderFogShape(shape);
                        }
                    });
                }
            });
    public static final RegistryObject<FluidType> LUMINOUS_GRAY_FLUID_TYPE = FLUID_TYPES.register("luminous_gray_fluid", () ->
            new FluidType(FluidType.Properties.create()
                    .canExtinguish(true)
                    .canConvertToSource(true)
                    .supportsBoating(true)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                    .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
                    .canHydrate(true)
                    .canSwim(true)
                    .canDrown(false)
                    .pathType(BlockPathTypes.WATER)
                    .density(1024)
                    .viscosity(1024)
            ) {

                @Override
                public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                    consumer.accept(new IClientFluidTypeExtensions() {
                        private static final ResourceLocation STILL = new ResourceLocation("block/water_still"),
                                FLOW = new ResourceLocation("block/water_flow"),
                                OVERLAY = new ResourceLocation("block/water_overlay"),
                                VIEW_OVERLAY = new ResourceLocation("textures/block/water_overlay.png");

                        @Override
                        public ResourceLocation getStillTexture() {
                            return STILL;
                        }

                        @Override
                        public ResourceLocation getFlowingTexture() {
                            return FLOW;
                        }

                        @Override
                        public ResourceLocation getOverlayTexture() {
                            return OVERLAY;
                        }

                        @Override
                        public ResourceLocation getRenderOverlayTexture(Minecraft mc) {
                            return VIEW_OVERLAY;
                        }

                        @Override
                        public int getTintColor() {
                            return 0xff474F52;
                        }

                        @Override
                        public @NotNull Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
                            int color = this.getTintColor();
                            return new Vector3f((color >> 16 & 0xFF) / 255F, (color >> 8 & 0xFF) / 255F, (color & 0xFF) / 255F);
                        }

                        @Override
                        public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape) {
                            nearDistance = -8F;
                            farDistance = 24F;

                            if (farDistance > renderDistance) {
                                farDistance = renderDistance;
                                shape = FogShape.CYLINDER;
                            }

                            RenderSystem.setShaderFogStart(nearDistance);
                            RenderSystem.setShaderFogEnd(farDistance);
                            RenderSystem.setShaderFogShape(shape);
                        }
                    });
                }
            });
    public static final RegistryObject<FluidType> LUMINOUS_LIGHT_BLUE_FLUID_TYPE = FLUID_TYPES.register("luminous_light_blue_fluid", () ->
            new FluidType(FluidType.Properties.create()
                    .canExtinguish(true)
                    .canConvertToSource(true)
                    .supportsBoating(true)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                    .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
                    .canHydrate(true)
                    .canSwim(true)
                    .canDrown(false)
                    .pathType(BlockPathTypes.WATER)
                    .density(1024)
                    .viscosity(1024)
            ) {

                @Override
                public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                    consumer.accept(new IClientFluidTypeExtensions() {
                        private static final ResourceLocation STILL = new ResourceLocation("block/water_still"),
                                FLOW = new ResourceLocation("block/water_flow"),
                                OVERLAY = new ResourceLocation("block/water_overlay"),
                                VIEW_OVERLAY = new ResourceLocation("textures/block/water_overlay.png");

                        @Override
                        public ResourceLocation getStillTexture() {
                            return STILL;
                        }

                        @Override
                        public ResourceLocation getFlowingTexture() {
                            return FLOW;
                        }

                        @Override
                        public ResourceLocation getOverlayTexture() {
                            return OVERLAY;
                        }

                        @Override
                        public ResourceLocation getRenderOverlayTexture(Minecraft mc) {
                            return VIEW_OVERLAY;
                        }

                        @Override
                        public int getTintColor() {
                            return 0xff3AB3DA;
                        }

                        @Override
                        public @NotNull Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
                            int color = this.getTintColor();
                            return new Vector3f((color >> 16 & 0xFF) / 255F, (color >> 8 & 0xFF) / 255F, (color & 0xFF) / 255F);
                        }

                        @Override
                        public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape) {
                            nearDistance = -8F;
                            farDistance = 24F;

                            if (farDistance > renderDistance) {
                                farDistance = renderDistance;
                                shape = FogShape.CYLINDER;
                            }

                            RenderSystem.setShaderFogStart(nearDistance);
                            RenderSystem.setShaderFogEnd(farDistance);
                            RenderSystem.setShaderFogShape(shape);
                        }
                    });
                }
            });
    public static final RegistryObject<FluidType> LUMINOUS_ORANGE_FLUID_TYPE = FLUID_TYPES.register("luminous_orange_fluid", () ->
            new FluidType(FluidType.Properties.create()
                    .canExtinguish(true)
                    .canConvertToSource(true)
                    .supportsBoating(true)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                    .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
                    .canHydrate(true)
                    .canSwim(true)
                    .canDrown(false)
                    .pathType(BlockPathTypes.WATER)
                    .density(1024)
                    .viscosity(1024)
            ) {
                @Override
                public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                    consumer.accept(new IClientFluidTypeExtensions() {
                        private static final ResourceLocation STILL = new ResourceLocation("block/water_still"),
                                FLOW = new ResourceLocation("block/water_flow"),
                                OVERLAY = new ResourceLocation("block/water_overlay"),
                                VIEW_OVERLAY = new ResourceLocation("textures/block/water_overlay.png");

                        @Override
                        public ResourceLocation getStillTexture() {
                            return STILL;
                        }

                        @Override
                        public ResourceLocation getFlowingTexture() {
                            return FLOW;
                        }

                        @Override
                        public ResourceLocation getOverlayTexture() {
                            return OVERLAY;
                        }

                        @Override
                        public ResourceLocation getRenderOverlayTexture(Minecraft mc) {
                            return VIEW_OVERLAY;
                        }

                        @Override
                        public int getTintColor() {
                            return 0xffF9801D;
                        }

                        @Override
                        public @NotNull Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
                            int color = this.getTintColor();
                            return new Vector3f((color >> 16 & 0xFF) / 255F, (color >> 8 & 0xFF) / 255F, (color & 0xFF) / 255F);
                        }

                        @Override
                        public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape) {
                            nearDistance = -8F;
                            farDistance = 24F;

                            if (farDistance > renderDistance) {
                                farDistance = renderDistance;
                                shape = FogShape.CYLINDER;
                            }

                            RenderSystem.setShaderFogStart(nearDistance);
                            RenderSystem.setShaderFogEnd(farDistance);
                            RenderSystem.setShaderFogShape(shape);
                        }
                    });
                }
            });
    public static final RegistryObject<FluidType> LUMINOUS_WHITE_FLUID_TYPE = FLUID_TYPES.register("luminous_white_fluid", () ->
            new FluidType(FluidType.Properties.create()
                    .canExtinguish(true)
                    .canConvertToSource(true)
                    .supportsBoating(true)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                    .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
                    .canHydrate(true)
                    .canSwim(true)
                    .canDrown(false)
                    .pathType(BlockPathTypes.WATER)
                    .density(1024)
                    .viscosity(1024)
            ) {
                @Override
                public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                    consumer.accept(new IClientFluidTypeExtensions() {
                        private static final ResourceLocation STILL = new ResourceLocation("block/water_still"),
                                FLOW = new ResourceLocation("block/water_flow"),
                                OVERLAY = new ResourceLocation("block/water_overlay"),
                                VIEW_OVERLAY = new ResourceLocation("textures/block/water_overlay.png");

                        @Override
                        public ResourceLocation getStillTexture() {
                            return STILL;
                        }

                        @Override
                        public ResourceLocation getFlowingTexture() {
                            return FLOW;
                        }

                        @Override
                        public ResourceLocation getOverlayTexture() {
                            return OVERLAY;
                        }

                        @Override
                        public ResourceLocation getRenderOverlayTexture(Minecraft mc) {
                            return VIEW_OVERLAY;
                        }

                        @Override
                        public int getTintColor() {
                            return 0xffF9FFFE;
                        }

                        @Override
                        public @NotNull Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
                            int color = this.getTintColor();
                            return new Vector3f((color >> 16 & 0xFF) / 255F, (color >> 8 & 0xFF) / 255F, (color & 0xFF) / 255F);
                        }

                        @Override
                        public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape) {
                            nearDistance = -8F;
                            farDistance = 24F;

                            if (farDistance > renderDistance) {
                                farDistance = renderDistance;
                                shape = FogShape.CYLINDER;
                            }

                            RenderSystem.setShaderFogStart(nearDistance);
                            RenderSystem.setShaderFogEnd(farDistance);
                            RenderSystem.setShaderFogShape(shape);
                        }
                    });
                }
            });

    public static ForgeFlowingFluid.Properties LUMINOUS_MAGENTAFluidProperties() {
        return new ForgeFlowingFluid.Properties(LUMINOUS_MAGENTA_FLUID_TYPE, LUMINOUS_MAGENTA_FLUID, LUMINOUS_MAGENTA_FLUID_FLOWING)
                .block(LUMINOUS_MAGENTA_FLUID_BLOCK)
                .bucket(ModItems.LUMINOUS_MAGENTA_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties LUMINOUS_PURPLEFluidProperties() {
        return new ForgeFlowingFluid.Properties(LUMINOUS_PURPLE_FLUID_TYPE, LUMINOUS_PURPLE_FLUID, LUMINOUS_PURPLE_FLUID_FLOWING)
                .block(LUMINOUS_PURPLE_FLUID_BLOCK)
                .bucket(ModItems.LUMINOUS_PURPLE_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties LUMINOUS_GREENFluidProperties() {
        return new ForgeFlowingFluid.Properties(LUMINOUS_GREEN_FLUID_TYPE, LUMINOUS_GREEN_FLUID, LUMINOUS_GREEN_FLUID_FLOWING)
                .block(LUMINOUS_GREEN_FLUID_BLOCK)
                .bucket(ModItems.LUMINOUS_GREEN_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties LUMINOUS_YELLOWFluidProperties() {
        return new ForgeFlowingFluid.Properties(LUMINOUS_YELLOW_FLUID_TYPE, LUMINOUS_YELLOW_FLUID, LUMINOUS_YELLOW_FLUID_FLOWING)
                .block(LUMINOUS_YELLOW_FLUID_BLOCK)
                .bucket(ModItems.LUMINOUS_YELLOW_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties LUMINOUS_LIMEFluidProperties() {
        return new ForgeFlowingFluid.Properties(LUMINOUS_LIME_FLUID_TYPE, LUMINOUS_LIME_FLUID, LUMINOUS_LIME_FLUID_FLOWING)
                .block(LUMINOUS_LIME_FLUID_BLOCK)
                .bucket(ModItems.LUMINOUS_LIME_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties LUMINOUS_PINKFluidProperties() {
        return new ForgeFlowingFluid.Properties(LUMINOUS_PINK_FLUID_TYPE, LUMINOUS_PINK_FLUID, LUMINOUS_PINK_FLUID_FLOWING)
                .block(LUMINOUS_PINK_FLUID_BLOCK)
                .bucket(ModItems.LUMINOUS_PINK_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties LUMINOUS_REDFluidProperties() {
        return new ForgeFlowingFluid.Properties(LUMINOUS_RED_FLUID_TYPE, LUMINOUS_RED_FLUID, LUMINOUS_RED_FLUID_FLOWING)
                .block(LUMINOUS_RED_FLUID_BLOCK)
                .bucket(ModItems.LUMINOUS_RED_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties LUMINOUS_BLACKFluidProperties() {
        return new ForgeFlowingFluid.Properties(LUMINOUS_BLACK_FLUID_TYPE, LUMINOUS_BLACK_FLUID, LUMINOUS_BLACK_FLUID_FLOWING)
                .block(LUMINOUS_BLACK_FLUID_BLOCK)
                .bucket(ModItems.LUMINOUS_BLACK_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties LUMINOUS_BROWNFluidProperties() {
        return new ForgeFlowingFluid.Properties(LUMINOUS_BROWN_FLUID_TYPE, LUMINOUS_BROWN_FLUID, LUMINOUS_BROWN_FLUID_FLOWING)
                .block(LUMINOUS_BROWN_FLUID_BLOCK)
                .bucket(ModItems.LUMINOUS_BROWN_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties LUMINOUS_BLUEFluidProperties() {
        return new ForgeFlowingFluid.Properties(LUMINOUS_BLUE_FLUID_TYPE, LUMINOUS_BLUE_FLUID, LUMINOUS_BLUE_FLUID_FLOWING)
                .block(LUMINOUS_BLUE_FLUID_BLOCK)
                .bucket(ModItems.LUMINOUS_BLUE_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties LUMINOUS_CYANFluidProperties() {
        return new ForgeFlowingFluid.Properties(LUMINOUS_CYAN_FLUID_TYPE, LUMINOUS_CYAN_FLUID, LUMINOUS_CYAN_FLUID_FLOWING)
                .block(LUMINOUS_CYAN_FLUID_BLOCK)
                .bucket(ModItems.LUMINOUS_CYAN_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties LUMINOUS_LIGHT_GRAYFluidProperties() {
        return new ForgeFlowingFluid.Properties(LUMINOUS_LIGHT_GRAY_FLUID_TYPE, LUMINOUS_LIGHT_GRAY_FLUID, LUMINOUS_LIGHT_GRAY_FLUID_FLOWING)
                .block(LUMINOUS_LIGHT_GRAY_FLUID_BLOCK)
                .bucket(ModItems.LUMINOUS_LIGHT_GRAY_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties LUMINOUS_GRAYFluidProperties() {
        return new ForgeFlowingFluid.Properties(LUMINOUS_GRAY_FLUID_TYPE, LUMINOUS_GRAY_FLUID, LUMINOUS_GRAY_FLUID_FLOWING)
                .block(LUMINOUS_GRAY_FLUID_BLOCK)
                .bucket(ModItems.LUMINOUS_GRAY_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties LUMINOUS_LIGHT_BLUEFluidProperties() {
        return new ForgeFlowingFluid.Properties(LUMINOUS_LIGHT_BLUE_FLUID_TYPE, LUMINOUS_LIGHT_BLUE_FLUID, LUMINOUS_LIGHT_BLUE_FLUID_FLOWING)
                .block(LUMINOUS_LIGHT_BLUE_FLUID_BLOCK)
                .bucket(ModItems.LUMINOUS_LIGHT_BLUE_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties LUMINOUS_ORANGEFluidProperties() {
        return new ForgeFlowingFluid.Properties(LUMINOUS_ORANGE_FLUID_TYPE, LUMINOUS_ORANGE_FLUID, LUMINOUS_ORANGE_FLUID_FLOWING)
                .block(LUMINOUS_ORANGE_FLUID_BLOCK)
                .bucket(ModItems.LUMINOUS_ORANGE_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties LUMINOUS_WHITEFluidProperties() {
        return new ForgeFlowingFluid.Properties(LUMINOUS_WHITE_FLUID_TYPE, LUMINOUS_WHITE_FLUID, LUMINOUS_WHITE_FLUID_FLOWING)
                .block(LUMINOUS_WHITE_FLUID_BLOCK)
                .bucket(ModItems.LUMINOUS_WHITE_WATER_BUCKET);
    }

    public static final RegistryObject<FlowingFluid> LUMINOUS_MAGENTA_FLUID = FLUIDS.register("luminous_magenta_fluid", () ->
            new ForgeFlowingFluid.Source(LUMINOUS_MAGENTAFluidProperties()));
    public static final RegistryObject<Fluid> LUMINOUS_MAGENTA_FLUID_FLOWING = FLUIDS.register("luminous_magenta_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(LUMINOUS_MAGENTAFluidProperties()));
    public static final RegistryObject<LiquidBlock> LUMINOUS_MAGENTA_FLUID_BLOCK = BLOCKS.register("luminous_magenta_fluid_block", () ->
            new LiquidBlock(LUMINOUS_MAGENTA_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).lightLevel((p_50755_) -> {
                return 15;
            }).noLootTable()));

    public static final RegistryObject<FlowingFluid> LUMINOUS_PURPLE_FLUID = FLUIDS.register("luminous_purple_fluid", () ->
            new ForgeFlowingFluid.Source(LUMINOUS_PURPLEFluidProperties()));
    public static final RegistryObject<Fluid> LUMINOUS_PURPLE_FLUID_FLOWING = FLUIDS.register("luminous_purple_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(LUMINOUS_PURPLEFluidProperties()));
    public static final RegistryObject<LiquidBlock> LUMINOUS_PURPLE_FLUID_BLOCK = BLOCKS.register("luminous_purple_fluid_block", () ->
            new LiquidBlock(LUMINOUS_PURPLE_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).lightLevel((p_50755_) -> {
                return 15;
            }).noLootTable()));


    public static final RegistryObject<FlowingFluid> LUMINOUS_GREEN_FLUID = FLUIDS.register("luminous_green_fluid", () ->
            new ForgeFlowingFluid.Source(LUMINOUS_GREENFluidProperties()));
    public static final RegistryObject<Fluid> LUMINOUS_GREEN_FLUID_FLOWING = FLUIDS.register("luminous_green_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(LUMINOUS_GREENFluidProperties()));
    public static final RegistryObject<LiquidBlock> LUMINOUS_GREEN_FLUID_BLOCK = BLOCKS.register("luminous_green_fluid_block", () ->
            new LiquidBlock(LUMINOUS_GREEN_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).lightLevel((p_50755_) -> {
                return 15;
            }).noLootTable()));


    public static final RegistryObject<FlowingFluid> LUMINOUS_YELLOW_FLUID = FLUIDS.register("luminous_yellow_fluid", () ->
            new ForgeFlowingFluid.Source(LUMINOUS_YELLOWFluidProperties()));
    public static final RegistryObject<Fluid> LUMINOUS_YELLOW_FLUID_FLOWING = FLUIDS.register("luminous_yellow_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(LUMINOUS_YELLOWFluidProperties()));
    public static final RegistryObject<LiquidBlock> LUMINOUS_YELLOW_FLUID_BLOCK = BLOCKS.register("luminous_yellow_fluid_block", () ->
            new LiquidBlock(LUMINOUS_YELLOW_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).lightLevel((p_50755_) -> {
                return 15;
            }).noLootTable()));


    public static final RegistryObject<FlowingFluid> LUMINOUS_LIME_FLUID = FLUIDS.register("luminous_lime_fluid", () ->
            new ForgeFlowingFluid.Source(LUMINOUS_LIMEFluidProperties()));
    public static final RegistryObject<Fluid> LUMINOUS_LIME_FLUID_FLOWING = FLUIDS.register("luminous_lime_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(LUMINOUS_LIMEFluidProperties()));
    public static final RegistryObject<LiquidBlock> LUMINOUS_LIME_FLUID_BLOCK = BLOCKS.register("luminous_lime_fluid_block", () ->
            new LiquidBlock(LUMINOUS_LIME_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).lightLevel((p_50755_) -> {
                return 15;
            }).noLootTable()));


    public static final RegistryObject<FlowingFluid> LUMINOUS_PINK_FLUID = FLUIDS.register("luminous_pink_fluid", () ->
            new ForgeFlowingFluid.Source(LUMINOUS_PINKFluidProperties()));
    public static final RegistryObject<Fluid> LUMINOUS_PINK_FLUID_FLOWING = FLUIDS.register("luminous_pink_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(LUMINOUS_PINKFluidProperties()));
    public static final RegistryObject<LiquidBlock> LUMINOUS_PINK_FLUID_BLOCK = BLOCKS.register("luminous_pink_fluid_block", () ->
            new LiquidBlock(LUMINOUS_PINK_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).lightLevel((p_50755_) -> {
                return 15;
            }).noLootTable()));


    public static final RegistryObject<FlowingFluid> LUMINOUS_RED_FLUID = FLUIDS.register("luminous_red_fluid", () ->
            new ForgeFlowingFluid.Source(LUMINOUS_REDFluidProperties()));
    public static final RegistryObject<Fluid> LUMINOUS_RED_FLUID_FLOWING = FLUIDS.register("luminous_red_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(LUMINOUS_REDFluidProperties()));
    public static final RegistryObject<LiquidBlock> LUMINOUS_RED_FLUID_BLOCK = BLOCKS.register("luminous_red_fluid_block", () ->
            new LiquidBlock(LUMINOUS_RED_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).lightLevel((p_50755_) -> {
                return 15;
            }).noLootTable()));


    public static final RegistryObject<FlowingFluid> LUMINOUS_BLACK_FLUID = FLUIDS.register("luminous_black_fluid", () ->
            new ForgeFlowingFluid.Source(LUMINOUS_BLACKFluidProperties()));
    public static final RegistryObject<Fluid> LUMINOUS_BLACK_FLUID_FLOWING = FLUIDS.register("luminous_black_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(LUMINOUS_BLACKFluidProperties()));
    public static final RegistryObject<LiquidBlock> LUMINOUS_BLACK_FLUID_BLOCK = BLOCKS.register("luminous_black_fluid_block", () ->
            new LiquidBlock(LUMINOUS_BLACK_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).lightLevel((p_50755_) -> {
                return 15;
            }).noLootTable()));


    public static final RegistryObject<FlowingFluid> LUMINOUS_BROWN_FLUID = FLUIDS.register("luminous_brown_fluid", () ->
            new ForgeFlowingFluid.Source(LUMINOUS_BROWNFluidProperties()));
    public static final RegistryObject<Fluid> LUMINOUS_BROWN_FLUID_FLOWING = FLUIDS.register("luminous_brown_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(LUMINOUS_BROWNFluidProperties()));
    public static final RegistryObject<LiquidBlock> LUMINOUS_BROWN_FLUID_BLOCK = BLOCKS.register("luminous_brown_fluid_block", () ->
            new LiquidBlock(LUMINOUS_BROWN_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).lightLevel((p_50755_) -> {
                return 15;
            }).noLootTable()));


    public static final RegistryObject<FlowingFluid> LUMINOUS_BLUE_FLUID = FLUIDS.register("luminous_blue_fluid", () ->
            new ForgeFlowingFluid.Source(LUMINOUS_BLUEFluidProperties()));
    public static final RegistryObject<Fluid> LUMINOUS_BLUE_FLUID_FLOWING = FLUIDS.register("luminous_blue_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(LUMINOUS_BLUEFluidProperties()));
    public static final RegistryObject<LiquidBlock> LUMINOUS_BLUE_FLUID_BLOCK = BLOCKS.register("luminous_blue_fluid_block", () ->
            new LiquidBlock(LUMINOUS_BLUE_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).lightLevel((p_50755_) -> {
                return 15;
            }).noLootTable()));


    public static final RegistryObject<FlowingFluid> LUMINOUS_CYAN_FLUID = FLUIDS.register("luminous_cyan_fluid", () ->
            new ForgeFlowingFluid.Source(LUMINOUS_CYANFluidProperties()));
    public static final RegistryObject<Fluid> LUMINOUS_CYAN_FLUID_FLOWING = FLUIDS.register("luminous_cyan_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(LUMINOUS_CYANFluidProperties()));
    public static final RegistryObject<LiquidBlock> LUMINOUS_CYAN_FLUID_BLOCK = BLOCKS.register("luminous_cyan_fluid_block", () ->
            new LiquidBlock(LUMINOUS_CYAN_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).lightLevel((p_50755_) -> {
                return 15;
            }).noLootTable()));


    public static final RegistryObject<FlowingFluid> LUMINOUS_LIGHT_GRAY_FLUID = FLUIDS.register("luminous_light_gray_fluid", () ->
            new ForgeFlowingFluid.Source(LUMINOUS_LIGHT_GRAYFluidProperties()));
    public static final RegistryObject<Fluid> LUMINOUS_LIGHT_GRAY_FLUID_FLOWING = FLUIDS.register("luminous_light_gray_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(LUMINOUS_LIGHT_GRAYFluidProperties()));
    public static final RegistryObject<LiquidBlock> LUMINOUS_LIGHT_GRAY_FLUID_BLOCK = BLOCKS.register("luminous_light_gray_fluid_block", () ->
            new LiquidBlock(LUMINOUS_LIGHT_GRAY_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).lightLevel((p_50755_) -> {
                return 15;
            }).noLootTable()));


    public static final RegistryObject<FlowingFluid> LUMINOUS_GRAY_FLUID = FLUIDS.register("luminous_gray_fluid", () ->
            new ForgeFlowingFluid.Source(LUMINOUS_GRAYFluidProperties()));
    public static final RegistryObject<Fluid> LUMINOUS_GRAY_FLUID_FLOWING = FLUIDS.register("luminous_gray_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(LUMINOUS_GRAYFluidProperties()));
    public static final RegistryObject<LiquidBlock> LUMINOUS_GRAY_FLUID_BLOCK = BLOCKS.register("luminous_gray_fluid_block", () ->
            new LiquidBlock(LUMINOUS_GRAY_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).lightLevel((p_50755_) -> {
                return 15;
            }).noLootTable()));


    public static final RegistryObject<FlowingFluid> LUMINOUS_LIGHT_BLUE_FLUID = FLUIDS.register("luminous_light_blue_fluid", () ->
            new ForgeFlowingFluid.Source(LUMINOUS_LIGHT_BLUEFluidProperties()));
    public static final RegistryObject<Fluid> LUMINOUS_LIGHT_BLUE_FLUID_FLOWING = FLUIDS.register("luminous_light_blue_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(LUMINOUS_LIGHT_BLUEFluidProperties()));
    public static final RegistryObject<LiquidBlock> LUMINOUS_LIGHT_BLUE_FLUID_BLOCK = BLOCKS.register("luminous_light_blue_fluid_block", () ->
            new LiquidBlock(LUMINOUS_LIGHT_BLUE_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).lightLevel((p_50755_) -> {
                return 15;
            }).noLootTable()));


    public static final RegistryObject<FlowingFluid> LUMINOUS_ORANGE_FLUID = FLUIDS.register("luminous_orange_fluid", () ->
            new ForgeFlowingFluid.Source(LUMINOUS_ORANGEFluidProperties()));
    public static final RegistryObject<Fluid> LUMINOUS_ORANGE_FLUID_FLOWING = FLUIDS.register("luminous_orange_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(LUMINOUS_ORANGEFluidProperties()));
    public static final RegistryObject<LiquidBlock> LUMINOUS_ORANGE_FLUID_BLOCK = BLOCKS.register("luminous_orange_fluid_block", () ->
            new LiquidBlock(LUMINOUS_ORANGE_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).lightLevel((p_50755_) -> {
                return 15;
            }).noLootTable()));


    public static final RegistryObject<FlowingFluid> LUMINOUS_WHITE_FLUID = FLUIDS.register("luminous_white_fluid", () ->
            new ForgeFlowingFluid.Source(LUMINOUS_WHITEFluidProperties()));
    public static final RegistryObject<Fluid> LUMINOUS_WHITE_FLUID_FLOWING = FLUIDS.register("luminous_white_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(LUMINOUS_WHITEFluidProperties()));
    public static final RegistryObject<LiquidBlock> LUMINOUS_WHITE_FLUID_BLOCK = BLOCKS.register("luminous_white_fluid_block", () ->
            new LiquidBlock(LUMINOUS_WHITE_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).lightLevel((p_50755_) -> {
                return 15;
            }).noLootTable()));

}
