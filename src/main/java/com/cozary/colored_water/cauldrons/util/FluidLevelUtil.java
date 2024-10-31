package com.cozary.colored_water.cauldrons.util;


import com.cozary.colored_water.cauldrons.ColorAbstractCauldronBlock;
import net.minecraft.block.AbstractCauldronBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class FluidLevelUtil {

    public static ItemStack exchangeStack(ItemStack inputStack, PlayerEntity player, ItemStack outputStack) {
        if (player.getAbilities().creativeMode) {
            if (!player.getInventory().contains(outputStack)) {
                player.getInventory().insertStack(outputStack);
            }
            return inputStack;
        } else {
            inputStack.decrement(1);
            if (inputStack.isEmpty()) {
                return outputStack;
            }
            if (!player.getInventory().insertStack(outputStack)) {
                player.dropItem(outputStack, false);
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
        if (state.getBlock() instanceof AbstractCauldronBlock) {
            return ColorAbstractCauldronBlock.getFluidLevel(state);
        } else if (state.getBlock() instanceof LeveledCauldronBlock) {
            return state.get(LeveledCauldronBlock.LEVEL);
        } else if (state.equals(Blocks.CAULDRON.getDefaultState())) {
            return 0;
        }
        return -1;
    }

    static int getMaxFluidLevel(BlockState state) {
        if (state.getBlock() instanceof AbstractCauldronBlock) {
            return ColorAbstractCauldronBlock.getMaxLevel();
        } else if (state.getBlock() instanceof LeveledCauldronBlock) {
            return 3;
        } else if (state.equals(Blocks.CAULDRON.getDefaultState())) {
            return 0;
        }
        return -1;
    }
}
