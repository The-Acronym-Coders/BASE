package com.teamacronymcoders.base.client;

import com.teamacronymcoders.base.client.models.IHasModel;
import com.teamacronymcoders.base.util.Platform;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;

import java.util.ArrayList;
import java.util.List;

public class Models {
    public static void registerModels(IHasModel model) {
        List<ItemStack> allSubItems = new ArrayList<>();
        model.getAllSubItems(allSubItems);
        int locationsIndex = 0;
        List<ModelResourceLocation> modelResourceLocations = model.getModelResourceLocations(new ArrayList<>());
        if(modelResourceLocations.size() > 0) {
            for (ItemStack itemStack: allSubItems) {
                ModelLoader.setCustomModelResourceLocation(itemStack.getItem(), itemStack.getMetadata(), modelResourceLocations.get(locationsIndex));
                locationsIndex++;
                if (locationsIndex >= modelResourceLocations.size()) {
                    locationsIndex = 0;
                }
            }
        } else {
            Platform.attemptLogErrorToCurrentMod(allSubItems.get(0).getItem().getRegistryName() + " implements IHasModel, but lists no models");
        }
    }
}
