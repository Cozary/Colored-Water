package com.cozary.colored_water.particles;

import com.cozary.colored_water.init.ModParticles;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.Nullable;

public class DripParticle extends SingleQuadParticle {
    private final Fluid type;
    protected boolean isGlowing;

    public DripParticle(ClientLevel level, double x, double y, double z, Fluid fluid, TextureAtlasSprite sprite) {
        super(level, x, y, z, sprite);
        this.setSize(0.01F, 0.01F);
        this.gravity = 0.06F;
        this.type = fluid;
    }

    public void setColor(int hex) {
        float r = ((hex >> 16) & 0xFF) / 255F;
        float g = ((hex >> 8) & 0xFF) / 255F;
        float b = (hex & 0xFF) / 255F;
        this.rCol = r;
        this.gCol = g;
        this.bCol = b;
    }

    protected Fluid getType() {
        return this.type;
    }

    @Override
    public Layer getLayer() {
        return Layer.TRANSLUCENT;
    }

    @Override
    public int getLightColor(float partialTick) {
        return this.isGlowing ? 240 : super.getLightColor(partialTick);
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        this.preMoveUpdate();
        if (!this.removed) {
            this.yd = this.yd - (double) this.gravity;
            this.move(this.xd, this.yd, this.zd);
            this.postMoveUpdate();
            if (!this.removed) {
                this.xd *= 0.98F;
                this.yd *= 0.98F;
                this.zd *= 0.98F;
                if (this.type != Fluids.EMPTY) {
                    BlockPos blockpos = BlockPos.containing(this.x, this.y, this.z);
                    FluidState fluidstate = this.level.getFluidState(blockpos);
                    if (fluidstate.getType() == this.type && this.y < (double) ((float) blockpos.getY() + fluidstate.getHeight(this.level, blockpos))) {
                        this.remove();
                    }
                }
            }
        }
    }

    protected void preMoveUpdate() {
        if (this.lifetime-- <= 0) {
            this.remove();
        }
    }

    protected void postMoveUpdate() {
    }

    public static class DripHangParticle extends DripParticle {
        private final ParticleOptions fallingParticle;

        public DripHangParticle(
                ClientLevel level,
                double x,
                double y,
                double z,
                Fluid fluid,
                ParticleOptions fallingParticle,
                TextureAtlasSprite sprite
        ) {
            super(level, x, y, z, fluid, sprite);
            this.fallingParticle = fallingParticle;
            this.gravity *= 0.02F;
            this.lifetime = 40;
        }

        @Override
        protected void preMoveUpdate() {
            if (this.lifetime-- <= 0) {
                this.remove();
                this.level.addParticle(this.fallingParticle, this.x, this.y, this.z, this.xd, this.yd, this.zd);
            }
        }

        @Override
        protected void postMoveUpdate() {
            this.xd *= 0.02;
            this.yd *= 0.02;
            this.zd *= 0.02;
        }
    }

    public static class FallAndLandParticle extends DripParticle {
        protected final ParticleOptions landParticle;

        public FallAndLandParticle(
                ClientLevel level,
                double x,
                double y,
                double z,
                Fluid fluid,
                ParticleOptions landParticle,
                TextureAtlasSprite sprite
        ) {
            super(level, x, y, z, fluid, sprite);
            this.lifetime = (int) (64.0 / (this.random.nextFloat() * 0.8 + 0.2));
            this.landParticle = landParticle;
        }

        @Override
        protected void postMoveUpdate() {
            if (this.onGround) {
                this.remove();
                this.level.addParticle(this.landParticle, this.x, this.y, this.z, 0.0, 0.0, 0.0);
            }
        }
    }

    public static class DripstoneFallAndLandParticle extends DripParticle {
        protected final ParticleOptions landParticle;

        public DripstoneFallAndLandParticle(
                ClientLevel level,
                double x,
                double y,
                double z,
                Fluid fluid,
                ParticleOptions landParticle,
                TextureAtlasSprite sprite
        ) {
            super(level, x, y, z, fluid, sprite);
            this.lifetime = (int) (64.0 / (this.random.nextFloat() * 0.8 + 0.2));
            this.landParticle = landParticle;
        }

        @Override
        protected void postMoveUpdate() {
            if (this.onGround) {
                this.remove();
                this.level.addParticle(this.landParticle, this.x, this.y, this.z, 0.0, 0.0, 0.0);
                float f = Mth.randomBetween(this.random, 0.3F, 1.0F);
                this.level.playLocalSound(this.x, this.y, this.z, SoundEvents.POINTED_DRIPSTONE_DRIP_WATER, SoundSource.BLOCKS, f, 1.0F, false);
            }
        }
    }

    public static class WaterHangProvider implements ParticleProvider<ColorParticleOptions> {
        private final SpriteSet sprite;

        public WaterHangProvider(SpriteSet sprite) {
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
            ColorParticleOptions fallingOptions = new ColorParticleOptions(ModParticles.FALLING_WATER.get(), options.color());
            DripParticle dripparticle = new DripHangParticle(
                    level, x, y, z, Fluids.WATER, fallingOptions, this.sprite.get(random)
            );
            dripparticle.setColor(options.color());
            return dripparticle;
        }
    }

    public static class WaterFallProvider implements ParticleProvider<ColorParticleOptions> {
        private final SpriteSet sprite;

        public WaterFallProvider(SpriteSet sprite) {
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
            ColorParticleOptions splashOptions = new ColorParticleOptions(ModParticles.SPLASH.get(), options.color());
            DripParticle dripparticle = new FallAndLandParticle(
                    level, x, y, z, Fluids.WATER, splashOptions, this.sprite.get(random)
            );
            dripparticle.setColor(options.color());
            return dripparticle;
        }
    }

    public static class DripstoneWaterHangProvider implements ParticleProvider<ColorParticleOptions> {
        private final SpriteSet sprite;

        public DripstoneWaterHangProvider(SpriteSet sprite) {
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
            ColorParticleOptions fallingOptions = new ColorParticleOptions(ModParticles.FALLING_DRIPSTONE_WATER.get(), options.color());
            DripParticle dripparticle = new DripHangParticle(
                    level, x, y, z, Fluids.WATER, fallingOptions, this.sprite.get(random)
            );
            dripparticle.setColor(options.color());
            return dripparticle;
        }
    }

    public static class DripstoneWaterFallProvider implements ParticleProvider<ColorParticleOptions> {
        private final SpriteSet sprite;

        public DripstoneWaterFallProvider(SpriteSet sprite) {
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
            ColorParticleOptions splashOptions = new ColorParticleOptions(ModParticles.SPLASH.get(), options.color());
            DripParticle dripparticle = new DripstoneFallAndLandParticle(
                    level, x, y, z, Fluids.WATER, splashOptions, this.sprite.get(random)
            );
            dripparticle.setColor(options.color());
            return dripparticle;
        }
    }
}
