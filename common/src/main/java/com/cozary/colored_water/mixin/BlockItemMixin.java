package com.cozary.colored_water.mixin;

import com.cozary.colored_water.block.entity.ColoredWaterBlockEntity;
import com.cozary.colored_water.fluids.BaseColorWater;
import com.cozary.colored_water.util.CapturedFluidInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockItem.class)
public abstract class BlockItemMixin {

    @Inject(method = "place", at = @At("HEAD"))
    private void coloredWater$captureFluidBeforePlacement(BlockPlaceContext context, CallbackInfoReturnable<InteractionResult> cir) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        FluidState fluidState = level.getFluidState(pos);

        if (fluidState.getType() instanceof BaseColorWater) {
            BlockEntity be = level.getBlockEntity(pos);
            int color = -1;
            boolean condensed = false;
            int luminosity = 0;

            if (be instanceof ColoredWaterBlockEntity coloredBe && coloredBe.hasCustomProperties()) {
                color = coloredBe.getColor();
                condensed = coloredBe.isCondensed();
                luminosity = coloredBe.getLuminosity();
            }

            CapturedFluidInfo.set(color, condensed, luminosity);
        } else {
            CapturedFluidInfo.clear();
        }
    }

    @Inject(method = "place", at = @At("RETURN"))
    private void coloredWater$applyFluidAfterPlacement(BlockPlaceContext context, CallbackInfoReturnable<InteractionResult> cir) {
        if (cir.getReturnValue() == InteractionResult.SUCCESS || cir.getReturnValue().consumesAction()) {
            if (CapturedFluidInfo.hasInfo()) {
                CapturedFluidInfo.Info info = CapturedFluidInfo.get();
                if (info != null) {
                    Level level = context.getLevel();
                    BlockPos pos = context.getClickedPos();
                    BlockState state = level.getBlockState(pos);

                    if (state.hasProperty(BlockStateProperties.WATERLOGGED)) {
                        if (!state.getValue(BlockStateProperties.WATERLOGGED)) {
                            state = state.setValue(BlockStateProperties.WATERLOGGED, true);
                            level.setBlock(pos, state, 3);
                        }
                        ColoredWaterBlockEntity coloredBe = ColoredWaterBlockEntity.getOrCreate(level, pos, state);
                        if (coloredBe != null) {
                            coloredBe.markAsPlacedByBucket();
                            coloredBe.setCondensed(info.condensed());
                            coloredBe.setLuminosity(info.luminosity());
                            if (info.color() != -1) {
                                coloredBe.setColor(info.color(), null, true);
                            }
                        }
                    }
                }
            }
        }
        CapturedFluidInfo.clear();
    }
}
