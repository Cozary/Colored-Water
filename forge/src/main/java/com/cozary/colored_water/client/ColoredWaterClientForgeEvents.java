package com.cozary.colored_water.client;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.block.entity.ColoredWaterCauldronBlockEntity;
import com.cozary.colored_water.init.ModCauldrons;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ColoredWater.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ColoredWaterClientForgeEvents {

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register((state, view, pos, tintIndex) -> {
            if (view != null && pos != null) {
                BlockEntity be = view.getBlockEntity(pos);
                if (be instanceof ColoredWaterCauldronBlockEntity coloredBe) {
                    return coloredBe.getColor();
                }
            }
            return 0xB43F76E4;
        }, ModCauldrons.COLORED_WATER_CAULDRON.get());
    }
}
