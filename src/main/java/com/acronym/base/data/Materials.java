package com.acronym.base.data;

import com.acronym.base.api.materials.MaterialType;
import com.acronym.base.api.materials.MaterialType.EnumPartType;

import java.awt.*;

public class Materials {

    public static final MaterialType WOOD = new MaterialType("Wood", new Color(0x9d804f), false, EnumPartType.GEAR);
    public static final MaterialType STONE = new MaterialType("Stone", new Color(0x514B4F), false, EnumPartType.GEAR);
    public static final MaterialType IRON = new MaterialType("Iron", new Color(0x878787), false, EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.NUGGET, EnumPartType.PLATE);
    public static final MaterialType GOLD = new MaterialType("Gold", new Color(0xb5a62c), false, EnumPartType.DUST, EnumPartType.GEAR);
    public static final MaterialType DIAMOND = new MaterialType("Diamond", new Color(0x58ad9e), false, EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.NUGGET, EnumPartType.PLATE);

    public static final MaterialType COPPER = new MaterialType("Copper", new Color(0xF0A930), false, EnumPartType.BLOCK, EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.INGOT, EnumPartType.NUGGET, EnumPartType.PLATE, EnumPartType.ORE);
    public static final MaterialType TIN = new MaterialType("Tin", new Color(0xA5CCCC), false, EnumPartType.BLOCK, EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.INGOT, EnumPartType.NUGGET, EnumPartType.PLATE, EnumPartType.ORE);
    public static final MaterialType LEAD = new MaterialType("Lead", new Color(0x616D7E), false, EnumPartType.BLOCK, EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.INGOT, EnumPartType.NUGGET, EnumPartType.PLATE, EnumPartType.ORE);
    public static final MaterialType SILVER = new MaterialType("Silver", new Color(0xDDDDDD), false, EnumPartType.BLOCK, EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.INGOT, EnumPartType.NUGGET, EnumPartType.PLATE, EnumPartType.ORE);
    public static final MaterialType ELECTRUM = new MaterialType("Electrum", new Color(0xD4D32E), false, EnumPartType.BLOCK, EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.INGOT, EnumPartType.NUGGET, EnumPartType.PLATE);
    public static final MaterialType NICKEL = new MaterialType("Nickel", new Color(0xA2A497), false, EnumPartType.BLOCK, EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.INGOT, EnumPartType.NUGGET, EnumPartType.PLATE, EnumPartType.ORE);
    public static final MaterialType ALUMINUM= new MaterialType("Aluminum", new Color(0xCBD2D8), false, EnumPartType.BLOCK, EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.INGOT, EnumPartType.NUGGET, EnumPartType.PLATE, EnumPartType.ORE);

}
