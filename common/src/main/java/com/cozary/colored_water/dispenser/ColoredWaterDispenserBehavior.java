package com.cozary.colored_water.dispenser;

import com.cozary.colored_water.block.ColoredWaterBlock;
import com.cozary.colored_water.block.entity.ColoredWaterBlockEntity;
import com.cozary.colored_water.init.ModItems;
import com.cozary.colored_water.util.ColoredWaterUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;

/**
 * Dispenser behaviors for Colored Water.
 * <p>
 * - Dispensing a colored water bucket places the fluid in the world and returns an empty bucket.
 * - Dispensing an empty bucket on a colored water block picks up the fluid and returns a colored water bucket. easy
 */
public class ColoredWaterDispenserBehavior {

    public static void register() {
        DispenserBlock.registerBehavior(ModItems.COLORED_WATER_BUCKET.get(), new DefaultDispenseItemBehavior() {
            private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

            @Override
            public ItemStack execute(BlockSource source, ItemStack stack) {
                if (stack.getItem() instanceof BucketItem bucketItem) {
                    BlockPos blockpos = source.pos().relative(source.state().getValue(DispenserBlock.FACING));
                    Level level = source.level();
                    if (bucketItem.emptyContents(null, level, blockpos, null)) {
                        bucketItem.checkExtraContent(null, level, stack, blockpos);
                        return this.consumeWithRemainder(source, stack, new ItemStack(Items.BUCKET));
                    }
                }
                return this.defaultDispenseItemBehavior.dispense(source, stack);
            }
        });

        DispenseItemBehavior vanillaBucketBehavior = DispenserBlock.DISPENSER_REGISTRY.get(Items.BUCKET);

        DispenserBlock.registerBehavior(Items.BUCKET, new DefaultDispenseItemBehavior() {
            @Override
            public ItemStack execute(BlockSource source, ItemStack stack) {
                BlockPos blockpos = source.pos().relative(source.state().getValue(DispenserBlock.FACING));
                Level level = source.level();
                BlockState blockstate = level.getBlockState(blockpos);

                if (blockstate.hasProperty(BlockStateProperties.WATERLOGGED) && blockstate.getValue(BlockStateProperties.WATERLOGGED)) {
                    BlockEntity blockEntity = level.getBlockEntity(blockpos);
                    if (blockEntity instanceof ColoredWaterBlockEntity coloredWaterBlockEntity && coloredWaterBlockEntity.hasCustomProperties()) {
                        ItemStack filledStack = ColoredWaterUtil.createBucketStack(coloredWaterBlockEntity);
                        level.setBlock(blockpos, blockstate.setValue(BlockStateProperties.WATERLOGGED, false), 3);
                        level.gameEvent(null, GameEvent.FLUID_PICKUP, blockpos);
                        return this.consumeWithRemainder(source, stack, filledStack);
                    }
                }

                if (blockstate.getBlock() instanceof ColoredWaterBlock || blockstate.is(Blocks.BUBBLE_COLUMN)) {
                    BucketPickup bucketPickup = (BucketPickup) blockstate.getBlock();
                    ItemStack filledStack = bucketPickup.pickupBlock(null, level, blockpos, blockstate);
                    if (!filledStack.isEmpty()) {
                        level.gameEvent(null, GameEvent.FLUID_PICKUP, blockpos);
                        return this.consumeWithRemainder(source, stack, filledStack);
                    }
                }
                return vanillaBucketBehavior != null ? vanillaBucketBehavior.dispense(source, stack)
                        : super.execute(source, stack);
            }
        });
    }
}
