package com.cozary.colored_water.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.cozary.colored_water.ColoredWater.MOD_ID;

public class ModTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public static final Supplier<CreativeModeTab> COLORED_WATER_TAB = CREATIVE_MODE_TABS.register("colored_water", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.colored_water"))
            .icon(() -> new ItemStack(ModItems.PINK_WATER_BUCKET.get()))
            .displayItems((parameters, output) -> ModItems.REGISTERED_ITEMS.forEach(output::accept))
            .build());
}
