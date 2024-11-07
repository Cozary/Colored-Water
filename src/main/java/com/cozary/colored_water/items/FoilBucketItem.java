package com.cozary.colored_water.items;


import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import org.jetbrains.annotations.NotNull;

public class FoilBucketItem extends BucketItem {


    public FoilBucketItem(Fluid supplier, Properties builder) {
        super(supplier, builder);
    }

    @Override
    public boolean isFoil(@NotNull ItemStack p_41453_) {
        return true;
    }
}
