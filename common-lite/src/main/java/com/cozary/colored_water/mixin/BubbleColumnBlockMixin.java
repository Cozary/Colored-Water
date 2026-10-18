package com.cozary.colored_water.mixin;

import com.cozary.colored_water.block.entity.WaterloggedColorBlockEntity;
import com.cozary.colored_water.fluids.BaseColorWater;
import com.cozary.colored_water.fluids.ColorWaterFluid;
import com.cozary.colored_water.init.ModParticles;
import com.cozary.colored_water.particles.ColorParticleOptions;
import com.cozary.colored_water.particles.SparkleParticleOptions;
import com.cozary.colored_water.util.ColoredWaterUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BubbleColumnBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BubbleColumnBlock.class)
public abstract class BubbleColumnBlockMixin {

    @Inject(method = "canExistIn", at = @At("HEAD"), cancellable = true)
    private static void coloredWater$canExistIn(BlockState blockState, CallbackInfoReturnable<Boolean> cir) {
        if (blockState.getFluidState().getType() instanceof BaseColorWater && blockState.getFluidState().isSource()) {
            cir.setReturnValue(true);
        }
    }

    @Redirect(method = "updateColumn(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/state/BlockState;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/LevelAccessor;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z"))
    private static boolean coloredWater$redirectSetBlockInUpdateColumn(LevelAccessor level, BlockPos pos, BlockState state, int flags) {
        if (state.is(Blocks.WATER)) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof WaterloggedColorBlockEntity waterloggedBe && waterloggedBe.hasFluid()) {
                BlockState coloredState = waterloggedBe.getFluid().getSource(false).createLegacyBlock();
                return level.setBlock(pos, coloredState, flags);
            }
            BlockState current = level.getBlockState(pos);
            if (current.getBlock() instanceof LiquidBlock && current.getFluidState().getType() instanceof BaseColorWater) {
                return level.setBlock(pos, current, flags);
            }
            if (level.getFluidState(pos).getType() instanceof ColorWaterFluid cwf) {
                return level.setBlock(pos, cwf.getSource(false).createLegacyBlock(), flags);
            }
        }
        return level.setBlock(pos, state, flags);
    }

    @Inject(method = "pickupBlock", at = @At("HEAD"), cancellable = true)
    private void coloredWater$pickupBubbleColumn(LivingEntity player, LevelAccessor level, BlockPos pos, BlockState state, CallbackInfoReturnable<ItemStack> cir) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof WaterloggedColorBlockEntity waterloggedBe && waterloggedBe.hasFluid()) {
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
            ItemStack stack = new ItemStack(waterloggedBe.getFluid().getBucket());
            cir.setReturnValue(stack);
        }
    }

    @Redirect(method = "animateTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addAlwaysVisibleParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"))
    private void colored_water$redirectBubbleColumnParticles(
            Level level, ParticleOptions particle, double x, double y, double z,
            double dx, double dy, double dz) {

        if (particle == ParticleTypes.BUBBLE_COLUMN_UP || particle == ParticleTypes.CURRENT_DOWN) {
            BlockPos pos = BlockPos.containing(x, y, z);
            int color = ColoredWaterUtil.getColoredWaterAt(level, pos);
            if (color == -1) {
                color = ColoredWaterUtil.getColoredWaterAt(level, pos.below());
            }
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


    @Inject(method = "animateTick", at = @At("TAIL"))
    private void coloredWater$animateSparkle(BlockState state, Level level, BlockPos pos, RandomSource random, CallbackInfo ci) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof WaterloggedColorBlockEntity waterloggedBe && waterloggedBe.hasFluid()) {
            ColorWaterFluid fluid = waterloggedBe.getFluid();
            if (fluid.isLuminous() && random.nextFloat() < 0.4F) {
                double x = (double) pos.getX() + random.nextDouble();
                double y = (double) pos.getY() + random.nextDouble();
                double z = (double) pos.getZ() + random.nextDouble();
                level.addParticle(SparkleParticleOptions.of(fluid.getSparkleColor()), x, y, z, 0.01, 0.01, 0.01);
            }
        }
    }
}
