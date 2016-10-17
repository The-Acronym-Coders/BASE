package com.teamacronymcoders.base.data;

import com.teamacronymcoders.base.api.materials.MaterialType;

import java.awt.*;

public class Materials {

    public static final MaterialType
			WOOD = new MaterialType("Wood", new Color(0x9d804f), false, MaterialType.EnumPartType.GEAR);
    public static final MaterialType STONE = new MaterialType("Stone", new Color(0x514B4F), false, MaterialType.EnumPartType.GEAR);
    public static final MaterialType IRON = new MaterialType("Iron", new Color(0x878787), false, MaterialType.EnumPartType.DUST, MaterialType.EnumPartType.NUGGET, MaterialType.EnumPartType.PLATE);
    public static final MaterialType GOLD = new MaterialType("Gold", new Color(0xb5a62c), false, MaterialType.EnumPartType.DUST, MaterialType.EnumPartType.GEAR);
    public static final MaterialType DIAMOND = new MaterialType("Diamond", new Color(0x58ad9e), false, MaterialType.EnumPartType.DUST, MaterialType.EnumPartType.GEAR, MaterialType.EnumPartType.NUGGET, MaterialType.EnumPartType.PLATE);

    public static final MaterialType COPPER = new MaterialType("Copper", new Color(0xF0A930), false, MaterialType.EnumPartType.BLOCK, MaterialType.EnumPartType.DUST, MaterialType.EnumPartType.GEAR, MaterialType.EnumPartType.INGOT, MaterialType.EnumPartType.NUGGET, MaterialType.EnumPartType.PLATE, MaterialType.EnumPartType.ORE);
    public static final MaterialType TIN = new MaterialType("Tin", new Color(0xA5CCCC), false, MaterialType.EnumPartType.BLOCK, MaterialType.EnumPartType.DUST, MaterialType.EnumPartType.GEAR, MaterialType.EnumPartType.INGOT, MaterialType.EnumPartType.NUGGET, MaterialType.EnumPartType.PLATE, MaterialType.EnumPartType.ORE);
    public static final MaterialType LEAD = new MaterialType("Lead", new Color(0x616D7E), false, MaterialType.EnumPartType.BLOCK, MaterialType.EnumPartType.DUST, MaterialType.EnumPartType.GEAR, MaterialType.EnumPartType.INGOT, MaterialType.EnumPartType.NUGGET, MaterialType.EnumPartType.PLATE, MaterialType.EnumPartType.ORE);
    public static final MaterialType SILVER = new MaterialType("Silver", new Color(0xDDDDDD), false, MaterialType.EnumPartType.BLOCK, MaterialType.EnumPartType.DUST, MaterialType.EnumPartType.GEAR, MaterialType.EnumPartType.INGOT, MaterialType.EnumPartType.NUGGET, MaterialType.EnumPartType.PLATE, MaterialType.EnumPartType.ORE);
    public static final MaterialType ELECTRUM = new MaterialType("Electrum", new Color(0xD4D32E), false, MaterialType.EnumPartType.BLOCK, MaterialType.EnumPartType.DUST, MaterialType.EnumPartType.GEAR, MaterialType.EnumPartType.INGOT, MaterialType.EnumPartType.NUGGET, MaterialType.EnumPartType.PLATE);
    public static final MaterialType NICKEL = new MaterialType("Nickel", new Color(0xA2A497), false, MaterialType.EnumPartType.BLOCK, MaterialType.EnumPartType.DUST, MaterialType.EnumPartType.GEAR, MaterialType.EnumPartType.INGOT, MaterialType.EnumPartType.NUGGET, MaterialType.EnumPartType.PLATE, MaterialType.EnumPartType.ORE);
    public static final MaterialType ALUMINUM= new MaterialType("Aluminum", new Color(0xCBD2D8), false, MaterialType.EnumPartType.BLOCK, MaterialType.EnumPartType.DUST, MaterialType.EnumPartType.GEAR, MaterialType.EnumPartType.INGOT, MaterialType.EnumPartType.NUGGET, MaterialType.EnumPartType.PLATE, MaterialType.EnumPartType.ORE);

}
