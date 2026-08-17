package com.cozary.colored_water.mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

//Idk lets try this, at this point im pretty lost tbh
@Mixin(Item.class)
public interface ItemInvoker {
    @Invoker("getPlayerPOVHitResult")
    static BlockHitResult invokeGetPlayerPOVHitResult(Level level, Player player, ClipContext.Fluid fluid) {
        throw new AssertionError();
    }
}
