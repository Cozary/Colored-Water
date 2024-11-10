package com.cozary.colored_water.cauldrons.behaviour;


import com.cozary.colored_water.cauldrons.ColorAbstractCauldronBlock;
import com.cozary.colored_water.cauldrons.util.FluidLevelUtil;
import com.cozary.colored_water.init.ModCauldrons;
import com.cozary.colored_water.init.ModItems;
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

import static net.minecraft.world.item.BucketItem.getEmptySuccessItem;
import static net.minecraft.world.level.block.LayeredCauldronBlock.LEVEL;


public interface ColorCauldronBehavior extends CauldronInteraction {

    InteractionMap BLACK_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("black_cauldron_behavior");
    InteractionMap BLUE_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("blue_cauldron_behavior");
    InteractionMap BROWN_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("brown_cauldron_behavior");
    InteractionMap CYAN_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("cyan_cauldron_behavior");
    InteractionMap GRAY_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("gray_cauldron_behavior");
    InteractionMap GREEN_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("green_cauldron_behavior");
    InteractionMap LIGHT_BLUE_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("light_blue_cauldron_behavior");
    InteractionMap LIGHT_GRAY_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("light_gray_cauldron_behavior");
    InteractionMap LIME_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("lime_cauldron_behavior");
    InteractionMap MAGENTA_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("magenta_cauldron_behavior");
    InteractionMap ORANGE_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("orange_cauldron_behavior");
    InteractionMap PINK_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("pink_cauldron_behavior");
    InteractionMap PURPLE_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("purple_cauldron_behavior");
    InteractionMap RED_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("red_cauldron_behavior");
    InteractionMap WHITE_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("white_cauldron_behavior");
    InteractionMap YELLOW_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("yellow_cauldron_behavior");


    static void init() {

        CauldronInteraction.EMPTY.map().put(ModItems.MAGENTA_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModCauldrons.MAGENTA_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        MAGENTA_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.MAGENTA_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.getValue(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockAndUpdate(pos, ModCauldrons.MAGENTA_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                }

            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        MAGENTA_CAULDRON_BEHAVIOR.map().put(ModItems.MAGENTA_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClientSide) {
                    Item item = stack.getItem();
                    player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(item));
                    ColorAbstractCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return InteractionResult.sidedSuccess(world.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        });


        CauldronInteraction.EMPTY.map().put(ModItems.PURPLE_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModCauldrons.PURPLE_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        PURPLE_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.PURPLE_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.getValue(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockAndUpdate(pos, ModCauldrons.PURPLE_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                }

            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        PURPLE_CAULDRON_BEHAVIOR.map().put(ModItems.PURPLE_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClientSide) {
                    Item item = stack.getItem();
                    player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(item));
                    ColorAbstractCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return InteractionResult.sidedSuccess(world.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        });


        CauldronInteraction.EMPTY.map().put(ModItems.GREEN_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModCauldrons.GREEN_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        GREEN_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.GREEN_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.getValue(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockAndUpdate(pos, ModCauldrons.GREEN_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                }

            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        GREEN_CAULDRON_BEHAVIOR.map().put(ModItems.GREEN_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClientSide) {
                    Item item = stack.getItem();
                    player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(item));
                    ColorAbstractCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return InteractionResult.sidedSuccess(world.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        });


        CauldronInteraction.EMPTY.map().put(ModItems.BLACK_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModCauldrons.BLACK_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        BLACK_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.BLACK_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.getValue(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockAndUpdate(pos, ModCauldrons.BLACK_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                }
            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        BLACK_CAULDRON_BEHAVIOR.map().put(ModItems.BLACK_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClientSide) {
                    Item item = stack.getItem();
                    player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(item));
                    ColorAbstractCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return InteractionResult.sidedSuccess(world.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        });


        BLUE_CAULDRON_BEHAVIOR.map().put(ModItems.BLUE_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModCauldrons.BLUE_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        CauldronInteraction.EMPTY.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.BLUE_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.getValue(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockAndUpdate(pos, ModCauldrons.BLUE_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                }

            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        BLUE_CAULDRON_BEHAVIOR.map().put(ModItems.BLUE_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClientSide) {
                    Item item = stack.getItem();
                    player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(item));
                    ColorAbstractCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return InteractionResult.sidedSuccess(world.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        });


        CauldronInteraction.EMPTY.map().put(ModItems.BROWN_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModCauldrons.BROWN_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        BROWN_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.BROWN_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.getValue(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockAndUpdate(pos, ModCauldrons.BROWN_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                }

            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        BROWN_CAULDRON_BEHAVIOR.map().put(ModItems.BROWN_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClientSide) {
                    Item item = stack.getItem();
                    player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(item));
                    ColorAbstractCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return InteractionResult.sidedSuccess(world.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        });


        CauldronInteraction.EMPTY.map().put(ModItems.CYAN_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModCauldrons.CYAN_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        CYAN_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.CYAN_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.getValue(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockAndUpdate(pos, ModCauldrons.CYAN_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                }

            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        CYAN_CAULDRON_BEHAVIOR.map().put(ModItems.CYAN_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClientSide) {
                    Item item = stack.getItem();
                    player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(item));
                    ColorAbstractCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return InteractionResult.sidedSuccess(world.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        });


        CauldronInteraction.EMPTY.map().put(ModItems.GRAY_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModCauldrons.GRAY_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        GRAY_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.GRAY_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.getValue(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockAndUpdate(pos, ModCauldrons.GRAY_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                }

            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        GRAY_CAULDRON_BEHAVIOR.map().put(ModItems.GRAY_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClientSide) {
                    Item item = stack.getItem();
                    player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(item));
                    ColorAbstractCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return InteractionResult.sidedSuccess(world.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        });


        CauldronInteraction.EMPTY.map().put(ModItems.LIGHT_BLUE_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModCauldrons.LIGHT_BLUE_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        LIGHT_BLUE_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LIGHT_BLUE_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.getValue(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockAndUpdate(pos, ModCauldrons.LIGHT_BLUE_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                }
            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        LIGHT_BLUE_CAULDRON_BEHAVIOR.map().put(ModItems.LIGHT_BLUE_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClientSide) {
                    Item item = stack.getItem();
                    player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(item));
                    ColorAbstractCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return InteractionResult.sidedSuccess(world.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        });


        CauldronInteraction.EMPTY.map().put(ModItems.LIGHT_GRAY_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModCauldrons.LIGHT_GRAY_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        LIGHT_GRAY_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LIGHT_GRAY_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.getValue(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockAndUpdate(pos, ModCauldrons.LIGHT_GRAY_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                }
            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        LIGHT_GRAY_CAULDRON_BEHAVIOR.map().put(ModItems.LIGHT_GRAY_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClientSide) {
                    Item item = stack.getItem();
                    player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(item));
                    ColorAbstractCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return InteractionResult.sidedSuccess(world.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        });


        CauldronInteraction.EMPTY.map().put(ModItems.LIME_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModCauldrons.LIME_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        LIME_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LIME_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.getValue(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockAndUpdate(pos, ModCauldrons.LIME_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                }
            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        LIME_CAULDRON_BEHAVIOR.map().put(ModItems.LIME_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClientSide) {
                    Item item = stack.getItem();
                    player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(item));
                    ColorAbstractCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return InteractionResult.sidedSuccess(world.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        });


        CauldronInteraction.EMPTY.map().put(ModItems.ORANGE_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModCauldrons.ORANGE_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        ORANGE_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.ORANGE_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.getValue(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockAndUpdate(pos, ModCauldrons.ORANGE_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                }
            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        ORANGE_CAULDRON_BEHAVIOR.map().put(ModItems.ORANGE_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClientSide) {
                    Item item = stack.getItem();
                    player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(item));
                    ColorAbstractCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return InteractionResult.sidedSuccess(world.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        });


        CauldronInteraction.EMPTY.map().put(ModItems.PINK_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModCauldrons.PINK_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        PINK_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.PINK_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.getValue(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockAndUpdate(pos, ModCauldrons.PINK_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                }
            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        PINK_CAULDRON_BEHAVIOR.map().put(ModItems.PINK_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClientSide) {
                    Item item = stack.getItem();
                    player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(item));
                    ColorAbstractCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return InteractionResult.sidedSuccess(world.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        });


        CauldronInteraction.EMPTY.map().put(ModItems.RED_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModCauldrons.RED_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        RED_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.RED_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.getValue(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockAndUpdate(pos, ModCauldrons.RED_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                }
            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        RED_CAULDRON_BEHAVIOR.map().put(ModItems.RED_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClientSide) {
                    Item item = stack.getItem();
                    player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(item));
                    ColorAbstractCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return InteractionResult.sidedSuccess(world.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        });


        CauldronInteraction.EMPTY.map().put(ModItems.WHITE_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModCauldrons.WHITE_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        WHITE_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.WHITE_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.getValue(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockAndUpdate(pos, ModCauldrons.WHITE_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                }
            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        WHITE_CAULDRON_BEHAVIOR.map().put(ModItems.WHITE_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClientSide) {
                    Item item = stack.getItem();
                    player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(item));
                    ColorAbstractCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return InteractionResult.sidedSuccess(world.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        });


        CauldronInteraction.EMPTY.map().put(ModItems.YELLOW_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModCauldrons.YELLOW_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.sidedSuccess(world.isClientSide);
        });

        YELLOW_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.YELLOW_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.getValue(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockAndUpdate(pos, ModCauldrons.YELLOW_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                }
            }

            return InteractionResult.sidedSuccess(world.isClientSide);

        });

        YELLOW_CAULDRON_BEHAVIOR.map().put(ModItems.YELLOW_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClientSide) {
                    Item item = stack.getItem();
                    player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(item));
                    ColorAbstractCauldronBlock.incrementFluidLevel(state, world, pos);
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
