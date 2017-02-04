package com.teamacronymcoders.base.materialsystem.parts;

import java.util.Locale;

public class Part {
    private String name;
    private String unlocalizedName;
    private PartType partType;

    public Part(String name, PartType partType) {
        this.name = name;
        this.unlocalizedName = name.substring(0, 1).toLowerCase(Locale.ROOT) + name.substring(1);
        this.partType = partType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnlocalizedName() {
        return unlocalizedName;
    }

    public void setUnlocalizedName(String unlocalizedName) {
        this.unlocalizedName = unlocalizedName;
    }

    public PartType getPartType() {
        return partType;
    }

    public void setPartType(PartType partType) {
        this.partType = partType;
    }

    public String getOreDictPrefix() {
        return this.unlocalizedName;
    }
}
