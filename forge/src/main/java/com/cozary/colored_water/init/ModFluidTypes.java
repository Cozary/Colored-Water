package com.cozary.colored_water.init;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.block.entity.ColoredWaterBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

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
                                BlockEntity be = getter.getBlockEntity(pos);
                                if (be instanceof ColoredWaterBlockEntity coloredBe) {
                                    int color = coloredBe.getColor();
                                    int alpha = (color >> 24) & 0xFF;
                                    if (alpha == 0) {
                                        alpha = coloredBe.isCondensed() ? 255 : 180;
                                        color = (alpha << 24) | (color & 0x00FFFFFF);
                                    }
                                    return color;
                                }
                            }
                            return 0xB43F76E4;
                        }
                    });
                }
            });
}
