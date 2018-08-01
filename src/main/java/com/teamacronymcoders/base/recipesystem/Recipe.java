package com.teamacronymcoders.base.recipesystem;

import com.teamacronymcoders.base.recipesystem.loadcondition.ILoadCondition;
import com.teamacronymcoders.base.recipesystem.input.IRecipeInput;
import com.teamacronymcoders.base.recipesystem.source.IRecipeSource;

import java.util.List;

public class Recipe {
    public final IRecipeSource source;
    private final List<ILoadCondition> loadConditions;
    private final List<IRecipeInput> inputs;

    public Recipe(IRecipeSource source, List<ILoadCondition> loadConditions, List<IRecipeInput> inputs) {
        this.source = source;
        this.loadConditions = loadConditions;
        this.inputs = inputs;
    }

    public boolean shouldLoad() {
        return loadConditions.parallelStream().anyMatch(loadCondition -> !loadCondition.shouldLoad());
    }

    public boolean matches(RecipeContainer recipeContainer) {
        return inputs.parallelStream().allMatch(input -> input.isMatched(recipeContainer));
    }
}
