package com.teamacronymcoders.base.recipesystem.command;

import com.teamacronymcoders.base.recipesystem.RecipeContainer;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RecipeSystemCommandSender implements ICommandSender {
    private final RecipeContainer recipeContainer;

    public RecipeSystemCommandSender(RecipeContainer recipeContainer) {
        this.recipeContainer = recipeContainer;
    }

    @Override
    @Nonnull
    public String getName() {
        return "RecipeSystem";
    }

    @Override
    public boolean canUseCommand(int permLevel, @Nonnull String commandName) {
        return true;
    }

    @Override
    @Nonnull
    public World getEntityWorld() {
        return recipeContainer.getWorld();
    }

    @Nullable
    @Override
    public MinecraftServer getServer() {
        return recipeContainer.getWorld().getMinecraftServer();
    }
}
