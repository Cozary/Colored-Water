package com.cozary.colored_water.cauldrons.util;

import com.cozary.colored_water.cauldrons.block.base.AbstractLeveledCauldronBlock;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;

public class FluidLevelUtil {

    public static ItemStack exchangeStack(ItemStack inputStack, Player player, ItemStack outputStack) {
        if (player.getAbilities().instabuild) {
            if (!player.getInventory().contains(outputStack)) {
                player.getInventory().add(outputStack);
            }
            return inputStack;
        } else {
            inputStack.shrink(1);
            if (inputStack.isEmpty()) {
                return outputStack;
            }
            if (!player.getInventory().add(outputStack)) {
                player.drop(outputStack, false);
            }
            return inputStack;
        }
    }

    public static boolean canIncrementFluidLevel(BlockState state, int amount) {
        int currentLevel = getFluidLevel(state);
        int newLevel = currentLevel + amount;
        int maxLevel = getMaxFluidLevel(state);
        return maxLevel != -1 && newLevel >= 0 && newLevel <= maxLevel && currentLevel != newLevel;
    }

    public static boolean canIncrementFluidLevel(BlockState state) {
        return canIncrementFluidLevel(state, 1);
    }

    static int getFluidLevel(BlockState state) {
        if (state.getBlock() instanceof AbstractLeveledCauldronBlock) {
            return AbstractLeveledCauldronBlock.getFluidLevel(state);
        } else if (state.getBlock() instanceof LayeredCauldronBlock) {
            return state.getValue(LayeredCauldronBlock.LEVEL);
        } else if (state.is(Blocks.CAULDRON)) {
            return 0;
        }
        return -1;
    }

    static int getMaxFluidLevel(BlockState state) {
        if (state.getBlock() instanceof AbstractLeveledCauldronBlock) {
            return AbstractLeveledCauldronBlock.getMaxLevel();
        } else if (state.getBlock() instanceof LayeredCauldronBlock) {
            return 3;
        } else if (state.is(Blocks.CAULDRON)) {
            return 0;
        }
        return -1;
    }
}
