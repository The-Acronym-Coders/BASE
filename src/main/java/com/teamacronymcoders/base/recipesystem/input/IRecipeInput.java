package com.teamacronymcoders.base.recipesystem.input;

import com.teamacronymcoders.base.recipesystem.Recipe;
import com.teamacronymcoders.base.recipesystem.RecipeContainer;

public interface IRecipeInput {
    boolean isMatched(RecipeContainer recipeContainer);

    void consume(RecipeContainer recipeContainer);
}
