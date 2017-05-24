package com.teamacronymcoders.base.client.models;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.items.IHasSubItems;
import com.teamacronymcoders.base.util.Platform;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public interface IHasModel extends IHasSubItems {
    default List<String> getModelNames(List<String> modelNames) {
        return modelNames;
    }

    default List<ResourceLocation> getResourceLocations(List<ResourceLocation> resourceLocations) {
        IBaseMod mod = Platform.getCurrentMod();
        if (mod != null) {
            getModelNames(new ArrayList<>()).forEach(modelName ->
                    resourceLocations.add(new ResourceLocation(mod.getID(), modelName.replace(mod.getID() + ".", ""))));
        }
        return resourceLocations;
    }

    @SideOnly(Side.CLIENT)
    default List<ModelResourceLocation> getModelResourceLocations(List<ModelResourceLocation> models) {
        List<ResourceLocation> resourceLocations = getResourceLocations(new ArrayList<>());
        for (ResourceLocation resourceLocation : resourceLocations) {
            models.add(new ModelResourceLocation(resourceLocation, "inventory"));
        }
        return models;
    }
}
