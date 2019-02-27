package com.teamacronymcoders.base.client.models;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.IModAware;
import com.teamacronymcoders.base.items.IHasSubItems;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public interface IHasModel extends IHasSubItems, IModAware {
    default List<String> getModelNames(List<String> modelNames) {
        return modelNames;
    }

    default List<ResourceLocation> getResourceLocations(List<ResourceLocation> resourceLocations) {
        IBaseMod mod = this.getMod();
        if (mod != null) {
            getModelNames(new ArrayList<>()).forEach(modelName ->
                    resourceLocations.add(new ResourceLocation(mod.getID(), modelName.replace(mod.getID() + ".", ""))));
        }
        return resourceLocations;
    }

    default List<ModelResourceLocation> getModelResourceLocations(List<ModelResourceLocation> models) {
        List<ResourceLocation> resourceLocations = getResourceLocations(new ArrayList<>());
        for (ResourceLocation resourceLocation : resourceLocations) {
            models.add(new ModelResourceLocation(resourceLocation, "inventory"));
        }
        return models;
    }
}
