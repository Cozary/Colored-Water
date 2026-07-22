package com.cozary.colored_water.command;

import com.cozary.colored_water.block.entity.ColoredWaterBlockEntity;
import com.cozary.colored_water.init.ModItems;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.permissions.Permissions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.List;
import java.util.Locale;

public class ColoredWaterCommand {

    private static final SimpleCommandExceptionType ERROR_INVALID_COLOR =
            new SimpleCommandExceptionType(Component.literal("Invalid Hex Color. Format: #RRGGBB or #AARRGGBB"));

    private static final SimpleCommandExceptionType ERROR_INVALID_TYPE =
            new SimpleCommandExceptionType(Component.literal("Invalid fluid type. Use: normal, condense, luminous, luminous_condense"));

    private static final SuggestionProvider<CommandSourceStack> TYPE_SUGGESTIONS = (context, builder) ->
            SharedSuggestionProvider.suggest(List.of("normal", "condense", "luminous", "luminous_condense"), builder);

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("coloredwater")
                .requires(source -> source.permissions().hasPermission(Permissions.COMMANDS_GAMEMASTER))
                .then(Commands.argument("type", StringArgumentType.word())
                        .suggests(TYPE_SUGGESTIONS)
                        .then(Commands.argument("hexcolor", StringArgumentType.string())
                                .executes(ctx -> executeCommand(ctx, false, false))
                                .then(Commands.argument("luminosity", IntegerArgumentType.integer(0, 15))
                                        .executes(ctx -> executeCommand(ctx, true, false))
                                        .then(Commands.argument("translucency", IntegerArgumentType.integer(0, 255))
                                                .executes(ctx -> executeCommand(ctx, true, true))
                                        )
                                )
                        )
                )
        );
    }

    private static int executeCommand(CommandContext<CommandSourceStack> context, boolean hasLuminosityArg, boolean hasTranslucencyArg) throws CommandSyntaxException {
        String typeStr = StringArgumentType.getString(context, "type").toLowerCase(Locale.ROOT);
        String hexString = StringArgumentType.getString(context, "hexcolor");

        if (!isValidType(typeStr)) {
            throw ERROR_INVALID_TYPE.create();
        }

        if (hexString.startsWith("#")) {
            hexString = hexString.substring(1);
        }

        long parsedColor;
        try {
            parsedColor = Long.parseLong(hexString, 16);
        } catch (NumberFormatException e) {
            throw ERROR_INVALID_COLOR.create();
        }

        int alpha = 255;
        int rgb;

        if (hexString.length() <= 6) {
            rgb = (int) (parsedColor & 0xFFFFFF);
        } else if (hexString.length() <= 8) {
            alpha = (int) ((parsedColor >> 24) & 0xFF);
            rgb = (int) (parsedColor & 0xFFFFFF);
        } else {
            throw ERROR_INVALID_COLOR.create();
        }

        if (hasTranslucencyArg) {
            alpha = IntegerArgumentType.getInteger(context, "translucency");
        }

        int argbColor = ((alpha & 0xFF) << 24) | (rgb & 0xFFFFFF);

        boolean isCondensed = typeStr.contains("condense");
        int defaultLuminosity = typeStr.contains("luminous") ? 15 : 0;
        int luminosity = hasLuminosityArg ? IntegerArgumentType.getInteger(context, "luminosity") : defaultLuminosity;

        CommandSourceStack source = context.getSource();
        Player player = source.getPlayerOrException();

        Item bucketItem = ModItems.COLORED_WATER_BUCKET.get();
        ItemStack bucket = new ItemStack(bucketItem);
        bucket.set(DataComponents.DYED_COLOR, new DyedItemColor(argbColor));


        CompoundTag tag = new CompoundTag();
        tag.putBoolean("Condensed", isCondensed);
        tag.putInt("Luminosity", luminosity);
        CustomData.update(DataComponents.CUSTOM_DATA, bucket, t -> t.merge(tag));

        if (!player.getInventory().add(bucket)) {
            player.drop(bucket, false);
        }

        if (player instanceof ServerPlayer serverPlayer) {
            BlockHitResult hitResult = serverPlayer.level().clip(new ClipContext(
                    serverPlayer.getEyePosition(1.0F),
                    serverPlayer.getEyePosition(1.0F).add(serverPlayer.getLookAngle().scale(5.0)),
                    ClipContext.Block.OUTLINE,
                    ClipContext.Fluid.ANY,
                    serverPlayer
            ));

            if (hitResult.getType() == HitResult.Type.BLOCK) {
                BlockPos targetPos = hitResult.getBlockPos();
                BlockEntity be = serverPlayer.level().getBlockEntity(targetPos);
                if (be instanceof ColoredWaterBlockEntity coloredBe) {
                    coloredBe.setColor(argbColor, null, true);
                    coloredBe.setCondensed(isCondensed);
                    coloredBe.setLuminosity(luminosity);
                }
            }
        }

        String hexDisplay = String.format("#%08X", argbColor);
        final int finalAlpha = alpha;
        final int finalLuminosity = luminosity;
        source.sendSuccess(() -> Component.literal(
                String.format("Gave %s Colored Water Bucket [Color: %s, Condensed: %b, Luminosity: %d, Opacity: %d/255]",
                        typeStr.toUpperCase(Locale.ROOT), hexDisplay, isCondensed, finalLuminosity, finalAlpha)
        ), true);

        return 1;
    }

    private static boolean isValidType(String type) {
        return switch (type) {
            case "normal", "colored_water", "water", "condense", "condense_colored_water", "luminous",
                 "luminous_colored_water", "luminous_condense", "luminous_condense_colored_water" -> true;
            default -> false;
        };
    }
}