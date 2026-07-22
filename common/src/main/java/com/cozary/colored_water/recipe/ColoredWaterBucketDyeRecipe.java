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
        boolean hasModifierOrDye = false;

        for (int i = 0; i < input.size(); ++i) {
            ItemStack stack = input.getItem(i);
            if (!stack.isEmpty()) {
                if (stack.is(net.minecraft.world.item.Items.WATER_BUCKET) || stack.is(ModItems.COLORED_WATER_BUCKET.get())) {
                    if (!bucket.isEmpty()) return false;
                    bucket = stack;
                } else if (stack.getItem() instanceof DyeItem || stack.is(net.minecraft.world.item.Items.REDSTONE) || stack.is(net.minecraft.world.item.Items.GLOWSTONE_DUST)) {
                    hasModifierOrDye = true;
                } else {
                    return false;
                }
            }
        }

        return !bucket.isEmpty() && hasModifierOrDye;
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull CraftingInput input, HolderLookup.@NotNull Provider registries) {
        ItemStack bucketInput = ItemStack.EMPTY;
        List<DyeItem> dyes = new ArrayList<>();
        boolean addRedstone = false;
        boolean addGlowstone = false;

        for (int i = 0; i < input.size(); ++i) {
            ItemStack stack = input.getItem(i);
            if (!stack.isEmpty()) {
                if (stack.is(net.minecraft.world.item.Items.WATER_BUCKET) || stack.is(ModItems.COLORED_WATER_BUCKET.get())) {
                    if (!bucketInput.isEmpty()) return ItemStack.EMPTY;
                    bucketInput = stack;
                } else if (stack.getItem() instanceof DyeItem dye) {
                    dyes.add(dye);
                } else if (stack.is(net.minecraft.world.item.Items.REDSTONE)) {
                    if (addRedstone) return ItemStack.EMPTY;
                    addRedstone = true;
                } else if (stack.is(net.minecraft.world.item.Items.GLOWSTONE_DUST)) {
                    if (addGlowstone) return ItemStack.EMPTY;
                    addGlowstone = true;
                }
            }
        }

        if (bucketInput.isEmpty() || (dyes.isEmpty() && !addRedstone && !addGlowstone)) {
            return ItemStack.EMPTY;
        }

        ItemStack resultStack;
        if (bucketInput.is(net.minecraft.world.item.Items.WATER_BUCKET)) {
            resultStack = new ItemStack(ModItems.COLORED_WATER_BUCKET.get());
        } else {
            resultStack = bucketInput.copy();
        }

        if (!dyes.isEmpty()) {
            resultStack = DyedItemColor.applyDyes(resultStack, dyes);
        } else if (bucketInput.is(net.minecraft.world.item.Items.WATER_BUCKET)) {
            resultStack.set(net.minecraft.core.component.DataComponents.DYED_COLOR, new DyedItemColor(0x3F76E4));
        }

        boolean isCondensed = false;
        int luminosity = 0;
        int alpha = 0;

        net.minecraft.world.item.component.CustomData customData = resultStack.get(net.minecraft.core.component.DataComponents.CUSTOM_DATA);
        if (customData != null) {
            net.minecraft.nbt.CompoundTag tag = customData.copyTag();
            isCondensed = tag.getBooleanOr("Condensed", false);
            luminosity = tag.getIntOr("Luminosity", 0);
            alpha = tag.getIntOr("Alpha", 0);
        }

        if (addRedstone) isCondensed = true;
        if (addGlowstone) luminosity = 15;

        if (alpha == 0) {
            alpha = isCondensed ? 255 : 180;
        }

        net.minecraft.nbt.CompoundTag newTag = new net.minecraft.nbt.CompoundTag();
        newTag.putBoolean("Condensed", isCondensed);
        newTag.putInt("Luminosity", luminosity);
        newTag.putInt("Alpha", alpha);

        resultStack.set(net.minecraft.core.component.DataComponents.CUSTOM_DATA, net.minecraft.world.item.component.CustomData.of(newTag));

        return resultStack;
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
