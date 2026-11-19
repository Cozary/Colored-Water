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
import net.minecraft.world.level.block.Block;
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

    @Inject(method = "canOccupy", at = @At("HEAD"), cancellable = true)
    private static void coloredWater$canOccupy(Block block, BlockState blockState, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (blockState.getFluidState().getType() instanceof BaseColorWater && blockState.getFluidState().isSource()) {
            callbackInfoReturnable.setReturnValue(true);
        }
    }

    @Redirect(method = "updateColumn(Lnet/minecraft/world/level/block/Block;Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/state/BlockState;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/LevelAccessor;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z"))
    private static boolean coloredWater$redirectSetBlockInUpdateColumn(LevelAccessor level, BlockPos blockPosition, BlockState state, int flags) {
        if (state.is(Blocks.WATER)) {
            BlockEntity blockEntity = level.getBlockEntity(blockPosition);
            if (blockEntity instanceof WaterloggedColorBlockEntity waterloggedBlockEntity && waterloggedBlockEntity.hasFluid()) {
                BlockState coloredState = waterloggedBlockEntity.getFluid().getSource(false).createLegacyBlock();
                return level.setBlock(blockPosition, coloredState, flags);
            }
            BlockState current = level.getBlockState(blockPosition);
            if (current.getBlock() instanceof LiquidBlock && current.getFluidState().getType() instanceof BaseColorWater) {
                return level.setBlock(blockPosition, current, flags);
            }
            if (level.getFluidState(blockPosition).getType() instanceof ColorWaterFluid colorWaterFluid) {
                return level.setBlock(blockPosition, colorWaterFluid.getSource(false).createLegacyBlock(), flags);
            }
        }
        return level.setBlock(blockPosition, state, flags);
    }

    @Inject(method = "pickupBlock", at = @At("HEAD"), cancellable = true)
    private void coloredWater$pickupBubbleColumn(LivingEntity player, LevelAccessor level, BlockPos blockPosition, BlockState state, CallbackInfoReturnable<ItemStack> callbackInfoReturnable) {
        BlockEntity blockEntity = level.getBlockEntity(blockPosition);
        if (blockEntity instanceof WaterloggedColorBlockEntity waterloggedBlockEntity && waterloggedBlockEntity.hasFluid()) {
            level.setBlock(blockPosition, Blocks.AIR.defaultBlockState(), 11);
            ItemStack stack = new ItemStack(waterloggedBlockEntity.getFluid().getBucket());
            callbackInfoReturnable.setReturnValue(stack);
        }
    }

    @Redirect(method = "animateTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addAlwaysVisibleParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"))
    private void colored_water$redirectBubbleColumnParticles(
            Level level, ParticleOptions particle, double x, double y, double z,
            double dx, double dy, double dz) {

        if (particle == ParticleTypes.BUBBLE_COLUMN_UP || particle == ParticleTypes.CURRENT_DOWN) {
            BlockPos blockPosition = BlockPos.containing(x, y, z);
            int color = ColoredWaterUtil.getColoredWaterAt(level, blockPosition);
            if (color == -1) {
                color = ColoredWaterUtil.getColoredWaterAt(level, blockPosition.below());
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
    private void coloredWater$animateSparkle(BlockState state, Level level, BlockPos blockPosition, RandomSource random, CallbackInfo callbackInfo) {
        BlockEntity blockEntity = level.getBlockEntity(blockPosition);
        if (blockEntity instanceof WaterloggedColorBlockEntity waterloggedBlockEntity && waterloggedBlockEntity.hasFluid()) {
            ColorWaterFluid fluid = waterloggedBlockEntity.getFluid();
            if (fluid.isLuminous() && random.nextFloat() < 0.4F) {
                double x = (double) blockPosition.getX() + random.nextDouble();
                double y = (double) blockPosition.getY() + random.nextDouble();
                double z = (double) blockPosition.getZ() + random.nextDouble();
                level.addParticle(SparkleParticleOptions.of(fluid.getSparkleColor()), x, y, z, 0.01, 0.01, 0.01);
            }
        }
    }
}
