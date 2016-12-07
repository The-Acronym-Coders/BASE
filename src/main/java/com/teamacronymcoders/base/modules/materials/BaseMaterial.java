package com.teamacronymcoders.base.modules.materials;

import com.teamacronymcoders.base.api.materials.MaterialType;
import com.teamacronymcoders.base.api.materials.MaterialType.EnumPartType;

import java.awt.*;

public enum BaseMaterial {
    WOOD(new MaterialType("Wood", new Color(0x9d804f), false, EnumPartType.GEAR)),
    STONE(new MaterialType("Stone", new Color(0x514B4F), false, MaterialType.EnumPartType.GEAR)),
    IRON(new MaterialType("Iron", new Color(0x878787), false, MaterialType.EnumPartType.DUST, MaterialType.EnumPartType.NUGGET, EnumPartType.PLATE)),
    GOLD(new MaterialType("Gold", new Color(0xb5a62c), false, EnumPartType.DUST, EnumPartType.GEAR)),
    DIAMOND(new MaterialType("Diamond", new Color(0x58ad9e), false, EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.NUGGET, EnumPartType.PLATE)),
    COPPER(new MaterialType("Copper", new Color(0xF0A930), false, EnumPartType.BLOCK, EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.INGOT, EnumPartType.NUGGET, EnumPartType.PLATE, EnumPartType.ORE)),
    TIN(new MaterialType("Tin", new Color(0xA5CCCC), false, EnumPartType.BLOCK, EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.INGOT, EnumPartType.NUGGET, EnumPartType.PLATE, EnumPartType.ORE)),
    LEAD(new MaterialType("Lead", new Color(0x616D7E), false, EnumPartType.BLOCK, EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.INGOT, EnumPartType.NUGGET, EnumPartType.PLATE, EnumPartType.ORE)),
    SILVER(new MaterialType("Silver", new Color(0xDDDDDD), false, EnumPartType.BLOCK, EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.INGOT, EnumPartType.NUGGET, EnumPartType.PLATE, EnumPartType.ORE)),
    ELECTRUM(new MaterialType("Electrum", new Color(0xD4D32E), false, EnumPartType.BLOCK, EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.INGOT, EnumPartType.NUGGET, EnumPartType.PLATE)),
    NICKEL(new MaterialType("Nickel", new Color(0xA2A497), false, EnumPartType.BLOCK, EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.INGOT, EnumPartType.NUGGET, EnumPartType.PLATE, EnumPartType.ORE)),
    ALUMINUM(new MaterialType("Aluminum", new Color(0xCBD2D8), false, EnumPartType.BLOCK, EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.INGOT, EnumPartType.NUGGET, EnumPartType.PLATE, EnumPartType.ORE));

    private MaterialType materialType;

    BaseMaterial(MaterialType materialType) {
        this.materialType = materialType;
    }

    public MaterialType getMaterialType() {
        return this.materialType;
    }
}
