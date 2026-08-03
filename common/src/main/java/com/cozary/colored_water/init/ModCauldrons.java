package com.cozary.colored_water.init;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.block.ColoredWaterCauldronBlock;
import com.cozary.colored_water.cauldrons.behaviour.ColoredWaterCauldronBehavior;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class ModCauldrons {

    public static final RegistrationProvider<Block> BLOCKS = RegistrationProvider.get(Registries.BLOCK, ColoredWater.MOD_ID);

    public static final Supplier<Block> COLORED_WATER_CAULDRON = BLOCKS.register("colored_water_cauldron",
            () -> new ColoredWaterCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON)
                    .lightLevel(state -> state.hasProperty(ColoredWaterCauldronBlock.LIGHT_LEVEL) ? state.getValue(ColoredWaterCauldronBlock.LIGHT_LEVEL) : 0)
                    .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(ColoredWater.MOD_ID, "colored_water_cauldron")))));

    public static void loadClass() {
    }

    public static void registerCauldronInteractions() {
        ColoredWaterCauldronBehavior.init();
    }
}
