package com.cozary.colored_water.mixin;

import com.cozary.colored_water.block.ColoredWaterBlock;
import com.cozary.colored_water.block.ColoredWaterCauldronBlock;
import com.cozary.colored_water.block.entity.ColoredWaterBlockEntity;
import com.cozary.colored_water.block.entity.ColoredWaterCauldronBlockEntity;
import com.cozary.colored_water.fluids.BaseColorWater;
import com.cozary.colored_water.init.ModParticles;
import com.cozary.colored_water.particles.ColorParticleOptions;
import com.cozary.colored_water.util.ColoredWaterUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FishingHook.class)
public abstract class FishingHookMixin {

    @Redirect(
        method = "catchingFish",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/level/ServerLevel;sendParticles(Lnet/minecraft/core/particles/ParticleOptions;DDDIDDDD)I"
        )
    )
    private <T extends ParticleOptions> int colored_water$redirectFishingParticles(
            ServerLevel level, T particle, double x, double y, double z, int count,
            double deltaX, double deltaY, double deltaZ, double speed) {

        if (particle == ParticleTypes.FISHING || particle == ParticleTypes.SPLASH) {
            FishingHook hook = (FishingHook) (Object) this;
            BlockPos hookPos = hook.blockPosition();
            int color = ColoredWaterUtil.getColoredWaterAt(level, hookPos);
            if (color == -1) {
                color = ColoredWaterUtil.getColoredWaterAt(level, BlockPos.containing(x, y, z));
            }

            if (color != -1) {
                var particleType = (particle == ParticleTypes.FISHING) ? ModParticles.FISHING.get() : ModParticles.SPLASH.get();
                return level.sendParticles(
                    new ColorParticleOptions(particleType, color),
                    x, y, z, count, deltaX, deltaY, deltaZ, speed
                );
            }
        }

        return level.sendParticles(particle, x, y, z, count, deltaX, deltaY, deltaZ, speed);
    }
}
