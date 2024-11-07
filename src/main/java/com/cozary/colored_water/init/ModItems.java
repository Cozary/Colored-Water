package com.cozary.colored_water.init;


import com.cozary.colored_water.items.FoilBucketItem;
import com.google.common.collect.Sets;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.LinkedHashSet;
import java.util.function.Supplier;

import static com.cozary.colored_water.ColoredWater.MOD_ID;
import static com.cozary.colored_water.init.ModFluids.*;
import static net.minecraft.world.item.Items.BUCKET;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MOD_ID);
    public static LinkedHashSet<DeferredItem<Item>> REGISTERED_ITEMS = Sets.newLinkedHashSet();
    public static final DeferredItem<Item> WHITE_WATER_BUCKET = registerWithSet("white_water_bucket", () -> new BucketItem(STILL_WHITE_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> CONDENSE_WHITE_WATER_BUCKET = registerWithSet("condense_white_water_bucket", () -> new BucketItem(STILL_CONDENSE_WHITE_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> LUMINOUS_WHITE_WATER_BUCKET = registerWithSet("luminous_white_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_WHITE_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> LUMINOUS_CONDENSE_WHITE_WATER_BUCKET = registerWithSet("luminous_condense_white_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_CONDENSE_WHITE_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));

    public static final DeferredItem<Item> ORANGE_WATER_BUCKET = registerWithSet("orange_water_bucket", () -> new BucketItem(STILL_ORANGE_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> CONDENSE_ORANGE_WATER_BUCKET = registerWithSet("condense_orange_water_bucket", () -> new BucketItem(STILL_CONDENSE_ORANGE_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> LUMINOUS_ORANGE_WATER_BUCKET = registerWithSet("luminous_orange_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_ORANGE_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> LUMINOUS_CONDENSE_ORANGE_WATER_BUCKET = registerWithSet("luminous_condense_orange_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_CONDENSE_ORANGE_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));

    public static final DeferredItem<Item> MAGENTA_WATER_BUCKET = registerWithSet("magenta_water_bucket", () -> new BucketItem(STILL_MAGENTA_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> CONDENSE_MAGENTA_WATER_BUCKET = registerWithSet("condense_magenta_water_bucket", () -> new BucketItem(STILL_CONDENSE_MAGENTA_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> LUMINOUS_MAGENTA_WATER_BUCKET = registerWithSet("luminous_magenta_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_MAGENTA_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> LUMINOUS_CONDENSE_MAGENTA_WATER_BUCKET = registerWithSet("luminous_condense_magenta_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_CONDENSE_MAGENTA_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));

    public static final DeferredItem<Item> LIGHT_BLUE_WATER_BUCKET = registerWithSet("light_blue_water_bucket", () -> new BucketItem(STILL_LIGHT_BLUE_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> CONDENSE_LIGHT_BLUE_WATER_BUCKET = registerWithSet("condense_light_blue_water_bucket", () -> new BucketItem(STILL_CONDENSE_LIGHT_BLUE_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> LUMINOUS_LIGHT_BLUE_WATER_BUCKET = registerWithSet("luminous_light_blue_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_LIGHT_BLUE_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_BUCKET = registerWithSet("luminous_condense_light_blue_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_CONDENSE_LIGHT_BLUE_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));

    public static final DeferredItem<Item> YELLOW_WATER_BUCKET = registerWithSet("yellow_water_bucket", () -> new BucketItem(STILL_YELLOW_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> CONDENSE_YELLOW_WATER_BUCKET = registerWithSet("condense_yellow_water_bucket", () -> new BucketItem(STILL_CONDENSE_YELLOW_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> LUMINOUS_YELLOW_WATER_BUCKET = registerWithSet("luminous_yellow_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_YELLOW_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> LUMINOUS_CONDENSE_YELLOW_WATER_BUCKET = registerWithSet("luminous_condense_yellow_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_CONDENSE_YELLOW_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));

    public static final DeferredItem<Item> LIME_WATER_BUCKET = registerWithSet("lime_water_bucket", () -> new BucketItem(STILL_LIME_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> CONDENSE_LIME_WATER_BUCKET = registerWithSet("condense_lime_water_bucket", () -> new BucketItem(STILL_CONDENSE_LIME_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> LUMINOUS_LIME_WATER_BUCKET = registerWithSet("luminous_lime_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_LIME_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> LUMINOUS_CONDENSE_LIME_WATER_BUCKET = registerWithSet("luminous_condense_lime_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_CONDENSE_LIME_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));

    public static final DeferredItem<Item> PINK_WATER_BUCKET = registerWithSet("pink_water_bucket", () -> new BucketItem(STILL_PINK_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> CONDENSE_PINK_WATER_BUCKET = registerWithSet("condense_pink_water_bucket", () -> new BucketItem(STILL_CONDENSE_PINK_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> LUMINOUS_PINK_WATER_BUCKET = registerWithSet("luminous_pink_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_PINK_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> LUMINOUS_CONDENSE_PINK_WATER_BUCKET = registerWithSet("luminous_condense_pink_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_CONDENSE_PINK_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));

    public static final DeferredItem<Item> GRAY_WATER_BUCKET = registerWithSet("gray_water_bucket", () -> new BucketItem(STILL_GRAY_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> CONDENSE_GRAY_WATER_BUCKET = registerWithSet("condense_gray_water_bucket", () -> new BucketItem(STILL_CONDENSE_GRAY_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> LUMINOUS_GRAY_WATER_BUCKET = registerWithSet("luminous_gray_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_GRAY_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> LUMINOUS_CONDENSE_GRAY_WATER_BUCKET = registerWithSet("luminous_condense_gray_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_CONDENSE_GRAY_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));

    public static final DeferredItem<Item> LIGHT_GRAY_WATER_BUCKET = registerWithSet("light_gray_water_bucket", () -> new BucketItem(STILL_LIGHT_GRAY_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> CONDENSE_LIGHT_GRAY_WATER_BUCKET = registerWithSet("condense_light_gray_water_bucket", () -> new BucketItem(STILL_CONDENSE_LIGHT_GRAY_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> LUMINOUS_LIGHT_GRAY_WATER_BUCKET = registerWithSet("luminous_light_gray_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_LIGHT_GRAY_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_BUCKET = registerWithSet("luminous_condense_light_gray_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_CONDENSE_LIGHT_GRAY_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));

    public static final DeferredItem<Item> CYAN_WATER_BUCKET = registerWithSet("cyan_water_bucket", () -> new BucketItem(STILL_CYAN_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> CONDENSE_CYAN_WATER_BUCKET = registerWithSet("condense_cyan_water_bucket", () -> new BucketItem(STILL_CONDENSE_CYAN_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> LUMINOUS_CYAN_WATER_BUCKET = registerWithSet("luminous_cyan_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_CYAN_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> LUMINOUS_CONDENSE_CYAN_WATER_BUCKET = registerWithSet("luminous_condense_cyan_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_CONDENSE_CYAN_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));

    public static final DeferredItem<Item> PURPLE_WATER_BUCKET = registerWithSet("purple_water_bucket", () -> new BucketItem(STILL_PURPLE_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> CONDENSE_PURPLE_WATER_BUCKET = registerWithSet("condense_purple_water_bucket", () -> new BucketItem(STILL_CONDENSE_PURPLE_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> LUMINOUS_PURPLE_WATER_BUCKET = registerWithSet("luminous_purple_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_PURPLE_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> LUMINOUS_CONDENSE_PURPLE_WATER_BUCKET = registerWithSet("luminous_condense_purple_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_CONDENSE_PURPLE_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));

    public static final DeferredItem<Item> BLUE_WATER_BUCKET = registerWithSet("blue_water_bucket", () -> new BucketItem(STILL_BLUE_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> CONDENSE_BLUE_WATER_BUCKET = registerWithSet("condense_blue_water_bucket", () -> new BucketItem(STILL_CONDENSE_BLUE_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> LUMINOUS_BLUE_WATER_BUCKET = registerWithSet("luminous_blue_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_BLUE_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> LUMINOUS_CONDENSE_BLUE_WATER_BUCKET = registerWithSet("luminous_condense_blue_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_CONDENSE_BLUE_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));

    public static final DeferredItem<Item> BROWN_WATER_BUCKET = registerWithSet("brown_water_bucket", () -> new BucketItem(STILL_BROWN_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> CONDENSE_BROWN_WATER_BUCKET = registerWithSet("condense_brown_water_bucket", () -> new BucketItem(STILL_CONDENSE_BROWN_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> LUMINOUS_BROWN_WATER_BUCKET = registerWithSet("luminous_brown_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_BROWN_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> LUMINOUS_CONDENSE_BROWN_WATER_BUCKET = registerWithSet("luminous_condense_brown_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_CONDENSE_BROWN_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));

    public static final DeferredItem<Item> GREEN_WATER_BUCKET = registerWithSet("green_water_bucket", () -> new BucketItem(STILL_GREEN_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> CONDENSE_GREEN_WATER_BUCKET = registerWithSet("condense_green_water_bucket", () -> new BucketItem(STILL_CONDENSE_GREEN_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> LUMINOUS_GREEN_WATER_BUCKET = registerWithSet("luminous_green_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_GREEN_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> LUMINOUS_CONDENSE_GREEN_WATER_BUCKET = registerWithSet("luminous_condense_green_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_CONDENSE_GREEN_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));

    public static final DeferredItem<Item> RED_WATER_BUCKET = registerWithSet("red_water_bucket", () -> new BucketItem(STILL_RED_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> CONDENSE_RED_WATER_BUCKET = registerWithSet("condense_red_water_bucket", () -> new BucketItem(STILL_CONDENSE_RED_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> LUMINOUS_RED_WATER_BUCKET = registerWithSet("luminous_red_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_RED_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> LUMINOUS_CONDENSE_RED_WATER_BUCKET = registerWithSet("luminous_condense_red_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_CONDENSE_RED_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));

    public static final DeferredItem<Item> BLACK_WATER_BUCKET = registerWithSet("black_water_bucket", () -> new BucketItem(STILL_BLACK_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> CONDENSE_BLACK_WATER_BUCKET = registerWithSet("condense_black_water_bucket", () -> new BucketItem(STILL_CONDENSE_BLACK_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> LUMINOUS_BLACK_WATER_BUCKET = registerWithSet("luminous_black_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_BLACK_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final DeferredItem<Item> LUMINOUS_CONDENSE_BLACK_WATER_BUCKET = registerWithSet("luminous_condense_black_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_CONDENSE_BLACK_WATER.get(), new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));

    public static DeferredItem<Item> registerWithSet(final String name, final Supplier<? extends Item> supplier) {
        DeferredItem<Item> item = ITEMS.register(name, supplier);
        REGISTERED_ITEMS.add(item);
        return item;
    }
}
