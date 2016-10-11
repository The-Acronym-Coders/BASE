package com.acronym.base.blocks;

import com.acronym.base.api.materials.MaterialRegistry;
import com.acronym.base.api.materials.MaterialType;
import com.acronym.base.blocks.sets.wood.BlockStorage;
import com.acronym.base.items.ItemBlockOre;
import com.acronym.base.items.ItemBlockStorage;
import com.acronym.base.reference.Reference;
import com.acronym.base.util.FileHelper;
import com.acronym.base.util.Platform;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

import static com.acronym.base.reference.Reference.tab;

/**
 * Created by Jared on 7/1/2016
 */

/**
 * Remastered by EwyBoy on 7/5/2016
 */
public class BaseBlocks {

    public static Multimap<String, Block> renderMap = ArrayListMultimap.create();
    public static Map<MaterialType, Block> oreBlockMap = new LinkedHashMap<>();
    public static Map<MaterialType, Block> storageBlockMap = new LinkedHashMap<>();


    public static void preInit() {
        for (Map.Entry<Integer, MaterialType> entry : MaterialRegistry.getMaterials().entrySet()) {
            if (entry.getValue().isTypeSet(MaterialType.EnumPartType.ORE)) {
                if (!oreBlockMap.containsKey(entry.getValue())) {
                    BlockOre ore = new BlockOre(entry.getValue(), 3, 5, "pickaxe", 2);

                    oreBlockMap.put(entry.getValue(), registerBlock(ore, Reference.MODID, "ore_" + entry.getValue().getName().toLowerCase(), "%s Ore", "ore", null, tab, new ItemBlockOre(ore)));
                }
            }
        }
        for (Map.Entry<Integer, MaterialType> entry : MaterialRegistry.getMaterials().entrySet()) {
            if (entry.getValue().isTypeSet(MaterialType.EnumPartType.BLOCK)) {
                if (!storageBlockMap.containsKey(entry.getValue())) {
                    BlockStorage ore = new BlockStorage(entry.getValue(), 5, 10, "pickaxe", 2);
                    storageBlockMap.put(entry.getValue(), registerBlock(ore, Reference.MODID, "storage_" + entry.getValue().getName().toLowerCase(), "%s Block", "storage", null, tab, new ItemBlockStorage(ore)));
                }
            }
        }

    }

    public static void init() {

    }


    public static void postInit() {
    }

    public static Block registerBlock(Block block, String key, String name) {
        return registerBlock(block, Reference.MODID, key, name, key, null, tab, new ItemBlock(block));
    }

    public static Block registerBlock(Block block, String key, String name, String texture) {
        return registerBlock(block, Reference.MODID, key, name, texture, null, tab, new ItemBlock(block));
    }

    public static Block registerBlock(Block block, String key, String name, String texture, Class tile) {
        return registerBlock(block, Reference.MODID, key, name, texture, tile, tab, new ItemBlock(block));
    }

    public static Block registerBlock(Block block, String key, String name, Class tile) {
        return registerBlock(block, Reference.MODID, key, name, tile, tab);
    }

    public static Block registerBlock(Block block, String modid, String key, String name, Class tile, CreativeTabs tab) {
        return registerBlock(block, modid, key, name, key, tile, tab, new ItemBlock(block));
    }

    public static Block registerBlock(Block block, String modid, String key, String name, String texture, Class tile, CreativeTabs tab, ItemBlock itemBlock) {
        block.setUnlocalizedName(modid + ":" + key).setCreativeTab(tab);

        if (Platform.generateBaseTextures()) {
            writeFile(modid, key, texture);
            writeLangFile(key, modid, name);
        }

        renderMap.put(texture, block);
        Block b = GameRegistry.register(block, new ResourceLocation(modid + ":" + key));
        GameRegistry.register(itemBlock, new ResourceLocation(modid + ":" + key));

        if (tile != null) {
            GameRegistry.registerTileEntity(tile, key);
        }
        return b;
    }

    private static void writeLangFile(String key, String modid, String name) {
        try {
            File lang = new File(new File(System.getProperty("user.dir")).getParentFile(), "src/main/resources/assets/" + modid + "/lang/en_US.lang");

            if (!lang.exists()) {
                lang.createNewFile();
            }
            Scanner scan = new Scanner(lang);
            List<String> content = new ArrayList<>();
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                content.add(line);
            }
            scan.close();
            if (!content.contains((String.format("tile.%s.name=%s", key, name))))
                content.add(String.format("tile.%s.name=%s", key, name));
            FileWriter write = new FileWriter(lang);
            for (String s : content) {
                write.write(s + "\n");
            }
            write.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeFile(String modid, String key, String texture) {
        try {
            File baseBlockState = new File(new File(System.getProperty("user.dir")).getParentFile(), "src/main/resources/assets/" + modid + "/blockstates/" + key + ".json");
            File baseBlockModel = new File(new File(System.getProperty("user.dir")).getParentFile(), "src/main/resources/assets/" + modid + "/models/block/" + key + ".json");
            File baseItem = new File(new File(System.getProperty("user.dir")).getParentFile(), "src/main/resources/assets/" + modid + "/models/item/" + key + ".json");

            FileHelper fileHelper = new FileHelper();

            if (!baseBlockState.exists()) {
                baseBlockState.createNewFile();
                fileHelper.writeFile(baseBlockState, fileHelper.scanFile(modid, key, texture, new File(System.getProperty("user.home") + "/getFluxed/baseBlockState.json")));
            }

            if (!baseBlockModel.exists()) {
                baseBlockModel.createNewFile();
                fileHelper.writeFile(baseBlockModel, fileHelper.scanFile(modid, key, texture, new File(System.getProperty("user.home") + "/getFluxed/baseBlockModel.json")));

            }

            if (!baseItem.exists()) {
                baseItem.createNewFile();
                fileHelper.writeFile(baseItem, fileHelper.scanFile(modid, key, texture, new File(System.getProperty("user.home") + "/getFluxed/baseBlockItem.json")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
