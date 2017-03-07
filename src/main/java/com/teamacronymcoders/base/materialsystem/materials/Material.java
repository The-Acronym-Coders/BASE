package com.teamacronymcoders.base.materialsystem.materials;

import com.teamacronymcoders.base.materialsystem.MaterialsSystem;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.awt.*;
import java.util.Locale;

public class Material {
    private String name;
    private String unlocalizedName;
    private Color color;
    private boolean hasEffect;

    Material(String name) {
        this(name, Color.BLACK, false);
    }

    Material(String name, Color color, boolean hasEffect) {
        this.name = name;
        this.setUnlocalizedName(name.substring(0, 1).toLowerCase(Locale.ROOT) + name.substring(1));
        this.color = color;
        this.hasEffect = hasEffect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isHasEffect() {
        return hasEffect;
    }

    public void setHasEffect(boolean hasEffect) {
        this.hasEffect = hasEffect;
    }

    public String getUnlocalizedName() {
        return unlocalizedName;
    }

    public void setUnlocalizedName(String unlocalizedName) {
        this.unlocalizedName = unlocalizedName;
    }

    public void registerPartsFor(String... partNames) {
        MaterialsSystem.registerPartsForMaterial(this, partNames);
    }
}
