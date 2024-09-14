package com.cozary.colored_water.cauldrons.block.comboFluid;

import com.cozary.colored_water.cauldrons.block.base.PreBaseCauldronBlock;
import com.cozary.colored_water.cauldrons.registry.LuminousCondenseCauldronBehavior;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class LuminousCondenseWhiteCauldronBlock extends PreBaseCauldronBlock {
    public static final IntegerProperty LEVEL = BlockStateProperties.LEVEL_CAULDRON;

    public LuminousCondenseWhiteCauldronBlock() {
        super(Properties.copy(Blocks.CAULDRON).noOcclusion().lightLevel((p_50886_) -> {
                    return 14;
                }),
                LuminousCondenseCauldronBehavior.LUMINOUS_CONDENSE_WHITE_CAULDRON_BEHAVIOR);
        this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, Integer.valueOf(1)));
    }

}
