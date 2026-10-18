package com.cozary.colored_water.mixin;

import com.cozary.colored_water.block.entity.WaterloggedColorBlockEntity;
import com.cozary.colored_water.fluids.ColorWaterFluid;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Level.class)
public abstract class LevelMixin {

    @Shadow
    public abstract BlockEntity getBlockEntity(BlockPos pos);

    @Inject(method = "getFluidState", at = @At("RETURN"), cancellable = true)
    private void coloredWater$getWaterloggedFluidState(BlockPos pos, CallbackInfoReturnable<FluidState> cir) {
        FluidState original = cir.getReturnValue();
        if (original != null && original.is(Fluids.WATER)) {
            BlockEntity blockEntity = this.getBlockEntity(pos);
            if (blockEntity instanceof WaterloggedColorBlockEntity waterloggedBe && waterloggedBe.hasFluid()) {
                ColorWaterFluid fluid = waterloggedBe.getFluid();
                cir.setReturnValue(fluid.getSource(false));
            }
        }
    }
}
