package com.cozary.colored_water.mixin;

import com.cozary.colored_water.particles.SparkleParticleOptions;
import com.cozary.colored_water.util.ColoredWaterUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LayeredCauldronBlock.class)
public abstract class LayeredCauldronBlockMixin {

    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (ColoredWaterUtil.isModCauldron(state.getBlock())) {
            int luminosity = state.getLightEmission();
            if (luminosity > 0) {
                float chance = (luminosity / 15.0F) * 0.4F;
                if (random.nextFloat() < chance) {
                    int color = ColoredWaterUtil.getCauldronColor(state.getBlock());
                    if (color != -1) {
                        double particleX = (double) pos.getX() + 0.125D + random.nextDouble() * 0.75D;
                        double particleY = (double) pos.getY() + 0.35D + (state.getValue(LayeredCauldronBlock.LEVEL) * 0.2D);
                        double particleZ = (double) pos.getZ() + 0.125D + random.nextDouble() * 0.75D;
                        level.addParticle(SparkleParticleOptions.of(color), particleX, particleY, particleZ, 0.01, 0.01, 0.01);
                    }
                }
            }
        }
    }
}
