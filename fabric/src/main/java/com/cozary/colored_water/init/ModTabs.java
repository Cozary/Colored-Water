package com.cozary.colored_water.init;

import com.cozary.colored_water.ColoredWater;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.DyedItemColor;

public class ModTabs {

    private static final ResourceKey<CreativeModeTab> ITEM_GROUP = ResourceKey.create(Registries.CREATIVE_MODE_TAB, Identifier.fromNamespaceAndPath(ColoredWater.MOD_ID, ColoredWater.MOD_ID + "_tab"));

    public static void loadClass() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ITEM_GROUP, FabricItemGroup.builder()
                .title(Component.translatable("itemGroup.colored_water"))
                .icon(() -> {
                    ItemStack stack = new ItemStack(ModItems.COLORED_WATER_BUCKET.get());
                    stack.set(DataComponents.DYED_COLOR, new DyedItemColor(DyeColor.PINK.getTextureDiffuseColor()));
                    return stack;
                })
                .displayItems((parameters, output) -> fillCreativeTab(output))
                .build()
        );
    }

    public static void fillCreativeTab(CreativeModeTab.Output output) {
        DyeColor[] colors = DyeColor.values();
        Item bucketItem = ModItems.COLORED_WATER_BUCKET.get();

        // 1. Normal (16 colors)
        for (DyeColor color : colors) {
            output.accept(createBucketStack(bucketItem, color.getTextureDiffuseColor(), false, 0));
        }
        // 2. Condense (16 colors)
        for (DyeColor color : colors) {
            output.accept(createBucketStack(bucketItem, color.getTextureDiffuseColor(), true, 0));
        }
        // 3. Luminous (16 colors)
        for (DyeColor color : colors) {
            output.accept(createBucketStack(bucketItem, color.getTextureDiffuseColor(), false, 15));
        }
        // 4. Luminous Condense (16 colors)
        for (DyeColor color : colors) {
            output.accept(createBucketStack(bucketItem, color.getTextureDiffuseColor(), true, 15));
        }
    }

    public static ItemStack createBucketStack(Item item, int rgbColor, boolean condensed, int luminosity) {
        ItemStack stack = new ItemStack(item);
        stack.set(DataComponents.DYED_COLOR, new DyedItemColor(rgbColor));
        CompoundTag tag = new CompoundTag();
        tag.putBoolean("Condensed", condensed);
        tag.putInt("Luminosity", luminosity);
        CustomData.update(DataComponents.CUSTOM_DATA, stack, t -> t.merge(tag));
        return stack;
    }
}
