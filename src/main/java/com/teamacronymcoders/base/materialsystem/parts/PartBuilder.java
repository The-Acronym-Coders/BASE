package com.teamacronymcoders.base.materialsystem.parts;

import com.google.common.collect.Lists;
import com.teamacronymcoders.base.materialsystem.MaterialException;
import com.teamacronymcoders.base.materialsystem.MaterialSystem;
import com.teamacronymcoders.base.materialsystem.parttype.PartType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PartBuilder {
    private String name;
    private PartType partType;
    private List<PartDataPiece> data;
    private MaterialSystem materialSystem;

    public PartBuilder(MaterialSystem materialSystem) {
        materialSystem.partsNotBuilt.add(this);
        this.materialSystem = materialSystem;
        this.data = Lists.newArrayList();
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

    public PartBuilder setData(String... dataNames) {
        return this.setData(Arrays.stream(dataNames).map(PartDataPiece::new).collect(Collectors.toList()));
    }

    public Part build() throws MaterialException {
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