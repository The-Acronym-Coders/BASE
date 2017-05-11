package com.teamacronymcoders.base.client.models.wrapped;

import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.util.Map;

public class WrappedBlockEntry {
    private Color color;
    private ResourceLocation baseResource;
    private Map<ResourceLocation, Boolean> layers;

    public WrappedBlockEntry(ResourceLocation baseResource, Map<ResourceLocation, Boolean> layers, int color) {
        this(baseResource, layers, new Color(color));
    }

    public WrappedBlockEntry(ResourceLocation baseResource, Map<ResourceLocation, Boolean> layers, Color color) {
        this.baseResource = baseResource;
        this.layers = layers;
        this.color = color;
    }

    public Map<ResourceLocation, Boolean> getLayers() {
        return layers;
    }

    public void setLayers(Map<ResourceLocation, Boolean> layers) {
        this.layers = layers;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ResourceLocation getBaseResource() {
        return baseResource;
    }

    public void setBaseResource(ResourceLocation baseResource) {
        this.baseResource = baseResource;
    }
}
