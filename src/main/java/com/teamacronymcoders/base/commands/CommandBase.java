package com.teamacronymcoders.base.commands;

import com.google.common.collect.Lists;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandBase implements ICommand {
    private String name;
    private String usage;
    private List<String> aliases;
    private int permissionLevel;
    private Map<String, ICommand> subCommands;

    public CommandBase(String name) {
        this(name, "", Lists.newArrayList(), 0, Lists.newArrayList());
    }

    public CommandBase(String name, String usage, List<String> aliases, int permissionLevel, List<ICommand> commands) {
        this.name = name;
        this.usage = usage;
        this.aliases = aliases;
        this.permissionLevel = permissionLevel;
        this.subCommands = new HashMap<>();
        commands.forEach(command -> subCommands.put(command.getName(), command));
    }

    @Nonnull
    @Override
    public String getName() {
        return name;
    }

    @Nonnull
    @Override
    public String getUsage(@Nonnull ICommandSender sender) {
        return usage;
    }

    @Nonnull
    @Override
    public List<String> getAliases() {
        return aliases;
    }

    @Override
    public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] args) throws CommandException {

    }

    @Override
    public boolean checkPermission(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender) {
        final boolean[] goodToGood = {true};
        subCommands.forEach((name, subCommand) -> goodToGood[0] &= subCommand.checkPermission(server, sender) );
        return goodToGood[0] && sender.canUseCommand(this.permissionLevel, this.getName());
    }

    @Nonnull
    @Override
    public List<String> getTabCompletions(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] args, @Nullable BlockPos pos) {
        if (subCommands.get(args[0]) != null) {
            return subCommands.get(args[0]).getTabCompletions(server, sender, args, pos);
        }
        return Lists.newArrayList();
    }

    @Override
    public boolean isUsernameIndex(@Nonnull String[] args, int index) {
        return false;
    }

    @Override
    public int compareTo(@Nonnull ICommand otherCommand) {
        return this.getName().compareTo(otherCommand.getName());
    }
}
