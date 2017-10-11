package com.teamacronymcoders.base.materialsystem.materialparts;

import com.google.common.collect.Maps;

import java.util.Map;

public class MaterialPartSave {
    private Map<String, Integer> materialMappings;

    public MaterialPartSave() {
        this(Maps.newHashMap());
    }

    public MaterialPartSave(Map<String, Integer> materialMappings) {
        this.materialMappings = materialMappings;
    }

    public Map<String, Integer> getMaterialMappings() {
        return materialMappings;
    }

    public static MaterialPartSave of(Map<String, Integer> materialMappings) {
        return new MaterialPartSave(materialMappings);
    }
}
