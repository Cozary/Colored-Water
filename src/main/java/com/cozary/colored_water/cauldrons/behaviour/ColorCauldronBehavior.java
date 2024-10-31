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


public interface ColorCauldronBehavior extends CauldronBehavior {

    Map<Item, CauldronBehavior> BLACK_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> BLUE_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> BROWN_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> CYAN_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> GRAY_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> GREEN_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> LIGHT_BLUE_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> LIGHT_GRAY_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> LIME_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> MAGENTA_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> ORANGE_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> PINK_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> PURPLE_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> RED_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> WHITE_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();
    Map<Item, CauldronBehavior> YELLOW_CAULDRON_BEHAVIOR = CauldronBehavior.createMap();

    static void init() {

        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.MAGENTA_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.MAGENTA_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        MAGENTA_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.MAGENTA_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.MAGENTA_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }

            }

            return ActionResult.success(world.isClient);

        });

        MAGENTA_CAULDRON_BEHAVIOR.put(ModItems.MAGENTA_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.PURPLE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.PURPLE_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        PURPLE_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.PURPLE_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.PURPLE_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }

            }

            return ActionResult.success(world.isClient);

        });

        PURPLE_CAULDRON_BEHAVIOR.put(ModItems.PURPLE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.GREEN_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.GREEN_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        GREEN_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.GREEN_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.GREEN_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }

            }

            return ActionResult.success(world.isClient);

        });

        GREEN_CAULDRON_BEHAVIOR.put(ModItems.GREEN_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.BLACK_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.BLACK_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        BLACK_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.BLACK_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.BLACK_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ActionResult.success(world.isClient);

        });

        BLACK_CAULDRON_BEHAVIOR.put(ModItems.BLACK_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.BLUE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.BLUE_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        BLUE_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.BLUE_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.BLUE_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }

            }

            return ActionResult.success(world.isClient);

        });

        BLUE_CAULDRON_BEHAVIOR.put(ModItems.BLUE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.BROWN_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.BROWN_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        BROWN_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.BROWN_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.BROWN_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }

            }

            return ActionResult.success(world.isClient);

        });

        BROWN_CAULDRON_BEHAVIOR.put(ModItems.BROWN_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.CYAN_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.CYAN_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        CYAN_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.CYAN_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.CYAN_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }

            }

            return ActionResult.success(world.isClient);

        });

        CYAN_CAULDRON_BEHAVIOR.put(ModItems.CYAN_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.GRAY_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.GRAY_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        GRAY_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.GRAY_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.GRAY_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }

            }

            return ActionResult.success(world.isClient);

        });

        GRAY_CAULDRON_BEHAVIOR.put(ModItems.GRAY_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.LIGHT_BLUE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.LIGHT_BLUE_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        LIGHT_BLUE_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LIGHT_BLUE_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.LIGHT_BLUE_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ActionResult.success(world.isClient);

        });

        LIGHT_BLUE_CAULDRON_BEHAVIOR.put(ModItems.LIGHT_BLUE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.LIGHT_GRAY_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.LIGHT_GRAY_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        LIGHT_GRAY_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LIGHT_GRAY_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.LIGHT_GRAY_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ActionResult.success(world.isClient);

        });

        LIGHT_GRAY_CAULDRON_BEHAVIOR.put(ModItems.LIGHT_GRAY_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.LIME_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.LIME_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        LIME_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.LIME_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.LIME_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ActionResult.success(world.isClient);

        });

        LIME_CAULDRON_BEHAVIOR.put(ModItems.LIME_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.ORANGE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.ORANGE_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        ORANGE_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.ORANGE_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.ORANGE_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ActionResult.success(world.isClient);

        });

        ORANGE_CAULDRON_BEHAVIOR.put(ModItems.ORANGE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.PINK_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.PINK_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        PINK_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.PINK_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.PINK_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ActionResult.success(world.isClient);

        });

        PINK_CAULDRON_BEHAVIOR.put(ModItems.PINK_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.RED_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.RED_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        RED_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.RED_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.RED_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ActionResult.success(world.isClient);

        });

        RED_CAULDRON_BEHAVIOR.put(ModItems.RED_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.WHITE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.WHITE_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        WHITE_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.WHITE_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.WHITE_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ActionResult.success(world.isClient);

        });

        WHITE_CAULDRON_BEHAVIOR.put(ModItems.WHITE_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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


        EMPTY_CAULDRON_BEHAVIOR.put(ModItems.YELLOW_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, getEmptiedStack(stack, player));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, ModCauldrons.YELLOW_WATER_CAULDRON.getDefaultState().with(LEVEL, 1));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
            }

            return ActionResult.success(world.isClient);
        });

        YELLOW_CAULDRON_BEHAVIOR.put(Items.BUCKET, (state, world, pos, player, hand, stack) -> {
            if (!world.isClient) {
                Item item = stack.getItem();
                player.setStackInHand(hand, FluidLevelUtil.exchangeStack(stack, player, new ItemStack(ModItems.YELLOW_WATER_BUCKET)));
                player.incrementStat(Stats.USE_CAULDRON);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);

                int currentLevel = state.get(LEVEL);
                if (currentLevel > 1) {
                    world.setBlockState(pos, ModCauldrons.YELLOW_WATER_CAULDRON.getDefaultState().with(LEVEL, currentLevel - 1));
                } else {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                }
            }

            return ActionResult.success(world.isClient);

        });

        YELLOW_CAULDRON_BEHAVIOR.put(ModItems.YELLOW_WATER_BUCKET, (state, world, pos, player, hand, stack) -> {
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
