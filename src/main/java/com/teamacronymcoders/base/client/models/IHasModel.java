package com.teamacronymcoders.base.client.models;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.util.Platform;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public interface IHasModel {
    default List<String> getModelNames(List<String> modelNames) {
        return modelNames;
    }

    default List<ResourceLocation> getResourceLocations(List<ResourceLocation> resourceLocations) {
        IBaseMod mod = Platform.getCurrentMod();
        if (mod != null) {
            getModelNames(new ArrayList<>()).forEach(modelName ->
                    resourceLocations.add(new ResourceLocation(mod.getID(), modelName)));
        }
        return resourceLocations;
    }
}
