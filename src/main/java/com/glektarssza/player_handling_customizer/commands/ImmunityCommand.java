package com.glektarssza.player_handling_customizer.commands;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.glektarssza.player_handling_customizer.utils.PlayerUtils;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

/**
 * A command handler to add an immunity to a player.
 */
public class ImmunityCommand extends CommandBase {
    @Override
    public String getName() {
        return "immunity";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return String.format(
            "/%s <player> <add|remove|list> [*|damage|hurt|knockback|targeting] [*|entityType]",
            this.getName());
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return index == 0;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server,
        ICommandSender sender, String[] args, BlockPos targetPos) {
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
        }
        return super.getTabCompletions(server, sender, args, targetPos);
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender,
        String[] args) throws CommandException {
        Entity entity = sender.getCommandSenderEntity();
        if (!(entity instanceof EntityPlayer)) {
            throw new CommandException("Command sender is not a player!");
        }
        EntityPlayer player = (EntityPlayer) entity;
        NBTTagCompound playerModData = PlayerUtils.getPlayerModData(player);
        // TODO: Update player NBT data
        throw new CommandException("Work in progress!");
    }
}
