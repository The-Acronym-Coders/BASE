package com.acronym.base.data;

import com.acronym.base.api.materials.MaterialType;
import com.acronym.base.api.materials.MaterialType.EnumPartType;

import java.awt.*;

public class Materials {

    public static final MaterialType IRON = new MaterialType("Iron", new Color(0x878787), false, EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.NUGGET, EnumPartType.PLATE);
    public static final MaterialType GOLD = new MaterialType("Gold", new Color(0xb5a62c), false, EnumPartType.DUST, EnumPartType.GEAR);
    public static final MaterialType DIAMOND = new MaterialType("Diamond", new Color(0x58ad9e), false, EnumPartType.GEAR, EnumPartType.NUGGET, EnumPartType.PLATE);
    public static final MaterialType WOOD = new MaterialType("Wood", new Color(0x9d804f), false, EnumPartType.GEAR);
    public static final MaterialType STONE = new MaterialType("Stone", new Color(0x514B4F), false, EnumPartType.DUST, EnumPartType.GEAR);
    public static final MaterialType COPPER = new MaterialType("Copper", new Color(0x8e5d47), false, EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.INGOT, EnumPartType.NUGGET, EnumPartType.PLATE, EnumPartType.ORE);
    public static final MaterialType SILVER = new MaterialType("Silver", new Color(0x8f9a9f), false, EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.INGOT, EnumPartType.NUGGET, EnumPartType.PLATE, EnumPartType.ORE);
    public static final MaterialType TIN = new MaterialType("Tin", new Color(0xa3a3a3), false, EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.INGOT, EnumPartType.NUGGET, EnumPartType.PLATE, EnumPartType.ORE);
    public static final MaterialType LEAD = new MaterialType("Lead", new Color(0x616D7E), false, EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.INGOT, EnumPartType.NUGGET, EnumPartType.PLATE, EnumPartType.ORE);
    public static final MaterialType ELECTRUM = new MaterialType("Electrum", new Color(0xD4D32E), false, EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.INGOT, EnumPartType.NUGGET, EnumPartType.PLATE);
}
