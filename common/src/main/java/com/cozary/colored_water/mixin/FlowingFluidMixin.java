package com.cozary.colored_water.mixin;

import com.cozary.colored_water.block.ColoredWaterBlock;
import com.cozary.colored_water.block.entity.ColoredWaterBlockEntity;
import com.cozary.colored_water.init.ModBlocks;
import com.cozary.colored_water.init.ModFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.tags.FluidTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FlowingFluid.class)
public abstract class FlowingFluidMixin {

    @Shadow protected abstract void beforeDestroyingBlock(LevelAccessor level, BlockPos pos, BlockState state);
    @Shadow protected static int getLegacyLevel(FluidState fluidState) {
        throw new AssertionError();
    }

    @Inject(method = "spreadTo", at = @At("HEAD"), cancellable = true)
    private void coloredWater$interceptWaterloggedSpread(LevelAccessor level, BlockPos pos, BlockState blockState, Direction direction, FluidState fluidState, CallbackInfo ci) {
        if (!(level instanceof ServerLevel serverLevel)) return;

        BlockPos sourcePos = pos.relative(direction.getOpposite());
        BlockEntity sourceBe = serverLevel.getBlockEntity(sourcePos);

        if (sourceBe instanceof ColoredWaterBlockEntity sBe) {
            int colorToPass = sBe.getColor();
            int lumToPass = sBe.getLuminosity();
            boolean condToPass = sBe.isCondensed();

            if (colorToPass == -1) return;

            // Handle lava collision
            FluidState targetFluidState = level.getFluidState(pos);
            if (targetFluidState.is(FluidTags.LAVA)) {
                if (blockState.getBlock() instanceof LiquidBlock) {
                    if (targetFluidState.isSource()) {
                        level.setBlock(pos, Blocks.OBSIDIAN.defaultBlockState(), 3);
                    } else {
                        level.setBlock(pos, Blocks.COBBLESTONE.defaultBlockState(), 3);
                    }
                }
                level.levelEvent(1501, pos, 0);
                ci.cancel();
                return;
            }

            if (blockState.getBlock() instanceof LiquidBlockContainer liquidblockcontainer) {
                FluidState customFluidState = fluidState.isSource()
                        ? ModFluids.STILL_COLORED_WATER.get().getSource(false)
                        : ModFluids.FLOWING_COLORED_WATER.get().getFlowing(fluidState.getAmount(), fluidState.hasProperty(FlowingFluid.FALLING) && fluidState.getValue(FlowingFluid.FALLING));
                if (customFluidState.hasProperty(ColoredWaterBlock.CONDENSED)) {
                    customFluidState = customFluidState.setValue(ColoredWaterBlock.CONDENSED, condToPass);
                }
                liquidblockcontainer.placeLiquid(level, pos, blockState, customFluidState);
            } else {
                if (!blockState.isAir()) {
                    this.beforeDestroyingBlock(level, pos, blockState);
                }

                BlockState targetState = ModBlocks.COLORED_WATER_BLOCK.get().defaultBlockState()
                        .setValue(ColoredWaterBlock.LEVEL, getLegacyLevel(fluidState))
                        .setValue(ColoredWaterBlock.CONDENSED, condToPass);

                serverLevel.setBlock(pos, targetState, 3);
            }

            // Transfer color, condensed, luminosity to target ColoredWaterBlockEntity
            ColoredWaterBlockEntity tBe = ColoredWaterBlockEntity.getOrCreate(serverLevel, pos, serverLevel.getBlockState(pos));
            tBe.setLuminosity(lumToPass);
            tBe.setCondensed(condToPass);
            tBe.setColor(colorToPass, sourcePos, true);
            tBe.updateBlockStateProps();
            tBe.propagateColor();
            tBe.markUpdated();

            ci.cancel();
        }
    }
}
