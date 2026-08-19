package com.cozary.colored_water.client;

import com.cozary.colored_water.init.ModParticles;
import com.cozary.colored_water.particles.BubbleColumnUpParticle;
import com.cozary.colored_water.particles.BubbleParticle;
import com.cozary.colored_water.particles.DripParticle;
import com.cozary.colored_water.particles.SparkleParticle;
import com.cozary.colored_water.particles.SplashParticle;
import com.cozary.colored_water.particles.UnderwaterParticle;
import com.cozary.colored_water.particles.WakeParticle;
import com.cozary.colored_water.particles.WaterCurrentDownParticle;
import com.cozary.colored_water.particles.WaterDropParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

public final class ParticleRegister implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry registry = ParticleFactoryRegistry.getInstance();
        registry.register(ModParticles.SPARKLE.get(), SparkleParticle.Factory::new);
        registry.register(ModParticles.FISHING.get(), WakeParticle.Factory::new);
        registry.register(ModParticles.SPLASH.get(), SplashParticle.Factory::new);
        registry.register(ModParticles.RAIN.get(), WaterDropParticle.Factory::new);
        registry.register(ModParticles.BUBBLE.get(), BubbleParticle.Factory::new);
        registry.register(ModParticles.BUBBLE_COLUMN_UP.get(), BubbleColumnUpParticle.Factory::new);
        registry.register(ModParticles.CURRENT_DOWN.get(), WaterCurrentDownParticle.Factory::new);
        registry.register(ModParticles.UNDERWATER.get(), UnderwaterParticle.Factory::new);
        registry.register(ModParticles.DRIPPING_DRIPSTONE_WATER.get(), DripParticle.DripstoneWaterHangProvider::new);
        registry.register(ModParticles.FALLING_DRIPSTONE_WATER.get(), DripParticle.DripstoneWaterFallProvider::new);
        registry.register(ModParticles.DRIPPING_WATER.get(), DripParticle.WaterHangProvider::new);
        registry.register(ModParticles.FALLING_WATER.get(), DripParticle.WaterFallProvider::new);
    }
}
