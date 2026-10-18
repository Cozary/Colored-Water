package com.cozary.colored_water.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.Nullable;

public class SplashParticle extends WaterDropParticle {

    public SplashParticle(ClientLevel level, double x, double y, double z, double dx, double dy, double dz, TextureAtlasSprite sprite) {
        super(level, x, y, z, sprite);
        this.gravity = 0.04F;
        if (dy == 0.0 && (dx != 0.0 || dz != 0.0)) {
            this.xd = dx;
            this.yd = 0.1;
            this.zd = dz;
        }
    }

    public static class Factory implements ParticleProvider<ColorParticleOptions> {
        private final SpriteSet sprite;

        public Factory(SpriteSet sprite) {
            this.sprite = sprite;
        }

        @Nullable
        @Override
        public Particle createParticle(ColorParticleOptions options, ClientLevel level, double x, double y, double z, double dx, double dy, double dz, RandomSource random) {
            SplashParticle particle = new SplashParticle(level, x, y, z, dx, dy, dz, this.sprite.get(random));
            particle.setColor(options.color());
            return particle;
        }
    }
}

