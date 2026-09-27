package com.cozary.colored_water.command;

import com.cozary.colored_water.block.ColoredWaterCauldronBlock;
import com.cozary.colored_water.block.entity.ColoredWaterBlockEntity;
import com.cozary.colored_water.block.entity.ColoredWaterCauldronBlockEntity;
import com.cozary.colored_water.util.ColoredWaterUtil;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.permissions.Permissions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

/**
 * Command handling for /coloredwater.
 * <p>
 * - Manages and obtains custom colored water buckets via command arguments.
 * - Supports syntax: /coloredwater <hexcolor> [luminosity] [opacity].
 * - Updates target colored water block or cauldron in front of player if looking at one.
 */
public class ColoredWaterCommand {

    private static final SimpleCommandExceptionType ERROR_INVALID_COLOR = new SimpleCommandExceptionType(
            Component.literal("Invalid Hex Color. Format: #RRGGBB or #AARRGGBB"));

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("coloredwater")
                .requires(source -> source.permissions().hasPermission(Permissions.COMMANDS_GAMEMASTER))
                .then(Commands.argument("hexcolor", StringArgumentType.string())
                        .executes(ctx -> executeCommand(ctx, false, false))
                        .then(Commands.argument("luminosity", IntegerArgumentType.integer(0, 15))
                                .executes(ctx -> executeCommand(ctx, true, false))
                                .then(Commands.argument("opacity", IntegerArgumentType.integer(0, 255))
                                        .executes(ctx -> executeCommand(ctx, true, true))))));
    }

    private static int executeCommand(CommandContext<CommandSourceStack> context, boolean hasLuminosityArg, boolean hasOpacityArg) throws CommandSyntaxException {
        String hexString = StringArgumentType.getString(context, "hexcolor");

        if (hexString.startsWith("#")) {
            hexString = hexString.substring(1);
        }

        long parsedColor;
        try {
            parsedColor = Long.parseLong(hexString, 16);
        } catch (NumberFormatException e) {
            throw ERROR_INVALID_COLOR.create();
        }

        int alpha = ColoredWaterUtil.DEFAULT_ALPHA;
        int rgb;

        if (hexString.length() <= 6) {
            rgb = (int) (parsedColor & 0xFFFFFF);
        } else if (hexString.length() <= 8) {
            alpha = (int) ((parsedColor >> 24) & 0xFF);
            rgb = (int) (parsedColor & 0xFFFFFF);
        } else {
            throw ERROR_INVALID_COLOR.create();
        }

        if (hasOpacityArg) {
            alpha = IntegerArgumentType.getInteger(context, "opacity");
        }

        int argbColor = ColoredWaterUtil.packArgb(alpha, (rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF);

        int luminosity = hasLuminosityArg ? IntegerArgumentType.getInteger(context, "luminosity") : ColoredWaterUtil.MIN_LUMINOSITY;
        boolean isCondensed = ColoredWaterUtil.isCondensedAlpha(alpha);

        CommandSourceStack source = context.getSource();
        Player player = source.getPlayerOrException();

        ItemStack bucket = ColoredWaterUtil.createBucketStack(argbColor, isCondensed, luminosity);

        if (!player.getInventory().add(bucket)) {
            player.drop(bucket, false);
        }

        if (player instanceof ServerPlayer serverPlayer) {
            BlockHitResult hitResult = serverPlayer.level().clip(new ClipContext(
                    serverPlayer.getEyePosition(1.0F),
                    serverPlayer.getEyePosition(1.0F).add(serverPlayer.getLookAngle().scale(5.0)),
                    ClipContext.Block.OUTLINE,
                    ClipContext.Fluid.ANY,
                    serverPlayer));

            if (hitResult.getType() == HitResult.Type.BLOCK) {
                BlockPos targetPos = hitResult.getBlockPos();
                BlockEntity blockEntity = serverPlayer.level().getBlockEntity(targetPos);
                if (blockEntity instanceof ColoredWaterBlockEntity coloredWaterBlockEntity) {
                    coloredWaterBlockEntity.setColor(argbColor, null, true);
                    coloredWaterBlockEntity.setCondensed(isCondensed);
                    coloredWaterBlockEntity.setLuminosity(luminosity);
                } else if (blockEntity instanceof ColoredWaterCauldronBlockEntity cauldronBlockEntity) {
                    cauldronBlockEntity.setProperties(argbColor, isCondensed, luminosity);
                    serverPlayer.level().setBlock(targetPos, serverPlayer.level().getBlockState(targetPos)
                            .setValue(ColoredWaterCauldronBlock.CONDENSED, isCondensed)
                            .setValue(ColoredWaterCauldronBlock.LIGHT_LEVEL, luminosity), 3);
                }
            }
        }

        String hexDisplay = String.format("#%08X", argbColor);
        int opacityPct = Math.round((alpha * 100.0f) / 255.0f);
        String variantName = (isCondensed && luminosity > 0) ? "Luminous Condensed" :
                isCondensed ? "Condensed" :
                        (luminosity > 0) ? "Luminous" : "Normal";

        MutableComponent message = Component.literal("Gave " + variantName + " Colored Water Bucket [Color: ")
                .append(Component.literal(hexDisplay).withStyle(style -> style.withColor(TextColor.fromRgb(rgb))))
                .append(String.format(", Luminosity: %d, Opacity: %d%%]", luminosity, opacityPct));

        source.sendSuccess(() -> message, true);

        return 1;
    }
}