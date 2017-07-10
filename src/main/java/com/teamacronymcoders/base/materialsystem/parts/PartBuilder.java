package com.teamacronymcoders.base.materialsystem.parts;

import com.teamacronymcoders.base.materialsystem.MaterialException;
import com.teamacronymcoders.base.materialsystem.MaterialSystem;
import com.teamacronymcoders.base.materialsystem.parttype.PartType;
import com.teamacronymcoders.base.util.TextUtils;

public class PartBuilder {
    private String name;
    private String unlocalizedName;
    private PartType partType;

    public PartBuilder() {
        MaterialSystem.partsNotBuilt.add(this);
    }

    public PartBuilder setName(String name) {
        this.name = name;
        if (unlocalizedName == null) {
            this.setUnlocalizedName("base.part." + TextUtils.toSnakeCase(name));
        }
        return this;
    }

    public PartBuilder setPartType(PartType partType) {
        this.partType = partType;
        return this;
    }

    public PartBuilder setUnlocalizedName(String unlocalizedName) {
        this.unlocalizedName = unlocalizedName;
        return this;
    }

    public Part build() throws MaterialException {
        validate();
        Part part =  new Part(name, unlocalizedName, partType);
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
        } else if (this.unlocalizedName == null) {
            missingField = "unlocalizedName";
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