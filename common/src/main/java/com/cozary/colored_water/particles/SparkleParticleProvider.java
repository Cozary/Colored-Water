package com.cozary.colored_water.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;


public class SparkleParticleProvider implements ParticleProvider<SparkleParticleOptions> {
    private final SpriteSet sprites;

    public SparkleParticleProvider(SpriteSet sprites) {
        this.sprites = sprites;
    }

    @Override
    public Particle createParticle(SparkleParticleOptions options, ClientLevel level,
                                   double x, double y, double z,
                                   double dx, double dy, double dz) {
        SparkleParticle particle = new SparkleParticle(level, x, y, z, dx, dy, dz, sprites);
        particle.setColor(options.color());
        return particle;
    }
}

