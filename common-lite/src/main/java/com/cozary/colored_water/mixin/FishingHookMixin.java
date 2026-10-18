package com.cozary.colored_water.mixin;

import com.cozary.colored_water.init.ModParticles;
import com.cozary.colored_water.particles.ColorParticleOptions;
import com.cozary.colored_water.util.ColoredWaterUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FishingHook.class)
public abstract class FishingHookMixin {

    @Redirect(method = "catchingFish", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z"))
    private boolean colored_water$checkWaterBlock(BlockState state, Block block) {
        if (block == Blocks.WATER) {
            return state.is(Blocks.WATER) || state.getFluidState().is(FluidTags.WATER);
        }
        return state.is(block);
    }

    @Redirect(method = "catchingFish", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;sendParticles(Lnet/minecraft/core/particles/ParticleOptions;DDDIDDDD)I"))
    private <T extends ParticleOptions> int colored_water$redirectFishingParticles(
            ServerLevel level, T particle, double x, double y, double z, int count,
            double deltaX, double deltaY, double deltaZ, double speed) {

        if (particle == ParticleTypes.FISHING || particle == ParticleTypes.SPLASH || particle == ParticleTypes.BUBBLE) {
            FishingHook hook = (FishingHook) (Object) this;
            BlockPos hookPos = hook.blockPosition();
            int color = ColoredWaterUtil.getColoredWaterAt(level, hookPos);
            if (color == -1) {
                color = ColoredWaterUtil.getColoredWaterAt(level, hookPos.below());
            }
            if (color == -1) {
                color = ColoredWaterUtil.getColoredWaterAt(level, BlockPos.containing(x, y - 0.5, z));
            }
            if (color == -1) {
                color = ColoredWaterUtil.getColoredWaterAt(level, BlockPos.containing(x, y - 1.0, z));
            }
            if (color == -1) {
                color = ColoredWaterUtil.getColoredWaterAt(level, BlockPos.containing(x, y, z));
            }

            if (color != -1) {
                ParticleType<ColorParticleOptions> particleType = (particle == ParticleTypes.FISHING) ? ModParticles.FISHING.get() : (particle == ParticleTypes.SPLASH) ? ModParticles.SPLASH.get() : ModParticles.BUBBLE.get();
                return level.sendParticles(new ColorParticleOptions(particleType, color), x, y, z, count, deltaX, deltaY, deltaZ, speed);
            }
        }

        return level.sendParticles(particle, x, y, z, count, deltaX, deltaY, deltaZ, speed);
    }
}
