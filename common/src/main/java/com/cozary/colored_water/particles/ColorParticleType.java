package com.cozary.colored_water.particles;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

public class ColorParticleType extends ParticleType<ColorParticleOptions> {

    public ColorParticleType(boolean overrideLimiter) {
        super(overrideLimiter);
    }

    public ColorParticleType() {
        this(false);
    }

    @Override
    public MapCodec<ColorParticleOptions> codec() {
        return ColorParticleOptions.codec(this);
    }

    @Override
    public StreamCodec<? super RegistryFriendlyByteBuf, ColorParticleOptions> streamCodec() {
        return ColorParticleOptions.streamCodec(this);
    }
}
