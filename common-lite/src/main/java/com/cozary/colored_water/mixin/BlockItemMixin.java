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
    private void coloredWater$captureFluidBeforePlacement(BlockPlaceContext context, CallbackInfoReturnable<InteractionResult> cir) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        FluidState fluidState = level.getFluidState(pos);

        if (fluidState.getType() instanceof ColorWaterFluid cwf && fluidState.isSource()) {
            CapturedFluidInfo.set(cwf);
        } else {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof WaterloggedColorBlockEntity waterloggedBe && waterloggedBe.hasFluid()) {
                CapturedFluidInfo.set(waterloggedBe.getFluid());
            } else {
                CapturedFluidInfo.clear();
            }
        }
    }

    @Inject(method = "place", at = @At("RETURN"))
    private void coloredWater$applyFluidAfterPlacement(BlockPlaceContext context, CallbackInfoReturnable<InteractionResult> cir) {
        if (cir.getReturnValue() == InteractionResult.SUCCESS || cir.getReturnValue().consumesAction()) {
            if (CapturedFluidInfo.hasInfo()) {
                ColorWaterFluid fluid = CapturedFluidInfo.get();
                if (fluid != null) {
                    Level level = context.getLevel();
                    BlockPos pos = context.getClickedPos();
                    BlockState state = level.getBlockState(pos);

                    if (state.hasProperty(BlockStateProperties.WATERLOGGED)) {
                        if (!state.getValue(BlockStateProperties.WATERLOGGED)) {
                            state = state.setValue(BlockStateProperties.WATERLOGGED, true);
                            level.setBlock(pos, state, 3);
                        }
                        WaterloggedColorBlockEntity be = WaterloggedColorBlockEntity.getOrCreate(level, pos, state);
                        if (be != null) {
                            be.setFluid(fluid);
                        }
                    }
                }
            }
        }
        CapturedFluidInfo.clear();
    }
}
