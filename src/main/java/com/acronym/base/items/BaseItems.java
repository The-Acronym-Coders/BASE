package com.acronym.base.items;

import com.acronym.base.api.materials.Material;
import com.acronym.base.api.materials.MaterialRegistry;
import com.acronym.base.items.tools.ItemWrench;
import com.acronym.base.reference.Reference;
import com.acronym.base.util.IMetaItem;
import com.acronym.base.util.Platform;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

import static com.acronym.base.reference.Reference.tab;

public class BaseItems {
    public static Map<String, Item> renderMap = new HashMap<String, Item>();
    public static Map<Item, int[]> colourMap = new HashMap<>();

    public static Item wrench = new ItemWrench();

    public static final ItemPart GEAR   = new ItemPart(Material.EnumPartType.GEAR);
    public static final ItemPart DUST   = new ItemPart(Material.EnumPartType.DUST);
    public static final ItemPart PLATE  = new ItemPart(Material.EnumPartType.PLATE);
    public static final ItemPart NUGGET = new ItemPart(Material.EnumPartType.NUGGET);
    public static final ItemPart INGOT  = new ItemPart(Material.EnumPartType.INGOT);


    public static void preInit() throws Exception {
        registerItemColour(GEAR,   "gear",   "gear",   new int[]{0});
        registerItemColour(DUST,   "dust",   "dust",   new int[]{0});
        registerItemColour(PLATE,  "plate",  "plate",  new int[]{0});
        registerItemColour(NUGGET, "nugget", "nugget", new int[]{0});
        registerItemColour(INGOT,  "ingot",  "ingot",  new int[]{0});
    }

    public static void init() {
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        for (Map.Entry<String, Item> ent : renderMap.entrySet()) {
            if (ent.getValue() instanceof IMetaItem) {
                IMetaItem metaItem = (IMetaItem) ent.getValue();
                for (int i : metaItem.getMetaData()) {
                    renderItem.getItemModelMesher().register(ent.getValue(), i, new ModelResourceLocation(Reference.MODID + ":" + ent.getKey(), "inventory"));
                }
            } else
                renderItem.getItemModelMesher().register(ent.getValue(), 0, new ModelResourceLocation(Reference.MODID + ":" + ent.getKey(), "inventory"));
        }
        for (Map.Entry<Item, int[]> ent : colourMap.entrySet()) {
            //TODO Convert to Lambda
            Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor() {
                @Override
                public int getColorFromItemstack(ItemStack stack, int tintIndex) {
                    Material mat = MaterialRegistry.getFromID(stack.getItemDamage());
                    if (mat == null) {
                        return 0xFFFFFF;
                    }
                    for (int i : ent.getValue()) {
                        if (tintIndex == i) {
                            return mat.getColour().getRGB();
                        }
                    }
                    return 0xFFFFFF;
                }

            }, ent.getKey());
        }
    }


    public static void registerItem(Item item, String name, String key) throws Exception {
        if (Platform.generateBaseTextures())
            writeFile(key, key);
        item.setUnlocalizedName(key).setCreativeTab(tab);
        renderMap.put(key, item);

        GameRegistry.register(item, new ResourceLocation(Reference.MODID + ":" + key));
    }

    public static void registerItemColour(Item item, String name, String key, int[] layers) throws Exception {
        if (Platform.generateBaseTextures())
            writeFile(key, key);
        item.setUnlocalizedName(key).setCreativeTab(tab);
        renderMap.put(key, item);
        colourMap.put(item, layers);
        GameRegistry.register(item, new ResourceLocation(Reference.MODID + ":" + key));
    }

    public static void registerItemMeta(Item item, String name, String key) throws Exception {
        if (Platform.generateBaseTextures())
            writeFile(key, key);
        item.setCreativeTab(tab);
        renderMap.put(key, item);
        GameRegistry.register(item, new ResourceLocation(Reference.MODID + ":" + key));
    }

    public static void registerItem(Item item, String name, String key, String texture) throws Exception {
        if (Platform.generateBaseTextures())
            writeFile(key, texture);
        item.setUnlocalizedName(key).setCreativeTab(tab);

        GameRegistry.register(item, new ResourceLocation(Reference.MODID + ":" + key));
    }

    public static void writeFile(String key, String texture) throws Exception {
        File file = new File(new File(System.getProperty("user.dir")).getParentFile(), "src/main/resources/assets/" + Reference.MODID + "/models/item/" + key + ".json");
        if (!file.exists()) {
            file.createNewFile();

            //TODO Use file scanner from BaseBlock or make the file scanner a common util class, so this can call it.
            File base = new File(System.getProperty("user.home") + "/getFluxed/baseItem.json");
            Scanner scan = new Scanner(base);
            List<String> content = new ArrayList<>();
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (line.contains("%modid%")) {
                    line = line.replace("%modid%", Reference.MODID);
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

            //TODO Use file writer from BaseBlock or make the file writer a common util class, so this can call it.
            FileWriter write = new FileWriter(file);
            for (String s : content) {
                write.write(s + "\n");
            }
            write.close();
        }
    }
}
