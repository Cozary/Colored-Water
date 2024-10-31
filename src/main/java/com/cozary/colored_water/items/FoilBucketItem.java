package com.cozary.colored_water.items;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;

public class FoilBucketItem extends BucketItem {


    public FoilBucketItem(Fluid fluid, Settings settings) {
        super(fluid, settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}
