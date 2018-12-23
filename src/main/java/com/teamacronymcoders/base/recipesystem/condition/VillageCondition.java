package com.teamacronymcoders.base.recipesystem.condition;

import com.teamacronymcoders.base.recipesystem.RecipeContainer;
import net.minecraft.entity.player.EntityPlayer;

import javax.annotation.Nullable;

public class VillageCondition implements ICondition {
    private final boolean inVillage;

    public VillageCondition(boolean inVillage) {
        this.inVillage = inVillage;
    }

    @Override
    public boolean isMet(RecipeContainer recipeContainer, @Nullable EntityPlayer entityPlayer) {
        return locatedInVillage(recipeContainer) == inVillage;
    }

    @SuppressWarnings("ConstantConditions")
    private boolean locatedInVillage(RecipeContainer recipeContainer) {
        return recipeContainer.getWorld().getVillageCollection().getNearestVillage(recipeContainer.getPos(), 32) != null;
    }
}
