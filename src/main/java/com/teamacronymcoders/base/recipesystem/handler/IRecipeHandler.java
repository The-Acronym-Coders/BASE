package com.teamacronymcoders.base.recipesystem.handler;

import com.teamacronymcoders.base.recipesystem.Recipe;
import com.teamacronymcoders.base.recipesystem.RecipeContainer;

import java.util.List;

public interface IRecipeHandler {
    void tickRecipe(RecipeContainer recipeContainer);

    void reloadRecipes(List<Recipe> recipeList);
}
