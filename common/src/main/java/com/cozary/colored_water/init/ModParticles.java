package com.cozary.colored_water.init;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.particles.SparkleParticleOptions;
import com.cozary.colored_water.particles.SparkleParticleType;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;

public class ModParticles {

    public static final RegistrationProvider<ParticleType<?>> PARTICLES = RegistrationProvider.get(Registries.PARTICLE_TYPE, ColoredWater.MOD_ID);

    public static final RegistryObject<ParticleType<SparkleParticleOptions>> SPARKLE =
            PARTICLES.register("sparkle", SparkleParticleType::new);


    public static void loadClass() {
    }

}
