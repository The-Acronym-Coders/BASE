package com.teamacronymcoders.base.materialsystem.materials;

import com.teamacronymcoders.base.util.TextUtils;

import java.awt.*;

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

    public String getOreDictSuffix() {
        return TextUtils.removeSpecialCharacters(this.name);
    }
}
