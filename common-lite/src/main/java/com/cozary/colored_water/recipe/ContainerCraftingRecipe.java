package com.cozary.colored_water.recipe;

import com.cozary.colored_water.init.ModRecipe;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ContainerCraftingRecipe extends ShapelessRecipe {

    final CraftingBookCategory category;
    private final String group;
    private final ItemStack recipeOutput;
    private final List<Ingredient> recipeItems;

    public ContainerCraftingRecipe(String group, CraftingBookCategory category, ItemStack result, List<Ingredient> ingredients) {
        super(group, category, result, ingredients);
        this.group = group;
        this.category = category;
        this.recipeOutput = result;
        this.recipeItems = ingredients;
    }


    @Override
    public @NotNull RecipeSerializer<ShapelessRecipe> getSerializer() {
        return (RecipeSerializer<ShapelessRecipe>) ModRecipe.CONTAINER_CRAFTING_RECIPE.get();
    }


    @Override
    public @NotNull NonNullList<ItemStack> getRemainingItems(CraftingInput input) {
        return NonNullList.withSize(input.size(), ItemStack.EMPTY);
    }

    public static class Serializer implements RecipeSerializer<ContainerCraftingRecipe> {
        public static final StreamCodec<RegistryFriendlyByteBuf, ContainerCraftingRecipe> STREAM_CODEC;
        private static final MapCodec<ContainerCraftingRecipe> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
            return instance.group(
                    Codec.STRING.optionalFieldOf("group", "").forGetter(recipe -> recipe.group),
                    CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.MISC).forGetter(recipe -> recipe.category),
                    ItemStack.STRICT_CODEC.fieldOf("result").forGetter(recipe -> recipe.recipeOutput),
                    Ingredient.CODEC.listOf(1, 9).fieldOf("ingredients").forGetter(recipe -> recipe.recipeItems)
            ).apply(instance, ContainerCraftingRecipe::new
            );
        });

        static {
            STREAM_CODEC = StreamCodec.composite(
                    ByteBufCodecs.STRING_UTF8, (recipe) -> {
                        return recipe.group;
                    }, CraftingBookCategory.STREAM_CODEC, (recipe) -> {
                        return recipe.category;
                    }, ItemStack.STREAM_CODEC, (recipe) -> {
                        return recipe.recipeOutput;
                    }, Ingredient.CONTENTS_STREAM_CODEC.apply(
                            ByteBufCodecs.list()), (recipe) -> {
                        return recipe.recipeItems;
                    }, ContainerCraftingRecipe::new);
        }

        public Serializer() {
        }

        @Override
        public MapCodec<ContainerCraftingRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, ContainerCraftingRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}

