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
    public void receiveStalactiteDrip(BlockState state, Level level, BlockPos pos, net.minecraft.world.level.material.Fluid fluid) {
        if (fluid == net.minecraft.world.level.material.Fluids.WATER) {
            if (level.getBlockEntity(pos) instanceof ColoredWaterCauldronBlockEntity cauldronBe) {
                int currentLevel = state.getValue(LEVEL);
                int newLevel = Math.min(3, currentLevel + 1);

                int cColor = cauldronBe.getColor();
                int cAlpha = cauldronBe.getAlpha();
                int cLuminosity = cauldronBe.getLuminosity();

                int wColor = 0x3F76E4;
                int wAlpha = 180;
                int wLuminosity = 0;

                int cWeight = currentLevel * 3;
                int dWeight = 1;
                int totalWeight = cWeight + dWeight;

                int mixedAlpha = (cAlpha * cWeight + wAlpha * dWeight) / totalWeight;
                int mixedRed = (((cColor >> 16) & 0xFF) * cWeight + ((wColor >> 16) & 0xFF) * dWeight) / totalWeight;
                int mixedGreen = (((cColor >> 8) & 0xFF) * cWeight + ((wColor >> 8) & 0xFF) * dWeight) / totalWeight;
                int mixedBlue = ((cColor & 0xFF) * cWeight + (wColor & 0xFF) * dWeight) / totalWeight;
                int mixedLuminosity = (cLuminosity * cWeight + wLuminosity * dWeight) / totalWeight;
                boolean mixedCondensed = mixedAlpha >= 220;

                int mixedColor = (mixedAlpha << 24) | (mixedRed << 16) | (mixedGreen << 8) | mixedBlue;

                cauldronBe.setCondensed(mixedCondensed);
                cauldronBe.setLuminosity(mixedLuminosity);
                cauldronBe.setAlpha(mixedAlpha);
                cauldronBe.setColor(mixedColor);

                level.setBlock(pos, state.setValue(LEVEL, newLevel)
                        .setValue(CONDENSED, mixedCondensed)
                        .setValue(LIGHT_LEVEL, mixedLuminosity), 3);

                level.levelEvent(1047, pos, 0);
            }
        }
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
