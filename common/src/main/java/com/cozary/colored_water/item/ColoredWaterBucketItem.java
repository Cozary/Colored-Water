package com.cozary.colored_water.item;

import com.cozary.colored_water.block.entity.ColoredWaterBlockEntity;
import com.cozary.colored_water.init.ModFluids;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
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
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.item.component.TooltipDisplay;
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

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Handles placement, painting of existing sources, and displays tooltips with the type and color.
 */
public class ColoredWaterBucketItem extends BucketItem {

    public ColoredWaterBucketItem(Supplier<? extends Fluid> fluidSupplier, Properties properties) {
        super(fluidSupplier.get(), properties);
    }

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

    protected boolean canBlockContainFluid(@org.jspecify.annotations.Nullable Player player, Level worldIn, BlockPos posIn, BlockState blockstate) {
        return blockstate.getBlock() instanceof LiquidBlockContainer && ((LiquidBlockContainer) blockstate.getBlock()).canPlaceLiquid(player, worldIn, posIn, blockstate, this.getContent());
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

        if (level.environmentAttributes().getValue(EnvironmentAttributes.WATER_EVAPORATES, pos) && fluid.is(FluidTags.WATER)) {
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
                    applyPropertiesToBE(bucketStack, level, pos);
                    this.playEmptySound(player, level, pos);
                    return true;
                }
            }
        }
        return false;
    }

    private void applyColorFromBucket(@Nullable LivingEntity player, Level level, BlockPos pos) {
        if (player == null) return;
        ItemStack bucketStack = getHeldBucket(player);
        if (bucketStack != null) {
            applyPropertiesToBE(bucketStack, level, pos);
        }
    }

    private void applyPropertiesToBE(ItemStack bucketStack, Level level, BlockPos pos) {
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof ColoredWaterBlockEntity coloredBe) {
            coloredBe.markAsPlacedByBucket();

            DyedItemColor dyedColor = bucketStack.get(DataComponents.DYED_COLOR);
            CustomData customData = bucketStack.get(DataComponents.CUSTOM_DATA);

            int rgb = dyedColor != null ? dyedColor.rgb() : 0x3F76E4;
            boolean condensed = false;
            int luminosity = 0;
            int alpha = 0;

            if (customData != null) {
                CompoundTag tag = customData.copyTag();
                condensed = tag.getBooleanOr("Condensed", false);
                luminosity = tag.getIntOr("Luminosity", 0);
                alpha = tag.getIntOr("Alpha", 0);
            }

            if (alpha == 0) alpha = condensed ? 255 : 180;
            int fullColor = (alpha << 24) | (rgb & 0x00FFFFFF);

            coloredBe.setCondensed(condensed);
            coloredBe.setLuminosity(luminosity);
            coloredBe.setColor(fullColor, null, true);
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
            level.addParticle(ParticleTypes.LARGE_SMOKE, (double) i + Math.random(), (double) j + Math.random(), (double) k + Math.random(), 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> tooltipAdder, TooltipFlag isAdvanced) {
        super.appendHoverText(stack, context, display, tooltipAdder, isAdvanced);

        boolean isCondensed = false;
        int luminosity = 0;
        int alpha = 0;

        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        if (customData != null) {
            CompoundTag tag = customData.copyTag();
            isCondensed = tag.getBooleanOr("Condensed", false);
            luminosity = tag.getIntOr("Luminosity", 0);
            alpha = tag.getIntOr("Alpha", 0);
        }

        if (alpha == 0) alpha = isCondensed ? 255 : 180;
        int opacityPct = Math.round((alpha * 100.0f) / 255.0f);

        String typeName;
        if (isCondensed && luminosity > 0) {
            typeName = "Luminous Condense";
        } else if (isCondensed) {
            typeName = "Condense";
        } else if (luminosity > 0) {
            typeName = "Luminous";
        } else {
            typeName = "Normal";
        }

        tooltipAdder.accept(Component.literal("Type: ").withStyle(ChatFormatting.GRAY)
                .append(Component.literal(typeName).withStyle(ChatFormatting.WHITE)));

        tooltipAdder.accept(Component.literal("Opacity: ").withStyle(ChatFormatting.GRAY)
                .append(Component.literal(opacityPct + "%").withStyle(ChatFormatting.WHITE)));

        if (luminosity > 0) {
            tooltipAdder.accept(Component.literal("Luminosity: ").withStyle(ChatFormatting.GRAY)
                    .append(Component.literal(String.valueOf(luminosity)).withStyle(ChatFormatting.GOLD)));
        }

        DyedItemColor dyedColor = stack.get(DataComponents.DYED_COLOR);
        if (dyedColor != null) {
            int colorRgb = dyedColor.rgb() & 0xFFFFFF;
            String hexString = String.format("#%06X", colorRgb);
            Component coloredHex = Component.literal(hexString)
                    .withStyle(Style.EMPTY.withColor(colorRgb));

            tooltipAdder.accept(Component.literal("Color: ").withStyle(ChatFormatting.GRAY)
                    .append(coloredHex));
        }
    }
}