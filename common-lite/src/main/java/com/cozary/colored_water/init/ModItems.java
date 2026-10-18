package com.cozary.colored_water.init;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.item.ColorWaterBucketItem;
import com.google.common.collect.Sets;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;

import java.util.LinkedHashSet;
import java.util.function.Supplier;

import static com.cozary.colored_water.init.ModFluids.*;
import static net.minecraft.world.item.Items.BUCKET;

public class ModItems {

    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(Registries.ITEM, ColoredWater.MOD_ID);

    public static LinkedHashSet<Supplier<Item>> REGISTERED_ITEMS = Sets.newLinkedHashSet();

    private static Supplier<Item> registerBucket(String name, Supplier<? extends Fluid> fluidSupplier) {
        return registerWithSet(name, () -> new ColorWaterBucketItem(fluidSupplier, new Item.Properties().craftRemainder(BUCKET).stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(ColoredWater.MOD_ID, name)))));
    }

    public static final Supplier<Item> WHITE_WATER_BUCKET = registerBucket("white_water_bucket", STILL_WHITE_WATER);
    public static final Supplier<Item> CONDENSE_WHITE_WATER_BUCKET = registerBucket("condense_white_water_bucket", STILL_CONDENSE_WHITE_WATER);
    public static final Supplier<Item> LUMINOUS_WHITE_WATER_BUCKET = registerBucket("luminous_white_water_bucket", STILL_LUMINOUS_WHITE_WATER);
    public static final Supplier<Item> LUMINOUS_CONDENSE_WHITE_WATER_BUCKET = registerBucket("luminous_condense_white_water_bucket", STILL_LUMINOUS_CONDENSE_WHITE_WATER);

    public static final Supplier<Item> ORANGE_WATER_BUCKET = registerBucket("orange_water_bucket", STILL_ORANGE_WATER);
    public static final Supplier<Item> CONDENSE_ORANGE_WATER_BUCKET = registerBucket("condense_orange_water_bucket", STILL_CONDENSE_ORANGE_WATER);
    public static final Supplier<Item> LUMINOUS_ORANGE_WATER_BUCKET = registerBucket("luminous_orange_water_bucket", STILL_LUMINOUS_ORANGE_WATER);
    public static final Supplier<Item> LUMINOUS_CONDENSE_ORANGE_WATER_BUCKET = registerBucket("luminous_condense_orange_water_bucket", STILL_LUMINOUS_CONDENSE_ORANGE_WATER);

    public static final Supplier<Item> MAGENTA_WATER_BUCKET = registerBucket("magenta_water_bucket", STILL_MAGENTA_WATER);
    public static final Supplier<Item> CONDENSE_MAGENTA_WATER_BUCKET = registerBucket("condense_magenta_water_bucket", STILL_CONDENSE_MAGENTA_WATER);
    public static final Supplier<Item> LUMINOUS_MAGENTA_WATER_BUCKET = registerBucket("luminous_magenta_water_bucket", STILL_LUMINOUS_MAGENTA_WATER);
    public static final Supplier<Item> LUMINOUS_CONDENSE_MAGENTA_WATER_BUCKET = registerBucket("luminous_condense_magenta_water_bucket", STILL_LUMINOUS_CONDENSE_MAGENTA_WATER);

    public static final Supplier<Item> LIGHT_BLUE_WATER_BUCKET = registerBucket("light_blue_water_bucket", STILL_LIGHT_BLUE_WATER);
    public static final Supplier<Item> CONDENSE_LIGHT_BLUE_WATER_BUCKET = registerBucket("condense_light_blue_water_bucket", STILL_CONDENSE_LIGHT_BLUE_WATER);
    public static final Supplier<Item> LUMINOUS_LIGHT_BLUE_WATER_BUCKET = registerBucket("luminous_light_blue_water_bucket", STILL_LUMINOUS_LIGHT_BLUE_WATER);
    public static final Supplier<Item> LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_BUCKET = registerBucket("luminous_condense_light_blue_water_bucket", STILL_LUMINOUS_CONDENSE_LIGHT_BLUE_WATER);

    public static final Supplier<Item> YELLOW_WATER_BUCKET = registerBucket("yellow_water_bucket", STILL_YELLOW_WATER);
    public static final Supplier<Item> CONDENSE_YELLOW_WATER_BUCKET = registerBucket("condense_yellow_water_bucket", STILL_CONDENSE_YELLOW_WATER);
    public static final Supplier<Item> LUMINOUS_YELLOW_WATER_BUCKET = registerBucket("luminous_yellow_water_bucket", STILL_LUMINOUS_YELLOW_WATER);
    public static final Supplier<Item> LUMINOUS_CONDENSE_YELLOW_WATER_BUCKET = registerBucket("luminous_condense_yellow_water_bucket", STILL_LUMINOUS_CONDENSE_YELLOW_WATER);

    public static final Supplier<Item> LIME_WATER_BUCKET = registerBucket("lime_water_bucket", STILL_LIME_WATER);
    public static final Supplier<Item> CONDENSE_LIME_WATER_BUCKET = registerBucket("condense_lime_water_bucket", STILL_CONDENSE_LIME_WATER);
    public static final Supplier<Item> LUMINOUS_LIME_WATER_BUCKET = registerBucket("luminous_lime_water_bucket", STILL_LUMINOUS_LIME_WATER);
    public static final Supplier<Item> LUMINOUS_CONDENSE_LIME_WATER_BUCKET = registerBucket("luminous_condense_lime_water_bucket", STILL_LUMINOUS_CONDENSE_LIME_WATER);

    public static final Supplier<Item> PINK_WATER_BUCKET = registerBucket("pink_water_bucket", STILL_PINK_WATER);
    public static final Supplier<Item> CONDENSE_PINK_WATER_BUCKET = registerBucket("condense_pink_water_bucket", STILL_CONDENSE_PINK_WATER);
    public static final Supplier<Item> LUMINOUS_PINK_WATER_BUCKET = registerBucket("luminous_pink_water_bucket", STILL_LUMINOUS_PINK_WATER);
    public static final Supplier<Item> LUMINOUS_CONDENSE_PINK_WATER_BUCKET = registerBucket("luminous_condense_pink_water_bucket", STILL_LUMINOUS_CONDENSE_PINK_WATER);

    public static final Supplier<Item> GRAY_WATER_BUCKET = registerBucket("gray_water_bucket", STILL_GRAY_WATER);
    public static final Supplier<Item> CONDENSE_GRAY_WATER_BUCKET = registerBucket("condense_gray_water_bucket", STILL_CONDENSE_GRAY_WATER);
    public static final Supplier<Item> LUMINOUS_GRAY_WATER_BUCKET = registerBucket("luminous_gray_water_bucket", STILL_LUMINOUS_GRAY_WATER);
    public static final Supplier<Item> LUMINOUS_CONDENSE_GRAY_WATER_BUCKET = registerBucket("luminous_condense_gray_water_bucket", STILL_LUMINOUS_CONDENSE_GRAY_WATER);

    public static final Supplier<Item> LIGHT_GRAY_WATER_BUCKET = registerBucket("light_gray_water_bucket", STILL_LIGHT_GRAY_WATER);
    public static final Supplier<Item> CONDENSE_LIGHT_GRAY_WATER_BUCKET = registerBucket("condense_light_gray_water_bucket", STILL_CONDENSE_LIGHT_GRAY_WATER);
    public static final Supplier<Item> LUMINOUS_LIGHT_GRAY_WATER_BUCKET = registerBucket("luminous_light_gray_water_bucket", STILL_LUMINOUS_LIGHT_GRAY_WATER);
    public static final Supplier<Item> LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_BUCKET = registerBucket("luminous_condense_light_gray_water_bucket", STILL_LUMINOUS_CONDENSE_LIGHT_GRAY_WATER);

    public static final Supplier<Item> CYAN_WATER_BUCKET = registerBucket("cyan_water_bucket", STILL_CYAN_WATER);
    public static final Supplier<Item> CONDENSE_CYAN_WATER_BUCKET = registerBucket("condense_cyan_water_bucket", STILL_CONDENSE_CYAN_WATER);
    public static final Supplier<Item> LUMINOUS_CYAN_WATER_BUCKET = registerBucket("luminous_cyan_water_bucket", STILL_LUMINOUS_CYAN_WATER);
    public static final Supplier<Item> LUMINOUS_CONDENSE_CYAN_WATER_BUCKET = registerBucket("luminous_condense_cyan_water_bucket", STILL_LUMINOUS_CONDENSE_CYAN_WATER);

    public static final Supplier<Item> PURPLE_WATER_BUCKET = registerBucket("purple_water_bucket", STILL_PURPLE_WATER);
    public static final Supplier<Item> CONDENSE_PURPLE_WATER_BUCKET = registerBucket("condense_purple_water_bucket", STILL_CONDENSE_PURPLE_WATER);
    public static final Supplier<Item> LUMINOUS_PURPLE_WATER_BUCKET = registerBucket("luminous_purple_water_bucket", STILL_LUMINOUS_PURPLE_WATER);
    public static final Supplier<Item> LUMINOUS_CONDENSE_PURPLE_WATER_BUCKET = registerBucket("luminous_condense_purple_water_bucket", STILL_LUMINOUS_CONDENSE_PURPLE_WATER);

    public static final Supplier<Item> BLUE_WATER_BUCKET = registerBucket("blue_water_bucket", STILL_BLUE_WATER);
    public static final Supplier<Item> CONDENSE_BLUE_WATER_BUCKET = registerBucket("condense_blue_water_bucket", STILL_CONDENSE_BLUE_WATER);
    public static final Supplier<Item> LUMINOUS_BLUE_WATER_BUCKET = registerBucket("luminous_blue_water_bucket", STILL_LUMINOUS_BLUE_WATER);
    public static final Supplier<Item> LUMINOUS_CONDENSE_BLUE_WATER_BUCKET = registerBucket("luminous_condense_blue_water_bucket", STILL_LUMINOUS_CONDENSE_BLUE_WATER);

    public static final Supplier<Item> BROWN_WATER_BUCKET = registerBucket("brown_water_bucket", STILL_BROWN_WATER);
    public static final Supplier<Item> CONDENSE_BROWN_WATER_BUCKET = registerBucket("condense_brown_water_bucket", STILL_CONDENSE_BROWN_WATER);
    public static final Supplier<Item> LUMINOUS_BROWN_WATER_BUCKET = registerBucket("luminous_brown_water_bucket", STILL_LUMINOUS_BROWN_WATER);
    public static final Supplier<Item> LUMINOUS_CONDENSE_BROWN_WATER_BUCKET = registerBucket("luminous_condense_brown_water_bucket", STILL_LUMINOUS_CONDENSE_BROWN_WATER);

    public static final Supplier<Item> GREEN_WATER_BUCKET = registerBucket("green_water_bucket", STILL_GREEN_WATER);
    public static final Supplier<Item> CONDENSE_GREEN_WATER_BUCKET = registerBucket("condense_green_water_bucket", STILL_CONDENSE_GREEN_WATER);
    public static final Supplier<Item> LUMINOUS_GREEN_WATER_BUCKET = registerBucket("luminous_green_water_bucket", STILL_LUMINOUS_GREEN_WATER);
    public static final Supplier<Item> LUMINOUS_CONDENSE_GREEN_WATER_BUCKET = registerBucket("luminous_condense_green_water_bucket", STILL_LUMINOUS_CONDENSE_GREEN_WATER);

    public static final Supplier<Item> RED_WATER_BUCKET = registerBucket("red_water_bucket", STILL_RED_WATER);
    public static final Supplier<Item> CONDENSE_RED_WATER_BUCKET = registerBucket("condense_red_water_bucket", STILL_CONDENSE_RED_WATER);
    public static final Supplier<Item> LUMINOUS_RED_WATER_BUCKET = registerBucket("luminous_red_water_bucket", STILL_LUMINOUS_RED_WATER);
    public static final Supplier<Item> LUMINOUS_CONDENSE_RED_WATER_BUCKET = registerBucket("luminous_condense_red_water_bucket", STILL_LUMINOUS_CONDENSE_RED_WATER);

    public static final Supplier<Item> BLACK_WATER_BUCKET = registerBucket("black_water_bucket", STILL_BLACK_WATER);
    public static final Supplier<Item> CONDENSE_BLACK_WATER_BUCKET = registerBucket("condense_black_water_bucket", STILL_CONDENSE_BLACK_WATER);
    public static final Supplier<Item> LUMINOUS_BLACK_WATER_BUCKET = registerBucket("luminous_black_water_bucket", STILL_LUMINOUS_BLACK_WATER);
    public static final Supplier<Item> LUMINOUS_CONDENSE_BLACK_WATER_BUCKET = registerBucket("luminous_condense_black_water_bucket", STILL_LUMINOUS_CONDENSE_BLACK_WATER);

    public static Supplier<Item> registerWithSet(final String name, final Supplier<? extends Item> supplier) {
        Supplier<Item> item = ITEMS.register(name, supplier);
        REGISTERED_ITEMS.add(item);
        return item;
    }

    public static void loadClass() {
    }

}
