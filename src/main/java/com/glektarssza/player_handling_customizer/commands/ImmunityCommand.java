package com.glektarssza.player_handling_customizer.commands;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

/**
 * A command handler to add an immunity to a player.
 */
public class ImmunityCommand extends CommandBase {
    @Override
    public String getName() {
        return "immunity";
    }

    @Override
    public String getUsage(@Nonnull ICommandSender sender) {
        return String.format(
                "/%s <player> <add|remove|list> [*|damage|hurt|knockback|targeting] [*|entityType] [*|direct|indirect]",
                this.getName());
    }

    @Override
    public boolean isUsernameIndex(@Nonnull String[] args, int index) {
        return index == 1;
    }

    @Override
    public List<String> getTabCompletions(@Nonnull MinecraftServer server,
            @Nonnull ICommandSender sender, @Nonnull String[] args, @Nullable BlockPos targetPos) {
        if (args.length == 1) {
            return Arrays.asList(server.getOnlinePlayerNames());
        } else if (args.length == 2) {
            return Arrays.asList("add", "remove", "list");
        } else if (args.length == 3) {
            return Arrays.asList("*", "damage", "hurt", "knockback",
                    "targeting");
        } else if (args.length == 4) {
            List<String> allOptions = EntityList.getEntityNameList().stream()
                    .map((rl) -> rl.toString()).collect(Collectors.toList());
            allOptions.add(0, "*");
            return allOptions;
        } else if (args.length == 5 && (args[3].equalsIgnoreCase("damage")
                || args[3].equalsIgnoreCase("hurt"))) {
            return Arrays.asList("*", "direct", "indirect");
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public void execute(@Nonnull MinecraftServer server,
            @Nonnull ICommandSender sender,
            @Nonnull String[] args) throws CommandException {
        if (args.length < 2) {
            throw new SyntaxErrorException(
                    "player_handling_customizer.commands.errors.syntax.missing_player_name");
        }
        if (args.length < 3) {
            throw new SyntaxErrorException(
                    "player_handling_customizer.commands.errors.syntax.missing_operation");
        }
        String playerName = args[1];
        EntityPlayerMP player = server.getPlayerList()
                .getPlayerByUsername(playerName);
        if (player == null) {
            throw new PlayerNotFoundException(
                    "player_handling_customizer.commands.errors.player_not_found",
                    playerName);
        }
        String operation = args[2];
        String immunityType = "*";
        String entityType = "*";
        String direction = "*";
        if (args.length > 3) {
            immunityType = args[3];
        }
        if (args.length > 4) {
            entityType = args[4];
        }
        if (args.length > 5) {
            direction = args[5];
        }
        if (operation.equalsIgnoreCase("add")) {
            throw new CommandException(
                    "player_handling_customizer.commands.errors.work_in_progress");
        } else if (operation.equalsIgnoreCase("remove")) {
            throw new CommandException(
                    "player_handling_customizer.commands.errors.work_in_progress");
        } else if (operation.equalsIgnoreCase("list")) {
            List<? extends IImmunity> immunities = null;
            if (immunityType.equalsIgnoreCase("*")) {
                immunities = PlayerUtils.getPlayerImmunities(player);
            } else if (immunityType.equalsIgnoreCase("damage")) {
                immunities = PlayerUtils.getPlayerDamageImmunities(player);
            } else if (immunityType.equalsIgnoreCase("hurt")) {
                immunities = PlayerUtils.getPlayerHurtImmunities(player);
            } else if (immunityType.equalsIgnoreCase("knockback")) {
                immunities = PlayerUtils.getPlayerKnockbackImmunities(player);
            } else if (immunityType.equalsIgnoreCase("targeting")) {
                immunities = PlayerUtils.getPlayerTargetingImmunities(player);
            } else {
                throw new SyntaxErrorException(
                        "player_handling_customizer.commands.errors.syntax.invalid_immunity_type",
                        immunityType);
            }
            sender.sendMessage(new TextComponentTranslation(
                    "player_handling_customizer.commands.response.list.header",
                    playerName));
            immunities.forEach((immunity) -> {
                ImmunityType type = immunity.getImmunityType();
                ITextComponent immunityTypeTranslation = null;
                switch (type) {
                    case Damage:
                        if (((IPhysicalImmunity) immunity)
                                .getAppliesToDirectDamage()
                                && ((IPhysicalImmunity) immunity)
                                        .getAppliesToIndirectDamage()) {
                            immunityTypeTranslation = new TextComponentTranslation(
                                    "player_handling_customizer.immunity.damage.upper");
                        } else if (((IPhysicalImmunity) immunity)
                                .getAppliesToDirectDamage()) {
                            immunityTypeTranslation = new TextComponentTranslation(
                                    "player_handling_customizer.immunity.damage.lower");
                        } else if (((IPhysicalImmunity) immunity)
                                .getAppliesToIndirectDamage()) {
                            immunityTypeTranslation = new TextComponentTranslation(
                                    "player_handling_customizer.immunity.damage.lower");
                        } else {
                            // How did we get here?????
                            return;
                        }
                        break;
                    case Hurt:
                        if (((IPhysicalImmunity) immunity)
                                .getAppliesToDirectDamage()
                                && ((IPhysicalImmunity) immunity)
                                        .getAppliesToIndirectDamage()) {
                            immunityTypeTranslation = new TextComponentTranslation(
                                    "player_handling_customizer.immunity.hurt.upper");
                        } else if (((IPhysicalImmunity) immunity)
                                .getAppliesToDirectDamage()) {
                            immunityTypeTranslation = new TextComponentTranslation(
                                    "player_handling_customizer.immunity.hurt.lower");
                        } else if (((IPhysicalImmunity) immunity)
                                .getAppliesToIndirectDamage()) {
                            immunityTypeTranslation = new TextComponentTranslation(
                                    "player_handling_customizer.immunity.hurt.lower");
                        } else {
                            // How did we get here?????
                            return;
                        }
                        break;
                    case Knockback:
                        immunityTypeTranslation = new TextComponentTranslation(
                                "player_handling_customizer.immunity.knockback.upper");
                        break;
                    case Targeting:
                        immunityTypeTranslation = new TextComponentTranslation(
                                "player_handling_customizer.immunity.targeting.upper");
                        break;
                }
            });
        } else

        {
            throw new SyntaxErrorException(
                    "player_handling_customizer.commands.errors.syntax.invalid_operation",
                    operation);
        }
    }
}
