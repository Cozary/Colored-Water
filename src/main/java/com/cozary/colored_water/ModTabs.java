package com.cozary.colored_water;

import com.cozary.colored_water.items.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ColoredWater.MOD_ID);

    public static RegistryObject<CreativeModeTab> COLOREDWATER_TAB = CREATIVE_MODE_TABS.register(ColoredWater.MOD_ID, () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.PINK_WATER_BUCKET.get()))
            .title(Component.translatable("tabs." + ColoredWater.MOD_ID + ".tab"))
            .displayItems((featureFlagSet, tabOutput) -> {
                ModItems.getItemsRegistered().forEach(registryObject -> tabOutput.accept(new ItemStack(registryObject)));
            })
            .build());

}


