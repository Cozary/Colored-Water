package com.cozary.colored_water.datagen;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.init.ModBlocks;
import com.cozary.colored_water.init.ModCauldrons;
import com.cozary.colored_water.init.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LayeredCauldronBlock;

import static net.minecraft.client.data.models.BlockModelGenerators.plainVariant;

public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput output) {
        super(output, ColoredWater.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        generateLayeredCauldronModels(blockModels, ModCauldrons.MAGENTA_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.PURPLE_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.GREEN_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.YELLOW_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.LIME_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.PINK_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.RED_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.BLACK_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.BROWN_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.BLUE_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.CYAN_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.LIGHT_GRAY_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.GRAY_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.LIGHT_BLUE_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.ORANGE_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.WHITE_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.CONDENSE_MAGENTA_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.CONDENSE_PURPLE_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.CONDENSE_GREEN_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.CONDENSE_YELLOW_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.CONDENSE_LIME_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.CONDENSE_PINK_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.CONDENSE_RED_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.CONDENSE_BLACK_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.CONDENSE_BROWN_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.CONDENSE_BLUE_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.CONDENSE_CYAN_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.CONDENSE_LIGHT_GRAY_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.CONDENSE_GRAY_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.CONDENSE_LIGHT_BLUE_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.CONDENSE_ORANGE_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.CONDENSE_WHITE_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.LUMINOUS_MAGENTA_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.LUMINOUS_PURPLE_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.LUMINOUS_GREEN_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.LUMINOUS_YELLOW_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.LUMINOUS_LIME_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.LUMINOUS_PINK_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.LUMINOUS_RED_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.LUMINOUS_BLACK_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.LUMINOUS_BROWN_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.LUMINOUS_BLUE_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.LUMINOUS_CYAN_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.LUMINOUS_LIGHT_GRAY_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.LUMINOUS_GRAY_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.LUMINOUS_LIGHT_BLUE_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.LUMINOUS_ORANGE_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.LUMINOUS_WHITE_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.LUMINOUS_CONDENSE_MAGENTA_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.LUMINOUS_CONDENSE_PURPLE_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.LUMINOUS_CONDENSE_GREEN_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.LUMINOUS_CONDENSE_YELLOW_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.LUMINOUS_CONDENSE_LIME_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.LUMINOUS_CONDENSE_PINK_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.LUMINOUS_CONDENSE_RED_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.LUMINOUS_CONDENSE_BLACK_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.LUMINOUS_CONDENSE_BROWN_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.LUMINOUS_CONDENSE_BLUE_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.LUMINOUS_CONDENSE_CYAN_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.LUMINOUS_CONDENSE_GRAY_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.LUMINOUS_CONDENSE_ORANGE_WATER_CAULDRON.get());
        generateLayeredCauldronModels(blockModels, ModCauldrons.LUMINOUS_CONDENSE_WHITE_WATER_CAULDRON.get());

        blockModels.createTrivialCube(ModBlocks.WHITE_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.CONDENSE_WHITE_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.LUMINOUS_WHITE_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.LUMINOUS_CONDENSE_WHITE_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.ORANGE_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.CONDENSE_ORANGE_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.LUMINOUS_ORANGE_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.LUMINOUS_CONDENSE_ORANGE_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.MAGENTA_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.CONDENSE_MAGENTA_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.LUMINOUS_MAGENTA_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.LUMINOUS_CONDENSE_MAGENTA_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.LIGHT_BLUE_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.CONDENSE_LIGHT_BLUE_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.LUMINOUS_LIGHT_BLUE_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.YELLOW_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.CONDENSE_YELLOW_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.LUMINOUS_YELLOW_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.LUMINOUS_CONDENSE_YELLOW_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.LIME_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.CONDENSE_LIME_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.LUMINOUS_LIME_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.LUMINOUS_CONDENSE_LIME_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.PINK_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.CONDENSE_PINK_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.LUMINOUS_PINK_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.LUMINOUS_CONDENSE_PINK_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.GRAY_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.CONDENSE_GRAY_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.LUMINOUS_GRAY_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.LUMINOUS_CONDENSE_GRAY_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.LIGHT_GRAY_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.CONDENSE_LIGHT_GRAY_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.LUMINOUS_LIGHT_GRAY_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.CYAN_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.CONDENSE_CYAN_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.LUMINOUS_CYAN_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.LUMINOUS_CONDENSE_CYAN_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.BLUE_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.CONDENSE_BLUE_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.LUMINOUS_BLUE_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.LUMINOUS_CONDENSE_BLUE_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.PURPLE_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.CONDENSE_PURPLE_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.LUMINOUS_PURPLE_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.LUMINOUS_CONDENSE_PURPLE_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.BLACK_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.CONDENSE_BLACK_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.LUMINOUS_BLACK_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.LUMINOUS_CONDENSE_BLACK_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.GREEN_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.CONDENSE_GREEN_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.LUMINOUS_GREEN_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.LUMINOUS_CONDENSE_GREEN_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.RED_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.CONDENSE_RED_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.LUMINOUS_RED_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.LUMINOUS_CONDENSE_RED_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.BROWN_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.CONDENSE_BROWN_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.LUMINOUS_BROWN_WATER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.LUMINOUS_CONDENSE_BROWN_WATER_BLOCK.get());

        itemModels.generateFlatItem(ModItems.WHITE_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.CONDENSE_WHITE_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LUMINOUS_WHITE_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LUMINOUS_CONDENSE_WHITE_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.ORANGE_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.CONDENSE_ORANGE_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LUMINOUS_ORANGE_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LUMINOUS_CONDENSE_ORANGE_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.MAGENTA_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.CONDENSE_MAGENTA_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LUMINOUS_MAGENTA_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LUMINOUS_CONDENSE_MAGENTA_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LIGHT_BLUE_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.CONDENSE_LIGHT_BLUE_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LUMINOUS_LIGHT_BLUE_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.YELLOW_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.CONDENSE_YELLOW_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LUMINOUS_YELLOW_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LUMINOUS_CONDENSE_YELLOW_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LIME_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.CONDENSE_LIME_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LUMINOUS_LIME_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LUMINOUS_CONDENSE_LIME_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.PINK_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.CONDENSE_PINK_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LUMINOUS_PINK_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LUMINOUS_CONDENSE_PINK_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.GRAY_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.CONDENSE_GRAY_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LUMINOUS_GRAY_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LUMINOUS_CONDENSE_GRAY_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LIGHT_GRAY_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.CONDENSE_LIGHT_GRAY_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LUMINOUS_LIGHT_GRAY_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.CYAN_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.CONDENSE_CYAN_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LUMINOUS_CYAN_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LUMINOUS_CONDENSE_CYAN_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.PURPLE_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.CONDENSE_PURPLE_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LUMINOUS_PURPLE_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LUMINOUS_CONDENSE_PURPLE_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.BLUE_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.CONDENSE_BLUE_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LUMINOUS_BLUE_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LUMINOUS_CONDENSE_BLUE_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.BROWN_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.CONDENSE_BROWN_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LUMINOUS_BROWN_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LUMINOUS_CONDENSE_BROWN_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.GREEN_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.CONDENSE_GREEN_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LUMINOUS_GREEN_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LUMINOUS_CONDENSE_GREEN_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.RED_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.CONDENSE_RED_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LUMINOUS_RED_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LUMINOUS_CONDENSE_RED_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.BLACK_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.CONDENSE_BLACK_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LUMINOUS_BLACK_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.LUMINOUS_CONDENSE_BLACK_WATER_BUCKET.get(), ModelTemplates.FLAT_ITEM);
    }


    public void generateLayeredCauldronModels(BlockModelGenerators blockModels, Block block) {
        ResourceLocation id = BuiltInRegistries.BLOCK.getKey(block);
        if (id == null) {
            throw new IllegalStateException("Block not registered: " + block);
        }

        String name = id.getPath();
        String color = extractColorFromName(name);
        String fluid = color + "_water_still";

        TextureMapping textures = TextureMapping.cauldron(
                ResourceLocation.fromNamespaceAndPath(ColoredWater.MOD_ID, "block/" + fluid)
        );

        blockModels.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(block).with(
                        PropertyDispatch.initial(LayeredCauldronBlock.LEVEL)
                                .select(1, plainVariant(
                                        ModelTemplates.CAULDRON_LEVEL1.createWithSuffix(block, "_level1", textures, blockModels.modelOutput)))
                                .select(2, plainVariant(
                                        ModelTemplates.CAULDRON_LEVEL2.createWithSuffix(block, "_level2", textures, blockModels.modelOutput)))
                                .select(3, plainVariant(
                                        ModelTemplates.CAULDRON_FULL.createWithSuffix(block, "_full", textures, blockModels.modelOutput)))
                )
        );
    }


    private String extractColorFromName(String name) {
        String[] prefixes = new String[]{
                "luminous_condense_",
                "luminous_",
                "condense_"
        };

        for (String prefix : prefixes) {
            if (name.startsWith(prefix)) {
                name = name.substring(prefix.length());
                break;
            }
        }

        if (name.endsWith("_water_cauldron")) {
            return name.substring(0, name.indexOf("_water_cauldron"));
        }

        throw new IllegalArgumentException("Can't extract color: " + name);
    }
}
