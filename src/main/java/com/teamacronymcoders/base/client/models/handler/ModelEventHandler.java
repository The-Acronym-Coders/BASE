package com.teamacronymcoders.base.client.models.handler;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelEventHandler {
    private ModelHandler modelHandler;

    ModelEventHandler(ModelHandler modelHandler) {
        this.modelHandler = modelHandler;
    }

    @SubscribeEvent
    public void onModelEvent(ModelRegistryEvent modelRegistryEvent) {
        this.modelHandler.registerModels();
    }
}
