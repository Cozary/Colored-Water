package com.cozary.colored_water.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.Nullable;

public class SparkleParticle extends SingleQuadParticle {

    public SparkleParticle(ClientLevel level, double x, double y, double z, double dx, double dy, double dz, TextureAtlasSprite sprite) {
        super(level, x, y, z, dx, dy, dz, sprite);
        this.gravity = -0.01F;
        this.friction = 0.95F;

        this.xd = dx + (random.nextDouble() - 0.5D) * 0.1D;
        this.zd = dz + (random.nextDouble() - 0.5D) * 0.1D;
        this.yd = dy + (random.nextDouble() - 0.5D) * 0.02D;

        this.quadSize = 0.1F + random.nextFloat() * 0.1F;
        this.lifetime = 80 + random.nextInt(40);
        this.setSprite(sprite);
    }

    public void setColor(int hex) {
        float r = ((hex >> 16) & 0xFF) / 255F;
        float g = ((hex >> 8) & 0xFF) / 255F;
        float b = (hex & 0xFF) / 255F;

        float lighten = 0.5F;
        this.rCol = r + (1.0F - r) * lighten;
        this.gCol = g + (1.0F - g) * lighten;
        this.bCol = b + (1.0F - b) * lighten;
    }

    @Override
    public void tick() {
        super.tick();
        this.alpha = 0.5F + 0.5F * Mth.sin(this.age / 4.0F);

        if (this.age > this.lifetime - 10) {
            this.alpha *= (this.lifetime - this.age) / 10.0F;
        }
    }

    @Override
    protected Layer getLayer() {
        return Layer.TRANSLUCENT;
    }

    public static class Factory implements ParticleProvider<SparkleParticleOptions> {
        private final SpriteSet spriteSet;

        public Factory(SpriteSet sprite) {
            this.spriteSet = sprite;
        }

        @Nullable
        @Override
        public Particle createParticle(SparkleParticleOptions options, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource random) {
            TextureAtlasSprite sprite = this.spriteSet.get(random);
            SparkleParticle particle = new SparkleParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, sprite);
            particle.setColor(options.color());
            return particle;
        }
    }
}
