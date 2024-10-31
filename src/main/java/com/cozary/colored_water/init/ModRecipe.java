package com.cozary.colored_water.init;

import com.cozary.colored_water.recipe.ContainerCraftingRecipe;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static com.cozary.colored_water.ColoredWater.MOD_ID;

public class ModRecipe {

    public static final ContainerCraftingRecipe.Serializer CONTAINER_CRAFTING_RECIPE;

    static {
        CONTAINER_CRAFTING_RECIPE = Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(MOD_ID, "container_shapeless_recipe_cw"), new ContainerCraftingRecipe.Serializer());
    }

    public static void loadClass() {

    }
}
