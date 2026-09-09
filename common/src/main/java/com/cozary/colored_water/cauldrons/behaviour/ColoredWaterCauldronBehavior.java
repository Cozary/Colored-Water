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
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BannerBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public interface ColoredWaterCauldronBehavior extends CauldronInteraction {

    CauldronInteraction COLOR_DYEABLE_ITEM = (state, level, pos, player, hand, stack) -> {
        if (!(level.getBlockEntity(pos) instanceof ColoredWaterCauldronBlockEntity coloredBe)) return InteractionResult.PASS;
        int currentLevel = state.getValue(LayeredCauldronBlock.LEVEL);
        if (currentLevel <= 0) return InteractionResult.PASS;

        if (!level.isClientSide()) {
            int cauldronRgb = ColoredWaterUtil.getRgb(coloredBe.getColor());
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

        // Empty Cauldron -> Fill with Colored Water Bucket
        CauldronInteraction.EMPTY.map().put(ModItems.COLORED_WATER_BUCKET.get(), (state, level, pos, player, hand, stack) -> {
            if (!level.isClientSide()) {
                fillFromEmptyCauldron(level, pos, player, hand, stack);
            }
            return InteractionResult.SUCCESS;
        });

        // Water Cauldron -> Mix in Colored Water Bucket
        CauldronInteraction.WATER.map().put(ModItems.COLORED_WATER_BUCKET.get(), (state, level, pos, player, hand, stack) -> {
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
            if (!(level.getBlockEntity(pos) instanceof ColoredWaterCauldronBlockEntity coloredBe)) {
                return InteractionResult.PASS;
            }
            int currentLevel = state.getValue(LayeredCauldronBlock.LEVEL);
            if (currentLevel <= 0) return InteractionResult.PASS;

            if (!level.isClientSide()) {
                ItemStack filledBucket = createBucketFromCauldron(coloredBe);
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
            if (!(level.getBlockEntity(pos) instanceof ColoredWaterCauldronBlockEntity coloredBe)) {
                return InteractionResult.PASS;
            }
            int currentLevel = state.getValue(LayeredCauldronBlock.LEVEL);
            if (currentLevel >= 3) return InteractionResult.PASS;

            if (!level.isClientSide()) {
                mixBucketIntoCauldron(level, pos, state, coloredBe, player, hand, stack, currentLevel);
            }
            return InteractionResult.SUCCESS;
        };
        map.put(ModItems.COLORED_WATER_BUCKET.get(), addOrMixBucket);
        map.put(Items.WATER_BUCKET, addOrMixBucket);

        // Redstone (Condense)
        map.put(Items.REDSTONE, (state, level, pos, player, hand, stack) -> {
            if (!(level.getBlockEntity(pos) instanceof ColoredWaterCauldronBlockEntity coloredBe) || coloredBe.isCondensed()) {
                return InteractionResult.PASS;
            }
            if (!level.isClientSide()) {
                int newColor = ColoredWaterUtil.withAlpha(coloredBe.getColor(), ColoredWaterUtil.CONDENSED_ALPHA);
                coloredBe.setProperties(newColor, true, coloredBe.getLuminosity());
                level.setBlock(pos, state.setValue(ColoredWaterCauldronBlock.CONDENSED, true), 3);

                if (!player.isCreative()) stack.shrink(1);
                player.awardStat(Stats.USE_CAULDRON);
                level.playSound(null, pos, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            return InteractionResult.SUCCESS;
        });

        // Glowstone Dust (Luminous)
        map.put(Items.GLOWSTONE_DUST, (state, level, pos, player, hand, stack) -> {
            if (!(level.getBlockEntity(pos) instanceof ColoredWaterCauldronBlockEntity coloredBe) || coloredBe.getLuminosity() >= ColoredWaterUtil.MAX_LUMINOSITY) {
                return InteractionResult.PASS;
            }
            if (!level.isClientSide()) {
                coloredBe.setProperties(coloredBe.getColor(), coloredBe.isCondensed(), ColoredWaterUtil.MAX_LUMINOSITY);
                level.setBlock(pos, state.setValue(ColoredWaterCauldronBlock.LIGHT_LEVEL, ColoredWaterUtil.MAX_LUMINOSITY), 3);

                if (!player.isCreative()) stack.shrink(1);
                player.awardStat(Stats.USE_CAULDRON);
                level.playSound(null, pos, SoundEvents.GLOW_INK_SAC_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            return InteractionResult.SUCCESS;
        });

        // Dyes (Mix Dye into Cauldron)
        CauldronInteraction dyeWater = (state, level, pos, player, hand, stack) -> {
            if (!(level.getBlockEntity(pos) instanceof ColoredWaterCauldronBlockEntity coloredBe) || !(stack.getItem() instanceof DyeItem dye)) {
                return InteractionResult.PASS;
            }
            if (!level.isClientSide()) {
                mixDyeIntoCauldron(coloredBe, dye);
                if (!player.isCreative()) stack.shrink(1);
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
            if (!(level.getBlockEntity(pos) instanceof ColoredWaterCauldronBlockEntity coloredBe)) return InteractionResult.PASS;
            int currentLevel = state.getValue(LayeredCauldronBlock.LEVEL);
            if (currentLevel <= 0) return InteractionResult.PASS;

            int colorRgb = ColoredWaterUtil.getRgb(coloredBe.getColor());
            DyeColor closestColor = ColoredWaterUtil.getClosestDyeColor(colorRgb);
            Item targetShulkerItem = ShulkerBoxBlock.getBlockByColor(closestColor).asItem();

            if (stack.is(targetShulkerItem)) return InteractionResult.PASS;

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
            if (!(level.getBlockEntity(pos) instanceof ColoredWaterCauldronBlockEntity coloredBe)) return InteractionResult.PASS;
            int currentLevel = state.getValue(LayeredCauldronBlock.LEVEL);
            if (currentLevel <= 0) return InteractionResult.PASS;

            int colorRgb = ColoredWaterUtil.getRgb(coloredBe.getColor());
            DyeColor closestColor = ColoredWaterUtil.getClosestDyeColor(colorRgb);
            Item targetBannerItem = BannerBlock.byColor(closestColor).asItem();

            if (stack.is(targetBannerItem)) return InteractionResult.PASS;

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

    // --- Helper Logic ---

    private static void mixBucketIntoCauldron(Level level, BlockPos pos, BlockState state, ColoredWaterCauldronBlockEntity coloredBe, Player player, InteractionHand hand, ItemStack stack, int currentLevel) {
        int bColor = stack.is(ModItems.COLORED_WATER_BUCKET.get())
                ? ColoredWaterUtil.getBucketFullColor(stack)
                : ColoredWaterUtil.DEFAULT_ARGB_NORMAL;
        int bLuminosity = stack.is(ModItems.COLORED_WATER_BUCKET.get())
                ? ColoredWaterUtil.getBucketLuminosity(stack)
                : 0;

        int newLevel = currentLevel + 1;
        int cWeight = currentLevel;
        int bWeight = 1;
        int totalWeight = cWeight + bWeight;

        int mixedColor = ColoredWaterUtil.blend(coloredBe.getColor(), cWeight, bColor, bWeight);
        int mixedAlpha = ColoredWaterUtil.getAlpha(mixedColor);
        int mixedLuminosity = (coloredBe.getLuminosity() * cWeight + bLuminosity * bWeight) / totalWeight;
        boolean mixedCondensed = ColoredWaterUtil.isCondensedAlpha(mixedAlpha);

        coloredBe.setProperties(mixedColor, mixedCondensed, mixedLuminosity);

        level.setBlock(pos, state.setValue(LayeredCauldronBlock.LEVEL, newLevel)
                .setValue(ColoredWaterCauldronBlock.CONDENSED, mixedCondensed)
                .setValue(ColoredWaterCauldronBlock.LIGHT_LEVEL, mixedLuminosity), 3);

        player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, new ItemStack(Items.BUCKET)));
        player.awardStat(Stats.USE_CAULDRON);
        player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
        level.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
    }

    private static void mixDyeIntoCauldron(ColoredWaterCauldronBlockEntity coloredBe, DyeItem dye) {
        int dyeRgb = dye.getDyeColor().getTextureDiffuseColor() & 0xFFFFFF;
        int cColor = coloredBe.getColor();
        int cAlpha = coloredBe.getAlpha();

        int mixedRgb = ColoredWaterUtil.getRgb(ColoredWaterUtil.blend(cColor, 1, dyeRgb, 1));
        int mixedColor = ColoredWaterUtil.withAlpha(mixedRgb, cAlpha);
        coloredBe.setColor(mixedColor);
    }

    static ItemStack createBucketFromCauldron(ColoredWaterCauldronBlockEntity coloredBe) {
        return ColoredWaterUtil.createBucketStack(coloredBe);
    }

    static void fillFromEmptyCauldron(Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack bucketStack) {
        int fullColor = ColoredWaterUtil.getBucketFullColor(bucketStack);
        boolean bCondensed = ColoredWaterUtil.getBucketCondensed(bucketStack);
        int bLuminosity = ColoredWaterUtil.getBucketLuminosity(bucketStack);

        level.setBlock(pos, ModCauldrons.COLORED_WATER_CAULDRON.get().defaultBlockState()
                .setValue(LayeredCauldronBlock.LEVEL, 1)
                .setValue(ColoredWaterCauldronBlock.CONDENSED, bCondensed)
                .setValue(ColoredWaterCauldronBlock.LIGHT_LEVEL, bLuminosity), 3);

        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof ColoredWaterCauldronBlockEntity coloredBe) {
            coloredBe.setProperties(fullColor, bCondensed, bLuminosity);
        }

        player.setItemInHand(hand, ItemUtils.createFilledResult(bucketStack, player, new ItemStack(Items.BUCKET)));
        player.awardStat(Stats.USE_CAULDRON);
        player.awardStat(Stats.ITEM_USED.get(bucketStack.getItem()));
        level.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
    }

    static void fillAndMixFromWaterCauldron(Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack bucketStack, int currentLevel) {
        int bColor = ColoredWaterUtil.getBucketFullColor(bucketStack);
        int bLuminosity = ColoredWaterUtil.getBucketLuminosity(bucketStack);

        int newLevel = currentLevel + 1;
        int cWeight = currentLevel;
        int bWeight = 1;
        int totalWeight = cWeight + bWeight;

        int mixedColor = ColoredWaterUtil.blend(ColoredWaterUtil.DEFAULT_ARGB_NORMAL, cWeight, bColor, bWeight);
        int mixedAlpha = ColoredWaterUtil.getAlpha(mixedColor);
        int mixedLuminosity = (0 * cWeight + bLuminosity * bWeight) / totalWeight;
        boolean mixedCondensed = ColoredWaterUtil.isCondensedAlpha(mixedAlpha);

        level.setBlock(pos, ModCauldrons.COLORED_WATER_CAULDRON.get().defaultBlockState()
                .setValue(LayeredCauldronBlock.LEVEL, newLevel)
                .setValue(ColoredWaterCauldronBlock.CONDENSED, mixedCondensed)
                .setValue(ColoredWaterCauldronBlock.LIGHT_LEVEL, mixedLuminosity), 3);

        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof ColoredWaterCauldronBlockEntity coloredBe) {
            coloredBe.setProperties(mixedColor, mixedCondensed, mixedLuminosity);
        }

        player.setItemInHand(hand, ItemUtils.createFilledResult(bucketStack, player, new ItemStack(Items.BUCKET)));
        player.awardStat(Stats.USE_CAULDRON);
        player.awardStat(Stats.ITEM_USED.get(bucketStack.getItem()));
        level.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
    }
}

