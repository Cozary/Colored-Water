package com.cozary.colored_water.cauldrons.behaviour;

import com.cozary.colored_water.init.ModCauldrons;
import com.cozary.colored_water.init.ModItems;
import com.cozary.colored_water.util.ColoredWaterUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BannerBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.*;
import java.util.function.Supplier;

import static net.minecraft.world.item.BucketItem.getEmptySuccessItem;
import static net.minecraft.world.level.block.LayeredCauldronBlock.LEVEL;

public final class CauldronBehaviorHelper {

    public record CauldronGroup(DyeColor dyeColor, Supplier<Block> normalCauldron, Supplier<Block> condenseCauldron, Supplier<Block> luminousCauldron, Supplier<Block> luminousCondenseCauldron, Supplier<Item> normalBucket, Supplier<Item> condenseBucket, Supplier<Item> luminousBucket, Supplier<Item> luminousCondenseBucket, CauldronInteraction.InteractionMap normalMap, CauldronInteraction.InteractionMap condenseMap, CauldronInteraction.InteractionMap luminousMap, CauldronInteraction.InteractionMap luminousCondenseMap) { }

    private static final Map<DyeColor, CauldronGroup> GROUPS_BY_COLOR = new EnumMap<>(DyeColor.class);
    private static final List<CauldronGroup> ALL_GROUPS = new ArrayList<>();

    private static void registerGroup(DyeColor color, Supplier<Block> normalCauldron, Supplier<Block> condenseCauldron, Supplier<Block> luminousCauldron, Supplier<Block> luminousCondenseCauldron, Supplier<Item> normalBucket, Supplier<Item> condenseBucket, Supplier<Item> luminousBucket, Supplier<Item> luminousCondenseBucket, CauldronInteraction.InteractionMap normalMap, CauldronInteraction.InteractionMap condenseMap, CauldronInteraction.InteractionMap luminousMap, CauldronInteraction.InteractionMap luminousCondenseMap) {
        CauldronGroup group = new CauldronGroup(color, normalCauldron, condenseCauldron, luminousCauldron, luminousCondenseCauldron, normalBucket, condenseBucket, luminousBucket, luminousCondenseBucket, normalMap, condenseMap, luminousMap, luminousCondenseMap);
        GROUPS_BY_COLOR.put(color, group);
        ALL_GROUPS.add(group);
    }

    static {
        registerGroup(DyeColor.WHITE,
                ModCauldrons.WHITE_WATER_CAULDRON, ModCauldrons.CONDENSE_WHITE_WATER_CAULDRON,
                ModCauldrons.LUMINOUS_WHITE_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_WHITE_WATER_CAULDRON,
                ModItems.WHITE_WATER_BUCKET, ModItems.CONDENSE_WHITE_WATER_BUCKET,
                ModItems.LUMINOUS_WHITE_WATER_BUCKET, ModItems.LUMINOUS_CONDENSE_WHITE_WATER_BUCKET,
                ColorCauldronBehavior.WHITE_CAULDRON_BEHAVIOR, CondenseCauldronBehavior.CONDENSE_WHITE_CAULDRON_BEHAVIOR,
                LuminousCauldronBehavior.LUMINOUS_WHITE_CAULDRON_BEHAVIOR, LuminousCondenseCauldronBehavior.LUMINOUS_CONDENSE_WHITE_CAULDRON_BEHAVIOR);

        registerGroup(DyeColor.ORANGE,
                ModCauldrons.ORANGE_WATER_CAULDRON, ModCauldrons.CONDENSE_ORANGE_WATER_CAULDRON,
                ModCauldrons.LUMINOUS_ORANGE_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_ORANGE_WATER_CAULDRON,
                ModItems.ORANGE_WATER_BUCKET, ModItems.CONDENSE_ORANGE_WATER_BUCKET,
                ModItems.LUMINOUS_ORANGE_WATER_BUCKET, ModItems.LUMINOUS_CONDENSE_ORANGE_WATER_BUCKET,
                ColorCauldronBehavior.ORANGE_CAULDRON_BEHAVIOR, CondenseCauldronBehavior.CONDENSE_ORANGE_CAULDRON_BEHAVIOR,
                LuminousCauldronBehavior.LUMINOUS_ORANGE_CAULDRON_BEHAVIOR, LuminousCondenseCauldronBehavior.LUMINOUS_CONDENSE_ORANGE_CAULDRON_BEHAVIOR);

        registerGroup(DyeColor.MAGENTA,
                ModCauldrons.MAGENTA_WATER_CAULDRON, ModCauldrons.CONDENSE_MAGENTA_WATER_CAULDRON,
                ModCauldrons.LUMINOUS_MAGENTA_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_MAGENTA_WATER_CAULDRON,
                ModItems.MAGENTA_WATER_BUCKET, ModItems.CONDENSE_MAGENTA_WATER_BUCKET,
                ModItems.LUMINOUS_MAGENTA_WATER_BUCKET, ModItems.LUMINOUS_CONDENSE_MAGENTA_WATER_BUCKET,
                ColorCauldronBehavior.MAGENTA_CAULDRON_BEHAVIOR, CondenseCauldronBehavior.CONDENSE_MAGENTA_CAULDRON_BEHAVIOR,
                LuminousCauldronBehavior.LUMINOUS_MAGENTA_CAULDRON_BEHAVIOR, LuminousCondenseCauldronBehavior.LUMINOUS_CONDENSE_MAGENTA_CAULDRON_BEHAVIOR);

        registerGroup(DyeColor.LIGHT_BLUE,
                ModCauldrons.LIGHT_BLUE_WATER_CAULDRON, ModCauldrons.CONDENSE_LIGHT_BLUE_WATER_CAULDRON,
                ModCauldrons.LUMINOUS_LIGHT_BLUE_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_CAULDRON,
                ModItems.LIGHT_BLUE_WATER_BUCKET, ModItems.CONDENSE_LIGHT_BLUE_WATER_BUCKET,
                ModItems.LUMINOUS_LIGHT_BLUE_WATER_BUCKET, ModItems.LUMINOUS_CONDENSE_LIGHT_BLUE_WATER_BUCKET,
                ColorCauldronBehavior.LIGHT_BLUE_CAULDRON_BEHAVIOR, CondenseCauldronBehavior.CONDENSE_LIGHT_BLUE_CAULDRON_BEHAVIOR,
                LuminousCauldronBehavior.LUMINOUS_LIGHT_BLUE_CAULDRON_BEHAVIOR, LuminousCondenseCauldronBehavior.LUMINOUS_CONDENSE_LIGHT_BLUE_CAULDRON_BEHAVIOR);

        registerGroup(DyeColor.YELLOW,
                ModCauldrons.YELLOW_WATER_CAULDRON, ModCauldrons.CONDENSE_YELLOW_WATER_CAULDRON,
                ModCauldrons.LUMINOUS_YELLOW_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_YELLOW_WATER_CAULDRON,
                ModItems.YELLOW_WATER_BUCKET, ModItems.CONDENSE_YELLOW_WATER_BUCKET,
                ModItems.LUMINOUS_YELLOW_WATER_BUCKET, ModItems.LUMINOUS_CONDENSE_YELLOW_WATER_BUCKET,
                ColorCauldronBehavior.YELLOW_CAULDRON_BEHAVIOR, CondenseCauldronBehavior.CONDENSE_YELLOW_CAULDRON_BEHAVIOR,
                LuminousCauldronBehavior.LUMINOUS_YELLOW_CAULDRON_BEHAVIOR, LuminousCondenseCauldronBehavior.LUMINOUS_CONDENSE_YELLOW_CAULDRON_BEHAVIOR);

        registerGroup(DyeColor.LIME,
                ModCauldrons.LIME_WATER_CAULDRON, ModCauldrons.CONDENSE_LIME_WATER_CAULDRON,
                ModCauldrons.LUMINOUS_LIME_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_LIME_WATER_CAULDRON,
                ModItems.LIME_WATER_BUCKET, ModItems.CONDENSE_LIME_WATER_BUCKET,
                ModItems.LUMINOUS_LIME_WATER_BUCKET, ModItems.LUMINOUS_CONDENSE_LIME_WATER_BUCKET,
                ColorCauldronBehavior.LIME_CAULDRON_BEHAVIOR, CondenseCauldronBehavior.CONDENSE_LIME_CAULDRON_BEHAVIOR,
                LuminousCauldronBehavior.LUMINOUS_LIME_CAULDRON_BEHAVIOR, LuminousCondenseCauldronBehavior.LUMINOUS_CONDENSE_LIME_CAULDRON_BEHAVIOR);

        registerGroup(DyeColor.PINK,
                ModCauldrons.PINK_WATER_CAULDRON, ModCauldrons.CONDENSE_PINK_WATER_CAULDRON,
                ModCauldrons.LUMINOUS_PINK_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_PINK_WATER_CAULDRON,
                ModItems.PINK_WATER_BUCKET, ModItems.CONDENSE_PINK_WATER_BUCKET,
                ModItems.LUMINOUS_PINK_WATER_BUCKET, ModItems.LUMINOUS_CONDENSE_PINK_WATER_BUCKET,
                ColorCauldronBehavior.PINK_CAULDRON_BEHAVIOR, CondenseCauldronBehavior.CONDENSE_PINK_CAULDRON_BEHAVIOR,
                LuminousCauldronBehavior.LUMINOUS_PINK_CAULDRON_BEHAVIOR, LuminousCondenseCauldronBehavior.LUMINOUS_CONDENSE_PINK_CAULDRON_BEHAVIOR);

        registerGroup(DyeColor.GRAY,
                ModCauldrons.GRAY_WATER_CAULDRON, ModCauldrons.CONDENSE_GRAY_WATER_CAULDRON,
                ModCauldrons.LUMINOUS_GRAY_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_GRAY_WATER_CAULDRON,
                ModItems.GRAY_WATER_BUCKET, ModItems.CONDENSE_GRAY_WATER_BUCKET,
                ModItems.LUMINOUS_GRAY_WATER_BUCKET, ModItems.LUMINOUS_CONDENSE_GRAY_WATER_BUCKET,
                ColorCauldronBehavior.GRAY_CAULDRON_BEHAVIOR, CondenseCauldronBehavior.CONDENSE_GRAY_CAULDRON_BEHAVIOR,
                LuminousCauldronBehavior.LUMINOUS_GRAY_CAULDRON_BEHAVIOR, LuminousCondenseCauldronBehavior.LUMINOUS_CONDENSE_GRAY_CAULDRON_BEHAVIOR);

        registerGroup(DyeColor.LIGHT_GRAY,
                ModCauldrons.LIGHT_GRAY_WATER_CAULDRON, ModCauldrons.CONDENSE_LIGHT_GRAY_WATER_CAULDRON,
                ModCauldrons.LUMINOUS_LIGHT_GRAY_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_CAULDRON,
                ModItems.LIGHT_GRAY_WATER_BUCKET, ModItems.CONDENSE_LIGHT_GRAY_WATER_BUCKET,
                ModItems.LUMINOUS_LIGHT_GRAY_WATER_BUCKET, ModItems.LUMINOUS_CONDENSE_LIGHT_GRAY_WATER_BUCKET,
                ColorCauldronBehavior.LIGHT_GRAY_CAULDRON_BEHAVIOR, CondenseCauldronBehavior.CONDENSE_LIGHT_GRAY_CAULDRON_BEHAVIOR,
                LuminousCauldronBehavior.LUMINOUS_LIGHT_GRAY_CAULDRON_BEHAVIOR, LuminousCondenseCauldronBehavior.LUMINOUS_CONDENSE_LIGHT_GRAY_CAULDRON_BEHAVIOR);

        registerGroup(DyeColor.CYAN,
                ModCauldrons.CYAN_WATER_CAULDRON, ModCauldrons.CONDENSE_CYAN_WATER_CAULDRON,
                ModCauldrons.LUMINOUS_CYAN_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_CYAN_WATER_CAULDRON,
                ModItems.CYAN_WATER_BUCKET, ModItems.CONDENSE_CYAN_WATER_BUCKET,
                ModItems.LUMINOUS_CYAN_WATER_BUCKET, ModItems.LUMINOUS_CONDENSE_CYAN_WATER_BUCKET,
                ColorCauldronBehavior.CYAN_CAULDRON_BEHAVIOR, CondenseCauldronBehavior.CONDENSE_CYAN_CAULDRON_BEHAVIOR,
                LuminousCauldronBehavior.LUMINOUS_CYAN_CAULDRON_BEHAVIOR, LuminousCondenseCauldronBehavior.LUMINOUS_CONDENSE_CYAN_CAULDRON_BEHAVIOR);

        registerGroup(DyeColor.PURPLE,
                ModCauldrons.PURPLE_WATER_CAULDRON, ModCauldrons.CONDENSE_PURPLE_WATER_CAULDRON,
                ModCauldrons.LUMINOUS_PURPLE_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_PURPLE_WATER_CAULDRON,
                ModItems.PURPLE_WATER_BUCKET, ModItems.CONDENSE_PURPLE_WATER_BUCKET,
                ModItems.LUMINOUS_PURPLE_WATER_BUCKET, ModItems.LUMINOUS_CONDENSE_PURPLE_WATER_BUCKET,
                ColorCauldronBehavior.PURPLE_CAULDRON_BEHAVIOR, CondenseCauldronBehavior.CONDENSE_PURPLE_CAULDRON_BEHAVIOR,
                LuminousCauldronBehavior.LUMINOUS_PURPLE_CAULDRON_BEHAVIOR, LuminousCondenseCauldronBehavior.LUMINOUS_CONDENSE_PURPLE_CAULDRON_BEHAVIOR);

        registerGroup(DyeColor.BLUE,
                ModCauldrons.BLUE_WATER_CAULDRON, ModCauldrons.CONDENSE_BLUE_WATER_CAULDRON,
                ModCauldrons.LUMINOUS_BLUE_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_BLUE_WATER_CAULDRON,
                ModItems.BLUE_WATER_BUCKET, ModItems.CONDENSE_BLUE_WATER_BUCKET,
                ModItems.LUMINOUS_BLUE_WATER_BUCKET, ModItems.LUMINOUS_CONDENSE_BLUE_WATER_BUCKET,
                ColorCauldronBehavior.BLUE_CAULDRON_BEHAVIOR, CondenseCauldronBehavior.CONDENSE_BLUE_CAULDRON_BEHAVIOR,
                LuminousCauldronBehavior.LUMINOUS_BLUE_CAULDRON_BEHAVIOR, LuminousCondenseCauldronBehavior.LUMINOUS_CONDENSE_BLUE_CAULDRON_BEHAVIOR);

        registerGroup(DyeColor.BROWN,
                ModCauldrons.BROWN_WATER_CAULDRON, ModCauldrons.CONDENSE_BROWN_WATER_CAULDRON,
                ModCauldrons.LUMINOUS_BROWN_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_BROWN_WATER_CAULDRON,
                ModItems.BROWN_WATER_BUCKET, ModItems.CONDENSE_BROWN_WATER_BUCKET,
                ModItems.LUMINOUS_BROWN_WATER_BUCKET, ModItems.LUMINOUS_CONDENSE_BROWN_WATER_BUCKET,
                ColorCauldronBehavior.BROWN_CAULDRON_BEHAVIOR, CondenseCauldronBehavior.CONDENSE_BROWN_CAULDRON_BEHAVIOR,
                LuminousCauldronBehavior.LUMINOUS_BROWN_CAULDRON_BEHAVIOR, LuminousCondenseCauldronBehavior.LUMINOUS_CONDENSE_BROWN_CAULDRON_BEHAVIOR);

        registerGroup(DyeColor.GREEN,
                ModCauldrons.GREEN_WATER_CAULDRON, ModCauldrons.CONDENSE_GREEN_WATER_CAULDRON,
                ModCauldrons.LUMINOUS_GREEN_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_GREEN_WATER_CAULDRON,
                ModItems.GREEN_WATER_BUCKET, ModItems.CONDENSE_GREEN_WATER_BUCKET,
                ModItems.LUMINOUS_GREEN_WATER_BUCKET, ModItems.LUMINOUS_CONDENSE_GREEN_WATER_BUCKET,
                ColorCauldronBehavior.GREEN_CAULDRON_BEHAVIOR, CondenseCauldronBehavior.CONDENSE_GREEN_CAULDRON_BEHAVIOR,
                LuminousCauldronBehavior.LUMINOUS_GREEN_CAULDRON_BEHAVIOR, LuminousCondenseCauldronBehavior.LUMINOUS_CONDENSE_GREEN_CAULDRON_BEHAVIOR);

        registerGroup(DyeColor.RED,
                ModCauldrons.RED_WATER_CAULDRON, ModCauldrons.CONDENSE_RED_WATER_CAULDRON,
                ModCauldrons.LUMINOUS_RED_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_RED_WATER_CAULDRON,
                ModItems.RED_WATER_BUCKET, ModItems.CONDENSE_RED_WATER_BUCKET,
                ModItems.LUMINOUS_RED_WATER_BUCKET, ModItems.LUMINOUS_CONDENSE_RED_WATER_BUCKET,
                ColorCauldronBehavior.RED_CAULDRON_BEHAVIOR, CondenseCauldronBehavior.CONDENSE_RED_CAULDRON_BEHAVIOR,
                LuminousCauldronBehavior.LUMINOUS_RED_CAULDRON_BEHAVIOR, LuminousCondenseCauldronBehavior.LUMINOUS_CONDENSE_RED_CAULDRON_BEHAVIOR);

        registerGroup(DyeColor.BLACK,
                ModCauldrons.BLACK_WATER_CAULDRON, ModCauldrons.CONDENSE_BLACK_WATER_CAULDRON,
                ModCauldrons.LUMINOUS_BLACK_WATER_CAULDRON, ModCauldrons.LUMINOUS_CONDENSE_BLACK_WATER_CAULDRON,
                ModItems.BLACK_WATER_BUCKET, ModItems.CONDENSE_BLACK_WATER_BUCKET,
                ModItems.LUMINOUS_BLACK_WATER_BUCKET, ModItems.LUMINOUS_CONDENSE_BLACK_WATER_BUCKET,
                ColorCauldronBehavior.BLACK_CAULDRON_BEHAVIOR, CondenseCauldronBehavior.CONDENSE_BLACK_CAULDRON_BEHAVIOR,
                LuminousCauldronBehavior.LUMINOUS_BLACK_CAULDRON_BEHAVIOR, LuminousCondenseCauldronBehavior.LUMINOUS_CONDENSE_BLACK_CAULDRON_BEHAVIOR);
    }

    public static CauldronGroup getGroup(DyeColor color) {
        return GROUPS_BY_COLOR.get(color);
    }

    public static synchronized void init() {
        for (CauldronGroup group : ALL_GROUPS) {
            setupBucketFillOnEmpty(group);
            setupGroup(group, group.normalMap(), group.normalBucket(), group.normalCauldron(), Variant.NORMAL);
            setupGroup(group, group.condenseMap(), group.condenseBucket(), group.condenseCauldron(), Variant.CONDENSED);
            setupGroup(group, group.luminousMap(), group.luminousBucket(), group.luminousCauldron(), Variant.LUMINOUS);
            setupGroup(group, group.luminousCondenseMap(), group.luminousCondenseBucket(), group.luminousCondenseCauldron(), Variant.LUMINOUS_CONDENSED);
        }

        // Vanilla Water Cauldron dyed with DyeItem -> becomes normal colored cauldron
        for (Item item : BuiltInRegistries.ITEM) {
            if (item instanceof DyeItem dye) {
                CauldronInteraction.WATER.map().put(item, (state, level, pos, player, hand, stack) -> {
                    CauldronGroup targetGroup = GROUPS_BY_COLOR.get(dye.getDyeColor());
                    if (targetGroup == null) return InteractionResult.PASS;
                    if (!level.isClientSide()) {
                        int currentLevel = state.getValue(LEVEL);
                        level.setBlock(pos, targetGroup.normalCauldron().get().defaultBlockState().setValue(LEVEL, currentLevel), 3);
                        if (!player.isCreative()) {
                            stack.shrink(1);
                        }
                        player.awardStat(Stats.USE_CAULDRON);
                        level.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    }
                    return InteractionResult.SUCCESS;
                });
            }
        }
    }

    private enum Variant {
        NORMAL, CONDENSED, LUMINOUS, LUMINOUS_CONDENSED
    }

    private static void setupBucketFillOnEmpty(CauldronGroup group) {
        registerBucketToEmpty(group.normalBucket().get(), group.normalCauldron());
        registerBucketToEmpty(group.condenseBucket().get(), group.condenseCauldron());
        registerBucketToEmpty(group.luminousBucket().get(), group.luminousCauldron());
        registerBucketToEmpty(group.luminousCondenseBucket().get(), group.luminousCondenseCauldron());
    }

    private static void registerBucketToEmpty(Item bucket, Supplier<Block> targetCauldron) {
        CauldronInteraction.EMPTY.map().put(bucket, (state, level, pos, player, hand, stack) -> {
            if (!level.isClientSide()) {
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(bucket));
                level.setBlockAndUpdate(pos, targetCauldron.get().defaultBlockState().setValue(LEVEL, 1));
                level.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.gameEvent(null, net.minecraft.world.level.gameevent.GameEvent.FLUID_PLACE, pos);
            }
            return InteractionResult.SUCCESS;
        });
    }

    private static void setupGroup(
            CauldronGroup group,
            CauldronInteraction.InteractionMap map,
            Supplier<Item> selfBucket,
            Supplier<Block> selfCauldron,
            Variant variant
    ) {
        var interactionMap = map.map();

        // 1. Take bucket - empties the cauldron completely in lite
        interactionMap.put(Items.BUCKET, (state, level, pos, player, hand, stack) -> {
            if (!state.hasProperty(LEVEL)) return InteractionResult.PASS;
            int currentLevel = state.getValue(LEVEL);
            if (currentLevel <= 0) return InteractionResult.PASS;

            if (!level.isClientSide()) {
                ItemStack filledBucket = new ItemStack(selfBucket.get());
                player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, filledBucket));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(Items.BUCKET));

                level.setBlock(pos, Blocks.CAULDRON.defaultBlockState(), 3);
                level.playSound(null, pos, SoundEvents.BUCKET_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            return InteractionResult.SUCCESS;
        });

        // 2. Same bucket adds 1 level up to level 3
        interactionMap.put(selfBucket.get(), (state, level, pos, player, hand, stack) -> {
            if (!state.hasProperty(LEVEL)) return InteractionResult.PASS;
            int currentLevel = state.getValue(LEVEL);
            if (currentLevel >= 3) return InteractionResult.PASS;

            if (!level.isClientSide()) {
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(selfBucket.get()));
                level.setBlockAndUpdate(pos, state.setValue(LEVEL, currentLevel + 1));
                level.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            return InteractionResult.SUCCESS;
        });

        // 3. Dyeable items (leather armor, wolf armor, horse armor)
        CauldronInteraction dyeableInteraction = (state, level, pos, player, hand, stack) -> {
            if (!state.hasProperty(LEVEL)) return InteractionResult.PASS;
            int currentLevel = state.getValue(LEVEL);
            if (currentLevel <= 0) return InteractionResult.PASS;

            if (!level.isClientSide()) {
                int cauldronRgb = ColoredWaterUtil.getCauldronColor(state.getBlock()) & 0xFFFFFF;
                int finalRgb = cauldronRgb;

                DyedItemColor existing = stack.get(DataComponents.DYED_COLOR);
                if (existing != null) {
                    finalRgb = ColoredWaterUtil.blendRgb(existing.rgb() & 0xFFFFFF, 1, cauldronRgb, 1);
                }

                if (stack.getCount() == 1) {
                    stack.set(DataComponents.DYED_COLOR, new DyedItemColor(finalRgb));
                } else {
                    ItemStack dyedResult = stack.split(1);
                    dyedResult.set(DataComponents.DYED_COLOR, new DyedItemColor(finalRgb));
                    player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, dyedResult));
                }

                player.awardStat(Stats.USE_CAULDRON);
                int newLevel = currentLevel - 1;
                if (newLevel <= 0) {
                    level.setBlock(pos, Blocks.CAULDRON.defaultBlockState(), 3);
                } else {
                    level.setBlock(pos, state.setValue(LEVEL, newLevel), 3);
                }
                level.playSound(null, pos, SoundEvents.ARMOR_EQUIP_LEATHER.value(), SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            return InteractionResult.SUCCESS;
        };

        interactionMap.put(Items.LEATHER_HELMET, dyeableInteraction);
        interactionMap.put(Items.LEATHER_CHESTPLATE, dyeableInteraction);
        interactionMap.put(Items.LEATHER_LEGGINGS, dyeableInteraction);
        interactionMap.put(Items.LEATHER_BOOTS, dyeableInteraction);
        interactionMap.put(Items.LEATHER_HORSE_ARMOR, dyeableInteraction);
        interactionMap.put(Items.WOLF_ARMOR, dyeableInteraction);

        // 4. Shulker Boxes
        DyeColor dyeColor = group.dyeColor();
        Item targetShulker = ShulkerBoxBlock.getBlockByColor(dyeColor).asItem();
        CauldronInteraction shulkerInteraction = (state, level, pos, player, hand, stack) -> {
            if (!state.hasProperty(LEVEL)) return InteractionResult.PASS;
            int currentLevel = state.getValue(LEVEL);
            if (currentLevel <= 0 || stack.is(targetShulker)) return InteractionResult.PASS;

            if (!level.isClientSide()) {
                ItemStack result = stack.transmuteCopy(targetShulker, 1);
                player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, result));
                player.awardStat(Stats.USE_CAULDRON);
                int newLevel = currentLevel - 1;
                if (newLevel <= 0) {
                    level.setBlock(pos, Blocks.CAULDRON.defaultBlockState(), 3);
                } else {
                    level.setBlock(pos, state.setValue(LEVEL, newLevel), 3);
                }
                level.playSound(null, pos, SoundEvents.ARMOR_EQUIP_LEATHER.value(), SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            return InteractionResult.SUCCESS;
        };
        interactionMap.put(Items.SHULKER_BOX, shulkerInteraction);
        for (DyeColor col : DyeColor.values()) {
            interactionMap.put(ShulkerBoxBlock.getBlockByColor(col).asItem(), shulkerInteraction);
        }

        // 5. Banners
        Item targetBanner = BannerBlock.byColor(dyeColor).asItem();
        CauldronInteraction bannerInteraction = (state, level, pos, player, hand, stack) -> {
            if (!state.hasProperty(LEVEL)) return InteractionResult.PASS;
            int currentLevel = state.getValue(LEVEL);
            if (currentLevel <= 0 || stack.is(targetBanner)) return InteractionResult.PASS;

            if (!level.isClientSide()) {
                ItemStack result = stack.transmuteCopy(targetBanner, 1);
                player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, result));
                player.awardStat(Stats.USE_CAULDRON);
                int newLevel = currentLevel - 1;
                if (newLevel <= 0) {
                    level.setBlock(pos, Blocks.CAULDRON.defaultBlockState(), 3);
                } else {
                    level.setBlock(pos, state.setValue(LEVEL, newLevel), 3);
                }
                level.playSound(null, pos, SoundEvents.ARMOR_EQUIP_LEATHER.value(), SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            return InteractionResult.SUCCESS;
        };
        for (DyeColor col : DyeColor.values()) {
            interactionMap.put(BannerBlock.byColor(col).asItem(), bannerInteraction);
        }

        // 6. Redstone (Condense)
        if (variant == Variant.NORMAL || variant == Variant.LUMINOUS) {
            Supplier<Block> targetCauldron = variant == Variant.NORMAL ? group.condenseCauldron() : group.luminousCondenseCauldron();
            interactionMap.put(Items.REDSTONE, (state, level, pos, player, hand, stack) -> {
                if (!state.hasProperty(LEVEL)) return InteractionResult.PASS;
                if (!level.isClientSide()) {
                    int currentLevel = state.getValue(LEVEL);
                    level.setBlock(pos, targetCauldron.get().defaultBlockState().setValue(LEVEL, currentLevel), 3);
                    if (!player.isCreative()) stack.shrink(1);
                    player.awardStat(Stats.USE_CAULDRON);
                    level.playSound(null, pos, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 1.0F, 1.0F);
                }
                return InteractionResult.SUCCESS;
            });
        }

        // 7. Glowstone Dust (Luminous)
        if (variant == Variant.NORMAL || variant == Variant.CONDENSED) {
            Supplier<Block> targetCauldron = variant == Variant.NORMAL ? group.luminousCauldron() : group.luminousCondenseCauldron();
            interactionMap.put(Items.GLOWSTONE_DUST, (state, level, pos, player, hand, stack) -> {
                if (!state.hasProperty(LEVEL)) return InteractionResult.PASS;
                if (!level.isClientSide()) {
                    int currentLevel = state.getValue(LEVEL);
                    level.setBlock(pos, targetCauldron.get().defaultBlockState().setValue(LEVEL, currentLevel), 3);
                    if (!player.isCreative()) stack.shrink(1);
                    player.awardStat(Stats.USE_CAULDRON);
                    level.playSound(null, pos, SoundEvents.GLOW_INK_SAC_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
                }
                return InteractionResult.SUCCESS;
            });
        }

        // 8. Dye items (switch color of cauldron)
        CauldronInteraction dyeWater = (state, level, pos, player, hand, stack) -> {
            if (!(stack.getItem() instanceof DyeItem dye)) return InteractionResult.PASS;
            if (dye.getDyeColor() == group.dyeColor()) return InteractionResult.PASS;
            CauldronGroup targetGroup = GROUPS_BY_COLOR.get(dye.getDyeColor());
            if (targetGroup == null) return InteractionResult.PASS;

            Supplier<Block> targetCauldron = switch (variant) {
                case NORMAL -> targetGroup.normalCauldron();
                case CONDENSED -> targetGroup.condenseCauldron();
                case LUMINOUS -> targetGroup.luminousCauldron();
                case LUMINOUS_CONDENSED -> targetGroup.luminousCondenseCauldron();
            };

            if (!level.isClientSide()) {
                int currentLevel = state.getValue(LEVEL);
                level.setBlock(pos, targetCauldron.get().defaultBlockState().setValue(LEVEL, currentLevel), 3);
                if (!player.isCreative()) stack.shrink(1);
                player.awardStat(Stats.USE_CAULDRON);
                level.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            return InteractionResult.SUCCESS;
        };

        for (Item item : BuiltInRegistries.ITEM) {
            if (item instanceof DyeItem) {
                interactionMap.put(item, dyeWater);
            }
        }
    }
}
