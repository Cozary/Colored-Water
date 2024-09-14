package com.cozary.colored_water.items;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.fluids.ModFluids;
import com.cozary.colored_water.fluids.ModFluidsCondense;
import com.cozary.colored_water.fluids.ModFluidsLight;
import com.cozary.colored_water.fluids.ModFluidsLightCondense;
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


public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ColoredWater.MOD_ID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ColoredWater.MOD_ID);

    public static final RegistryObject<RecipeSerializer<ContainerCraftingRecipe>> CONTAINER_CRAFTING_RECIPE = RECIPES.register("container_shapeless_recipe_cw", ContainerCraftingRecipe.Serializer::new);

    //Buckets
    public static final RegistryObject<BucketItem> MAGENTA_WATER_BUCKET = ITEMS.register("magenta_water_bucket", () -> new BucketItem(
            ModFluids.MAGENTA_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> PURPLE_WATER_BUCKET = ITEMS.register("purple_water_bucket", () -> new BucketItem(
            ModFluids.PURPLE_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> GREEN_WATER_BUCKET = ITEMS.register("green_water_bucket", () -> new BucketItem(
            ModFluids.GREEN_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> YELLOW_WATER_BUCKET = ITEMS.register("yellow_water_bucket", () -> new BucketItem(
            ModFluids.YELLOW_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> LIME_WATER_BUCKET = ITEMS.register("lime_water_bucket", () -> new BucketItem(
            ModFluids.LIME_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> PINK_WATER_BUCKET = ITEMS.register("pink_water_bucket", () -> new BucketItem(
            ModFluids.PINK_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> RED_WATER_BUCKET = ITEMS.register("red_water_bucket", () -> new BucketItem(
            ModFluids.RED_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> BLACK_WATER_BUCKET = ITEMS.register("black_water_bucket", () -> new BucketItem(
            ModFluids.BLACK_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> BROWN_WATER_BUCKET = ITEMS.register("brown_water_bucket", () -> new BucketItem(
            ModFluids.BROWN_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> BLUE_WATER_BUCKET = ITEMS.register("blue_water_bucket", () -> new BucketItem(
            ModFluids.BLUE_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> CYAN_WATER_BUCKET = ITEMS.register("cyan_water_bucket", () -> new BucketItem(
            ModFluids.CYAN_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> LIGHT_GRAY_WATER_BUCKET = ITEMS.register("light_gray_water_bucket", () -> new BucketItem(
            ModFluids.LIGHT_GRAY_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> GRAY_WATER_BUCKET = ITEMS.register("gray_water_bucket", () -> new BucketItem(
            ModFluids.GRAY_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> LIGHT_BLUE_WATER_BUCKET = ITEMS.register("light_blue_water_bucket", () -> new BucketItem(
            ModFluids.LIGHT_BLUE_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> ORANGE_WATER_BUCKET = ITEMS.register("orange_water_bucket", () -> new BucketItem(
            ModFluids.ORANGE_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> WHITE_WATER_BUCKET = ITEMS.register("white_water_bucket", () -> new BucketItem(
            ModFluids.WHITE_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));


    //Condense Buckets
    public static final RegistryObject<BucketItem> CONDENSE_MAGENTA_WATER_BUCKET = ITEMS.register("condense_magenta_water_bucket", () -> new BucketItem(
            ModFluidsCondense.CONDENSE_MAGENTA_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> CONDENSE_PURPLE_WATER_BUCKET = ITEMS.register("condense_purple_water_bucket", () -> new BucketItem(
            ModFluidsCondense.CONDENSE_PURPLE_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> CONDENSE_GREEN_WATER_BUCKET = ITEMS.register("condense_green_water_bucket", () -> new BucketItem(
            ModFluidsCondense.CONDENSE_GREEN_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> CONDENSE_YELLOW_WATER_BUCKET = ITEMS.register("condense_yellow_water_bucket", () -> new BucketItem(
            ModFluidsCondense.CONDENSE_YELLOW_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> CONDENSE_LIME_WATER_BUCKET = ITEMS.register("condense_lime_water_bucket", () -> new BucketItem(
            ModFluidsCondense.CONDENSE_LIME_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> CONDENSE_PINK_WATER_BUCKET = ITEMS.register("condense_pink_water_bucket", () -> new BucketItem(
            ModFluidsCondense.CONDENSE_PINK_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> CONDENSE_RED_WATER_BUCKET = ITEMS.register("condense_red_water_bucket", () -> new BucketItem(
            ModFluidsCondense.CONDENSE_RED_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> CONDENSE_BLACK_WATER_BUCKET = ITEMS.register("condense_black_water_bucket", () -> new BucketItem(
            ModFluidsCondense.CONDENSE_BLACK_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> CONDENSE_BROWN_WATER_BUCKET = ITEMS.register("condense_brown_water_bucket", () -> new BucketItem(
            ModFluidsCondense.CONDENSE_BROWN_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> CONDENSE_BLUE_WATER_BUCKET = ITEMS.register("condense_blue_water_bucket", () -> new BucketItem(
            ModFluidsCondense.CONDENSE_BLUE_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> CONDENSE_CYAN_WATER_BUCKET = ITEMS.register("condense_cyan_water_bucket", () -> new BucketItem(
            ModFluidsCondense.CONDENSE_CYAN_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> CONDENSE_LIGHT_GRAY_WATER_BUCKET = ITEMS.register("condense_light_gray_water_bucket", () -> new BucketItem(
            ModFluidsCondense.CONDENSE_LIGHT_GRAY_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> CONDENSE_GRAY_WATER_BUCKET = ITEMS.register("condense_gray_water_bucket", () -> new BucketItem(
            ModFluidsCondense.CONDENSE_GRAY_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> CONDENSE_LIGHT_BLUE_WATER_BUCKET = ITEMS.register("condense_light_blue_water_bucket", () -> new BucketItem(
            ModFluidsCondense.CONDENSE_LIGHT_BLUE_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> CONDENSE_ORANGE_WATER_BUCKET = ITEMS.register("condense_orange_water_bucket", () -> new BucketItem(
            ModFluidsCondense.CONDENSE_ORANGE_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> CONDENSE_WHITE_WATER_BUCKET = ITEMS.register("condense_white_water_bucket", () -> new BucketItem(
            ModFluidsCondense.CONDENSE_WHITE_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));

    //Luminous Bucket
    public static final RegistryObject<BucketItem> LUMINOUS_MAGENTA_WATER_BUCKET = ITEMS.register("luminous_magenta_water_bucket", () -> new FoilBucketItem(
            ModFluidsLight.LUMINOUS_MAGENTA_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> LUMINOUS_PURPLE_WATER_BUCKET = ITEMS.register("luminous_purple_water_bucket", () -> new FoilBucketItem(
            ModFluidsLight.LUMINOUS_PURPLE_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> LUMINOUS_GREEN_WATER_BUCKET = ITEMS.register("luminous_green_water_bucket", () -> new FoilBucketItem(
            ModFluidsLight.LUMINOUS_GREEN_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> LUMINOUS_YELLOW_WATER_BUCKET = ITEMS.register("luminous_yellow_water_bucket", () -> new FoilBucketItem(
            ModFluidsLight.LUMINOUS_YELLOW_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> LUMINOUS_LIME_WATER_BUCKET = ITEMS.register("luminous_lime_water_bucket", () -> new FoilBucketItem(
            ModFluidsLight.LUMINOUS_LIME_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> LUMINOUS_PINK_WATER_BUCKET = ITEMS.register("luminous_pink_water_bucket", () -> new FoilBucketItem(
            ModFluidsLight.LUMINOUS_PINK_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> LUMINOUS_RED_WATER_BUCKET = ITEMS.register("luminous_red_water_bucket", () -> new FoilBucketItem(
            ModFluidsLight.LUMINOUS_RED_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> LUMINOUS_BLACK_WATER_BUCKET = ITEMS.register("luminous_black_water_bucket", () -> new FoilBucketItem(
            ModFluidsLight.LUMINOUS_BLACK_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> LUMINOUS_BROWN_WATER_BUCKET = ITEMS.register("luminous_brown_water_bucket", () -> new FoilBucketItem(
            ModFluidsLight.LUMINOUS_BROWN_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> LUMINOUS_BLUE_WATER_BUCKET = ITEMS.register("luminous_blue_water_bucket", () -> new FoilBucketItem(
            ModFluidsLight.LUMINOUS_BLUE_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> LUMINOUS_CYAN_WATER_BUCKET = ITEMS.register("luminous_cyan_water_bucket", () -> new FoilBucketItem(
            ModFluidsLight.LUMINOUS_CYAN_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> LUMINOUS_LIGHT_GRAY_WATER_BUCKET = ITEMS.register("luminous_light_gray_water_bucket", () -> new FoilBucketItem(
            ModFluidsLight.LUMINOUS_LIGHT_GRAY_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> LUMINOUS_GRAY_WATER_BUCKET = ITEMS.register("luminous_gray_water_bucket", () -> new FoilBucketItem(
            ModFluidsLight.LUMINOUS_GRAY_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> LUMINOUS_LIGHT_BLUE_WATER_BUCKET = ITEMS.register("luminous_light_blue_water_bucket", () -> new FoilBucketItem(
            ModFluidsLight.LUMINOUS_LIGHT_BLUE_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> LUMINOUS_ORANGE_WATER_BUCKET = ITEMS.register("luminous_orange_water_bucket", () -> new FoilBucketItem(
            ModFluidsLight.LUMINOUS_ORANGE_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> LUMINOUS_WHITE_WATER_BUCKET = ITEMS.register("luminous_white_water_bucket", () -> new FoilBucketItem(
            ModFluidsLight.LUMINOUS_WHITE_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));


    //Light Condense Buckets
    public static final RegistryObject<BucketItem> LUMINOUS_CONDENSE_MAGENTA_WATER_BUCKET = ITEMS.register("luminous_condense_magenta_water_bucket", () -> new FoilBucketItem(
            ModFluidsLightCondense.LUMINOUS_CONDENSE_MAGENTA_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> LUMINOUS_CONDENSE_PURPLE_WATER_BUCKET = ITEMS.register("luminous_condense_purple_water_bucket", () -> new FoilBucketItem(
            ModFluidsLightCondense.LUMINOUS_CONDENSE_PURPLE_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> LUMINOUS_CONDENSE_GREEN_WATER_BUCKET = ITEMS.register("luminous_condense_green_water_bucket", () -> new FoilBucketItem(
            ModFluidsLightCondense.LUMINOUS_CONDENSE_GREEN_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> LUMINOUS_CONDENSE_YELLOW_WATER_BUCKET = ITEMS.register("luminous_condense_yellow_water_bucket", () -> new FoilBucketItem(
            ModFluidsLightCondense.LUMINOUS_CONDENSE_YELLOW_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> LUMINOUS_CONDENSE_LIME_WATER_BUCKET = ITEMS.register("luminous_condense_lime_water_bucket", () -> new FoilBucketItem(
            ModFluidsLightCondense.LUMINOUS_CONDENSE_LIME_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> LUMINOUS_CONDENSE_PINK_WATER_BUCKET = ITEMS.register("luminous_condense_pink_water_bucket", () -> new FoilBucketItem(
            ModFluidsLightCondense.LUMINOUS_CONDENSE_PINK_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> LUMINOUS_CONDENSE_RED_WATER_BUCKET = ITEMS.register("luminous_condense_red_water_bucket", () -> new FoilBucketItem(
            ModFluidsLightCondense.LUMINOUS_CONDENSE_RED_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> LUMINOUS_CONDENSE_BLACK_WATER_BUCKET = ITEMS.register("luminous_condense_black_water_bucket", () -> new FoilBucketItem(
            ModFluidsLightCondense.LUMINOUS_CONDENSE_BLACK_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> LUMINOUS_CONDENSE_BROWN_WATER_BUCKET = ITEMS.register("luminous_condense_brown_water_bucket", () -> new FoilBucketItem(
            ModFluidsLightCondense.LUMINOUS_CONDENSE_BROWN_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> LUMINOUS_CONDENSE_BLUE_WATER_BUCKET = ITEMS.register("luminous_condense_blue_water_bucket", () -> new FoilBucketItem(
            ModFluidsLightCondense.LUMINOUS_CONDENSE_BLUE_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> LUMINOUS_CONDENSE_CYAN_WATER_BUCKET = ITEMS.register("luminous_condense_cyan_water_bucket", () -> new FoilBucketItem(
            ModFluidsLightCondense.LUMINOUS_CONDENSE_CYAN_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_BUCKET = ITEMS.register("luminous_condense_light_gray_water_bucket", () -> new FoilBucketItem(
            ModFluidsLightCondense.LUMINOUS_CONDENSE_LIGHT_GRAY_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> LUMINOUS_CONDENSE_GRAY_WATER_BUCKET = ITEMS.register("luminous_condense_gray_water_bucket", () -> new FoilBucketItem(
            ModFluidsLightCondense.LUMINOUS_CONDENSE_GRAY_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_BUCKET = ITEMS.register("luminous_condense_light_blue_water_bucket", () -> new FoilBucketItem(
            ModFluidsLightCondense.LUMINOUS_CONDENSE_LIGHT_BLUE_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> LUMINOUS_CONDENSE_ORANGE_WATER_BUCKET = ITEMS.register("luminous_condense_orange_water_bucket", () -> new FoilBucketItem(
            ModFluidsLightCondense.LUMINOUS_CONDENSE_ORANGE_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final RegistryObject<BucketItem> LUMINOUS_CONDENSE_WHITE_WATER_BUCKET = ITEMS.register("luminous_condense_white_water_bucket", () -> new FoilBucketItem(
            ModFluidsLightCondense.LUMINOUS_CONDENSE_WHITE_FLUID, (new Item.Properties()).stacksTo(1).craftRemainder(Items.BUCKET)));

    public static List<Item> getItemsRegistered() {
        return ITEMS.getEntries().stream()
                .filter(RegistryObject::isPresent)
                .map(RegistryObject::get)
                .filter(item -> item instanceof Item)
                .collect(Collectors.toList());
    }

}
