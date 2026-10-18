package com.cozary.colored_water.mixin;

import com.cozary.colored_water.init.ModParticles;
import com.cozary.colored_water.particles.ColorParticleOptions;
import com.cozary.colored_water.util.ColoredWaterUtil;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.WeatherEffectRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(WeatherEffectRenderer.class)
public abstract class WeatherEffectRendererMixin {

    @Redirect(method = "tickRainParticles", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientLevel;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"))
    private void colored_water$redirectRainParticle(ClientLevel level, ParticleOptions particle, double x, double y, double z, double dx, double dy, double dz) {

        if (particle == ParticleTypes.RAIN) {
            BlockPos pos = BlockPos.containing(x, y - 0.1, z);
            int color = ColoredWaterUtil.getColoredWaterAt(level, pos);
            if (color == -1) {
                color = ColoredWaterUtil.getColoredWaterAt(level, BlockPos.containing(x, y, z));
            }

            if (color != -1) {
                level.addParticle(new ColorParticleOptions(ModParticles.RAIN.get(), color), x, y, z, dx, dy, dz);
                return;
            }
        }

        level.addParticle(particle, x, y, z, dx, dy, dz);
    }
}
