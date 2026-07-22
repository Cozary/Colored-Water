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

public interface LuminousCauldronBehavior extends CauldronInteraction {

    InteractionMap LUMINOUS_BLACK_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("luminous_black_cauldron_behavior");
    InteractionMap LUMINOUS_BLUE_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("luminous_blue_cauldron_behavior");
    InteractionMap LUMINOUS_BROWN_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("luminous_brown_cauldron_behavior");
    InteractionMap LUMINOUS_CYAN_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("luminous_cyan_cauldron_behavior");
    InteractionMap LUMINOUS_GRAY_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("luminous_gray_cauldron_behavior");
    InteractionMap LUMINOUS_GREEN_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("luminous_green_cauldron_behavior");
    InteractionMap LUMINOUS_LIGHT_BLUE_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("luminous_light_blue_cauldron_behavior");
    InteractionMap LUMINOUS_LIGHT_GRAY_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("luminous_light_gray_cauldron_behavior");
    InteractionMap LUMINOUS_LIME_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("luminous_lime_cauldron_behavior");
    InteractionMap LUMINOUS_MAGENTA_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("luminous_magenta_cauldron_behavior");
    InteractionMap LUMINOUS_ORANGE_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("luminous_orange_cauldron_behavior");
    InteractionMap LUMINOUS_PINK_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("luminous_pink_cauldron_behavior");
    InteractionMap LUMINOUS_PURPLE_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("luminous_purple_cauldron_behavior");
    InteractionMap LUMINOUS_RED_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("luminous_red_cauldron_behavior");
    InteractionMap LUMINOUS_WHITE_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("luminous_white_cauldron_behavior");
    InteractionMap LUMINOUS_YELLOW_CAULDRON_BEHAVIOR = CauldronInteraction.newInteractionMap("luminous_yellow_cauldron_behavior");

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
