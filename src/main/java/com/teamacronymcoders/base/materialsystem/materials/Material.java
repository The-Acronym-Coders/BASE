package com.teamacronymcoders.base.materialsystem.materials;

import com.teamacronymcoders.base.materialsystem.MaterialsSystem;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.awt.*;
import java.util.Locale;

@ZenClass("mods.base.Material")
public class Material {
    private String name;
    private String unlocalizedName;
    private Color color;
    private boolean hasEffect;

    public Material(String name) {
        this(name, Color.BLACK, false);
    }

    public Material(String name, Color color, boolean hasEffect) {
        this.name = name;
        this.setUnlocalizedName(name.substring(0, 1).toLowerCase(Locale.ROOT) + name.substring(1));
        this.color = color;
        this.hasEffect = hasEffect;
    }

    @ZenMethod
    public String getName() {
        return name;
    }

    @ZenMethod
    public void setName(String name) {
        this.name = name;
    }

    @ZenMethod
    public Color getColor() {
        return color;
    }

    @ZenMethod
    public void setColor(Color color) {
        this.color = color;
    }

    @ZenMethod
    public boolean isHasEffect() {
        return hasEffect;
    }

    @ZenMethod
    public void setHasEffect(boolean hasEffect) {
        this.hasEffect = hasEffect;
    }

    @ZenMethod
    public String getUnlocalizedName() {
        return unlocalizedName;
    }

    @ZenMethod
    public void setUnlocalizedName(String unlocalizedName) {
        this.unlocalizedName = unlocalizedName;
    }

    @ZenMethod
    public void registerPartsFor(String... partNames) {
        MaterialsSystem.registerPartsForMaterial(this, partNames);
    }
}
