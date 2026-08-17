package com.cozary.colored_water.mixin;

import com.cozary.colored_water.block.ColoredWaterBlock;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.world.level.levelgen.blockpredicates.MatchingBlocksPredicate")
public abstract class MatchingBlocksPredicateMixin {

    @Shadow @Final private HolderSet<Block> blocks;

    @Inject(method = "test(Lnet/minecraft/world/level/block/state/BlockState;)Z", at = @At("HEAD"), cancellable = true)
    private void coloredWater$matchColoredWaterBlock(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.getBlock() instanceof ColoredWaterBlock) {
            if (this.blocks.contains(Blocks.WATER.builtInRegistryHolder())) {
                cir.setReturnValue(true);
            }
        }
    }
}
