package com.cozary.colored_water.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.Nullable;

public class SplashParticle extends SingleQuadParticle {

    public SplashParticle(
            ClientLevel level,
            double x,
            double y,
            double z,
            double dx,
            double dy,
            double dz,
            TextureAtlasSprite sprite
    ) {
        super(level, x, y, z, sprite);
        this.setSize(0.01F, 0.01F);
        this.gravity = 0.04F;
        this.lifetime = (int)(16.0 / (this.random.nextFloat() * 0.8 + 0.2));
        if (dy == 0.0 && (dx != 0.0 || dz != 0.0)) {
            this.xd = dx;
            this.yd = 0.1;
            this.zd = dz;
        } else {
            this.xd = dx;
            this.yd = dy;
            this.zd = dz;
        }
    }

    public void setColor(int hex) {
        float r = ((hex >> 16) & 0xFF) / 255F;
        float g = ((hex >> 8) & 0xFF) / 255F;
        float b = (hex & 0xFF) / 255F;
        this.rCol = r;
        this.gCol = g;
        this.bCol = b;
    }

    @Override
    public Layer getLayer() {
        return Layer.TRANSLUCENT;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.lifetime-- <= 0) {
            this.remove();
        } else {
            this.yd = this.yd - this.gravity;
            this.move(this.xd, this.yd, this.zd);
            this.xd *= 0.98F;
            this.yd *= 0.98F;
            this.zd *= 0.98F;
            if (this.onGround) {
                if (Math.random() < 0.5) {
                    this.remove();
                }
                this.xd *= 0.7F;
                this.zd *= 0.7F;
            }
        }
    }

    public static class Factory implements ParticleProvider<ColorParticleOptions> {
        private final SpriteSet sprite;

        public Factory(SpriteSet sprite) {
            this.sprite = sprite;
        }

        @Nullable
        @Override
        public Particle createParticle(
                ColorParticleOptions options,
                ClientLevel level,
                double x,
                double y,
                double z,
                double dx,
                double dy,
                double dz,
                RandomSource random
        ) {
            SplashParticle particle = new SplashParticle(level, x, y, z, dx, dy, dz, this.sprite.get(random));
            particle.setColor(options.color());
            return particle;
        }
    }
}
