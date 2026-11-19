package com.cozary.colored_water.mixin;

import com.cozary.colored_water.block.entity.WaterloggedColorBlockEntity;
import com.cozary.colored_water.fluids.ColorWaterFluid;
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
    private void coloredWater$captureFluidBeforePlacement(BlockPlaceContext context, CallbackInfoReturnable<InteractionResult> callbackInfoReturnable) {
        Level level = context.getLevel();
        BlockPos blockPosition = context.getClickedPos();
        FluidState fluidState = level.getFluidState(blockPosition);

        if (fluidState.getType() instanceof ColorWaterFluid colorWaterFluid && fluidState.isSource()) {
            CapturedFluidInfo.set(colorWaterFluid);
        } else {
            BlockEntity blockEntity = level.getBlockEntity(blockPosition);
            if (blockEntity instanceof WaterloggedColorBlockEntity waterloggedBlockEntity && waterloggedBlockEntity.hasFluid()) {
                CapturedFluidInfo.set(waterloggedBlockEntity.getFluid());
            } else {
                CapturedFluidInfo.clear();
            }
        }
    }

    @Inject(method = "place", at = @At("RETURN"))
    private void coloredWater$applyFluidAfterPlacement(BlockPlaceContext context, CallbackInfoReturnable<InteractionResult> callbackInfoReturnable) {
        if (callbackInfoReturnable.getReturnValue() == InteractionResult.SUCCESS || callbackInfoReturnable.getReturnValue().consumesAction()) {
            if (CapturedFluidInfo.hasInfo()) {
                ColorWaterFluid fluid = CapturedFluidInfo.get();
                if (fluid != null) {
                    Level level = context.getLevel();
                    BlockPos blockPosition = context.getClickedPos();
                    BlockState state = level.getBlockState(blockPosition);

                    if (state.hasProperty(BlockStateProperties.WATERLOGGED)) {
                        if (!state.getValue(BlockStateProperties.WATERLOGGED)) {
                            state = state.setValue(BlockStateProperties.WATERLOGGED, true);
                            level.setBlock(blockPosition, state, 3);
                        }
                        WaterloggedColorBlockEntity waterloggedBlockEntity = WaterloggedColorBlockEntity.getOrCreate(level, blockPosition, state);
                        if (waterloggedBlockEntity != null) {
                            waterloggedBlockEntity.setFluid(fluid);
                        }
                    }
                }
            }
        }
        CapturedFluidInfo.clear();
    }
}
