package com.cozary.colored_water.datagen;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.init.ModCauldrons;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ColoredWater.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        layeredCauldronState(ModCauldrons.MAGENTA_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.PURPLE_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.GREEN_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.YELLOW_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.LIME_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.PINK_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.RED_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.BLACK_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.BROWN_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.BLUE_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.CYAN_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.LIGHT_GRAY_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.GRAY_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.LIGHT_BLUE_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.ORANGE_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.WHITE_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.CONDENSE_MAGENTA_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.CONDENSE_PURPLE_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.CONDENSE_GREEN_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.CONDENSE_YELLOW_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.CONDENSE_LIME_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.CONDENSE_PINK_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.CONDENSE_RED_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.CONDENSE_BLACK_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.CONDENSE_BROWN_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.CONDENSE_BLUE_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.CONDENSE_CYAN_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.CONDENSE_LIGHT_GRAY_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.CONDENSE_GRAY_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.CONDENSE_LIGHT_BLUE_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.CONDENSE_ORANGE_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.CONDENSE_WHITE_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.LUMINOUS_MAGENTA_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.LUMINOUS_PURPLE_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.LUMINOUS_GREEN_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.LUMINOUS_YELLOW_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.LUMINOUS_LIME_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.LUMINOUS_PINK_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.LUMINOUS_RED_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.LUMINOUS_BLACK_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.LUMINOUS_BROWN_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.LUMINOUS_BLUE_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.LUMINOUS_CYAN_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.LUMINOUS_LIGHT_GRAY_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.LUMINOUS_GRAY_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.LUMINOUS_LIGHT_BLUE_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.LUMINOUS_ORANGE_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.LUMINOUS_WHITE_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.LUMINOUS_CONDENSE_MAGENTA_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.LUMINOUS_CONDENSE_PURPLE_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.LUMINOUS_CONDENSE_GREEN_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.LUMINOUS_CONDENSE_YELLOW_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.LUMINOUS_CONDENSE_LIME_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.LUMINOUS_CONDENSE_PINK_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.LUMINOUS_CONDENSE_RED_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.LUMINOUS_CONDENSE_BLACK_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.LUMINOUS_CONDENSE_BROWN_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.LUMINOUS_CONDENSE_BLUE_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.LUMINOUS_CONDENSE_CYAN_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.LUMINOUS_CONDENSE_GRAY_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.LUMINOUS_CONDENSE_ORANGE_WATER_CAULDRON);
        layeredCauldronState(ModCauldrons.LUMINOUS_CONDENSE_WHITE_WATER_CAULDRON);
    }

    private void layeredCauldronState(Supplier<Block> blockSupplier) {
        Block block = blockSupplier.get();
        ResourceLocation id = BuiltInRegistries.BLOCK.getKey(block);
        if (id == null) {
            throw new IllegalStateException("Block not registered: " + block);
        }

        String modelBaseName = id.getPath();

        getVariantBuilder(block).forAllStates(state -> {
            int level = state.getValue(LayeredCauldronBlock.LEVEL);
            String modelName = switch (level) {
                case 1 -> modelBaseName + "_level1";
                case 2 -> modelBaseName + "_level2";
                case 3 -> modelBaseName + "_full";
                default -> modelBaseName + "_empty";
            };
            return ConfiguredModel.builder()
                    .modelFile(models().getExistingFile(modLoc("block/" + modelName)))
                    .build();
        });
    }
}
