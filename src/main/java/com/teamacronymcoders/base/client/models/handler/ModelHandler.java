package com.teamacronymcoders.base.client.models.handler;

import com.google.common.collect.Maps;
import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.client.models.IHasModel;
import com.teamacronymcoders.base.client.models.handler.ModelEventHandler;
import com.teamacronymcoders.base.util.ItemStackUtils;
import com.teamacronymcoders.base.util.logging.ILogger;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SideOnly(Side.CLIENT)
public class ModelHandler {
    private Map<ItemStack, ModelResourceLocation> models;
    private ILogger logger;

    public ModelHandler(IBaseMod mod) {
        this.logger = mod.getLogger();
        this.models = Maps.newHashMap();
        MinecraftForge.EVENT_BUS.register(new ModelEventHandler(this));
    }

    public void registerModels(IHasModel model) {
        List<ItemStack> allSubItems = new ArrayList<>();
        model.getAllSubItems(allSubItems);
        int locationsIndex = 0;
        List<ModelResourceLocation> modelResourceLocations = model.getModelResourceLocations(new ArrayList<>());
        if (!modelResourceLocations.isEmpty()) {
            for (ItemStack itemStack : allSubItems) {
                ModelResourceLocation modelResourceLocation = modelResourceLocations.get(locationsIndex);
                if (ItemStackUtils.isValid(itemStack) && modelResourceLocation != null) {
                    this.models.put(itemStack, modelResourceLocation);
                    locationsIndex++;
                    if (locationsIndex >= modelResourceLocations.size()) {
                        locationsIndex = 0;
                    }
                } else {
                    this.logger.warning("Found IHasModel with Empty Itemstack or ResourceLocation");
                }
            }
        } else {
            ItemStack itemStack;
            if (!allSubItems.isEmpty() && !(itemStack = allSubItems.get(0)).isEmpty()) {
                this.logger.warning(itemStack.getUnlocalizedName() + " has no models");
            } else {
                this.logger.warning("There's an issue with an IHasModel.");
            }
        }
    }

    public void registerModels() {
        this.models.forEach((itemStack, modelResourceLocation) ->
                ModelLoader.setCustomModelResourceLocation(itemStack.getItem(), itemStack.getMetadata(), modelResourceLocation));
    }
}
