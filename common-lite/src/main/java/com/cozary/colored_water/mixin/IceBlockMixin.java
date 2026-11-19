package com.cozary.colored_water.mixin;

import com.cozary.colored_water.block.entity.WaterloggedColorBlockEntity;
import com.cozary.colored_water.fluids.ColorWaterFluid;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.IceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(IceBlock.class)
public abstract class IceBlockMixin {

    private static void restoreColoredWater(Level level, BlockPos blockPosition, ColorWaterFluid fluid) {
        BlockState coloredWaterState = fluid.getSource(false).createLegacyBlock();
        level.setBlockAndUpdate(blockPosition, coloredWaterState);
        level.neighborChanged(blockPosition, coloredWaterState.getBlock(), null);
    }

    @Inject(method = "melt", at = @At("HEAD"), cancellable = true)
    private void coloredWater$meltIntoColoredWater(BlockState state, Level level, BlockPos blockPosition, CallbackInfo callbackInfo) {
        BlockEntity blockEntity = level.getBlockEntity(blockPosition);
        if (blockEntity instanceof WaterloggedColorBlockEntity waterloggedBlockEntity && waterloggedBlockEntity.hasFluid()) {
            ColorWaterFluid fluid = waterloggedBlockEntity.getFluid();
            if (level.environmentAttributes().getValue(EnvironmentAttributes.WATER_EVAPORATES, blockPosition)) {
                level.removeBlock(blockPosition, false);
            } else {
                restoreColoredWater(level, blockPosition, fluid);
            }
            callbackInfo.cancel();
        }
    }

    @Inject(method = "playerDestroy", at = @At("HEAD"), cancellable = true)
    private void coloredWater$destroyIntoColoredWater(Level level, Player player, BlockPos blockPosition, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool, CallbackInfo callbackInfo) {
        BlockEntity targetBlockEntity = blockEntity != null ? blockEntity : level.getBlockEntity(blockPosition);
        if (targetBlockEntity instanceof WaterloggedColorBlockEntity waterloggedBlockEntity && waterloggedBlockEntity.hasFluid()) {
            if (!EnchantmentHelper.hasTag(tool, EnchantmentTags.PREVENTS_ICE_MELTING)) {
                if (level.environmentAttributes().getValue(EnvironmentAttributes.WATER_EVAPORATES, blockPosition)) {
                    level.removeBlock(blockPosition, false);
                    callbackInfo.cancel();
                    return;
                }

                BlockState below = level.getBlockState(blockPosition.below());
                if (below.blocksMotion() || below.liquid()) {
                    restoreColoredWater(level, blockPosition, waterloggedBlockEntity.getFluid());
                    callbackInfo.cancel();
                }
            }
        }
    }
}
