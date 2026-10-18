package com.cozary.colored_water.init;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.block.entity.WaterloggedColorBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.Set;
import java.util.function.Supplier;

public class ModBlockEntities {

    public static final RegistrationProvider<BlockEntityType<?>> BLOCK_ENTITIES = RegistrationProvider.get(Registries.BLOCK_ENTITY_TYPE, ColoredWater.MOD_ID);

    public static final Supplier<BlockEntityType<WaterloggedColorBlockEntity>> WATERLOGGED_BE = BLOCK_ENTITIES.register("waterlogged_be",
            () -> new BlockEntityType<>(WaterloggedColorBlockEntity::new, Set.of(Blocks.WATER)));

    public static void loadClass() {
    }
}
