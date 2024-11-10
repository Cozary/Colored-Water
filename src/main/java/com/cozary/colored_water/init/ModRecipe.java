package com.cozary.colored_water.init;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.recipe.ContainerCraftingRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipe {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ColoredWater.MOD_ID);

    public static final RegistryObject<RecipeSerializer<ContainerCraftingRecipe>> CONTAINER_CRAFTING_RECIPE = RECIPES.register("container_shapeless_recipe_cw", ContainerCraftingRecipe.Serializer::new);

}
