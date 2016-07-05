package com.acronym.base.blocks;

import com.acronym.base.api.materials.Material;
import com.acronym.base.api.materials.MaterialRegistry;
import com.acronym.base.reference.Reference;
import com.acronym.base.util.Platform;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.commons.lang3.tuple.MutablePair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static com.acronym.base.reference.Reference.tab;

/**
 * Created by Jared on 7/1/2016.
 */
public class BaseBlocks {


    public static Map<String, Block> renderMap = new HashMap<>();

    public static BlockTest test = new BlockTest();

    public static Map<Material, Block> oreBlockMap = new LinkedHashMap<>();

    public static void preInit() {
        for (Map.Entry<MutablePair<String, Integer>, Material> entry : MaterialRegistry.getMaterials().entrySet()) {
            if (entry.getValue().isTypeSet(Material.EnumPartType.ORE)) {
                BlockOre ore = new BlockOre(entry.getValue());
                registerBlock(ore, "ore_" + entry.getValue().getName().toLowerCase(), "%s Ore", "ore", null, tab);
                oreBlockMap.put(entry.getValue(), ore);
            }
        }
    }


    public static void init() {
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        for (Map.Entry<String, Block> ent : renderMap.entrySet()) {
            renderItem.getItemModelMesher().register(Item.getItemFromBlock(ent.getValue()), 0, new ModelResourceLocation(Reference.MODID + ":" + ent.getKey(), "inventory"));
        }
        for (Map.Entry<Material, Block> entry : oreBlockMap.entrySet()) {
            Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor() {
                @Override
                public int getColorFromItemstack(ItemStack stack, int tintIndex) {
                    return entry.getKey().getColour().getRGB();
                }

            }, entry.getValue());
        }
    }

    public static void postInit() {
    }


    private static void registerBlock(Block block, String key, String name) {
        registerBlock(block, key, name, key, null, tab);
    }

    private static void registerBlock(Block block, String key, String name, String texture) {
        registerBlock(block, key, name, texture, null, tab);
    }

    private static void registerBlock(Block block, String key, String name, String texture, Class tile) {
        registerBlock(block, key, name, texture, tile, tab);
    }

    private static void registerBlock(Block block, String key, String name, Class tile) {
        registerBlock(block, key, name, key, tile, tab);
    }

    private static void registerBlock(Block block, String key, String name, Class tile, CreativeTabs tab) {
        registerBlock(block, key, name, key, tile, tab);
    }

    private static void registerBlock(Block block, String key, String name, String texture, Class tile, CreativeTabs tab) {
        block.setUnlocalizedName(key).setCreativeTab(tab);
        if (Platform.generateBaseTextures()) {
            writeFile(key, texture);
            writeLangFile(key, name);
        }
        renderMap.put(key, block);
        GameRegistry.register(block, new ResourceLocation(Reference.MODID + ":" + key));
        GameRegistry.register(new ItemBlock(block), new ResourceLocation(Reference.MODID + ":" + key));
        if (tile != null) {
            GameRegistry.registerTileEntity(tile, key);
        }
    }

    public static void writeLangFile(String key, String name) {
        {
            try {
                File lang = new File(new File(System.getProperty("user.dir")).getParentFile(), "src/main/resources/assets/" + Reference.MODID + "/lang/en_US.lang");

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
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeFile(String key, String texture) {
        try {
            File baseBlockState = new File(new File(System.getProperty("user.dir")).getParentFile(), "src/main/resources/assets/" + Reference.MODID + "/blockstates/" + key + ".json");
            File baseBlockModel = new File(new File(System.getProperty("user.dir")).getParentFile(), "src/main/resources/assets/" + Reference.MODID + "/models/block/" + key + ".json");
            File baseItem = new File(new File(System.getProperty("user.dir")).getParentFile(), "src/main/resources/assets/" + Reference.MODID + "/models/item/" + key + ".json");

            if (!baseBlockState.exists()) {
                baseBlockState.createNewFile();
                File base = new File(System.getProperty("user.home") + "/getFluxed/baseBlockState.json");
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
                FileWriter write = new FileWriter(baseBlockState);
                for (String s : content) {
                    write.write(s + "\n");
                }
                write.close();
            }
            if (!baseBlockModel.exists()) {
                baseBlockModel.createNewFile();
                File base = new File(System.getProperty("user.home") + "/getFluxed/baseBlockModel.json");
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
                FileWriter write = new FileWriter(baseBlockModel);
                for (String s : content) {
                    write.write(s + "\n");
                }
                write.close();
            }

            if (!baseItem.exists()) {
                baseItem.createNewFile();
                File base = new File(System.getProperty("user.home") + "/getFluxed/baseBlockItem.json");
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
                FileWriter write = new FileWriter(baseItem);
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
