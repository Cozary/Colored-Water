package com.cozary.colored_water.recipe;

import com.cozary.colored_water.init.ModItems;
import com.cozary.colored_water.init.ModRecipe;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.util.collection.DefaultedList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ContainerCraftingRecipe extends ShapelessRecipe {

    private final String group;
    private final ItemStack recipeOutput;
    private final DefaultedList<Ingredient> recipeItems;

    public ContainerCraftingRecipe(String group, CraftingRecipeCategory category, ItemStack result, DefaultedList<Ingredient> ingredients) {
        super(group, category, result, ingredients);
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

        public Serializer() {
        }

        public static final MapCodec<ContainerCraftingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance
                .group(
                        Codec.STRING.fieldOf("group").orElse("").forGetter(ContainerCraftingRecipe::getGroup),
                        ItemStack.CODEC.fieldOf("result").forGetter(result -> result.recipeOutput),
                        Codec.list(Ingredient.ALLOW_EMPTY_CODEC).fieldOf("ingredients").forGetter(ContainerCraftingRecipe::getIngredients)
                ).apply(instance, (group, result, ingredients) -> new ContainerCraftingRecipe(
                        group,
                        CraftingRecipeCategory.MISC,
                        result,
                        DefaultedList.copyOf(Ingredient.EMPTY, ingredients.toArray(new Ingredient[0]))
                ))
        );


        public static final PacketCodec<RegistryByteBuf, ContainerCraftingRecipe> PACKET_CODEC = PacketCodec.ofStatic(ContainerCraftingRecipe.Serializer::write, ContainerCraftingRecipe.Serializer::read);


        @Override
        public @NotNull MapCodec<ContainerCraftingRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, ContainerCraftingRecipe> packetCodec() {
            return PACKET_CODEC;
        }

        private static ContainerCraftingRecipe read(RegistryByteBuf buf) {
            String s = buf.readString();
            int i = buf.readVarInt();
            DefaultedList<Ingredient> nonnulllist = DefaultedList.ofSize(i, Ingredient.EMPTY);

            for (int j = 0; j < nonnulllist.size(); ++j) {
                nonnulllist.set(j, Ingredient.PACKET_CODEC.decode(buf));
            }

            ItemStack itemstack =  ItemStack.PACKET_CODEC.decode(buf);
            return new ContainerCraftingRecipe(s, CraftingRecipeCategory.MISC, itemstack, nonnulllist);
        }

        private static void write(RegistryByteBuf buf, ContainerCraftingRecipe recipe) {
            buf.writeString(recipe.group);
            buf.writeVarInt(recipe.recipeItems.size());

            for (Ingredient ingredient : recipe.recipeItems) {
                Ingredient.PACKET_CODEC.encode(buf, ingredient);
            }

            ItemStack.PACKET_CODEC.encode(buf, recipe.recipeOutput);
        }

    }

}
