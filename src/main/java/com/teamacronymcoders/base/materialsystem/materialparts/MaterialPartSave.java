package com.teamacronymcoders.base.materialsystem.materialparts;

import com.google.common.collect.HashBiMap;

import java.util.Map;

public class MaterialPartSave {
    private Map<String, Integer> materialMappings;

    public MaterialPartSave() {
        materialMappings = HashBiMap.create();
    }

    public Map<String, Integer> getMaterialMappings() {
        return materialMappings;
    }

    public void setMaterialMappings(Map<Integer, MaterialPart> materialMappings) {
        this.materialMappings = HashBiMap.create();
        materialMappings.forEach((id, materialPart) -> this.materialMappings.put(materialPart.getName(), id));
    }
}
