package com.cozary.colored_water.cauldrons.behaviour;

import com.cozary.colored_water.cauldrons.ColorAbstractCauldronBlock;
import com.cozary.colored_water.cauldrons.util.FluidLevelUtil;
import com.cozary.colored_water.init.ModCauldrons;
import com.cozary.colored_water.init.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ItemActionResult;
import net.minecraft.world.event.GameEvent;

import java.util.Map;

import static net.minecraft.block.LeveledCauldronBlock.LEVEL;
import static net.minecraft.item.BucketItem.getEmptiedStack;


public interface LuminousCondenseCauldronBehavior extends CauldronBehavior {

    CauldronBehaviorMap LUMINOUS_CONDENSE_BLACK_CAULDRON_BEHAVIOR = CauldronBehavior.createMap("luminous_condense_black_cauldron_behavior");
    CauldronBehaviorMap LUMINOUS_CONDENSE_BLUE_CAULDRON_BEHAVIOR = CauldronBehavior.createMap("luminous_condense_blue_cauldron_behavior");
    CauldronBehaviorMap LUMINOUS_CONDENSE_BROWN_CAULDRON_BEHAVIOR = CauldronBehavior.createMap("luminous_condense_brown_cauldron_behavior");
    CauldronBehaviorMap LUMINOUS_CONDENSE_CYAN_CAULDRON_BEHAVIOR = CauldronBehavior.createMap("luminous_condense_cyan_cauldron_behavior");
    CauldronBehaviorMap LUMINOUS_CONDENSE_GRAY_CAULDRON_BEHAVIOR = CauldronBehavior.createMap("luminous_condense_gray_cauldron_behavior");
    CauldronBehaviorMap LUMINOUS_CONDENSE_GREEN_CAULDRON_BEHAVIOR = CauldronBehavior.createMap("luminous_condense_green_cauldron_behavior");
    CauldronBehaviorMap LUMINOUS_CONDENSE_LIGHT_BLUE_CAULDRON_BEHAVIOR = CauldronBehavior.createMap("luminous_condense_light_blue_cauldron_behavior");
    CauldronBehaviorMap LUMINOUS_CONDENSE_LIGHT_GRAY_CAULDRON_BEHAVIOR = CauldronBehavior.createMap("luminous_condense_light_gray_cauldron_behavior");
    CauldronBehaviorMap LUMINOUS_CONDENSE_LIME_CAULDRON_BEHAVIOR = CauldronBehavior.createMap("luminous_condense_lime_cauldron_behavior");
    CauldronBehaviorMap LUMINOUS_CONDENSE_MAGENTA_CAULDRON_BEHAVIOR = CauldronBehavior.createMap("luminous_condense_magenta_cauldron_behavior");
    CauldronBehaviorMap LUMINOUS_CONDENSE_ORANGE_CAULDRON_BEHAVIOR = CauldronBehavior.createMap("luminous_condense_orange_cauldron_behavior");
    CauldronBehaviorMap LUMINOUS_CONDENSE_PINK_CAULDRON_BEHAVIOR = CauldronBehavior.createMap("luminous_condense_pink_cauldron_behavior");
    CauldronBehaviorMap LUMINOUS_CONDENSE_PURPLE_CAULDRON_BEHAVIOR = CauldronBehavior.createMap("luminous_condense_purple_cauldron_behavior");
    CauldronBehaviorMap LUMINOUS_CONDENSE_RED_CAULDRON_BEHAVIOR = CauldronBehavior.createMap("luminous_condense_red_cauldron_behavior");
    CauldronBehaviorMap LUMINOUS_CONDENSE_WHITE_CAULDRON_BEHAVIOR = CauldronBehavior.createMap("luminous_condense_white_cauldron_behavior");
    CauldronBehaviorMap LUMINOUS_CONDENSE_YELLOW_CAULDRON_BEHAVIOR = CauldronBehavior.createMap("luminous_condense_yellow_cauldron_behavior");

    static void init() {


        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_MAGENTA_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.LUMINOUS_CONDENSE_MAGENTA_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ItemActionResult.success(world.isClient);
        });

        LUMINOUS_CONDENSE_MAGENTA_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_CONDENSE_MAGENTA_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.LUMINOUS_CONDENSE_MAGENTA_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ItemActionResult.success(world.isClient);

        });

        LUMINOUS_CONDENSE_MAGENTA_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_MAGENTA_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClient) {
                    Item item = stack.getItem();
                    player.setStackInHand(hand, getEmptiedStack(stack, player));
                    player.incrementStat(Stats.USE_CAULDRON);
                    player.incrementStat(Stats.USED.getOrCreateStat(item));
                    ColorAbstractCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return ItemActionResult.success(world.isClient);
            } else {
                return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });


        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_PURPLE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.LUMINOUS_CONDENSE_PURPLE_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ItemActionResult.success(world.isClient);
        });

        LUMINOUS_CONDENSE_PURPLE_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_CONDENSE_PURPLE_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.LUMINOUS_CONDENSE_PURPLE_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ItemActionResult.success(world.isClient);

        });

        LUMINOUS_CONDENSE_PURPLE_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_PURPLE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClient) {
                    Item item = stack.getItem();
                    player.setStackInHand(hand, getEmptiedStack(stack, player));
                    player.incrementStat(Stats.USE_CAULDRON);
                    player.incrementStat(Stats.USED.getOrCreateStat(item));
                    ColorAbstractCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return ItemActionResult.success(world.isClient);
            } else {
                return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });


        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_GREEN_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.LUMINOUS_CONDENSE_GREEN_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ItemActionResult.success(world.isClient);
        });

        LUMINOUS_CONDENSE_GREEN_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_CONDENSE_GREEN_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.LUMINOUS_CONDENSE_GREEN_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ItemActionResult.success(world.isClient);

        });

        LUMINOUS_CONDENSE_GREEN_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_GREEN_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClient) {
                    Item item = stack.getItem();
                    player.setStackInHand(hand, getEmptiedStack(stack, player));
                    player.incrementStat(Stats.USE_CAULDRON);
                    player.incrementStat(Stats.USED.getOrCreateStat(item));
                    ColorAbstractCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return ItemActionResult.success(world.isClient);
            } else {
                return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });


        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_BLACK_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.LUMINOUS_CONDENSE_BLACK_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ItemActionResult.success(world.isClient);
        });

        LUMINOUS_CONDENSE_BLACK_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_CONDENSE_BLACK_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.LUMINOUS_CONDENSE_BLACK_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ItemActionResult.success(world.isClient);

        });

        LUMINOUS_CONDENSE_BLACK_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_BLACK_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClient) {
                    Item item = stack.getItem();
                    player.setStackInHand(hand, getEmptiedStack(stack, player));
                    player.incrementStat(Stats.USE_CAULDRON);
                    player.incrementStat(Stats.USED.getOrCreateStat(item));
                    ColorAbstractCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return ItemActionResult.success(world.isClient);
            } else {
                return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });


        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_BLUE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.LUMINOUS_CONDENSE_BLUE_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ItemActionResult.success(world.isClient);
        });

        LUMINOUS_CONDENSE_BLUE_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_CONDENSE_BLUE_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.LUMINOUS_CONDENSE_BLUE_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ItemActionResult.success(world.isClient);

        });

        LUMINOUS_CONDENSE_BLUE_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_BLUE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClient) {
                    Item item = stack.getItem();
                    player.setStackInHand(hand, getEmptiedStack(stack, player));
                    player.incrementStat(Stats.USE_CAULDRON);
                    player.incrementStat(Stats.USED.getOrCreateStat(item));
                    ColorAbstractCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return ItemActionResult.success(world.isClient);
            } else {
                return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });


        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_BROWN_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.LUMINOUS_CONDENSE_BROWN_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ItemActionResult.success(world.isClient);
        });

        LUMINOUS_CONDENSE_BROWN_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_CONDENSE_BROWN_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.LUMINOUS_CONDENSE_BROWN_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ItemActionResult.success(world.isClient);

        });

        LUMINOUS_CONDENSE_BROWN_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_BROWN_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClient) {
                    Item item = stack.getItem();
                    player.setStackInHand(hand, getEmptiedStack(stack, player));
                    player.incrementStat(Stats.USE_CAULDRON);
                    player.incrementStat(Stats.USED.getOrCreateStat(item));
                    ColorAbstractCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return ItemActionResult.success(world.isClient);
            } else {
                return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });


        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_CYAN_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.LUMINOUS_CONDENSE_CYAN_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ItemActionResult.success(world.isClient);
        });

        LUMINOUS_CONDENSE_CYAN_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_CONDENSE_CYAN_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.LUMINOUS_CONDENSE_CYAN_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ItemActionResult.success(world.isClient);

        });

        LUMINOUS_CONDENSE_CYAN_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_CYAN_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClient) {
                    Item item = stack.getItem();
                    player.setStackInHand(hand, getEmptiedStack(stack, player));
                    player.incrementStat(Stats.USE_CAULDRON);
                    player.incrementStat(Stats.USED.getOrCreateStat(item));
                    ColorAbstractCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return ItemActionResult.success(world.isClient);
            } else {
                return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });


        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_GRAY_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.LUMINOUS_CONDENSE_GRAY_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ItemActionResult.success(world.isClient);
        });

        LUMINOUS_CONDENSE_GRAY_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_CONDENSE_GRAY_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.LUMINOUS_CONDENSE_GRAY_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ItemActionResult.success(world.isClient);

        });

        LUMINOUS_CONDENSE_GRAY_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_GRAY_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClient) {
                    Item item = stack.getItem();
                    player.setStackInHand(hand, getEmptiedStack(stack, player));
                    player.incrementStat(Stats.USE_CAULDRON);
                    player.incrementStat(Stats.USED.getOrCreateStat(item));
                    ColorAbstractCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return ItemActionResult.success(world.isClient);
            } else {
                return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });


        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ItemActionResult.success(world.isClient);
        });

        LUMINOUS_CONDENSE_LIGHT_BLUE_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ItemActionResult.success(world.isClient);

        });

        LUMINOUS_CONDENSE_LIGHT_BLUE_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClient) {
                    Item item = stack.getItem();
                    player.setStackInHand(hand, getEmptiedStack(stack, player));
                    player.incrementStat(Stats.USE_CAULDRON);
                    player.incrementStat(Stats.USED.getOrCreateStat(item));
                    ColorAbstractCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return ItemActionResult.success(world.isClient);
            } else {
                return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });


        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ItemActionResult.success(world.isClient);
        });

        LUMINOUS_CONDENSE_LIGHT_GRAY_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ItemActionResult.success(world.isClient);

        });

        LUMINOUS_CONDENSE_LIGHT_GRAY_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClient) {
                    Item item = stack.getItem();
                    player.setStackInHand(hand, getEmptiedStack(stack, player));
                    player.incrementStat(Stats.USE_CAULDRON);
                    player.incrementStat(Stats.USED.getOrCreateStat(item));
                    ColorAbstractCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return ItemActionResult.success(world.isClient);
            } else {
                return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });


        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_LIME_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.LUMINOUS_CONDENSE_LIME_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ItemActionResult.success(world.isClient);
        });

        LUMINOUS_CONDENSE_LIME_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_CONDENSE_LIME_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.LUMINOUS_CONDENSE_LIME_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ItemActionResult.success(world.isClient);

        });

        LUMINOUS_CONDENSE_LIME_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_LIME_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClient) {
                    Item item = stack.getItem();
                    player.setStackInHand(hand, getEmptiedStack(stack, player));
                    player.incrementStat(Stats.USE_CAULDRON);
                    player.incrementStat(Stats.USED.getOrCreateStat(item));
                    ColorAbstractCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return ItemActionResult.success(world.isClient);
            } else {
                return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });


        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_ORANGE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.LUMINOUS_CONDENSE_ORANGE_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ItemActionResult.success(world.isClient);
        });

        LUMINOUS_CONDENSE_ORANGE_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_CONDENSE_ORANGE_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.LUMINOUS_CONDENSE_ORANGE_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ItemActionResult.success(world.isClient);

        });

        LUMINOUS_CONDENSE_ORANGE_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_ORANGE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClient) {
                    Item item = stack.getItem();
                    player.setStackInHand(hand, getEmptiedStack(stack, player));
                    player.incrementStat(Stats.USE_CAULDRON);
                    player.incrementStat(Stats.USED.getOrCreateStat(item));
                    ColorAbstractCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return ItemActionResult.success(world.isClient);
            } else {
                return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });


        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_PINK_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.LUMINOUS_CONDENSE_PINK_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ItemActionResult.success(world.isClient);
        });

        LUMINOUS_CONDENSE_PINK_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_CONDENSE_PINK_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.LUMINOUS_CONDENSE_PINK_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ItemActionResult.success(world.isClient);

        });

        LUMINOUS_CONDENSE_PINK_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_PINK_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClient) {
                    Item item = stack.getItem();
                    player.setStackInHand(hand, getEmptiedStack(stack, player));
                    player.incrementStat(Stats.USE_CAULDRON);
                    player.incrementStat(Stats.USED.getOrCreateStat(item));
                    ColorAbstractCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return ItemActionResult.success(world.isClient);
            } else {
                return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });


        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_RED_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.LUMINOUS_CONDENSE_RED_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ItemActionResult.success(world.isClient);
        });

        LUMINOUS_CONDENSE_RED_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_CONDENSE_RED_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.LUMINOUS_CONDENSE_RED_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ItemActionResult.success(world.isClient);

        });

        LUMINOUS_CONDENSE_RED_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_RED_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClient) {
                    Item item = stack.getItem();
                    player.setStackInHand(hand, getEmptiedStack(stack, player));
                    player.incrementStat(Stats.USE_CAULDRON);
                    player.incrementStat(Stats.USED.getOrCreateStat(item));
                    ColorAbstractCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return ItemActionResult.success(world.isClient);
            } else {
                return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });


        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_WHITE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.LUMINOUS_CONDENSE_WHITE_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ItemActionResult.success(world.isClient);
        });

        LUMINOUS_CONDENSE_WHITE_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_CONDENSE_WHITE_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.LUMINOUS_CONDENSE_WHITE_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ItemActionResult.success(world.isClient);

        });

        LUMINOUS_CONDENSE_WHITE_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_WHITE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClient) {
                    Item item = stack.getItem();
                    player.setStackInHand(hand, getEmptiedStack(stack, player));
                    player.incrementStat(Stats.USE_CAULDRON);
                    player.incrementStat(Stats.USED.getOrCreateStat(item));
                    ColorAbstractCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return ItemActionResult.success(world.isClient);
            } else {
                return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });


        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_YELLOW_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.LUMINOUS_CONDENSE_YELLOW_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ItemActionResult.success(world.isClient);
        });

        LUMINOUS_CONDENSE_YELLOW_CAULDRON_BEHAVIOR.map().put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_CONDENSE_YELLOW_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.LUMINOUS_CONDENSE_YELLOW_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ItemActionResult.success(world.isClient);

        });

        LUMINOUS_CONDENSE_YELLOW_CAULDRON_BEHAVIOR.map().put(ModItems.LUMINOUS_CONDENSE_YELLOW_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (FluidLevelUtil.canIncrementFluidLevel(state)) {
                if (!world.isClient) {
                    Item item = stack.getItem();
                    player.setStackInHand(hand, getEmptiedStack(stack, player));
                    player.incrementStat(Stats.USE_CAULDRON);
                    player.incrementStat(Stats.USED.getOrCreateStat(item));
                    ColorAbstractCauldronBlock.incrementFluidLevel(state, world, pos);
                    world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
                }

                return ItemActionResult.success(world.isClient);
            } else {
                return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        });

    }
}
