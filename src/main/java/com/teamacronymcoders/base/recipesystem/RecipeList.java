package com.teamacronymcoders.base.recipesystem;

import com.google.common.collect.ImmutableList;
import com.teamacronymcoders.base.recipesystem.type.RecipeType;

import java.util.List;

public class RecipeList {
    public final RecipeType type;
    public final ImmutableList<Recipe> recipes;

    public RecipeList(RecipeType type, List<Recipe> recipes) {
        this(type, ImmutableList.copyOf(recipes));
    }

    public RecipeList(RecipeType type, ImmutableList<Recipe> recipes) {
        this.type = type;
        this.recipes = recipes;
    }
}
