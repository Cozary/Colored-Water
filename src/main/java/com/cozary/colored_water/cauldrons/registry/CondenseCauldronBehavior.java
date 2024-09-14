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

public interface CondenseCauldronBehavior extends CauldronInteraction {

    Map<Item, CauldronInteraction> CONDENSE_BLACK_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap();
    Map<Item, CauldronInteraction> CONDENSE_BLUE_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap();
    Map<Item, CauldronInteraction> CONDENSE_BROWN_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap();
    Map<Item, CauldronInteraction> CONDENSE_CYAN_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap();
    Map<Item, CauldronInteraction> CONDENSE_GRAY_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap();
    Map<Item, CauldronInteraction> CONDENSE_GREEN_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap();
    Map<Item, CauldronInteraction> CONDENSE_LIGHT_BLUE_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap();
    Map<Item, CauldronInteraction> CONDENSE_LIGHT_GRAY_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap();
    Map<Item, CauldronInteraction> CONDENSE_LIME_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap();
    Map<Item, CauldronInteraction> CONDENSE_MAGENTA_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap();
    Map<Item, CauldronInteraction> CONDENSE_ORANGE_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap();
    Map<Item, CauldronInteraction> CONDENSE_PINK_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap();
    Map<Item, CauldronInteraction> CONDENSE_PURPLE_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap();
    Map<Item, CauldronInteraction> CONDENSE_RED_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap();
    Map<Item, CauldronInteraction> CONDENSE_WHITE_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap();
    Map<Item, CauldronInteraction> CONDENSE_YELLOW_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap();

    static void init() {


        EMPTY.put(ModItems.CONDENSE_MAGENTA_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModBlocks.CONDENSE_MAGENTA_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        CONDENSE_MAGENTA_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.CONDENSE_MAGENTA_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (ModBlocks.CONDENSE_MAGENTA_WATER_CAULDRON.get().defaultBlockState().getValue(LEVEL) == 1)
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                world.setBlockAndUpdate(pos, ModBlocks.CONDENSE_MAGENTA_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, state.getValue(LEVEL) - 1));

            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        CONDENSE_MAGENTA_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_MAGENTA_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
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


        EMPTY.put(ModItems.CONDENSE_PURPLE_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModBlocks.CONDENSE_PURPLE_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        CONDENSE_PURPLE_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.CONDENSE_PURPLE_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (ModBlocks.CONDENSE_PURPLE_WATER_CAULDRON.get().defaultBlockState().getValue(LEVEL) == 1)
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                world.setBlockAndUpdate(pos, ModBlocks.CONDENSE_PURPLE_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, state.getValue(LEVEL) - 1));

            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        CONDENSE_PURPLE_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_PURPLE_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
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


        EMPTY.put(ModItems.CONDENSE_GREEN_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModBlocks.CONDENSE_GREEN_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        CONDENSE_GREEN_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.CONDENSE_GREEN_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (ModBlocks.CONDENSE_GREEN_WATER_CAULDRON.get().defaultBlockState().getValue(LEVEL) == 1)
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                world.setBlockAndUpdate(pos, ModBlocks.CONDENSE_GREEN_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, state.getValue(LEVEL) - 1));

            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        CONDENSE_GREEN_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_GREEN_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
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


        EMPTY.put(ModItems.CONDENSE_BLACK_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModBlocks.CONDENSE_BLACK_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        CONDENSE_BLACK_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.CONDENSE_BLACK_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (ModBlocks.CONDENSE_BLACK_WATER_CAULDRON.get().defaultBlockState().getValue(LEVEL) == 1)
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                world.setBlockAndUpdate(pos, ModBlocks.CONDENSE_BLACK_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, state.getValue(LEVEL) - 1));

            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        CONDENSE_BLACK_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_BLACK_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
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


        EMPTY.put(ModItems.CONDENSE_BLUE_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModBlocks.CONDENSE_BLUE_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        CONDENSE_BLUE_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.CONDENSE_BLUE_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (ModBlocks.CONDENSE_BLUE_WATER_CAULDRON.get().defaultBlockState().getValue(LEVEL) == 1)
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                world.setBlockAndUpdate(pos, ModBlocks.CONDENSE_BLUE_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, state.getValue(LEVEL) - 1));

            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        CONDENSE_BLUE_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_BLUE_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
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


        EMPTY.put(ModItems.CONDENSE_BROWN_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModBlocks.CONDENSE_BROWN_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        CONDENSE_BROWN_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.CONDENSE_BROWN_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (ModBlocks.CONDENSE_BROWN_WATER_CAULDRON.get().defaultBlockState().getValue(LEVEL) == 1)
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                world.setBlockAndUpdate(pos, ModBlocks.CONDENSE_BROWN_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, state.getValue(LEVEL) - 1));

            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        CONDENSE_BROWN_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_BROWN_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
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


        EMPTY.put(ModItems.CONDENSE_CYAN_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModBlocks.CONDENSE_CYAN_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        CONDENSE_CYAN_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.CONDENSE_CYAN_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (ModBlocks.CONDENSE_CYAN_WATER_CAULDRON.get().defaultBlockState().getValue(LEVEL) == 1)
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                world.setBlockAndUpdate(pos, ModBlocks.CONDENSE_CYAN_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, state.getValue(LEVEL) - 1));

            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        CONDENSE_CYAN_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_CYAN_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
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


        EMPTY.put(ModItems.CONDENSE_GRAY_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModBlocks.CONDENSE_GRAY_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        CONDENSE_GRAY_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.CONDENSE_GRAY_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (ModBlocks.CONDENSE_GRAY_WATER_CAULDRON.get().defaultBlockState().getValue(LEVEL) == 1)
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                world.setBlockAndUpdate(pos, ModBlocks.CONDENSE_GRAY_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, state.getValue(LEVEL) - 1));

            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        CONDENSE_GRAY_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_GRAY_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
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


        EMPTY.put(ModItems.CONDENSE_LIGHT_BLUE_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModBlocks.CONDENSE_LIGHT_BLUE_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        CONDENSE_LIGHT_BLUE_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.CONDENSE_LIGHT_BLUE_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (ModBlocks.CONDENSE_LIGHT_BLUE_WATER_CAULDRON.get().defaultBlockState().getValue(LEVEL) == 1)
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                world.setBlockAndUpdate(pos, ModBlocks.CONDENSE_LIGHT_BLUE_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, state.getValue(LEVEL) - 1));

            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        CONDENSE_LIGHT_BLUE_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_LIGHT_BLUE_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
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


        EMPTY.put(ModItems.CONDENSE_LIGHT_GRAY_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModBlocks.CONDENSE_LIGHT_GRAY_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        CONDENSE_LIGHT_GRAY_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.CONDENSE_LIGHT_GRAY_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (ModBlocks.CONDENSE_LIGHT_GRAY_WATER_CAULDRON.get().defaultBlockState().getValue(LEVEL) == 1)
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                world.setBlockAndUpdate(pos, ModBlocks.CONDENSE_LIGHT_GRAY_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, state.getValue(LEVEL) - 1));

            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        CONDENSE_LIGHT_GRAY_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_LIGHT_GRAY_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
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


        EMPTY.put(ModItems.CONDENSE_LIME_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModBlocks.CONDENSE_LIME_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        CONDENSE_LIME_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.CONDENSE_LIME_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (ModBlocks.CONDENSE_LIME_WATER_CAULDRON.get().defaultBlockState().getValue(LEVEL) == 1)
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                world.setBlockAndUpdate(pos, ModBlocks.CONDENSE_LIME_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, state.getValue(LEVEL) - 1));

            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        CONDENSE_LIME_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_LIME_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
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


        EMPTY.put(ModItems.CONDENSE_ORANGE_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModBlocks.CONDENSE_ORANGE_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        CONDENSE_ORANGE_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.CONDENSE_ORANGE_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (ModBlocks.CONDENSE_ORANGE_WATER_CAULDRON.get().defaultBlockState().getValue(LEVEL) == 1)
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                world.setBlockAndUpdate(pos, ModBlocks.CONDENSE_ORANGE_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, state.getValue(LEVEL) - 1));

            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        CONDENSE_ORANGE_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_ORANGE_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
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


        EMPTY.put(ModItems.CONDENSE_PINK_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModBlocks.CONDENSE_PINK_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        CONDENSE_PINK_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.CONDENSE_PINK_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (ModBlocks.CONDENSE_PINK_WATER_CAULDRON.get().defaultBlockState().getValue(LEVEL) == 1)
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                world.setBlockAndUpdate(pos, ModBlocks.CONDENSE_PINK_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, state.getValue(LEVEL) - 1));

            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        CONDENSE_PINK_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_PINK_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
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


        EMPTY.put(ModItems.CONDENSE_RED_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModBlocks.CONDENSE_RED_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        CONDENSE_RED_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.CONDENSE_RED_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (ModBlocks.CONDENSE_RED_WATER_CAULDRON.get().defaultBlockState().getValue(LEVEL) == 1)
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                world.setBlockAndUpdate(pos, ModBlocks.CONDENSE_RED_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, state.getValue(LEVEL) - 1));

            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        CONDENSE_RED_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_RED_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
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


        EMPTY.put(ModItems.CONDENSE_WHITE_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModBlocks.CONDENSE_WHITE_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        CONDENSE_WHITE_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.CONDENSE_WHITE_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (ModBlocks.CONDENSE_WHITE_WATER_CAULDRON.get().defaultBlockState().getValue(LEVEL) == 1)
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                world.setBlockAndUpdate(pos, ModBlocks.CONDENSE_WHITE_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, state.getValue(LEVEL) - 1));

            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        CONDENSE_WHITE_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_WHITE_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
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


        EMPTY.put(ModItems.CONDENSE_YELLOW_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModBlocks.CONDENSE_YELLOW_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        CONDENSE_YELLOW_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.CONDENSE_YELLOW_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (ModBlocks.CONDENSE_YELLOW_WATER_CAULDRON.get().defaultBlockState().getValue(LEVEL) == 1)
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                world.setBlockAndUpdate(pos, ModBlocks.CONDENSE_YELLOW_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, state.getValue(LEVEL) - 1));

            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        CONDENSE_YELLOW_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_YELLOW_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
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
