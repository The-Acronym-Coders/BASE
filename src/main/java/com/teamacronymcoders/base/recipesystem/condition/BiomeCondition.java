package com.teamacronymcoders.base.recipesystem.condition;

import com.google.gson.annotations.JsonAdapter;
import com.teamacronymcoders.base.recipesystem.RecipeContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class BiomeCondition implements ICondition {
    @JsonAdapter(value = ResourceLocation.Serializer.class, nullSafe = false)
    private final ResourceLocation biome;
    private final boolean inBiome;

    public BiomeCondition(ResourceLocation biome, boolean inBiome) {
        this.biome = biome;
        this.inBiome = inBiome;
    }

    @Override
    public boolean isMet(RecipeContainer recipeContainer, @Nullable EntityPlayer entityPlayer) {
        return recipeContainer.getWorld().isBlockLoaded(recipeContainer.getPos()) &&
                (biome.equals(recipeContainer.getWorld().getBiome(recipeContainer.getPos()).getRegistryName()) == inBiome);
    }
}
