package com.teamacronymcoders.base.items;

import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.api.materials.MaterialType;
import com.teamacronymcoders.base.reference.Reference;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.HashMap;
import java.util.Map;

public class BaseItems {
    public static Map<String, Item> renderMap = new HashMap<>();
    public static Map<Item, int[]> colourMap = new HashMap<>();

    public static final ItemPart GEAR = new ItemPart(MaterialType.EnumPartType.GEAR);
    public static final ItemPart DUST = new ItemPart(MaterialType.EnumPartType.DUST);
    public static final ItemPart PLATE = new ItemPart(MaterialType.EnumPartType.PLATE);
    public static final ItemPart NUGGET = new ItemPart(MaterialType.EnumPartType.NUGGET);
    public static final ItemPart INGOT = new ItemPart(MaterialType.EnumPartType.INGOT);


    public static void preInit() {
        registerItemColour(GEAR, "gear", new int[]{0});
        registerItemColour(DUST, "dust", new int[]{0});
        registerItemColour(PLATE, "plate", new int[]{0});
        registerItemColour(NUGGET, "nugget", new int[]{0});
        registerItemColour(INGOT, "ingot", new int[]{0});
    }

    public static void registerItemColour(Item item, String key, int[] layers) {
        item.setUnlocalizedName(key).setCreativeTab(Base.instance.getCreativeTab());
        renderMap.put(key, item);
        colourMap.put(item, layers);
        GameRegistry.register(item, new ResourceLocation(Reference.MODID + ":" + key));
    }
}
