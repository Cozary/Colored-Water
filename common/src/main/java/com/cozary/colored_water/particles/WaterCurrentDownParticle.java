package com.cozary.colored_water.particles;

import com.cozary.colored_water.fluids.BaseColorWater;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.material.FluidState;
import org.jetbrains.annotations.Nullable;

public class WaterCurrentDownParticle extends SingleQuadParticle {
    private float angle;

    public WaterCurrentDownParticle(ClientLevel level, double x, double y, double z, TextureAtlasSprite sprite) {
        super(level, x, y, z, sprite);
        this.lifetime = (int) (this.random.nextFloat() * 60.0F) + 30;
        this.hasPhysics = false;
        this.xd = 0.0;
        this.yd = -0.05;
        this.zd = 0.0;
        this.setSize(0.02F, 0.02F);
        this.quadSize = this.quadSize * (this.random.nextFloat() * 0.6F + 0.2F);
        this.gravity = 0.002F;
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
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            this.xd = this.xd + 0.6F * Mth.cos(this.angle);
            this.zd = this.zd + 0.6F * Mth.sin(this.angle);
            this.xd *= 0.07;
            this.zd *= 0.07;
            this.move(this.xd, this.yd, this.zd);
            FluidState fluid = this.level.getFluidState(BlockPos.containing(this.x, this.y, this.z));
            if ((!fluid.is(FluidTags.WATER) && !(fluid.getType() instanceof BaseColorWater)) || this.onGround) {
                this.remove();
            }

            this.angle += 0.08F;
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
            WaterCurrentDownParticle particle = new WaterCurrentDownParticle(level, x, y, z, this.sprite.get(random));
            particle.setColor(options.color());
            return particle;
        }
    }
}
