package com.cozary.colored_water.cauldrons;

import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

import java.util.Map;

import static net.minecraft.world.level.block.LayeredCauldronBlock.LEVEL;

public abstract class ColorAbstractCauldronBlock extends AbstractCauldronBlock {

    public ColorAbstractCauldronBlock(
            Properties settings, Map<Item, CauldronInteraction> behaviorMap) {
        super(settings, behaviorMap);
    }

    public static int getMaxLevel() {
        return 3;
    }

    public static IntegerProperty getLevelProperty() {
        return LEVEL;
    }

    public static int getFluidLevel(BlockState state) {
        return state.getValue(getLevelProperty());
    }

    public static boolean incrementFluidLevel(BlockState state, Level world, BlockPos pos, boolean required, int amount) {
        int level = getFluidLevel(state) + amount;
        return setFluidLevel(state, world, pos, required, level);
    }

    public static boolean incrementFluidLevel(BlockState state, Level world, BlockPos pos) {
        return incrementFluidLevel(state, world, pos, true, 1);
    }

    public static boolean setFluidLevel(BlockState state, Level world, BlockPos pos, boolean required, int level) {
        int actualLevel = Math.max(0, Math.min(level, getMaxLevel()));

        if ((level != actualLevel && required) || getFluidLevel(state) == actualLevel) return false;

        return world.setBlockAndUpdate(pos, actualLevel == 0 ? Blocks.CAULDRON.defaultBlockState() : state.cycle(getLevelProperty()));
    }


}