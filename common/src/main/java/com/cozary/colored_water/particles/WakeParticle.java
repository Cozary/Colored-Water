package com.cozary.colored_water.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.Nullable;

public class WakeParticle extends SingleQuadParticle {
    private final SpriteSet sprites;

    public WakeParticle(
            ClientLevel level,
            double x,
            double y,
            double z,
            double dx,
            double dy,
            double dz,
            SpriteSet sprites
    ) {
        super(level, x, y, z, 0.0, 0.0, 0.0, sprites.first());
        this.sprites = sprites;
        this.xd *= 0.3F;
        this.yd = this.random.nextFloat() * 0.2F + 0.1F;
        this.zd *= 0.3F;
        this.setSize(0.01F, 0.01F);
        this.lifetime = (int)(8.0 / (this.random.nextFloat() * 0.8 + 0.2));
        this.setSpriteFromAge(sprites);
        this.gravity = 0.0F;
        this.xd = dx;
        this.yd = dy;
        this.zd = dz;
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
        int i = 60 - this.lifetime;
        if (this.lifetime-- <= 0) {
            this.remove();
        } else {
            this.yd = this.yd - this.gravity;
            this.move(this.xd, this.yd, this.zd);
            this.xd *= 0.98F;
            this.yd *= 0.98F;
            this.zd *= 0.98F;
            float f = i * 0.001F;
            this.setSize(f, f);
            this.setSprite(this.sprites.get(i % 4, 4));
        }
    }

    public static class Factory implements ParticleProvider<ColorParticleOptions> {
        private final SpriteSet sprites;

        public Factory(SpriteSet sprites) {
            this.sprites = sprites;
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
            WakeParticle particle = new WakeParticle(level, x, y, z, dx, dy, dz, this.sprites);
            particle.setColor(options.color());
            return particle;
        }
    }
}
