package com.cozary.colored_water.command;

import com.cozary.colored_water.init.ModItems;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.permissions.Permissions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;

public class ColoredWaterCommand {

    private static final SimpleCommandExceptionType ERROR_INVALID_COLOR = new SimpleCommandExceptionType(Component.literal("Invalid Hex Color. Use format: #RRGGBB or RRGGBB"));

    /**
     * Registers the command structure to the dispatcher.
     * <p>
     * Syntax: {@code /coloredwater give <hex>}
     *
     * @param dispatcher The command dispatcher.
     */
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("coloredwater")
                .requires(source -> source.permissions().hasPermission(Permissions.COMMANDS_GAMEMASTER))
                .then(Commands.literal("give")
                        .then(Commands.argument("hex", StringArgumentType.string())
                                .executes(ColoredWaterCommand::giveColoredBucket)
                        )
                )
        );
    }

    private static int giveColoredBucket(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        String hexString = StringArgumentType.getString(context, "hex");

        if (hexString.startsWith("#")) {
            hexString = hexString.substring(1);
        }

        int color;
        try {
            color = Integer.parseInt(hexString, 16);
            if (color < 0 || color > 0xFFFFFF) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            throw ERROR_INVALID_COLOR.create();
        }

        ItemStack bucket = new ItemStack(ModItems.COLORED_WATER_BUCKET.get());
        bucket.set(DataComponents.DYED_COLOR, new DyedItemColor(color));

        CommandSourceStack source = context.getSource();
        Player player = source.getPlayerOrException();

        if (!player.getInventory().add(bucket)) {
            player.drop(bucket, false);
        }

        //TODO add lang string
        source.sendSuccess(() -> Component.literal("Gave Colored Water Bucket with color: #" + Integer.toHexString(color).toUpperCase()), true);
        return 1;
    }
}