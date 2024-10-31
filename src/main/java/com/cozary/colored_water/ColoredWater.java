package com.cozary.colored_water;

import com.cozary.colored_water.cauldrons.behaviour.ColorCauldronBehavior;
import com.cozary.colored_water.cauldrons.behaviour.CondenseCauldronBehavior;
import com.cozary.colored_water.cauldrons.behaviour.LuminousCauldronBehavior;
import com.cozary.colored_water.cauldrons.behaviour.LuminousCondenseCauldronBehavior;
import com.cozary.colored_water.init.ModCauldrons;
import com.cozary.colored_water.init.ModBlocks;
import com.cozary.colored_water.init.ModFluids;
import com.cozary.colored_water.init.ModItems;
import com.cozary.colored_water.init.ModRecipe;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

public class ColoredWater implements ModInitializer {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "colored_water";

    private static final RegistryKey<ItemGroup> ITEM_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(MOD_ID, "colored_water_tab"));

    @Override
    public void onInitialize() {

        Registry.register(Registries.ITEM_GROUP, ITEM_GROUP, FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup.colored_water"))
                .icon(() -> new ItemStack(ModItems.PINK_WATER_BUCKET))
                .entries((displayContext, entries) -> {
                    entries.addAll(ModItems.REGISTERED_ITEMS.stream()
                            .map(ItemStack::new)
                            .collect(Collectors.toList()));
                })
                .build()
        );

        ModFluids.loadClass();
        ModBlocks.loadClass();
        ModItems.loadClass();
        ModRecipe.loadClass();
        ModCauldrons.loadClass();

        ColorCauldronBehavior.init();
        CondenseCauldronBehavior.init();
        LuminousCauldronBehavior.init();
        LuminousCondenseCauldronBehavior.init();

    }

}
