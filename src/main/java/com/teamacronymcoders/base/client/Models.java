package com.teamacronymcoders.base.client;

import com.teamacronymcoders.base.client.models.IHasModel;
import com.teamacronymcoders.base.util.ItemStackUtils;
import com.teamacronymcoders.base.util.Platform;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class Models {
    public static void registerModels(IHasModel model) {
        List<ItemStack> allSubItems = new ArrayList<>();
        model.getAllSubItems(allSubItems);
        int locationsIndex = 0;
        List<ModelResourceLocation> modelResourceLocations = model.getModelResourceLocations(new ArrayList<>());
        if (!modelResourceLocations.isEmpty()) {
            for (ItemStack itemStack : allSubItems) {
                ModelResourceLocation modelResourceLocation = modelResourceLocations.get(locationsIndex);
                if (ItemStackUtils.isValidItemStack(itemStack) && modelResourceLocation != null) {
                    ModelLoader.setCustomModelResourceLocation(itemStack.getItem(), itemStack.getMetadata(), modelResourceLocation);
                    locationsIndex++;
                    if (locationsIndex >= modelResourceLocations.size()) {
                        locationsIndex = 0;
                    }
                } else {
                    Platform.attemptLogErrorToCurrentMod("Found IHasModel with null Itemstack or ResourceLocation");
                }
            }
        } else {
            ItemStack itemStack;
            if (!allSubItems.isEmpty() && (itemStack = allSubItems.get(0)) != null) {
                Platform.attemptLogErrorToCurrentMod(itemStack.getUnlocalizedName() + " has no models");
            } else {
                Platform.attemptLogErrorToCurrentMod("There's an issue with an IHasModel.");
            }
        }
    }
}
