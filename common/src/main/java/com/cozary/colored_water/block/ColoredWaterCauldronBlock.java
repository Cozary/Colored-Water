package com.cozary.colored_water.block;

import com.cozary.colored_water.block.entity.ColoredWaterCauldronBlockEntity;
import com.cozary.colored_water.init.ModCauldrons;
import com.cozary.colored_water.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class ColoredWaterCauldronBlock extends LayeredCauldronBlock implements EntityBlock {

    public static final BooleanProperty CONDENSED = ColoredWaterBlock.CONDENSED;
    public static final IntegerProperty LIGHT_LEVEL = ColoredWaterBlock.LIGHT_LEVEL;
    public static final CauldronInteraction.InteractionMap COLORED_WATER_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("colored_water_cauldron_behavior");

    public ColoredWaterCauldronBlock(Properties properties) {
        super(Biome.Precipitation.RAIN, COLORED_WATER_CAULDRON_BEHAVIOR, properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(LEVEL, 1)
                .setValue(CONDENSED, false)
                .setValue(LIGHT_LEVEL, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<net.minecraft.world.level.block.Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(CONDENSED, LIGHT_LEVEL);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ColoredWaterCauldronBlockEntity(pos, state);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        BlockEntity be = level.getBlockEntity(pos);
        if (!(be instanceof ColoredWaterCauldronBlockEntity coloredBe)) {
            return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
        }

        int currentLevel = state.getValue(LEVEL);

        // 1. Take liquid with Empty Bucket (allowed at ANY level: 1, 2, or 3)
        if (stack.is(Items.BUCKET)) {
            if (currentLevel > 0) {
                if (!level.isClientSide()) {
                    ItemStack filledBucket = createBucketFromCauldron(coloredBe);
                    player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, filledBucket));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(Items.BUCKET));

                    int newLevel = currentLevel - 1;
                    if (newLevel <= 0) {
                        level.setBlock(pos, Blocks.CAULDRON.defaultBlockState(), 3);
                    } else {
                        level.setBlock(pos, state.setValue(LEVEL, newLevel), 3);
                    }
                    level.playSound(null, pos, SoundEvents.BUCKET_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                }
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.PASS;
        }

        // 2. Add or mix liquid from Colored Water Bucket or Water Bucket
        if (stack.is(ModItems.COLORED_WATER_BUCKET.get()) || stack.is(Items.WATER_BUCKET)) {
            // Cannot add liquid if cauldron is already full (Level 3)!
            if (currentLevel >= 3) {
                return InteractionResult.PASS;
            }

            if (!level.isClientSide()) {
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

                // Pouring a bucket adds 1 level (currentLevel -> currentLevel + 1)
                int newLevel = currentLevel + 1;

                // Weighted fluid mixing based on current cauldron level (1 or 2) + new bucket (1 level weight)
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

                level.setBlock(pos, state.setValue(LEVEL, newLevel).setValue(CONDENSED, mixedCondensed).setValue(LIGHT_LEVEL, mixedLuminosity), 3);

                player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, new ItemStack(Items.BUCKET)));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
                level.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            return InteractionResult.SUCCESS;
        }

        // 3. Dye item used directly on cauldron
        if (stack.getItem() instanceof DyeItem dye) {
            if (!level.isClientSide()) {
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

                if (!player.isCreative()) stack.shrink(1);
                player.awardStat(Stats.USE_CAULDRON);
                level.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            return InteractionResult.SUCCESS;
        }

        // 4. Redstone used to condense
        if (stack.is(Items.REDSTONE)) {
            if (!coloredBe.isCondensed()) {
                if (!level.isClientSide()) {
                    coloredBe.setCondensed(true);
                    coloredBe.setAlpha(255);
                    int curColor = coloredBe.getColor();
                    coloredBe.setColor((255 << 24) | (curColor & 0xFFFFFF));
                    level.setBlock(pos, state.setValue(CONDENSED, true), 3);

                    if (!player.isCreative()) stack.shrink(1);
                    player.awardStat(Stats.USE_CAULDRON);
                    level.playSound(null, pos, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 1.0F, 1.0F);
                }
                return InteractionResult.SUCCESS;
            }
        }

        // 5. Glowstone Dust used for luminosity
        if (stack.is(Items.GLOWSTONE_DUST)) {
            if (coloredBe.getLuminosity() < 15) {
                if (!level.isClientSide()) {
                    coloredBe.setLuminosity(15);
                    level.setBlock(pos, state.setValue(LIGHT_LEVEL, 15), 3);

                    if (!player.isCreative()) stack.shrink(1);
                    player.awardStat(Stats.USE_CAULDRON);
                    level.playSound(null, pos, SoundEvents.GLOW_INK_SAC_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
                }
                return InteractionResult.SUCCESS;
            }
        }

        // 6. Glass Bottle
        if (stack.is(Items.GLASS_BOTTLE)) {
            if (currentLevel > 0) {
                if (!level.isClientSide()) {
                    ItemStack waterBottle = new ItemStack(Items.POTION);
                    player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, waterBottle));
                    player.awardStat(Stats.USE_CAULDRON);
                    player.awardStat(Stats.ITEM_USED.get(Items.GLASS_BOTTLE));

                    int newLevel = currentLevel - 1;
                    if (newLevel <= 0) {
                        level.setBlock(pos, Blocks.CAULDRON.defaultBlockState(), 3);
                    } else {
                        level.setBlock(pos, state.setValue(LEVEL, newLevel), 3);
                    }
                    level.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                }
                return InteractionResult.SUCCESS;
            }
        }

        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    private ItemStack createBucketFromCauldron(ColoredWaterCauldronBlockEntity coloredBe) {
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

        com.cozary.colored_water.ColoredWater.LOG.info("[ColoredWaterCauldron] Picked up water from cauldron: fullColor=#{}, colorRgb=#{}, alpha={}, condensed={}",
                Integer.toHexString(fullColor).toUpperCase(),
                String.format("%06X", colorRgb),
                alpha,
                coloredBe.isCondensed());

        return stack;
    }

    public static void fillFromEmptyCauldron(Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack bucketStack) {
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

        // First bucket into an empty cauldron sets LEVEL = 1
        level.setBlock(pos, ModCauldrons.COLORED_WATER_CAULDRON.get().defaultBlockState()
                .setValue(LEVEL, 1)
                .setValue(CONDENSED, bCondensed)
                .setValue(LIGHT_LEVEL, bLuminosity), 3);

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

    public static void fillAndMixFromWaterCauldron(Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack bucketStack, int currentLevel) {
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
                .setValue(LEVEL, newLevel)
                .setValue(CONDENSED, mixedCondensed)
                .setValue(LIGHT_LEVEL, mixedLuminosity), 3);

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
}
