package com.cozary.colored_water.mixin;

import com.cozary.colored_water.util.ColoredWaterUtil;
import com.mojang.blaze3d.vertex.QuadInstance;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.client.renderer.block.BlockQuadOutput;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.resources.model.geometry.BakedQuad;
import net.minecraft.core.BlockPos;
import net.minecraft.util.ARGB;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Applies custom opacity (alpha) to tinted cauldron water quads during model rendering.
 */
@Mixin(ModelBlockRenderer.class)
public class ModelBlockRendererMixin {

    @Shadow
    private QuadInstance quadInstance;

    @Inject(method = "putQuadWithTint", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/block/BlockQuadOutput;put(FFFLnet/minecraft/client/resources/model/geometry/BakedQuad;Lcom/mojang/blaze3d/vertex/QuadInstance;)V"))
    private void coloredWater$applyAlphaToCauldronQuad(BlockQuadOutput output, float x, float y, float z, BlockAndTintGetter level, BlockState state, BlockPos blockPosition, BakedQuad quad, CallbackInfo callbackInfo) {
        if (state != null && quad.materialInfo().tintIndex() != -1 && ColoredWaterUtil.isModCauldron(state.getBlock())) {
            int a = ColoredWaterUtil.isCondensedCauldron(state.getBlock()) ? ColoredWaterUtil.CONDENSED_ALPHA : ColoredWaterUtil.DEFAULT_ALPHA;
            for (int i = 0; i < 4; i++) {
                int c = this.quadInstance.getColor(i);
                this.quadInstance.setColor(i, ARGB.color(a, ARGB.red(c), ARGB.green(c), ARGB.blue(c)));
            }
        }
    }
}
