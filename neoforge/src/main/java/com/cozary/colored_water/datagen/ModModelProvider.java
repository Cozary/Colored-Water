package com.cozary.colored_water.datagen;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.init.ModBlocks;
import com.cozary.colored_water.init.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;

public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput output) {
        super(output, ColoredWater.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        blockModels.createTrivialCube(ModBlocks.COLORED_WATER_BLOCK.get());
        itemModels.generateFlatItem(ModItems.COLORED_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
    }
}
