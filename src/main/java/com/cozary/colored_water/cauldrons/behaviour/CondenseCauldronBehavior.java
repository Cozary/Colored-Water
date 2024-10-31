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
import net.minecraft.util.ActionResult;
import net.minecraft.world.event.GameEvent;

import java.util.Map;

import static net.minecraft.block.LeveledCauldronBlock.LEVEL;
import static net.minecraft.item.BucketItem.getEmptiedStack;


public interface CondenseCauldronBehavior extends CauldronBehavior {

    Map<Item, CauldronBehavior> CONDENSE_BLACK_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> CONDENSE_BLUE_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> CONDENSE_BROWN_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> CONDENSE_CYAN_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> CONDENSE_GRAY_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> CONDENSE_GREEN_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> CONDENSE_LIGHT_BLUE_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> CONDENSE_LIGHT_GRAY_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> CONDENSE_LIME_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> CONDENSE_MAGENTA_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> CONDENSE_ORANGE_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> CONDENSE_PINK_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> CONDENSE_PURPLE_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> CONDENSE_RED_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> CONDENSE_WHITE_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> CONDENSE_YELLOW_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();

    static void init() {


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_MAGENTA_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.CONDENSE_MAGENTA_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        CONDENSE_MAGENTA_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.CONDENSE_MAGENTA_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.CONDENSE_MAGENTA_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }

            }

            return ActionResult.success(world.isClient);

        });

        CONDENSE_MAGENTA_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_MAGENTA_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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

                return ActionResult.success(world.isClient);
            } else {
                return ActionResult.PASS;
            }
        });


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_PURPLE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.CONDENSE_PURPLE_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        CONDENSE_PURPLE_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.CONDENSE_PURPLE_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.CONDENSE_PURPLE_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }

            }

            return ActionResult.success(world.isClient);

        });

        CONDENSE_PURPLE_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_PURPLE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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

                return ActionResult.success(world.isClient);
            } else {
                return ActionResult.PASS;
            }
        });


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_GREEN_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.CONDENSE_GREEN_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        CONDENSE_GREEN_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.CONDENSE_GREEN_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.CONDENSE_GREEN_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }

            }

            return ActionResult.success(world.isClient);

        });

        CONDENSE_GREEN_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_GREEN_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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

                return ActionResult.success(world.isClient);
            } else {
                return ActionResult.PASS;
            }
        });


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_BLACK_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.CONDENSE_BLACK_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        CONDENSE_BLACK_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.CONDENSE_BLACK_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.CONDENSE_BLACK_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }

            }

            return ActionResult.success(world.isClient);

        });

        CONDENSE_BLACK_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_BLACK_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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

                return ActionResult.success(world.isClient);
            } else {
                return ActionResult.PASS;
            }
        });


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_BLUE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.CONDENSE_BLUE_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        CONDENSE_BLUE_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.CONDENSE_BLUE_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.CONDENSE_BLUE_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ActionResult.success(world.isClient);

        });

        CONDENSE_BLUE_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_BLUE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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

                return ActionResult.success(world.isClient);
            } else {
                return ActionResult.PASS;
            }
        });


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_BROWN_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.CONDENSE_BROWN_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        CONDENSE_BROWN_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.CONDENSE_BROWN_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.CONDENSE_BROWN_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ActionResult.success(world.isClient);

        });

        CONDENSE_BROWN_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_BROWN_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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

                return ActionResult.success(world.isClient);
            } else {
                return ActionResult.PASS;
            }
        });


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_CYAN_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.CONDENSE_CYAN_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        CONDENSE_CYAN_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.CONDENSE_CYAN_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.CONDENSE_CYAN_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ActionResult.success(world.isClient);

        });

        CONDENSE_CYAN_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_CYAN_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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

                return ActionResult.success(world.isClient);
            } else {
                return ActionResult.PASS;
            }
        });


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_GRAY_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.CONDENSE_GRAY_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        CONDENSE_GRAY_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.CONDENSE_GRAY_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.CONDENSE_GRAY_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ActionResult.success(world.isClient);

        });

        CONDENSE_GRAY_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_GRAY_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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

                return ActionResult.success(world.isClient);
            } else {
                return ActionResult.PASS;
            }
        });


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_LIGHT_BLUE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.CONDENSE_LIGHT_BLUE_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        CONDENSE_LIGHT_BLUE_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.CONDENSE_LIGHT_BLUE_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.CONDENSE_LIGHT_BLUE_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ActionResult.success(world.isClient);

        });

        CONDENSE_LIGHT_BLUE_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_LIGHT_BLUE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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

                return ActionResult.success(world.isClient);
            } else {
                return ActionResult.PASS;
            }
        });


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_LIGHT_GRAY_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.CONDENSE_LIGHT_GRAY_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        CONDENSE_LIGHT_GRAY_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.CONDENSE_LIGHT_GRAY_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.CONDENSE_LIGHT_GRAY_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ActionResult.success(world.isClient);

        });

        CONDENSE_LIGHT_GRAY_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_LIGHT_GRAY_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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

                return ActionResult.success(world.isClient);
            } else {
                return ActionResult.PASS;
            }
        });


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_LIME_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.CONDENSE_LIME_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        CONDENSE_LIME_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.CONDENSE_LIME_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.CONDENSE_LIME_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ActionResult.success(world.isClient);

        });

        CONDENSE_LIME_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_LIME_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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

                return ActionResult.success(world.isClient);
            } else {
                return ActionResult.PASS;
            }
        });


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_ORANGE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.CONDENSE_ORANGE_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        CONDENSE_ORANGE_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.CONDENSE_ORANGE_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.CONDENSE_ORANGE_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }

            }

            return ActionResult.success(world.isClient);

        });

        CONDENSE_ORANGE_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_ORANGE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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

                return ActionResult.success(world.isClient);
            } else {
                return ActionResult.PASS;
            }
        });


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_PINK_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.CONDENSE_PINK_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        CONDENSE_PINK_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.CONDENSE_PINK_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.CONDENSE_PINK_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ActionResult.success(world.isClient);

        });

        CONDENSE_PINK_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_PINK_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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

                return ActionResult.success(world.isClient);
            } else {
                return ActionResult.PASS;
            }
        });


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_RED_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.CONDENSE_RED_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        CONDENSE_RED_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.CONDENSE_RED_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.CONDENSE_RED_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ActionResult.success(world.isClient);

        });

        CONDENSE_RED_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_RED_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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

                return ActionResult.success(world.isClient);
            } else {
                return ActionResult.PASS;
            }
        });


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_WHITE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.CONDENSE_WHITE_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        CONDENSE_WHITE_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.CONDENSE_WHITE_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.CONDENSE_WHITE_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ActionResult.success(world.isClient);

        });

        CONDENSE_WHITE_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_WHITE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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

                return ActionResult.success(world.isClient);
            } else {
                return ActionResult.PASS;
            }
        });


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_YELLOW_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.CONDENSE_YELLOW_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        CONDENSE_YELLOW_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.CONDENSE_YELLOW_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.CONDENSE_YELLOW_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ActionResult.success(world.isClient);

        });

        CONDENSE_YELLOW_CAULDRON_BEHAVIOR.put(ModItems.CONDENSE_YELLOW_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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

                return ActionResult.success(world.isClient);
            } else {
                return ActionResult.PASS;
            }
        });

    }
}
