package com.acronym.base.data;

import com.acronym.base.api.materials.Material;
import com.acronym.base.api.materials.Material.EnumPartType;
import com.acronym.base.api.registries.MaterialRegistry;
import com.acronym.base.util.ColourHelper;
import com.acronym.base.util.ResourceUtils;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.awt.*;

public enum Materials {
    IRON("Iron", Items.IRON_INGOT, "Iron", EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.NUGGET, EnumPartType.PLATE),
    GOLD("Gold", Items.GOLD_INGOT, "Gold", EnumPartType.DUST, EnumPartType.GEAR),
    DIAMOND("Diamond", Items.DIAMOND, "Diamond", EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.NUGGET, EnumPartType.PLATE),
    WOOD("Wooden", Blocks.PLANKS, "Wood", EnumPartType.GEAR),
    COPPER("Copper", new Color(0x8e5d47), "Copper", EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.INGOT, EnumPartType.NUGGET, EnumPartType.PLATE),
    SILVER("Silver", new Color(0x8f9a9f), "Silver", EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.INGOT, EnumPartType.NUGGET, EnumPartType.PLATE),
    TIN("Tin", new Color(0xa3a3a3), "Tin", EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.INGOT, EnumPartType.NUGGET, EnumPartType.PLATE),
    LEAD("Lead", new Color(0x54575c), "Lead", EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.INGOT, EnumPartType.NUGGET, EnumPartType.PLATE),
    PLATINUM("Platinum", new Color(0x919ea5), "Platinum", EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.INGOT, EnumPartType.NUGGET, EnumPartType.PLATE),
    BRONZE("Bronze", new Color(0xae7748), "Bronze", EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.INGOT, EnumPartType.NUGGET, EnumPartType.PLATE),
    STEEL("Steel", new Color(0x8c8c8c), "Steel", EnumPartType.DUST, EnumPartType.GEAR, EnumPartType.INGOT, EnumPartType.NUGGET, EnumPartType.PLATE);

    private String name;
    private String oreDictSuffix;
    private EnumPartType[] types;
    private Color color;

    Materials(String name, Color color, String oreDictSuffix, EnumPartType... types) {
        this.name = name;
        this.types = types;
        this.color = color;
        this.oreDictSuffix = oreDictSuffix;
    }

    Materials(String name, ItemStack stack, String oreDictSuffix, EnumPartType... types) {
        this(name, new Color(ColourHelper.getColour(ResourceUtils.getResourceFromItem(stack).getInputStream())), oreDictSuffix, types);
    }

    Materials(String name, Item item, String oreDictSuffix, EnumPartType... types) {
        this(name, new Color(ColourHelper.getColour(ResourceUtils.getResourceFromItem(new ItemStack(item)).getInputStream())), oreDictSuffix, types);
    }

    Materials(String name, Block block, String oreDictSuffix, EnumPartType... types) {
        this(name, new Color(ColourHelper.getColour(ResourceUtils.getResourceFromItem(new ItemStack(block)).getInputStream())), oreDictSuffix, types);
    }

    public static final Material iron = new Material("Iron", new Color(ColourHelper.getColour(ResourceUtils.getResourceFromItem(new ItemStack(Items.IRON_INGOT)).getInputStream())), EnumPartType.values());


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
        return oreDictSuffix;
    }
}
