package com.teamacronymcoders.base.materialsystem;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaterialPartData {
    private Map<String, Object> data = Maps.newHashMap();
    private List<String> requiredData = Lists.newArrayList();
    private List<String> allData = Lists.newArrayList();

    public MaterialPartData(MaterialPart materialPart) {

    }

}
