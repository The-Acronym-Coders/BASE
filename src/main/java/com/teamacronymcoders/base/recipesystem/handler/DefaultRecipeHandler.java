package com.teamacronymcoders.base.recipesystem.handler;

import com.teamacronymcoders.base.recipesystem.Recipe;
import com.teamacronymcoders.base.recipesystem.RecipeContainer;
import net.minecraft.entity.player.EntityPlayer;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;

public class DefaultRecipeHandler implements IRecipeHandler {
    private final List<Recipe> recipes;
    private Recipe currentRecipe = null;
    private int waiting = 0;

    public DefaultRecipeHandler(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public boolean handleRecipe(RecipeContainer recipeContainer, @Nullable EntityPlayer entityPlayer) {
        if (waiting > 0) {
            waiting--;
        }

        if (currentRecipe == null && waiting <= 0) {
            Iterator<Recipe> recipeIterator = recipes.iterator();
            while (currentRecipe == null && recipeIterator.hasNext()) {
                Recipe recipe = recipeIterator.next();
                if (recipe.matches(recipeContainer, entityPlayer)) {
                    currentRecipe = recipe;
                }
            }
            waiting = 100;
        }

        if (currentRecipe != null) {
            if (currentRecipe.matches(recipeContainer, entityPlayer)) {
                if (currentRecipe.canOutput(recipeContainer)) {
                    currentRecipe.doOutput(recipeContainer);
                    return true;
                }
            } else {
                currentRecipe = null;
            }
        }

        return false;
    }

    @Override
    public void reloadRecipes(List<Recipe> recipeList) {
        recipes.clear();
        recipes.addAll(recipeList);
    }
}
