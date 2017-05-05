package com.teamacronymcoders.base.materialsystem.materials;

import com.teamacronymcoders.base.materialsystem.MaterialException;
import com.teamacronymcoders.base.materialsystem.MaterialSystem;
import com.teamacronymcoders.base.util.TextUtils;

import java.awt.*;

public class MaterialBuilder {
    private String name;
    private String unlocalizedName;
    private Color color;
    private boolean hasEffect;
    private MaterialSystem materialSystem;

    public MaterialBuilder(MaterialSystem materialSystem) {
        materialSystem.materialsNotBuilt.add(this);
        this.materialSystem = materialSystem;
    }

    public MaterialBuilder setName(String name) {
        this.name = name;
        return unlocalizedName == null ? this.setUnlocalizedName(TextUtils.toSnakeCase(name)) : this;
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
        materialSystem.registerMaterial(material);
        materialSystem.materialsNotBuilt.remove(this);
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