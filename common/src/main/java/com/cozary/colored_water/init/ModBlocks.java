package com.cozary.colored_water.init;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.block.ColoredWaterBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

import static com.cozary.colored_water.init.ModFluids.STILL_COLORED_WATER;

public class ModBlocks {
    public static final RegistrationProvider<Block> BLOCKS = RegistrationProvider.get(Registries.BLOCK, ColoredWater.MOD_ID);

    public static final Supplier<Block> COLORED_WATER_BLOCK = BLOCKS.register("colored_water_block",
            () -> new ColoredWaterBlock(STILL_COLORED_WATER, BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)
                    .lightLevel(state -> state.hasProperty(ColoredWaterBlock.LIGHT_LEVEL) ? state.getValue(ColoredWaterBlock.LIGHT_LEVEL) : 0)
                    .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(ColoredWater.MOD_ID, "colored_water_block")))));

    public static void loadClass() {
    }
}
