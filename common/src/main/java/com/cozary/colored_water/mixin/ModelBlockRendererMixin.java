package com.cozary.colored_water.mixin;

import com.cozary.colored_water.block.ColoredWaterCauldronBlock;
import com.cozary.colored_water.block.entity.ColoredWaterCauldronBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ModelBlockRenderer.class)
public class ModelBlockRendererMixin {

    @Redirect(
        method = "putQuadData",
        at = @At(
            value = "INVOKE",
            target = "Lcom/mojang/blaze3d/vertex/VertexConsumer;putBulkData(Lcom/mojang/blaze3d/vertex/PoseStack$Pose;Lnet/minecraft/client/renderer/block/model/BakedQuad;[FFFFF[II)V"
        )
    )
    private void coloredWater$applyAlphaToCauldronQuad(
        VertexConsumer instance,
        PoseStack.Pose pose,
        BakedQuad quad,
        float[] brightnesses,
        float red,
        float green,
        float blue,
        float alpha,
        int[] packedLight,
        int packedOverlay,
        BlockAndTintGetter level,
        BlockState state,
        BlockPos pos
    ) {
        float targetAlpha = alpha;
        if (state.getBlock() instanceof ColoredWaterCauldronBlock && level != null && pos != null && quad.isTinted()) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof ColoredWaterCauldronBlockEntity coloredBe) {
                int a = coloredBe.getAlpha();
                if (a == 0) a = coloredBe.isCondensed() ? 255 : 180;
                targetAlpha = alpha * (a / 255.0F);
                com.cozary.colored_water.ColoredWater.LOG.info("[ColoredWaterCauldron] Rendering quad at {} with alpha: {} ({} / 255)", pos, targetAlpha, a);
            }
        }
        instance.putBulkData(pose, quad, brightnesses, red, green, blue, targetAlpha, packedLight, packedOverlay);
    }
}
