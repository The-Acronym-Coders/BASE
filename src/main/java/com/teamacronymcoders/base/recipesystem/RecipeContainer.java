package com.teamacronymcoders.base.recipesystem;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public  class RecipeContainer implements ICapabilityProvider {
    private final ICapabilityProvider capabilityProvider;
    private final Object recipeHolder;

    public RecipeContainer(Object recipeHolder, ICapabilityProvider capabilityProvider) {
        this.capabilityProvider = capabilityProvider;
        this.recipeHolder = recipeHolder;
    }

    public void tick() {

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
}
