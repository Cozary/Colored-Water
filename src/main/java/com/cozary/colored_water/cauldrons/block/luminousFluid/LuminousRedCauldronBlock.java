package com.cozary.colored_water.cauldrons.block.luminousFluid;

import com.cozary.colored_water.cauldrons.block.base.PreBaseCauldronBlock;
import com.cozary.colored_water.cauldrons.registry.LuminousCauldronBehavior;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class LuminousRedCauldronBlock extends PreBaseCauldronBlock {
    public static final IntegerProperty LEVEL = BlockStateProperties.LEVEL_CAULDRON;

    public LuminousRedCauldronBlock() {
        super(Properties.copy(Blocks.CAULDRON).noOcclusion().lightLevel((p_50886_) -> {
                    return 14;
                }),
                LuminousCauldronBehavior.LUMINOUS_RED_CAULDRON_BEHAVIOR);
        this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, Integer.valueOf(1)));
    }

}
