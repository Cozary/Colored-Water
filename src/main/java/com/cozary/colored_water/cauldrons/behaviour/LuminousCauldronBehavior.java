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


public interface LuminousCauldronBehavior extends CauldronBehavior {

    Map<Item, CauldronBehavior> LUMINOUS_BLACK_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> LUMINOUS_BLUE_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> LUMINOUS_BROWN_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> LUMINOUS_CYAN_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> LUMINOUS_GRAY_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> LUMINOUS_GREEN_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> LUMINOUS_LIGHT_BLUE_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> LUMINOUS_LIGHT_GRAY_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> LUMINOUS_LIME_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> LUMINOUS_MAGENTA_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> LUMINOUS_ORANGE_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> LUMINOUS_PINK_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> LUMINOUS_PURPLE_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> LUMINOUS_RED_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> LUMINOUS_WHITE_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> LUMINOUS_YELLOW_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();

    static void init() {


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.LUMINOUS_MAGENTA_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.LUMINOUS_MAGENTA_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        LUMINOUS_MAGENTA_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_MAGENTA_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.LUMINOUS_MAGENTA_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ActionResult.success(world.isClient);

        });

        LUMINOUS_MAGENTA_CAULDRON_BEHAVIOR.put(ModItems.LUMINOUS_MAGENTA_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.LUMINOUS_PURPLE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.LUMINOUS_PURPLE_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        LUMINOUS_PURPLE_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_PURPLE_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.LUMINOUS_PURPLE_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ActionResult.success(world.isClient);

        });

        LUMINOUS_PURPLE_CAULDRON_BEHAVIOR.put(ModItems.LUMINOUS_PURPLE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.LUMINOUS_GREEN_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.LUMINOUS_GREEN_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        LUMINOUS_GREEN_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_GREEN_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.LUMINOUS_GREEN_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ActionResult.success(world.isClient);

        });

        LUMINOUS_GREEN_CAULDRON_BEHAVIOR.put(ModItems.LUMINOUS_GREEN_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.LUMINOUS_BLACK_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.LUMINOUS_BLACK_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        LUMINOUS_BLACK_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_BLACK_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.LUMINOUS_BLACK_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ActionResult.success(world.isClient);

        });

        LUMINOUS_BLACK_CAULDRON_BEHAVIOR.put(ModItems.LUMINOUS_BLACK_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.LUMINOUS_BLUE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.LUMINOUS_BLUE_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        LUMINOUS_BLUE_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_BLUE_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.LUMINOUS_BLUE_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ActionResult.success(world.isClient);

        });

        LUMINOUS_BLUE_CAULDRON_BEHAVIOR.put(ModItems.LUMINOUS_BLUE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.LUMINOUS_BROWN_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.LUMINOUS_BROWN_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        LUMINOUS_BROWN_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_BROWN_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.LUMINOUS_BROWN_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ActionResult.success(world.isClient);

        });

        LUMINOUS_BROWN_CAULDRON_BEHAVIOR.put(ModItems.LUMINOUS_BROWN_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.LUMINOUS_CYAN_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.LUMINOUS_CYAN_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        LUMINOUS_CYAN_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_CYAN_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.LUMINOUS_CYAN_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ActionResult.success(world.isClient);

        });

        LUMINOUS_CYAN_CAULDRON_BEHAVIOR.put(ModItems.LUMINOUS_CYAN_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.LUMINOUS_GRAY_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.LUMINOUS_GRAY_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        LUMINOUS_GRAY_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_GRAY_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.LUMINOUS_GRAY_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ActionResult.success(world.isClient);

        });

        LUMINOUS_GRAY_CAULDRON_BEHAVIOR.put(ModItems.LUMINOUS_GRAY_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.LUMINOUS_LIGHT_BLUE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.LUMINOUS_LIGHT_BLUE_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        LUMINOUS_LIGHT_BLUE_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_LIGHT_BLUE_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.LUMINOUS_LIGHT_BLUE_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ActionResult.success(world.isClient);

        });

        LUMINOUS_LIGHT_BLUE_CAULDRON_BEHAVIOR.put(ModItems.LUMINOUS_LIGHT_BLUE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.LUMINOUS_LIGHT_GRAY_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.LUMINOUS_LIGHT_GRAY_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        LUMINOUS_LIGHT_GRAY_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_LIGHT_GRAY_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.LUMINOUS_LIGHT_GRAY_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ActionResult.success(world.isClient);

        });

        LUMINOUS_LIGHT_GRAY_CAULDRON_BEHAVIOR.put(ModItems.LUMINOUS_LIGHT_GRAY_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.LUMINOUS_LIME_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.LUMINOUS_LIME_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        LUMINOUS_LIME_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_LIME_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.LUMINOUS_LIME_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ActionResult.success(world.isClient);

        });

        LUMINOUS_LIME_CAULDRON_BEHAVIOR.put(ModItems.LUMINOUS_LIME_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.LUMINOUS_ORANGE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.LUMINOUS_ORANGE_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        LUMINOUS_ORANGE_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_ORANGE_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.LUMINOUS_ORANGE_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ActionResult.success(world.isClient);

        });

        LUMINOUS_ORANGE_CAULDRON_BEHAVIOR.put(ModItems.LUMINOUS_ORANGE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.LUMINOUS_PINK_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.LUMINOUS_PINK_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        LUMINOUS_PINK_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_PINK_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.LUMINOUS_PINK_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ActionResult.success(world.isClient);

        });

        LUMINOUS_PINK_CAULDRON_BEHAVIOR.put(ModItems.LUMINOUS_PINK_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.LUMINOUS_RED_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.LUMINOUS_RED_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        LUMINOUS_RED_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_RED_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.LUMINOUS_RED_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ActionResult.success(world.isClient);

        });

        LUMINOUS_RED_CAULDRON_BEHAVIOR.put(ModItems.LUMINOUS_RED_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.LUMINOUS_WHITE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.LUMINOUS_WHITE_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        LUMINOUS_WHITE_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_WHITE_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.LUMINOUS_WHITE_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ActionResult.success(world.isClient);

        });

        LUMINOUS_WHITE_CAULDRON_BEHAVIOR.put(ModItems.LUMINOUS_WHITE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.LUMINOUS_YELLOW_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.LUMINOUS_YELLOW_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        LUMINOUS_YELLOW_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LUMINOUS_YELLOW_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.LUMINOUS_YELLOW_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ActionResult.success(world.isClient);

        });

        LUMINOUS_YELLOW_CAULDRON_BEHAVIOR.put(ModItems.LUMINOUS_YELLOW_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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
