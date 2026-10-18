package com.cozary.colored_water.mixin;

import com.cozary.colored_water.client.ColoredWaterFog;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.fog.environment.WaterFogEnvironment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WaterFogEnvironment.class)
public abstract class FabricWaterFogEnvironmentMixin {

    @Inject(method = "setupFog", at = @At("RETURN"))
    private void coloredWater$setupFog(FogData fogData, Camera camera, ClientLevel level, float renderDistance, DeltaTracker deltaTracker, CallbackInfo ci) {
        ColoredWaterFog.modifyFogRender(camera, level, renderDistance, fogData);
    }
}
