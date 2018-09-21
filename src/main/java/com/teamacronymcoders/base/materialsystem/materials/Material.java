package com.teamacronymcoders.base.materialsystem.materials;

import com.teamacronymcoders.base.util.TextUtils;

import java.awt.*;

public class Material {
    protected String name;
    protected String unlocalizedName;
    protected String oreDictSuffix;
    protected Color color;
    protected boolean hasEffect;

    Material(String name, Color color, boolean hasEffect) {
        this.name = name;
        this.unlocalizedName = TextUtils.toSnakeCase(name);
        this.oreDictSuffix = TextUtils.removeSpecialCharacters(name);
        this.color = color;
        this.hasEffect = hasEffect;
    }

    public String getName() {
        return name;
    }

    public String getOreDictSuffix() {
        return oreDictSuffix;
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

    public String getTranslationKey() {
        return "base.material." + unlocalizedName;
    }
}
