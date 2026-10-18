package com.cozary.colored_water.mixin;

import com.cozary.colored_water.client.ColoredWaterOverlayRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ScreenEffectRenderer.class)
public abstract class ScreenEffectRendererMixin {

    @Inject(method = "renderWater", at = @At("HEAD"), cancellable = true)
    private static void colored_water$renderWaterOverlay(Minecraft minecraft, PoseStack poseStack, MultiBufferSource bufferSource, CallbackInfo ci) {
        if (ColoredWaterOverlayRenderer.renderOverlay(minecraft, poseStack, bufferSource)) {
            ci.cancel();
        }
    }
}
