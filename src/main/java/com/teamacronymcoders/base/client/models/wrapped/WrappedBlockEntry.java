package com.teamacronymcoders.base.client.models.wrapped;

import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.util.Map;

public class WrappedBlockEntry {
    private Color color;
    private Map<ResourceLocation, Boolean> layers;

    public WrappedBlockEntry(Map<ResourceLocation, Boolean> layers, Color color) {
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
}
