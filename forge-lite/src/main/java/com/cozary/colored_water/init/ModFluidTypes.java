package com.cozary.colored_water.init;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.block.entity.WaterloggedColorBlockEntity;
import com.cozary.colored_water.client.ColoredWaterFog;
import com.cozary.colored_water.client.ColoredWaterOverlayRenderer;
import com.cozary.colored_water.fluids.BaseColorWater;
import com.cozary.colored_water.fluids.ColorWaterFluid;
import com.cozary.colored_water.util.ColoredWaterUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ModFluidTypes {

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, ColoredWater.MOD_ID);

    public static final RegistryObject<FluidType> BLACK_WATER_TYPE = registerColoredWaterType("black_water_type", 0xb41D1D21);
    public static final RegistryObject<FluidType> BLUE_WATER_TYPE = registerColoredWaterType("blue_water_type", 0xb43C44AA);
    public static final RegistryObject<FluidType> BROWN_WATER_TYPE = registerColoredWaterType("brown_water_type", 0xb4835432);
    public static final RegistryObject<FluidType> CYAN_WATER_TYPE = registerColoredWaterType("cyan_water_type", 0xb4169C9C);
    public static final RegistryObject<FluidType> GRAY_WATER_TYPE = registerColoredWaterType("gray_water_type", 0xb4474F52);
    public static final RegistryObject<FluidType> GREEN_WATER_TYPE = registerColoredWaterType("green_water_type", 0xb45E7C16);
    public static final RegistryObject<FluidType> LIGHT_BLUE_WATER_TYPE = registerColoredWaterType("light_blue_water_type", 0xb43AB3DA);
    public static final RegistryObject<FluidType> LIGHT_GRAY_WATER_TYPE = registerColoredWaterType("light_gray_water_type", 0xb49D9D97);
    public static final RegistryObject<FluidType> LIME_WATER_TYPE = registerColoredWaterType("lime_water_type", 0xb480C71F);
    public static final RegistryObject<FluidType> MAGENTA_WATER_TYPE = registerColoredWaterType("magenta_water_type", 0xb4C74EBD);
    public static final RegistryObject<FluidType> ORANGE_WATER_TYPE = registerColoredWaterType("orange_water_type", 0xb4F9801D);
    public static final RegistryObject<FluidType> PINK_WATER_TYPE = registerColoredWaterType("pink_water_type", 0xb4F38BAA);
    public static final RegistryObject<FluidType> PURPLE_WATER_TYPE = registerColoredWaterType("purple_water_type", 0xb48932B8);
    public static final RegistryObject<FluidType> RED_WATER_TYPE = registerColoredWaterType("red_water_type", 0xb4B02E26);
    public static final RegistryObject<FluidType> WHITE_WATER_TYPE = registerColoredWaterType("white_water_type", 0xb4F9FFFE);
    public static final RegistryObject<FluidType> YELLOW_WATER_TYPE = registerColoredWaterType("yellow_water_type", 0xb4FED83D);
    public static final RegistryObject<FluidType> CONDENSE_BLACK_WATER_TYPE = registerColoredWaterType("condense_black_water_type", 0xff1D1D21);
    public static final RegistryObject<FluidType> CONDENSE_BLUE_WATER_TYPE = registerColoredWaterType("condense_blue_water_type", 0xff3C44AA);
    public static final RegistryObject<FluidType> CONDENSE_BROWN_WATER_TYPE = registerColoredWaterType("condense_brown_water_type", 0xff835432);
    public static final RegistryObject<FluidType> CONDENSE_CYAN_WATER_TYPE = registerColoredWaterType("condense_cyan_water_type", 0xff169C9C);
    public static final RegistryObject<FluidType> CONDENSE_GRAY_WATER_TYPE = registerColoredWaterType("condense_gray_water_type", 0xff474F52);
    public static final RegistryObject<FluidType> CONDENSE_GREEN_WATER_TYPE = registerColoredWaterType("condense_green_water_type", 0xff5E7C16);
    public static final RegistryObject<FluidType> CONDENSE_LIGHT_BLUE_WATER_TYPE = registerColoredWaterType("condense_light_blue_water_type", 0xff3AB3DA);
    public static final RegistryObject<FluidType> CONDENSE_LIGHT_GRAY_WATER_TYPE = registerColoredWaterType("condense_light_gray_water_type", 0xff9D9D97);
    public static final RegistryObject<FluidType> CONDENSE_LIME_WATER_TYPE = registerColoredWaterType("condense_lime_water_type", 0xff80C71F);
    public static final RegistryObject<FluidType> CONDENSE_MAGENTA_WATER_TYPE = registerColoredWaterType("condense_magenta_water_type", 0xffC74EBD);
    public static final RegistryObject<FluidType> CONDENSE_ORANGE_WATER_TYPE = registerColoredWaterType("condense_orange_water_type", 0xffF9801D);
    public static final RegistryObject<FluidType> CONDENSE_PINK_WATER_TYPE = registerColoredWaterType("condense_pink_water_type", 0xffF38BAA);
    public static final RegistryObject<FluidType> CONDENSE_PURPLE_WATER_TYPE = registerColoredWaterType("condense_purple_water_type", 0xff8932B8);
    public static final RegistryObject<FluidType> CONDENSE_RED_WATER_TYPE = registerColoredWaterType("condense_red_water_type", 0xffB02E26);
    public static final RegistryObject<FluidType> CONDENSE_WHITE_WATER_TYPE = registerColoredWaterType("condense_white_water_type", 0xffF9FFFE);
    public static final RegistryObject<FluidType> CONDENSE_YELLOW_WATER_TYPE = registerColoredWaterType("condense_yellow_water_type", 0xffFED83D);
    public static final RegistryObject<FluidType> LUMINOUS_BLACK_WATER_TYPE = registerColoredWaterType("luminous_black_water_type", 0xb41D1D21);
    public static final RegistryObject<FluidType> LUMINOUS_BLUE_WATER_TYPE = registerColoredWaterType("luminous_blue_water_type", 0xb43C44AA);
    public static final RegistryObject<FluidType> LUMINOUS_BROWN_WATER_TYPE = registerColoredWaterType("luminous_brown_water_type", 0xb4835432);
    public static final RegistryObject<FluidType> LUMINOUS_CYAN_WATER_TYPE = registerColoredWaterType("luminous_cyan_water_type", 0xb4169C9C);
    public static final RegistryObject<FluidType> LUMINOUS_GRAY_WATER_TYPE = registerColoredWaterType("luminous_gray_water_type", 0xb4474F52);
    public static final RegistryObject<FluidType> LUMINOUS_GREEN_WATER_TYPE = registerColoredWaterType("luminous_green_water_type", 0xb45E7C16);
    public static final RegistryObject<FluidType> LUMINOUS_LIGHT_BLUE_WATER_TYPE = registerColoredWaterType("luminous_light_blue_water_type", 0xb43AB3DA);
    public static final RegistryObject<FluidType> LUMINOUS_LIGHT_GRAY_WATER_TYPE = registerColoredWaterType("luminous_light_gray_water_type", 0xb49D9D97);
    public static final RegistryObject<FluidType> LUMINOUS_LIME_WATER_TYPE = registerColoredWaterType("luminous_lime_water_type", 0xb480C71F);
    public static final RegistryObject<FluidType> LUMINOUS_MAGENTA_WATER_TYPE = registerColoredWaterType("luminous_magenta_water_type", 0xb4C74EBD);
    public static final RegistryObject<FluidType> LUMINOUS_ORANGE_WATER_TYPE = registerColoredWaterType("luminous_orange_water_type", 0xb4F9801D);
    public static final RegistryObject<FluidType> LUMINOUS_PINK_WATER_TYPE = registerColoredWaterType("luminous_pink_water_type", 0xb4F38BAA);
    public static final RegistryObject<FluidType> LUMINOUS_PURPLE_WATER_TYPE = registerColoredWaterType("luminous_purple_water_type", 0xb48932B8);
    public static final RegistryObject<FluidType> LUMINOUS_RED_WATER_TYPE = registerColoredWaterType("luminous_red_water_type", 0xb4B02E26);
    public static final RegistryObject<FluidType> LUMINOUS_WHITE_WATER_TYPE = registerColoredWaterType("luminous_white_water_type", 0xb4F9FFFE);
    public static final RegistryObject<FluidType> LUMINOUS_YELLOW_WATER_TYPE = registerColoredWaterType("luminous_yellow_water_type", 0xb4FED83D);
    public static final RegistryObject<FluidType> LUMINOUS_CONDENSE_BLACK_WATER_TYPE = registerColoredWaterType("luminous_condense_black_water_type", 0xff1D1D21);
    public static final RegistryObject<FluidType> LUMINOUS_CONDENSE_BLUE_WATER_TYPE = registerColoredWaterType("luminous_condense_blue_water_type", 0xff3C44AA);
    public static final RegistryObject<FluidType> LUMINOUS_CONDENSE_BROWN_WATER_TYPE = registerColoredWaterType("luminous_condense_brown_water_type", 0xff835432);
    public static final RegistryObject<FluidType> LUMINOUS_CONDENSE_CYAN_WATER_TYPE = registerColoredWaterType("luminous_condense_cyan_water_type", 0xff169C9C);
    public static final RegistryObject<FluidType> LUMINOUS_CONDENSE_GRAY_WATER_TYPE = registerColoredWaterType("luminous_condense_gray_water_type", 0xff474F52);
    public static final RegistryObject<FluidType> LUMINOUS_CONDENSE_GREEN_WATER_TYPE = registerColoredWaterType("luminous_condense_green_water_type", 0xff5E7C16);
    public static final RegistryObject<FluidType> LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_TYPE = registerColoredWaterType("luminous_condense_light_blue_water_type", 0xff3AB3DA);
    public static final RegistryObject<FluidType> LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_TYPE = registerColoredWaterType("luminous_condense_light_gray_water_type", 0xff9D9D97);
    public static final RegistryObject<FluidType> LUMINOUS_CONDENSE_LIME_WATER_TYPE = registerColoredWaterType("luminous_condense_lime_water_type", 0xff80C71F);
    public static final RegistryObject<FluidType> LUMINOUS_CONDENSE_MAGENTA_WATER_TYPE = registerColoredWaterType("luminous_condense_magenta_water_type", 0xffC74EBD);
    public static final RegistryObject<FluidType> LUMINOUS_CONDENSE_ORANGE_WATER_TYPE = registerColoredWaterType("luminous_condense_orange_water_type", 0xffF9801D);
    public static final RegistryObject<FluidType> LUMINOUS_CONDENSE_PINK_WATER_TYPE = registerColoredWaterType("luminous_condense_pink_water_type", 0xffF38BAA);
    public static final RegistryObject<FluidType> LUMINOUS_CONDENSE_PURPLE_WATER_TYPE = registerColoredWaterType("luminous_condense_purple_water_type", 0xff8932B8);
    public static final RegistryObject<FluidType> LUMINOUS_CONDENSE_RED_WATER_TYPE = registerColoredWaterType("luminous_condense_red_water_type", 0xffB02E26);
    public static final RegistryObject<FluidType> LUMINOUS_CONDENSE_WHITE_WATER_TYPE = registerColoredWaterType("luminous_condense_white_water_type", 0xffF9FFFE);
    public static final RegistryObject<FluidType> LUMINOUS_CONDENSE_YELLOW_WATER_TYPE = registerColoredWaterType("luminous_condense_yellow_water_type", 0xffFED83D);

    private static RegistryObject<FluidType> registerColoredWaterType(String name, int color) {
        return FLUID_TYPES.register(name, () -> new FluidType(createFluidTypeProperties()) {
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
                    public int getTintColor() {
                        return color;
                    }

                    @Override
                    public int getTintColor(FluidState state, BlockAndTintGetter getter, BlockPos pos) {
                        if (getter != null && pos != null) {
                            BlockEntity blockEntity = getter.getBlockEntity(pos);
                            if (blockEntity instanceof WaterloggedColorBlockEntity waterloggedBe && waterloggedBe.hasFluid()) {
                                int rgb = waterloggedBe.getFluid().getTintColor();
                                int alpha = waterloggedBe.getFluid().isCondensed() ? ColoredWaterUtil.CONDENSED_ALPHA : ColoredWaterUtil.DEFAULT_ALPHA;
                                return ColoredWaterUtil.withAlpha(rgb, alpha);
                            }
                        }
                        if (state != null && state.getType() instanceof ColorWaterFluid cwf) {
                            int rgb = cwf.getTintColor();
                            int alpha = cwf.isCondensed() ? ColoredWaterUtil.CONDENSED_ALPHA : ColoredWaterUtil.DEFAULT_ALPHA;
                            return ColoredWaterUtil.withAlpha(rgb, alpha);
                        }
                        return color;
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

    public static FluidType.Properties createFluidTypeProperties() {
        return FluidType.Properties.create()
                .canExtinguish(true)
                .canConvertToSource(true)
                .supportsBoating(true)
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
                .canHydrate(true)
                .canSwim(true)
                .canDrown(true)
                .pathType(PathType.WATER)
                .density(1024)
                .viscosity(1024);
    }

    private static final Map<Fluid, Supplier<FluidType>> FLUID_TO_TYPE = new IdentityHashMap<>();

    private static synchronized void initMap() {
        if (!FLUID_TO_TYPE.isEmpty()) return;
        map(ModFluids.STILL_BLACK_WATER, BLACK_WATER_TYPE);
        map(ModFluids.FLOWING_BLACK_WATER, BLACK_WATER_TYPE);
        map(ModFluids.STILL_BLUE_WATER, BLUE_WATER_TYPE);
        map(ModFluids.FLOWING_BLUE_WATER, BLUE_WATER_TYPE);
        map(ModFluids.STILL_BROWN_WATER, BROWN_WATER_TYPE);
        map(ModFluids.FLOWING_BROWN_WATER, BROWN_WATER_TYPE);
        map(ModFluids.STILL_CYAN_WATER, CYAN_WATER_TYPE);
        map(ModFluids.FLOWING_CYAN_WATER, CYAN_WATER_TYPE);
        map(ModFluids.STILL_GRAY_WATER, GRAY_WATER_TYPE);
        map(ModFluids.FLOWING_GRAY_WATER, GRAY_WATER_TYPE);
        map(ModFluids.STILL_GREEN_WATER, GREEN_WATER_TYPE);
        map(ModFluids.FLOWING_GREEN_WATER, GREEN_WATER_TYPE);
        map(ModFluids.STILL_LIGHT_BLUE_WATER, LIGHT_BLUE_WATER_TYPE);
        map(ModFluids.FLOWING_LIGHT_BLUE_WATER, LIGHT_BLUE_WATER_TYPE);
        map(ModFluids.STILL_LIGHT_GRAY_WATER, LIGHT_GRAY_WATER_TYPE);
        map(ModFluids.FLOWING_LIGHT_GRAY_WATER, LIGHT_GRAY_WATER_TYPE);
        map(ModFluids.STILL_LIME_WATER, LIME_WATER_TYPE);
        map(ModFluids.FLOWING_LIME_WATER, LIME_WATER_TYPE);
        map(ModFluids.STILL_MAGENTA_WATER, MAGENTA_WATER_TYPE);
        map(ModFluids.FLOWING_MAGENTA_WATER, MAGENTA_WATER_TYPE);
        map(ModFluids.STILL_ORANGE_WATER, ORANGE_WATER_TYPE);
        map(ModFluids.FLOWING_ORANGE_WATER, ORANGE_WATER_TYPE);
        map(ModFluids.STILL_PINK_WATER, PINK_WATER_TYPE);
        map(ModFluids.FLOWING_PINK_WATER, PINK_WATER_TYPE);
        map(ModFluids.STILL_PURPLE_WATER, PURPLE_WATER_TYPE);
        map(ModFluids.FLOWING_PURPLE_WATER, PURPLE_WATER_TYPE);
        map(ModFluids.STILL_RED_WATER, RED_WATER_TYPE);
        map(ModFluids.FLOWING_RED_WATER, RED_WATER_TYPE);
        map(ModFluids.STILL_WHITE_WATER, WHITE_WATER_TYPE);
        map(ModFluids.FLOWING_WHITE_WATER, WHITE_WATER_TYPE);
        map(ModFluids.STILL_YELLOW_WATER, YELLOW_WATER_TYPE);
        map(ModFluids.FLOWING_YELLOW_WATER, YELLOW_WATER_TYPE);

        map(ModFluids.STILL_CONDENSE_BLACK_WATER, CONDENSE_BLACK_WATER_TYPE);
        map(ModFluids.FLOWING_CONDENSE_BLACK_WATER, CONDENSE_BLACK_WATER_TYPE);
        map(ModFluids.STILL_CONDENSE_BLUE_WATER, CONDENSE_BLUE_WATER_TYPE);
        map(ModFluids.FLOWING_CONDENSE_BLUE_WATER, CONDENSE_BLUE_WATER_TYPE);
        map(ModFluids.STILL_CONDENSE_BROWN_WATER, CONDENSE_BROWN_WATER_TYPE);
        map(ModFluids.FLOWING_CONDENSE_BROWN_WATER, CONDENSE_BROWN_WATER_TYPE);
        map(ModFluids.STILL_CONDENSE_CYAN_WATER, CONDENSE_CYAN_WATER_TYPE);
        map(ModFluids.FLOWING_CONDENSE_CYAN_WATER, CONDENSE_CYAN_WATER_TYPE);
        map(ModFluids.STILL_CONDENSE_GRAY_WATER, CONDENSE_GRAY_WATER_TYPE);
        map(ModFluids.FLOWING_CONDENSE_GRAY_WATER, CONDENSE_GRAY_WATER_TYPE);
        map(ModFluids.STILL_CONDENSE_GREEN_WATER, CONDENSE_GREEN_WATER_TYPE);
        map(ModFluids.FLOWING_CONDENSE_GREEN_WATER, CONDENSE_GREEN_WATER_TYPE);
        map(ModFluids.STILL_CONDENSE_LIGHT_BLUE_WATER, CONDENSE_LIGHT_BLUE_WATER_TYPE);
        map(ModFluids.FLOWING_CONDENSE_LIGHT_BLUE_WATER, CONDENSE_LIGHT_BLUE_WATER_TYPE);
        map(ModFluids.STILL_CONDENSE_LIGHT_GRAY_WATER, CONDENSE_LIGHT_GRAY_WATER_TYPE);
        map(ModFluids.FLOWING_CONDENSE_LIGHT_GRAY_WATER, CONDENSE_LIGHT_GRAY_WATER_TYPE);
        map(ModFluids.STILL_CONDENSE_LIME_WATER, CONDENSE_LIME_WATER_TYPE);
        map(ModFluids.FLOWING_CONDENSE_LIME_WATER, CONDENSE_LIME_WATER_TYPE);
        map(ModFluids.STILL_CONDENSE_MAGENTA_WATER, CONDENSE_MAGENTA_WATER_TYPE);
        map(ModFluids.FLOWING_CONDENSE_MAGENTA_WATER, CONDENSE_MAGENTA_WATER_TYPE);
        map(ModFluids.STILL_CONDENSE_ORANGE_WATER, CONDENSE_ORANGE_WATER_TYPE);
        map(ModFluids.FLOWING_CONDENSE_ORANGE_WATER, CONDENSE_ORANGE_WATER_TYPE);
        map(ModFluids.STILL_CONDENSE_PINK_WATER, CONDENSE_PINK_WATER_TYPE);
        map(ModFluids.FLOWING_CONDENSE_PINK_WATER, CONDENSE_PINK_WATER_TYPE);
        map(ModFluids.STILL_CONDENSE_PURPLE_WATER, CONDENSE_PURPLE_WATER_TYPE);
        map(ModFluids.FLOWING_CONDENSE_PURPLE_WATER, CONDENSE_PURPLE_WATER_TYPE);
        map(ModFluids.STILL_CONDENSE_RED_WATER, CONDENSE_RED_WATER_TYPE);
        map(ModFluids.FLOWING_CONDENSE_RED_WATER, CONDENSE_RED_WATER_TYPE);
        map(ModFluids.STILL_CONDENSE_WHITE_WATER, CONDENSE_WHITE_WATER_TYPE);
        map(ModFluids.FLOWING_CONDENSE_WHITE_WATER, CONDENSE_WHITE_WATER_TYPE);
        map(ModFluids.STILL_CONDENSE_YELLOW_WATER, CONDENSE_YELLOW_WATER_TYPE);
        map(ModFluids.FLOWING_CONDENSE_YELLOW_WATER, CONDENSE_YELLOW_WATER_TYPE);

        map(ModFluids.STILL_LUMINOUS_BLACK_WATER, LUMINOUS_BLACK_WATER_TYPE);
        map(ModFluids.FLOWING_LUMINOUS_BLACK_WATER, LUMINOUS_BLACK_WATER_TYPE);
        map(ModFluids.STILL_LUMINOUS_BLUE_WATER, LUMINOUS_BLUE_WATER_TYPE);
        map(ModFluids.FLOWING_LUMINOUS_BLUE_WATER, LUMINOUS_BLUE_WATER_TYPE);
        map(ModFluids.STILL_LUMINOUS_BROWN_WATER, LUMINOUS_BROWN_WATER_TYPE);
        map(ModFluids.FLOWING_LUMINOUS_BROWN_WATER, LUMINOUS_BROWN_WATER_TYPE);
        map(ModFluids.STILL_LUMINOUS_CYAN_WATER, LUMINOUS_CYAN_WATER_TYPE);
        map(ModFluids.FLOWING_LUMINOUS_CYAN_WATER, LUMINOUS_CYAN_WATER_TYPE);
        map(ModFluids.STILL_LUMINOUS_GRAY_WATER, LUMINOUS_GRAY_WATER_TYPE);
        map(ModFluids.FLOWING_LUMINOUS_GRAY_WATER, LUMINOUS_GRAY_WATER_TYPE);
        map(ModFluids.STILL_LUMINOUS_GREEN_WATER, LUMINOUS_GREEN_WATER_TYPE);
        map(ModFluids.FLOWING_LUMINOUS_GREEN_WATER, LUMINOUS_GREEN_WATER_TYPE);
        map(ModFluids.STILL_LUMINOUS_LIGHT_BLUE_WATER, LUMINOUS_LIGHT_BLUE_WATER_TYPE);
        map(ModFluids.FLOWING_LUMINOUS_LIGHT_BLUE_WATER, LUMINOUS_LIGHT_BLUE_WATER_TYPE);
        map(ModFluids.STILL_LUMINOUS_LIGHT_GRAY_WATER, LUMINOUS_LIGHT_GRAY_WATER_TYPE);
        map(ModFluids.FLOWING_LUMINOUS_LIGHT_GRAY_WATER, LUMINOUS_LIGHT_GRAY_WATER_TYPE);
        map(ModFluids.STILL_LUMINOUS_LIME_WATER, LUMINOUS_LIME_WATER_TYPE);
        map(ModFluids.FLOWING_LUMINOUS_LIME_WATER, LUMINOUS_LIME_WATER_TYPE);
        map(ModFluids.STILL_LUMINOUS_MAGENTA_WATER, LUMINOUS_MAGENTA_WATER_TYPE);
        map(ModFluids.FLOWING_LUMINOUS_MAGENTA_WATER, LUMINOUS_MAGENTA_WATER_TYPE);
        map(ModFluids.STILL_LUMINOUS_ORANGE_WATER, LUMINOUS_ORANGE_WATER_TYPE);
        map(ModFluids.FLOWING_LUMINOUS_ORANGE_WATER, LUMINOUS_ORANGE_WATER_TYPE);
        map(ModFluids.STILL_LUMINOUS_PINK_WATER, LUMINOUS_PINK_WATER_TYPE);
        map(ModFluids.FLOWING_LUMINOUS_PINK_WATER, LUMINOUS_PINK_WATER_TYPE);
        map(ModFluids.STILL_LUMINOUS_PURPLE_WATER, LUMINOUS_PURPLE_WATER_TYPE);
        map(ModFluids.FLOWING_LUMINOUS_PURPLE_WATER, LUMINOUS_PURPLE_WATER_TYPE);
        map(ModFluids.STILL_LUMINOUS_RED_WATER, LUMINOUS_RED_WATER_TYPE);
        map(ModFluids.FLOWING_LUMINOUS_RED_WATER, LUMINOUS_RED_WATER_TYPE);
        map(ModFluids.STILL_LUMINOUS_WHITE_WATER, LUMINOUS_WHITE_WATER_TYPE);
        map(ModFluids.FLOWING_LUMINOUS_WHITE_WATER, LUMINOUS_WHITE_WATER_TYPE);
        map(ModFluids.STILL_LUMINOUS_YELLOW_WATER, LUMINOUS_YELLOW_WATER_TYPE);
        map(ModFluids.FLOWING_LUMINOUS_YELLOW_WATER, LUMINOUS_YELLOW_WATER_TYPE);

        map(ModFluids.STILL_LUMINOUS_CONDENSE_BLACK_WATER, LUMINOUS_CONDENSE_BLACK_WATER_TYPE);
        map(ModFluids.FLOWING_LUMINOUS_CONDENSE_BLACK_WATER, LUMINOUS_CONDENSE_BLACK_WATER_TYPE);
        map(ModFluids.STILL_LUMINOUS_CONDENSE_BLUE_WATER, LUMINOUS_CONDENSE_BLUE_WATER_TYPE);
        map(ModFluids.FLOWING_LUMINOUS_CONDENSE_BLUE_WATER, LUMINOUS_CONDENSE_BLUE_WATER_TYPE);
        map(ModFluids.STILL_LUMINOUS_CONDENSE_BROWN_WATER, LUMINOUS_CONDENSE_BROWN_WATER_TYPE);
        map(ModFluids.FLOWING_LUMINOUS_CONDENSE_BROWN_WATER, LUMINOUS_CONDENSE_BROWN_WATER_TYPE);
        map(ModFluids.STILL_LUMINOUS_CONDENSE_CYAN_WATER, LUMINOUS_CONDENSE_CYAN_WATER_TYPE);
        map(ModFluids.FLOWING_LUMINOUS_CONDENSE_CYAN_WATER, LUMINOUS_CONDENSE_CYAN_WATER_TYPE);
        map(ModFluids.STILL_LUMINOUS_CONDENSE_GRAY_WATER, LUMINOUS_CONDENSE_GRAY_WATER_TYPE);
        map(ModFluids.FLOWING_LUMINOUS_CONDENSE_GRAY_WATER, LUMINOUS_CONDENSE_GRAY_WATER_TYPE);
        map(ModFluids.STILL_LUMINOUS_CONDENSE_GREEN_WATER, LUMINOUS_CONDENSE_GREEN_WATER_TYPE);
        map(ModFluids.FLOWING_LUMINOUS_CONDENSE_GREEN_WATER, LUMINOUS_CONDENSE_GREEN_WATER_TYPE);
        map(ModFluids.STILL_LUMINOUS_CONDENSE_LIGHT_BLUE_WATER, LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_TYPE);
        map(ModFluids.FLOWING_LUMINOUS_CONDENSE_LIGHT_BLUE_WATER, LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_TYPE);
        map(ModFluids.STILL_LUMINOUS_CONDENSE_LIGHT_GRAY_WATER, LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_TYPE);
        map(ModFluids.FLOWING_LUMINOUS_CONDENSE_LIGHT_GRAY_WATER, LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_TYPE);
        map(ModFluids.STILL_LUMINOUS_CONDENSE_LIME_WATER, LUMINOUS_CONDENSE_LIME_WATER_TYPE);
        map(ModFluids.FLOWING_LUMINOUS_CONDENSE_LIME_WATER, LUMINOUS_CONDENSE_LIME_WATER_TYPE);
        map(ModFluids.STILL_LUMINOUS_CONDENSE_MAGENTA_WATER, LUMINOUS_CONDENSE_MAGENTA_WATER_TYPE);
        map(ModFluids.FLOWING_LUMINOUS_CONDENSE_MAGENTA_WATER, LUMINOUS_CONDENSE_MAGENTA_WATER_TYPE);
        map(ModFluids.STILL_LUMINOUS_CONDENSE_ORANGE_WATER, LUMINOUS_CONDENSE_ORANGE_WATER_TYPE);
        map(ModFluids.FLOWING_LUMINOUS_CONDENSE_ORANGE_WATER, LUMINOUS_CONDENSE_ORANGE_WATER_TYPE);
        map(ModFluids.STILL_LUMINOUS_CONDENSE_PINK_WATER, LUMINOUS_CONDENSE_PINK_WATER_TYPE);
        map(ModFluids.FLOWING_LUMINOUS_CONDENSE_PINK_WATER, LUMINOUS_CONDENSE_PINK_WATER_TYPE);
        map(ModFluids.STILL_LUMINOUS_CONDENSE_PURPLE_WATER, LUMINOUS_CONDENSE_PURPLE_WATER_TYPE);
        map(ModFluids.FLOWING_LUMINOUS_CONDENSE_PURPLE_WATER, LUMINOUS_CONDENSE_PURPLE_WATER_TYPE);
        map(ModFluids.STILL_LUMINOUS_CONDENSE_RED_WATER, LUMINOUS_CONDENSE_RED_WATER_TYPE);
        map(ModFluids.FLOWING_LUMINOUS_CONDENSE_RED_WATER, LUMINOUS_CONDENSE_RED_WATER_TYPE);
        map(ModFluids.STILL_LUMINOUS_CONDENSE_WHITE_WATER, LUMINOUS_CONDENSE_WHITE_WATER_TYPE);
        map(ModFluids.FLOWING_LUMINOUS_CONDENSE_WHITE_WATER, LUMINOUS_CONDENSE_WHITE_WATER_TYPE);
        map(ModFluids.STILL_LUMINOUS_CONDENSE_YELLOW_WATER, LUMINOUS_CONDENSE_YELLOW_WATER_TYPE);
        map(ModFluids.FLOWING_LUMINOUS_CONDENSE_YELLOW_WATER, LUMINOUS_CONDENSE_YELLOW_WATER_TYPE);
    }

    private static void map(Supplier<? extends Fluid> fluid, Supplier<FluidType> type) {
        FLUID_TO_TYPE.put(fluid.get(), type);
    }

    public static FluidType getFluidTypeFor(BaseColorWater fluid) {
        if (FLUID_TO_TYPE.isEmpty()) {
            initMap();
        }
        Supplier<FluidType> type = FLUID_TO_TYPE.get(fluid);
        return type != null ? type.get() : WHITE_WATER_TYPE.get();
    }

    private static Set<FluidType> ALL_TYPES = null;

    public static boolean isModFluidType(FluidType type) {
        if (type == null) return false;
        Identifier key = ForgeRegistries.FLUID_TYPES.get().getKey(type);
        if (key != null) {
            return key.getNamespace().equals(ColoredWater.MOD_ID);
        }
        if (ALL_TYPES == null || ALL_TYPES.size() < 48) {
            Set<FluidType> set = new HashSet<>();
            for (RegistryObject<FluidType> entry : FLUID_TYPES.getEntries()) {
                try {
                    set.add(entry.get());
                } catch (Exception ignored) {
                }
            }
            if (!set.isEmpty()) {
                ALL_TYPES = set;
            }
        }
        return ALL_TYPES != null && ALL_TYPES.contains(type);
    }
}
