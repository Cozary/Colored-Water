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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BubbleColumnBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BubbleColumnBlock.class)
public abstract class BubbleColumnBlockMixin {

    @Redirect(
        method = "animateTick",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/Level;addAlwaysVisibleParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"
        )
    )
    private void colored_water$redirectBubbleColumnParticles(
            Level level, ParticleOptions particle, double x, double y, double z,
            double dx, double dy, double dz) {

        if (particle == ParticleTypes.BUBBLE_COLUMN_UP || particle == ParticleTypes.CURRENT_DOWN) {
            BlockPos pos = BlockPos.containing(x, y, z);
            int color = getColoredWaterAt(level, pos);
            if (color != -1) {
                var pType = (particle == ParticleTypes.BUBBLE_COLUMN_UP)
                        ? ModParticles.BUBBLE_COLUMN_UP.get()
                        : ModParticles.CURRENT_DOWN.get();
                level.addAlwaysVisibleParticle(new ColorParticleOptions(pType, color), x, y, z, dx, dy, dz);
                return;
            }
        }

        level.addAlwaysVisibleParticle(particle, x, y, z, dx, dy, dz);
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
