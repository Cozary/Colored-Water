package com.cozary.colored_water.datagen;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.init.ModFluids;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.tags.FluidTags;

import java.util.concurrent.CompletableFuture;

public class ModFluidTagProvider extends FluidTagsProvider {
    public ModFluidTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, ColoredWater.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(FluidTags.WATER)
                .add(ModFluids.STILL_COLORED_WATER.get())
                .add(ModFluids.FLOWING_COLORED_WATER.get());
    }
}
