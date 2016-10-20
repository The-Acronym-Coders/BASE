package com.teamacronymcoders.base.client.models;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.util.Platform;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public interface IHasModel {
    default List<String> getModelNames() {
        return new ArrayList<>();
    }

    default List<ResourceLocation> getResourceLocations() {
        IBaseMod mod = Platform.getCurrentMod();
        List<ResourceLocation> resourceLocations = new ArrayList<>();
        if (mod != null) {
            getModelNames().forEach(modelName ->
                    resourceLocations.add(new ResourceLocation(mod.getPrefix(), modelName)));
        }
        return resourceLocations;
    }
}
