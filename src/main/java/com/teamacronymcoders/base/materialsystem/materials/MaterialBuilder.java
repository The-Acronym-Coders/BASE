package com.teamacronymcoders.base.materialsystem.materials;

import com.teamacronymcoders.base.materialsystem.MaterialException;
import com.teamacronymcoders.base.materialsystem.MaterialsSystem;

import java.awt.*;

public class MaterialBuilder {
    private String name;
    private String unlocalizedName;
    private Color color;
    private boolean hasEffect;

    public MaterialBuilder() {
        MaterialsSystem.MATERIALS_NOT_BUILT.add(this);
    }

    public MaterialBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public MaterialBuilder setUnlocalizedName(String unlocalizedName) {
        this.unlocalizedName = unlocalizedName;
        return this;
    }

    public MaterialBuilder setColor(Color color) {
        this.color = color;
        return this;
    }

    public MaterialBuilder setHasEffect(boolean hasEffect) {
        this.hasEffect = hasEffect;
        return this;
    }

    public Material createMaterial() throws MaterialException {
        validate();
        Material material = new Material(this.name, this.unlocalizedName, this.color, this.hasEffect);
        MaterialsSystem.registerMaterial(material);
        MaterialsSystem.MATERIALS_NOT_BUILT.remove(this);
        return material;
    }

    private void validate() throws MaterialException {
        String missingField = null;
        if (this.name == null) {
            missingField = "name";
        } else if (this.color == null) {
            missingField = "color";
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