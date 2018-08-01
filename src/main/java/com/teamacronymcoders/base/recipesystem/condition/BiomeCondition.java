package com.teamacronymcoders.base.recipesystem.condition;

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
    public boolean isMet(World world, BlockPos blockPos, @Nullable EntityPlayer entityPlayer) {
        return world.isBlockLoaded(blockPos) && name.equals(world.getBiome(blockPos).getBiomeName());
    }
}
