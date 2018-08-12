package com.teamacronymcoders.base.recipesystem.condition;

import com.teamacronymcoders.base.recipesystem.RecipeContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public interface ICondition {
    boolean isMet(RecipeContainer recipeContainer, @Nullable EntityPlayer entityPlayer);
}
