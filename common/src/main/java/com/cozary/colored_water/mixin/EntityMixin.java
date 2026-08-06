package com.cozary.colored_water.mixin;

import com.cozary.colored_water.block.ColoredWaterBlock;
import com.cozary.colored_water.block.ColoredWaterCauldronBlock;
import com.cozary.colored_water.fluids.BaseColorWater;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Inject(method = "isInWaterOrRain", at = @At("HEAD"), cancellable = true)
    private void colored_water$isInWaterOrRain(CallbackInfoReturnable<Boolean> cir) {
        Entity entity = (Entity) (Object) this;
        Level level = entity.level();
        if (level != null) {
            BlockPos pos = entity.blockPosition();
            BlockState state = level.getBlockState(pos);
            if (state.getBlock() instanceof ColoredWaterBlock || state.getBlock() instanceof ColoredWaterCauldronBlock) {
                cir.setReturnValue(true);
                return;
            }
            BlockPos eyePos = BlockPos.containing(entity.getEyePosition());
            BlockState eyeState = level.getBlockState(eyePos);
            if (eyeState.getBlock() instanceof ColoredWaterBlock || eyeState.getBlock() instanceof ColoredWaterCauldronBlock) {
                cir.setReturnValue(true);
                return;
            }
            FluidState fluidState = level.getFluidState(pos);
            if (fluidState.getType() instanceof BaseColorWater) {
                cir.setReturnValue(true);
            }
        }
    }
}
