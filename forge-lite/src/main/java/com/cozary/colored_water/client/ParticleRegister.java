package com.cozary.colored_water.client;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.init.ModParticles;
import com.cozary.colored_water.particles.SparkleParticle;
import com.cozary.colored_water.particles.SparkleParticleProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ColoredWater.MOD_ID, value = Dist.CLIENT)
public class ParticleRegister {

    @SubscribeEvent
    public static void registerFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.SPARKLE.get(), SparkleParticle.Factory::new);
    }
}
