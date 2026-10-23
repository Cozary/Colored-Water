package com.cozary.colored_water.mixin;

import com.cozary.colored_water.fluids.BaseColorWater;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.WaterlilyBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WaterlilyBlock.class)
public abstract class WaterlilyBlockMixin {

    @Unique
    private static final TagKey<Fluid> COLORED_WATER$SUPPORTS_LILY_PAD =
            TagKey.create(Registries.FLUID, Identifier.fromNamespaceAndPath("minecraft", "supports_lily_pad"));

    @Inject(method = "mayPlaceOn", at = @At("RETURN"), cancellable = true)
    private void coloredWater$mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue()) {
            FluidState fluidState = level.getFluidState(pos);
            FluidState aboveFluidState = level.getFluidState(pos.above());
            if (aboveFluidState.isEmpty() && (fluidState.getType() instanceof BaseColorWater || fluidState.is(COLORED_WATER$SUPPORTS_LILY_PAD))) {
                cir.setReturnValue(true);
            }
        }
    }
}
