package com.cozary.colored_water.particles;

import com.cozary.colored_water.init.ModParticles;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public record SparkleParticleOptions(int color) implements ParticleOptions {
    private static final Map<Integer, SparkleParticleOptions> CACHE = new ConcurrentHashMap<>();

    public static SparkleParticleOptions of(int color) {
        return CACHE.computeIfAbsent(color, SparkleParticleOptions::new);
    }

    public static final MapCodec<SparkleParticleOptions> CODEC =
            Codec.INT.fieldOf("color").xmap(SparkleParticleOptions::of, o -> o.color);

    public static final StreamCodec<RegistryFriendlyByteBuf, SparkleParticleOptions> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.INT,
                    SparkleParticleOptions::color,
                    SparkleParticleOptions::of
            );

    @Override
    public ParticleType<?> getType() {
        return ModParticles.SPARKLE.get();
    }
}
