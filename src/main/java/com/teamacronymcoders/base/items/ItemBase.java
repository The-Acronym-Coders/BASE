package com.teamacronymcoders.base.items;

import com.teamacronymcoders.base.client.models.IHasModel;

import java.util.List;

public class ItemBase extends ItemBaseNoModel implements IHasModel {


    public ItemBase(String name) {
        this("", name);
    }

    public ItemBase(String texturePath, String name) {
        super(texturePath, name);
    }


    @Override
    public List<String> getModelNames(List<String> modelNames) {
        modelNames.add(texturePath + name);
        return modelNames;
    }
}
