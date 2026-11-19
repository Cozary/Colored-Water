package com.cozary.colored_water.cauldrons.behaviour;

import com.cozary.colored_water.block.ColoredWaterCauldronBlock;
import com.cozary.colored_water.block.entity.ColoredWaterCauldronBlockEntity;
import com.cozary.colored_water.init.ModCauldrons;
import com.cozary.colored_water.init.ModItems;
import com.cozary.colored_water.mixin.CauldronInteractionDispatcherInvoker;
import com.cozary.colored_water.util.ColoredWaterUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.cauldron.CauldronInteractions;
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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Cauldron interactions and behaviors for Colored Water cauldrons.
 * <p>
 * - Fills and mixes colored water buckets with cauldrons.
 * - Dyes leather armor, wolf armor, and leather horse armor with color mixing
 * logic.
 * - Upgrades cauldron fluid with Redstone (condensed) and Glowstone Dust
 * (luminous).
 * - Blends dyes directly into the cauldron fluid.
 */
public interface ColoredWaterCauldronBehavior extends CauldronInteraction {

    CauldronInteraction COLOR_DYEABLE_ITEM = (state, level, blockPosition, player, hand, stack) -> {
        if (!(level.getBlockEntity(blockPosition) instanceof ColoredWaterCauldronBlockEntity cauldronBlockEntity))
            return InteractionResult.PASS;
        int currentLevel = state.getValue(LayeredCauldronBlock.LEVEL);
        if (currentLevel <= 0)
            return InteractionResult.PASS;

        if (!level.isClientSide()) {
            int cauldronRgb = ColoredWaterUtil.getRgb(cauldronBlockEntity.getColor());
            int finalRgb = cauldronRgb;

            DyedItemColor existingDyedColor = stack.get(DataComponents.DYED_COLOR);
            if (existingDyedColor != null) {
                int existingRgb = existingDyedColor.rgb() & 0xFFFFFF;
                finalRgb = ColoredWaterUtil.getRgb(ColoredWaterUtil.blend(existingRgb, 1, cauldronRgb, 1));
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
                level.setBlock(blockPosition, Blocks.CAULDRON.defaultBlockState(), 3);
            } else {
                level.setBlock(blockPosition, state.setValue(LayeredCauldronBlock.LEVEL, newLevel), 3);
            }
            level.playSound(null, blockPosition, SoundEvents.ARMOR_EQUIP_LEATHER.value(), SoundSource.BLOCKS, 1.0F, 1.0F);
        }
        return InteractionResult.SUCCESS;
    };

    static void put(CauldronInteraction.Dispatcher dispatcher, Item item, CauldronInteraction interaction) {
        ((CauldronInteractionDispatcherInvoker) dispatcher).coloredWater$put(item, interaction);
    }

    static void init() {

        // Fill with Colored Water Bucket
        put(CauldronInteractions.EMPTY, ModItems.COLORED_WATER_BUCKET.get(),
                (state, level, blockPosition, player, hand, stack) -> {
                    if (!level.isClientSide()) {
                        fillFromEmptyCauldron(level, blockPosition, player, hand, stack);
                    }
                    return InteractionResult.SUCCESS;
                });

        // Water Cauldron mix with Colored Water Bucket
        put(CauldronInteractions.WATER, ModItems.COLORED_WATER_BUCKET.get(),
                (state, level, blockPosition, player, hand, stack) -> {
                    int currentLevel = state.getValue(LayeredCauldronBlock.LEVEL);
                    if (currentLevel >= 3) {
                        return InteractionResult.PASS;
                    }
                    if (!level.isClientSide()) {
                        fillAndMixFromWaterCauldron(level, blockPosition, player, hand, stack, currentLevel);
                    }
                    return InteractionResult.SUCCESS;
                });

        var map = ColoredWaterCauldronBlock.COLORED_WATER_CAULDRON_BEHAVIOR;

        // Take water with Bucket
        put(map, Items.BUCKET, (state, level, blockPosition, player, hand, stack) -> {
            if (!(level.getBlockEntity(blockPosition) instanceof ColoredWaterCauldronBlockEntity cauldronBlockEntity)) {
                return InteractionResult.PASS;
            }
            int currentLevel = state.getValue(LayeredCauldronBlock.LEVEL);
            if (currentLevel <= 0)
                return InteractionResult.PASS;

            if (!level.isClientSide()) {
                ItemStack filledBucket = createBucketFromCauldron(cauldronBlockEntity);
                player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, filledBucket));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(Items.BUCKET));

                int newLevel = currentLevel - 1;
                if (newLevel <= 0) {
                    level.setBlock(blockPosition, Blocks.CAULDRON.defaultBlockState(), 3);
                } else {
                    level.setBlock(blockPosition, state.setValue(LayeredCauldronBlock.LEVEL, newLevel), 3);
                }
                level.playSound(null, blockPosition, SoundEvents.BUCKET_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            return InteractionResult.SUCCESS;
        });

        // Add/Mix Bucket (Water Bucket or Colored Water Bucket)
        CauldronInteraction addOrMixBucket = (state, level, blockPosition, player, hand, stack) -> {
            if (!(level.getBlockEntity(blockPosition) instanceof ColoredWaterCauldronBlockEntity cauldronBlockEntity)) {
                return InteractionResult.PASS;
            }
            int currentLevel = state.getValue(LayeredCauldronBlock.LEVEL);
            if (currentLevel >= 3)
                return InteractionResult.PASS;

            if (!level.isClientSide()) {
                mixBucketIntoCauldron(level, blockPosition, state, cauldronBlockEntity, player, hand, stack, currentLevel);
            }
            return InteractionResult.SUCCESS;
        };
        put(map, ModItems.COLORED_WATER_BUCKET.get(), addOrMixBucket);
        put(map, Items.WATER_BUCKET, addOrMixBucket);

        // Redstone (Condense)
        put(map, Items.REDSTONE, (state, level, blockPosition, player, hand, stack) -> {
            if (!(level.getBlockEntity(blockPosition) instanceof ColoredWaterCauldronBlockEntity cauldronBlockEntity)
                    || cauldronBlockEntity.isCondensed()) {
                return InteractionResult.PASS;
            }
            if (!level.isClientSide()) {
                int newColor = ColoredWaterUtil.withAlpha(cauldronBlockEntity.getColor(),
                        ColoredWaterUtil.CONDENSED_ALPHA);
                cauldronBlockEntity.setProperties(newColor, true, cauldronBlockEntity.getLuminosity());
                level.setBlock(blockPosition, state.setValue(ColoredWaterCauldronBlock.CONDENSED, true), 3);

                if (!player.isCreative())
                    stack.shrink(1);
                player.awardStat(Stats.USE_CAULDRON);
                level.playSound(null, blockPosition, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            return InteractionResult.SUCCESS;
        });

        // Glowstone Dust (Luminous)
        put(map, Items.GLOWSTONE_DUST, (state, level, blockPosition, player, hand, stack) -> {
            if (!(level.getBlockEntity(blockPosition) instanceof ColoredWaterCauldronBlockEntity cauldronBlockEntity)
                    || cauldronBlockEntity.getLuminosity() >= ColoredWaterUtil.MAX_LUMINOSITY) {
                return InteractionResult.PASS;
            }
            if (!level.isClientSide()) {
                cauldronBlockEntity.setProperties(cauldronBlockEntity.getColor(), cauldronBlockEntity.isCondensed(),
                        ColoredWaterUtil.MAX_LUMINOSITY);
                level.setBlock(blockPosition,
                        state.setValue(ColoredWaterCauldronBlock.LIGHT_LEVEL, ColoredWaterUtil.MAX_LUMINOSITY), 3);

                if (!player.isCreative())
                    stack.shrink(1);
                player.awardStat(Stats.USE_CAULDRON);
                level.playSound(null, blockPosition, SoundEvents.GLOW_INK_SAC_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            return InteractionResult.SUCCESS;
        });

        // Dyes (Mix Dye into Cauldron)
        CauldronInteraction dyeWater = (state, level, blockPosition, player, hand, stack) -> {
            if (!(level.getBlockEntity(blockPosition) instanceof ColoredWaterCauldronBlockEntity cauldronBlockEntity)
                    || !stack.has(DataComponents.DYE)) {
                return InteractionResult.PASS;
            }
            if (!level.isClientSide()) {
                mixDyeIntoCauldron(cauldronBlockEntity, stack);
                if (!player.isCreative())
                    stack.shrink(1);
                player.awardStat(Stats.USE_CAULDRON);
                level.playSound(null, blockPosition, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.sendBlockUpdated(blockPosition, state, state, 3);
            }
            return InteractionResult.SUCCESS;
        };
        for (DyeColor color : DyeColor.values()) {
            put(map, getDyeItem(color), dyeWater);
        }
        for (Item item : BuiltInRegistries.ITEM) {
            try {
                if (item.components().has(DataComponents.DYE)) {
                    put(map, item, dyeWater);
                }
            } catch (Exception ignored) {
            }
        }

        // Dyeable Items (Leather Armor, Wolf Armor, Horse Armor)
        put(map, Items.LEATHER_HELMET, COLOR_DYEABLE_ITEM);
        put(map, Items.LEATHER_CHESTPLATE, COLOR_DYEABLE_ITEM);
        put(map, Items.LEATHER_LEGGINGS, COLOR_DYEABLE_ITEM);
        put(map, Items.LEATHER_BOOTS, COLOR_DYEABLE_ITEM);
        put(map, Items.LEATHER_HORSE_ARMOR, COLOR_DYEABLE_ITEM);
        put(map, Items.WOLF_ARMOR, COLOR_DYEABLE_ITEM);

        // Shulker Boxes
        CauldronInteraction colorShulker = (state, level, blockPosition, player, hand, stack) -> {
            if (!(level.getBlockEntity(blockPosition) instanceof ColoredWaterCauldronBlockEntity cauldronBlockEntity))
                return InteractionResult.PASS;
            int currentLevel = state.getValue(LayeredCauldronBlock.LEVEL);
            if (currentLevel <= 0)
                return InteractionResult.PASS;

            int colorRgb = ColoredWaterUtil.getRgb(cauldronBlockEntity.getColor());
            DyeColor closestColor = ColoredWaterUtil.getClosestDyeColor(colorRgb);
            Item targetShulkerItem = getShulkerBoxItem(closestColor);

            if (stack.is(targetShulkerItem))
                return InteractionResult.PASS;

            if (!level.isClientSide()) {
                ItemStack result = stack.transmuteCopy(targetShulkerItem, 1);
                player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, result));

                player.awardStat(Stats.USE_CAULDRON);
                int newLevel = currentLevel - 1;
                if (newLevel <= 0) {
                    level.setBlock(blockPosition, Blocks.CAULDRON.defaultBlockState(), 3);
                } else {
                    level.setBlock(blockPosition, state.setValue(LayeredCauldronBlock.LEVEL, newLevel), 3);
                }
                level.playSound(null, blockPosition, SoundEvents.ARMOR_EQUIP_LEATHER.value(), SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            return InteractionResult.SUCCESS;
        };
        put(map, Items.SHULKER_BOX, colorShulker);
        for (DyeColor color : DyeColor.values()) {
            put(map, getShulkerBoxItem(color), colorShulker);
        }

        // Banners
        CauldronInteraction colorBanner = (state, level, blockPosition, player, hand, stack) -> {
            if (!(level.getBlockEntity(blockPosition) instanceof ColoredWaterCauldronBlockEntity cauldronBlockEntity))
                return InteractionResult.PASS;
            int currentLevel = state.getValue(LayeredCauldronBlock.LEVEL);
            if (currentLevel <= 0)
                return InteractionResult.PASS;

            int colorRgb = ColoredWaterUtil.getRgb(cauldronBlockEntity.getColor());
            DyeColor closestColor = ColoredWaterUtil.getClosestDyeColor(colorRgb);
            Item targetBannerItem = getBannerItem(closestColor);

            if (stack.is(targetBannerItem))
                return InteractionResult.PASS;

            if (!level.isClientSide()) {
                ItemStack result = stack.transmuteCopy(targetBannerItem, 1);
                player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, result));

                player.awardStat(Stats.USE_CAULDRON);
                int newLevel = currentLevel - 1;
                if (newLevel <= 0) {
                    level.setBlock(blockPosition, Blocks.CAULDRON.defaultBlockState(), 3);
                } else {
                    level.setBlock(blockPosition, state.setValue(LayeredCauldronBlock.LEVEL, newLevel), 3);
                }
                level.playSound(null, blockPosition, SoundEvents.ARMOR_EQUIP_LEATHER.value(), SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            return InteractionResult.SUCCESS;
        };
        for (DyeColor color : DyeColor.values()) {
            put(map, getBannerItem(color), colorBanner);
        }
    }

    static Item getShulkerBoxItem(DyeColor color) {
        return switch (color) {
            case WHITE -> Items.WHITE_SHULKER_BOX;
            case ORANGE -> Items.ORANGE_SHULKER_BOX;
            case MAGENTA -> Items.MAGENTA_SHULKER_BOX;
            case LIGHT_BLUE -> Items.LIGHT_BLUE_SHULKER_BOX;
            case YELLOW -> Items.YELLOW_SHULKER_BOX;
            case LIME -> Items.LIME_SHULKER_BOX;
            case PINK -> Items.PINK_SHULKER_BOX;
            case GRAY -> Items.GRAY_SHULKER_BOX;
            case LIGHT_GRAY -> Items.LIGHT_GRAY_SHULKER_BOX;
            case CYAN -> Items.CYAN_SHULKER_BOX;
            case PURPLE -> Items.PURPLE_SHULKER_BOX;
            case BLUE -> Items.BLUE_SHULKER_BOX;
            case BROWN -> Items.BROWN_SHULKER_BOX;
            case GREEN -> Items.GREEN_SHULKER_BOX;
            case RED -> Items.RED_SHULKER_BOX;
            case BLACK -> Items.BLACK_SHULKER_BOX;
        };
    }

    static Item getDyeItem(DyeColor color) {
        return switch (color) {
            case WHITE -> Items.WHITE_DYE;
            case ORANGE -> Items.ORANGE_DYE;
            case MAGENTA -> Items.MAGENTA_DYE;
            case LIGHT_BLUE -> Items.LIGHT_BLUE_DYE;
            case YELLOW -> Items.YELLOW_DYE;
            case LIME -> Items.LIME_DYE;
            case PINK -> Items.PINK_DYE;
            case GRAY -> Items.GRAY_DYE;
            case LIGHT_GRAY -> Items.LIGHT_GRAY_DYE;
            case CYAN -> Items.CYAN_DYE;
            case PURPLE -> Items.PURPLE_DYE;
            case BLUE -> Items.BLUE_DYE;
            case BROWN -> Items.BROWN_DYE;
            case GREEN -> Items.GREEN_DYE;
            case RED -> Items.RED_DYE;
            case BLACK -> Items.BLACK_DYE;
        };
    }

    static Item getBannerItem(DyeColor color) {
        return switch (color) {
            case WHITE -> Items.WHITE_BANNER;
            case ORANGE -> Items.ORANGE_BANNER;
            case MAGENTA -> Items.MAGENTA_BANNER;
            case LIGHT_BLUE -> Items.LIGHT_BLUE_BANNER;
            case YELLOW -> Items.YELLOW_BANNER;
            case LIME -> Items.LIME_BANNER;
            case PINK -> Items.PINK_BANNER;
            case GRAY -> Items.GRAY_BANNER;
            case LIGHT_GRAY -> Items.LIGHT_GRAY_BANNER;
            case CYAN -> Items.CYAN_BANNER;
            case PURPLE -> Items.PURPLE_BANNER;
            case BLUE -> Items.BLUE_BANNER;
            case BROWN -> Items.BROWN_BANNER;
            case GREEN -> Items.GREEN_BANNER;
            case RED -> Items.RED_BANNER;
            case BLACK -> Items.BLACK_BANNER;
        };
    }

    private static void mixDyeIntoCauldron(ColoredWaterCauldronBlockEntity cauldronBlockEntity, ItemStack dyeStack) {
        DyeColor dyeColor = dyeStack.get(DataComponents.DYE);
        if (dyeColor == null) return;
        int dyeRgb = dyeColor.getTextureDiffuseColor() & 0xFFFFFF;
        int cauldronColor = cauldronBlockEntity.getColor();
        int cauldronAlpha = cauldronBlockEntity.getAlpha();

        int mixedRgb = ColoredWaterUtil.getRgb(ColoredWaterUtil.blend(cauldronColor, 1, dyeRgb, 1));
        int mixedColor = ColoredWaterUtil.withAlpha(mixedRgb, cauldronAlpha);
        cauldronBlockEntity.setColor(mixedColor);
    }

    static void mixBucketIntoCauldron(Level level, BlockPos blockPosition, BlockState state, ColoredWaterCauldronBlockEntity cauldronBlockEntity, Player player, InteractionHand hand, ItemStack stack, int currentLevel) {
        int bucketColor = ColoredWaterUtil.getBucketFullColor(stack);
        int bucketLuminosity = ColoredWaterUtil.getBucketLuminosity(stack);
        int newLevel = currentLevel + 1;
        int cauldronWeight = currentLevel;
        int bucketWeight = 1;
        int totalWeight = cauldronWeight + bucketWeight;
        int mixedColor = ColoredWaterUtil.blend(cauldronBlockEntity.getColor(), cauldronWeight, bucketColor, bucketWeight);
        int mixedAlpha = ColoredWaterUtil.getAlpha(mixedColor);
        int mixedLuminosity = (cauldronBlockEntity.getLuminosity() * cauldronWeight + bucketLuminosity * bucketWeight) / totalWeight;
        boolean mixedCondensed = ColoredWaterUtil.isCondensedAlpha(mixedAlpha);

        cauldronBlockEntity.setProperties(mixedColor, mixedCondensed, mixedLuminosity);

        level.setBlock(blockPosition, state.setValue(LayeredCauldronBlock.LEVEL, newLevel)
                .setValue(ColoredWaterCauldronBlock.CONDENSED, mixedCondensed)
                .setValue(ColoredWaterCauldronBlock.LIGHT_LEVEL, mixedLuminosity), 3);

        player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, new ItemStack(Items.BUCKET)));
        player.awardStat(Stats.USE_CAULDRON);
        player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
        level.playSound(null, blockPosition, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
    }

    static ItemStack createBucketFromCauldron(ColoredWaterCauldronBlockEntity cauldronBlockEntity) {
        return ColoredWaterUtil.createBucketStack(cauldronBlockEntity);
    }

    static void fillFromEmptyCauldron(Level level, BlockPos blockPosition, Player player, InteractionHand hand, ItemStack bucketStack) {
        int fullColor = ColoredWaterUtil.getBucketFullColor(bucketStack);
        boolean bucketCondensed = ColoredWaterUtil.getBucketCondensed(bucketStack);
        int bucketLuminosity = ColoredWaterUtil.getBucketLuminosity(bucketStack);

        level.setBlock(blockPosition, ModCauldrons.COLORED_WATER_CAULDRON.get().defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, 1).setValue(ColoredWaterCauldronBlock.CONDENSED, bucketCondensed).setValue(ColoredWaterCauldronBlock.LIGHT_LEVEL, bucketLuminosity), 3);

        BlockEntity blockEntity = level.getBlockEntity(blockPosition);
        if (blockEntity instanceof ColoredWaterCauldronBlockEntity cauldronBlockEntity) {
            cauldronBlockEntity.setProperties(fullColor, bucketCondensed, bucketLuminosity);
        }

        player.setItemInHand(hand, ItemUtils.createFilledResult(bucketStack, player, new ItemStack(Items.BUCKET)));
        player.awardStat(Stats.USE_CAULDRON);
        player.awardStat(Stats.ITEM_USED.get(bucketStack.getItem()));
        level.playSound(null, blockPosition, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
    }

    static void fillAndMixFromWaterCauldron(Level level, BlockPos blockPosition, Player player, InteractionHand hand, ItemStack bucketStack, int currentLevel) {
        int bucketColor = ColoredWaterUtil.getBucketFullColor(bucketStack);
        int bucketLuminosity = ColoredWaterUtil.getBucketLuminosity(bucketStack);
        int newLevel = currentLevel + 1;
        int cauldronWeight = currentLevel;
        int bucketWeight = 1;
        int totalWeight = cauldronWeight + bucketWeight;
        int mixedColor = ColoredWaterUtil.blend(ColoredWaterUtil.DEFAULT_ARGB_NORMAL, cauldronWeight, bucketColor, bucketWeight);
        int mixedAlpha = ColoredWaterUtil.getAlpha(mixedColor);
        int mixedLuminosity = (0 * cauldronWeight + bucketLuminosity * bucketWeight) / totalWeight;
        boolean mixedCondensed = ColoredWaterUtil.isCondensedAlpha(mixedAlpha);

        level.setBlock(blockPosition, ModCauldrons.COLORED_WATER_CAULDRON.get().defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, newLevel).setValue(ColoredWaterCauldronBlock.CONDENSED, mixedCondensed).setValue(ColoredWaterCauldronBlock.LIGHT_LEVEL, mixedLuminosity), 3);

        BlockEntity blockEntity = level.getBlockEntity(blockPosition);
        if (blockEntity instanceof ColoredWaterCauldronBlockEntity cauldronBlockEntity) {
            cauldronBlockEntity.setProperties(mixedColor, mixedCondensed, mixedLuminosity);
        }

        player.setItemInHand(hand, ItemUtils.createFilledResult(bucketStack, player, new ItemStack(Items.BUCKET)));
        player.awardStat(Stats.USE_CAULDRON);
        player.awardStat(Stats.ITEM_USED.get(bucketStack.getItem()));
        level.playSound(null, blockPosition, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
    }
}
