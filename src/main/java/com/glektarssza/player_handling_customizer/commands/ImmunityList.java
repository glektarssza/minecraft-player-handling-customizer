package com.glektarssza.player_handling_customizer.commands;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import com.glektarssza.player_handling_customizer.api.IImmunity;
import com.glektarssza.player_handling_customizer.api.IPhysicalImmunity;
import com.glektarssza.player_handling_customizer.api.ImmunityType;
import com.glektarssza.player_handling_customizer.utils.PlayerUtils;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.command.SyntaxErrorException;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

/**
 * A command for listing the immunities attached to a player.
 */
public class ImmunityList extends CommandBase {
    /**
     * Get the name of the command.
     *
     * @return The name of the command.
     */
    @Override
    public String getName() {
        return new TextComponentTranslation(
            "player_handling_customizer.commands.immunityList")
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
            "player_handling_customizer.commands.immunityList.usage",
            new TextComponentTranslation(
                "player_handling_customizer.commands.immunityList"),
            new TextComponentTranslation(
                "player_handling_customizer.words.player.lower"),
            new TextComponentTranslation(
                "player_handling_customizer.immunityTypes.damage.lower"),
            new TextComponentTranslation(
                "player_handling_customizer.immunityTypes.hurt.lower"),
            new TextComponentTranslation(
                "player_handling_customizer.immunityTypes.knockback.lower"),
            new TextComponentTranslation(
                "player_handling_customizer.immunityTypes.targeting.lower"))
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
            default:
                // No further completions available
                return Collections.emptyList();
        }
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender,
        String[] args) throws CommandException {
        if (args.length < 1) {
            throw new SyntaxErrorException(
                "player_handling_customizer.commands.errors.syntax.missing_player");
        }
        String playerName = args[0];
        EntityPlayerMP player = server.getPlayerList()
            .getPlayerByUsername(playerName);
        if (player == null) {
            throw new PlayerNotFoundException(
                "player_handling_customizer.commands.errors.player_not_found");
        }
        List<IImmunity> playerImmunities = PlayerUtils
            .getPlayerImmunities(player);
        if (args.length > 1) {
            ImmunityType filter = ImmunityType.fromString(args[1]);
            playerImmunities = playerImmunities.stream()
                .filter((item) -> item.getImmunityType() == filter)
                .collect(Collectors.toList());
        }
        sender.sendMessage(new TextComponentTranslation(
            "player_handling_customizer.commands.immunityList.response.header",
            playerName));
        if (playerImmunities.isEmpty()) {
            sender.sendMessage(new TextComponentTranslation(
                "player_handling_customizer.commands.immunityList.response.no_items"));
            return;
        }
        playerImmunities.stream().limit(100).forEach((item) -> {
            ITextComponent response = null;
            ITextComponent immunityTypeResponse = null;
            switch (item.getImmunityType()) {
                case Damage:
                    immunityTypeResponse = new TextComponentTranslation(
                        "player_handling_customizer.immunityTypes.damage.lower");
                    break;
                case Hurt:
                    immunityTypeResponse = new TextComponentTranslation(
                        "player_handling_customizer.immunityTypes.hurt.lower");
                    break;
                case Knockback:
                    immunityTypeResponse = new TextComponentTranslation(
                        "player_handling_customizer.immunityTypes.knockback.lower");
                    break;
                case Targeting:
                    immunityTypeResponse = new TextComponentTranslation(
                        "player_handling_customizer.immunityTypes.targeting.lower");
                    break;
            }
            boolean isDirectional = false;
            if (item instanceof IPhysicalImmunity) {
                isDirectional = ((IPhysicalImmunity) item)
                    .getAppliesToDirectDamage()
                    ^ ((IPhysicalImmunity) item).getAppliesToIndirectDamage();
            }
            if (isDirectional) {
                ITextComponent directionality = ((IPhysicalImmunity) item)
                    .getAppliesToDirectDamage()
                        ? new TextComponentTranslation(
                            "player_handling_customizer.immunityTypes.direct.upper")
                        : new TextComponentTranslation(
                            "player_handling_customizer.immunityTypes.indirect.upper");
                ;
                response = new TextComponentTranslation(
                    "player_handling_customizer.commands.immunityList.response.item.directional",
                    directionality, ((IPhysicalImmunity) item).getDamageType(),
                    immunityTypeResponse, EntityList.getTranslationName(
                        new ResourceLocation(item.getEntityType())));
            } else {
                response = new TextComponentTranslation(
                    "player_handling_customizer.commands.immunityList.response.item.non_directional",
                    ((IPhysicalImmunity) item).getDamageType(),
                    immunityTypeResponse, EntityList.getTranslationName(
                        new ResourceLocation(item.getEntityType())));
            }
            sender.sendMessage(response);
        });
        if (playerImmunities.size() > 100) {
            sender.sendMessage(new TextComponentTranslation(
                "player_handling_customizer.commands.immunityList.response.limited"));
        }
    }
}
