package com.teamacronymcoders.base.materialsystem.parts;

import java.util.List;
import java.util.Locale;

public class Part {
    private String name;
    private String unlocalizedName;
    private PartType partType;
    private List<PartDataPiece> data;

    Part(String name, PartType partType, List<PartDataPiece> data) {
        this.name = name;
        this.unlocalizedName = name.substring(0, 1).toLowerCase(Locale.ROOT) + name.substring(1);
        this.partType = partType;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public String getUnlocalizedName() {
        return unlocalizedName;
    }

    public PartType getPartType() {
        return partType;
    }

    public String getPartTypeName() {
        return partType.getName();
    }

    public String getOreDictPrefix() {
        return this.unlocalizedName;
    }

    public List<PartDataPiece> getData() {
        return this.data;
    }

}
