package com.cozary.colored_water.particles;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

public class SparkleParticleType extends ParticleType<SparkleParticleOptions> {

    public SparkleParticleType() {
        super(true);
    }

    @Override
    public MapCodec<SparkleParticleOptions> codec() {
        return SparkleParticleOptions.CODEC;
    }

    @Override
    public StreamCodec<? super RegistryFriendlyByteBuf, SparkleParticleOptions> streamCodec() {
        return SparkleParticleOptions.STREAM_CODEC;
    }
}

