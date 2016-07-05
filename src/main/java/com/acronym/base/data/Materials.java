package com.acronym.base.data;

import com.acronym.base.api.materials.Material;
import com.acronym.base.api.materials.Material.EnumPartType;
import com.acronym.base.api.materials.MaterialRegistry;
import com.acronym.base.util.ColourHelper;
import com.acronym.base.util.ResourceUtils;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;

public enum Materials {
    IRON(new Color(0x878787), EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.NUGGET, EnumPartType.PLATE),
    GOLD(new Color(0xb5a62c), EnumPartType.DUST, EnumPartType.GEAR),
    DIAMOND(new Color(0x58ad9e), EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.NUGGET, EnumPartType.PLATE),
    WOOD(new Color(0x9d804f), EnumPartType.GEAR),
    STONE(new Color(0x514B4F), EnumPartType.DUST, EnumPartType.GEAR),
    COPPER(new Color(0x8e5d47), EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.INGOT, EnumPartType.NUGGET, EnumPartType.PLATE, EnumPartType.ORE),
    SILVER(new Color(0x8f9a9f), EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.INGOT, EnumPartType.NUGGET, EnumPartType.PLATE, EnumPartType.ORE),
    TIN(new Color(0xa3a3a3), EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.INGOT, EnumPartType.NUGGET, EnumPartType.PLATE, EnumPartType.ORE),
    LEAD(new Color(0x54575c), EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.INGOT, EnumPartType.NUGGET, EnumPartType.PLATE, EnumPartType.ORE);

    private String name;
    private EnumPartType[] types;
    private Color color;

    Materials(Color color, EnumPartType... types) {
        this.name = StringUtils.capitalize(name().toLowerCase());
        this.types = types;
        this.color = color;
    }

    Materials(ItemStack stack, EnumPartType... types) {
        this(new Color(ColourHelper.getColour(ResourceUtils.getResourceFromItem(stack).getInputStream())), types);
    }

    Materials(Item item, String oreDictSuffix, EnumPartType... types) {
        this(new Color(ColourHelper.getColour(ResourceUtils.getResourceFromItem(new ItemStack(item)).getInputStream())), types);
    }

    Materials(String name, Block block, String oreDictSuffix, EnumPartType... types) {
        this(new Color(ColourHelper.getColour(ResourceUtils.getResourceFromItem(new ItemStack(block)).getInputStream())), types);
    }

    public Material getMaterial() {
        if (MaterialRegistry.isRegistered(this.name)) {
            return MaterialRegistry.getFromName(this.name);
        }
        return new Material(this.name, this.color, this.types);
    }

    public String getName() {
        return name;
    }

    public EnumPartType[] getTypes() {
        return types;
    }

    public Color getColor() {
        return color;
    }

    public String getOreDictSuffix() {
        return name;
    }
}
