package com.cozary.colored_water.particles;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record ColorParticleOptions(ParticleType<ColorParticleOptions> particleType, int color) implements ParticleOptions {

    @Override
    public ParticleType<?> getType() {
        return this.particleType;
    }

    public static MapCodec<ColorParticleOptions> codec(ParticleType<ColorParticleOptions> type) {
        return Codec.INT.fieldOf("color").xmap(
                color -> new ColorParticleOptions(type, color),
                ColorParticleOptions::color
        );
    }

    public static StreamCodec<RegistryFriendlyByteBuf, ColorParticleOptions> streamCodec(ParticleType<ColorParticleOptions> type) {
        return StreamCodec.composite(
                ByteBufCodecs.INT,
                ColorParticleOptions::color,
                color -> new ColorParticleOptions(type, color)
        );
    }
}
