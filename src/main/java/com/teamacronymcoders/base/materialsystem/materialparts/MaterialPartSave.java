package com.teamacronymcoders.base.materialsystem.materialparts;

import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;
import java.util.stream.Collectors;

public class MaterialPartSave {
    private Map<String, Integer> materialMappings;

    private MaterialPartSave(Map<String, Integer> materialMappings) {
        this.materialMappings = materialMappings;
    }

    public Map<String, Integer> getMaterialMappings() {
        return materialMappings;
    }

    public static MaterialPartSave of(Map<String, Integer> materialMappings) {
        return new MaterialPartSave(materialMappings);
    }
}
