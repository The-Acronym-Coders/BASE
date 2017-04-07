package com.teamacronymcoders.base.materialsystem.materialparts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaterialPartData {
    private Map<String, Object> data;
    private List<String> requiredFields;
    private List<String> allFields;

    public MaterialPartData(List<String> requiredFields, List<String> allFields) {
        this.data = new HashMap<>();
        this.requiredFields = requiredFields;
        this.allFields = allFields;
    }

    public Object getDataPiece(String name) {
        return this.data.get(name);
    }

    public void addDataPiece(String name, Object value) {
        if (allFields.contains(name)) {
            this.data.put(name, value);
        }
    }

    public boolean validateRequired() {
        boolean valid = true;
        for (String string : requiredFields) {
            valid &= this.data.containsKey(string);
        }
        return valid;
    }
}
