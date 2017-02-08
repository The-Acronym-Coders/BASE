package com.teamacronymcoders.base.materialsystem.parts;

import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.Locale;

@ZenClass("mods.base.Part")
public class Part {
    private String name;
    private String unlocalizedName;
    private PartType partType;

    public Part(String name, PartType partType) {
        this.name = name;
        this.unlocalizedName = name.substring(0, 1).toLowerCase(Locale.ROOT) + name.substring(1);
        this.partType = partType;
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
    public String getUnlocalizedName() {
        return unlocalizedName;
    }

    @ZenMethod
    public void setUnlocalizedName(String unlocalizedName) {
        this.unlocalizedName = unlocalizedName;
    }

    public PartType getPartType() {
        return partType;
    }

    public void setPartType(PartType partType) {
        this.partType = partType;
    }

    @ZenMethod
    public String getPartTypeName() {
        return partType.name();
    }

    @ZenMethod
    public void setPartType(String partTypeName) {
        this.partType = PartType.valueOf(partTypeName);
    }

    public String getOreDictPrefix() {
        return this.unlocalizedName;
    }
}
