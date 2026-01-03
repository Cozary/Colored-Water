package com.cozary.colored_water.datagen;


import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.init.ModItems;
import com.cozary.colored_water.recipe.ContainerCraftingRecipe;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {
        addColoredWaterRecipe(output, Items.BLACK_DYE, ModItems.BLACK_WATER_BUCKET.get());
        addColoredWaterRecipe(output, List.of(Items.BLACK_DYE, Items.BLACK_DYE), ModItems.CONDENSE_BLACK_WATER_BUCKET.get());
        addColoredWaterRecipe(output, List.of(Items.BLACK_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_BLACK_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(output, List.of(Items.BLACK_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_BLACK_WATER_BUCKET.get(), "ink");
        addColoredWaterRecipe(output, List.of(Items.BLACK_DYE, Items.BLACK_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_CONDENSE_BLACK_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(output, List.of(Items.BLACK_DYE, Items.BLACK_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_CONDENSE_BLACK_WATER_BUCKET.get(), "ink");

        addColoredWaterRecipe(output, Items.WHITE_DYE, ModItems.WHITE_WATER_BUCKET.get());
        addColoredWaterRecipe(output, List.of(Items.WHITE_DYE, Items.WHITE_DYE), ModItems.CONDENSE_WHITE_WATER_BUCKET.get());
        addColoredWaterRecipe(output, List.of(Items.WHITE_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_WHITE_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(output, List.of(Items.WHITE_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_WHITE_WATER_BUCKET.get(), "ink");
        addColoredWaterRecipe(output, List.of(Items.WHITE_DYE, Items.WHITE_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_CONDENSE_WHITE_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(output, List.of(Items.WHITE_DYE, Items.WHITE_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_CONDENSE_WHITE_WATER_BUCKET.get(), "ink");

        addColoredWaterRecipe(output, Items.MAGENTA_DYE, ModItems.MAGENTA_WATER_BUCKET.get());
        addColoredWaterRecipe(output, List.of(Items.MAGENTA_DYE, Items.MAGENTA_DYE), ModItems.CONDENSE_MAGENTA_WATER_BUCKET.get());
        addColoredWaterRecipe(output, List.of(Items.MAGENTA_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_MAGENTA_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(output, List.of(Items.MAGENTA_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_MAGENTA_WATER_BUCKET.get(), "ink");
        addColoredWaterRecipe(output, List.of(Items.MAGENTA_DYE, Items.MAGENTA_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_CONDENSE_MAGENTA_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(output, List.of(Items.MAGENTA_DYE, Items.MAGENTA_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_CONDENSE_MAGENTA_WATER_BUCKET.get(), "ink");

        addColoredWaterRecipe(output, Items.LIGHT_BLUE_DYE, ModItems.LIGHT_BLUE_WATER_BUCKET.get());
        addColoredWaterRecipe(output, List.of(Items.LIGHT_BLUE_DYE, Items.LIGHT_BLUE_DYE), ModItems.CONDENSE_LIGHT_BLUE_WATER_BUCKET.get());
        addColoredWaterRecipe(output, List.of(Items.LIGHT_BLUE_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_LIGHT_BLUE_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(output, List.of(Items.LIGHT_BLUE_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_LIGHT_BLUE_WATER_BUCKET.get(), "ink");
        addColoredWaterRecipe(output, List.of(Items.LIGHT_BLUE_DYE, Items.LIGHT_BLUE_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(output, List.of(Items.LIGHT_BLUE_DYE, Items.LIGHT_BLUE_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_BUCKET.get(), "ink");

        addColoredWaterRecipe(output, Items.YELLOW_DYE, ModItems.YELLOW_WATER_BUCKET.get());
        addColoredWaterRecipe(output, List.of(Items.YELLOW_DYE, Items.YELLOW_DYE), ModItems.CONDENSE_YELLOW_WATER_BUCKET.get());
        addColoredWaterRecipe(output, List.of(Items.YELLOW_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_YELLOW_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(output, List.of(Items.YELLOW_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_YELLOW_WATER_BUCKET.get(), "ink");
        addColoredWaterRecipe(output, List.of(Items.YELLOW_DYE, Items.YELLOW_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_CONDENSE_YELLOW_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(output, List.of(Items.YELLOW_DYE, Items.YELLOW_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_CONDENSE_YELLOW_WATER_BUCKET.get(), "ink");

        addColoredWaterRecipe(output, Items.LIME_DYE, ModItems.LIME_WATER_BUCKET.get());
        addColoredWaterRecipe(output, List.of(Items.LIME_DYE, Items.LIME_DYE), ModItems.CONDENSE_LIME_WATER_BUCKET.get());
        addColoredWaterRecipe(output, List.of(Items.LIME_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_LIME_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(output, List.of(Items.LIME_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_LIME_WATER_BUCKET.get(), "ink");
        addColoredWaterRecipe(output, List.of(Items.LIME_DYE, Items.LIME_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_CONDENSE_LIME_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(output, List.of(Items.LIME_DYE, Items.LIME_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_CONDENSE_LIME_WATER_BUCKET.get(), "ink");

        addColoredWaterRecipe(output, Items.PINK_DYE, ModItems.PINK_WATER_BUCKET.get());
        addColoredWaterRecipe(output, List.of(Items.PINK_DYE, Items.PINK_DYE), ModItems.CONDENSE_PINK_WATER_BUCKET.get());
        addColoredWaterRecipe(output, List.of(Items.PINK_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_PINK_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(output, List.of(Items.PINK_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_PINK_WATER_BUCKET.get(), "ink");
        addColoredWaterRecipe(output, List.of(Items.PINK_DYE, Items.PINK_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_CONDENSE_PINK_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(output, List.of(Items.PINK_DYE, Items.PINK_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_CONDENSE_PINK_WATER_BUCKET.get(), "ink");

        addColoredWaterRecipe(output, Items.GRAY_DYE, ModItems.GRAY_WATER_BUCKET.get());
        addColoredWaterRecipe(output, List.of(Items.GRAY_DYE, Items.GRAY_DYE), ModItems.CONDENSE_GRAY_WATER_BUCKET.get());
        addColoredWaterRecipe(output, List.of(Items.GRAY_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_GRAY_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(output, List.of(Items.GRAY_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_GRAY_WATER_BUCKET.get(), "ink");
        addColoredWaterRecipe(output, List.of(Items.GRAY_DYE, Items.GRAY_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_CONDENSE_GRAY_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(output, List.of(Items.GRAY_DYE, Items.GRAY_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_CONDENSE_GRAY_WATER_BUCKET.get(), "ink");

        addColoredWaterRecipe(output, Items.LIGHT_GRAY_DYE, ModItems.LIGHT_GRAY_WATER_BUCKET.get());
        addColoredWaterRecipe(output, List.of(Items.LIGHT_GRAY_DYE, Items.LIGHT_GRAY_DYE), ModItems.CONDENSE_LIGHT_GRAY_WATER_BUCKET.get());
        addColoredWaterRecipe(output, List.of(Items.LIGHT_GRAY_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_LIGHT_GRAY_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(output, List.of(Items.LIGHT_GRAY_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_LIGHT_GRAY_WATER_BUCKET.get(), "ink");
        addColoredWaterRecipe(output, List.of(Items.LIGHT_GRAY_DYE, Items.LIGHT_GRAY_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(output, List.of(Items.LIGHT_GRAY_DYE, Items.LIGHT_GRAY_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_BUCKET.get(), "ink");

        addColoredWaterRecipe(output, Items.CYAN_DYE, ModItems.CYAN_WATER_BUCKET.get());
        addColoredWaterRecipe(output, List.of(Items.CYAN_DYE, Items.CYAN_DYE), ModItems.CONDENSE_CYAN_WATER_BUCKET.get());
        addColoredWaterRecipe(output, List.of(Items.CYAN_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_CYAN_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(output, List.of(Items.CYAN_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_CYAN_WATER_BUCKET.get(), "ink");
        addColoredWaterRecipe(output, List.of(Items.CYAN_DYE, Items.CYAN_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_CONDENSE_CYAN_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(output, List.of(Items.CYAN_DYE, Items.CYAN_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_CONDENSE_CYAN_WATER_BUCKET.get(), "ink");

        addColoredWaterRecipe(output, Items.PURPLE_DYE, ModItems.PURPLE_WATER_BUCKET.get());
        addColoredWaterRecipe(output, List.of(Items.PURPLE_DYE, Items.PURPLE_DYE), ModItems.CONDENSE_PURPLE_WATER_BUCKET.get());
        addColoredWaterRecipe(output, List.of(Items.PURPLE_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_PURPLE_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(output, List.of(Items.PURPLE_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_PURPLE_WATER_BUCKET.get(), "ink");
        addColoredWaterRecipe(output, List.of(Items.PURPLE_DYE, Items.PURPLE_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_CONDENSE_PURPLE_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(output, List.of(Items.PURPLE_DYE, Items.PURPLE_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_CONDENSE_PURPLE_WATER_BUCKET.get(), "ink");

        addColoredWaterRecipe(output, Items.BLUE_DYE, ModItems.BLUE_WATER_BUCKET.get());
        addColoredWaterRecipe(output, List.of(Items.BLUE_DYE, Items.BLUE_DYE), ModItems.CONDENSE_BLUE_WATER_BUCKET.get());
        addColoredWaterRecipe(output, List.of(Items.BLUE_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_BLUE_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(output, List.of(Items.BLUE_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_BLUE_WATER_BUCKET.get(), "ink");
        addColoredWaterRecipe(output, List.of(Items.BLUE_DYE, Items.BLUE_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_CONDENSE_BLUE_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(output, List.of(Items.BLUE_DYE, Items.BLUE_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_CONDENSE_BLUE_WATER_BUCKET.get(), "ink");

        addColoredWaterRecipe(output, Items.BROWN_DYE, ModItems.BROWN_WATER_BUCKET.get());
        addColoredWaterRecipe(output, List.of(Items.BROWN_DYE, Items.BROWN_DYE), ModItems.CONDENSE_BROWN_WATER_BUCKET.get());
        addColoredWaterRecipe(output, List.of(Items.BROWN_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_BROWN_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(output, List.of(Items.BROWN_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_BROWN_WATER_BUCKET.get(), "ink");
        addColoredWaterRecipe(output, List.of(Items.BROWN_DYE, Items.BROWN_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_CONDENSE_BROWN_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(output, List.of(Items.BROWN_DYE, Items.BROWN_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_CONDENSE_BROWN_WATER_BUCKET.get(), "ink");

        addColoredWaterRecipe(output, Items.GREEN_DYE, ModItems.GREEN_WATER_BUCKET.get());
        addColoredWaterRecipe(output, List.of(Items.GREEN_DYE, Items.GREEN_DYE), ModItems.CONDENSE_GREEN_WATER_BUCKET.get());
        addColoredWaterRecipe(output, List.of(Items.GREEN_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_GREEN_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(output, List.of(Items.GREEN_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_GREEN_WATER_BUCKET.get(), "ink");
        addColoredWaterRecipe(output, List.of(Items.GREEN_DYE, Items.GREEN_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_CONDENSE_GREEN_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(output, List.of(Items.GREEN_DYE, Items.GREEN_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_CONDENSE_GREEN_WATER_BUCKET.get(), "ink");

        addColoredWaterRecipe(output, Items.RED_DYE, ModItems.RED_WATER_BUCKET.get());
        addColoredWaterRecipe(output, List.of(Items.RED_DYE, Items.RED_DYE), ModItems.CONDENSE_RED_WATER_BUCKET.get());
        addColoredWaterRecipe(output, List.of(Items.RED_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_RED_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(output, List.of(Items.RED_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_RED_WATER_BUCKET.get(), "ink");
        addColoredWaterRecipe(output, List.of(Items.RED_DYE, Items.RED_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_CONDENSE_RED_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(output, List.of(Items.RED_DYE, Items.RED_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_CONDENSE_RED_WATER_BUCKET.get(), "ink");

        addColoredWaterRecipe(output, Items.ORANGE_DYE, ModItems.ORANGE_WATER_BUCKET.get());
        addColoredWaterRecipe(output, List.of(Items.ORANGE_DYE, Items.ORANGE_DYE), ModItems.CONDENSE_RED_WATER_BUCKET.get());
        addColoredWaterRecipe(output, List.of(Items.ORANGE_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_ORANGE_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(output, List.of(Items.ORANGE_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_ORANGE_WATER_BUCKET.get(), "ink");
        addColoredWaterRecipe(output, List.of(Items.ORANGE_DYE, Items.ORANGE_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_CONDENSE_ORANGE_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(output, List.of(Items.ORANGE_DYE, Items.ORANGE_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_CONDENSE_ORANGE_WATER_BUCKET.get(), "ink");
    }

    private void addColoredWaterRecipe(RecipeOutput output, ItemLike singleDye, ItemLike resultItem) {
        addColoredWaterRecipe(output, List.of(singleDye), resultItem, 1, "");
    }

    private void addColoredWaterRecipe(RecipeOutput output, List<ItemLike> extraIngredients, ItemLike resultItem, String suffix) {
        addColoredWaterRecipe(output, extraIngredients, resultItem, 1, suffix);
    }

    private void addColoredWaterRecipe(RecipeOutput output, List<ItemLike> extraIngredients, ItemLike resultItem) {
        addColoredWaterRecipe(output, extraIngredients, resultItem, 1, "");
    }

    private void addColoredWaterRecipe(RecipeOutput output, List<ItemLike> extraIngredients, ItemLike resultItem, int count, String suffix) {

        NonNullList<Ingredient> ingredients = NonNullList.create();
        ingredients.add(Ingredient.of(Items.WATER_BUCKET));
        for (ItemLike item : extraIngredients) {
            ingredients.add(Ingredient.of(item));
        }

        ItemStack resultStack = new ItemStack(resultItem, count);

        ResourceLocation resultId = BuiltInRegistries.ITEM.getKey(resultItem.asItem());
        if (resultId == null) throw new IllegalStateException("Unregistered result item: " + resultItem.asItem());

        String path = resultId.getPath();
        if (suffix != null && !suffix.isEmpty()) {
            path += "_" + suffix;
        }

        ResourceLocation recipeId = ResourceLocation.fromNamespaceAndPath(ColoredWater.MOD_ID, path);

        ContainerCraftingRecipe recipe = new ContainerCraftingRecipe(
                "",
                CraftingBookCategory.MISC,
                resultStack,
                ingredients
        );

        output.accept(recipeId, recipe, null);
    }


}
