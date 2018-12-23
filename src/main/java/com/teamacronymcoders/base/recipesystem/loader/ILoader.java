package com.teamacronymcoders.base.recipesystem.loader;

import com.teamacronymcoders.base.recipesystem.Recipe;
import com.teamacronymcoders.base.recipesystem.source.IRecipeSource;

import java.util.List;

public interface ILoader {
    IRecipeSource getRecipeSource();

    List<Recipe> loadRecipes();
}
