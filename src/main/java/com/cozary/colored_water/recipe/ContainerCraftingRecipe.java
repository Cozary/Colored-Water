package com.cozary.colored_water.recipe;

import com.cozary.colored_water.init.ModItems;
import com.cozary.colored_water.init.ModRecipe;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
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
        return ModRecipe.CONTAINER_CRAFTING_RECIPE.get();
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

        public static final MapCodec<ContainerCraftingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance
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

        public static final StreamCodec<RegistryFriendlyByteBuf, ContainerCraftingRecipe> STREAM_CODEC = StreamCodec.of(
                ContainerCraftingRecipe.Serializer::toNetwork, ContainerCraftingRecipe.Serializer::fromNetwork
        );

        @Override
        public @NotNull MapCodec<ContainerCraftingRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, ContainerCraftingRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static ContainerCraftingRecipe fromNetwork(RegistryFriendlyByteBuf friendlyByteBuf) {
            String s = friendlyByteBuf.readUtf();
            int i = friendlyByteBuf.readVarInt();
            NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i, Ingredient.EMPTY);

            for (int j = 0; j < nonnulllist.size(); ++j) {
                nonnulllist.set(j, Ingredient.CONTENTS_STREAM_CODEC.decode(friendlyByteBuf));
            }

            ItemStack itemstack = ItemStack.STREAM_CODEC.decode(friendlyByteBuf);
            return new ContainerCraftingRecipe(s, CraftingBookCategory.MISC, itemstack, nonnulllist);
        }


        private static void toNetwork(RegistryFriendlyByteBuf buffer, ContainerCraftingRecipe recipe) {
            buffer.writeUtf(recipe.group);
            buffer.writeVarInt(recipe.recipeItems.size());

            for (Ingredient ingredient : recipe.recipeItems) {
                Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, ingredient);
            }

            ItemStack.STREAM_CODEC.encode(buffer, recipe.recipeOutput);
        }
    }


}
