package com.cozary.colored_water.item;

import com.cozary.colored_water.block.entity.WaterloggedColorBlockEntity;
import com.cozary.colored_water.fluids.BaseColorWater;
import com.cozary.colored_water.fluids.ColorWaterFluid;
import com.cozary.colored_water.util.ColoredWaterUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ColorWaterBucketItem extends BucketItem {

    public ColorWaterBucketItem(Supplier<? extends Fluid> fluidSupplier, Properties properties) {
        super(fluidSupplier.get(), properties);
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
                if (this.emptyContents(player, level, blockpos2, blockhitresult)) {
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

    protected boolean canBlockContainFluid(@Nullable Player player, Level worldIn, BlockPos posIn, BlockState blockstate) {
        if (blockstate.hasProperty(BlockStateProperties.WATERLOGGED) && !blockstate.getValue(BlockStateProperties.WATERLOGGED)) {
            return true;
        }
        return blockstate.getBlock() instanceof LiquidBlockContainer && ((LiquidBlockContainer) blockstate.getBlock()).canPlaceLiquid(player, worldIn, posIn, blockstate, this.getContent());
    }

    @Override
    public boolean emptyContents(@Nullable LivingEntity player, Level level, BlockPos pos, @Nullable BlockHitResult result) {
        Fluid fluid = this.getContent();
        if (!(fluid instanceof FlowingFluid)) {
            return false;
        }

        BlockState blockstate = level.getBlockState(pos);

        ItemStack bucketStack = ItemStack.EMPTY;
        if (player instanceof Player p) {
            bucketStack = p.getItemInHand(p.getUsedItemHand());
            if (bucketStack.isEmpty() || !(bucketStack.getItem() instanceof ColorWaterBucketItem)) {
                bucketStack = p.getMainHandItem().getItem() instanceof ColorWaterBucketItem ? p.getMainHandItem() : p.getOffhandItem();
            }
        }

        if (tryRepaintSource(level, pos, blockstate, fluid, bucketStack)) {
            return true;
        }

        Block block = blockstate.getBlock();
        boolean isWaterloggable = blockstate.hasProperty(BlockStateProperties.WATERLOGGED) && !blockstate.getValue(BlockStateProperties.WATERLOGGED);
        boolean canReplace = blockstate.canBeReplaced(fluid);
        boolean canPlace = blockstate.isAir() || isWaterloggable || canReplace || (block instanceof LiquidBlockContainer && ((LiquidBlockContainer) block).canPlaceLiquid(player, level, pos, blockstate, fluid));

        if (!canPlace) {
            return result != null && this.emptyContents(player, level, result.getBlockPos().relative(result.getDirection()), null);
        }

        if (level.environmentAttributes().getValue(EnvironmentAttributes.WATER_EVAPORATES, pos) && fluid.is(FluidTags.WATER)) {
            playEvaporationEffects(level, pos, player);
            return true;
        }

        if (isWaterloggable) {
            level.setBlock(pos, blockstate.setValue(BlockStateProperties.WATERLOGGED, true), 3);
            if (!level.isClientSide()) {
                level.scheduleTick(pos, fluid, fluid.getTickDelay(level));
            }
            applyPropertiesToBlockEntity(bucketStack, level, pos);
            this.playEmptySound(player, level, pos);
            return true;
        }

        if (block instanceof LiquidBlockContainer container && container.canPlaceLiquid(player, level, pos, blockstate, fluid)) {
            container.placeLiquid(level, pos, blockstate, fluid.defaultFluidState());
            applyPropertiesToBlockEntity(bucketStack, level, pos);
            this.playEmptySound(player, level, pos);
            return true;
        }

        if (!level.isClientSide() && canReplace && !blockstate.liquid()) {
            level.destroyBlock(pos, true);
        }

        if (!level.setBlock(pos, fluid.defaultFluidState().createLegacyBlock(), 11) && !blockstate.getFluidState().isSource()) {
            return false;
        }

        applyPropertiesToBlockEntity(bucketStack, level, pos);
        this.playEmptySound(player, level, pos);

        return true;
    }

    @Override
    public void checkExtraContent(@Nullable LivingEntity player, Level level, ItemStack stack, BlockPos pos) {
        applyPropertiesToBlockEntity(stack, level, pos);
    }

    private boolean tryRepaintSource(Level level, BlockPos pos, BlockState state, Fluid fluid, ItemStack bucketStack) {
        if (state.getBlock() instanceof LiquidBlock && (state.getFluidState().is(FluidTags.WATER) || state.getFluidState().getType() instanceof BaseColorWater)) {
            BlockState coloredState = fluid.defaultFluidState().createLegacyBlock();
            level.setBlock(pos, coloredState, 11);
            applyPropertiesToBlockEntity(bucketStack, level, pos);
            this.playEmptySound(null, level, pos);
            return true;
        } else if (state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED)) {
            applyPropertiesToBlockEntity(bucketStack, level, pos);
            this.playEmptySound(null, level, pos);
            return true;
        } else if (state.is(Blocks.BUBBLE_COLUMN)) {
            applyPropertiesToBlockEntity(bucketStack, level, pos);
            this.playEmptySound(null, level, pos);
            return true;
        }
        return false;
    }

    private void applyPropertiesToBlockEntity(ItemStack bucketStack, Level level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        boolean isWaterlogged = state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED);
        if (isWaterlogged || state.is(Blocks.BUBBLE_COLUMN) || state.is(Blocks.FROSTED_ICE)) {
            Fluid fluid = this.getContent();
            if (fluid instanceof ColorWaterFluid cwf) {
                WaterloggedColorBlockEntity be = WaterloggedColorBlockEntity.getOrCreate(level, pos, state);
                if (be != null) {
                    be.setFluid(cwf);
                }
            }
        }
    }

    private void playEvaporationEffects(Level level, BlockPos pos, @Nullable LivingEntity player) {
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();
        level.playSound(player, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.5F, 2.6F + (level.random.nextFloat() - level.random.nextFloat()) * 0.8F);
        for (int l = 0; l < 8; ++l) {
            level.addParticle(ParticleTypes.LARGE_SMOKE, (double) i + Math.random(), (double) j + Math.random(), (double) k + Math.random(), 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> tooltipAdder, TooltipFlag isAdvanced) {
        super.appendHoverText(stack, context, display, tooltipAdder, isAdvanced);

        Fluid fluid = this.getContent();
        if (fluid instanceof ColorWaterFluid colorWaterFluid) {
            boolean isCondensed = colorWaterFluid.isCondensed();
            boolean isLuminous = colorWaterFluid.isLuminous();
            int alpha = isCondensed ? ColoredWaterUtil.CONDENSED_ALPHA : ColoredWaterUtil.DEFAULT_ALPHA;
            int opacityPct = Math.round((alpha * 100.0f) / 255.0f);
            int luminosity = isLuminous ? 15 : 0;
            int colorRgb = colorWaterFluid.getTintColor() & 0xFFFFFF;

            Component typeComponent;
            if (isCondensed && isLuminous) {
                typeComponent = Component.translatable("tooltip.colored_water.type.luminous_condense");
            } else if (isCondensed) {
                typeComponent = Component.translatable("tooltip.colored_water.type.condense");
            } else if (isLuminous) {
                typeComponent = Component.translatable("tooltip.colored_water.type.luminous");
            } else {
                typeComponent = Component.translatable("tooltip.colored_water.type.normal");
            }

            tooltipAdder.accept(Component.translatable("tooltip.colored_water.type",
                    typeComponent.copy().withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.GRAY));

            tooltipAdder.accept(Component.translatable("tooltip.colored_water.opacity",
                    Component.literal(opacityPct + "%").withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.GRAY));

            if (luminosity > 0) {
                tooltipAdder.accept(Component.translatable("tooltip.colored_water.luminosity",
                        Component.literal(String.valueOf(luminosity)).withStyle(ChatFormatting.GOLD)).withStyle(ChatFormatting.GRAY));
            }

            String hexString = String.format("#%06X", colorRgb);
            Component coloredHex = Component.literal(hexString)
                    .withStyle(Style.EMPTY.withColor(colorRgb));

            tooltipAdder.accept(Component.translatable("tooltip.colored_water.color",
                    coloredHex).withStyle(ChatFormatting.GRAY));
        }
    }
}
