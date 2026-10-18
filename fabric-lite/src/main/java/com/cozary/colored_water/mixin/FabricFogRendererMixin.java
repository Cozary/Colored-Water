package com.cozary.colored_water.mixin;

import com.cozary.colored_water.client.ColoredWaterFog;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.fog.FogRenderer;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FogRenderer.class)
public abstract class FabricFogRendererMixin {

    @Inject(method = "computeFogColor", at = @At("RETURN"), cancellable = true)
    private void coloredWater$computeFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistanceChunks, float bossColorModifier, CallbackInfoReturnable<Vector4f> cir) {
        Vector4f customColor = ColoredWaterFog.getFogColor(camera, partialTick, level, cir.getReturnValue());
        if (customColor != null && customColor != cir.getReturnValue()) {
            cir.setReturnValue(customColor);
        }
    }
}
