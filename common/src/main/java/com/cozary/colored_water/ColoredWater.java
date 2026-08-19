package com.cozary.colored_water;

import com.cozary.colored_water.block.ColoredWaterBlock;
import com.cozary.colored_water.command.ColoredWaterCommand;
import com.cozary.colored_water.init.*;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.client.particle.WakeParticle;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ColoredWater {

    public static final String MOD_ID = "colored_water";
    public static final String MOD_NAME = "Colored Water";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);
    public static boolean isInitialized = false;

    public static void init() {
        ModBlocks.loadClass();
        ModCauldrons.loadClass();
        ModFluids.loadClass();
        ModItems.loadClass();
        ModRecipe.loadClass();
        ModParticles.loadClass();
        ModBlockEntities.loadClass();
        isInitialized = true;
    }

    public static void postInit() {
        ModCauldrons.registerCauldronInteractions();
        registerDispenserBehaviors();
    }

    private static void registerDispenserBehaviors() {
        DispenserBlock.registerBehavior(ModItems.COLORED_WATER_BUCKET.get(), new DefaultDispenseItemBehavior() {
            private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

            @Override
            public ItemStack execute(BlockSource source, ItemStack stack) {
                if (stack.getItem() instanceof BucketItem bucketItem) {
                    BlockPos blockpos = source.pos().relative(source.state().getValue(DispenserBlock.FACING));
                    Level level = source.level();
                    if (bucketItem.emptyContents(null, level, blockpos, null)) {
                        bucketItem.checkExtraContent(null, level, stack, blockpos);
                        return this.consumeWithRemainder(source, stack, new ItemStack(Items.BUCKET));
                    }
                }
                return this.defaultDispenseItemBehavior.dispense(source, stack);
            }
        });

        DispenseItemBehavior vanillaBucketBehavior = DispenserBlock.DISPENSER_REGISTRY.get(Items.BUCKET);

        DispenserBlock.registerBehavior(Items.BUCKET, new DefaultDispenseItemBehavior() {
            @Override
            public ItemStack execute(BlockSource source, ItemStack stack) {
                BlockPos blockpos = source.pos().relative(source.state().getValue(DispenserBlock.FACING));
                Level level = source.level();
                BlockState blockstate = level.getBlockState(blockpos);
                if (blockstate.getBlock() instanceof ColoredWaterBlock) {
                    BucketPickup bucketPickup = (BucketPickup) blockstate.getBlock();
                    ItemStack filledStack = bucketPickup.pickupBlock(null, level, blockpos, blockstate);
                    if (!filledStack.isEmpty()) {
                        level.gameEvent(null, GameEvent.FLUID_PICKUP, blockpos);
                        return this.consumeWithRemainder(source, stack, filledStack);
                    }
                }
                return vanillaBucketBehavior != null ? vanillaBucketBehavior.dispense(source, stack)
                        : super.execute(source, stack);
            }
        });
    }

    public static void registerCommands(CommandDispatcher<CommandSourceStack> dispatcher) {
        ColoredWaterCommand.register(dispatcher);
    }
}