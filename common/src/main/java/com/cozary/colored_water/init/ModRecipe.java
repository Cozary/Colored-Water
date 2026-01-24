package com.cozary.colored_water.init;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.recipe.ColoredWaterBucketDyeRecipe;
import com.cozary.colored_water.recipe.ContainerCraftingRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class ModRecipe {

    public static final RegistrationProvider<RecipeSerializer<?>> RECIPES = RegistrationProvider.get(Registries.RECIPE_SERIALIZER, ColoredWater.MOD_ID);

    public static final RegistryObject<RecipeSerializer<?>> CONTAINER_CRAFTING_RECIPE = RECIPES.register("container_shapeless_recipe_cw", ContainerCraftingRecipe.Serializer::new);
    public static final RegistryObject<RecipeSerializer<?>> COLORED_WATER_BUCKET_DYE = RECIPES.register("crafting_special_colored_water_bucket_dye", ColoredWaterBucketDyeRecipe.Serializer::new);

    public static void loadClass() {
    }
}
