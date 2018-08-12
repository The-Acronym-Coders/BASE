package com.teamacronymcoders.base.recipesystem;

import com.teamacronymcoders.base.recipesystem.input.IRecipeInput;
import com.teamacronymcoders.base.recipesystem.source.IRecipeSource;

import java.util.List;

public class Recipe {
    public final IRecipeSource source;
    private final List<IRecipeInput> inputs;

    public Recipe(IRecipeSource source, List<IRecipeInput> inputs) {
        this.source = source;
        this.inputs = inputs;
    }

    public boolean matches(RecipeContainer recipeContainer) {
        return inputs.parallelStream().allMatch(input -> input.isMatched(recipeContainer));
    }
}
