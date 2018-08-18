package com.teamacronymcoders.base.recipesystem.input;

import com.google.gson.annotations.JsonAdapter;
import com.teamacronymcoders.base.json.deserializer.EntityClassDeserializer;
import com.teamacronymcoders.base.recipesystem.RecipeContainer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class EntityInput implements IInput {
    @JsonAdapter(value = EntityClassDeserializer.class, nullSafe = false)
    private final Class<? extends Entity> entity;

    public EntityInput(Class<? extends Entity> entity) {
        this.entity = entity;
    }

    @Override
    public boolean isMatched(RecipeContainer recipeContainer) {
        return recipeContainer.getRecipeHolder().getClass().isAssignableFrom(entity);
    }

    @Override
    public void consume(RecipeContainer recipeContainer) {
        if (recipeContainer.getRecipeHolder() instanceof Entity) {
            ((Entity) recipeContainer.getRecipeHolder()).setDead();
        }
    }
}
