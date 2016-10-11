package com.acronym.base.api.materials;

import com.acronym.base.Base;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;


public class MaterialType {

    private String name;
    private Color colour;
    private ArrayList<EnumPartType> types;
    private boolean hasEffect;


    public MaterialType() {
        this("null", Color.white, false, new ArrayList<EnumPartType>());
    }

    public MaterialType(String name, Color colour, boolean hasEffect, ArrayList<EnumPartType> types) {
        this.name = name;
        this.colour = colour;
        this.hasEffect = hasEffect;
        this.types = types;
    }

    public MaterialType(String name, Color colour, boolean hasEffect, EnumPartType... types) {
        this(name, colour, hasEffect, new ArrayList<EnumPartType>(Arrays.asList(types)));
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

    public ArrayList<EnumPartType> getTypes() {
        return types;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }

    public boolean isTypeSet(EnumPartType type) {
        return this.getTypes().contains(type);
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
        sb.append(", types=").append(types);
        sb.append('}');

        return sb.toString();
    }
}
