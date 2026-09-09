package com.cozary.colored_water.mixin;

import com.cozary.colored_water.block.ColoredWaterBlock;
import com.cozary.colored_water.block.ColoredWaterCauldronBlock;
import com.cozary.colored_water.block.entity.ColoredWaterBlockEntity;
import com.cozary.colored_water.block.entity.ColoredWaterCauldronBlockEntity;
import com.cozary.colored_water.fluids.BaseColorWater;
import com.cozary.colored_water.init.ModBlocks;
import com.cozary.colored_water.init.ModFluids;
import com.cozary.colored_water.init.ModParticles;
import com.cozary.colored_water.particles.ColorParticleOptions;
import com.cozary.colored_water.particles.SparkleParticleOptions;
import com.cozary.colored_water.util.ColoredWaterUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BubbleColumnBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
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
        if (blockState.getBlock() instanceof ColoredWaterBlock && blockState.getValue(ColoredWaterBlock.LEVEL) == 0) {
            cir.setReturnValue(true);
        }
    }

    @Redirect(
        method = "updateColumn(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/state/BlockState;)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/LevelAccessor;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z"
        )
    )
    private static boolean coloredWater$redirectSetBlockInUpdateColumn(LevelAccessor level, BlockPos pos, BlockState state, int flags) {
        if (state.is(Blocks.WATER)) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof ColoredWaterBlockEntity coloredBe && coloredBe.hasCustomProperties()) {
                BlockState coloredState = ModBlocks.COLORED_WATER_BLOCK.get().defaultBlockState()
                        .setValue(ColoredWaterBlock.CONDENSED, coloredBe.isCondensed())
                        .setValue(ColoredWaterBlock.LIGHT_LEVEL, coloredBe.getLuminosity());
                return level.setBlock(pos, coloredState, flags);
            }
        }
        return level.setBlock(pos, state, flags);
    }

    @Inject(method = "pickupBlock", at = @At("HEAD"), cancellable = true)
    private void coloredWater$pickupBubbleColumn(LivingEntity player, LevelAccessor level, BlockPos pos, BlockState state, CallbackInfoReturnable<ItemStack> cir) {
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof ColoredWaterBlockEntity coloredBe && coloredBe.hasCustomProperties()) {
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
            ItemStack stack = ColoredWaterUtil.createBucketStack(coloredBe);
            cir.setReturnValue(stack);
        }
    }

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
            int color = ColoredWaterUtil.getColoredWaterAt(level, pos);
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
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof ColoredWaterBlockEntity coloredBe && coloredBe.hasCustomProperties()) {
            int luminosity = coloredBe.getLuminosity();
            if (luminosity > 0) {
                float chance = (luminosity / 15.0F) * 0.4F;
                if (random.nextFloat() < chance) {
                    int color = coloredBe.getColor();
                    double x = (double) pos.getX() + random.nextDouble();
                    double y = (double) pos.getY() + random.nextDouble();
                    double z = (double) pos.getZ() + random.nextDouble();
                    level.addParticle(new SparkleParticleOptions(color), x, y, z, 0.01, 0.01, 0.01);
                }
            }
        }
    }
}
