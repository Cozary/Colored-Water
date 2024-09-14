package com.cozary.colored_water.cauldrons.block.base;

import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

import java.util.Map;

public abstract class AbstractLeveledCauldronBlock extends AbstractCauldronBlock {
    public static final IntegerProperty LEVEL = BlockStateProperties.LEVEL_CAULDRON;

    public AbstractLeveledCauldronBlock(
            Properties settings, Map<Item, CauldronInteraction> behaviorMap) {
        super(settings, behaviorMap);
        this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, Integer.valueOf(1)));
    }

    public static IntegerProperty getLevelProperty() {
        return LEVEL;
    }

    public static int getMaxLevel() {
        return 3;
    }

    public static int getFluidLevel(BlockState state) {
        return state.getValue(getLevelProperty());
    }

    public static boolean setFluidLevel(BlockState state, Level world, BlockPos pos, boolean required, int level) {
        int actualLevel = Math.max(0, Math.min(level, getMaxLevel()));

        if ((level != actualLevel && required) || getFluidLevel(state) == actualLevel) return false;

        return world.setBlockAndUpdate(pos, actualLevel == 0 ? Blocks.CAULDRON.defaultBlockState() : state.cycle(getLevelProperty()));
    }

    public static boolean incrementFluidLevel(BlockState state, Level world, BlockPos pos, boolean required, int amount) {
        int level = getFluidLevel(state) + amount;
        return setFluidLevel(state, world, pos, required, level);
    }

    public static boolean incrementFluidLevel(BlockState state, Level world, BlockPos pos) {
        return incrementFluidLevel(state, world, pos, true, 1);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_51305_) {
        p_51305_.add(LEVEL);
    }

    public boolean incrementFluidLevel(BlockState state, Level world, BlockPos pos, boolean required) {
        return incrementFluidLevel(state, world, pos, required, 1);
    }

    @Override
    public boolean isFull(BlockState state) {
        return state.getValue(getLevelProperty()) == getMaxLevel();
    }

}