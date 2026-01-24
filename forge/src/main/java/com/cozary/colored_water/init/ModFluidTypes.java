package com.cozary.colored_water.init;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.block.entity.ColoredWaterBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;

public class ModFluidTypes {

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, ColoredWater.MOD_ID);

    public static final RegistryObject<FluidType> BLACK_WATER_TYPE = registerColoredWaterType("black_water_type", 0xff1D1D21);
    public static final RegistryObject<FluidType> BLUE_WATER_TYPE = registerColoredWaterType("blue_water_type", 0xff3C44AA);
    public static final RegistryObject<FluidType> BROWN_WATER_TYPE = registerColoredWaterType("brown_water_type", 0xff835432);
    public static final RegistryObject<FluidType> CYAN_WATER_TYPE = registerColoredWaterType("cyan_water_type", 0xff169C9C);
    public static final RegistryObject<FluidType> GRAY_WATER_TYPE = registerColoredWaterType("gray_water_type", 0xff474F52);
    public static final RegistryObject<FluidType> GREEN_WATER_TYPE = registerColoredWaterType("green_water_type", 0xff5E7C16);
    public static final RegistryObject<FluidType> LIGHT_BLUE_WATER_TYPE = registerColoredWaterType("light_blue_water_type", 0xff3AB3DA);
    public static final RegistryObject<FluidType> LIGHT_GRAY_WATER_TYPE = registerColoredWaterType("light_gray_water_type", 0xff9D9D97);
    public static final RegistryObject<FluidType> LIME_WATER_TYPE = registerColoredWaterType("lime_water_type", 0xff80C71F);
    public static final RegistryObject<FluidType> MAGENTA_WATER_TYPE = registerColoredWaterType("magenta_water_type", 0xffC74EBD);
    public static final RegistryObject<FluidType> ORANGE_WATER_TYPE = registerColoredWaterType("orange_water_type", 0xffF9801D);
    public static final RegistryObject<FluidType> PINK_WATER_TYPE = registerColoredWaterType("pink_water_type", 0xffF38BAA);
    public static final RegistryObject<FluidType> PURPLE_WATER_TYPE = registerColoredWaterType("purple_water_type", 0xff8932B8);
    public static final RegistryObject<FluidType> RED_WATER_TYPE = registerColoredWaterType("red_water_type", 0xffB02E26);
    public static final RegistryObject<FluidType> WHITE_WATER_TYPE = registerColoredWaterType("white_water_type", 0xffF9FFFE);
    public static final RegistryObject<FluidType> YELLOW_WATER_TYPE = registerColoredWaterType("yellow_water_type", 0xffFED83D);
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
    public static final RegistryObject<FluidType> LUMINOUS_BLACK_WATER_TYPE = registerColoredWaterType("luminous_black_water_type", 0xff1D1D21);
    public static final RegistryObject<FluidType> LUMINOUS_BLUE_WATER_TYPE = registerColoredWaterType("luminous_blue_water_type", 0xff3C44AA);
    public static final RegistryObject<FluidType> LUMINOUS_BROWN_WATER_TYPE = registerColoredWaterType("luminous_brown_water_type", 0xff835432);
    public static final RegistryObject<FluidType> LUMINOUS_CYAN_WATER_TYPE = registerColoredWaterType("luminous_cyan_water_type", 0xff169C9C);
    public static final RegistryObject<FluidType> LUMINOUS_GRAY_WATER_TYPE = registerColoredWaterType("luminous_gray_water_type", 0xff474F52);
    public static final RegistryObject<FluidType> LUMINOUS_GREEN_WATER_TYPE = registerColoredWaterType("luminous_green_water_type", 0xff5E7C16);
    public static final RegistryObject<FluidType> LUMINOUS_LIGHT_BLUE_WATER_TYPE = registerColoredWaterType("luminous_light_blue_water_type", 0xff3AB3DA);
    public static final RegistryObject<FluidType> LUMINOUS_LIGHT_GRAY_WATER_TYPE = registerColoredWaterType("luminous_light_gray_water_type", 0xff9D9D97);
    public static final RegistryObject<FluidType> LUMINOUS_LIME_WATER_TYPE = registerColoredWaterType("luminous_lime_water_type", 0xff80C71F);
    public static final RegistryObject<FluidType> LUMINOUS_MAGENTA_WATER_TYPE = registerColoredWaterType("luminous_magenta_water_type", 0xffC74EBD);
    public static final RegistryObject<FluidType> LUMINOUS_ORANGE_WATER_TYPE = registerColoredWaterType("luminous_orange_water_type", 0xffF9801D);
    public static final RegistryObject<FluidType> LUMINOUS_PINK_WATER_TYPE = registerColoredWaterType("luminous_pink_water_type", 0xffF38BAA);
    public static final RegistryObject<FluidType> LUMINOUS_PURPLE_WATER_TYPE = registerColoredWaterType("luminous_purple_water_type", 0xff8932B8);
    public static final RegistryObject<FluidType> LUMINOUS_RED_WATER_TYPE = registerColoredWaterType("luminous_red_water_type", 0xffB02E26);
    public static final RegistryObject<FluidType> LUMINOUS_WHITE_WATER_TYPE = registerColoredWaterType("luminous_white_water_type", 0xffF9FFFE);
    public static final RegistryObject<FluidType> LUMINOUS_YELLOW_WATER_TYPE = registerColoredWaterType("luminous_yellow_water_type", 0xffFED83D);
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

    public static final RegistryObject<FluidType> COLORED_WATER_TYPE = registerColoredWaterTypeSpecial("colored_water_type");


    private static RegistryObject<FluidType> registerColoredWaterTypeSpecial(String name) {
        return FLUID_TYPES.register(name, () -> new FluidType(createFluidTypeProperties()) {
            @Override
            public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                consumer.accept(new IClientFluidTypeExtensions() {
                    private static final Identifier STILL_TEXTURE = Identifier.fromNamespaceAndPath("minecraft", "block/water_still");
                    private static final Identifier FLOWING_TEXTURE = Identifier.fromNamespaceAndPath("minecraft", "block/water_flow");

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
                        return 0x3F76E4;
                    }

                    @Override
                    public int getTintColor(FluidState state, BlockAndTintGetter getter, BlockPos pos) {
                        if (getter != null && pos != null) {
                            BlockEntity be = getter.getBlockEntity(pos);
                            if (be instanceof ColoredWaterBlockEntity) {
                                return ((ColoredWaterBlockEntity) be).getColor() | 0xFF000000;
                            }
                        }
                        return 0x3F76E4 | 0xFF000000;
                    }
                });
            }
        });
    }

    private static RegistryObject<FluidType> registerColoredWaterType(String name, int color) {
        return FLUID_TYPES.register(name, () -> new FluidType(createFluidTypeProperties()) {
            @Override
            public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                consumer.accept(new IClientFluidTypeExtensions() {
                    private static final Identifier STILL_TEXTURE = Identifier.fromNamespaceAndPath("minecraft", "block/water_still");
                    private static final Identifier FLOWING_TEXTURE = Identifier.fromNamespaceAndPath("minecraft", "block/water_flow");

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
}
