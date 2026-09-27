package com.cozary.colored_water.client;

import com.cozary.colored_water.init.ModParticles;
import com.cozary.colored_water.particles.*;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;

public class ParticleRegister {

    public static void registerFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.SPARKLE.get(), SparkleParticle.Factory::new);
        event.registerSpriteSet(ModParticles.FISHING.get(), WakeParticle.Factory::new);
        event.registerSpriteSet(ModParticles.SPLASH.get(), SplashParticle.Factory::new);
        event.registerSpriteSet(ModParticles.RAIN.get(), WaterDropParticle.Factory::new);
        event.registerSpriteSet(ModParticles.BUBBLE.get(), BubbleParticle.Factory::new);
        event.registerSpriteSet(ModParticles.BUBBLE_COLUMN_UP.get(), BubbleColumnUpParticle.Factory::new);
        event.registerSpriteSet(ModParticles.CURRENT_DOWN.get(), WaterCurrentDownParticle.Factory::new);
        event.registerSpriteSet(ModParticles.UNDERWATER.get(), UnderwaterParticle.Factory::new);
        event.registerSpriteSet(ModParticles.DRIPPING_DRIPSTONE_WATER.get(), DripParticle.DripstoneWaterHangProvider::new);
        event.registerSpriteSet(ModParticles.FALLING_DRIPSTONE_WATER.get(), DripParticle.DripstoneWaterFallProvider::new);
        event.registerSpriteSet(ModParticles.DRIPPING_WATER.get(), DripParticle.WaterHangProvider::new);
        event.registerSpriteSet(ModParticles.FALLING_WATER.get(), DripParticle.WaterFallProvider::new);
    }
}
