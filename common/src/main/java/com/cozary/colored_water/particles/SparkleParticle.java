package com.cozary.colored_water.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.util.Mth;

public class SparkleParticle extends TextureSheetParticle {
    private final SpriteSet sprites;

    public SparkleParticle(ClientLevel level, double x, double y, double z, double dx, double dy, double dz, SpriteSet sprites) {
        super(level, x, y, z);
        this.sprites = sprites;
        this.gravity = -0.01F;
        this.friction = 0.95F;

        this.xd = dx + (random.nextDouble() - 0.5D) * 0.1D;
        this.zd = dz + (random.nextDouble() - 0.5D) * 0.1D;
        this.yd = dy + (random.nextDouble() - 0.5D) * 0.02D;

        this.quadSize = 0.1F + random.nextFloat() * 0.1F;
        this.lifetime = 80 + random.nextInt(40);
        this.setSpriteFromAge(sprites);
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
        this.setSpriteFromAge(sprites);

        this.alpha = 0.5F + 0.5F * Mth.sin(this.age / 4.0F);

        if (this.age > this.lifetime - 10) {
            this.alpha *= (this.lifetime - this.age) / 10.0F;
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }
}