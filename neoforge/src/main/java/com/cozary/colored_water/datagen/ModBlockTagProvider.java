package com.cozary.colored_water.datagen;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.init.ModCauldrons;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, ColoredWater.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.CAULDRONS)
                .add(ModCauldrons.MAGENTA_WATER_CAULDRON.get())
                .add(ModCauldrons.PURPLE_WATER_CAULDRON.get())
                .add(ModCauldrons.GREEN_WATER_CAULDRON.get())
                .add(ModCauldrons.YELLOW_WATER_CAULDRON.get())
                .add(ModCauldrons.LIME_WATER_CAULDRON.get())
                .add(ModCauldrons.PINK_WATER_CAULDRON.get())
                .add(ModCauldrons.RED_WATER_CAULDRON.get())
                .add(ModCauldrons.BLACK_WATER_CAULDRON.get())
                .add(ModCauldrons.BROWN_WATER_CAULDRON.get())
                .add(ModCauldrons.BLUE_WATER_CAULDRON.get())
                .add(ModCauldrons.CYAN_WATER_CAULDRON.get())
                .add(ModCauldrons.LIGHT_GRAY_WATER_CAULDRON.get())
                .add(ModCauldrons.GRAY_WATER_CAULDRON.get())
                .add(ModCauldrons.LIGHT_BLUE_WATER_CAULDRON.get())
                .add(ModCauldrons.ORANGE_WATER_CAULDRON.get())
                .add(ModCauldrons.WHITE_WATER_CAULDRON.get())
                .add(ModCauldrons.CONDENSE_MAGENTA_WATER_CAULDRON.get())
                .add(ModCauldrons.CONDENSE_PURPLE_WATER_CAULDRON.get())
                .add(ModCauldrons.CONDENSE_GREEN_WATER_CAULDRON.get())
                .add(ModCauldrons.CONDENSE_YELLOW_WATER_CAULDRON.get())
                .add(ModCauldrons.CONDENSE_LIME_WATER_CAULDRON.get())
                .add(ModCauldrons.CONDENSE_PINK_WATER_CAULDRON.get())
                .add(ModCauldrons.CONDENSE_RED_WATER_CAULDRON.get())
                .add(ModCauldrons.CONDENSE_BLACK_WATER_CAULDRON.get())
                .add(ModCauldrons.CONDENSE_BROWN_WATER_CAULDRON.get())
                .add(ModCauldrons.CONDENSE_BLUE_WATER_CAULDRON.get())
                .add(ModCauldrons.CONDENSE_CYAN_WATER_CAULDRON.get())
                .add(ModCauldrons.CONDENSE_LIGHT_GRAY_WATER_CAULDRON.get())
                .add(ModCauldrons.CONDENSE_GRAY_WATER_CAULDRON.get())
                .add(ModCauldrons.CONDENSE_LIGHT_BLUE_WATER_CAULDRON.get())
                .add(ModCauldrons.CONDENSE_ORANGE_WATER_CAULDRON.get())
                .add(ModCauldrons.CONDENSE_WHITE_WATER_CAULDRON.get())
                .add(ModCauldrons.LUMINOUS_MAGENTA_WATER_CAULDRON.get())
                .add(ModCauldrons.LUMINOUS_PURPLE_WATER_CAULDRON.get())
                .add(ModCauldrons.LUMINOUS_GREEN_WATER_CAULDRON.get())
                .add(ModCauldrons.LUMINOUS_YELLOW_WATER_CAULDRON.get())
                .add(ModCauldrons.LUMINOUS_LIME_WATER_CAULDRON.get())
                .add(ModCauldrons.LUMINOUS_PINK_WATER_CAULDRON.get())
                .add(ModCauldrons.LUMINOUS_RED_WATER_CAULDRON.get())
                .add(ModCauldrons.LUMINOUS_BLACK_WATER_CAULDRON.get())
                .add(ModCauldrons.LUMINOUS_BROWN_WATER_CAULDRON.get())
                .add(ModCauldrons.LUMINOUS_BLUE_WATER_CAULDRON.get())
                .add(ModCauldrons.LUMINOUS_CYAN_WATER_CAULDRON.get())
                .add(ModCauldrons.LUMINOUS_LIGHT_GRAY_WATER_CAULDRON.get())
                .add(ModCauldrons.LUMINOUS_GRAY_WATER_CAULDRON.get())
                .add(ModCauldrons.LUMINOUS_LIGHT_BLUE_WATER_CAULDRON.get())
                .add(ModCauldrons.LUMINOUS_ORANGE_WATER_CAULDRON.get())
                .add(ModCauldrons.LUMINOUS_WHITE_WATER_CAULDRON.get())
                .add(ModCauldrons.LUMINOUS_CONDENSE_MAGENTA_WATER_CAULDRON.get())
                .add(ModCauldrons.LUMINOUS_CONDENSE_PURPLE_WATER_CAULDRON.get())
                .add(ModCauldrons.LUMINOUS_CONDENSE_GREEN_WATER_CAULDRON.get())
                .add(ModCauldrons.LUMINOUS_CONDENSE_YELLOW_WATER_CAULDRON.get())
                .add(ModCauldrons.LUMINOUS_CONDENSE_LIME_WATER_CAULDRON.get())
                .add(ModCauldrons.LUMINOUS_CONDENSE_PINK_WATER_CAULDRON.get())
                .add(ModCauldrons.LUMINOUS_CONDENSE_RED_WATER_CAULDRON.get())
                .add(ModCauldrons.LUMINOUS_CONDENSE_BLACK_WATER_CAULDRON.get())
                .add(ModCauldrons.LUMINOUS_CONDENSE_BROWN_WATER_CAULDRON.get())
                .add(ModCauldrons.LUMINOUS_CONDENSE_BLUE_WATER_CAULDRON.get())
                .add(ModCauldrons.LUMINOUS_CONDENSE_CYAN_WATER_CAULDRON.get())
                .add(ModCauldrons.LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_CAULDRON.get())
                .add(ModCauldrons.LUMINOUS_CONDENSE_GRAY_WATER_CAULDRON.get())
                .add(ModCauldrons.LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_CAULDRON.get())
                .add(ModCauldrons.LUMINOUS_CONDENSE_ORANGE_WATER_CAULDRON.get())
                .add(ModCauldrons.LUMINOUS_CONDENSE_WHITE_WATER_CAULDRON.get()
                );
    }
}
