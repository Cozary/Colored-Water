package com.cozary.colored_water.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ContainerCraftingRecipe extends ShapelessRecipe {

    public static final MapCodec<ContainerCraftingRecipe> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
        return instance.group(
                Recipe.CommonInfo.MAP_CODEC.forGetter(recipe -> recipe.commonInfo),
                CraftingRecipe.CraftingBookInfo.MAP_CODEC.forGetter(recipe -> recipe.bookInfo),
                ItemStackTemplate.MAP_CODEC.fieldOf("result").forGetter(recipe -> recipe.result),
                Ingredient.CODEC.listOf(1, 9).fieldOf("ingredients").forGetter(recipe -> recipe.ingredients)
        ).apply(instance, ContainerCraftingRecipe::new);
    });

    public static final StreamCodec<RegistryFriendlyByteBuf, ContainerCraftingRecipe> STREAM_CODEC = StreamCodec.composite(
            Recipe.CommonInfo.STREAM_CODEC, (recipe) -> recipe.commonInfo,
            CraftingRecipe.CraftingBookInfo.STREAM_CODEC, (recipe) -> recipe.bookInfo,
            ItemStackTemplate.STREAM_CODEC, (recipe) -> recipe.result,
            Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()), (recipe) -> recipe.ingredients,
            ContainerCraftingRecipe::new
    );

    public static final RecipeSerializer<ContainerCraftingRecipe> SERIALIZER = new RecipeSerializer<>(CODEC, STREAM_CODEC);

    private final Recipe.CommonInfo commonInfo;
    private final CraftingRecipe.CraftingBookInfo bookInfo;
    private final ItemStackTemplate result;
    private final List<Ingredient> ingredients;

    public ContainerCraftingRecipe(Recipe.CommonInfo commonInfo, CraftingRecipe.CraftingBookInfo bookInfo, ItemStackTemplate result, List<Ingredient> ingredients) {
        super(commonInfo, bookInfo, result, ingredients);
        this.commonInfo = commonInfo;
        this.bookInfo = bookInfo;
        this.result = result;
        this.ingredients = ingredients;
    }

    @Override
    @SuppressWarnings("unchecked")
    public @NotNull RecipeSerializer<ShapelessRecipe> getSerializer() {
        return (RecipeSerializer<ShapelessRecipe>) (Object) SERIALIZER;
    }

    @Override
    public @NotNull NonNullList<ItemStack> getRemainingItems(CraftingInput input) {
        return NonNullList.withSize(input.size(), ItemStack.EMPTY);
    }
}
