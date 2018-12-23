package com.teamacronymcoders.base.recipesystem.output;

import com.teamacronymcoders.base.recipesystem.RecipeContainer;
import com.teamacronymcoders.base.recipesystem.command.RecipeSystemCommandSender;
import net.minecraft.server.MinecraftServer;

import java.util.Optional;

public class CommandOutput implements IOutput {
    private final String command;

    public CommandOutput(String command) {
        this.command = command;
    }

    @Override
    public boolean canOutput(RecipeContainer recipeContainer) {
        return true;
    }

    @Override
    public void output(RecipeContainer recipeContainer) {
        Optional.ofNullable(recipeContainer.getWorld().getMinecraftServer())
                .map(MinecraftServer::getCommandManager)
                .ifPresent(iCommandManager -> iCommandManager.executeCommand(new RecipeSystemCommandSender(recipeContainer), command));
    }
}
