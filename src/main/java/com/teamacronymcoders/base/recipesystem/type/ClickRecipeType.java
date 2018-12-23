package com.teamacronymcoders.base.recipesystem.type;

import com.teamacronymcoders.base.recipesystem.RecipeSystem;
import com.teamacronymcoders.base.recipesystem.handler.ClickedRecipeHandler;
import com.teamacronymcoders.base.recipesystem.handler.IRecipeHandler;
import net.minecraft.util.ResourceLocation;

public class ClickRecipeType extends RecipeType {
    public ClickRecipeType(ResourceLocation name) {
        super(name);
    }

    @Override
    public IRecipeHandler createHandler() {
        return new ClickedRecipeHandler(RecipeSystem.getRecipesFor(this));
    }
}
