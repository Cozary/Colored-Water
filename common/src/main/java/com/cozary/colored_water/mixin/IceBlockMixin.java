package com.cozary.colored_water.mixin;

import com.cozary.colored_water.block.ColoredWaterBlock;
import com.cozary.colored_water.block.entity.ColoredWaterBlockEntity;
import com.cozary.colored_water.init.ModBlocks;
import com.cozary.colored_water.util.ColoredWaterUtil;
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

    @Inject(method = "melt", at = @At("HEAD"), cancellable = true)
    private void coloredWater$meltIntoColoredWater(BlockState state, Level level, BlockPos pos, CallbackInfo ci) {
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof ColoredWaterBlockEntity coloredBe && coloredBe.hasCustomProperties()) {
            if (level.environmentAttributes().getValue(EnvironmentAttributes.WATER_EVAPORATES, pos)) {
                level.removeBlock(pos, false);
            } else {
                restoreColoredWater(level, pos, coloredBe);
            }
            ci.cancel();
        }
    }

    @Inject(method = "playerDestroy", at = @At("HEAD"), cancellable = true)
    private void coloredWater$destroyIntoColoredWater(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool, CallbackInfo ci) {
        BlockEntity be = blockEntity != null ? blockEntity : level.getBlockEntity(pos);
        if (be instanceof ColoredWaterBlockEntity coloredBe && coloredBe.hasCustomProperties()) {
            if (!EnchantmentHelper.hasTag(tool, EnchantmentTags.PREVENTS_ICE_MELTING)) {
                if (level.environmentAttributes().getValue(EnvironmentAttributes.WATER_EVAPORATES, pos)) {
                    level.removeBlock(pos, false);
                    ci.cancel();
                    return;
                }

                BlockState below = level.getBlockState(pos.below());
                if (below.blocksMotion() || below.liquid()) {
                    restoreColoredWater(level, pos, coloredBe);
                    ci.cancel();
                }
            }
        }
    }

    private static void restoreColoredWater(Level level, BlockPos pos, ColoredWaterBlockEntity oldBe) {
        boolean condensed = oldBe.isCondensed();
        BlockState coloredWaterState = ModBlocks.COLORED_WATER_BLOCK.get().defaultBlockState()
                .setValue(ColoredWaterBlock.CONDENSED, condensed);
        level.setBlockAndUpdate(pos, coloredWaterState);
        level.neighborChanged(pos, coloredWaterState.getBlock(), null);

        BlockEntity newBe = level.getBlockEntity(pos);
        if (newBe instanceof ColoredWaterBlockEntity newColoredBe) {
            ColoredWaterUtil.transferProperties(oldBe, newColoredBe);
        }
    }
}
