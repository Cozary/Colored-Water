package com.cozary.colored_water.datagen;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.recipe.ColoredWaterBucketDyeRecipe;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SpecialRecipeBuilder;
import net.minecraft.resources.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {

    protected ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
        SpecialRecipeBuilder.special(ColoredWaterBucketDyeRecipe::new)
                .save(this.output, Identifier.fromNamespaceAndPath(ColoredWater.MOD_ID, "colored_water_bucket_dye").toString());
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
            super(output, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
            return new ModRecipeProvider(registries, output);
        }

        @Override
        public String getName() {
            return "Colored Water Recipes";
        }
    }
}
