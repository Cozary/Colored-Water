package com.cozary.colored_water.init;

import com.cozary.colored_water.ColoredWater;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.DyedItemColor;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.cozary.colored_water.ColoredWater.MOD_ID;

public class ModTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public static final Supplier<CreativeModeTab> COLORED_WATER_TAB = CREATIVE_MODE_TABS.register(ColoredWater.MOD_ID, () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.colored_water"))
            .icon(() -> {
                ItemStack stack = new ItemStack(ModItems.COLORED_WATER_BUCKET.get());
                stack.set(DataComponents.DYED_COLOR, new DyedItemColor(DyeColor.PINK.getTextureDiffuseColor()));
                return stack;
            })
            .displayItems((parameters, output) -> fillCreativeTab(output))
            .build());

    public static void fillCreativeTab(CreativeModeTab.Output output) {
        DyeColor[] colors = DyeColor.values();
        Item bucketItem = ModItems.COLORED_WATER_BUCKET.get();

        for (DyeColor color : colors) {
            output.accept(createBucketStack(bucketItem, color.getTextureDiffuseColor(), false, 0));
        }
        for (DyeColor color : colors) {
            output.accept(createBucketStack(bucketItem, color.getTextureDiffuseColor(), true, 0));
        }
        for (DyeColor color : colors) {
            output.accept(createBucketStack(bucketItem, color.getTextureDiffuseColor(), false, 15));
        }
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