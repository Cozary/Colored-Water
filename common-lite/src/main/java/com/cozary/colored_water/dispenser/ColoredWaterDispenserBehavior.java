package com.cozary.colored_water.dispenser;

import com.cozary.colored_water.block.entity.WaterloggedColorBlockEntity;
import com.cozary.colored_water.fluids.BaseColorWater;
import com.cozary.colored_water.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
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

import java.util.function.Supplier;


public class ColoredWaterDispenserBehavior {

    public static void register() {
        DefaultDispenseItemBehavior bucketPlaceBehavior = new DefaultDispenseItemBehavior() {
            private final DefaultDispenseItemBehavior fallback = new DefaultDispenseItemBehavior();

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
                return this.fallback.dispense(source, stack);
            }
        };

        for (Supplier<Item> itemSupplier : ModItems.REGISTERED_ITEMS) {
            DispenserBlock.registerBehavior(itemSupplier.get(), bucketPlaceBehavior);
        }

        DispenseItemBehavior vanillaBucketBehavior = DispenserBlock.DISPENSER_REGISTRY.get(Items.BUCKET);

        DispenserBlock.registerBehavior(Items.BUCKET, new DefaultDispenseItemBehavior() {
            @Override
            public ItemStack execute(BlockSource source, ItemStack stack) {
                BlockPos blockpos = source.pos().relative(source.state().getValue(DispenserBlock.FACING));
                Level level = source.level();
                BlockState blockstate = level.getBlockState(blockpos);
                if (blockstate.hasProperty(BlockStateProperties.WATERLOGGED) && blockstate.getValue(BlockStateProperties.WATERLOGGED)) {
                    BlockEntity be = level.getBlockEntity(blockpos);
                    if (be instanceof WaterloggedColorBlockEntity waterloggedBe && waterloggedBe.hasFluid()) {
                        ItemStack filledStack = new ItemStack(waterloggedBe.getFluid().getBucket());
                        level.setBlock(blockpos, blockstate.setValue(BlockStateProperties.WATERLOGGED, false), 3);
                        level.gameEvent(null, GameEvent.FLUID_PICKUP, blockpos);
                        return this.consumeWithRemainder(source, stack, filledStack);
                    }
                }
                if (blockstate.getFluidState().getType() instanceof BaseColorWater || blockstate.is(Blocks.BUBBLE_COLUMN)) {
                    if (blockstate.getBlock() instanceof BucketPickup bucketPickup) {
                        ItemStack filledStack = bucketPickup.pickupBlock(null, level, blockpos, blockstate);
                        if (!filledStack.isEmpty()) {
                            level.gameEvent(null, GameEvent.FLUID_PICKUP, blockpos);
                            return this.consumeWithRemainder(source, stack, filledStack);
                        }
                    }
                }
                return vanillaBucketBehavior != null ? vanillaBucketBehavior.dispense(source, stack)
                        : super.execute(source, stack);
            }
        });
    }
}
