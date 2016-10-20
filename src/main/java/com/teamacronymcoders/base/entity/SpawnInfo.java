package com.teamacronymcoders.base.entity;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.Biome;

public class SpawnInfo {
    private int weighted;
    private int minimum;
    private int maximum;
    private EnumCreatureType creatureType;
    private Biome[] spawnBiomes;

    public SpawnInfo(int weightedProb, int min, int max, EnumCreatureType typeOfCreature, Biome... biomes) {
        this.setWeighted(weightedProb);
        this.setMinimum(min);
        this.setMaximum(max);
        this.setCreatureType(typeOfCreature);
        this.setSpawnBiomes(biomes);
    }

    public int getWeighted() {
        return weighted;
    }

    public void setWeighted(int weighted) {
        this.weighted = weighted;
    }

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }

    public EnumCreatureType getCreatureType() {
        return creatureType;
    }

    public void setCreatureType(EnumCreatureType creatureType) {
        this.creatureType = creatureType;
    }

    public Biome[] getSpawnBiomes() {
        return spawnBiomes;
    }

    public void setSpawnBiomes(Biome[] spawnBiomes) {
        this.spawnBiomes = spawnBiomes;
    }
}
