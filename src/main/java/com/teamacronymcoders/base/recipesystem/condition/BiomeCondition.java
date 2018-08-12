package com.teamacronymcoders.base.recipesystem.condition;

import com.teamacronymcoders.base.recipesystem.RecipeContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BiomeCondition implements ICondition {
    private final String name;

    public BiomeCondition(String name) {
        this.name = name;
    }

    @Override
    public boolean isMet(RecipeContainer recipeContainer, @Nullable EntityPlayer entityPlayer) {
        return recipeContainer.getWorld().isBlockLoaded(recipeContainer.getPos()) &&
                name.equals(recipeContainer.getWorld().getBiome(recipeContainer.getPos()).getBiomeName());
    }
}
