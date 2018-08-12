package com.teamacronymcoders.base.recipesystem.condition;

import com.google.gson.annotations.JsonAdapter;
import com.teamacronymcoders.base.json.deserializer.ResourceLocationDeserializer;
import com.teamacronymcoders.base.recipesystem.RecipeContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class BiomeCondition implements ICondition {
    @JsonAdapter(value = ResourceLocationDeserializer.class, nullSafe = false)
    private final ResourceLocation biome;

    public BiomeCondition(ResourceLocation biome) {
        this.biome = biome;
    }

    @Override
    public boolean isMet(RecipeContainer recipeContainer, @Nullable EntityPlayer entityPlayer) {
        return recipeContainer.getWorld().isBlockLoaded(recipeContainer.getPos()) &&
                biome.equals(recipeContainer.getWorld().getBiome(recipeContainer.getPos()).getRegistryName());
    }
}
