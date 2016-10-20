package com.teamacronymcoders.base.items;

import net.minecraft.item.crafting.IRecipe;

import java.util.List;

@FunctionalInterface
public interface IHasRecipe {
    List<IRecipe> getRecipes(List<IRecipe> recipes);
}
