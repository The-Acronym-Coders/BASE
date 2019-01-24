package com.teamacronymcoders.base.recipesystem.output;

import com.google.gson.annotations.JsonAdapter;
import com.teamacronymcoders.base.json.deserializer.EntityFactoryDeserializer;
import com.teamacronymcoders.base.recipesystem.RecipeContainer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Function;

public class EntityOutput implements IOutput {
    @JsonAdapter(value = EntityFactoryDeserializer.class)
    private final Function<World, ? extends Entity> entity;

    public EntityOutput(Function<World, ? extends Entity> entity) {
        this.entity = entity;
    }

    @Override
    public boolean canOutput(RecipeContainer recipeContainer) {
        return true;
    }

    @Override
    public void output(RecipeContainer recipeContainer) {
        if (!recipeContainer.getWorld().isRemote) {
            Entity entity = this.entity.apply(recipeContainer.getWorld());
            BlockPos blockPos = recipeContainer.getPos();
            entity.setPosition(blockPos.getX(), blockPos.getY(), blockPos.getZ());
            recipeContainer.getWorld().spawnEntity(entity);
        }
    }
}
