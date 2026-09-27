package com.cozary.colored_water.init;

import com.cozary.colored_water.ColoredWater;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.pathfinder.PathType;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

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
        return FLUID_TYPES.register(name, () -> new FluidType(createFluidTypeProperties()));
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
        private static final Identifier STILL_TEXTURE = Identifier.fromNamespaceAndPath("minecraft", "block/water_still");
        private static final Identifier FLOWING_TEXTURE = Identifier.fromNamespaceAndPath("minecraft", "block/water_flow");
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
    }
}
