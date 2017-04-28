package com.teamacronymcoders.base.materialsystem.parts;

import com.google.common.collect.Lists;
import com.teamacronymcoders.base.materialsystem.MaterialException;
import com.teamacronymcoders.base.materialsystem.MaterialSystem;

import java.util.List;

public class PartBuilder {
    private String name;
    private PartType partType;
    private List<PartDataPiece> data;
    private MaterialSystem materialSystem;

    public PartBuilder(MaterialSystem materialSystem) {
        materialSystem.partsNotBuilt.add(this);
        data = Lists.newArrayList();
    }

    public PartBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PartBuilder setPartType(PartType partType) {
        this.partType = partType;
        return this;
    }

    public PartBuilder setData(List<PartDataPiece> data) {
        this.data = data;
        return this;
    }

    public PartBuilder addData(PartDataPiece dataName) {
        this.data.add(dataName);
        return this;
    }

    public Part createPart() throws MaterialException {
        validate();
        Part part =  new Part(name, partType, data);
        this.materialSystem.registerPart(part);
        this.materialSystem.partsNotBuilt.remove(this);
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