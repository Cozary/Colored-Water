package com.cozary.colored_water.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.Nullable;


public class SparkleParticleProvider implements ParticleProvider<SparkleParticleOptions> {
    private final TextureAtlasSprite sprites;

    public SparkleParticleProvider(TextureAtlasSprite sprites) {
        this.sprites = sprites;
    }


    @Override
    public @Nullable Particle createParticle(SparkleParticleOptions sparkleParticleOptions, ClientLevel clientLevel, double x, double y, double z, double dx, double dy, double dz, RandomSource randomSource) {
        SparkleParticle particle = new SparkleParticle(clientLevel, x, y, z, dx, dy, dz, sprites);
        particle.setColor(sparkleParticleOptions.color());
        return particle;
    }
}

