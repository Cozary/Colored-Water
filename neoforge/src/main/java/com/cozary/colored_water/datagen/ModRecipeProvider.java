package com.cozary.colored_water.datagen;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.init.ModItems;
import com.cozary.colored_water.recipe.ContainerCraftingRecipeBuilder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {


    protected ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
        addColoredWaterRecipe(Items.BLACK_DYE, ModItems.BLACK_WATER_BUCKET.get());
        addColoredWaterRecipe(List.of(Items.BLACK_DYE, Items.BLACK_DYE), ModItems.CONDENSE_BLACK_WATER_BUCKET.get());
        addColoredWaterRecipe(List.of(Items.BLACK_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_BLACK_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(List.of(Items.BLACK_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_BLACK_WATER_BUCKET.get(), "ink");
        addColoredWaterRecipe(List.of(Items.BLACK_DYE, Items.BLACK_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_CONDENSE_BLACK_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(List.of(Items.BLACK_DYE, Items.BLACK_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_CONDENSE_BLACK_WATER_BUCKET.get(), "ink");

        addColoredWaterRecipe(Items.WHITE_DYE, ModItems.WHITE_WATER_BUCKET.get());
        addColoredWaterRecipe(List.of(Items.WHITE_DYE, Items.WHITE_DYE), ModItems.CONDENSE_WHITE_WATER_BUCKET.get());
        addColoredWaterRecipe(List.of(Items.WHITE_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_WHITE_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(List.of(Items.WHITE_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_WHITE_WATER_BUCKET.get(), "ink");
        addColoredWaterRecipe(List.of(Items.WHITE_DYE, Items.WHITE_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_CONDENSE_WHITE_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(List.of(Items.WHITE_DYE, Items.WHITE_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_CONDENSE_WHITE_WATER_BUCKET.get(), "ink");

        addColoredWaterRecipe(Items.MAGENTA_DYE, ModItems.MAGENTA_WATER_BUCKET.get());
        addColoredWaterRecipe(List.of(Items.MAGENTA_DYE, Items.MAGENTA_DYE), ModItems.CONDENSE_MAGENTA_WATER_BUCKET.get());
        addColoredWaterRecipe(List.of(Items.MAGENTA_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_MAGENTA_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(List.of(Items.MAGENTA_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_MAGENTA_WATER_BUCKET.get(), "ink");
        addColoredWaterRecipe(List.of(Items.MAGENTA_DYE, Items.MAGENTA_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_CONDENSE_MAGENTA_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(List.of(Items.MAGENTA_DYE, Items.MAGENTA_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_CONDENSE_MAGENTA_WATER_BUCKET.get(), "ink");

        addColoredWaterRecipe(Items.LIGHT_BLUE_DYE, ModItems.LIGHT_BLUE_WATER_BUCKET.get());
        addColoredWaterRecipe(List.of(Items.LIGHT_BLUE_DYE, Items.LIGHT_BLUE_DYE), ModItems.CONDENSE_LIGHT_BLUE_WATER_BUCKET.get());
        addColoredWaterRecipe(List.of(Items.LIGHT_BLUE_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_LIGHT_BLUE_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(List.of(Items.LIGHT_BLUE_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_LIGHT_BLUE_WATER_BUCKET.get(), "ink");
        addColoredWaterRecipe(List.of(Items.LIGHT_BLUE_DYE, Items.LIGHT_BLUE_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(List.of(Items.LIGHT_BLUE_DYE, Items.LIGHT_BLUE_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_BUCKET.get(), "ink");

        addColoredWaterRecipe(Items.YELLOW_DYE, ModItems.YELLOW_WATER_BUCKET.get());
        addColoredWaterRecipe(List.of(Items.YELLOW_DYE, Items.YELLOW_DYE), ModItems.CONDENSE_YELLOW_WATER_BUCKET.get());
        addColoredWaterRecipe(List.of(Items.YELLOW_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_YELLOW_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(List.of(Items.YELLOW_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_YELLOW_WATER_BUCKET.get(), "ink");
        addColoredWaterRecipe(List.of(Items.YELLOW_DYE, Items.YELLOW_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_CONDENSE_YELLOW_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(List.of(Items.YELLOW_DYE, Items.YELLOW_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_CONDENSE_YELLOW_WATER_BUCKET.get(), "ink");

        addColoredWaterRecipe(Items.LIME_DYE, ModItems.LIME_WATER_BUCKET.get());
        addColoredWaterRecipe(List.of(Items.LIME_DYE, Items.LIME_DYE), ModItems.CONDENSE_LIME_WATER_BUCKET.get());
        addColoredWaterRecipe(List.of(Items.LIME_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_LIME_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(List.of(Items.LIME_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_LIME_WATER_BUCKET.get(), "ink");
        addColoredWaterRecipe(List.of(Items.LIME_DYE, Items.LIME_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_CONDENSE_LIME_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(List.of(Items.LIME_DYE, Items.LIME_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_CONDENSE_LIME_WATER_BUCKET.get(), "ink");

        addColoredWaterRecipe(Items.PINK_DYE, ModItems.PINK_WATER_BUCKET.get());
        addColoredWaterRecipe(List.of(Items.PINK_DYE, Items.PINK_DYE), ModItems.CONDENSE_PINK_WATER_BUCKET.get());
        addColoredWaterRecipe(List.of(Items.PINK_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_PINK_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(List.of(Items.PINK_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_PINK_WATER_BUCKET.get(), "ink");
        addColoredWaterRecipe(List.of(Items.PINK_DYE, Items.PINK_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_CONDENSE_PINK_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(List.of(Items.PINK_DYE, Items.PINK_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_CONDENSE_PINK_WATER_BUCKET.get(), "ink");

        addColoredWaterRecipe(Items.GRAY_DYE, ModItems.GRAY_WATER_BUCKET.get());
        addColoredWaterRecipe(List.of(Items.GRAY_DYE, Items.GRAY_DYE), ModItems.CONDENSE_GRAY_WATER_BUCKET.get());
        addColoredWaterRecipe(List.of(Items.GRAY_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_GRAY_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(List.of(Items.GRAY_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_GRAY_WATER_BUCKET.get(), "ink");
        addColoredWaterRecipe(List.of(Items.GRAY_DYE, Items.GRAY_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_CONDENSE_GRAY_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(List.of(Items.GRAY_DYE, Items.GRAY_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_CONDENSE_GRAY_WATER_BUCKET.get(), "ink");

        addColoredWaterRecipe(Items.LIGHT_GRAY_DYE, ModItems.LIGHT_GRAY_WATER_BUCKET.get());
        addColoredWaterRecipe(List.of(Items.LIGHT_GRAY_DYE, Items.LIGHT_GRAY_DYE), ModItems.CONDENSE_LIGHT_GRAY_WATER_BUCKET.get());
        addColoredWaterRecipe(List.of(Items.LIGHT_GRAY_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_LIGHT_GRAY_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(List.of(Items.LIGHT_GRAY_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_LIGHT_GRAY_WATER_BUCKET.get(), "ink");
        addColoredWaterRecipe(List.of(Items.LIGHT_GRAY_DYE, Items.LIGHT_GRAY_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(List.of(Items.LIGHT_GRAY_DYE, Items.LIGHT_GRAY_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_BUCKET.get(), "ink");

        addColoredWaterRecipe(Items.CYAN_DYE, ModItems.CYAN_WATER_BUCKET.get());
        addColoredWaterRecipe(List.of(Items.CYAN_DYE, Items.CYAN_DYE), ModItems.CONDENSE_CYAN_WATER_BUCKET.get());
        addColoredWaterRecipe(List.of(Items.CYAN_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_CYAN_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(List.of(Items.CYAN_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_CYAN_WATER_BUCKET.get(), "ink");
        addColoredWaterRecipe(List.of(Items.CYAN_DYE, Items.CYAN_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_CONDENSE_CYAN_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(List.of(Items.CYAN_DYE, Items.CYAN_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_CONDENSE_CYAN_WATER_BUCKET.get(), "ink");

        addColoredWaterRecipe(Items.PURPLE_DYE, ModItems.PURPLE_WATER_BUCKET.get());
        addColoredWaterRecipe(List.of(Items.PURPLE_DYE, Items.PURPLE_DYE), ModItems.CONDENSE_PURPLE_WATER_BUCKET.get());
        addColoredWaterRecipe(List.of(Items.PURPLE_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_PURPLE_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(List.of(Items.PURPLE_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_PURPLE_WATER_BUCKET.get(), "ink");
        addColoredWaterRecipe(List.of(Items.PURPLE_DYE, Items.PURPLE_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_CONDENSE_PURPLE_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(List.of(Items.PURPLE_DYE, Items.PURPLE_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_CONDENSE_PURPLE_WATER_BUCKET.get(), "ink");

        addColoredWaterRecipe(Items.BLUE_DYE, ModItems.BLUE_WATER_BUCKET.get());
        addColoredWaterRecipe(List.of(Items.BLUE_DYE, Items.BLUE_DYE), ModItems.CONDENSE_BLUE_WATER_BUCKET.get());
        addColoredWaterRecipe(List.of(Items.BLUE_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_BLUE_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(List.of(Items.BLUE_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_BLUE_WATER_BUCKET.get(), "ink");
        addColoredWaterRecipe(List.of(Items.BLUE_DYE, Items.BLUE_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_CONDENSE_BLUE_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(List.of(Items.BLUE_DYE, Items.BLUE_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_CONDENSE_BLUE_WATER_BUCKET.get(), "ink");

        addColoredWaterRecipe(Items.BROWN_DYE, ModItems.BROWN_WATER_BUCKET.get());
        addColoredWaterRecipe(List.of(Items.BROWN_DYE, Items.BROWN_DYE), ModItems.CONDENSE_BROWN_WATER_BUCKET.get());
        addColoredWaterRecipe(List.of(Items.BROWN_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_BROWN_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(List.of(Items.BROWN_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_BROWN_WATER_BUCKET.get(), "ink");
        addColoredWaterRecipe(List.of(Items.BROWN_DYE, Items.BROWN_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_CONDENSE_BROWN_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(List.of(Items.BROWN_DYE, Items.BROWN_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_CONDENSE_BROWN_WATER_BUCKET.get(), "ink");

        addColoredWaterRecipe(Items.GREEN_DYE, ModItems.GREEN_WATER_BUCKET.get());
        addColoredWaterRecipe(List.of(Items.GREEN_DYE, Items.GREEN_DYE), ModItems.CONDENSE_GREEN_WATER_BUCKET.get());
        addColoredWaterRecipe(List.of(Items.GREEN_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_GREEN_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(List.of(Items.GREEN_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_GREEN_WATER_BUCKET.get(), "ink");
        addColoredWaterRecipe(List.of(Items.GREEN_DYE, Items.GREEN_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_CONDENSE_GREEN_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(List.of(Items.GREEN_DYE, Items.GREEN_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_CONDENSE_GREEN_WATER_BUCKET.get(), "ink");

        addColoredWaterRecipe(Items.RED_DYE, ModItems.RED_WATER_BUCKET.get());
        addColoredWaterRecipe(List.of(Items.RED_DYE, Items.RED_DYE), ModItems.CONDENSE_RED_WATER_BUCKET.get());
        addColoredWaterRecipe(List.of(Items.RED_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_RED_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(List.of(Items.RED_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_RED_WATER_BUCKET.get(), "ink");
        addColoredWaterRecipe(List.of(Items.RED_DYE, Items.RED_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_CONDENSE_RED_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(List.of(Items.RED_DYE, Items.RED_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_CONDENSE_RED_WATER_BUCKET.get(), "ink");

        addColoredWaterRecipe(Items.ORANGE_DYE, ModItems.ORANGE_WATER_BUCKET.get());
        addColoredWaterRecipe(List.of(Items.ORANGE_DYE, Items.ORANGE_DYE), ModItems.CONDENSE_RED_WATER_BUCKET.get());
        addColoredWaterRecipe(List.of(Items.ORANGE_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_ORANGE_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(List.of(Items.ORANGE_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_ORANGE_WATER_BUCKET.get(), "ink");
        addColoredWaterRecipe(List.of(Items.ORANGE_DYE, Items.ORANGE_DYE, Items.GLOWSTONE_DUST), ModItems.LUMINOUS_CONDENSE_ORANGE_WATER_BUCKET.get(), "glowstone");
        addColoredWaterRecipe(List.of(Items.ORANGE_DYE, Items.ORANGE_DYE, Items.GLOW_INK_SAC), ModItems.LUMINOUS_CONDENSE_ORANGE_WATER_BUCKET.get(), "ink");
    }

    private void addColoredWaterRecipe(ItemLike singleDye, ItemLike resultItem) {
        addColoredWaterRecipe(List.of(singleDye), resultItem, 1, "");
    }

    private void addColoredWaterRecipe(List<ItemLike> extraIngredients, ItemLike resultItem, String suffix) {
        addColoredWaterRecipe(extraIngredients, resultItem, 1, suffix);
    }

    private void addColoredWaterRecipe(List<ItemLike> extraIngredients, ItemLike resultItem) {
        addColoredWaterRecipe(extraIngredients, resultItem, 1, "");
    }

    private void addColoredWaterRecipe(List<ItemLike> extraIngredients, ItemLike resultItem, int count, String suffix) {
        Identifier resultId = BuiltInRegistries.ITEM.getKey(resultItem.asItem());
        if (resultId == null) throw new IllegalStateException("Unregistered result item: " + resultItem.asItem());

        String path = resultId.getPath();
        if (suffix != null && !suffix.isEmpty()) {
            path += "_" + suffix;
        }
        Identifier recipeId = Identifier.fromNamespaceAndPath(ColoredWater.MOD_ID, path);

        HolderGetter<Item> itemGetter = this.registries
                .lookup(Registries.ITEM)
                .orElseThrow(() -> new IllegalStateException("No item registry found"));

        ItemStack resultStack = resultItem.asItem().getDefaultInstance().copy();
        resultStack.setCount(count);

        ContainerCraftingRecipeBuilder builder = ContainerCraftingRecipeBuilder.shapeless(
                itemGetter,
                RecipeCategory.MISC,
                resultStack.getItem()
        );

        builder.requires(Items.WATER_BUCKET);
        for (ItemLike item : extraIngredients) {
            builder.requires(item);
        }

        builder.unlockedBy("has_water_bucket", has(Items.WATER_BUCKET));
        builder.save(this.output, ResourceKey.create(Registries.RECIPE, recipeId));
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider) {
            super(packOutput, provider);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
            return new ModRecipeProvider(provider, recipeOutput);
        }

        @Override
        public String getName() {
            return "Colored Water Recipers";
        }
    }

}
