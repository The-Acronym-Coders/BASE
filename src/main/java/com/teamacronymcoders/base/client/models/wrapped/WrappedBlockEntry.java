package com.teamacronymcoders.base.client.models.wrapped;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import java.util.Collection;
import java.util.List;

public class WrappedBlockEntry {
    private IBlockState blockState;
    private ResourceLocation resourceLocation;
    private List<ResourceLocation> layers;

    public WrappedBlockEntry(IBlockState blockState, ResourceLocation resourceLocation, List<ResourceLocation> layers) {
        this.blockState = blockState;
        this.layers = layers;
        this.resourceLocation = resourceLocation;
    }

    public IBlockState getBlockState() {
        return blockState;
    }

    public void setBlockState(IBlockState blockState) {
        this.blockState = blockState;
    }

    public List<ResourceLocation> getLayers() {
        return layers;
    }

    public void setLayers(List<ResourceLocation> layers) {
        this.layers = layers;
    }

    public ResourceLocation getResourceLocation() {
        return resourceLocation;
    }

    public void setResourceLocation(ResourceLocation resourceLocation) {
        this.resourceLocation = resourceLocation;
    }
}
