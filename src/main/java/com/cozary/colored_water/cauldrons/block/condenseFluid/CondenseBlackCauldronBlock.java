package com.cozary.colored_water.cauldrons.block.condenseFluid;

import com.cozary.colored_water.cauldrons.block.base.PreBaseCauldronBlock;
import com.cozary.colored_water.cauldrons.registry.CondenseCauldronBehavior;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class CondenseBlackCauldronBlock extends PreBaseCauldronBlock {
    public static final IntegerProperty LEVEL = BlockStateProperties.LEVEL_CAULDRON;

    public CondenseBlackCauldronBlock() {
        super(Properties.copy(Blocks.CAULDRON).noOcclusion(),
                CondenseCauldronBehavior.CONDENSE_BLACK_CAULDRON_BEHAVIOR);
        this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, Integer.valueOf(1)));
    }

}
