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

public class ModFluidsCondense {

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, ColoredWater.MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, ColoredWater.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ColoredWater.MOD_ID);

    public static final RegistryObject<FluidType> CONDENSE_MAGENTA_FLUID_TYPE = FLUID_TYPES.register("condense_magenta_fluid", () ->
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
    public static final RegistryObject<FluidType> CONDENSE_PURPLE_FLUID_TYPE = FLUID_TYPES.register("condense_purple_fluid", () ->
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
    public static final RegistryObject<FluidType> CONDENSE_GREEN_FLUID_TYPE = FLUID_TYPES.register("condense_green_fluid", () ->
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
    public static final RegistryObject<FluidType> CONDENSE_YELLOW_FLUID_TYPE = FLUID_TYPES.register("condense_yellow_fluid", () ->
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
    public static final RegistryObject<FluidType> CONDENSE_LIME_FLUID_TYPE = FLUID_TYPES.register("condense_lime_fluid", () ->
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
    public static final RegistryObject<FluidType> CONDENSE_PINK_FLUID_TYPE = FLUID_TYPES.register("condense_pink_fluid", () ->
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
    public static final RegistryObject<FluidType> CONDENSE_RED_FLUID_TYPE = FLUID_TYPES.register("condense_red_fluid", () ->
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
    public static final RegistryObject<FluidType> CONDENSE_BLACK_FLUID_TYPE = FLUID_TYPES.register("condense_black_fluid", () ->
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
    public static final RegistryObject<FluidType> CONDENSE_BROWN_FLUID_TYPE = FLUID_TYPES.register("condense_brown_fluid", () ->
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
    public static final RegistryObject<FluidType> CONDENSE_BLUE_FLUID_TYPE = FLUID_TYPES.register("condense_blue_fluid", () ->
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
    public static final RegistryObject<FluidType> CONDENSE_CYAN_FLUID_TYPE = FLUID_TYPES.register("condense_cyan_fluid", () ->
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
    public static final RegistryObject<FluidType> CONDENSE_LIGHT_GRAY_FLUID_TYPE = FLUID_TYPES.register("condense_light_gray_fluid", () ->
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
    public static final RegistryObject<FluidType> CONDENSE_GRAY_FLUID_TYPE = FLUID_TYPES.register("condense_gray_fluid", () ->
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
    public static final RegistryObject<FluidType> CONDENSE_LIGHT_BLUE_FLUID_TYPE = FLUID_TYPES.register("condense_light_blue_fluid", () ->
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
    public static final RegistryObject<FluidType> CONDENSE_ORANGE_FLUID_TYPE = FLUID_TYPES.register("condense_orange_fluid", () ->
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
    public static final RegistryObject<FluidType> CONDENSE_WHITE_FLUID_TYPE = FLUID_TYPES.register("condense_white_fluid", () ->
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

    public static ForgeFlowingFluid.Properties CONDENSE_MAGENTAFluidProperties() {
        return new ForgeFlowingFluid.Properties(CONDENSE_MAGENTA_FLUID_TYPE, CONDENSE_MAGENTA_FLUID, CONDENSE_MAGENTA_FLUID_FLOWING)
                .block(CONDENSE_MAGENTA_FLUID_BLOCK)
                .bucket(ModItems.CONDENSE_MAGENTA_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties CONDENSE_PURPLEFluidProperties() {
        return new ForgeFlowingFluid.Properties(CONDENSE_PURPLE_FLUID_TYPE, CONDENSE_PURPLE_FLUID, CONDENSE_PURPLE_FLUID_FLOWING)
                .block(CONDENSE_PURPLE_FLUID_BLOCK)
                .bucket(ModItems.CONDENSE_PURPLE_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties CONDENSE_GREENFluidProperties() {
        return new ForgeFlowingFluid.Properties(CONDENSE_GREEN_FLUID_TYPE, CONDENSE_GREEN_FLUID, CONDENSE_GREEN_FLUID_FLOWING)
                .block(CONDENSE_GREEN_FLUID_BLOCK)
                .bucket(ModItems.CONDENSE_GREEN_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties CONDENSE_YELLOWFluidProperties() {
        return new ForgeFlowingFluid.Properties(CONDENSE_YELLOW_FLUID_TYPE, CONDENSE_YELLOW_FLUID, CONDENSE_YELLOW_FLUID_FLOWING)
                .block(CONDENSE_YELLOW_FLUID_BLOCK)
                .bucket(ModItems.CONDENSE_YELLOW_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties CONDENSE_LIMEFluidProperties() {
        return new ForgeFlowingFluid.Properties(CONDENSE_LIME_FLUID_TYPE, CONDENSE_LIME_FLUID, CONDENSE_LIME_FLUID_FLOWING)
                .block(CONDENSE_LIME_FLUID_BLOCK)
                .bucket(ModItems.CONDENSE_LIME_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties CONDENSE_PINKFluidProperties() {
        return new ForgeFlowingFluid.Properties(CONDENSE_PINK_FLUID_TYPE, CONDENSE_PINK_FLUID, CONDENSE_PINK_FLUID_FLOWING)
                .block(CONDENSE_PINK_FLUID_BLOCK)
                .bucket(ModItems.CONDENSE_PINK_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties CONDENSE_REDFluidProperties() {
        return new ForgeFlowingFluid.Properties(CONDENSE_RED_FLUID_TYPE, CONDENSE_RED_FLUID, CONDENSE_RED_FLUID_FLOWING)
                .block(CONDENSE_RED_FLUID_BLOCK)
                .bucket(ModItems.CONDENSE_RED_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties CONDENSE_BLACKFluidProperties() {
        return new ForgeFlowingFluid.Properties(CONDENSE_BLACK_FLUID_TYPE, CONDENSE_BLACK_FLUID, CONDENSE_BLACK_FLUID_FLOWING)
                .block(CONDENSE_BLACK_FLUID_BLOCK)
                .bucket(ModItems.CONDENSE_BLACK_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties CONDENSE_BROWNFluidProperties() {
        return new ForgeFlowingFluid.Properties(CONDENSE_BROWN_FLUID_TYPE, CONDENSE_BROWN_FLUID, CONDENSE_BROWN_FLUID_FLOWING)
                .block(CONDENSE_BROWN_FLUID_BLOCK)
                .bucket(ModItems.CONDENSE_BROWN_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties CONDENSE_BLUEFluidProperties() {
        return new ForgeFlowingFluid.Properties(CONDENSE_BLUE_FLUID_TYPE, CONDENSE_BLUE_FLUID, CONDENSE_BLUE_FLUID_FLOWING)
                .block(CONDENSE_BLUE_FLUID_BLOCK)
                .bucket(ModItems.CONDENSE_BLUE_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties CONDENSE_CYANFluidProperties() {
        return new ForgeFlowingFluid.Properties(CONDENSE_CYAN_FLUID_TYPE, CONDENSE_CYAN_FLUID, CONDENSE_CYAN_FLUID_FLOWING)
                .block(CONDENSE_CYAN_FLUID_BLOCK)
                .bucket(ModItems.CONDENSE_CYAN_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties CONDENSE_LIGHT_GRAYFluidProperties() {
        return new ForgeFlowingFluid.Properties(CONDENSE_LIGHT_GRAY_FLUID_TYPE, CONDENSE_LIGHT_GRAY_FLUID, CONDENSE_LIGHT_GRAY_FLUID_FLOWING)
                .block(CONDENSE_LIGHT_GRAY_FLUID_BLOCK)
                .bucket(ModItems.CONDENSE_LIGHT_GRAY_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties CONDENSE_GRAYFluidProperties() {
        return new ForgeFlowingFluid.Properties(CONDENSE_GRAY_FLUID_TYPE, CONDENSE_GRAY_FLUID, CONDENSE_GRAY_FLUID_FLOWING)
                .block(CONDENSE_GRAY_FLUID_BLOCK)
                .bucket(ModItems.CONDENSE_GRAY_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties CONDENSE_LIGHT_BLUEFluidProperties() {
        return new ForgeFlowingFluid.Properties(CONDENSE_LIGHT_BLUE_FLUID_TYPE, CONDENSE_LIGHT_BLUE_FLUID, CONDENSE_LIGHT_BLUE_FLUID_FLOWING)
                .block(CONDENSE_LIGHT_BLUE_FLUID_BLOCK)
                .bucket(ModItems.CONDENSE_LIGHT_BLUE_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties CONDENSE_ORANGEFluidProperties() {
        return new ForgeFlowingFluid.Properties(CONDENSE_ORANGE_FLUID_TYPE, CONDENSE_ORANGE_FLUID, CONDENSE_ORANGE_FLUID_FLOWING)
                .block(CONDENSE_ORANGE_FLUID_BLOCK)
                .bucket(ModItems.CONDENSE_ORANGE_WATER_BUCKET);
    }

    public static ForgeFlowingFluid.Properties CONDENSE_WHITEFluidProperties() {
        return new ForgeFlowingFluid.Properties(CONDENSE_WHITE_FLUID_TYPE, CONDENSE_WHITE_FLUID, CONDENSE_WHITE_FLUID_FLOWING)
                .block(CONDENSE_WHITE_FLUID_BLOCK)
                .bucket(ModItems.CONDENSE_WHITE_WATER_BUCKET);
    }

    public static final RegistryObject<FlowingFluid> CONDENSE_MAGENTA_FLUID = FLUIDS.register("condense_magenta_fluid", () ->
            new ForgeFlowingFluid.Source(CONDENSE_MAGENTAFluidProperties()));
    public static final RegistryObject<Fluid> CONDENSE_MAGENTA_FLUID_FLOWING = FLUIDS.register("condense_magenta_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(CONDENSE_MAGENTAFluidProperties()));
    public static final RegistryObject<LiquidBlock> CONDENSE_MAGENTA_FLUID_BLOCK = BLOCKS.register("condense_magenta_fluid_block", () ->
            new LiquidBlock(CONDENSE_MAGENTA_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).noLootTable()));

    public static final RegistryObject<FlowingFluid> CONDENSE_PURPLE_FLUID = FLUIDS.register("condense_purple_fluid", () ->
            new ForgeFlowingFluid.Source(CONDENSE_PURPLEFluidProperties()));
    public static final RegistryObject<Fluid> CONDENSE_PURPLE_FLUID_FLOWING = FLUIDS.register("condense_purple_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(CONDENSE_PURPLEFluidProperties()));
    public static final RegistryObject<LiquidBlock> CONDENSE_PURPLE_FLUID_BLOCK = BLOCKS.register("condense_purple_fluid_block", () ->
            new LiquidBlock(CONDENSE_PURPLE_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).noLootTable()));


    public static final RegistryObject<FlowingFluid> CONDENSE_GREEN_FLUID = FLUIDS.register("condense_green_fluid", () ->
            new ForgeFlowingFluid.Source(CONDENSE_GREENFluidProperties()));
    public static final RegistryObject<Fluid> CONDENSE_GREEN_FLUID_FLOWING = FLUIDS.register("condense_green_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(CONDENSE_GREENFluidProperties()));
    public static final RegistryObject<LiquidBlock> CONDENSE_GREEN_FLUID_BLOCK = BLOCKS.register("condense_green_fluid_block", () ->
            new LiquidBlock(CONDENSE_GREEN_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).noLootTable()));


    public static final RegistryObject<FlowingFluid> CONDENSE_YELLOW_FLUID = FLUIDS.register("condense_yellow_fluid", () ->
            new ForgeFlowingFluid.Source(CONDENSE_YELLOWFluidProperties()));
    public static final RegistryObject<Fluid> CONDENSE_YELLOW_FLUID_FLOWING = FLUIDS.register("condense_yellow_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(CONDENSE_YELLOWFluidProperties()));
    public static final RegistryObject<LiquidBlock> CONDENSE_YELLOW_FLUID_BLOCK = BLOCKS.register("condense_yellow_fluid_block", () ->
            new LiquidBlock(CONDENSE_YELLOW_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).noLootTable()));


    public static final RegistryObject<FlowingFluid> CONDENSE_LIME_FLUID = FLUIDS.register("condense_lime_fluid", () ->
            new ForgeFlowingFluid.Source(CONDENSE_LIMEFluidProperties()));
    public static final RegistryObject<Fluid> CONDENSE_LIME_FLUID_FLOWING = FLUIDS.register("condense_lime_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(CONDENSE_LIMEFluidProperties()));
    public static final RegistryObject<LiquidBlock> CONDENSE_LIME_FLUID_BLOCK = BLOCKS.register("condense_lime_fluid_block", () ->
            new LiquidBlock(CONDENSE_LIME_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).noLootTable()));


    public static final RegistryObject<FlowingFluid> CONDENSE_PINK_FLUID = FLUIDS.register("condense_pink_fluid", () ->
            new ForgeFlowingFluid.Source(CONDENSE_PINKFluidProperties()));
    public static final RegistryObject<Fluid> CONDENSE_PINK_FLUID_FLOWING = FLUIDS.register("condense_pink_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(CONDENSE_PINKFluidProperties()));
    public static final RegistryObject<LiquidBlock> CONDENSE_PINK_FLUID_BLOCK = BLOCKS.register("condense_pink_fluid_block", () ->
            new LiquidBlock(CONDENSE_PINK_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).noLootTable()));


    public static final RegistryObject<FlowingFluid> CONDENSE_RED_FLUID = FLUIDS.register("condense_red_fluid", () ->
            new ForgeFlowingFluid.Source(CONDENSE_REDFluidProperties()));
    public static final RegistryObject<Fluid> CONDENSE_RED_FLUID_FLOWING = FLUIDS.register("condense_red_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(CONDENSE_REDFluidProperties()));
    public static final RegistryObject<LiquidBlock> CONDENSE_RED_FLUID_BLOCK = BLOCKS.register("condense_red_fluid_block", () ->
            new LiquidBlock(CONDENSE_RED_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).noLootTable()));


    public static final RegistryObject<FlowingFluid> CONDENSE_BLACK_FLUID = FLUIDS.register("condense_black_fluid", () ->
            new ForgeFlowingFluid.Source(CONDENSE_BLACKFluidProperties()));
    public static final RegistryObject<Fluid> CONDENSE_BLACK_FLUID_FLOWING = FLUIDS.register("condense_black_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(CONDENSE_BLACKFluidProperties()));
    public static final RegistryObject<LiquidBlock> CONDENSE_BLACK_FLUID_BLOCK = BLOCKS.register("condense_black_fluid_block", () ->
            new LiquidBlock(CONDENSE_BLACK_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).noLootTable()));


    public static final RegistryObject<FlowingFluid> CONDENSE_BROWN_FLUID = FLUIDS.register("condense_brown_fluid", () ->
            new ForgeFlowingFluid.Source(CONDENSE_BROWNFluidProperties()));
    public static final RegistryObject<Fluid> CONDENSE_BROWN_FLUID_FLOWING = FLUIDS.register("condense_brown_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(CONDENSE_BROWNFluidProperties()));
    public static final RegistryObject<LiquidBlock> CONDENSE_BROWN_FLUID_BLOCK = BLOCKS.register("condense_brown_fluid_block", () ->
            new LiquidBlock(CONDENSE_BROWN_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).noLootTable()));


    public static final RegistryObject<FlowingFluid> CONDENSE_BLUE_FLUID = FLUIDS.register("condense_blue_fluid", () ->
            new ForgeFlowingFluid.Source(CONDENSE_BLUEFluidProperties()));
    public static final RegistryObject<Fluid> CONDENSE_BLUE_FLUID_FLOWING = FLUIDS.register("condense_blue_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(CONDENSE_BLUEFluidProperties()));
    public static final RegistryObject<LiquidBlock> CONDENSE_BLUE_FLUID_BLOCK = BLOCKS.register("condense_blue_fluid_block", () ->
            new LiquidBlock(CONDENSE_BLUE_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).noLootTable()));


    public static final RegistryObject<FlowingFluid> CONDENSE_CYAN_FLUID = FLUIDS.register("condense_cyan_fluid", () ->
            new ForgeFlowingFluid.Source(CONDENSE_CYANFluidProperties()));
    public static final RegistryObject<Fluid> CONDENSE_CYAN_FLUID_FLOWING = FLUIDS.register("condense_cyan_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(CONDENSE_CYANFluidProperties()));
    public static final RegistryObject<LiquidBlock> CONDENSE_CYAN_FLUID_BLOCK = BLOCKS.register("condense_cyan_fluid_block", () ->
            new LiquidBlock(CONDENSE_CYAN_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).noLootTable()));


    public static final RegistryObject<FlowingFluid> CONDENSE_LIGHT_GRAY_FLUID = FLUIDS.register("condense_light_gray_fluid", () ->
            new ForgeFlowingFluid.Source(CONDENSE_LIGHT_GRAYFluidProperties()));
    public static final RegistryObject<Fluid> CONDENSE_LIGHT_GRAY_FLUID_FLOWING = FLUIDS.register("condense_light_gray_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(CONDENSE_LIGHT_GRAYFluidProperties()));
    public static final RegistryObject<LiquidBlock> CONDENSE_LIGHT_GRAY_FLUID_BLOCK = BLOCKS.register("condense_light_gray_fluid_block", () ->
            new LiquidBlock(CONDENSE_LIGHT_GRAY_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).noLootTable()));


    public static final RegistryObject<FlowingFluid> CONDENSE_GRAY_FLUID = FLUIDS.register("condense_gray_fluid", () ->
            new ForgeFlowingFluid.Source(CONDENSE_GRAYFluidProperties()));
    public static final RegistryObject<Fluid> CONDENSE_GRAY_FLUID_FLOWING = FLUIDS.register("condense_gray_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(CONDENSE_GRAYFluidProperties()));
    public static final RegistryObject<LiquidBlock> CONDENSE_GRAY_FLUID_BLOCK = BLOCKS.register("condense_gray_fluid_block", () ->
            new LiquidBlock(CONDENSE_GRAY_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).noLootTable()));


    public static final RegistryObject<FlowingFluid> CONDENSE_LIGHT_BLUE_FLUID = FLUIDS.register("condense_light_blue_fluid", () ->
            new ForgeFlowingFluid.Source(CONDENSE_LIGHT_BLUEFluidProperties()));
    public static final RegistryObject<Fluid> CONDENSE_LIGHT_BLUE_FLUID_FLOWING = FLUIDS.register("condense_light_blue_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(CONDENSE_LIGHT_BLUEFluidProperties()));
    public static final RegistryObject<LiquidBlock> CONDENSE_LIGHT_BLUE_FLUID_BLOCK = BLOCKS.register("condense_light_blue_fluid_block", () ->
            new LiquidBlock(CONDENSE_LIGHT_BLUE_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).noLootTable()));


    public static final RegistryObject<FlowingFluid> CONDENSE_ORANGE_FLUID = FLUIDS.register("condense_orange_fluid", () ->
            new ForgeFlowingFluid.Source(CONDENSE_ORANGEFluidProperties()));
    public static final RegistryObject<Fluid> CONDENSE_ORANGE_FLUID_FLOWING = FLUIDS.register("condense_orange_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(CONDENSE_ORANGEFluidProperties()));
    public static final RegistryObject<LiquidBlock> CONDENSE_ORANGE_FLUID_BLOCK = BLOCKS.register("condense_orange_fluid_block", () ->
            new LiquidBlock(CONDENSE_ORANGE_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).noLootTable()));


    public static final RegistryObject<FlowingFluid> CONDENSE_WHITE_FLUID = FLUIDS.register("condense_white_fluid", () ->
            new ForgeFlowingFluid.Source(CONDENSE_WHITEFluidProperties()));
    public static final RegistryObject<Fluid> CONDENSE_WHITE_FLUID_FLOWING = FLUIDS.register("condense_white_fluid_flowing", () ->
            new ForgeFlowingFluid.Flowing(CONDENSE_WHITEFluidProperties()));
    public static final RegistryObject<LiquidBlock> CONDENSE_WHITE_FLUID_BLOCK = BLOCKS.register("condense_white_fluid_block", () ->
            new LiquidBlock(CONDENSE_WHITE_FLUID, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().liquid().pushReaction(PushReaction.DESTROY).noCollission().strength(100.0F).noLootTable()));

}
