package com.cozary.colored_water.init;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.particles.ColorParticleOptions;
import com.cozary.colored_water.particles.ColorParticleType;
import com.cozary.colored_water.particles.SparkleParticleOptions;
import com.cozary.colored_water.particles.SparkleParticleType;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;

public class ModParticles {

    public static final RegistrationProvider<ParticleType<?>> PARTICLES = RegistrationProvider
            .get(Registries.PARTICLE_TYPE, ColoredWater.MOD_ID);

    public static final RegistryObject<ParticleType<SparkleParticleOptions>> SPARKLE = PARTICLES.register("sparkle",
            SparkleParticleType::new);

    public static final RegistryObject<ParticleType<ColorParticleOptions>> FISHING = PARTICLES.register("fishing",
            () -> new ColorParticleType(false));

    public static final RegistryObject<ParticleType<ColorParticleOptions>> SPLASH = PARTICLES.register("splash",
            () -> new ColorParticleType(false));

    public static final RegistryObject<ParticleType<ColorParticleOptions>> RAIN = PARTICLES.register("rain",
            () -> new ColorParticleType(false));

    public static final RegistryObject<ParticleType<ColorParticleOptions>> BUBBLE = PARTICLES.register("bubble",
            () -> new ColorParticleType(false));

    public static final RegistryObject<ParticleType<ColorParticleOptions>> BUBBLE_COLUMN_UP = PARTICLES.register("bubble_column_up",
            () -> new ColorParticleType(false));

    public static final RegistryObject<ParticleType<ColorParticleOptions>> CURRENT_DOWN = PARTICLES.register("current_down",
            () -> new ColorParticleType(false));

    public static final RegistryObject<ParticleType<ColorParticleOptions>> UNDERWATER = PARTICLES.register("underwater",
            () -> new ColorParticleType(false));

    public static final RegistryObject<ParticleType<ColorParticleOptions>> DRIPPING_DRIPSTONE_WATER = PARTICLES.register("dripping_dripstone_water",
            () -> new ColorParticleType(false));

    public static final RegistryObject<ParticleType<ColorParticleOptions>> FALLING_DRIPSTONE_WATER = PARTICLES.register("falling_dripstone_water",
            () -> new ColorParticleType(false));

    public static final RegistryObject<ParticleType<ColorParticleOptions>> DRIPPING_WATER = PARTICLES.register("dripping_water",
            () -> new ColorParticleType(false));

    public static final RegistryObject<ParticleType<ColorParticleOptions>> FALLING_WATER = PARTICLES.register("falling_water",
            () -> new ColorParticleType(false));

    public static void loadClass() {
    }

}
