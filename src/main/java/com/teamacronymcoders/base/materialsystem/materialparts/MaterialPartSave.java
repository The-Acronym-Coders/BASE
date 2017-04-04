package com.teamacronymcoders.base.materialsystem.materialparts;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class MaterialPartSave {
    private BiMap<Integer, MaterialPart> materialParts;

    public MaterialPartSave() {
        HashBiMap.create();
    }

    public BiMap<Integer, MaterialPart> getMaterialParts() {
        return materialParts;
    }

    public void setMaterialParts(BiMap<Integer, MaterialPart> materialParts) {
        this.materialParts = materialParts;
    }
}
