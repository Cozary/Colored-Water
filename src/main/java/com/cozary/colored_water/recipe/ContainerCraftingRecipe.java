package com.cozary.colored_water.recipe;

import com.cozary.colored_water.init.ModRecipe;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import org.jetbrains.annotations.NotNull;

public class ContainerCraftingRecipe extends ShapelessRecipe {

    private final String group;
    private final ItemStack recipeOutput;
    private final DefaultedList<Ingredient> recipeItems;

    public ContainerCraftingRecipe(Identifier id, String group, CraftingRecipeCategory category, ItemStack result, DefaultedList<Ingredient> ingredients) {
        super(id, group, category, result, ingredients);
        this.group = group;
        this.recipeOutput = result;
        this.recipeItems = ingredients;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return ModRecipe.CONTAINER_CRAFTING_RECIPE;
    }

    @Override
    public @NotNull DefaultedList<Ingredient> getIngredients() {
        return recipeItems;
    }

    @Override
    public DefaultedList<ItemStack> getRemainder(RecipeInputInventory inventory) {
        return DefaultedList.ofSize(inventory.size(), ItemStack.EMPTY);
    }

    public static class Serializer implements RecipeSerializer<ContainerCraftingRecipe> {

        private static DefaultedList<Ingredient> readIngredients(JsonArray jsonArray) {
            DefaultedList<Ingredient> ingredients = DefaultedList.of();

            for (int i = 0; i < jsonArray.size(); ++i) {
                Ingredient ingredient = Ingredient.fromJson(jsonArray.get(i));
                if (!ingredient.isEmpty()) {
                    ingredients.add(ingredient);
                }
            }

            return ingredients;
        }

        @Override
        public ContainerCraftingRecipe read(Identifier id, JsonObject json) {

            String group = JsonHelper.getString(json, "group", "");
            DefaultedList<Ingredient> ingredients = readIngredients(json.getAsJsonArray("ingredients"));

            if (ingredients.isEmpty()) {
                throw new JsonParseException("No ingredients for recipe " + id);
            }

            JsonObject resultObject = JsonHelper.getObject(json, "result");
            ItemStack result = ShapedRecipe.outputFromJson(resultObject);

            return new ContainerCraftingRecipe(id, group, CraftingRecipeCategory.MISC, result, ingredients);
        }

        @Override
        public ContainerCraftingRecipe read(Identifier id, PacketByteBuf buf) {
            String group = buf.readString();
            int count = buf.readVarInt();

            DefaultedList<Ingredient> ingredients = DefaultedList.ofSize(count, Ingredient.EMPTY);
            for (int i = 0; i < count; ++i) {
                ingredients.set(i, Ingredient.fromPacket(buf));
            }

            ItemStack result = buf.readItemStack();
            return new ContainerCraftingRecipe(id, group, CraftingRecipeCategory.MISC, result, ingredients);
        }

        @Override
        public void write(PacketByteBuf buf, ContainerCraftingRecipe recipe) {
            buf.writeString(recipe.group);
            buf.writeVarInt(recipe.recipeItems.size());

            for (Ingredient ingredient : recipe.recipeItems) {
                ingredient.write(buf);
            }

            buf.writeItemStack(recipe.recipeOutput);
        }
    }
}
