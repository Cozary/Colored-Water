package com.cozary.colored_water.init;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.recipe.ContainerCraftingRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModRecipe {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(Registries.RECIPE_SERIALIZER, ColoredWater.MOD_ID);

    public static final Supplier<RecipeSerializer<ContainerCraftingRecipe>> CONTAINER_CRAFTING_RECIPE = RECIPES.register("container_shapeless_recipe_cw", ContainerCraftingRecipe.Serializer::new);

}
