package com.cozary.colored_water.init;

import com.cozary.colored_water.ColoredWater;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModTabs {

    private static final ResourceKey<CreativeModeTab> ITEM_GROUP = ResourceKey.create(Registries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(ColoredWater.MOD_ID, ColoredWater.MOD_ID + "_tab"));

    public static void loadClass() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ITEM_GROUP, FabricItemGroup.builder()
                .title(Component.translatable("itemGroup.colored_water"))
                .icon(() -> new ItemStack(ModBlocks.PINK_WATER_BLOCK.get()))
                .displayItems((parameters, output) -> ModItems.REGISTERED_ITEMS.forEach((item) -> output.accept(item.get())))
                .build()
        );
    }
}
