package com.cozary.colored_water.datagen;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.init.ModCauldrons;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

public class ModBlockModelProvider extends BlockModelProvider {
    public ModBlockModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ColoredWater.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        generateLayeredCauldronModels(ModCauldrons.MAGENTA_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.PURPLE_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.GREEN_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.YELLOW_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.LIME_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.PINK_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.RED_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.BLACK_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.BROWN_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.BLUE_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.CYAN_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.LIGHT_GRAY_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.GRAY_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.LIGHT_BLUE_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.ORANGE_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.WHITE_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.CONDENSE_MAGENTA_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.CONDENSE_PURPLE_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.CONDENSE_GREEN_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.CONDENSE_YELLOW_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.CONDENSE_LIME_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.CONDENSE_PINK_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.CONDENSE_RED_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.CONDENSE_BLACK_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.CONDENSE_BROWN_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.CONDENSE_BLUE_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.CONDENSE_CYAN_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.CONDENSE_LIGHT_GRAY_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.CONDENSE_GRAY_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.CONDENSE_LIGHT_BLUE_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.CONDENSE_ORANGE_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.CONDENSE_WHITE_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.LUMINOUS_MAGENTA_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.LUMINOUS_PURPLE_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.LUMINOUS_GREEN_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.LUMINOUS_YELLOW_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.LUMINOUS_LIME_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.LUMINOUS_PINK_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.LUMINOUS_RED_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.LUMINOUS_BLACK_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.LUMINOUS_BROWN_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.LUMINOUS_BLUE_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.LUMINOUS_CYAN_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.LUMINOUS_LIGHT_GRAY_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.LUMINOUS_GRAY_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.LUMINOUS_LIGHT_BLUE_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.LUMINOUS_ORANGE_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.LUMINOUS_WHITE_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.LUMINOUS_CONDENSE_MAGENTA_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.LUMINOUS_CONDENSE_PURPLE_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.LUMINOUS_CONDENSE_GREEN_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.LUMINOUS_CONDENSE_YELLOW_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.LUMINOUS_CONDENSE_LIME_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.LUMINOUS_CONDENSE_PINK_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.LUMINOUS_CONDENSE_RED_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.LUMINOUS_CONDENSE_BLACK_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.LUMINOUS_CONDENSE_BROWN_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.LUMINOUS_CONDENSE_BLUE_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.LUMINOUS_CONDENSE_CYAN_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.LUMINOUS_CONDENSE_GRAY_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.LUMINOUS_CONDENSE_ORANGE_WATER_CAULDRON);
        generateLayeredCauldronModels(ModCauldrons.LUMINOUS_CONDENSE_WHITE_WATER_CAULDRON);
    }

    private void generateLayeredCauldronModels(Supplier<Block> blockSupplier) {
        Block block = blockSupplier.get();
        ResourceLocation id = BuiltInRegistries.BLOCK.getKey(block);
        if (id == null) {
            throw new IllegalStateException("Block not registered: " + block);
        }

        String name = id.getPath();
        String color = extractColorFromName(name);
        String fluid = color + "_water_still";

        String contentTex = modLoc("block/" + fluid).toString();
        String cauldronSide = "minecraft:block/cauldron_side";
        String cauldronTop = "minecraft:block/cauldron_top";
        String cauldronBottom = "minecraft:block/cauldron_bottom";
        String cauldronInner = "minecraft:block/cauldron_inner";

        String renderType = name.contains("condense") ? "cutout" : "translucent";

        withExistingParent(name + "_full", "minecraft:block/template_cauldron_full")
                .renderType(renderType)
                .texture("content", contentTex)
                .texture("inside", cauldronInner)
                .texture("particle", cauldronSide)
                .texture("top", cauldronTop)
                .texture("bottom", cauldronBottom)
                .texture("side", cauldronSide);

        withExistingParent(name + "_level1", "minecraft:block/template_cauldron_level1")
                .renderType(renderType)
                .texture("content", contentTex)
                .texture("inside", cauldronInner)
                .texture("particle", cauldronSide)
                .texture("top", cauldronTop)
                .texture("bottom", cauldronBottom)
                .texture("side", cauldronSide);

        withExistingParent(name + "_level2", "minecraft:block/template_cauldron_level2")
                .renderType(renderType)
                .texture("content", contentTex)
                .texture("inside", cauldronInner)
                .texture("particle", cauldronSide)
                .texture("top", cauldronTop)
                .texture("bottom", cauldronBottom)
                .texture("side", cauldronSide);
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
