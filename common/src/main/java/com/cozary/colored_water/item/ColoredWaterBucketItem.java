package com.cozary.colored_water.item;

import com.cozary.colored_water.block.entity.ColoredWaterBlockEntity;
import com.cozary.colored_water.init.ModFluids;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.Nullable;

//TODO clean this class
public class ColoredWaterBucketItem extends BucketItem {

    public ColoredWaterBucketItem(Properties properties) {
        super(ModFluids.STILL_COLORED_WATER.get(), properties);

    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        BlockHitResult blockhitresult = getPlayerPOVHitResult(
                level, player, this.getContent() == Fluids.EMPTY ? ClipContext.Fluid.SOURCE_ONLY : ClipContext.Fluid.NONE
        );
        if (blockhitresult.getType() == HitResult.Type.MISS) {
            return InteractionResult.PASS;
        } else if (blockhitresult.getType() != HitResult.Type.BLOCK) {
            return InteractionResult.PASS;
        } else {
            BlockPos blockpos = blockhitresult.getBlockPos();
            Direction direction = blockhitresult.getDirection();
            BlockPos blockpos1 = blockpos.relative(direction);
            if (!level.mayInteract(player, blockpos) || !player.mayUseItemAt(blockpos1, direction, itemstack)) {
                return InteractionResult.FAIL;
            } else if (this.getContent() == Fluids.EMPTY) {
                BlockState blockstate1 = level.getBlockState(blockpos);
                if (blockstate1.getBlock() instanceof BucketPickup bucketpickup) {
                    ItemStack itemstack3 = bucketpickup.pickupBlock(player, level, blockpos, blockstate1);
                    if (!itemstack3.isEmpty()) {
                        player.awardStat(Stats.ITEM_USED.get(this));
                        bucketpickup.getPickupSound().ifPresent(p_150709_ -> player.playSound(p_150709_, 1.0F, 1.0F));
                        level.gameEvent(player, GameEvent.FLUID_PICKUP, blockpos);
                        ItemStack itemstack2 = ItemUtils.createFilledResult(itemstack, player, itemstack3);
                        if (!level.isClientSide()) {
                            CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) player, itemstack3);
                        }

                        return InteractionResult.SUCCESS.heldItemTransformedTo(itemstack2);
                    }
                }

                return InteractionResult.FAIL;
            } else {
                BlockState blockstate = level.getBlockState(blockpos);
                BlockPos blockpos2 = canBlockContainFluid(player, level, blockpos, blockstate) ? blockpos : blockpos1;
                if (this.emptyContents(player, level, blockpos2, blockhitresult)) { // Note: emptyContents signature in BucketItem doesn't take itemstack in some versions, checking super
                    this.checkExtraContent(player, level, itemstack, blockpos2);
                    if (player instanceof ServerPlayer) {
                        CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer) player, blockpos2, itemstack);
                    }

                    player.awardStat(Stats.ITEM_USED.get(this));
                    ItemStack itemstack1 = ItemUtils.createFilledResult(itemstack, player, getEmptySuccessItem(itemstack, player));
                    return InteractionResult.SUCCESS.heldItemTransformedTo(itemstack1);
                } else {
                    return InteractionResult.FAIL;
                }
            }
        }
    }

    protected boolean canBlockContainFluid(@org.jspecify.annotations.Nullable Player player, Level worldIn, BlockPos posIn, BlockState blockstate) {
        return blockstate.getBlock() instanceof LiquidBlockContainer && ((LiquidBlockContainer) blockstate.getBlock()).canPlaceLiquid(player, worldIn, posIn, blockstate, ModFluids.STILL_COLORED_WATER.get());
    }

    @Override
    public boolean emptyContents(@Nullable LivingEntity player, Level level, BlockPos pos, @Nullable BlockHitResult result) {
        Fluid fluid = this.getContent();
        if (!(fluid instanceof FlowingFluid)) {
            return false;
        }

        BlockState blockstate = level.getBlockState(pos);

        if (tryRepaintSource(player, level, pos, blockstate, fluid)) {
            return true;
        }

        Block block = blockstate.getBlock();
        boolean canReplace = blockstate.canBeReplaced(fluid);
        boolean canPlace = blockstate.isAir() || canReplace || (block instanceof LiquidBlockContainer && ((LiquidBlockContainer) block).canPlaceLiquid(player, level, pos, blockstate, fluid));

        if (!canPlace) {
            return result != null && this.emptyContents(player, level, result.getBlockPos().relative(result.getDirection()), null);
        }

        if (level.environmentAttributes().getValue(EnvironmentAttributes.WATER_EVAPORATES, pos) && fluid.is(net.minecraft.tags.FluidTags.WATER)) {
            playEvaporationEffects(level, pos, player);
            return true;
        }

        if (block instanceof LiquidBlockContainer container && container.canPlaceLiquid(player, level, pos, blockstate, fluid)) {
            container.placeLiquid(level, pos, blockstate, fluid.defaultFluidState());
            this.playEmptySound(player, level, pos);
            return true;
        }

        if (!level.isClientSide() && canReplace && !blockstate.liquid()) {
            level.destroyBlock(pos, true);
        }

        if (!level.setBlock(pos, fluid.defaultFluidState().createLegacyBlock(), 11) && !blockstate.getFluidState().isSource()) {
            return false;
        }

        this.playEmptySound(player, level, pos);

        applyColorFromBucket(player, level, pos);

        return true;
    }


    private boolean tryRepaintSource(@Nullable LivingEntity player, Level level, BlockPos pos, BlockState state, Fluid fluid) {
        if (state.getBlock() instanceof LiquidBlock && state.getFluidState().isSource() && state.getFluidState().getType().isSame(fluid)) {
            if (player != null) {
                ItemStack bucketStack = getHeldBucket(player);
                if (bucketStack != null) {
                    DyedItemColor dyedColor = bucketStack.get(DataComponents.DYED_COLOR);
                    if (dyedColor != null) {
                        BlockEntity be = level.getBlockEntity(pos);
                        if (be instanceof ColoredWaterBlockEntity coloredBe) {
                            // Only update if color is different
                            if (coloredBe.getColor() != dyedColor.rgb()) {
                                coloredBe.markAsPlacedByBucket();
                                coloredBe.setColor(dyedColor.rgb(), null, true);
                                this.playEmptySound(player, level, pos);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }


    private void applyColorFromBucket(@Nullable LivingEntity player, Level level, BlockPos pos) {
        if (player == null) return;
        ItemStack bucketStack = getHeldBucket(player);

        if (bucketStack != null) {
            DyedItemColor dyedColor = bucketStack.get(DataComponents.DYED_COLOR);
            if (dyedColor != null) {
                BlockEntity be = level.getBlockEntity(pos);
                if (be instanceof ColoredWaterBlockEntity coloredBe) {
                    coloredBe.markAsPlacedByBucket();
                    coloredBe.setColor(dyedColor.rgb(), null, true);
                }
            }
        }
    }


    @Nullable
    private ItemStack getHeldBucket(LivingEntity player) {
        ItemStack main = player.getMainHandItem();
        if (main.getItem() instanceof ColoredWaterBucketItem) return main;
        ItemStack off = player.getOffhandItem();
        if (off.getItem() instanceof ColoredWaterBucketItem) return off;
        return null;
    }

    private void playEvaporationEffects(Level level, BlockPos pos, @Nullable LivingEntity player) {
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();
        level.playSound(player, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.5F, 2.6F + (level.random.nextFloat() - level.random.nextFloat()) * 0.8F);
        for (int l = 0; l < 8; ++l) {
            level.addParticle(net.minecraft.core.particles.ParticleTypes.LARGE_SMOKE, (double) i + Math.random(), (double) j + Math.random(), (double) k + Math.random(), 0.0D, 0.0D, 0.0D);
        }
    }
}