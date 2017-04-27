package com.teamacronymcoders.base.materialsystem.materials;

import com.teamacronymcoders.base.materialsystem.MaterialException;
import com.teamacronymcoders.base.materialsystem.MaterialSystem;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;

import java.awt.*;
import java.util.List;

public class Material {
    protected String name;
    protected String unlocalizedName;
    protected Color color;
    protected boolean hasEffect;

    Material(String name, String unlocalizedName, Color color, boolean hasEffect) {
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
        return MaterialSystem.registerPartsForMaterial(this, partNames);
    }
}
