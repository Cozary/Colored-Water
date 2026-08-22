package com.cozary.colored_water.init;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.item.ColoredWaterBucketItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.DyedItemColor;

import java.util.function.Supplier;

import static com.cozary.colored_water.init.ModFluids.STILL_COLORED_WATER;

public class ModItems {

    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(Registries.ITEM, ColoredWater.MOD_ID);

    public static final Supplier<Item> COLORED_WATER_BUCKET = ITEMS.register("colored_water_bucket",
            () -> new ColoredWaterBucketItem(STILL_COLORED_WATER, new Item.Properties().stacksTo(1)
                    .component(DataComponents.DYED_COLOR, new DyedItemColor(0x3F76E4))
                    .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(ColoredWater.MOD_ID, "colored_water_bucket")))));

    public static void loadClass() {
    }
}
