package com.cozary.colored_water.block;

import com.cozary.colored_water.block.entity.ColoredWaterCauldronBlockEntity;
import com.cozary.colored_water.particles.SparkleParticleOptions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jetbrains.annotations.Nullable;

public class ColoredWaterCauldronBlock extends LayeredCauldronBlock implements EntityBlock {

    public static final BooleanProperty CONDENSED = ColoredWaterBlock.CONDENSED;
    public static final IntegerProperty LIGHT_LEVEL = ColoredWaterBlock.LIGHT_LEVEL;
    public static final CauldronInteraction.InteractionMap COLORED_WATER_CAULDRON_BEHAVIOR = CauldronInteraction
            .newInteractionMap("colored_water_cauldron_behavior");

    public ColoredWaterCauldronBlock(Properties properties) {
        super(Biome.Precipitation.RAIN, COLORED_WATER_CAULDRON_BEHAVIOR, properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(LEVEL, 1)
                .setValue(CONDENSED, false)
                .setValue(LIGHT_LEVEL, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(CONDENSED, LIGHT_LEVEL);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ColoredWaterCauldronBlockEntity(pos, state);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (level.getBlockEntity(pos) instanceof ColoredWaterCauldronBlockEntity coloredBe) {
            int luminosity = coloredBe.getLuminosity();
            if (luminosity > 0) {
                float chance = (luminosity / 15.0F) * 0.4F;
                if (random.nextFloat() < chance) {
                    int color = coloredBe.getColor();
                    double x = (double) pos.getX() + 0.125D + random.nextDouble() * 0.75D;
                    double y = (double) pos.getY() + 0.35D + (state.getValue(LEVEL) * 0.2D);
                    double z = (double) pos.getZ() + 0.125D + random.nextDouble() * 0.75D;
                    level.addParticle(new SparkleParticleOptions(color), x, y, z, 0.01, 0.01, 0.01);
                }
            }
        }
    }
}
