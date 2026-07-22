package com.cozary.colored_water.cauldrons.behaviour;

import com.cozary.colored_water.init.ModItems;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.gameevent.GameEvent;

import static net.minecraft.world.item.BucketItem.getEmptySuccessItem;

public interface CondenseCauldronBehavior extends CauldronInteraction {

    InteractionMap CONDENSE_BLACK_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("condense_black_cauldron_behavior");
    InteractionMap CONDENSE_BLUE_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("condense_blue_cauldron_behavior");
    InteractionMap CONDENSE_BROWN_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("condense_brown_cauldron_behavior");
    InteractionMap CONDENSE_CYAN_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("condense_cyan_cauldron_behavior");
    InteractionMap CONDENSE_GRAY_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("condense_gray_cauldron_behavior");
    InteractionMap CONDENSE_GREEN_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("condense_green_cauldron_behavior");
    InteractionMap CONDENSE_LIGHT_BLUE_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("condense_light_blue_cauldron_behavior");
    InteractionMap CONDENSE_LIGHT_GRAY_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("condense_light_gray_cauldron_behavior");
    InteractionMap CONDENSE_LIME_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("condense_lime_cauldron_behavior");
    InteractionMap CONDENSE_MAGENTA_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("condense_magenta_cauldron_behavior");
    InteractionMap CONDENSE_ORANGE_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("condense_orange_cauldron_behavior");
    InteractionMap CONDENSE_PINK_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("condense_pink_cauldron_behavior");
    InteractionMap CONDENSE_PURPLE_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("condense_purple_cauldron_behavior");
    InteractionMap CONDENSE_RED_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("condense_red_cauldron_behavior");
    InteractionMap CONDENSE_WHITE_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("condense_white_cauldron_behavior");
    InteractionMap CONDENSE_YELLOW_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("condense_yellow_cauldron_behavior");

    static void init() {
        CauldronInteraction.EMPTY.map().put(ModItems.COLORED_WATER_BUCKET.get(), (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide()) {
                Item item = stack.getItem();
                player.setItemInHand(hand, getEmptySuccessItem(stack, player));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }
            return InteractionResult.SUCCESS;
        });
    }
}
