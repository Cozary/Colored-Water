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
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.material.FluidState;
import org.jetbrains.annotations.Nullable;

public class BubbleColumnUpParticle extends SingleQuadParticle {

    public BubbleColumnUpParticle(
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
        this.gravity = -0.125F;
        this.friction = 0.85F;
        this.setSize(0.02F, 0.02F);
        this.quadSize = this.quadSize * (this.random.nextFloat() * 0.6F + 0.2F);
        this.xd = dx * 0.2F + (this.random.nextFloat() * 2.0F - 1.0F) * 0.02F;
        this.yd = dy * 0.2F + (this.random.nextFloat() * 2.0F - 1.0F) * 0.02F;
        this.zd = dz * 0.2F + (this.random.nextFloat() * 2.0F - 1.0F) * 0.02F;
        this.lifetime = (int) (40.0 / (this.random.nextFloat() * 0.8 + 0.2));
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
        super.tick();
        if (!this.removed) {
            FluidState fluid = this.level.getFluidState(BlockPos.containing(this.x, this.y, this.z));
            if (!fluid.is(FluidTags.WATER) && !(fluid.getType() instanceof BaseColorWater)) {
                this.remove();
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
            BubbleColumnUpParticle particle = new BubbleColumnUpParticle(level, x, y, z, dx, dy, dz, this.sprite.get(random));
            particle.setColor(options.color());
            return particle;
        }
    }
}
