package com.glektarssza.player_handling_customizer.commands;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.EntityList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;

/**
 * A command for listing the immunities attached to a player.
 */
public class ImmunityAdd extends CommandBase {
    /**
     * Get the name of the command.
     *
     * @return The name of the command.
     */
    @Override
    public String getName() {
        return new TextComponentTranslation(
            "player_handling_customizer.commands.immunityAdd")
            .getUnformattedText();
    }

    /**
     * Get the usage of the command.
     *
     * @param sender The thing that sent the command.
     *
     * @return The usage of the command.
     */
    @Override
    public String getUsage(ICommandSender sender) {
        return new TextComponentTranslation(
            "player_handling_customizer.commands.immunityAdd.usage",
            new TextComponentTranslation(
                "player_handling_customizer.commands.immunityAdd"),
            new TextComponentTranslation(
                "player_handling_customizer.words.player.lower"),
            new TextComponentTranslation(
                "player_handling_customizer.immunityTypes.damage.lower"),
            new TextComponentTranslation(
                "player_handling_customizer.immunityTypes.hurt.lower"),
            new TextComponentTranslation(
                "player_handling_customizer.immunityTypes.knockback.lower"),
            new TextComponentTranslation(
                "player_handling_customizer.immunityTypes.targeting.lower"),
            new TextComponentTranslation(
                "player_handling_customizer.words.entityType.lower"),
            new TextComponentTranslation(
                "player_handling_customizer.immunityTypes.direct.lower"),
            new TextComponentTranslation(
                "player_handling_customizer.immunityTypes.indirect.lower"))
            .getUnformattedText();
    }

    /**
     * Get whether the given index in the given arguments is a username.
     *
     * @param args The arguments to check.
     * @param index The index to check.
     *
     * @return {@code true} if the index in the arguments is a username;
     *         {@code false} otherwise.
     */
    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return index == 1;
    }

    /**
     * Get the tabbing completions for the current command arguments.
     *
     * @param server The Minecraft server instance.
     * @param sender The thing that sent the command.
     * @param args The current arguments to the command.
     * @param targetPos The block position that the thing sending the command is
     *        looking at, if it is capable of doing so.
     */
    @Override
    public List<String> getTabCompletions(MinecraftServer server,
        ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        switch (args.length) {
            case 0:
                // How did we get here????
                return super.getTabCompletions(server, sender, args, targetPos);
            case 1:
                // Only the command is given so far, return player names
                return Arrays.asList(server.getOnlinePlayerNames());
            case 2:
                // Player name given, return immunity types to filter on
                return Arrays.asList(
                    new TextComponentTranslation(
                        "player_handling_customizer.immunityTypes.damage.lower")
                        .getUnformattedText(),
                    new TextComponentTranslation(
                        "player_handling_customizer.immunityTypes.hurt.lower")
                        .getUnformattedText(),
                    new TextComponentTranslation(
                        "player_handling_customizer.immunityTypes.knockback.lower")
                        .getUnformattedText(),
                    new TextComponentTranslation(
                        "player_handling_customizer.immunityTypes.targeting.lower")
                        .getUnformattedText());
            case 3:
                // Immunity type given, return entity types to filter on
                List<String> entityNames = EntityList.getEntityNameList()
                    .stream().limit(99).map((item) -> item.toString())
                    .collect(Collectors.toList());
                entityNames.add(0, "*");
                return entityNames;
            case 4:
                // Entity type given, return directionality to filter on
                return Arrays.asList("*",
                    new TextComponentTranslation(
                        "player_handling_customizer.immunityTypes.direct.lower")
                        .getUnformattedText(),
                    new TextComponentTranslation(
                        "player_handling_customizer.immunityTypes.indirect.lower")
                        .getUnformattedText());
            default:
                // No further completions available
                return Collections.emptyList();
        }
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender,
        String[] args) throws CommandException {
        throw new CommandException(
            "player_handling_customizer.commands.errors.work_in_progress");
    }
}
