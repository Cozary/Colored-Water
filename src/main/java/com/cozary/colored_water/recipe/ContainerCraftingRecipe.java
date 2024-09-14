package com.cozary.colored_water.recipe;

import com.cozary.colored_water.items.ModItems;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ContainerCraftingRecipe extends ShapelessRecipe {

    private final String group;
    private final ItemStack recipeOutput;
    private final NonNullList<Ingredient> recipeItems;

    public ContainerCraftingRecipe(String groupIn, CraftingBookCategory craftingBookCategory, ItemStack recipeOutputIn, NonNullList<Ingredient> recipeItemsIn) {
        super(groupIn, craftingBookCategory, recipeOutputIn, recipeItemsIn);
        this.group = groupIn;
        this.recipeOutput = recipeOutputIn;
        this.recipeItems = recipeItemsIn;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return ModItems.CONTAINER_CRAFTING_RECIPE.get();
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        return recipeItems;
    }

    @Override
    public @NotNull NonNullList<ItemStack> getRemainingItems(CraftingContainer inv) {
        return NonNullList.withSize(inv.getContainerSize(), ItemStack.EMPTY);
    }

    public static class Serializer implements RecipeSerializer<ContainerCraftingRecipe> {

        public static final Codec<ContainerCraftingRecipe> CODEC = RecordCodecBuilder.create(instance -> instance
                .group(
                        Codec.STRING.fieldOf("group").orElse("").forGetter(ContainerCraftingRecipe::getGroup),
                        ItemStack.CODEC.fieldOf("result").forGetter(result -> result.recipeOutput),
                        Codec.list(Ingredient.CODEC).fieldOf("ingredients").forGetter(ContainerCraftingRecipe::getIngredients)
                ).apply(instance, (group, result, ingredients) -> new ContainerCraftingRecipe(
                        group,
                        CraftingBookCategory.MISC,
                        result,
                        NonNullList.of(Ingredient.EMPTY, ingredients.toArray(new Ingredient[0]))
                ))
        );

        @Override
        public @NotNull Codec<ContainerCraftingRecipe> codec() {
            return CODEC;
        }

        @Override
        public @Nullable ContainerCraftingRecipe fromNetwork(FriendlyByteBuf friendlyByteBuf) {
            String s = friendlyByteBuf.readUtf();
            int i = friendlyByteBuf.readVarInt();
            NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i, Ingredient.EMPTY);

            for (int j = 0; j < nonnulllist.size(); ++j) {
                nonnulllist.set(j, Ingredient.fromNetwork(friendlyByteBuf));
            }

            ItemStack itemstack = friendlyByteBuf.readItem();
            return new ContainerCraftingRecipe(s, CraftingBookCategory.MISC, itemstack, nonnulllist);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, ContainerCraftingRecipe recipe) {
            buffer.writeUtf(recipe.group);
            buffer.writeVarInt(recipe.recipeItems.size());

            for (Ingredient ingredient : recipe.recipeItems) {
                ingredient.toNetwork(buffer);
            }

            buffer.writeItem(recipe.recipeOutput);
        }
    }


}
