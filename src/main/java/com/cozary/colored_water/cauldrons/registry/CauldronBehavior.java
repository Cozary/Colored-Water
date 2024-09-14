package com.cozary.colored_water.cauldrons.registry;


import com.cozary.colored_water.cauldrons.block.base.AbstractLeveledCauldronBlock;
import com.cozary.colored_water.cauldrons.util.FluidLevelUtil;
import com.cozary.colored_water.items.ModItems;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.Map;

import static com.cozary.colored_water.cauldrons.block.base.PreBaseCauldronBlock.LEVEL;
import static net.minecraft.world.item.BucketItem.getEmptySuccessItem;

public interface CauldronBehavior extends CauldronInteraction {

    Map<Item, CauldronInteraction> BLACK_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap();
    Map<Item, CauldronInteraction> BLUE_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap();
    Map<Item, CauldronInteraction> BROWN_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap();
    Map<Item, CauldronInteraction> CYAN_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap();
    Map<Item, CauldronInteraction> GRAY_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap();
    Map<Item, CauldronInteraction> GREEN_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap();
    Map<Item, CauldronInteraction> LIGHT_BLUE_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap();
    Map<Item, CauldronInteraction> LIGHT_GRAY_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap();
    Map<Item, CauldronInteraction> LIME_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap();
    Map<Item, CauldronInteraction> MAGENTA_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap();
    Map<Item, CauldronInteraction> ORANGE_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap();
    Map<Item, CauldronInteraction> PINK_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap();
    Map<Item, CauldronInteraction> PURPLE_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap();
    Map<Item, CauldronInteraction> RED_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap();
    Map<Item, CauldronInteraction> WHITE_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap();
    Map<Item, CauldronInteraction> YELLOW_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap();

    static void init() {


        EMPTY.put(ModItems.MAGENTA_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModBlocks.MAGENTA_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        MAGENTA_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.MAGENTA_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (ModBlocks.MAGENTA_WATER_CAULDRON.get().defaultBlockState().getValue(LEVEL) == 1)
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                world.setBlockAndUpdate(pos, ModBlocks.MAGENTA_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, state.getValue(LEVEL) - 1));

            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        MAGENTA_CAULDRON_BEHAVIOR.put(ModItems.MAGENTA_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClientSide) {
                    Item item = stack.getItem();
                    player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(item));
                    AbstractLeveledCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return InteractionResult.sidedSuccess(world.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        });


        EMPTY.put(ModItems.PURPLE_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModBlocks.PURPLE_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        PURPLE_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.PURPLE_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (ModBlocks.PURPLE_WATER_CAULDRON.get().defaultBlockState().getValue(LEVEL) == 1)
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                world.setBlockAndUpdate(pos, ModBlocks.PURPLE_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, state.getValue(LEVEL) - 1));

            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        PURPLE_CAULDRON_BEHAVIOR.put(ModItems.PURPLE_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClientSide) {
                    Item item = stack.getItem();
                    player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(item));
                    AbstractLeveledCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return InteractionResult.sidedSuccess(world.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        });


        EMPTY.put(ModItems.GREEN_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModBlocks.GREEN_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        GREEN_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.GREEN_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (ModBlocks.GREEN_WATER_CAULDRON.get().defaultBlockState().getValue(LEVEL) == 1)
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                world.setBlockAndUpdate(pos, ModBlocks.GREEN_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, state.getValue(LEVEL) - 1));

            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        GREEN_CAULDRON_BEHAVIOR.put(ModItems.GREEN_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClientSide) {
                    Item item = stack.getItem();
                    player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(item));
                    AbstractLeveledCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return InteractionResult.sidedSuccess(world.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        });


        EMPTY.put(ModItems.BLACK_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModBlocks.BLACK_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        BLACK_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.BLACK_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (ModBlocks.BLACK_WATER_CAULDRON.get().defaultBlockState().getValue(LEVEL) == 1)
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                world.setBlockAndUpdate(pos, ModBlocks.BLACK_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, state.getValue(LEVEL) - 1));

            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        BLACK_CAULDRON_BEHAVIOR.put(ModItems.BLACK_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClientSide) {
                    Item item = stack.getItem();
                    player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(item));
                    AbstractLeveledCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return InteractionResult.sidedSuccess(world.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        });


        EMPTY.put(ModItems.BLUE_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModBlocks.BLUE_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        BLUE_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.BLUE_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (ModBlocks.BLUE_WATER_CAULDRON.get().defaultBlockState().getValue(LEVEL) == 1)
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                world.setBlockAndUpdate(pos, ModBlocks.BLUE_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, state.getValue(LEVEL) - 1));

            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        BLUE_CAULDRON_BEHAVIOR.put(ModItems.BLUE_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClientSide) {
                    Item item = stack.getItem();
                    player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(item));
                    AbstractLeveledCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return InteractionResult.sidedSuccess(world.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        });


        EMPTY.put(ModItems.BROWN_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModBlocks.BROWN_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        BROWN_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.BROWN_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (ModBlocks.BROWN_WATER_CAULDRON.get().defaultBlockState().getValue(LEVEL) == 1)
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                world.setBlockAndUpdate(pos, ModBlocks.BROWN_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, state.getValue(LEVEL) - 1));

            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        BROWN_CAULDRON_BEHAVIOR.put(ModItems.BROWN_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClientSide) {
                    Item item = stack.getItem();
                    player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(item));
                    AbstractLeveledCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return InteractionResult.sidedSuccess(world.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        });


        EMPTY.put(ModItems.CYAN_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModBlocks.CYAN_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        CYAN_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.CYAN_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (ModBlocks.CYAN_WATER_CAULDRON.get().defaultBlockState().getValue(LEVEL) == 1)
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                world.setBlockAndUpdate(pos, ModBlocks.CYAN_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, state.getValue(LEVEL) - 1));

            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        CYAN_CAULDRON_BEHAVIOR.put(ModItems.CYAN_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClientSide) {
                    Item item = stack.getItem();
                    player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(item));
                    AbstractLeveledCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return InteractionResult.sidedSuccess(world.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        });


        EMPTY.put(ModItems.GRAY_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModBlocks.GRAY_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        GRAY_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.GRAY_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (ModBlocks.GRAY_WATER_CAULDRON.get().defaultBlockState().getValue(LEVEL) == 1)
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                world.setBlockAndUpdate(pos, ModBlocks.GRAY_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, state.getValue(LEVEL) - 1));

            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        GRAY_CAULDRON_BEHAVIOR.put(ModItems.GRAY_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClientSide) {
                    Item item = stack.getItem();
                    player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(item));
                    AbstractLeveledCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return InteractionResult.sidedSuccess(world.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        });


        EMPTY.put(ModItems.LIGHT_BLUE_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModBlocks.LIGHT_BLUE_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        LIGHT_BLUE_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LIGHT_BLUE_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (ModBlocks.LIGHT_BLUE_WATER_CAULDRON.get().defaultBlockState().getValue(LEVEL) == 1)
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                world.setBlockAndUpdate(pos, ModBlocks.LIGHT_BLUE_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, state.getValue(LEVEL) - 1));

            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        LIGHT_BLUE_CAULDRON_BEHAVIOR.put(ModItems.LIGHT_BLUE_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClientSide) {
                    Item item = stack.getItem();
                    player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(item));
                    AbstractLeveledCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return InteractionResult.sidedSuccess(world.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        });


        EMPTY.put(ModItems.LIGHT_GRAY_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModBlocks.LIGHT_GRAY_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        LIGHT_GRAY_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LIGHT_GRAY_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (ModBlocks.LIGHT_GRAY_WATER_CAULDRON.get().defaultBlockState().getValue(LEVEL) == 1)
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                world.setBlockAndUpdate(pos, ModBlocks.LIGHT_GRAY_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, state.getValue(LEVEL) - 1));

            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        LIGHT_GRAY_CAULDRON_BEHAVIOR.put(ModItems.LIGHT_GRAY_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClientSide) {
                    Item item = stack.getItem();
                    player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(item));
                    AbstractLeveledCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return InteractionResult.sidedSuccess(world.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        });


        EMPTY.put(ModItems.LIME_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModBlocks.LIME_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        LIME_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LIME_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (ModBlocks.LIME_WATER_CAULDRON.get().defaultBlockState().getValue(LEVEL) == 1)
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                world.setBlockAndUpdate(pos, ModBlocks.LIME_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, state.getValue(LEVEL) - 1));

            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        LIME_CAULDRON_BEHAVIOR.put(ModItems.LIME_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClientSide) {
                    Item item = stack.getItem();
                    player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(item));
                    AbstractLeveledCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return InteractionResult.sidedSuccess(world.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        });


        EMPTY.put(ModItems.ORANGE_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModBlocks.ORANGE_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        ORANGE_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.ORANGE_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (ModBlocks.ORANGE_WATER_CAULDRON.get().defaultBlockState().getValue(LEVEL) == 1)
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                world.setBlockAndUpdate(pos, ModBlocks.ORANGE_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, state.getValue(LEVEL) - 1));

            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        ORANGE_CAULDRON_BEHAVIOR.put(ModItems.ORANGE_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClientSide) {
                    Item item = stack.getItem();
                    player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(item));
                    AbstractLeveledCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return InteractionResult.sidedSuccess(world.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        });


        EMPTY.put(ModItems.PINK_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModBlocks.PINK_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        PINK_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.PINK_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (ModBlocks.PINK_WATER_CAULDRON.get().defaultBlockState().getValue(LEVEL) == 1)
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                world.setBlockAndUpdate(pos, ModBlocks.PINK_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, state.getValue(LEVEL) - 1));

            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        PINK_CAULDRON_BEHAVIOR.put(ModItems.PINK_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClientSide) {
                    Item item = stack.getItem();
                    player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(item));
                    AbstractLeveledCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return InteractionResult.sidedSuccess(world.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        });


        EMPTY.put(ModItems.RED_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModBlocks.RED_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        RED_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.RED_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (ModBlocks.RED_WATER_CAULDRON.get().defaultBlockState().getValue(LEVEL) == 1)
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                world.setBlockAndUpdate(pos, ModBlocks.RED_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, state.getValue(LEVEL) - 1));

            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        RED_CAULDRON_BEHAVIOR.put(ModItems.RED_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClientSide) {
                    Item item = stack.getItem();
                    player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(item));
                    AbstractLeveledCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return InteractionResult.sidedSuccess(world.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        });


        EMPTY.put(ModItems.WHITE_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModBlocks.WHITE_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        WHITE_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.WHITE_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (ModBlocks.WHITE_WATER_CAULDRON.get().defaultBlockState().getValue(LEVEL) == 1)
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                world.setBlockAndUpdate(pos, ModBlocks.WHITE_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, state.getValue(LEVEL) - 1));

            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        WHITE_CAULDRON_BEHAVIOR.put(ModItems.WHITE_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClientSide) {
                    Item item = stack.getItem();
                    player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(item));
                    AbstractLeveledCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return InteractionResult.sidedSuccess(world.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        });


        EMPTY.put(ModItems.YELLOW_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModBlocks.YELLOW_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        YELLOW_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.YELLOW_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (ModBlocks.YELLOW_WATER_CAULDRON.get().defaultBlockState().getValue(LEVEL) == 1)
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                world.setBlockAndUpdate(pos, ModBlocks.YELLOW_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, state.getValue(LEVEL) - 1));

            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        YELLOW_CAULDRON_BEHAVIOR.put(ModItems.YELLOW_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClientSide) {
                    Item item = stack.getItem();
                    player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(item));
                    AbstractLeveledCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return InteractionResult.sidedSuccess(world.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        });

    }
}
