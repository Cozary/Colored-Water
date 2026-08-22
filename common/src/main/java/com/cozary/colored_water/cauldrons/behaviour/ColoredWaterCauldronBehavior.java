package com.cozary.colored_water.cauldrons.behaviour;

import com.cozary.colored_water.block.ColoredWaterCauldronBlock;
import com.cozary.colored_water.block.entity.ColoredWaterCauldronBlockEntity;
import com.cozary.colored_water.init.ModCauldrons;
import com.cozary.colored_water.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
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
import net.minecraft.world.item.component.CustomData;
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
            int cauldronRgb = coloredBe.getColor() & 0xFFFFFF;
            int finalRgb = cauldronRgb;

            DyedItemColor existingDyedColor = stack.get(DataComponents.DYED_COLOR);
            if (existingDyedColor != null) {
                int existingRgb = existingDyedColor.rgb() & 0xFFFFFF;
                int eRed = (existingRgb >> 16) & 0xFF;
                int eGreen = (existingRgb >> 8) & 0xFF;
                int eBlue = existingRgb & 0xFF;

                int cRed = (cauldronRgb >> 16) & 0xFF;
                int cGreen = (cauldronRgb >> 8) & 0xFF;
                int cBlue = cauldronRgb & 0xFF;

                int mRed = (eRed + cRed) / 2;
                int mGreen = (eGreen + cGreen) / 2;
                int mBlue = (eBlue + cBlue) / 2;

                finalRgb = (mRed << 16) | (mGreen << 8) | mBlue;
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
                coloredBe.setCondensed(true);
                coloredBe.setAlpha(255);
                coloredBe.setColor((255 << 24) | (coloredBe.getColor() & 0xFFFFFF));
                level.setBlock(pos, state.setValue(ColoredWaterCauldronBlock.CONDENSED, true), 3);

                if (!player.isCreative()) stack.shrink(1);
                player.awardStat(Stats.USE_CAULDRON);
                level.playSound(null, pos, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            return InteractionResult.SUCCESS;
        });

        // Glowstone Dust (Liminous)
        map.put(Items.GLOWSTONE_DUST, (state, level, pos, player, hand, stack) -> {
            if (!(level.getBlockEntity(pos) instanceof ColoredWaterCauldronBlockEntity coloredBe) || coloredBe.getLuminosity() >= 15) {
                return InteractionResult.PASS;
            }
            if (!level.isClientSide()) {
                coloredBe.setLuminosity(15);
                level.setBlock(pos, state.setValue(ColoredWaterCauldronBlock.LIGHT_LEVEL, 15), 3);

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

            int colorRgb = coloredBe.getColor() & 0xFFFFFF;
            DyeColor closestColor = getClosestDyeColor(colorRgb);
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

            int colorRgb = coloredBe.getColor() & 0xFFFFFF;
            DyeColor closestColor = getClosestDyeColor(colorRgb);
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
        int bRgb = 0x3F76E4;
        boolean bCondensed = false;
        int bLuminosity = 0;
        int bAlpha = 0;

        if (stack.is(ModItems.COLORED_WATER_BUCKET.get())) {
            DyedItemColor dyedColor = stack.get(DataComponents.DYED_COLOR);
            if (dyedColor != null) {
                bRgb = dyedColor.rgb() & 0xFFFFFF;
            }

            CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
            if (customData != null) {
                CompoundTag tag = customData.copyTag();
                bCondensed = tag.getBooleanOr("Condensed", false);
                bLuminosity = tag.getIntOr("Luminosity", 0);
                if (tag.contains("Alpha")) {
                    bAlpha = tag.getIntOr("Alpha", 0);
                }
            }
        }

        if (bAlpha == 0) bAlpha = bCondensed ? 255 : 180;
        if (bCondensed) bAlpha = 255;

        int newLevel = currentLevel + 1;
        int cAlpha = coloredBe.getAlpha();
        int cColor = coloredBe.getColor();
        int cRed = (cColor >> 16) & 0xFF;
        int cGreen = (cColor >> 8) & 0xFF;
        int cBlue = cColor & 0xFF;

        int bRed = (bRgb >> 16) & 0xFF;
        int bGreen = (bRgb >> 8) & 0xFF;
        int bBlue = bRgb & 0xFF;

        int cWeight = currentLevel;
        int bWeight = 1;
        int totalWeight = cWeight + bWeight;

        int mixedAlpha = (cAlpha * cWeight + bAlpha * bWeight) / totalWeight;
        int mixedRed = (cRed * cWeight + bRed * bWeight) / totalWeight;
        int mixedGreen = (cGreen * cWeight + bGreen * bWeight) / totalWeight;
        int mixedBlue = (cBlue * cWeight + bBlue * bWeight) / totalWeight;

        int mixedLuminosity = (coloredBe.getLuminosity() * cWeight + bLuminosity * bWeight) / totalWeight;
        boolean mixedCondensed = mixedAlpha >= 220;

        int mixedColor = (mixedAlpha << 24) | (mixedRed << 16) | (mixedGreen << 8) | mixedBlue;

        coloredBe.setCondensed(mixedCondensed);
        coloredBe.setLuminosity(mixedLuminosity);
        coloredBe.setAlpha(mixedAlpha);
        coloredBe.setColor(mixedColor);

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
        int cRed = (cColor >> 16) & 0xFF;
        int cGreen = (cColor >> 8) & 0xFF;
        int cBlue = cColor & 0xFF;

        int dRed = (dyeRgb >> 16) & 0xFF;
        int dGreen = (dyeRgb >> 8) & 0xFF;
        int dBlue = dyeRgb & 0xFF;

        int mixedRed = (cRed + dRed) / 2;
        int mixedGreen = (cGreen + dGreen) / 2;
        int mixedBlue = (cBlue + dBlue) / 2;

        int mixedColor = (cAlpha << 24) | (mixedRed << 16) | (mixedGreen << 8) | mixedBlue;
        coloredBe.setColor(mixedColor);
    }

    static ItemStack createBucketFromCauldron(ColoredWaterCauldronBlockEntity coloredBe) {
        ItemStack stack = new ItemStack(ModItems.COLORED_WATER_BUCKET.get());
        int fullColor = coloredBe.getColor();
        int colorRgb = fullColor & 0xFFFFFF;
        int alpha = coloredBe.getAlpha();

        stack.set(DataComponents.DYED_COLOR, new DyedItemColor(colorRgb));

        CompoundTag tag = new CompoundTag();
        tag.putBoolean("Condensed", coloredBe.isCondensed());
        tag.putInt("Luminosity", coloredBe.getLuminosity());
        tag.putInt("Alpha", alpha);
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));

        return stack;
    }

    static void fillFromEmptyCauldron(Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack bucketStack) {
        int bRgb = 0x3F76E4;
        boolean bCondensed = false;
        int bLuminosity = 0;
        int bAlpha = 0;

        if (bucketStack.is(ModItems.COLORED_WATER_BUCKET.get())) {
            DyedItemColor dyedColor = bucketStack.get(DataComponents.DYED_COLOR);
            if (dyedColor != null) {
                bRgb = dyedColor.rgb() & 0xFFFFFF;
            }

            CustomData customData = bucketStack.get(DataComponents.CUSTOM_DATA);
            if (customData != null) {
                CompoundTag tag = customData.copyTag();
                bCondensed = tag.getBooleanOr("Condensed", false);
                bLuminosity = tag.getIntOr("Luminosity", 0);
                if (tag.contains("Alpha")) {
                    bAlpha = tag.getIntOr("Alpha", 0);
                }
            }
        }

        if (bAlpha == 0) bAlpha = bCondensed ? 255 : 180;
        if (bCondensed) bAlpha = 255;

        int fullColor = (bAlpha << 24) | (bRgb & 0xFFFFFF);

        level.setBlock(pos, ModCauldrons.COLORED_WATER_CAULDRON.get().defaultBlockState()
                .setValue(LayeredCauldronBlock.LEVEL, 1)
                .setValue(ColoredWaterCauldronBlock.CONDENSED, bCondensed)
                .setValue(ColoredWaterCauldronBlock.LIGHT_LEVEL, bLuminosity), 3);

        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof ColoredWaterCauldronBlockEntity coloredBe) {
            coloredBe.setCondensed(bCondensed);
            coloredBe.setLuminosity(bLuminosity);
            coloredBe.setAlpha(bAlpha);
            coloredBe.setColor(fullColor);
        }

        player.setItemInHand(hand, ItemUtils.createFilledResult(bucketStack, player, new ItemStack(Items.BUCKET)));
        player.awardStat(Stats.USE_CAULDRON);
        player.awardStat(Stats.ITEM_USED.get(bucketStack.getItem()));
        level.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
    }

    static void fillAndMixFromWaterCauldron(Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack bucketStack, int currentLevel) {
        int bRgb = 0x3F76E4;
        boolean bCondensed = false;
        int bLuminosity = 0;
        int bAlpha = 0;

        if (bucketStack.is(ModItems.COLORED_WATER_BUCKET.get())) {
            DyedItemColor dyedColor = bucketStack.get(DataComponents.DYED_COLOR);
            if (dyedColor != null) {
                bRgb = dyedColor.rgb() & 0xFFFFFF;
            }

            CustomData customData = bucketStack.get(DataComponents.CUSTOM_DATA);
            if (customData != null) {
                CompoundTag tag = customData.copyTag();
                bCondensed = tag.getBooleanOr("Condensed", false);
                bLuminosity = tag.getIntOr("Luminosity", 0);
                if (tag.contains("Alpha")) {
                    bAlpha = tag.getIntOr("Alpha", 0);
                }
            }
        }

        if (bAlpha == 0) bAlpha = bCondensed ? 255 : 180;
        if (bCondensed) bAlpha = 255;

        int cAlpha = 180;
        int cColor = 0x3F76E4;
        int cRed = (cColor >> 16) & 0xFF;
        int cGreen = (cColor >> 8) & 0xFF;
        int cBlue = cColor & 0xFF;

        int bRed = (bRgb >> 16) & 0xFF;
        int bGreen = (bRgb >> 8) & 0xFF;
        int bBlue = bRgb & 0xFF;

        int newLevel = currentLevel + 1;
        int cWeight = currentLevel;
        int bWeight = 1;
        int totalWeight = cWeight + bWeight;

        int mixedAlpha = (cAlpha * cWeight + bAlpha * bWeight) / totalWeight;
        int mixedRed = (cRed * cWeight + bRed * bWeight) / totalWeight;
        int mixedGreen = (cGreen * cWeight + bGreen * bWeight) / totalWeight;
        int mixedBlue = (cBlue * cWeight + bBlue * bWeight) / totalWeight;
        int mixedLuminosity = (0 * cWeight + bLuminosity * bWeight) / totalWeight;
        boolean mixedCondensed = mixedAlpha >= 220;

        int mixedColor = (mixedAlpha << 24) | (mixedRed << 16) | (mixedGreen << 8) | mixedBlue;

        level.setBlock(pos, ModCauldrons.COLORED_WATER_CAULDRON.get().defaultBlockState()
                .setValue(LayeredCauldronBlock.LEVEL, newLevel)
                .setValue(ColoredWaterCauldronBlock.CONDENSED, mixedCondensed)
                .setValue(ColoredWaterCauldronBlock.LIGHT_LEVEL, mixedLuminosity), 3);

        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof ColoredWaterCauldronBlockEntity coloredBe) {
            coloredBe.setCondensed(mixedCondensed);
            coloredBe.setLuminosity(mixedLuminosity);
            coloredBe.setAlpha(mixedAlpha);
            coloredBe.setColor(mixedColor);
        }

        player.setItemInHand(hand, ItemUtils.createFilledResult(bucketStack, player, new ItemStack(Items.BUCKET)));
        player.awardStat(Stats.USE_CAULDRON);
        player.awardStat(Stats.ITEM_USED.get(bucketStack.getItem()));
        level.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
    }

    static DyeColor getClosestDyeColor(int rgb) {
        int r = (rgb >> 16) & 0xFF;
        int g = (rgb >> 8) & 0xFF;
        int b = rgb & 0xFF;

        DyeColor closest = DyeColor.WHITE;
        double minDistance = Double.MAX_VALUE;

        for (DyeColor color : DyeColor.values()) {
            int dyeRgb = color.getTextureDiffuseColor() & 0xFFFFFF;
            int dr = r - ((dyeRgb >> 16) & 0xFF);
            int dg = g - ((dyeRgb >> 8) & 0xFF);
            int db = b - (dyeRgb & 0xFF);
            double dist = dr * dr + dg * dg + db * db;
            if (dist < minDistance) {
                minDistance = dist;
                closest = color;
            }
        }
        return closest;
    }
}
