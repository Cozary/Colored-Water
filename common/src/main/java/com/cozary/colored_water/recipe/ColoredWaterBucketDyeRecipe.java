package com.cozary.colored_water.recipe;

import com.cozary.colored_water.init.ModItems;
import com.cozary.colored_water.init.ModRecipe;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO need to test if this recipe and the other one in truly necessary.
 */
public class ColoredWaterBucketDyeRecipe extends CustomRecipe {

    public ColoredWaterBucketDyeRecipe(CraftingBookCategory category) {
        super(category);
    }


    @Override
    public boolean matches(@NotNull CraftingInput input, @NotNull Level level) {
        ItemStack bucket = ItemStack.EMPTY;
        boolean hasDye = false;

        for (int i = 0; i < input.size(); ++i) {
            ItemStack stack = input.getItem(i);
            if (!stack.isEmpty()) {
                if (stack.is(ModItems.COLORED_WATER_BUCKET.get())) {
                    if (!bucket.isEmpty()) return false;
                    bucket = stack;
                } else if (stack.getItem() instanceof DyeItem) {
                    hasDye = true;
                } else {
                    return false;
                }
            }
        }

        return !bucket.isEmpty() && hasDye;
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull CraftingInput input, HolderLookup.@NotNull Provider registries) {
        ItemStack bucket = ItemStack.EMPTY;
        List<DyeItem> dyes = new ArrayList<>();

        for (int i = 0; i < input.size(); ++i) {
            ItemStack stack = input.getItem(i);
            if (!stack.isEmpty()) {
                if (stack.is(ModItems.COLORED_WATER_BUCKET.get())) {
                    if (!bucket.isEmpty()) return ItemStack.EMPTY;
                    bucket = stack.copy();
                } else if (stack.getItem() instanceof DyeItem dye) {
                    dyes.add(dye);
                }
            }
        }

        return !bucket.isEmpty() && !dyes.isEmpty() ? DyedItemColor.applyDyes(bucket, dyes) : ItemStack.EMPTY;
    }


    @Override
    public @NotNull NonNullList<ItemStack> getRemainingItems(@NotNull CraftingInput input) {
        return NonNullList.withSize(input.size(), ItemStack.EMPTY);
    }

    @Override
    public @NotNull RecipeSerializer<CustomRecipe> getSerializer() {
        return (RecipeSerializer<CustomRecipe>) (Object) ModRecipe.COLORED_WATER_BUCKET_DYE.get();
    }

    public static class Serializer implements RecipeSerializer<ColoredWaterBucketDyeRecipe> {
        public static final StreamCodec<RegistryFriendlyByteBuf, ColoredWaterBucketDyeRecipe> STREAM_CODEC = StreamCodec.composite(
                CraftingBookCategory.STREAM_CODEC, ColoredWaterBucketDyeRecipe::category,
                ColoredWaterBucketDyeRecipe::new
        );
        private static final MapCodec<ColoredWaterBucketDyeRecipe> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
            return instance.group(
                    CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.MISC).forGetter(ColoredWaterBucketDyeRecipe::category)
            ).apply(instance, ColoredWaterBucketDyeRecipe::new);
        });

        public Serializer() {
        }

        @Override
        public @NotNull MapCodec<ColoredWaterBucketDyeRecipe> codec() {
            return CODEC;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, ColoredWaterBucketDyeRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
