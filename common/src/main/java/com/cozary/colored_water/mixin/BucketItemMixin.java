package com.cozary.colored_water.mixin;

import com.cozary.colored_water.block.entity.ColoredWaterBlockEntity;
import com.cozary.colored_water.init.ModFluids;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BucketItem.class)
public abstract class BucketItemMixin {

    @Shadow @Final private Fluid content;

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void coloredWater$useEmptyBucketOnWaterlogged(Level level, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        if (this.content == Fluids.EMPTY) {
            ItemStack heldStack = player.getItemInHand(hand);
            BlockHitResult hitResult = ItemInvoker.invokeGetPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
            if (hitResult.getType() == HitResult.Type.BLOCK) {
                BlockPos pos = hitResult.getBlockPos();
                BlockState state = level.getBlockState(pos);
                if (state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED)) {
                    BlockEntity be = level.getBlockEntity(pos);
                    if (be instanceof ColoredWaterBlockEntity coloredBe) {
                        int color = coloredBe.getColor();
                        boolean condensed = coloredBe.isCondensed();
                        int luminosity = coloredBe.getLuminosity();
                        int alpha = (color >> 24) & 0xFF;

                        level.setBlock(pos, state.setValue(BlockStateProperties.WATERLOGGED, false), 3);
                        player.playSound(SoundEvents.BUCKET_FILL, 1.0F, 1.0F);
                        level.gameEvent(player, GameEvent.FLUID_PICKUP, pos);

                        ItemStack filledBucket = new ItemStack(ModFluids.STILL_COLORED_WATER.get().getBucket());
                        if (color != -1) {
                            filledBucket.set(DataComponents.DYED_COLOR, new DyedItemColor(color & 0xFFFFFF));
                        }

                        CompoundTag tag = new CompoundTag();
                        tag.putBoolean("Condensed", condensed);
                        tag.putInt("Luminosity", luminosity);
                        if (alpha > 0) {
                            tag.putInt("Alpha", alpha);
                        }
                        filledBucket.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));

                        player.awardStat(Stats.ITEM_USED.get((BucketItem) (Object) this));
                        if (!level.isClientSide()) {
                            CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) player, filledBucket);
                        }

                        if (player.hasInfiniteMaterials()) {
                            cir.setReturnValue(InteractionResult.SUCCESS.heldItemTransformedTo(heldStack));
                        } else {
                            ItemStack resultStack = ItemUtils.createFilledResult(heldStack, player, filledBucket, false);
                            cir.setReturnValue(InteractionResult.SUCCESS.heldItemTransformedTo(resultStack));
                        }
                    }
                }
            }
        }
    }
}
