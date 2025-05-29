package com.cozary.colored_water.datagen;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.init.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ColoredWater.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.WHITE_WATER_BUCKET.get());
        basicItem(ModItems.CONDENSE_WHITE_WATER_BUCKET.get());
        basicItem(ModItems.LUMINOUS_WHITE_WATER_BUCKET.get());
        basicItem(ModItems.LUMINOUS_CONDENSE_WHITE_WATER_BUCKET.get());
        basicItem(ModItems.ORANGE_WATER_BUCKET.get());
        basicItem(ModItems.CONDENSE_ORANGE_WATER_BUCKET.get());
        basicItem(ModItems.LUMINOUS_ORANGE_WATER_BUCKET.get());
        basicItem(ModItems.LUMINOUS_CONDENSE_ORANGE_WATER_BUCKET.get());
        basicItem(ModItems.MAGENTA_WATER_BUCKET.get());
        basicItem(ModItems.CONDENSE_MAGENTA_WATER_BUCKET.get());
        basicItem(ModItems.LUMINOUS_MAGENTA_WATER_BUCKET.get());
        basicItem(ModItems.LUMINOUS_CONDENSE_MAGENTA_WATER_BUCKET.get());
        basicItem(ModItems.LIGHT_BLUE_WATER_BUCKET.get());
        basicItem(ModItems.CONDENSE_LIGHT_BLUE_WATER_BUCKET.get());
        basicItem(ModItems.LUMINOUS_LIGHT_BLUE_WATER_BUCKET.get());
        basicItem(ModItems.LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_BUCKET.get());
        basicItem(ModItems.YELLOW_WATER_BUCKET.get());
        basicItem(ModItems.CONDENSE_YELLOW_WATER_BUCKET.get());
        basicItem(ModItems.LUMINOUS_YELLOW_WATER_BUCKET.get());
        basicItem(ModItems.LUMINOUS_CONDENSE_YELLOW_WATER_BUCKET.get());
        basicItem(ModItems.LIME_WATER_BUCKET.get());
        basicItem(ModItems.CONDENSE_LIME_WATER_BUCKET.get());
        basicItem(ModItems.LUMINOUS_LIME_WATER_BUCKET.get());
        basicItem(ModItems.LUMINOUS_CONDENSE_LIME_WATER_BUCKET.get());
        basicItem(ModItems.PINK_WATER_BUCKET.get());
        basicItem(ModItems.CONDENSE_PINK_WATER_BUCKET.get());
        basicItem(ModItems.LUMINOUS_PINK_WATER_BUCKET.get());
        basicItem(ModItems.LUMINOUS_CONDENSE_PINK_WATER_BUCKET.get());
        basicItem(ModItems.GRAY_WATER_BUCKET.get());
        basicItem(ModItems.CONDENSE_GRAY_WATER_BUCKET.get());
        basicItem(ModItems.LUMINOUS_GRAY_WATER_BUCKET.get());
        basicItem(ModItems.LUMINOUS_CONDENSE_GRAY_WATER_BUCKET.get());
        basicItem(ModItems.LIGHT_GRAY_WATER_BUCKET.get());
        basicItem(ModItems.CONDENSE_LIGHT_GRAY_WATER_BUCKET.get());
        basicItem(ModItems.LUMINOUS_LIGHT_GRAY_WATER_BUCKET.get());
        basicItem(ModItems.LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_BUCKET.get());
        basicItem(ModItems.CYAN_WATER_BUCKET.get());
        basicItem(ModItems.CONDENSE_CYAN_WATER_BUCKET.get());
        basicItem(ModItems.LUMINOUS_CYAN_WATER_BUCKET.get());
        basicItem(ModItems.LUMINOUS_CONDENSE_CYAN_WATER_BUCKET.get());
        basicItem(ModItems.PURPLE_WATER_BUCKET.get());
        basicItem(ModItems.CONDENSE_PURPLE_WATER_BUCKET.get());
        basicItem(ModItems.LUMINOUS_PURPLE_WATER_BUCKET.get());
        basicItem(ModItems.LUMINOUS_CONDENSE_PURPLE_WATER_BUCKET.get());
        basicItem(ModItems.BLUE_WATER_BUCKET.get());
        basicItem(ModItems.CONDENSE_BLUE_WATER_BUCKET.get());
        basicItem(ModItems.LUMINOUS_BLUE_WATER_BUCKET.get());
        basicItem(ModItems.LUMINOUS_CONDENSE_BLUE_WATER_BUCKET.get());
        basicItem(ModItems.BROWN_WATER_BUCKET.get());
        basicItem(ModItems.CONDENSE_BROWN_WATER_BUCKET.get());
        basicItem(ModItems.LUMINOUS_BROWN_WATER_BUCKET.get());
        basicItem(ModItems.LUMINOUS_CONDENSE_BROWN_WATER_BUCKET.get());
        basicItem(ModItems.GREEN_WATER_BUCKET.get());
        basicItem(ModItems.CONDENSE_GREEN_WATER_BUCKET.get());
        basicItem(ModItems.LUMINOUS_GREEN_WATER_BUCKET.get());
        basicItem(ModItems.LUMINOUS_CONDENSE_GREEN_WATER_BUCKET.get());
        basicItem(ModItems.RED_WATER_BUCKET.get());
        basicItem(ModItems.CONDENSE_RED_WATER_BUCKET.get());
        basicItem(ModItems.LUMINOUS_RED_WATER_BUCKET.get());
        basicItem(ModItems.LUMINOUS_CONDENSE_RED_WATER_BUCKET.get());
        basicItem(ModItems.BLACK_WATER_BUCKET.get());
        basicItem(ModItems.CONDENSE_BLACK_WATER_BUCKET.get());
        basicItem(ModItems.LUMINOUS_BLACK_WATER_BUCKET.get());
        basicItem(ModItems.LUMINOUS_CONDENSE_BLACK_WATER_BUCKET.get());
    }
}
