package com.teamacronymcoders.base.recipesystem.handler;

import com.teamacronymcoders.base.recipesystem.Recipe;
import com.teamacronymcoders.base.recipesystem.RecipeContainer;
import net.minecraft.entity.player.EntityPlayer;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;

public class ClickedRecipeHandler implements IRecipeHandler {
    private final List<Recipe> recipes;
    private Recipe currentRecipe = null;

    public ClickedRecipeHandler(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public boolean handleRecipe(RecipeContainer recipeContainer, @Nullable EntityPlayer entityPlayer) {
        if (currentRecipe != null) {
            if (!currentRecipe.matches(recipeContainer, entityPlayer)) {
                currentRecipe = null;
            }
        }

        if (currentRecipe == null) {
            Iterator<Recipe> recipeIterator = recipes.iterator();
            while (currentRecipe == null && recipeIterator.hasNext()) {
                Recipe recipe = recipeIterator.next();
                if (recipe.matches(recipeContainer, entityPlayer)) {
                    currentRecipe = recipe;
                }
            }
        }

        if (currentRecipe != null) {
            if (currentRecipe.matches(recipeContainer, entityPlayer)) {
                if (currentRecipe.canOutput(recipeContainer)) {
                    currentRecipe.consumeInput(recipeContainer);
                    currentRecipe.doOutput(recipeContainer);
                    return true;
                }
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
