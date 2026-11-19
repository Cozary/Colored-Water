package com.cozary.colored_water.mixin;

import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(CauldronInteraction.Dispatcher.class)
public interface CauldronInteractionDispatcherInvoker {

    @Invoker("put")
    void coloredWater$put(Item item, CauldronInteraction interaction);

    @Invoker("put")
    void coloredWater$put(TagKey<Item> tag, CauldronInteraction interaction);
}

