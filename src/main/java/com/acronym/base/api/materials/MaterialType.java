package com.acronym.base.api.materials;

import com.acronym.base.Base;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//TODO why the fuck is stuff like this not documented at all!?!?!?!

public class MaterialType {

    private String name;
    private Color colour;
    private EnumPartType[] types;
    private List<EnumPartType> typeList = new ArrayList<>();
    private boolean hasEffect;

    public MaterialType() {
        this("null", Color.white, false, null);
    }

    public MaterialType(String name, Color colour, boolean hasEffect, EnumPartType... types) {
        this.name = name;
        this.colour = colour;
        this.hasEffect = hasEffect;
        this.types = types;

        if (types != null)
            for (EnumPartType type : types)
                this.typeList.add(type);
    }

    public String getName() {
        return name;
    }

    public String getUnlocalizedName() {
        return "material.base." + this.name.toLowerCase();
    }

    public String getLocalizedName() {
        return Base.languageHelper.none(getName());
    }

    public Color getColour() {
        return colour;
    }

    public EnumPartType[] getTypes() {
        return types;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }

    public boolean isTypeSet(EnumPartType type) {
        return this.typeList.contains(type);
    }

    public boolean isHasEffect() {
        return hasEffect;
    }

    public void setHasEffect(boolean hasEffect) {
        this.hasEffect = hasEffect;
    }

    public enum EnumPartType {
        BLOCK,
        DUST,
        GEAR,
        INGOT,
        NUGGET,
        ORE,
        PLATE;

        public String getUnlocalizedName() {
            return "base.part." + this.getName().toLowerCase();
        }

        public String getLocalizedName() {
            return Base.languageHelper.none(getUnlocalizedName());
        }

        public String getName() {
            return this.name();
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MaterialType{");

        sb.append("name='").append(name).append('\'');
        sb.append(", colour=").append(colour);
        sb.append(", types=").append(Arrays.toString(types));
        sb.append(", typeList=").append(typeList);
        sb.append('}');

        return sb.toString();
    }
}
