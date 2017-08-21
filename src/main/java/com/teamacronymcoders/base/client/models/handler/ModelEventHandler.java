package com.teamacronymcoders.base.client.models.handler;

import com.teamacronymcoders.base.IBaseMod;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.jws.WebParam;

@SideOnly(Side.CLIENT)
public class ModelEventHandler {
    private ModelHandler modelHandler;

    ModelEventHandler(ModelHandler modelHandler) {
        this.modelHandler = modelHandler;
    }

    public void onModelEvent(ModelRegistryEvent modelRegistryEvent) {
        this.modelHandler.registerModels();;
    }
}
