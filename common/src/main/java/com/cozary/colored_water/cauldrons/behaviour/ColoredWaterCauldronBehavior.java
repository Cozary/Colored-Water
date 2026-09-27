package com.cozary.colored_water.cauldrons.behaviour;

import com.cozary.colored_water.block.ColoredWaterCauldronBlock;
import com.cozary.colored_water.block.entity.ColoredWaterCauldronBlockEntity;
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

    CauldronInteraction COLOR_DYEABLE_ITEM = (state, level, pos, player, hand, stack) -> {
        if (!(level.getBlockEntity(pos) instanceof ColoredWaterCauldronBlockEntity cauldronBlockEntity))
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
                level.setBlock(pos, Blocks.CAULDRON.defaultBlockState(), 3);
            } else {
                level.setBlock(pos, state.setValue(LayeredCauldronBlock.LEVEL, newLevel), 3);
            }
            level.playSound(null, pos, SoundEvents.ARMOR_EQUIP_LEATHER.value(), SoundSource.BLOCKS, 1.0F, 1.0F);
        }
        return InteractionResult.SUCCESS;
    };

    static void init() {

        // Fill with Colored Water Bucket
        CauldronInteraction.EMPTY.map().put(ModItems.COLORED_WATER_BUCKET.get(),
                (state, level, pos, player, hand, stack) -> {
                    if (!level.isClientSide()) {
                        fillFromEmptyCauldron(level, pos, player, hand, stack);
                    }
                    return InteractionResult.SUCCESS;
                });

        // Water Cauldron mix with Colored Water Bucket
        CauldronInteraction.WATER.map().put(ModItems.COLORED_WATER_BUCKET.get(),
                (state, level, pos, player, hand, stack) -> {
                    int currentLevel = state.getValue(LayeredCauldronBlock.LEVEL);
                    if (currentLevel >= 3) {
                        return InteractionResult.PASS;
                    }
                    if (!level.isClientSide()) {
                        fillAndMixFromWaterCauldron(level, pos, player, hand, stack, currentLevel);
                    }
                    return InteractionResult.SUCCESS;
                });

        var map = ColoredWaterCauldronBlock.COLORED_WATER_CAULDRON_BEHAVIOR.map();

        // Take water with Bucket
        map.put(Items.BUCKET, (state, level, pos, player, hand, stack) -> {
            if (!(level.getBlockEntity(pos) instanceof ColoredWaterCauldronBlockEntity cauldronBlockEntity)) {
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
                    level.setBlock(pos, Blocks.CAULDRON.defaultBlockState(), 3);
                } else {
                    level.setBlock(pos, state.setValue(LayeredCauldronBlock.LEVEL, newLevel), 3);
                }
                level.playSound(null, pos, SoundEvents.BUCKET_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            return InteractionResult.SUCCESS;
        });

        // Add/Mix Bucket (Water Bucket or Colored Water Bucket)
        CauldronInteraction addOrMixBucket = (state, level, pos, player, hand, stack) -> {
            if (!(level.getBlockEntity(pos) instanceof ColoredWaterCauldronBlockEntity cauldronBlockEntity)) {
                return InteractionResult.PASS;
            }
            int currentLevel = state.getValue(LayeredCauldronBlock.LEVEL);
            if (currentLevel >= 3)
                return InteractionResult.PASS;

            if (!level.isClientSide()) {
                mixBucketIntoCauldron(level, pos, state, cauldronBlockEntity, player, hand, stack, currentLevel);
            }
            return InteractionResult.SUCCESS;
        };
        map.put(ModItems.COLORED_WATER_BUCKET.get(), addOrMixBucket);
        map.put(Items.WATER_BUCKET, addOrMixBucket);

        // Redstone (Condense)
        map.put(Items.REDSTONE, (state, level, pos, player, hand, stack) -> {
            if (!(level.getBlockEntity(pos) instanceof ColoredWaterCauldronBlockEntity cauldronBlockEntity)
                    || cauldronBlockEntity.isCondensed()) {
                return InteractionResult.PASS;
            }
            if (!level.isClientSide()) {
                int newColor = ColoredWaterUtil.withAlpha(cauldronBlockEntity.getColor(),
                        ColoredWaterUtil.CONDENSED_ALPHA);
                cauldronBlockEntity.setProperties(newColor, true, cauldronBlockEntity.getLuminosity());
                level.setBlock(pos, state.setValue(ColoredWaterCauldronBlock.CONDENSED, true), 3);

                if (!player.isCreative())
                    stack.shrink(1);
                player.awardStat(Stats.USE_CAULDRON);
                level.playSound(null, pos, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            return InteractionResult.SUCCESS;
        });

        // Glowstone Dust (Luminous)
        map.put(Items.GLOWSTONE_DUST, (state, level, pos, player, hand, stack) -> {
            if (!(level.getBlockEntity(pos) instanceof ColoredWaterCauldronBlockEntity cauldronBlockEntity)
                    || cauldronBlockEntity.getLuminosity() >= ColoredWaterUtil.MAX_LUMINOSITY) {
                return InteractionResult.PASS;
            }
            if (!level.isClientSide()) {
                cauldronBlockEntity.setProperties(cauldronBlockEntity.getColor(), cauldronBlockEntity.isCondensed(),
                        ColoredWaterUtil.MAX_LUMINOSITY);
                level.setBlock(pos,
                        state.setValue(ColoredWaterCauldronBlock.LIGHT_LEVEL, ColoredWaterUtil.MAX_LUMINOSITY), 3);

                if (!player.isCreative())
                    stack.shrink(1);
                player.awardStat(Stats.USE_CAULDRON);
                level.playSound(null, pos, SoundEvents.GLOW_INK_SAC_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            return InteractionResult.SUCCESS;
        });

        // Dyes (Mix Dye into Cauldron)
        CauldronInteraction dyeWater = (state, level, pos, player, hand, stack) -> {
            if (!(level.getBlockEntity(pos) instanceof ColoredWaterCauldronBlockEntity cauldronBlockEntity)
                    || !(stack.getItem() instanceof DyeItem dye)) {
                return InteractionResult.PASS;
            }
            if (!level.isClientSide()) {
                mixDyeIntoCauldron(cauldronBlockEntity, dye);
                if (!player.isCreative())
                    stack.shrink(1);
                player.awardStat(Stats.USE_CAULDRON);
                level.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.sendBlockUpdated(pos, state, state, 3);
            }
            return InteractionResult.SUCCESS;
        };
        for (Item item : BuiltInRegistries.ITEM) {
            if (item instanceof DyeItem) {
                map.put(item, dyeWater);
            }
        }

        // Dyeable Items (Leather Armor, Wolf Armor, Horse Armor)
        map.put(Items.LEATHER_HELMET, COLOR_DYEABLE_ITEM);
        map.put(Items.LEATHER_CHESTPLATE, COLOR_DYEABLE_ITEM);
        map.put(Items.LEATHER_LEGGINGS, COLOR_DYEABLE_ITEM);
        map.put(Items.LEATHER_BOOTS, COLOR_DYEABLE_ITEM);
        map.put(Items.LEATHER_HORSE_ARMOR, COLOR_DYEABLE_ITEM);
        map.put(Items.WOLF_ARMOR, COLOR_DYEABLE_ITEM);

        // Shulker Boxes
        CauldronInteraction colorShulker = (state, level, pos, player, hand, stack) -> {
            if (!(level.getBlockEntity(pos) instanceof ColoredWaterCauldronBlockEntity cauldronBlockEntity))
                return InteractionResult.PASS;
            int currentLevel = state.getValue(LayeredCauldronBlock.LEVEL);
            if (currentLevel <= 0)
                return InteractionResult.PASS;

            int colorRgb = ColoredWaterUtil.getRgb(cauldronBlockEntity.getColor());
            DyeColor closestColor = ColoredWaterUtil.getClosestDyeColor(colorRgb);
            Item targetShulkerItem = ShulkerBoxBlock.getBlockByColor(closestColor).asItem();

            if (stack.is(targetShulkerItem))
                return InteractionResult.PASS;

            if (!level.isClientSide()) {
                ItemStack result = stack.transmuteCopy(targetShulkerItem, 1);
                player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, result));

                player.awardStat(Stats.USE_CAULDRON);
                int newLevel = currentLevel - 1;
                if (newLevel <= 0) {
                    level.setBlock(pos, Blocks.CAULDRON.defaultBlockState(), 3);
                } else {
                    level.setBlock(pos, state.setValue(LayeredCauldronBlock.LEVEL, newLevel), 3);
                }
                level.playSound(null, pos, SoundEvents.ARMOR_EQUIP_LEATHER.value(), SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            return InteractionResult.SUCCESS;
        };
        map.put(Items.SHULKER_BOX, colorShulker);
        for (DyeColor color : DyeColor.values()) {
            map.put(ShulkerBoxBlock.getBlockByColor(color).asItem(), colorShulker);
        }

        // Banners
        CauldronInteraction colorBanner = (state, level, pos, player, hand, stack) -> {
            if (!(level.getBlockEntity(pos) instanceof ColoredWaterCauldronBlockEntity cauldronBlockEntity))
                return InteractionResult.PASS;
            int currentLevel = state.getValue(LayeredCauldronBlock.LEVEL);
            if (currentLevel <= 0)
                return InteractionResult.PASS;

            int colorRgb = ColoredWaterUtil.getRgb(cauldronBlockEntity.getColor());
            DyeColor closestColor = ColoredWaterUtil.getClosestDyeColor(colorRgb);
            Item targetBannerItem = BannerBlock.byColor(closestColor).asItem();

            if (stack.is(targetBannerItem))
                return InteractionResult.PASS;

            if (!level.isClientSide()) {
                ItemStack result = stack.transmuteCopy(targetBannerItem, 1);
                player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, result));

                player.awardStat(Stats.USE_CAULDRON);
                int newLevel = currentLevel - 1;
                if (newLevel <= 0) {
                    level.setBlock(pos, Blocks.CAULDRON.defaultBlockState(), 3);
                } else {
                    level.setBlock(pos, state.setValue(LayeredCauldronBlock.LEVEL, newLevel), 3);
                }
                level.playSound(null, pos, SoundEvents.ARMOR_EQUIP_LEATHER.value(), SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            return InteractionResult.SUCCESS;
        };
        for (DyeColor color : DyeColor.values()) {
            map.put(BannerBlock.byColor(color).asItem(), colorBanner);
        }
    }

    private static void mixBucketIntoCauldron(Level level, BlockPos pos, BlockState state, ColoredWaterCauldronBlockEntity cauldronBlockEntity, Player player, InteractionHand hand, ItemStack stack, int currentLevel) {
        int bucketColor = stack.is(ModItems.COLORED_WATER_BUCKET.get()) ? ColoredWaterUtil.getBucketFullColor(stack) : ColoredWaterUtil.DEFAULT_ARGB_NORMAL;
        int bucketLuminosity = stack.is(ModItems.COLORED_WATER_BUCKET.get()) ? ColoredWaterUtil.getBucketLuminosity(stack) : 0;
        int newLevel = currentLevel + 1;
        int cauldronWeight = currentLevel;
        int bucketWeight = 1;
        int totalWeight = cauldronWeight + bucketWeight;
        int mixedColor = ColoredWaterUtil.blend(cauldronBlockEntity.getColor(), cauldronWeight, bucketColor, bucketWeight);
        int mixedAlpha = ColoredWaterUtil.getAlpha(mixedColor);
        int mixedLuminosity = (cauldronBlockEntity.getLuminosity() * cauldronWeight + bucketLuminosity * bucketWeight) / totalWeight;
        boolean mixedCondensed = ColoredWaterUtil.isCondensedAlpha(mixedAlpha);

        cauldronBlockEntity.setProperties(mixedColor, mixedCondensed, mixedLuminosity);

        level.setBlock(pos, state.setValue(LayeredCauldronBlock.LEVEL, newLevel).setValue(ColoredWaterCauldronBlock.CONDENSED, mixedCondensed).setValue(ColoredWaterCauldronBlock.LIGHT_LEVEL, mixedLuminosity), 3);

        player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, new ItemStack(Items.BUCKET)));
        player.awardStat(Stats.USE_CAULDRON);
        player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
        level.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
    }

    private static void mixDyeIntoCauldron(ColoredWaterCauldronBlockEntity cauldronBlockEntity, DyeItem dye) {
        int dyeRgb = dye.getDyeColor().getTextureDiffuseColor() & 0xFFFFFF;
        int cauldronColor = cauldronBlockEntity.getColor();
        int cauldronAlpha = cauldronBlockEntity.getAlpha();

        int mixedRgb = ColoredWaterUtil.getRgb(ColoredWaterUtil.blend(cauldronColor, 1, dyeRgb, 1));
        int mixedColor = ColoredWaterUtil.withAlpha(mixedRgb, cauldronAlpha);
        cauldronBlockEntity.setColor(mixedColor);
    }

    static ItemStack createBucketFromCauldron(ColoredWaterCauldronBlockEntity cauldronBlockEntity) {
        return ColoredWaterUtil.createBucketStack(cauldronBlockEntity);
    }

    static void fillFromEmptyCauldron(Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack bucketStack) {
        int fullColor = ColoredWaterUtil.getBucketFullColor(bucketStack);
        boolean bucketCondensed = ColoredWaterUtil.getBucketCondensed(bucketStack);
        int bucketLuminosity = ColoredWaterUtil.getBucketLuminosity(bucketStack);

        level.setBlock(pos, ModCauldrons.COLORED_WATER_CAULDRON.get().defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, 1).setValue(ColoredWaterCauldronBlock.CONDENSED, bucketCondensed).setValue(ColoredWaterCauldronBlock.LIGHT_LEVEL, bucketLuminosity), 3);

        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof ColoredWaterCauldronBlockEntity cauldronBlockEntity) {
            cauldronBlockEntity.setProperties(fullColor, bucketCondensed, bucketLuminosity);
        }

        player.setItemInHand(hand, ItemUtils.createFilledResult(bucketStack, player, new ItemStack(Items.BUCKET)));
        player.awardStat(Stats.USE_CAULDRON);
        player.awardStat(Stats.ITEM_USED.get(bucketStack.getItem()));
        level.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
    }

    static void fillAndMixFromWaterCauldron(Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack bucketStack, int currentLevel) {
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

        level.setBlock(pos, ModCauldrons.COLORED_WATER_CAULDRON.get().defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, newLevel).setValue(ColoredWaterCauldronBlock.CONDENSED, mixedCondensed).setValue(ColoredWaterCauldronBlock.LIGHT_LEVEL, mixedLuminosity), 3);

        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof ColoredWaterCauldronBlockEntity cauldronBlockEntity) {
            cauldronBlockEntity.setProperties(mixedColor, mixedCondensed, mixedLuminosity);
        }

        player.setItemInHand(hand, ItemUtils.createFilledResult(bucketStack, player, new ItemStack(Items.BUCKET)));
        player.awardStat(Stats.USE_CAULDRON);
        player.awardStat(Stats.ITEM_USED.get(bucketStack.getItem()));
        level.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
    }
}
