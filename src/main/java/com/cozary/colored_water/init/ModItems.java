package com.cozary.colored_water.init;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.items.FoilBucketItem;
import com.cozary.colored_water.recipe.ContainerCraftingRecipe;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.stream.Collectors;

import static com.cozary.colored_water.init.ModFluids.*;
import static com.cozary.colored_water.init.ModFluids.STILL_LUMINOUS_CONDENSE_BLACK_WATER;
import static net.minecraft.world.item.Items.BUCKET;


public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ColoredWater.MOD_ID);

    public static final RegistryObject<Item> WHITE_WATER_BUCKET = ITEMS.register("white_water_bucket", () -> new BucketItem(STILL_WHITE_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> CONDENSE_WHITE_WATER_BUCKET = ITEMS.register("condense_white_water_bucket", () -> new BucketItem(STILL_CONDENSE_WHITE_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> LUMINOUS_WHITE_WATER_BUCKET = ITEMS.register("luminous_white_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_WHITE_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> LUMINOUS_CONDENSE_WHITE_WATER_BUCKET = ITEMS.register("luminous_condense_white_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_CONDENSE_WHITE_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> ORANGE_WATER_BUCKET = ITEMS.register("orange_water_bucket", () -> new BucketItem(STILL_ORANGE_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> CONDENSE_ORANGE_WATER_BUCKET = ITEMS.register("condense_orange_water_bucket", () -> new BucketItem(STILL_CONDENSE_ORANGE_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> LUMINOUS_ORANGE_WATER_BUCKET = ITEMS.register("luminous_orange_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_ORANGE_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> LUMINOUS_CONDENSE_ORANGE_WATER_BUCKET = ITEMS.register("luminous_condense_orange_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_CONDENSE_ORANGE_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> MAGENTA_WATER_BUCKET = ITEMS.register("magenta_water_bucket", () -> new BucketItem(STILL_MAGENTA_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> CONDENSE_MAGENTA_WATER_BUCKET = ITEMS.register("condense_magenta_water_bucket", () -> new BucketItem(STILL_CONDENSE_MAGENTA_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> LUMINOUS_MAGENTA_WATER_BUCKET = ITEMS.register("luminous_magenta_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_MAGENTA_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> LUMINOUS_CONDENSE_MAGENTA_WATER_BUCKET = ITEMS.register("luminous_condense_magenta_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_CONDENSE_MAGENTA_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> LIGHT_BLUE_WATER_BUCKET = ITEMS.register("light_blue_water_bucket", () -> new BucketItem(STILL_LIGHT_BLUE_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> CONDENSE_LIGHT_BLUE_WATER_BUCKET = ITEMS.register("condense_light_blue_water_bucket", () -> new BucketItem(STILL_CONDENSE_LIGHT_BLUE_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> LUMINOUS_LIGHT_BLUE_WATER_BUCKET = ITEMS.register("luminous_light_blue_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_LIGHT_BLUE_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_BUCKET = ITEMS.register("luminous_condense_light_blue_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_CONDENSE_LIGHT_BLUE_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> YELLOW_WATER_BUCKET = ITEMS.register("yellow_water_bucket", () -> new BucketItem(STILL_YELLOW_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> CONDENSE_YELLOW_WATER_BUCKET = ITEMS.register("condense_yellow_water_bucket", () -> new BucketItem(STILL_CONDENSE_YELLOW_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> LUMINOUS_YELLOW_WATER_BUCKET = ITEMS.register("luminous_yellow_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_YELLOW_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> LUMINOUS_CONDENSE_YELLOW_WATER_BUCKET = ITEMS.register("luminous_condense_yellow_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_CONDENSE_YELLOW_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> LIME_WATER_BUCKET = ITEMS.register("lime_water_bucket", () -> new BucketItem(STILL_LIME_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> CONDENSE_LIME_WATER_BUCKET = ITEMS.register("condense_lime_water_bucket", () -> new BucketItem(STILL_CONDENSE_LIME_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> LUMINOUS_LIME_WATER_BUCKET = ITEMS.register("luminous_lime_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_LIME_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> LUMINOUS_CONDENSE_LIME_WATER_BUCKET = ITEMS.register("luminous_condense_lime_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_CONDENSE_LIME_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> PINK_WATER_BUCKET = ITEMS.register("pink_water_bucket", () -> new BucketItem(STILL_PINK_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> CONDENSE_PINK_WATER_BUCKET = ITEMS.register("condense_pink_water_bucket", () -> new BucketItem(STILL_CONDENSE_PINK_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> LUMINOUS_PINK_WATER_BUCKET = ITEMS.register("luminous_pink_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_PINK_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> LUMINOUS_CONDENSE_PINK_WATER_BUCKET = ITEMS.register("luminous_condense_pink_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_CONDENSE_PINK_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> GRAY_WATER_BUCKET = ITEMS.register("gray_water_bucket", () -> new BucketItem(STILL_GRAY_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> CONDENSE_GRAY_WATER_BUCKET = ITEMS.register("condense_gray_water_bucket", () -> new BucketItem(STILL_CONDENSE_GRAY_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> LUMINOUS_GRAY_WATER_BUCKET = ITEMS.register("luminous_gray_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_GRAY_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> LUMINOUS_CONDENSE_GRAY_WATER_BUCKET = ITEMS.register("luminous_condense_gray_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_CONDENSE_GRAY_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> LIGHT_GRAY_WATER_BUCKET = ITEMS.register("light_gray_water_bucket", () -> new BucketItem(STILL_LIGHT_GRAY_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> CONDENSE_LIGHT_GRAY_WATER_BUCKET = ITEMS.register("condense_light_gray_water_bucket", () -> new BucketItem(STILL_CONDENSE_LIGHT_GRAY_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> LUMINOUS_LIGHT_GRAY_WATER_BUCKET = ITEMS.register("luminous_light_gray_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_LIGHT_GRAY_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_BUCKET = ITEMS.register("luminous_condense_light_gray_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_CONDENSE_LIGHT_GRAY_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> CYAN_WATER_BUCKET = ITEMS.register("cyan_water_bucket", () -> new BucketItem(STILL_CYAN_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> CONDENSE_CYAN_WATER_BUCKET = ITEMS.register("condense_cyan_water_bucket", () -> new BucketItem(STILL_CONDENSE_CYAN_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> LUMINOUS_CYAN_WATER_BUCKET = ITEMS.register("luminous_cyan_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_CYAN_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> LUMINOUS_CONDENSE_CYAN_WATER_BUCKET = ITEMS.register("luminous_condense_cyan_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_CONDENSE_CYAN_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> PURPLE_WATER_BUCKET = ITEMS.register("purple_water_bucket", () -> new BucketItem(STILL_PURPLE_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> CONDENSE_PURPLE_WATER_BUCKET = ITEMS.register("condense_purple_water_bucket", () -> new BucketItem(STILL_CONDENSE_PURPLE_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> LUMINOUS_PURPLE_WATER_BUCKET = ITEMS.register("luminous_purple_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_PURPLE_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> LUMINOUS_CONDENSE_PURPLE_WATER_BUCKET = ITEMS.register("luminous_condense_purple_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_CONDENSE_PURPLE_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> BLUE_WATER_BUCKET = ITEMS.register("blue_water_bucket", () -> new BucketItem(STILL_BLUE_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> CONDENSE_BLUE_WATER_BUCKET = ITEMS.register("condense_blue_water_bucket", () -> new BucketItem(STILL_CONDENSE_BLUE_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> LUMINOUS_BLUE_WATER_BUCKET = ITEMS.register("luminous_blue_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_BLUE_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> LUMINOUS_CONDENSE_BLUE_WATER_BUCKET = ITEMS.register("luminous_condense_blue_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_CONDENSE_BLUE_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> BROWN_WATER_BUCKET = ITEMS.register("brown_water_bucket", () -> new BucketItem(STILL_BROWN_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> CONDENSE_BROWN_WATER_BUCKET = ITEMS.register("condense_brown_water_bucket", () -> new BucketItem(STILL_CONDENSE_BROWN_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> LUMINOUS_BROWN_WATER_BUCKET = ITEMS.register("luminous_brown_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_BROWN_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> LUMINOUS_CONDENSE_BROWN_WATER_BUCKET = ITEMS.register("luminous_condense_brown_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_CONDENSE_BROWN_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> GREEN_WATER_BUCKET = ITEMS.register("green_water_bucket", () -> new BucketItem(STILL_GREEN_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> CONDENSE_GREEN_WATER_BUCKET = ITEMS.register("condense_green_water_bucket", () -> new BucketItem(STILL_CONDENSE_GREEN_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> LUMINOUS_GREEN_WATER_BUCKET = ITEMS.register("luminous_green_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_GREEN_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> LUMINOUS_CONDENSE_GREEN_WATER_BUCKET = ITEMS.register("luminous_condense_green_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_CONDENSE_GREEN_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> RED_WATER_BUCKET = ITEMS.register("red_water_bucket", () -> new BucketItem(STILL_RED_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> CONDENSE_RED_WATER_BUCKET = ITEMS.register("condense_red_water_bucket", () -> new BucketItem(STILL_CONDENSE_RED_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> LUMINOUS_RED_WATER_BUCKET = ITEMS.register("luminous_red_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_RED_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> LUMINOUS_CONDENSE_RED_WATER_BUCKET = ITEMS.register("luminous_condense_red_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_CONDENSE_RED_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> BLACK_WATER_BUCKET = ITEMS.register("black_water_bucket", () -> new BucketItem(STILL_BLACK_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> CONDENSE_BLACK_WATER_BUCKET = ITEMS.register("condense_black_water_bucket", () -> new BucketItem(STILL_CONDENSE_BLACK_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> LUMINOUS_BLACK_WATER_BUCKET = ITEMS.register("luminous_black_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_BLACK_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> LUMINOUS_CONDENSE_BLACK_WATER_BUCKET = ITEMS.register("luminous_condense_black_water_bucket", () -> new FoilBucketItem(STILL_LUMINOUS_CONDENSE_BLACK_WATER, new Item.Properties().craftRemainder(BUCKET).stacksTo(1)));

    public static List<Item> getItemsRegistered() {
        return ITEMS.getEntries().stream()
                .filter(RegistryObject::isPresent)
                .map(RegistryObject::get)
                .filter(item -> item instanceof Item)
                .collect(Collectors.toList());
    }

}
