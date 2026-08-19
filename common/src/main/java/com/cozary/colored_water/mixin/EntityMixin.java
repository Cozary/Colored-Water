package com.cozary.colored_water.mixin;

import com.cozary.colored_water.block.ColoredWaterBlock;
import com.cozary.colored_water.block.ColoredWaterCauldronBlock;
import com.cozary.colored_water.block.entity.ColoredWaterBlockEntity;
import com.cozary.colored_water.block.entity.ColoredWaterCauldronBlockEntity;
import com.cozary.colored_water.fluids.BaseColorWater;
import com.cozary.colored_water.init.ModParticles;
import com.cozary.colored_water.particles.ColorParticleOptions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Inject(method = "isInWaterOrRain", at = @At("HEAD"), cancellable = true)
    private void colored_water$isInWaterOrRain(CallbackInfoReturnable<Boolean> cir) {
        Entity entity = (Entity) (Object) this;
        Level level = entity.level();
        if (level != null) {
            BlockPos pos = entity.blockPosition();
            BlockState state = level.getBlockState(pos);
            if (state.getBlock() instanceof ColoredWaterBlock || state.getBlock() instanceof ColoredWaterCauldronBlock) {
                cir.setReturnValue(true);
                return;
            }
            BlockPos eyePos = BlockPos.containing(entity.getEyePosition());
            BlockState eyeState = level.getBlockState(eyePos);
            if (eyeState.getBlock() instanceof ColoredWaterBlock || eyeState.getBlock() instanceof ColoredWaterCauldronBlock) {
                cir.setReturnValue(true);
                return;
            }
            FluidState fluidState = level.getFluidState(pos);
            if (fluidState.getType() instanceof BaseColorWater) {
                cir.setReturnValue(true);
            }
        }
    }

    @Redirect(
        method = "doWaterSplashEffect",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"
        )
    )
    private void colored_water$redirectDoWaterSplashParticle(
            Level level, ParticleOptions particle, double x, double y, double z,
            double dx, double dy, double dz) {

        if (particle == ParticleTypes.SPLASH || particle == ParticleTypes.BUBBLE) {
            Entity entity = (Entity) (Object) this;
            BlockPos pos = BlockPos.containing(x, y - 0.5, z);
            int color = getColoredWaterAt(level, pos);
            if (color == -1) {
                color = getColoredWaterAt(level, entity.blockPosition());
            }

            if (color != -1) {
                var pType = (particle == ParticleTypes.SPLASH) ? ModParticles.SPLASH.get() : ModParticles.BUBBLE.get();
                level.addParticle(new ColorParticleOptions(pType, color), x, y, z, dx, dy, dz);
                return;
            }
        }

        level.addParticle(particle, x, y, z, dx, dy, dz);
    }

    @Redirect(
        method = "sendBubbleColumnParticles",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/level/ServerLevel;sendParticles(Lnet/minecraft/core/particles/ParticleOptions;DDDIDDDD)I"
        )
    )
    private static <T extends ParticleOptions> int colored_water$redirectBubbleColumnParticles(
            ServerLevel level, T particle, double x, double y, double z, int count,
            double dx, double dy, double dz, double speed) {

        if (particle == ParticleTypes.SPLASH || particle == ParticleTypes.BUBBLE) {
            BlockPos pos = BlockPos.containing(x, y - 0.5, z);
            int color = getColoredWaterAt(level, pos);
            if (color != -1) {
                var pType = (particle == ParticleTypes.SPLASH) ? ModParticles.SPLASH.get() : ModParticles.BUBBLE.get();
                return level.sendParticles(new ColorParticleOptions(pType, color), x, y, z, count, dx, dy, dz, speed);
            }
        }

        return level.sendParticles(particle, x, y, z, count, dx, dy, dz, speed);
    }

    private static int getColoredWaterAt(Level level, BlockPos pos) {
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof ColoredWaterBlockEntity coloredBe) {
            int c = coloredBe.getColor();
            return c != -1 ? c : 0x3F76E4;
        }
        if (be instanceof ColoredWaterCauldronBlockEntity cauldronBe) {
            return cauldronBe.getColor();
        }
        FluidState fluid = level.getFluidState(pos);
        if (fluid.getType() instanceof BaseColorWater) {
            return 0x3F76E4;
        }
        BlockState state = level.getBlockState(pos);
        if (state.getBlock() instanceof ColoredWaterBlock || state.getBlock() instanceof ColoredWaterCauldronBlock) {
            return 0x3F76E4;
        }
        return -1;
    }
}
