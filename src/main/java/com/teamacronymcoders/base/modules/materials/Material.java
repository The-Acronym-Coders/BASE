package com.teamacronymcoders.base.modules.materials;

import com.teamacronymcoders.base.api.materials.MaterialType;

import java.awt.*;

public enum Material {
    WOOD(new MaterialType("Wood", new Color(0x9d804f), false, MaterialType.EnumPartType.GEAR)),
    STONE(new MaterialType("Stone", new Color(0x514B4F), false, MaterialType.EnumPartType.GEAR)),
    IRON(new MaterialType("Iron", new Color(0x878787), false, MaterialType.EnumPartType.DUST, MaterialType.EnumPartType.NUGGET, MaterialType.EnumPartType.PLATE)),
    GOLD(new MaterialType("Gold", new Color(0xb5a62c), false, MaterialType.EnumPartType.DUST, MaterialType.EnumPartType.GEAR)),
    DIAMOND(new MaterialType("Diamond", new Color(0x58ad9e), false, MaterialType.EnumPartType.DUST, MaterialType.EnumPartType.GEAR, MaterialType.EnumPartType.NUGGET, MaterialType.EnumPartType.PLATE)),
    COPPER(new MaterialType("Copper", new Color(0xF0A930), false, MaterialType.EnumPartType.BLOCK, MaterialType.EnumPartType.DUST, MaterialType.EnumPartType.GEAR, MaterialType.EnumPartType.INGOT, MaterialType.EnumPartType.NUGGET, MaterialType.EnumPartType.PLATE, MaterialType.EnumPartType.ORE)),
    TIN(new MaterialType("Tin", new Color(0xA5CCCC), false, MaterialType.EnumPartType.BLOCK, MaterialType.EnumPartType.DUST, MaterialType.EnumPartType.GEAR, MaterialType.EnumPartType.INGOT, MaterialType.EnumPartType.NUGGET, MaterialType.EnumPartType.PLATE, MaterialType.EnumPartType.ORE)),
    LEAD(new MaterialType("Lead", new Color(0x616D7E), false, MaterialType.EnumPartType.BLOCK, MaterialType.EnumPartType.DUST, MaterialType.EnumPartType.GEAR, MaterialType.EnumPartType.INGOT, MaterialType.EnumPartType.NUGGET, MaterialType.EnumPartType.PLATE, MaterialType.EnumPartType.ORE)),
    SILVER(new MaterialType("Silver", new Color(0xDDDDDD), false, MaterialType.EnumPartType.BLOCK, MaterialType.EnumPartType.DUST, MaterialType.EnumPartType.GEAR, MaterialType.EnumPartType.INGOT, MaterialType.EnumPartType.NUGGET, MaterialType.EnumPartType.PLATE, MaterialType.EnumPartType.ORE)),
    ELECTRUM(new MaterialType("Electrum", new Color(0xD4D32E), false, MaterialType.EnumPartType.BLOCK, MaterialType.EnumPartType.DUST, MaterialType.EnumPartType.GEAR, MaterialType.EnumPartType.INGOT, MaterialType.EnumPartType.NUGGET, MaterialType.EnumPartType.PLATE)),
    NICKEL(new MaterialType("Nickel", new Color(0xA2A497), false, MaterialType.EnumPartType.BLOCK, MaterialType.EnumPartType.DUST, MaterialType.EnumPartType.GEAR, MaterialType.EnumPartType.INGOT, MaterialType.EnumPartType.NUGGET, MaterialType.EnumPartType.PLATE, MaterialType.EnumPartType.ORE)),
    ALUMINUM(new MaterialType("Aluminum", new Color(0xCBD2D8), false, MaterialType.EnumPartType.BLOCK, MaterialType.EnumPartType.DUST, MaterialType.EnumPartType.GEAR, MaterialType.EnumPartType.INGOT, MaterialType.EnumPartType.NUGGET, MaterialType.EnumPartType.PLATE, MaterialType.EnumPartType.ORE));

    private MaterialType materialType;

    Material(MaterialType materialType) {
        this.materialType = materialType;
    }

    public MaterialType getMaterialType() {
        return this.materialType;
    }
}
