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


public interface LuminousCondenseCauldronBehavior extends CauldronInteraction {

    InteractionMap LUMINOUS_CONDENSE_BLACK_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("luminous_condense_black_cauldron_behavior");
    InteractionMap LUMINOUS_CONDENSE_BLUE_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("luminous_condense_blue_cauldron_behavior");
    InteractionMap LUMINOUS_CONDENSE_BROWN_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("luminous_condense_brown_cauldron_behavior");
    InteractionMap LUMINOUS_CONDENSE_CYAN_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("luminous_condense_cyan_cauldron_behavior");
    InteractionMap LUMINOUS_CONDENSE_GRAY_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("luminous_condense_gray_cauldron_behavior");
    InteractionMap LUMINOUS_CONDENSE_GREEN_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("luminous_condense_green_cauldron_behavior");
    InteractionMap LUMINOUS_CONDENSE_LIGHT_BLUE_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("luminous_condense_light_blue_cauldron_behavior");
    InteractionMap LUMINOUS_CONDENSE_LIGHT_GRAY_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("luminous_condense_light_gray_cauldron_behavior");
    InteractionMap LUMINOUS_CONDENSE_LIME_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("luminous_condense_lime_cauldron_behavior");
    InteractionMap LUMINOUS_CONDENSE_MAGENTA_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("luminous_condense_magenta_cauldron_behavior");
    InteractionMap LUMINOUS_CONDENSE_ORANGE_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("luminous_condense_orange_cauldron_behavior");
    InteractionMap LUMINOUS_CONDENSE_PINK_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("luminous_condense_pink_cauldron_behavior");
    InteractionMap LUMINOUS_CONDENSE_PURPLE_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("luminous_condense_purple_cauldron_behavior");
    InteractionMap LUMINOUS_CONDENSE_RED_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("luminous_condense_red_cauldron_behavior");
    InteractionMap LUMINOUS_CONDENSE_WHITE_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("luminous_condense_white_cauldron_behavior");
    InteractionMap LUMINOUS_CONDENSE_YELLOW_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("luminous_condense_yellow_cauldron_behavior");

    static void init() {


        CauldronInteraction.EMPTY.map().put(ModItems.LUMINOUS_CONDENSE_MAGENTA_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModCauldrons.LUMINOUS_CONDENSE_MAGENTA_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.SUCCESS;
        });

        LUMINOUS_CONDENSE_MAGENTA_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_CONDENSE_MAGENTA_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (!state.hasProperty(LEVEL)) {
                    return InteractionResult.FAIL;
                }

                int currentLevel = state.getValue(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockAndUpdate(pos, ModCauldrons.LUMINOUS_CONDENSE_MAGENTA_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                }
            }

            return InteractionResult.SUCCESS;

        });

        LUMINOUS_CONDENSE_MAGENTA_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_MAGENTA_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
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

                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.SUCCESS;
            }
        });


        CauldronInteraction.EMPTY.map().put(ModItems.LUMINOUS_CONDENSE_PURPLE_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModCauldrons.LUMINOUS_CONDENSE_PURPLE_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.SUCCESS;
        });

        LUMINOUS_CONDENSE_PURPLE_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_CONDENSE_PURPLE_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (!state.hasProperty(LEVEL)) {
                    return InteractionResult.FAIL;
                }

                int currentLevel = state.getValue(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockAndUpdate(pos, ModCauldrons.LUMINOUS_CONDENSE_PURPLE_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                }
            }

            return InteractionResult.SUCCESS;

        });

        LUMINOUS_CONDENSE_PURPLE_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_PURPLE_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
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

                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.SUCCESS;
            }
        });


        CauldronInteraction.EMPTY.map().put(ModItems.LUMINOUS_CONDENSE_GREEN_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModCauldrons.LUMINOUS_CONDENSE_GREEN_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.SUCCESS;
        });

        LUMINOUS_CONDENSE_GREEN_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_CONDENSE_GREEN_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (!state.hasProperty(LEVEL)) {
                    return InteractionResult.FAIL;
                }

                int currentLevel = state.getValue(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockAndUpdate(pos, ModCauldrons.LUMINOUS_CONDENSE_GREEN_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                }
            }

            return InteractionResult.SUCCESS;

        });

        LUMINOUS_CONDENSE_GREEN_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_GREEN_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
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

                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.SUCCESS;
            }
        });


        CauldronInteraction.EMPTY.map().put(ModItems.LUMINOUS_CONDENSE_BLACK_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModCauldrons.LUMINOUS_CONDENSE_BLACK_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.SUCCESS;
        });

        LUMINOUS_CONDENSE_BLACK_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_CONDENSE_BLACK_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (!state.hasProperty(LEVEL)) {
                    return InteractionResult.FAIL;
                }

                int currentLevel = state.getValue(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockAndUpdate(pos, ModCauldrons.LUMINOUS_CONDENSE_BLACK_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                }
            }

            return InteractionResult.SUCCESS;

        });

        LUMINOUS_CONDENSE_BLACK_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_BLACK_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
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

                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.SUCCESS;
            }
        });


        CauldronInteraction.EMPTY.map().put(ModItems.LUMINOUS_CONDENSE_BLUE_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModCauldrons.LUMINOUS_CONDENSE_BLUE_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.SUCCESS;
        });

        LUMINOUS_CONDENSE_BLUE_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_CONDENSE_BLUE_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (!state.hasProperty(LEVEL)) {
                    return InteractionResult.FAIL;
                }

                int currentLevel = state.getValue(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockAndUpdate(pos, ModCauldrons.LUMINOUS_CONDENSE_BLUE_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                }
            }

            return InteractionResult.SUCCESS;

        });

        LUMINOUS_CONDENSE_BLUE_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_BLUE_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
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

                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.SUCCESS;
            }
        });


        CauldronInteraction.EMPTY.map().put(ModItems.LUMINOUS_CONDENSE_BROWN_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModCauldrons.LUMINOUS_CONDENSE_BROWN_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.SUCCESS;
        });

        LUMINOUS_CONDENSE_BROWN_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_CONDENSE_BROWN_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (!state.hasProperty(LEVEL)) {
                    return InteractionResult.FAIL;
                }

                int currentLevel = state.getValue(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockAndUpdate(pos, ModCauldrons.LUMINOUS_CONDENSE_BROWN_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                }
            }

            return InteractionResult.SUCCESS;

        });

        LUMINOUS_CONDENSE_BROWN_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_BROWN_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
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

                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.SUCCESS;
            }
        });


        CauldronInteraction.EMPTY.map().put(ModItems.LUMINOUS_CONDENSE_CYAN_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModCauldrons.LUMINOUS_CONDENSE_CYAN_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.SUCCESS;
        });

        LUMINOUS_CONDENSE_CYAN_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_CONDENSE_CYAN_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (!state.hasProperty(LEVEL)) {
                    return InteractionResult.FAIL;
                }

                int currentLevel = state.getValue(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockAndUpdate(pos, ModCauldrons.LUMINOUS_CONDENSE_CYAN_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                }
            }

            return InteractionResult.SUCCESS;

        });

        LUMINOUS_CONDENSE_CYAN_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_CYAN_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
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

                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.SUCCESS;
            }
        });


        CauldronInteraction.EMPTY.map().put(ModItems.LUMINOUS_CONDENSE_GRAY_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModCauldrons.LUMINOUS_CONDENSE_GRAY_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.SUCCESS;
        });

        LUMINOUS_CONDENSE_GRAY_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_CONDENSE_GRAY_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (!state.hasProperty(LEVEL)) {
                    return InteractionResult.FAIL;
                }

                int currentLevel = state.getValue(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockAndUpdate(pos, ModCauldrons.LUMINOUS_CONDENSE_GRAY_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                }
            }

            return InteractionResult.SUCCESS;

        });

        LUMINOUS_CONDENSE_GRAY_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_GRAY_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
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

                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.SUCCESS;
            }
        });


        CauldronInteraction.EMPTY.map().put(ModItems.LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModCauldrons.LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.SUCCESS;
        });

        LUMINOUS_CONDENSE_LIGHT_BLUE_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (!state.hasProperty(LEVEL)) {
                    return InteractionResult.FAIL;
                }

                int currentLevel = state.getValue(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockAndUpdate(pos, ModCauldrons.LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                }
            }

            return InteractionResult.SUCCESS;

        });

        LUMINOUS_CONDENSE_LIGHT_BLUE_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
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

                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.SUCCESS;
            }
        });


        CauldronInteraction.EMPTY.map().put(ModItems.LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModCauldrons.LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.SUCCESS;
        });

        LUMINOUS_CONDENSE_LIGHT_GRAY_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (!state.hasProperty(LEVEL)) {
                    return InteractionResult.FAIL;
                }

                int currentLevel = state.getValue(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockAndUpdate(pos, ModCauldrons.LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                }
            }

            return InteractionResult.SUCCESS;

        });

        LUMINOUS_CONDENSE_LIGHT_GRAY_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
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

                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.SUCCESS;
            }
        });


        CauldronInteraction.EMPTY.map().put(ModItems.LUMINOUS_CONDENSE_LIME_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModCauldrons.LUMINOUS_CONDENSE_LIME_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.SUCCESS;
        });

        LUMINOUS_CONDENSE_LIME_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_CONDENSE_LIME_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (!state.hasProperty(LEVEL)) {
                    return InteractionResult.FAIL;
                }

                int currentLevel = state.getValue(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockAndUpdate(pos, ModCauldrons.LUMINOUS_CONDENSE_LIME_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                }
            }

            return InteractionResult.SUCCESS;

        });

        LUMINOUS_CONDENSE_LIME_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_LIME_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
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

                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.SUCCESS;
            }
        });


        CauldronInteraction.EMPTY.map().put(ModItems.LUMINOUS_CONDENSE_ORANGE_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModCauldrons.LUMINOUS_CONDENSE_ORANGE_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.SUCCESS;
        });

        LUMINOUS_CONDENSE_ORANGE_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_CONDENSE_ORANGE_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (!state.hasProperty(LEVEL)) {
                    return InteractionResult.FAIL;
                }

                int currentLevel = state.getValue(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockAndUpdate(pos, ModCauldrons.LUMINOUS_CONDENSE_ORANGE_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                }
            }

            return InteractionResult.SUCCESS;

        });

        LUMINOUS_CONDENSE_ORANGE_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_ORANGE_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
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

                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.SUCCESS;
            }
        });


        CauldronInteraction.EMPTY.map().put(ModItems.LUMINOUS_CONDENSE_PINK_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModCauldrons.LUMINOUS_CONDENSE_PINK_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.SUCCESS;
        });

        LUMINOUS_CONDENSE_PINK_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_CONDENSE_PINK_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (!state.hasProperty(LEVEL)) {
                    return InteractionResult.FAIL;
                }

                int currentLevel = state.getValue(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockAndUpdate(pos, ModCauldrons.LUMINOUS_CONDENSE_PINK_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                }
            }

            return InteractionResult.SUCCESS;

        });

        LUMINOUS_CONDENSE_PINK_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_PINK_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
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

                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.SUCCESS;
            }
        });


        CauldronInteraction.EMPTY.map().put(ModItems.LUMINOUS_CONDENSE_RED_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModCauldrons.LUMINOUS_CONDENSE_RED_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.SUCCESS;
        });

        LUMINOUS_CONDENSE_RED_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_CONDENSE_RED_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (!state.hasProperty(LEVEL)) {
                    return InteractionResult.FAIL;
                }

                int currentLevel = state.getValue(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockAndUpdate(pos, ModCauldrons.LUMINOUS_CONDENSE_RED_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                }
            }

            return InteractionResult.SUCCESS;

        });

        LUMINOUS_CONDENSE_RED_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_RED_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
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

                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.SUCCESS;
            }
        });


        CauldronInteraction.EMPTY.map().put(ModItems.LUMINOUS_CONDENSE_WHITE_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModCauldrons.LUMINOUS_CONDENSE_WHITE_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.SUCCESS;
        });

        LUMINOUS_CONDENSE_WHITE_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_CONDENSE_WHITE_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (!state.hasProperty(LEVEL)) {
                    return InteractionResult.FAIL;
                }

                int currentLevel = state.getValue(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockAndUpdate(pos, ModCauldrons.LUMINOUS_CONDENSE_WHITE_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                }
            }

            return InteractionResult.SUCCESS;

        });

        LUMINOUS_CONDENSE_WHITE_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_WHITE_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
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

                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.SUCCESS;
            }
        });


        CauldronInteraction.EMPTY.map().put(ModItems.LUMINOUS_CONDENSE_YELLOW_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ModCauldrons.LUMINOUS_CONDENSE_YELLOW_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return InteractionResult.SUCCESS;
        });

        LUMINOUS_CONDENSE_YELLOW_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_CONDENSE_YELLOW_WATER_BUCKET.get())));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);

                if (!state.hasProperty(LEVEL)) {
                    return InteractionResult.FAIL;
                }

                int currentLevel = state.getValue(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockAndUpdate(pos, ModCauldrons.LUMINOUS_CONDENSE_YELLOW_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                }
            }

            return InteractionResult.SUCCESS;

        });

        LUMINOUS_CONDENSE_YELLOW_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_YELLOW_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
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

                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.SUCCESS;
            }
        });

    }
}
