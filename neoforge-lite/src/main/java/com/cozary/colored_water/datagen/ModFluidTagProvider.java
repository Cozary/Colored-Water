package com.cozary.colored_water.datagen;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.init.ModFluids;
import com.cozary.colored_water.init.RegistryObject;
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
        TagKey<Fluid> waterTag = FluidTags.WATER;
        TagKey<Fluid> entityFloatableTag = minecraftTag("entity_floatable");
        TagKey<Fluid> sugarCaneTag = minecraftTag("supports_sugar_cane_adjacently");
        TagKey<Fluid> axolotlTag = minecraftTag("axolotl_tries_to_find");
        TagKey<Fluid> bubbleTag = minecraftTag("bubble_column_can_occupy");
        TagKey<Fluid> dolphinTag = minecraftTag("dolphin_tries_to_find");
        TagKey<Fluid> frogLandTag = minecraftTag("frog_tries_to_find_land_near");
        TagKey<Fluid> frogspawnTag = minecraftTag("supports_frogspawn");
        TagKey<Fluid> lilyPadTag = minecraftTag("supports_lily_pad");

        for (RegistryObject<Fluid> entry : ModFluids.FLUIDS.getEntries()) {
            Fluid fluid = entry.get();
            tag(waterTag).add(fluid);
            tag(entityFloatableTag).add(fluid);
            tag(sugarCaneTag).add(fluid);

            if (!entry.getId().getPath().startsWith("flowing_")) {
                tag(axolotlTag).add(fluid);
                tag(bubbleTag).add(fluid);
                tag(dolphinTag).add(fluid);
                tag(frogLandTag).add(fluid);
                tag(frogspawnTag).add(fluid);
                tag(lilyPadTag).add(fluid);
            }
        }
    }
}
