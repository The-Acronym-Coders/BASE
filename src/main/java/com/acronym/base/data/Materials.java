package com.acronym.base.data;

import com.acronym.base.api.materials.Material;
import com.acronym.base.api.materials.Material.EnumPartType;
import com.acronym.base.util.ColourHelper;
import com.acronym.base.util.ResourceUtils;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.awt.*;

public class Materials {

//    public static final Material copper = new Material("Copper", 0xFF44FF, Arrays.asList(new Resource[]{new Resource("ingotCopper"), new Resource("dustCopper"), new Resource("oreCopper")}));
//    public static final Material tin = new Material("Tin", 0x44FF44, Arrays.asList(new Resource[]{new Resource("ingotTin"), new Resource("dustTin"), new Resource("oreTin")}));

    public static final Material iron = new Material("Iron", new Color(ColourHelper.getColour(ResourceUtils.getResourceFromItem(new ItemStack(Items.IRON_INGOT)).getInputStream())), EnumPartType.values());
    public static final Material gold = new Material("Gold", new Color(ColourHelper.getColour(ResourceUtils.getResourceFromItem(new ItemStack(Items.GOLD_INGOT)).getInputStream())), EnumPartType.values());
    public static final Material diamond = new Material("Diamond", new Color(ColourHelper.getColour(ResourceUtils.getResourceFromItem(new ItemStack(Items.DIAMOND)).getInputStream())),EnumPartType.NUGGET,EnumPartType.PLATE);
    /* TEST */public static final Material copper = new Material("Copper", new Color(255, 128, 61),EnumPartType.values());
    
} 
