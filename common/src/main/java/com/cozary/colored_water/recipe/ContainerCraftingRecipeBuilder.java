package com.cozary.colored_water.recipe;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ContainerCraftingRecipeBuilder implements RecipeBuilder {
    private final HolderGetter<Item> items;
    private final RecipeCategory category;
    private final ItemStack result;
    private final List<Ingredient> ingredients = new ArrayList<>();
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
    @Nullable
    private String group;

    private ContainerCraftingRecipeBuilder(HolderGetter<Item> items, RecipeCategory category, ItemStack result) {
        this.items = items;
        this.category = category;
        this.result = result;
    }

    public static ContainerCraftingRecipeBuilder shapeless(HolderGetter<Item> items, RecipeCategory category, ItemLike result) {
        return shapeless(items, category, result, 1);
    }

    public static ContainerCraftingRecipeBuilder shapeless(HolderGetter<Item> items, RecipeCategory category, ItemLike result, int count) {
        return new ContainerCraftingRecipeBuilder(items, category, result.asItem().getDefaultInstance().copyWithCount(count));
    }

    public ContainerCraftingRecipeBuilder requires(ItemLike item) {
        return this.requires(Ingredient.of(item));
    }

    public ContainerCraftingRecipeBuilder requires(ItemLike item, int quantity) {
        for (int i = 0; i < quantity; i++) {
            this.ingredients.add(Ingredient.of(item));
        }
        return this;
    }

    public ContainerCraftingRecipeBuilder requires(Ingredient ingredient) {
        return this.requires(ingredient, 1);
    }

    public ContainerCraftingRecipeBuilder requires(Ingredient ingredient, int quantity) {
        for (int i = 0; i < quantity; i++) {
            this.ingredients.add(ingredient);
        }
        return this;
    }

    public ContainerCraftingRecipeBuilder group(@Nullable String groupName) {
        this.group = groupName;
        return this;
    }

    @Override
    public Item getResult() {
        return this.result.getItem();
    }

    public ContainerCraftingRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public void save(RecipeOutput output, ResourceKey<Recipe<?>> recipeId) {
        this.ensureValid(recipeId);

        Advancement.Builder advancement = output.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .requirements(AdvancementRequirements.Strategy.OR);

        this.criteria.forEach(advancement::addCriterion);

        ContainerCraftingRecipe recipe = new ContainerCraftingRecipe(
                this.group != null ? this.group : "",
                RecipeBuilder.determineBookCategory(this.category),
                this.result,
                this.ingredients
        );

        output.accept(recipeId, recipe, advancement.build(recipeId.location().withPrefix("recipes/" + this.category.getFolderName() + "/")));
    }

    private void ensureValid(ResourceKey<Recipe<?>> id) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + id.location());
        }
    }
}
