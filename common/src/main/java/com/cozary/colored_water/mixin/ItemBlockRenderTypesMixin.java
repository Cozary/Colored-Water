package com.cozary.colored_water.mixin;

import com.cozary.colored_water.block.ColoredWaterBlock;
import com.cozary.colored_water.fluids.ColoredWaterFluid;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemBlockRenderTypes.class)
public class ItemBlockRenderTypesMixin {

    @Inject(method = "getRenderLayer(Lnet/minecraft/world/level/material/FluidState;)Lnet/minecraft/client/renderer/chunk/ChunkSectionLayer;", at = @At("HEAD"), cancellable = true)
    private static void coloredWater$getDynamicFluidRenderLayer(FluidState state, CallbackInfoReturnable<ChunkSectionLayer> cir) {
        if (state.getType() instanceof ColoredWaterFluid) {
            cir.setReturnValue(ChunkSectionLayer.TRANSLUCENT);
        }
    }

    @Inject(method = "getChunkRenderType(Lnet/minecraft/world/level/block/state/BlockState;)Lnet/minecraft/client/renderer/chunk/ChunkSectionLayer;", at = @At("HEAD"), cancellable = true)
    private static void coloredWater$getDynamicChunkRenderType(BlockState state, CallbackInfoReturnable<ChunkSectionLayer> cir) {
        if (state.getBlock() instanceof ColoredWaterBlock) {
            cir.setReturnValue(ChunkSectionLayer.TRANSLUCENT);
        }
    }
}
