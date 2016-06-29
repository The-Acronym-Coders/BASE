package com.acronym.base.api.materials;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Material {

    private String name;
    private Color color;
    private EnumPartType[] types;
    private List<EnumPartType> typeList = new ArrayList<>();

    public Material(String name, Color color, EnumPartType... types) {
        this.name = name;
        this.color = color;
        this.types = types;
        if (types != null)
            for (EnumPartType type : types)
                this.typeList.add(type);
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public EnumPartType[] getTypes() {
        return types;
    }

    public boolean isTypeSet(EnumPartType type) {
        return this.typeList.contains(type);
    }

    public enum EnumPartType {
        NUGGET,
        DUST,
        INGOT,
        BLOCK,
        ORE,
        GEAR,
        FLUID,
        PLATE
    }
}
