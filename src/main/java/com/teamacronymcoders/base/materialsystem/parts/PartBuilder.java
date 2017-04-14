package com.teamacronymcoders.base.materialsystem.parts;

import com.google.common.collect.Lists;
import com.teamacronymcoders.base.materialsystem.MaterialException;
import com.teamacronymcoders.base.materialsystem.MaterialsSystem;

import java.util.List;

public class PartBuilder {
    private String name;
    private PartType partType;
    private List<String> possibleData;
    private List<String> requiredData;

    public PartBuilder() {
        MaterialsSystem.PARTS_NOT_BUILT.add(this);
        possibleData = Lists.newArrayList();
        requiredData = Lists.newArrayList();
    }

    public PartBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PartBuilder setPartType(PartType partType) {
        this.partType = partType;
        return this;
    }

    public PartBuilder setPossibleData(List<String> possibleData) {
        this.possibleData = possibleData;
        return this;
    }

    public PartBuilder setRequiredData(List<String> requiredData) {
        this.requiredData = requiredData;
        for (String dataName : this.requiredData) {
            if (!possibleData.contains(dataName)) {
                possibleData.add(dataName);
            }
        }
        return this;
    }

    public PartBuilder addPossibleData(String dataName) {
        this.possibleData.add(dataName);
        return this;
    }

    public PartBuilder addRequiredData(String dataName) {
        this.requiredData.add(dataName);
        if (!possibleData.contains(dataName)) {
            possibleData.add(dataName);
        }
        return this;
    }

    public Part createPart() throws MaterialException {
        validate();
        Part part =  new Part(name, partType, possibleData, requiredData);
        MaterialsSystem.registerPart(part);
        MaterialsSystem.PARTS_NOT_BUILT.remove(this);
        return part;
    }

    private void validate() throws MaterialException {
        String missingField = null;
        if (this.name == null) {
            missingField = "name";
        } else if (this.partType == null) {
            missingField = "part type";
        }
        if (missingField != null) {
            String message = "Field " + missingField + " is not set";
            if (this.name != null) {
                message += " for material with name: " + this.name;
            }
            throw new MaterialException(message);
        }
    }
}