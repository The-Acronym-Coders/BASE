package com.teamacronymcoders.base.client.models.wrapped;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class WrappedBlockEntry {
    private IBlockState blockState;
    private List<ResourceLocation> layers;

    public WrappedBlockEntry(IBlockState blockState, List<ResourceLocation> layers) {
        this.blockState = blockState;
        this.layers = layers;
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
}
