package com.teamacronymcoders.base.materialsystem.parts;

import com.teamacronymcoders.base.materialsystem.parttype.PartType;
import com.teamacronymcoders.base.util.TextUtils;

import java.util.List;
import java.util.Locale;

public class Part {
    private String name;
    private String unlocalizedName;
    private String oreDictName;
    private PartType partType;
    private List<PartDataPiece> data;

    Part(String name, PartType partType, List<PartDataPiece> data) {
        this.name = name;
        this.unlocalizedName = TextUtils.toSnakeCase(name);
        String oreDict = name.substring(0, 1).toLowerCase(Locale.US) + name.substring(1);
        this.oreDictName = TextUtils.removeSpecialCharacters(oreDict);
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
        return this.oreDictName;
    }

    public List<PartDataPiece> getData() {
        return this.data;
    }

}
