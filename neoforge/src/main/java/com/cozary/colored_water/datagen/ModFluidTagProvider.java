package com.cozary.colored_water.datagen;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.init.ModFluids;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;

import java.util.concurrent.CompletableFuture;

public class ModFluidTagProvider extends FluidTagsProvider {

    public ModFluidTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, ColoredWater.MOD_ID);
    }

    private static TagKey<Fluid> minecraftTag(String name) {
        return TagKey.create(Registries.FLUID, Identifier.fromNamespaceAndPath("minecraft", name));
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        Fluid stillWater = ModFluids.STILL_COLORED_WATER.get();
        Fluid flowingWater = ModFluids.FLOWING_COLORED_WATER.get();

        tag(FluidTags.WATER)
                .add(stillWater)
                .add(flowingWater);

        tag(minecraftTag("entity_floatable"))
                .add(stillWater)
                .add(flowingWater);

        tag(minecraftTag("supports_sugar_cane_adjacently"))
                .add(stillWater)
                .add(flowingWater);

        tag(minecraftTag("axolotl_tries_to_find"))
                .add(stillWater);

        tag(minecraftTag("bubble_column_can_occupy"))
                .add(stillWater);

        tag(minecraftTag("dolphin_tries_to_find"))
                .add(stillWater);

        tag(minecraftTag("frog_tries_to_find_land_near"))
                .add(stillWater);

        tag(minecraftTag("supports_frogspawn"))
                .add(stillWater);

        tag(minecraftTag("supports_lily_pad"))
                .add(stillWater);
    }
}
