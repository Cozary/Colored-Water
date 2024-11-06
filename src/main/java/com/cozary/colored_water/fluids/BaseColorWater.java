package com.cozary.colored_water.fluids;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;

import javax.annotation.Nullable;
import java.util.Optional;

public abstract class BaseColorWater extends FlowingFluid {

    @Override
    public void animateTick(Level pLevel, BlockPos pPos, FluidState pState, RandomSource pRandom) {
        if (!pState.isSource() && !(Boolean) pState.getValue(FALLING)) {
            if (pRandom.nextInt(64) == 0) {
                pLevel.playLocalSound((double) pPos.getX() + 0.5, (double) pPos.getY() + 0.5, (double) pPos.getZ() + 0.5, SoundEvents.WATER_AMBIENT, SoundSource.BLOCKS, pRandom.nextFloat() * 0.25F + 0.75F, pRandom.nextFloat() + 0.5F, false);
            }
        } else if (pRandom.nextInt(10) == 0) {
            pLevel.addParticle(ParticleTypes.UNDERWATER, (double) pPos.getX() + pRandom.nextDouble(), (double) pPos.getY() + pRandom.nextDouble(), (double) pPos.getZ() + pRandom.nextDouble(), 0.0, 0.0, 0.0);
        }

    }

    @Override
    @Nullable
    public ParticleOptions getDripParticle() {
        return ParticleTypes.DRIPPING_WATER;
    }

    @Override
    protected boolean canConvertToSource(Level pLevel) {
        return pLevel.getGameRules().getBoolean(GameRules.RULE_WATER_SOURCE_CONVERSION);
    }

    @Override
    protected void beforeDestroyingBlock(LevelAccessor pLevel, BlockPos pPos, BlockState pState) {
        BlockEntity blockentity = pState.hasBlockEntity() ? pLevel.getBlockEntity(pPos) : null;
        Block.dropResources(pState, pLevel, pPos, blockentity);
    }

    @Override
    public int getSlopeFindDistance(LevelReader pLevel) {
        return 4;
    }

    @Override
    public boolean isSame(Fluid pFluid) {
        return pFluid == getSource() || pFluid == getFlowing();
    }

    @Override
    public int getDropOff(LevelReader pLevel) {
        return 1;
    }

    @Override
    public int getTickDelay(LevelReader pLevel) {
        return 5;
    }

    @Override
    public boolean canBeReplacedWith(FluidState pFluidState, BlockGetter pBlockReader, BlockPos pPos, Fluid pFluid, Direction pDirection) {
        return pDirection == Direction.DOWN && !pFluid.is(FluidTags.WATER);
    }

    @Override
    protected float getExplosionResistance() {
        return 100.0F;
    }

    @Override
    public Optional<SoundEvent> getPickupSound() {
        return Optional.of(SoundEvents.BUCKET_FILL);
    }
}
