package com.teamacronymcoders.base.recipesystem;

import com.google.common.collect.Lists;
import com.teamacronymcoders.base.recipesystem.handler.DefaultRecipeHandler;
import com.teamacronymcoders.base.recipesystem.handler.IRecipeHandler;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class RecipeType {
    public final String name;
    public final List<IRecipeHandler> recipeHandlers;

    public RecipeType(ResourceLocation name) {
        this.name = name.toString();
        recipeHandlers = Lists.newArrayList();
    }

    public IRecipeHandler createHandler() {
        return new DefaultRecipeHandler(RecipeSystem.getRecipesFor(this));
    }

    public List<IRecipeHandler> getRecipeHandlers() {
        return this.recipeHandlers;
    }
}
