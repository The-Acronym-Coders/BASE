package com.teamacronymcoders.base.recipesystem;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public  class RecipeContainer implements ICapabilityProvider {
    private final ICapabilityProvider capabilityProvider;
    private final Object recipeHolder;
    private final World world;
    private final BlockPos pos;

    public RecipeContainer(Object recipeHolder, ICapabilityProvider capabilityProvider, World world, BlockPos pos) {
        this.capabilityProvider = capabilityProvider;
        this.recipeHolder = recipeHolder;
        this.world = world;
        this.pos = pos;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capabilityProvider.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capabilityProvider.getCapability(capability, facing);
    }

    public Object getRecipeHolder() {
        return recipeHolder;
    }

    public World getWorld() {
        return this.world;
    }

    public BlockPos getPos() {
        return this.pos;
    }
}
