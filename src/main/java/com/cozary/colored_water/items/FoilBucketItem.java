package com.cozary.colored_water.items;


import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class FoilBucketItem extends BucketItem {


    public FoilBucketItem(Fluid supplier) {
        super(supplier, new Properties().stacksTo(1).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true));
    }
}
