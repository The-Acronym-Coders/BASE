package com.teamacronymcoders.base.recipesystem.command;

import com.teamacronymcoders.base.recipesystem.RecipeSystem;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class ReloadRecipesCommand extends CommandBase {
    @Override
    @Nonnull
    public String getName() {
        return "reload";
    }

    @Override
    @Nonnull
    @ParametersAreNonnullByDefault
    public String getUsage(ICommandSender sender) {
        return "Reloads the Recipe System";
    }

    @Override
    @ParametersAreNonnullByDefault
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        RecipeSystem.reloadRecipe();
    }
}
