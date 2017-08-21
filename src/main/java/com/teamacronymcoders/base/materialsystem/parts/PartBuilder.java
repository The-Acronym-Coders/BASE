package com.teamacronymcoders.base.materialsystem.parts;

import com.teamacronymcoders.base.materialsystem.MaterialException;
import com.teamacronymcoders.base.materialsystem.MaterialSystem;
import com.teamacronymcoders.base.materialsystem.MaterialUser;
import com.teamacronymcoders.base.materialsystem.parttype.PartType;

public class PartBuilder {
    private String name;
    private PartType partType;
    private MaterialUser materialUser;

    public PartBuilder(MaterialUser user) {
        MaterialSystem.partsNotBuilt.add(this);
        this.materialUser = user;
    }

    public PartBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PartBuilder setPartType(PartType partType) {
        this.partType = partType;
        return this;
    }

    public Part build() throws MaterialException {
        validate();
        Part part =  new Part(name, partType, materialUser);
        MaterialSystem.registerPart(part);
        MaterialSystem.partsNotBuilt.remove(this);
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