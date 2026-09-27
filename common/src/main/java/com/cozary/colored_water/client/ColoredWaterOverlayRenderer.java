package com.cozary.colored_water.client;

import com.cozary.colored_water.ColoredWater;
import com.cozary.colored_water.block.entity.ColoredWaterBlockEntity;
import com.cozary.colored_water.util.ColoredWaterUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.joml.Matrix4f;

/**
 * Renders the custom colored water screen overlay with color tinting and opacity
 * based on the fluid's alpha.
 */
public class ColoredWaterOverlayRenderer {

    public static final Identifier COLORED_WATER_OVERLAY_LOCATION =
            Identifier.fromNamespaceAndPath(ColoredWater.MOD_ID, "textures/block/water_overlay.png");

    public static boolean renderOverlay(Minecraft minecraft, PoseStack poseStack, MultiBufferSource bufferSource) {
        if (minecraft.player == null || minecraft.level == null) {
            return false;
        }

        BlockPos eyePos = BlockPos.containing(minecraft.player.getX(), minecraft.player.getEyeY(), minecraft.player.getZ());
        int color = ColoredWaterUtil.getColoredWaterAt(minecraft.level, eyePos);
        if (color == -1) {
            eyePos = BlockPos.containing(minecraft.player.getX(), minecraft.player.getEyeY() - 0.1111111, minecraft.player.getZ());
            color = ColoredWaterUtil.getColoredWaterAt(minecraft.level, eyePos);
        }

        if (color == -1) {
            return false;
        }

        float brightness = LightTexture.getBrightness(minecraft.player.level().dimensionType(), minecraft.player.level().getMaxLocalRawBrightness(eyePos));

        BlockEntity be = minecraft.player.level().getBlockEntity(eyePos);
        if (be == null) {
            be = minecraft.player.level().getBlockEntity(BlockPos.containing(minecraft.player.getX(), minecraft.player.getEyeY() - 0.1111111, minecraft.player.getZ()));
        }

        int r = ColoredWaterUtil.getRed(color);
        int g = ColoredWaterUtil.getGreen(color);
        int b = ColoredWaterUtil.getBlue(color);
        int alpha = ColoredWaterUtil.getAlpha(color);
        int luminosity = be instanceof ColoredWaterBlockEntity cwbe ? cwbe.getLuminosity() : 0;

        if (alpha == 0) {
            boolean condensed = be instanceof ColoredWaterBlockEntity cwbe && cwbe.isCondensed();
            alpha = condensed ? ColoredWaterUtil.CONDENSED_ALPHA : ColoredWaterUtil.DEFAULT_ALPHA;
        }

        float normalizedAlpha = (float) alpha / 255.0F;
        float minOverlayAlpha = 0.05F;
        float normalOverlayAlpha = 0.10F;
        float maxOverlayAlpha = 0.22F;

        float overlayAlpha;
        if (normalizedAlpha <= 0.706F) {
            float t = normalizedAlpha / 0.706F;
            overlayAlpha = Mth.lerp(t, minOverlayAlpha, normalOverlayAlpha);
        } else {
            float t = (normalizedAlpha - 0.706F) / (1.0F - 0.706F);
            overlayAlpha = Mth.lerp(t, normalOverlayAlpha, maxOverlayAlpha);
        }

        float lumBonus = (float) Mth.clamp(luminosity, 0, 15) / 15.0F;
        float effectiveLight = Math.max(brightness, lumBonus);
        float light = Mth.clamp(0.45F + 0.55F * effectiveLight, 0.0F, 1.0F);

        float red = (r / 255.0F) * light;
        float green = (g / 255.0F) * light;
        float blue = (b / 255.0F) * light;

        int vertexColor = ARGB.colorFromFloat(overlayAlpha, red, green, blue);

        float f7 = -minecraft.player.getYRot() / 64.0F;
        float f8 = minecraft.player.getXRot() / 64.0F;
        Matrix4f matrix4f = poseStack.last().pose();
        VertexConsumer vertexconsumer = bufferSource.getBuffer(RenderTypes.blockScreenEffect(COLORED_WATER_OVERLAY_LOCATION));
        vertexconsumer.addVertex(matrix4f, -1.0F, -1.0F, -0.5F).setUv(4.0F + f7, 4.0F + f8).setColor(vertexColor);
        vertexconsumer.addVertex(matrix4f, 1.0F, -1.0F, -0.5F).setUv(0.0F + f7, 4.0F + f8).setColor(vertexColor);
        vertexconsumer.addVertex(matrix4f, 1.0F, 1.0F, -0.5F).setUv(0.0F + f7, 0.0F + f8).setColor(vertexColor);
        vertexconsumer.addVertex(matrix4f, -1.0F, 1.0F, -0.5F).setUv(4.0F + f7, 0.0F + f8).setColor(vertexColor);

        return true;
    }
}
