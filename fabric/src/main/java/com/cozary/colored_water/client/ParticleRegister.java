package com.cozary.colored_water.client;

import com.cozary.colored_water.init.ModParticles;
import com.cozary.colored_water.particles.SparkleParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

public final class ParticleRegister implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(ModParticles.SPARKLE.get(), SparkleParticle.Factory::new);
    }
}
