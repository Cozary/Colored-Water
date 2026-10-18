package com.cozary.colored_water.mixin;

import com.cozary.colored_water.block.entity.ColoredWaterBlockEntity;
import com.cozary.colored_water.fluids.BaseColorWater;
import com.cozary.colored_water.init.ModParticles;
import com.cozary.colored_water.particles.ColorParticleOptions;
import com.cozary.colored_water.particles.SparkleParticleOptions;
import com.cozary.colored_water.util.ColoredWaterUtil;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientLevel.class)
public abstract class ClientLevelMixin {

    @ModifyVariable(method = "trySpawnDripParticles", at = @At("HEAD"), argsOnly = true)
    private ParticleOptions coloredWater$modifyDripParticle(ParticleOptions particleData, BlockPos blockPos, BlockState blockState, ParticleOptions originalParticleData, boolean shapeDownSolid) {
        ClientLevel clientLevel = (ClientLevel) (Object) this;
        BlockPos fluidPos = blockPos.above();
        FluidState fluidState = clientLevel.getFluidState(fluidPos);
        if (fluidState.getType() instanceof BaseColorWater) {
            int color = ColoredWaterUtil.DEFAULT_COLOR;
            BlockEntity blockEntity = clientLevel.getBlockEntity(fluidPos);
            if (blockEntity instanceof ColoredWaterBlockEntity coloredWaterBlockEntity) {
                int customColor = coloredWaterBlockEntity.getColor();
                if (customColor != -1) {
                    color = customColor;
                }
            }
            return new ColorParticleOptions(ModParticles.DRIPPING_WATER.get(), color);
        }
        return particleData;
    }

    @Inject(method = "spawnFluidParticle", at = @At("TAIL"))
    private void coloredWater$spawnDripSparkleParticle(double xStart, double xEnd, double zStart, double zEnd, double y, ParticleOptions particleData, CallbackInfo callbackInfo) {
        if (particleData instanceof ColorParticleOptions colorParticleOptions && colorParticleOptions.getType() == ModParticles.DRIPPING_WATER.get()) {
            ClientLevel clientLevel = (ClientLevel) (Object) this;
            BlockPos fluidPos = BlockPos.containing(xStart, y + 1.05, zStart);
            BlockEntity blockEntity = clientLevel.getBlockEntity(fluidPos);
            int luminosity = 0;
            if (blockEntity instanceof ColoredWaterBlockEntity coloredWaterBlockEntity) {
                luminosity = coloredWaterBlockEntity.getLuminosity();
            }

            if (luminosity > 0) {
                double particleX = Mth.lerp(clientLevel.random.nextDouble(), xStart, xEnd);
                double particleZ = Mth.lerp(clientLevel.random.nextDouble(), zStart, zEnd);
                clientLevel.addParticle(new SparkleParticleOptions(colorParticleOptions.color()), particleX, y, particleZ, 0.01, -0.02, 0.01);
            }
        }
    }
}
