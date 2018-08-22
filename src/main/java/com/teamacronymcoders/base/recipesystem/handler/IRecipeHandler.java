package com.teamacronymcoders.base.recipesystem.handler;

import com.teamacronymcoders.base.recipesystem.Recipe;
import com.teamacronymcoders.base.recipesystem.RecipeContainer;
import net.minecraft.entity.player.EntityPlayer;

import javax.annotation.Nullable;
import java.util.List;

public interface IRecipeHandler {
    boolean handleRecipe(RecipeContainer recipeContainer, @Nullable EntityPlayer entityPlayer);

    void reloadRecipes(List<Recipe> recipeList);
}
