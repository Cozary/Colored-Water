package com.cozary.colored_water.mixin;

import com.cozary.colored_water.block.entity.ColoredWaterBlockEntity;
import com.cozary.colored_water.fluids.ColoredWaterFluid;
import com.cozary.colored_water.init.ModFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Level.class)
public abstract class LevelMixin {

    @Shadow public abstract BlockState getBlockState(BlockPos pos);
    @Shadow public abstract BlockEntity getBlockEntity(BlockPos pos);

    @Inject(method = "getFluidState", at = @At("HEAD"), cancellable = true)
    private void coloredWater$getWaterloggedFluidState(BlockPos pos, CallbackInfoReturnable<FluidState> cir) {
        BlockState state = this.getBlockState(pos);
        boolean isWaterlogged = state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED);
        boolean isBubbleColumn = state.is(Blocks.BUBBLE_COLUMN);
        if (isWaterlogged || isBubbleColumn) {
            BlockEntity be = this.getBlockEntity(pos);
            if (be instanceof ColoredWaterBlockEntity coloredBe) {
                boolean isCondensed = coloredBe.isCondensed();
                FluidState fluidState = ModFluids.STILL_COLORED_WATER.get().getSource(false);
                if (fluidState.hasProperty(ColoredWaterFluid.CONDENSED)) {
                    fluidState = fluidState.setValue(ColoredWaterFluid.CONDENSED, isCondensed);
                }
                cir.setReturnValue(fluidState);
            }
        }
    }
}
