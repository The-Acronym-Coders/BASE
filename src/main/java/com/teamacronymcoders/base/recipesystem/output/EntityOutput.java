package com.teamacronymcoders.base.recipesystem.output;

import com.teamacronymcoders.base.recipesystem.RecipeContainer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Function;

public class EntityOutput implements IOutput {
    private final Function<World, ? extends Entity> entityConstructor;

    public EntityOutput(Function<World, ? extends Entity> entityConstructor) {
        this.entityConstructor = entityConstructor;
    }

    @Override
    public boolean canOutput(RecipeContainer recipeContainer) {
        return true;
    }

    @Override
    public void output(RecipeContainer recipeContainer) {
        Entity entity = entityConstructor.apply(recipeContainer.getWorld());
        BlockPos blockPos = recipeContainer.getPos();
        entity.setPosition(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        recipeContainer.getWorld().spawnEntity(entity);
    }
}
