package com.acronym.base.items;

import com.acronym.base.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

import static com.acronym.base.Base.generateTextures;
import static com.acronym.base.Reference.tab;

/**
 * Created by Jared on 6/27/2016.
 */
public class BaseItems {
    public static Map<String, Item> renderMap = new HashMap<String, Item>();


    public static void preInit() {

    }

    public static void init() {
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        for (Map.Entry<String, Item> ent : renderMap.entrySet()) {
            renderItem.getItemModelMesher().register(ent.getValue(), 0, new ModelResourceLocation(Reference.modid + ":" + ent.getKey(), "inventory"));
        }
    }


    public static void registerItem(Item item, String name, String key) {
        if (generateTextures)
            writeFile(key, key);
        item.setUnlocalizedName(key).setCreativeTab(tab);
        renderMap.put(key, item);

        GameRegistry.register(item, new ResourceLocation(Reference.modid + ":" + key));
    }

    public static void registerItemColour(Item item, String name, String key, int[] layers) {
        if (generateTextures)
            writeFile(key, key);
        item.setUnlocalizedName(key).setCreativeTab(tab);
        renderMap.put(key, item);
        //TODO reimplement colour map
//        colourMap.put(item, layers);
        GameRegistry.register(item, new ResourceLocation(Reference.modid + ":" + key));
    }

    public static void registerItemMeta(Item item, String name, String key) {
        if (generateTextures)
            writeFile(key, key);
        item.setCreativeTab(tab);
        renderMap.put(key, item);

        GameRegistry.register(item, new ResourceLocation(Reference.modid + ":" + key));
    }

    public static void registerItem(Item item, String name, String key, String texture) {
        if (generateTextures)
            writeFile(key, texture);
        item.setUnlocalizedName(key).setCreativeTab(tab);

        GameRegistry.register(item, new ResourceLocation(Reference.modid + ":" + key));
    }

    public static void writeFile(String key, String texture) {
        try {
            File f = new File(new File(System.getProperty("user.dir")).getParentFile(), "src/main/resources/assets/" + Reference.modid + "/models/item/" + key + ".json");
            if (!f.exists()) {
                f.createNewFile();
                File base = new File(System.getProperty("user.home") + "/getFluxed/baseItem.json");
                Scanner scan = new Scanner(base);
                List<String> content = new ArrayList<>();
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    if (line.contains("%modid%")) {
                        line = line.replace("%modid%", Reference.modid);
                    }
                    if (line.contains("%key%")) {
                        line = line.replace("%key%", key);
                    }
                    if (line.contains("%texture%")) {
                        line = line.replace("%texture%", texture);
                    }
                    content.add(line);
                }
                scan.close();
                FileWriter write = new FileWriter(f);
                for (String s : content) {
                    write.write(s + "\n");
                }
                write.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
