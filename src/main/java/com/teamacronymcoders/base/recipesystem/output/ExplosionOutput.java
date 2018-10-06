package com.teamacronymcoders.base.recipesystem.output;

import com.google.gson.annotations.JsonAdapter;
import com.teamacronymcoders.base.json.deserializer.BlockPosDeserializer;
import com.teamacronymcoders.base.recipesystem.RecipeContainer;
import net.minecraft.util.math.BlockPos;

public class ExplosionOutput implements IOutput {
    @JsonAdapter(BlockPosDeserializer.class)
    private final BlockPos offset;
    private final float strength;
    private final boolean damageTerrain;
    private final boolean causesFire;

    public ExplosionOutput(BlockPos offset, float strength, boolean damageTerrain, boolean causesFire) {
        this.offset = offset;
        this.strength = strength;
        this.damageTerrain = damageTerrain;
        this.causesFire = causesFire;
    }

    @Override
    public boolean canOutput(RecipeContainer recipeContainer) {
        return true;
    }

    @Override
    public void output(RecipeContainer recipeContainer) {
        BlockPos explosionPos = offset == null ? recipeContainer.getPos() : recipeContainer.getPos().add(offset);
        recipeContainer.getWorld().newExplosion(null, explosionPos.getX(), explosionPos.getY(), explosionPos.getZ(),
                strength, damageTerrain, causesFire);
    }
}
