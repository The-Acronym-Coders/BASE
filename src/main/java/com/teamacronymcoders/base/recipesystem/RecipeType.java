package com.teamacronymcoders.base.recipesystem;

import com.google.common.collect.Lists;
import com.teamacronymcoders.base.recipesystem.handler.DefaultRecipeHandler;
import com.teamacronymcoders.base.recipesystem.handler.IRecipeHandler;

import java.util.List;

public class RecipeType {
    public final String name;
    public final List<IRecipeHandler> recipes;

    public RecipeType(String name) {
        this.name = name;
        recipes = Lists.newArrayList();
    }

    public IRecipeHandler createHandler() {
        return new DefaultRecipeHandler(RecipeSystem.getRecipesFor(this));
    }

    public List<IRecipeHandler> getHandlers() {
        return this.getHandlers();
    }
}
