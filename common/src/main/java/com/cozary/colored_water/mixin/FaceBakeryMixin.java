package com.cozary.colored_water.mixin;

import com.mojang.blaze3d.platform.Transparency;
import net.minecraft.client.resources.model.cuboid.CuboidFace;
import net.minecraft.client.resources.model.cuboid.FaceBakery;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Ensures quads using colored water textures are dynamically assigned to the TRANSLUCENT chunk layer.
 */
@Mixin(FaceBakery.class)
public abstract class FaceBakeryMixin {

    @Unique
    private static final Identifier COLORED_WATER_STILL = Identifier.fromNamespaceAndPath("colored_water", "block/water_still");

    @Inject(method = "computeMaterialTransparency", at = @At("HEAD"), cancellable = true)
    private static void coloredWater$forceTranslucentForColoredWater(Material.Baked material, CuboidFace.UVs uvs, CallbackInfoReturnable<Transparency> callbackInfoReturnable) {
        if (!material.forceTranslucent() && COLORED_WATER_STILL.equals(material.sprite().contents().name())) {
            callbackInfoReturnable.setReturnValue(Transparency.TRANSLUCENT);
        }
    }
}
