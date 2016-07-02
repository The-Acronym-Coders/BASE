package com.acronym.base.data;

import com.acronym.base.api.materials.Material;
import com.acronym.base.api.materials.Material.EnumPartType;
import com.acronym.base.api.registries.MaterialRegistry;
import com.acronym.base.util.ColourHelper;
import com.acronym.base.util.ResourceUtils;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.awt.*;

public enum Materials {
    IRON("Iron", Items.IRON_INGOT, EnumPartType.NUGGET, EnumPartType.DUST, EnumPartType.PLATE, EnumPartType.GEAR, EnumPartType.FLUID),
    GOLD("Gold", Items.GOLD_INGOT, EnumPartType.DUST, EnumPartType.PLATE, EnumPartType.GEAR, EnumPartType.FLUID),
    DIAMOND("Diamond", Items.DIAMOND, EnumPartType.DUST, EnumPartType.PLATE, EnumPartType.NUGGET, EnumPartType.GEAR),;

    private String name;
    private EnumPartType[] types;
    private Color color;

    Materials(String name, Color color, EnumPartType... types) {
        this.name = name;
        this.types = types;
        this.color = color;
    }

    Materials(String name, ItemStack stack, EnumPartType... types) {
        this(name, new Color(ColourHelper.getColour(ResourceUtils.getResourceFromItem(stack).getInputStream())), types);
    }

    Materials(String name, Item item, EnumPartType... types) {
        this(name, new Color(ColourHelper.getColour(ResourceUtils.getResourceFromItem(new ItemStack(item)).getInputStream())), types);
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
}
