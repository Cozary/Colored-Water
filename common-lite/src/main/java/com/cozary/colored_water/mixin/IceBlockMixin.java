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

    private static void restoreColoredWater(Level level, BlockPos pos, ColorWaterFluid fluid) {
        BlockState coloredWaterState = fluid.getSource(false).createLegacyBlock();
        level.setBlockAndUpdate(pos, coloredWaterState);
        level.neighborChanged(pos, coloredWaterState.getBlock(), null);
    }

    @Inject(method = "melt", at = @At("HEAD"), cancellable = true)
    private void coloredWater$meltIntoColoredWater(BlockState state, Level level, BlockPos pos, CallbackInfo ci) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof WaterloggedColorBlockEntity waterloggedBe && waterloggedBe.hasFluid()) {
            ColorWaterFluid fluid = waterloggedBe.getFluid();
            if (level.environmentAttributes().getValue(EnvironmentAttributes.WATER_EVAPORATES, pos)) {
                level.removeBlock(pos, false);
            } else {
                restoreColoredWater(level, pos, fluid);
            }
            ci.cancel();
        }
    }

    @Inject(method = "playerDestroy", at = @At("HEAD"), cancellable = true)
    private void coloredWater$destroyIntoColoredWater(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool, CallbackInfo ci) {
        BlockEntity targetBlockEntity = blockEntity != null ? blockEntity : level.getBlockEntity(pos);
        if (targetBlockEntity instanceof WaterloggedColorBlockEntity waterloggedBe && waterloggedBe.hasFluid()) {
            if (!EnchantmentHelper.hasTag(tool, EnchantmentTags.PREVENTS_ICE_MELTING)) {
                if (level.environmentAttributes().getValue(EnvironmentAttributes.WATER_EVAPORATES, pos)) {
                    level.removeBlock(pos, false);
                    ci.cancel();
                    return;
                }

                BlockState below = level.getBlockState(pos.below());
                if (below.blocksMotion() || below.liquid()) {
                    restoreColoredWater(level, pos, waterloggedBe.getFluid());
                    ci.cancel();
                }
            }
        }
    }
}
