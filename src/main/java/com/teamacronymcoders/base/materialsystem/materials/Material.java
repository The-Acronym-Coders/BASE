package com.teamacronymcoders.base.materialsystem.materials;

import com.teamacronymcoders.base.materialsystem.MaterialException;
import com.teamacronymcoders.base.materialsystem.MaterialsSystem;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.util.TextUtils;

import java.awt.*;
import java.util.List;

public class Material {
    protected String name;
    protected String unlocalizedName;
    protected Color color;
    protected boolean hasEffect;

    private Material() {

    }

    private Material(String name, String unlocalizedName, Color color, boolean hasEffect) {
        this.name = name;
        this.unlocalizedName = unlocalizedName;
        this.color = color;
        this.hasEffect = hasEffect;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public boolean isHasEffect() {
        return hasEffect;
    }

    public String getUnlocalizedName() {
        return unlocalizedName;
    }

    public List<MaterialPart> registerPartsFor(String... partNames) throws MaterialException {
        return MaterialsSystem.registerPartsForMaterial(this, partNames);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends Material {
        private Builder() {
            MaterialsSystem.MATERIALS_NOT_BUILT.add(this);
        }

        public void setName(String name) {
            this.name = name;
            if (unlocalizedName == null) {
                this.unlocalizedName = TextUtils.toSnakeCase(name);
            }
        }

        public void setUnlocalizedName(String unlocalizedName) {
            this.unlocalizedName = unlocalizedName;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public void setHasEffect(boolean hasEffect) {
            this.hasEffect = hasEffect;
        }

        @Override
        public List<MaterialPart> registerPartsFor(String... partNames) throws MaterialException {
            throw new MaterialException("You must call build() first");
        }

        public Material build() throws MaterialException {
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
}
