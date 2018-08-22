package com.teamacronymcoders.base.command;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraftforge.server.command.CommandTreeBase;

import javax.annotation.Nonnull;
import java.util.stream.Collectors;

public class CommandSubBase extends CommandTreeBase {
    private final String name;

    public CommandSubBase(String name) {
        this.name = name;
    }

    @Override
    @Nonnull
    public String getName() {
        return name;
    }

    @Override
    @Nonnull
    public String getUsage(@Nonnull ICommandSender sender) {
        return "/" + name + " " + this.getSubCommands()
                .stream()
                .map(ICommand::getName)
                .collect(Collectors.joining(" | "));
    }
}
