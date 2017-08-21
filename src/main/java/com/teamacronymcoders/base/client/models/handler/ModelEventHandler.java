package com.teamacronymcoders.base.client.models.handler;

import com.teamacronymcoders.base.BaseModFoundation;
import com.teamacronymcoders.base.IBaseMod;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelEventHandler {
    private ModelHandler modelHandler;
    private IBaseMod mod;

    ModelEventHandler(ModelHandler modelHandler, IBaseMod mod) {
        this.modelHandler = modelHandler;
        this.mod = mod;
    }

    @SubscribeEvent
    public void onModelEvent(ModelRegistryEvent modelRegistryEvent) {
        this.modelHandler.registerModels();

        if (mod.hasExternalResources()) {
            BaseModFoundation.externalResourceUsers--;
            if (BaseModFoundation.externalResourceUsers <= 0) {
                mod.getLibProxy().assembleResourcePack();
            }
        }
    }
}
