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
import net.minecraft.client.renderer.fog.environment.FogEnvironment;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.PathType;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.joml.Vector4f;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class ModFluidTypes {

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, ColoredWater.MOD_ID);

    public static final Supplier<FluidType> BLACK_WATER_TYPE = registerColoredWaterType("black_water_type");
    public static final Supplier<FluidType> BLUE_WATER_TYPE = registerColoredWaterType("blue_water_type");
    public static final Supplier<FluidType> BROWN_WATER_TYPE = registerColoredWaterType("brown_water_type");
    public static final Supplier<FluidType> CYAN_WATER_TYPE = registerColoredWaterType("cyan_water_type");
    public static final Supplier<FluidType> GRAY_WATER_TYPE = registerColoredWaterType("gray_water_type");
    public static final Supplier<FluidType> GREEN_WATER_TYPE = registerColoredWaterType("green_water_type");
    public static final Supplier<FluidType> LIGHT_BLUE_WATER_TYPE = registerColoredWaterType("light_blue_water_type");
    public static final Supplier<FluidType> LIGHT_GRAY_WATER_TYPE = registerColoredWaterType("light_gray_water_type");
    public static final Supplier<FluidType> LIME_WATER_TYPE = registerColoredWaterType("lime_water_type");
    public static final Supplier<FluidType> MAGENTA_WATER_TYPE = registerColoredWaterType("magenta_water_type");
    public static final Supplier<FluidType> ORANGE_WATER_TYPE = registerColoredWaterType("orange_water_type");
    public static final Supplier<FluidType> PINK_WATER_TYPE = registerColoredWaterType("pink_water_type");
    public static final Supplier<FluidType> PURPLE_WATER_TYPE = registerColoredWaterType("purple_water_type");
    public static final Supplier<FluidType> RED_WATER_TYPE = registerColoredWaterType("red_water_type");
    public static final Supplier<FluidType> WHITE_WATER_TYPE = registerColoredWaterType("white_water_type");
    public static final Supplier<FluidType> YELLOW_WATER_TYPE = registerColoredWaterType("yellow_water_type");
    public static final Supplier<FluidType> CONDENSE_BLACK_WATER_TYPE = registerColoredWaterType("condense_black_water_type");
    public static final Supplier<FluidType> CONDENSE_BLUE_WATER_TYPE = registerColoredWaterType("condense_blue_water_type");
    public static final Supplier<FluidType> CONDENSE_BROWN_WATER_TYPE = registerColoredWaterType("condense_brown_water_type");
    public static final Supplier<FluidType> CONDENSE_CYAN_WATER_TYPE = registerColoredWaterType("condense_cyan_water_type");
    public static final Supplier<FluidType> CONDENSE_GRAY_WATER_TYPE = registerColoredWaterType("condense_gray_water_type");
    public static final Supplier<FluidType> CONDENSE_GREEN_WATER_TYPE = registerColoredWaterType("condense_green_water_type");
    public static final Supplier<FluidType> CONDENSE_LIGHT_BLUE_WATER_TYPE = registerColoredWaterType("condense_light_blue_water_type");
    public static final Supplier<FluidType> CONDENSE_LIGHT_GRAY_WATER_TYPE = registerColoredWaterType("condense_light_gray_water_type");
    public static final Supplier<FluidType> CONDENSE_LIME_WATER_TYPE = registerColoredWaterType("condense_lime_water_type");
    public static final Supplier<FluidType> CONDENSE_MAGENTA_WATER_TYPE = registerColoredWaterType("condense_magenta_water_type");
    public static final Supplier<FluidType> CONDENSE_ORANGE_WATER_TYPE = registerColoredWaterType("condense_orange_water_type");
    public static final Supplier<FluidType> CONDENSE_PINK_WATER_TYPE = registerColoredWaterType("condense_pink_water_type");
    public static final Supplier<FluidType> CONDENSE_PURPLE_WATER_TYPE = registerColoredWaterType("condense_purple_water_type");
    public static final Supplier<FluidType> CONDENSE_RED_WATER_TYPE = registerColoredWaterType("condense_red_water_type");
    public static final Supplier<FluidType> CONDENSE_WHITE_WATER_TYPE = registerColoredWaterType("condense_white_water_type");
    public static final Supplier<FluidType> CONDENSE_YELLOW_WATER_TYPE = registerColoredWaterType("condense_yellow_water_type");
    public static final Supplier<FluidType> LUMINOUS_BLACK_WATER_TYPE = registerColoredWaterType("luminous_black_water_type");
    public static final Supplier<FluidType> LUMINOUS_BLUE_WATER_TYPE = registerColoredWaterType("luminous_blue_water_type");
    public static final Supplier<FluidType> LUMINOUS_BROWN_WATER_TYPE = registerColoredWaterType("luminous_brown_water_type");
    public static final Supplier<FluidType> LUMINOUS_CYAN_WATER_TYPE = registerColoredWaterType("luminous_cyan_water_type");
    public static final Supplier<FluidType> LUMINOUS_GRAY_WATER_TYPE = registerColoredWaterType("luminous_gray_water_type");
    public static final Supplier<FluidType> LUMINOUS_GREEN_WATER_TYPE = registerColoredWaterType("luminous_green_water_type");
    public static final Supplier<FluidType> LUMINOUS_LIGHT_BLUE_WATER_TYPE = registerColoredWaterType("luminous_light_blue_water_type");
    public static final Supplier<FluidType> LUMINOUS_LIGHT_GRAY_WATER_TYPE = registerColoredWaterType("luminous_light_gray_water_type");
    public static final Supplier<FluidType> LUMINOUS_LIME_WATER_TYPE = registerColoredWaterType("luminous_lime_water_type");
    public static final Supplier<FluidType> LUMINOUS_MAGENTA_WATER_TYPE = registerColoredWaterType("luminous_magenta_water_type");
    public static final Supplier<FluidType> LUMINOUS_ORANGE_WATER_TYPE = registerColoredWaterType("luminous_orange_water_type");
    public static final Supplier<FluidType> LUMINOUS_PINK_WATER_TYPE = registerColoredWaterType("luminous_pink_water_type");
    public static final Supplier<FluidType> LUMINOUS_PURPLE_WATER_TYPE = registerColoredWaterType("luminous_purple_water_type");
    public static final Supplier<FluidType> LUMINOUS_RED_WATER_TYPE = registerColoredWaterType("luminous_red_water_type");
    public static final Supplier<FluidType> LUMINOUS_WHITE_WATER_TYPE = registerColoredWaterType("luminous_white_water_type");
    public static final Supplier<FluidType> LUMINOUS_YELLOW_WATER_TYPE = registerColoredWaterType("luminous_yellow_water_type");
    public static final Supplier<FluidType> LUMINOUS_CONDENSE_BLACK_WATER_TYPE = registerColoredWaterType("luminous_condense_black_water_type");
    public static final Supplier<FluidType> LUMINOUS_CONDENSE_BLUE_WATER_TYPE = registerColoredWaterType("luminous_condense_blue_water_type");
    public static final Supplier<FluidType> LUMINOUS_CONDENSE_BROWN_WATER_TYPE = registerColoredWaterType("luminous_condense_brown_water_type");
    public static final Supplier<FluidType> LUMINOUS_CONDENSE_CYAN_WATER_TYPE = registerColoredWaterType("luminous_condense_cyan_water_type");
    public static final Supplier<FluidType> LUMINOUS_CONDENSE_GRAY_WATER_TYPE = registerColoredWaterType("luminous_condense_gray_water_type");
    public static final Supplier<FluidType> LUMINOUS_CONDENSE_GREEN_WATER_TYPE = registerColoredWaterType("luminous_condense_green_water_type");
    public static final Supplier<FluidType> LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_TYPE = registerColoredWaterType("luminous_condense_light_blue_water_type");
    public static final Supplier<FluidType> LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_TYPE = registerColoredWaterType("luminous_condense_light_gray_water_type");
    public static final Supplier<FluidType> LUMINOUS_CONDENSE_LIME_WATER_TYPE = registerColoredWaterType("luminous_condense_lime_water_type");
    public static final Supplier<FluidType> LUMINOUS_CONDENSE_MAGENTA_WATER_TYPE = registerColoredWaterType("luminous_condense_magenta_water_type");
    public static final Supplier<FluidType> LUMINOUS_CONDENSE_ORANGE_WATER_TYPE = registerColoredWaterType("luminous_condense_orange_water_type");
    public static final Supplier<FluidType> LUMINOUS_CONDENSE_PINK_WATER_TYPE = registerColoredWaterType("luminous_condense_pink_water_type");
    public static final Supplier<FluidType> LUMINOUS_CONDENSE_PURPLE_WATER_TYPE = registerColoredWaterType("luminous_condense_purple_water_type");
    public static final Supplier<FluidType> LUMINOUS_CONDENSE_RED_WATER_TYPE = registerColoredWaterType("luminous_condense_red_water_type");
    public static final Supplier<FluidType> LUMINOUS_CONDENSE_WHITE_WATER_TYPE = registerColoredWaterType("luminous_condense_white_water_type");
    public static final Supplier<FluidType> LUMINOUS_CONDENSE_YELLOW_WATER_TYPE = registerColoredWaterType("luminous_condense_yellow_water_type");

    private static Supplier<FluidType> registerColoredWaterType(String name) {
        return FLUID_TYPES.register(name, () -> new FluidType(createFluidTypeProperties()) {
            @Override
            public boolean getIsWaterLike() {
                return true;
            }

            @Override
            public boolean canDrownIn(net.minecraft.world.entity.LivingEntity entity) {
                return !entity.canBreatheUnderwater();
            }
        });
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
                .viscosity(1024)
                ;
    }

    public static class FluidClientExtensions implements IClientFluidTypeExtensions {
        private static final Identifier STILL_TEXTURE = Identifier.fromNamespaceAndPath(ColoredWater.MOD_ID, "block/water_still");
        private static final Identifier FLOWING_TEXTURE = Identifier.fromNamespaceAndPath(ColoredWater.MOD_ID, "block/water_flowing");
        private final int color;

        public FluidClientExtensions(int color) {
            this.color = color;
        }

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
        public Vector4f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistanceChunks, float bossColorModifier, Vector4f defaultColor) {
            return ColoredWaterFog.getFogColor(camera, partialTick, level, defaultColor);
        }

        @Override
        public void modifyFogRender(Camera camera, FogEnvironment fogEnvironment, float renderDistance, float partialTick, FogData fogData) {
            ColoredWaterFog.modifyFogRender(camera, (ClientLevel) camera.entity().level(), renderDistance, fogData);
        }
    }

    private static Set<FluidType> ALL_TYPES = null;

    public static boolean isModFluidType(FluidType type) {
        if (type == null) return false;
        Identifier key = NeoForgeRegistries.FLUID_TYPES.getKey(type);
        if (key != null) {
            return key.getNamespace().equals(ColoredWater.MOD_ID);
        }
        if (ALL_TYPES == null || ALL_TYPES.size() < 48) {
            Set<FluidType> set = new HashSet<>();
            for (var entry : FLUID_TYPES.getEntries()) {
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
