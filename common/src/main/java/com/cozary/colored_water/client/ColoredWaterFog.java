package com.cozary.colored_water.client;

import com.cozary.colored_water.block.entity.ColoredWaterBlockEntity;
import com.cozary.colored_water.util.ColoredWaterUtil;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector4f;

/**
 * Unified client-side fog color and distance calculations for Colored Water across all mod loaders.
 */
public final class ColoredWaterFog {

    private ColoredWaterFog() {
    }

    @Nullable
    public static Vector4f getFogColor(Camera camera, float partialTick, ClientLevel level, @Nullable Vector4f defaultColor) {
        BlockPos pos = camera.blockPosition();
        int color = ColoredWaterUtil.getColoredWaterAt(level, pos);
        BlockPos activePos = pos;
        if (color == -1) {
            activePos = BlockPos.containing(camera.position().x, camera.position().y - 0.1, camera.position().z);
            color = ColoredWaterUtil.getColoredWaterAt(level, activePos);
        }

        if (color != -1) {
            BlockEntity be = level.getBlockEntity(activePos);
            int luminosity = be instanceof ColoredWaterBlockEntity cwbe ? cwbe.getLuminosity() : 0;
            float ambientLight = LightTexture.getBrightness(level.dimensionType(), level.getMaxLocalRawBrightness(activePos));
            float lumFactor = (float) Mth.clamp(luminosity, 0, 15) / 15.0F;
            float effectiveLight = Math.max(ambientLight, lumFactor);
            float waterVision = 1.0F;
            if (camera.entity() instanceof LocalPlayer player) {
                waterVision = player.getWaterVision();
            }

            float baseR = ColoredWaterUtil.getRed(color) / 255.0F;
            float baseG = ColoredWaterUtil.getGreen(color) / 255.0F;
            float baseB = ColoredWaterUtil.getBlue(color) / 255.0F;
            float maxC = Math.max(baseR, Math.max(baseG, baseB));

            float lightScale = 0.45F + 0.55F * effectiveLight;
            float visionBrightness = Mth.lerp(waterVision, 0.35F, 1.0F);
            float fogFactor = lightScale * visionBrightness;
            fogFactor = Math.min(fogFactor + lumFactor * 0.25F, 1.0F);

            float r = baseR * fogFactor;
            float g = baseG * fogFactor;
            float b = baseB * fogFactor;

            if (maxC > 0.0F) {
                float boost = 1.0F / maxC;
                r = Mth.lerp(waterVision * 0.5F, r, baseR * boost * fogFactor);
                g = Mth.lerp(waterVision * 0.5F, g, baseG * boost * fogFactor);
                b = Mth.lerp(waterVision * 0.5F, b, baseB * boost * fogFactor);
            }

            float alpha = defaultColor != null ? defaultColor.w : 1.0F;
            return new Vector4f(r, g, b, alpha);
        }

        return defaultColor;
    }

    public static boolean modifyFogRender(Camera camera, ClientLevel level, float renderDistance, FogData fogData) {
        BlockPos pos = camera.blockPosition();
        BlockPos activePos = pos;
        int color = ColoredWaterUtil.getColoredWaterAt(level, pos);
        if (color == -1) {
            activePos = BlockPos.containing(camera.position().x, camera.position().y - 0.1, camera.position().z);
            color = ColoredWaterUtil.getColoredWaterAt(level, activePos);
        }

        if (color == -1) {
            return false;
        }

        BlockEntity be = level.getBlockEntity(activePos);
        int luminosity = be instanceof ColoredWaterBlockEntity cwbe ? cwbe.getLuminosity() : 0;
        boolean condensed = be instanceof ColoredWaterBlockEntity cwbe && cwbe.isCondensed();
        int alpha = ColoredWaterUtil.getAlpha(color);
        if (alpha == 0) {
            alpha = condensed ? ColoredWaterUtil.CONDENSED_ALPHA : ColoredWaterUtil.DEFAULT_ALPHA;
        }

        float normalizedAlpha = (float) alpha / 255.0F;
        float baseDistance;
        if (normalizedAlpha <= 0.706F) {
            float t = normalizedAlpha / 0.706F;
            baseDistance = Mth.lerp(t, 128.0F, 96.0F);
        } else {
            float t = (normalizedAlpha - 0.706F) / (1.0F - 0.706F);
            baseDistance = Mth.lerp(t, 96.0F, 16.0F);
        }

        float lightMultiplier = 1.0F + ((float) Mth.clamp(luminosity, 0, 15) / 15.0F);
        float maxDistance = baseDistance * lightMultiplier;

        float waterVision = 1.0F;
        if (camera.entity() instanceof LocalPlayer player) {
            waterVision = player.getWaterVision();
        }

        float alphaProgress = Mth.clamp((normalizedAlpha - 0.706F) / (1.0F - 0.706F), 0.0F, 1.0F);
        float minVisionRatio = Mth.lerp(alphaProgress, 0.25F, 0.40F);
        float visionProgress = Mth.lerp(waterVision, minVisionRatio, 1.0F);

        float horizon = fogData.renderDistanceEnd > 0.0F ? fogData.renderDistanceEnd : renderDistance * 16.0F;
        float end = Math.min(maxDistance * visionProgress, horizon);

        float start = Mth.lerp(alphaProgress, -8.0F, -4.0F);
        fogData.environmentalStart = start;
        fogData.environmentalEnd = end;
        fogData.skyEnd = end;
        fogData.cloudEnd = end;
        return true;
    }
}
