package com.teamacronymcoders.base.modules.materials;

import com.teamacronymcoders.base.api.materials.MaterialType;

import java.awt.*;

import static com.teamacronymcoders.base.api.materials.MaterialType.EnumPartType.*;

public enum BaseMaterial {
    WOOD(new MaterialType("Wood", new Color(0x9d804f), false, GEAR)),
    STONE(new MaterialType("Stone", new Color(0x514B4F), false, GEAR)),
    IRON(new MaterialType("Iron", new Color(0x878787), false, GEAR, DUST, NUGGET, PLATE)),
    GOLD(new MaterialType("Gold", new Color(0xb5a62c), false, DUST, GEAR)),
    DIAMOND(new MaterialType("Diamond", new Color(0x58ad9e), false, DUST, GEAR, NUGGET, PLATE)),
    COPPER(new MaterialType("Copper", new Color(0xF0A930), false, BLOCK, DUST, GEAR, INGOT, NUGGET, PLATE, ORE)),
    TIN(new MaterialType("Tin", new Color(0xA5CCCC), false, BLOCK, DUST, GEAR, INGOT, NUGGET, PLATE, ORE)),
    LEAD(new MaterialType("Lead", new Color(0x616D7E), false, BLOCK, DUST, GEAR, INGOT, NUGGET, PLATE, ORE)),
    SILVER(new MaterialType("Silver", new Color(0xDDDDDD), false, BLOCK, DUST, GEAR, INGOT, NUGGET, PLATE, ORE)),
    ELECTRUM(new MaterialType("Electrum", new Color(0xD4D32E), false, BLOCK, DUST, GEAR, INGOT, NUGGET, PLATE)),
    NICKEL(new MaterialType("Nickel", new Color(0xA2A497), false, BLOCK, DUST, GEAR, INGOT, NUGGET, PLATE, ORE)),
    ALUMINUM(new MaterialType("Aluminum", new Color(0xCBD2D8), false, BLOCK, DUST, GEAR, INGOT, NUGGET, PLATE, ORE));

    private MaterialType materialType;

    BaseMaterial(MaterialType materialType) {
        this.materialType = materialType;
    }

    public MaterialType getMaterialType() {
        return this.materialType;
    }
}
