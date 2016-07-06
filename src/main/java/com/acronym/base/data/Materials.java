package com.acronym.base.data;

import com.acronym.base.api.materials.Material;
import com.acronym.base.api.materials.Material.EnumPartType;

import java.awt.*;

public class Materials {

    public static final Material IRON = new Material("Iron", new Color(0x878787), EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.NUGGET, EnumPartType.PLATE);
    public static final Material GOLD = new Material("Gold", new Color(0xb5a62c), EnumPartType.DUST, EnumPartType.GEAR);
    public static final Material DIAMOND = new Material("Diamond", new Color(0x58ad9e), EnumPartType.GEAR, EnumPartType.NUGGET, EnumPartType.PLATE);
    public static final Material WOOD = new Material("Wood", new Color(0x9d804f), EnumPartType.GEAR);
    public static final Material STONE = new Material("Stone", new Color(0x514B4F), EnumPartType.DUST, EnumPartType.GEAR);
    public static final Material COPPER = new Material("Copper", new Color(0x8e5d47), EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.INGOT, EnumPartType.NUGGET, EnumPartType.PLATE, EnumPartType.ORE);
    public static final Material SILVER = new Material("Silver", new Color(0x8f9a9f), EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.INGOT, EnumPartType.NUGGET, EnumPartType.PLATE, EnumPartType.ORE);
    public static final Material TIN = new Material("Tin", new Color(0xa3a3a3), EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.INGOT, EnumPartType.NUGGET, EnumPartType.PLATE, EnumPartType.ORE);
    public static final Material LEAD = new Material("Lead", new Color(0x54575c), EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.INGOT, EnumPartType.NUGGET, EnumPartType.PLATE, EnumPartType.ORE);
    public static final Material ELECTRUM= new Material("Lead", new Color(0xD4D32E), EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.INGOT, EnumPartType.NUGGET, EnumPartType.PLATE, EnumPartType.ORE);

}
