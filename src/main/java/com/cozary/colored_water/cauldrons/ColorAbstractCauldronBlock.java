package com.cozary.colored_water.cauldrons;

import net.minecraft.block.AbstractCauldronBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.item.Item;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Map;

import static net.minecraft.block.LeveledCauldronBlock.LEVEL;

public abstract class ColorAbstractCauldronBlock extends AbstractCauldronBlock {
    public ColorAbstractCauldronBlock(Settings settings, CauldronBehavior.CauldronBehaviorMap behaviorMap) {
        super(settings, behaviorMap);
    }

    public static int getMaxLevel() {
        return 3;
    }

    public static IntProperty getLevelProperty() {
        return LEVEL;
    }

    public static int getFluidLevel(BlockState state) {
        return state.get(getLevelProperty());
    }

    public static boolean incrementFluidLevel(BlockState state, World world, BlockPos pos, boolean required, int amount) {
        int level = getFluidLevel(state) + amount;
        return setFluidLevel(state, world, pos, required, level);
    }

    public static boolean incrementFluidLevel(BlockState state, World world, BlockPos pos) {
        return incrementFluidLevel(state, world, pos, true, 1);
    }

    public static boolean setFluidLevel(BlockState state, World world, BlockPos pos, boolean required, int level) {
        int actualLevel = Math.max(0, Math.min(level, getMaxLevel()));

        if ((level != actualLevel && required) || getFluidLevel(state) == actualLevel) return false;

        return world.setBlockState(pos, actualLevel == 0 ? Blocks.CAULDRON.getDefaultState() : state.cycle(getLevelProperty()));
    }

}
