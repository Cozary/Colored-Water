package com.cozary.colored_water.cauldrons.block.baseFluid;

import com.cozary.colored_water.cauldrons.block.base.PreBaseCauldronBlock;
import com.cozary.colored_water.cauldrons.registry.CauldronBehavior;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class PurpleCauldronBlock extends PreBaseCauldronBlock {
    public static final IntegerProperty LEVEL = BlockStateProperties.LEVEL_CAULDRON;

    public PurpleCauldronBlock() {
        super(Properties.copy(Blocks.CAULDRON).noOcclusion(),
                CauldronBehavior.PURPLE_CAULDRON_BEHAVIOR);
        this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, Integer.valueOf(1)));
    }

}
