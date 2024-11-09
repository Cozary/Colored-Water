package com.cozary.colored_water.init;


import com.cozary.colored_water.items.FoilBucketItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

import static com.cozary.colored_water.init.ModFluids.*;

public class ModItems {

    public static final List<Item> REGISTERED_ITEMS = new ArrayList<>();

    public static final Item WHITE_WATER_BUCKET = registerItem(Identifier.of("colored_water", "white_water_bucket"), new BucketItem(STILL_WHITE_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item CONDENSE_WHITE_WATER_BUCKET = registerItem(Identifier.of("colored_water", "condense_white_water_bucket"), new BucketItem(STILL_CONDENSE_WHITE_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item LUMINOUS_WHITE_WATER_BUCKET = registerItem(Identifier.of("colored_water", "luminous_white_water_bucket"), new FoilBucketItem(STILL_LUMINOUS_WHITE_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item LUMINOUS_CONDENSE_WHITE_WATER_BUCKET = registerItem(Identifier.of("colored_water", "luminous_condense_white_water_bucket"), new FoilBucketItem(STILL_LUMINOUS_CONDENSE_WHITE_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));

    public static final Item ORANGE_WATER_BUCKET = registerItem(Identifier.of("colored_water", "orange_water_bucket"), new BucketItem(STILL_ORANGE_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item CONDENSE_ORANGE_WATER_BUCKET = registerItem(Identifier.of("colored_water", "condense_orange_water_bucket"), new BucketItem(STILL_CONDENSE_ORANGE_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item LUMINOUS_ORANGE_WATER_BUCKET = registerItem(Identifier.of("colored_water", "luminous_orange_water_bucket"), new FoilBucketItem(STILL_LUMINOUS_ORANGE_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item LUMINOUS_CONDENSE_ORANGE_WATER_BUCKET = registerItem(Identifier.of("colored_water", "luminous_condense_orange_water_bucket"), new FoilBucketItem(STILL_LUMINOUS_CONDENSE_ORANGE_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));

    public static final Item MAGENTA_WATER_BUCKET = registerItem(Identifier.of("colored_water", "magenta_water_bucket"), new BucketItem(STILL_MAGENTA_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item CONDENSE_MAGENTA_WATER_BUCKET = registerItem(Identifier.of("colored_water", "condense_magenta_water_bucket"), new BucketItem(STILL_CONDENSE_MAGENTA_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item LUMINOUS_MAGENTA_WATER_BUCKET = registerItem(Identifier.of("colored_water", "luminous_magenta_water_bucket"), new FoilBucketItem(STILL_LUMINOUS_MAGENTA_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item LUMINOUS_CONDENSE_MAGENTA_WATER_BUCKET = registerItem(Identifier.of("colored_water", "luminous_condense_magenta_water_bucket"), new FoilBucketItem(STILL_LUMINOUS_CONDENSE_MAGENTA_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));

    public static final Item LIGHT_BLUE_WATER_BUCKET = registerItem(Identifier.of("colored_water", "light_blue_water_bucket"), new BucketItem(STILL_LIGHT_BLUE_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item CONDENSE_LIGHT_BLUE_WATER_BUCKET = registerItem(Identifier.of("colored_water", "condense_light_blue_water_bucket"), new BucketItem(STILL_CONDENSE_LIGHT_BLUE_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item LUMINOUS_LIGHT_BLUE_WATER_BUCKET = registerItem(Identifier.of("colored_water", "luminous_light_blue_water_bucket"), new FoilBucketItem(STILL_LUMINOUS_LIGHT_BLUE_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_BUCKET = registerItem(Identifier.of("colored_water", "luminous_condense_light_blue_water_bucket"), new FoilBucketItem(STILL_LUMINOUS_CONDENSE_LIGHT_BLUE_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));

    public static final Item YELLOW_WATER_BUCKET = registerItem(Identifier.of("colored_water", "yellow_water_bucket"), new BucketItem(STILL_YELLOW_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item CONDENSE_YELLOW_WATER_BUCKET = registerItem(Identifier.of("colored_water", "condense_yellow_water_bucket"), new BucketItem(STILL_CONDENSE_YELLOW_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item LUMINOUS_YELLOW_WATER_BUCKET = registerItem(Identifier.of("colored_water", "luminous_yellow_water_bucket"), new FoilBucketItem(STILL_LUMINOUS_YELLOW_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item LUMINOUS_CONDENSE_YELLOW_WATER_BUCKET = registerItem(Identifier.of("colored_water", "luminous_condense_yellow_water_bucket"), new FoilBucketItem(STILL_LUMINOUS_CONDENSE_YELLOW_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));

    public static final Item LIME_WATER_BUCKET = registerItem(Identifier.of("colored_water", "lime_water_bucket"), new BucketItem(STILL_LIME_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item CONDENSE_LIME_WATER_BUCKET = registerItem(Identifier.of("colored_water", "condense_lime_water_bucket"), new BucketItem(STILL_CONDENSE_LIME_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item LUMINOUS_LIME_WATER_BUCKET = registerItem(Identifier.of("colored_water", "luminous_lime_water_bucket"), new FoilBucketItem(STILL_LUMINOUS_LIME_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item LUMINOUS_CONDENSE_LIME_WATER_BUCKET = registerItem(Identifier.of("colored_water", "luminous_condense_lime_water_bucket"), new FoilBucketItem(STILL_LUMINOUS_CONDENSE_LIME_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));

    public static final Item PINK_WATER_BUCKET = registerItem(Identifier.of("colored_water", "pink_water_bucket"), new BucketItem(STILL_PINK_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item CONDENSE_PINK_WATER_BUCKET = registerItem(Identifier.of("colored_water", "condense_pink_water_bucket"), new BucketItem(STILL_CONDENSE_PINK_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item LUMINOUS_PINK_WATER_BUCKET = registerItem(Identifier.of("colored_water", "luminous_pink_water_bucket"), new FoilBucketItem(STILL_LUMINOUS_PINK_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item LUMINOUS_CONDENSE_PINK_WATER_BUCKET = registerItem(Identifier.of("colored_water", "luminous_condense_pink_water_bucket"), new FoilBucketItem(STILL_LUMINOUS_CONDENSE_PINK_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));

    public static final Item GRAY_WATER_BUCKET = registerItem(Identifier.of("colored_water", "gray_water_bucket"), new BucketItem(STILL_GRAY_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item CONDENSE_GRAY_WATER_BUCKET = registerItem(Identifier.of("colored_water", "condense_gray_water_bucket"), new BucketItem(STILL_CONDENSE_GRAY_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item LUMINOUS_GRAY_WATER_BUCKET = registerItem(Identifier.of("colored_water", "luminous_gray_water_bucket"), new FoilBucketItem(STILL_LUMINOUS_GRAY_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item LUMINOUS_CONDENSE_GRAY_WATER_BUCKET = registerItem(Identifier.of("colored_water", "luminous_condense_gray_water_bucket"), new FoilBucketItem(STILL_LUMINOUS_CONDENSE_GRAY_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));

    public static final Item LIGHT_GRAY_WATER_BUCKET = registerItem(Identifier.of("colored_water", "light_gray_water_bucket"), new BucketItem(STILL_LIGHT_GRAY_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item CONDENSE_LIGHT_GRAY_WATER_BUCKET = registerItem(Identifier.of("colored_water", "condense_light_gray_water_bucket"), new BucketItem(STILL_CONDENSE_LIGHT_GRAY_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item LUMINOUS_LIGHT_GRAY_WATER_BUCKET = registerItem(Identifier.of("colored_water", "luminous_light_gray_water_bucket"), new FoilBucketItem(STILL_LUMINOUS_LIGHT_GRAY_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_BUCKET = registerItem(Identifier.of("colored_water", "luminous_condense_light_gray_water_bucket"), new FoilBucketItem(STILL_LUMINOUS_CONDENSE_LIGHT_GRAY_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));

    public static final Item CYAN_WATER_BUCKET = registerItem(Identifier.of("colored_water", "cyan_water_bucket"), new BucketItem(STILL_CYAN_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item CONDENSE_CYAN_WATER_BUCKET = registerItem(Identifier.of("colored_water", "condense_cyan_water_bucket"), new BucketItem(STILL_CONDENSE_CYAN_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item LUMINOUS_CYAN_WATER_BUCKET = registerItem(Identifier.of("colored_water", "luminous_cyan_water_bucket"), new FoilBucketItem(STILL_LUMINOUS_CYAN_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item LUMINOUS_CONDENSE_CYAN_WATER_BUCKET = registerItem(Identifier.of("colored_water", "luminous_condense_cyan_water_bucket"), new FoilBucketItem(STILL_LUMINOUS_CONDENSE_CYAN_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));

    public static final Item PURPLE_WATER_BUCKET = registerItem(Identifier.of("colored_water", "purple_water_bucket"), new BucketItem(STILL_PURPLE_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item CONDENSE_PURPLE_WATER_BUCKET = registerItem(Identifier.of("colored_water", "condense_purple_water_bucket"), new BucketItem(STILL_CONDENSE_PURPLE_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item LUMINOUS_PURPLE_WATER_BUCKET = registerItem(Identifier.of("colored_water", "luminous_purple_water_bucket"), new FoilBucketItem(STILL_LUMINOUS_PURPLE_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item LUMINOUS_CONDENSE_PURPLE_WATER_BUCKET = registerItem(Identifier.of("colored_water", "luminous_condense_purple_water_bucket"), new FoilBucketItem(STILL_LUMINOUS_CONDENSE_PURPLE_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));

    public static final Item BLUE_WATER_BUCKET = registerItem(Identifier.of("colored_water", "blue_water_bucket"), new BucketItem(STILL_BLUE_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item CONDENSE_BLUE_WATER_BUCKET = registerItem(Identifier.of("colored_water", "condense_blue_water_bucket"), new BucketItem(STILL_CONDENSE_BLUE_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item LUMINOUS_BLUE_WATER_BUCKET = registerItem(Identifier.of("colored_water", "luminous_blue_water_bucket"), new FoilBucketItem(STILL_LUMINOUS_BLUE_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item LUMINOUS_CONDENSE_BLUE_WATER_BUCKET = registerItem(Identifier.of("colored_water", "luminous_condense_blue_water_bucket"), new FoilBucketItem(STILL_LUMINOUS_CONDENSE_BLUE_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));

    public static final Item BROWN_WATER_BUCKET = registerItem(Identifier.of("colored_water", "brown_water_bucket"), new BucketItem(STILL_BROWN_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item CONDENSE_BROWN_WATER_BUCKET = registerItem(Identifier.of("colored_water", "condense_brown_water_bucket"), new BucketItem(STILL_CONDENSE_BROWN_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item LUMINOUS_BROWN_WATER_BUCKET = registerItem(Identifier.of("colored_water", "luminous_brown_water_bucket"), new FoilBucketItem(STILL_LUMINOUS_BROWN_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item LUMINOUS_CONDENSE_BROWN_WATER_BUCKET = registerItem(Identifier.of("colored_water", "luminous_condense_brown_water_bucket"), new FoilBucketItem(STILL_LUMINOUS_CONDENSE_BROWN_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));

    public static final Item GREEN_WATER_BUCKET = registerItem(Identifier.of("colored_water", "green_water_bucket"), new BucketItem(STILL_GREEN_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item CONDENSE_GREEN_WATER_BUCKET = registerItem(Identifier.of("colored_water", "condense_green_water_bucket"), new BucketItem(STILL_CONDENSE_GREEN_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item LUMINOUS_GREEN_WATER_BUCKET = registerItem(Identifier.of("colored_water", "luminous_green_water_bucket"), new FoilBucketItem(STILL_LUMINOUS_GREEN_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item LUMINOUS_CONDENSE_GREEN_WATER_BUCKET = registerItem(Identifier.of("colored_water", "luminous_condense_green_water_bucket"), new FoilBucketItem(STILL_LUMINOUS_CONDENSE_GREEN_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));

    public static final Item RED_WATER_BUCKET = registerItem(Identifier.of("colored_water", "red_water_bucket"), new BucketItem(STILL_RED_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item CONDENSE_RED_WATER_BUCKET = registerItem(Identifier.of("colored_water", "condense_red_water_bucket"), new BucketItem(STILL_CONDENSE_RED_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item LUMINOUS_RED_WATER_BUCKET = registerItem(Identifier.of("colored_water", "luminous_red_water_bucket"), new FoilBucketItem(STILL_LUMINOUS_RED_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item LUMINOUS_CONDENSE_RED_WATER_BUCKET = registerItem(Identifier.of("colored_water", "luminous_condense_red_water_bucket"), new FoilBucketItem(STILL_LUMINOUS_CONDENSE_RED_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));

    public static final Item BLACK_WATER_BUCKET = registerItem(Identifier.of("colored_water", "black_water_bucket"), new BucketItem(STILL_BLACK_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item CONDENSE_BLACK_WATER_BUCKET = registerItem(Identifier.of("colored_water", "condense_black_water_bucket"), new BucketItem(STILL_CONDENSE_BLACK_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item LUMINOUS_BLACK_WATER_BUCKET = registerItem(Identifier.of("colored_water", "luminous_black_water_bucket"), new FoilBucketItem(STILL_LUMINOUS_BLACK_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    public static final Item LUMINOUS_CONDENSE_BLACK_WATER_BUCKET = registerItem(Identifier.of("colored_water", "luminous_condense_black_water_bucket"), new FoilBucketItem(STILL_LUMINOUS_CONDENSE_BLACK_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));

    public static void loadClass() {
    }


    private static Item registerItem(Identifier id, Item item) {
        Registry.register(Registries.ITEM, id, item);
        REGISTERED_ITEMS.add(item);
        return item;
    }

}
