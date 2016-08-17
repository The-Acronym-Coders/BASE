package com.acronym.base.blocks;

import com.acronym.base.api.materials.MaterialRegistry;
import com.acronym.base.api.materials.MaterialType;
import com.acronym.base.reference.Reference;
import com.acronym.base.util.FileHelper;
import com.acronym.base.util.Platform;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.commons.lang3.tuple.MutablePair;

import javax.annotation.Nullable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static com.acronym.base.reference.Reference.tab;

/**
 * Created by Jared on 7/1/2016
 */

/**
 * Remastered by EwyBoy on 7/5/2016
 */
public class BaseBlocks {

    public static Map<String, Block> renderMap = new HashMap<>();
    public static Map<MaterialType, Block> oreBlockMap = new LinkedHashMap<>();

    public static void preInit() throws Exception {
        for (Map.Entry<MutablePair<String, Integer>, MaterialType> entry : MaterialRegistry.getMaterials().entrySet()) {
            if (entry.getValue().isTypeSet(MaterialType.EnumPartType.ORE)) {
                BlockOre ore = new BlockOre(entry.getValue());
                registerBlock(ore, Reference.MODID, "ore_" + entry.getValue().getName().toLowerCase(), "%s Ore", "ore", null, tab);
                oreBlockMap.put(entry.getValue(), ore);
            }
        }
    }

    public static void init() {
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();

        for (Map.Entry<String, Block> ent : renderMap.entrySet()) {
            renderItem.getItemModelMesher().register(Item.getItemFromBlock(ent.getValue()), 0, new ModelResourceLocation(Reference.MODID + ":" + ent.getKey(), "inventory"));
        }

        BlockColors bc = Minecraft.getMinecraft().getBlockColors();
        ItemColors ic = Minecraft.getMinecraft().getItemColors();
        for (Map.Entry<MaterialType, Block> entry : oreBlockMap.entrySet()) {
            bc.registerBlockColorHandler(new IBlockColor() {
                @Override
                public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex) {
                    return entry.getKey().getColour().getRGB();
                }
            }, entry.getValue());
            ic.registerItemColorHandler(new IItemColor() {
                @Override
                public int getColorFromItemstack(ItemStack stack, int tintIndex) {
                    return entry.getKey().getColour().getRGB();
                }

            }, entry.getValue());
        }
    }


    public static void postInit() {
    }

    private static void registerBlock(Block block, String key, String name) throws Exception {
        registerBlock(block, Reference.MODID, key, name, key, null, tab);
    }

    private static void registerBlock(Block block, String key, String name, String texture) throws Exception {
        registerBlock(block, Reference.MODID, key, name, texture, null, tab);
    }

    private static void registerBlock(Block block, String key, String name, String texture, Class tile) throws Exception {
        registerBlock(block, Reference.MODID, key, name, texture, tile, tab);
    }

    private static void registerBlock(Block block, String key, String name, Class tile) throws Exception {
        registerBlock(block, Reference.MODID, key, name, tile, tab);
    }

    private static void registerBlock(Block block, String modid, String key, String name, Class tile, CreativeTabs tab) throws Exception {
        registerBlock(block, modid, key, name, key, tile, tab);
    }

    private static void registerBlock(Block block, String modid, String key, String name, String texture, Class tile, CreativeTabs tab) throws Exception {
        block.setUnlocalizedName(key).setCreativeTab(tab);

        if (Platform.generateBaseTextures()) {
            writeFile(modid, key, texture);
            writeLangFile(key, name);
        }

        renderMap.put(key, block);
        GameRegistry.register(block, new ResourceLocation(Reference.MODID + ":" + key));
        GameRegistry.register(new ItemBlock(block), new ResourceLocation(Reference.MODID + ":" + key));

        if (tile != null) {
            GameRegistry.registerTileEntity(tile, key);
        }
    }

    private static void writeLangFile(String key, String name) throws IOException {
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
    }

    private static void writeFile(String modid, String key, String texture) throws Exception {
        File baseBlockState = new File(new File(System.getProperty("user.dir")).getParentFile(), "src/main/resources/assets/" + Reference.MODID + "/blockstates/" + key + ".json");
        File baseBlockModel = new File(new File(System.getProperty("user.dir")).getParentFile(), "src/main/resources/assets/" + Reference.MODID + "/models/block/" + key + ".json");
        File baseItem = new File(new File(System.getProperty("user.dir")).getParentFile(), "src/main/resources/assets/" + Reference.MODID + "/models/item/" + key + ".json");

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
    }
}
