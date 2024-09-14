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

public class ModFluids {

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, ColoredWater.MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, ColoredWater.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ColoredWater.MOD_ID);

    public static final RegistryObject<FluidType> MAGENTA_FLUID_TYPE = FLUID_TYPES.register("magenta_fluid", () ->
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
    public static final RegistryObject<FluidType> PURPLE_FLUID_TYPE = FLUID_TYPES.register("purple_fluid", () ->
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
    public static final RegistryObject<FluidType> GREEN_FLUID_TYPE = FLUID_TYPES.register("green_fluid", () ->
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
    public static final RegistryObject<FluidType> YELLOW_FLUID_TYPE = FLUID_TYPES.register("yellow_fluid", () ->
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
    public static final RegistryObject<FluidType> LIME_FLUID_TYPE = FLUID_TYPES.register("lime_fluid", () ->
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
    public static final RegistryObject<FluidType> PINK_FLUID_TYPE = FLUID_TYPES.register("pink_fluid", () ->
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
    public static final RegistryObject<FluidType> RED_FLUID_TYPE = FLUID_TYPES.register("red_fluid", () ->
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
    public static final RegistryObject<FluidType> BLACK_FLUID_TYPE = FLUID_TYPES.register("black_fluid", () ->
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
    public static final RegistryObject<FluidType> BROWN_FLUID_TYPE = FLUID_TYPES.register("brown_fluid", () ->
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
    public static final RegistryObject<FluidType> BLUE_FLUID_TYPE = FLUID_TYPES.register("blue_fluid", () ->
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
    public static final RegistryObject<FluidType> CYAN_FLUID_TYPE = FLUID_TYPES.register("cyan_fluid", () ->
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
    public static final RegistryObject<FluidType> LIGHT_GRAY_FLUID_TYPE = FLUID_TYPES.register("light_gray_fluid", () ->
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
    public static final RegistryObject<FluidType> GRAY_FLUID_TYPE = FLUID_TYPES.register("gray_fluid", () ->
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
    public static final RegistryObject<FluidType> LIGHT_BLUE_FLUID_TYPE = FLUID_TYPES.register("light_blue_fluid", () ->
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
    public static final RegistryObject<FluidType> ORANGE_FLUID_TYPE = FLUID_TYPES.register("orange_fluid", () ->
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
    public static final RegistryObject<FluidType> WHITE_FLUID_TYPE = FLUID_TYPES.register("white_fluid", () ->
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

    public static ForgeFlowingFluid.Properties MAGENTAFluidProperties() {
        return new ForgeFlowingFluid.Properties(MAGENTA_FLUID_TYPE, MAGENTA_FLUID, MAGENTA_FLUID_FLOWING)
                .block(MAGENTA_FLUID_BLOCK)
                .bucket(ModItems.MAGENTA_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties PURPLEFluidProperties() {
        return new ForgeFlowingFluid.Properties(PURPLE_FLUID_TYPE, PURPLE_FLUID, PURPLE_FLUID_FLOWING)
                .block(PURPLE_FLUID_BLOCK)
                .bucket(ModItems.PURPLE_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties GREENFluidProperties() {
        return new ForgeFlowingFluid.Properties(GREEN_FLUID_TYPE, GREEN_FLUID, GREEN_FLUID_FLOWING)
                .block(GREEN_FLUID_BLOCK)
                .bucket(ModItems.GREEN_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties YELLOWFluidProperties() {
        return new ForgeFlowingFluid.Properties(YELLOW_FLUID_TYPE, YELLOW_FLUID, YELLOW_FLUID_FLOWING)
                .block(YELLOW_FLUID_BLOCK)
                .bucket(ModItems.YELLOW_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties LIMEFluidProperties() {
        return new ForgeFlowingFluid.Properties(LIME_FLUID_TYPE, LIME_FLUID, LIME_FLUID_FLOWING)
                .block(LIME_FLUID_BLOCK)
                .bucket(ModItems.LIME_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties PINKFluidProperties() {
        return new ForgeFlowingFluid.Properties(PINK_FLUID_TYPE, PINK_FLUID, PINK_FLUID_FLOWING)
                .block(PINK_FLUID_BLOCK)
                .bucket(ModItems.PINK_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties REDFluidProperties() {
        return new ForgeFlowingFluid.Properties(RED_FLUID_TYPE, RED_FLUID, RED_FLUID_FLOWING)
                .block(RED_FLUID_BLOCK)
                .bucket(ModItems.RED_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties BLACKFluidProperties() {
        return new ForgeFlowingFluid.Properties(BLACK_FLUID_TYPE, BLACK_FLUID, BLACK_FLUID_FLOWING)
                .block(BLACK_FLUID_BLOCK)
                .bucket(ModItems.BLACK_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties BROWNFluidProperties() {
        return new ForgeFlowingFluid.Properties(BROWN_FLUID_TYPE, BROWN_FLUID, BROWN_FLUID_FLOWING)
                .block(BROWN_FLUID_BLOCK)
                .bucket(ModItems.BROWN_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties BLUEFluidProperties() {
        return new ForgeFlowingFluid.Properties(BLUE_FLUID_TYPE, BLUE_FLUID, BLUE_FLUID_FLOWING)
                .block(BLUE_FLUID_BLOCK)
                .bucket(ModItems.BLUE_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties CYANFluidProperties() {
        return new ForgeFlowingFluid.Properties(CYAN_FLUID_TYPE, CYAN_FLUID, CYAN_FLUID_FLOWING)
                .block(CYAN_FLUID_BLOCK)
                .bucket(ModItems.CYAN_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties LIGHT_GRAYFluidProperties() {
        return new ForgeFlowingFluid.Properties(LIGHT_GRAY_FLUID_TYPE, LIGHT_GRAY_FLUID, LIGHT_GRAY_FLUID_FLOWING)
                .block(LIGHT_GRAY_FLUID_BLOCK)
                .bucket(ModItems.LIGHT_GRAY_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties GRAYFluidProperties() {
        return new ForgeFlowingFluid.Properties(GRAY_FLUID_TYPE, GRAY_FLUID, GRAY_FLUID_FLOWING)
                .block(GRAY_FLUID_BLOCK)
                .bucket(ModItems.GRAY_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties LIGHT_BLUEFluidProperties() {
        return new ForgeFlowingFluid.Properties(LIGHT_BLUE_FLUID_TYPE, LIGHT_BLUE_FLUID, LIGHT_BLUE_FLUID_FLOWING)
                .block(LIGHT_BLUE_FLUID_BLOCK)
                .bucket(ModItems.LIGHT_BLUE_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties ORANGEFluidProperties() {
        return new ForgeFlowingFluid.Properties(ORANGE_FLUID_TYPE, ORANGE_FLUID, ORANGE_FLUID_FLOWING)
                .block(ORANGE_FLUID_BLOCK)
                .bucket(ModItems.ORANGE_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties WHITEFluidProperties() {
        return new ForgeFlowingFluid.Properties(WHITE_FLUID_TYPE, WHITE_FLUID, WHITE_FLUID_FLOWING)
                .block(WHITE_FLUID_BLOCK)
                .bucket(ModItems.WHITE_WATER_BUCKET);
    }

    public static final RegistryObject<FlowingFluid> MAGENTA_FLUID = FLUIDS.register("magenta_fluid", () ->
            new ForgeFlowingFluid.Source(MAGENTAFluidProperties()));
    public static final RegistryObject<Fluid> MAGENTA_FLUID_FLOWING = FLUIDS.register("magenta_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(MAGENTAFluidProperties()));
    public static final RegistryObject<LiquidBlock> MAGENTA_FLUID_BLOCK = BLOCKS.register("magenta_fluid_block", () ->
            new LiquidBlock(MAGENTA_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).noLootTable()));

    public static final RegistryObject<FlowingFluid> PURPLE_FLUID = FLUIDS.register("purple_fluid", () ->
            new ForgeFlowingFluid.Source(PURPLEFluidProperties()));
    public static final RegistryObject<Fluid> PURPLE_FLUID_FLOWING = FLUIDS.register("purple_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(PURPLEFluidProperties()));
    public static final RegistryObject<LiquidBlock> PURPLE_FLUID_BLOCK = BLOCKS.register("purple_fluid_block", () ->
            new LiquidBlock(PURPLE_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).noLootTable()));


    public static final RegistryObject<FlowingFluid> GREEN_FLUID = FLUIDS.register("green_fluid", () ->
            new ForgeFlowingFluid.Source(GREENFluidProperties()));
    public static final RegistryObject<Fluid> GREEN_FLUID_FLOWING = FLUIDS.register("green_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(GREENFluidProperties()));
    public static final RegistryObject<LiquidBlock> GREEN_FLUID_BLOCK = BLOCKS.register("green_fluid_block", () ->
            new LiquidBlock(GREEN_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).noLootTable()));


    public static final RegistryObject<FlowingFluid> YELLOW_FLUID = FLUIDS.register("yellow_fluid", () ->
            new ForgeFlowingFluid.Source(YELLOWFluidProperties()));
    public static final RegistryObject<Fluid> YELLOW_FLUID_FLOWING = FLUIDS.register("yellow_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(YELLOWFluidProperties()));
    public static final RegistryObject<LiquidBlock> YELLOW_FLUID_BLOCK = BLOCKS.register("yellow_fluid_block", () ->
            new LiquidBlock(YELLOW_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).noLootTable()));


    public static final RegistryObject<FlowingFluid> LIME_FLUID = FLUIDS.register("lime_fluid", () ->
            new ForgeFlowingFluid.Source(LIMEFluidProperties()));
    public static final RegistryObject<Fluid> LIME_FLUID_FLOWING = FLUIDS.register("lime_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(LIMEFluidProperties()));
    public static final RegistryObject<LiquidBlock> LIME_FLUID_BLOCK = BLOCKS.register("lime_fluid_block", () ->
            new LiquidBlock(LIME_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).noLootTable()));


    public static final RegistryObject<FlowingFluid> PINK_FLUID = FLUIDS.register("pink_fluid", () ->
            new ForgeFlowingFluid.Source(PINKFluidProperties()));
    public static final RegistryObject<Fluid> PINK_FLUID_FLOWING = FLUIDS.register("pink_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(PINKFluidProperties()));
    public static final RegistryObject<LiquidBlock> PINK_FLUID_BLOCK = BLOCKS.register("pink_fluid_block", () ->
            new LiquidBlock(PINK_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).noLootTable()));


    public static final RegistryObject<FlowingFluid> RED_FLUID = FLUIDS.register("red_fluid", () ->
            new ForgeFlowingFluid.Source(REDFluidProperties()));
    public static final RegistryObject<Fluid> RED_FLUID_FLOWING = FLUIDS.register("red_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(REDFluidProperties()));
    public static final RegistryObject<LiquidBlock> RED_FLUID_BLOCK = BLOCKS.register("red_fluid_block", () ->
            new LiquidBlock(RED_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).noLootTable()));


    public static final RegistryObject<FlowingFluid> BLACK_FLUID = FLUIDS.register("black_fluid", () ->
            new ForgeFlowingFluid.Source(BLACKFluidProperties()));
    public static final RegistryObject<Fluid> BLACK_FLUID_FLOWING = FLUIDS.register("black_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(BLACKFluidProperties()));
    public static final RegistryObject<LiquidBlock> BLACK_FLUID_BLOCK = BLOCKS.register("black_fluid_block", () ->
            new LiquidBlock(BLACK_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).noLootTable()));


    public static final RegistryObject<FlowingFluid> BROWN_FLUID = FLUIDS.register("brown_fluid", () ->
            new ForgeFlowingFluid.Source(BROWNFluidProperties()));
    public static final RegistryObject<Fluid> BROWN_FLUID_FLOWING = FLUIDS.register("brown_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(BROWNFluidProperties()));
    public static final RegistryObject<LiquidBlock> BROWN_FLUID_BLOCK = BLOCKS.register("brown_fluid_block", () ->
            new LiquidBlock(BROWN_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).noLootTable()));


    public static final RegistryObject<FlowingFluid> BLUE_FLUID = FLUIDS.register("blue_fluid", () ->
            new ForgeFlowingFluid.Source(BLUEFluidProperties()));
    public static final RegistryObject<Fluid> BLUE_FLUID_FLOWING = FLUIDS.register("blue_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(BLUEFluidProperties()));
    public static final RegistryObject<LiquidBlock> BLUE_FLUID_BLOCK = BLOCKS.register("blue_fluid_block", () ->
            new LiquidBlock(BLUE_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).noLootTable()));


    public static final RegistryObject<FlowingFluid> CYAN_FLUID = FLUIDS.register("cyan_fluid", () ->
            new ForgeFlowingFluid.Source(CYANFluidProperties()));
    public static final RegistryObject<Fluid> CYAN_FLUID_FLOWING = FLUIDS.register("cyan_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(CYANFluidProperties()));
    public static final RegistryObject<LiquidBlock> CYAN_FLUID_BLOCK = BLOCKS.register("cyan_fluid_block", () ->
            new LiquidBlock(CYAN_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).noLootTable()));


    public static final RegistryObject<FlowingFluid> LIGHT_GRAY_FLUID = FLUIDS.register("light_gray_fluid", () ->
            new ForgeFlowingFluid.Source(LIGHT_GRAYFluidProperties()));
    public static final RegistryObject<Fluid> LIGHT_GRAY_FLUID_FLOWING = FLUIDS.register("light_gray_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(LIGHT_GRAYFluidProperties()));
    public static final RegistryObject<LiquidBlock> LIGHT_GRAY_FLUID_BLOCK = BLOCKS.register("light_gray_fluid_block", () ->
            new LiquidBlock(LIGHT_GRAY_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).noLootTable()));


    public static final RegistryObject<FlowingFluid> GRAY_FLUID = FLUIDS.register("gray_fluid", () ->
            new ForgeFlowingFluid.Source(GRAYFluidProperties()));
    public static final RegistryObject<Fluid> GRAY_FLUID_FLOWING = FLUIDS.register("gray_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(GRAYFluidProperties()));
    public static final RegistryObject<LiquidBlock> GRAY_FLUID_BLOCK = BLOCKS.register("gray_fluid_block", () ->
            new LiquidBlock(GRAY_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).noLootTable()));


    public static final RegistryObject<FlowingFluid> LIGHT_BLUE_FLUID = FLUIDS.register("light_blue_fluid", () ->
            new ForgeFlowingFluid.Source(LIGHT_BLUEFluidProperties()));
    public static final RegistryObject<Fluid> LIGHT_BLUE_FLUID_FLOWING = FLUIDS.register("light_blue_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(LIGHT_BLUEFluidProperties()));
    public static final RegistryObject<LiquidBlock> LIGHT_BLUE_FLUID_BLOCK = BLOCKS.register("light_blue_fluid_block", () ->
            new LiquidBlock(LIGHT_BLUE_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).noLootTable()));


    public static final RegistryObject<FlowingFluid> ORANGE_FLUID = FLUIDS.register("orange_fluid", () ->
            new ForgeFlowingFluid.Source(ORANGEFluidProperties()));
    public static final RegistryObject<Fluid> ORANGE_FLUID_FLOWING = FLUIDS.register("orange_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(ORANGEFluidProperties()));
    public static final RegistryObject<LiquidBlock> ORANGE_FLUID_BLOCK = BLOCKS.register("orange_fluid_block", () ->
            new LiquidBlock(ORANGE_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).noLootTable()));


    public static final RegistryObject<FlowingFluid> WHITE_FLUID = FLUIDS.register("white_fluid", () ->
            new ForgeFlowingFluid.Source(WHITEFluidProperties()));
    public static final RegistryObject<Fluid> WHITE_FLUID_FLOWING = FLUIDS.register("white_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(WHITEFluidProperties()));
    public static final RegistryObject<LiquidBlock> WHITE_FLUID_BLOCK = BLOCKS.register("white_fluid_block", () ->
            new LiquidBlock(WHITE_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).noLootTable()));

}
