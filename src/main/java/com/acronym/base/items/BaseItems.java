package com.acronym.base.items;

import com.acronym.base.api.materials.MaterialType;
import com.acronym.base.items.tools.ItemWrench;
import com.acronym.base.reference.Reference;
import com.acronym.base.util.FileHelper;
import com.acronym.base.util.Platform;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.acronym.base.reference.Reference.tab;

public class BaseItems {
    public static Map<String, Item> renderMap = new HashMap<>();
    public static Map<Item, int[]> colourMap = new HashMap<>();

    public static Item wrench = new ItemWrench();

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

    public static void init() {
    }

    public static void registerItem(Item item, String key) {
        if (Platform.generateBaseTextures()) writeFile(key, key);
        item.setUnlocalizedName(key).setCreativeTab(tab);
        renderMap.put(key, item);

        GameRegistry.register(item, new ResourceLocation(Reference.MODID + ":" + key));
    }

    public static void registerItem(Item item, String key, String modid, String texture) {
        if (Platform.generateBaseTextures()) writeFile(key, texture);
        item.setUnlocalizedName(key).setCreativeTab(tab);
        GameRegistry.register(item, new ResourceLocation(modid + ":" + key));
    }

    public static void registerItem(Item item, String modid, String key) {
        if (Platform.generateBaseTextures()) writeFile(key, key);
        item.setUnlocalizedName(key).setCreativeTab(tab);
        renderMap.put(key, item);

        GameRegistry.register(item, new ResourceLocation(modid + ":" + key));
    }

    public static void registerItemColour(Item item, String key, int[] layers) {
        if (Platform.generateBaseTextures()) writeFile(key, key);
        item.setUnlocalizedName(key).setCreativeTab(tab);
        renderMap.put(key, item);
        colourMap.put(item, layers);
        GameRegistry.register(item, new ResourceLocation(Reference.MODID + ":" + key));
    }

    public static void registerItemMeta(Item item, String name, String key) {
        if (Platform.generateBaseTextures()) writeFile(key, key);
        item.setCreativeTab(tab);
        renderMap.put(key, item);
        GameRegistry.register(item, new ResourceLocation(Reference.MODID + ":" + key));
    }

    public static void writeFile(String key, String texture) {
        File file = new File(new File(System.getProperty("user.dir")).getParentFile(), "src/main/resources/assets/" + Reference.MODID + "/models/item/" + key + ".json");

        if (!file.exists()) {
            try {
                file.createNewFile();
                FileHelper fileHelper = new FileHelper();
                fileHelper.writeFile(file, fileHelper.scanFile(Reference.MODID, key, texture, new File(System.getProperty("user.home") + "/getFluxed/baseItem.json")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
