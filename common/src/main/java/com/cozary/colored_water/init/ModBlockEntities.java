package com.cozary.colored_water.init;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.block.entity.ColoredWaterBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.Set;
import java.util.function.Supplier;

public class ModBlockEntities {

    public static final RegistrationProvider<BlockEntityType<?>> BLOCK_ENTITIES = RegistrationProvider.get(Registries.BLOCK_ENTITY_TYPE, ColoredWater.MOD_ID);

    public static final Supplier<BlockEntityType<ColoredWaterBlockEntity>> COLORED_WATER_BE = BLOCK_ENTITIES.register("colored_water_be",
            () -> new BlockEntityType<>(ColoredWaterBlockEntity::new, Set.of(ModBlocks.COLORED_WATER_BLOCK.get())));

    public static void loadClass() {
    }
}
