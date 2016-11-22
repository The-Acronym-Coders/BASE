package com.teamacronymcoders.base.blocks;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.api.materials.MaterialRegistry;
import com.teamacronymcoders.base.api.materials.MaterialType;
import com.teamacronymcoders.base.blocks.sets.wood.BlockStorage;
import com.teamacronymcoders.base.items.ItemBlockOre;
import com.teamacronymcoders.base.items.ItemBlockStorage;
import com.teamacronymcoders.base.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.LinkedHashMap;
import java.util.Map;

public class BaseBlocks {

    public static Multimap<String, Block> renderMap = ArrayListMultimap.create();
    public static Map<MaterialType, Block> oreBlockMap = new LinkedHashMap<>();
    public static Map<MaterialType, Block> storageBlockMap = new LinkedHashMap<>();


    public static void preInit() {
        for (Map.Entry<Integer, MaterialType> entry : MaterialRegistry.getMaterials().entrySet()) {
            if (entry.getValue().isTypeSet(MaterialType.EnumPartType.ORE)) {
                if (!oreBlockMap.containsKey(entry.getValue())) {
                    BlockOre ore = new BlockOre(entry.getValue(), 3, 5, "pickaxe", 2);

                    oreBlockMap.put(entry.getValue(), registerBlock(ore, Reference.MODID, "ore_" + entry.getValue().getName().toLowerCase(), "%s Ore", "ore", null, Base.instance.getCreativeTab(), new ItemBlockOre(ore)));
                }
            }
        }
        for (Map.Entry<Integer, MaterialType> entry : MaterialRegistry.getMaterials().entrySet()) {
            if (entry.getValue().isTypeSet(MaterialType.EnumPartType.BLOCK)) {
                if (!storageBlockMap.containsKey(entry.getValue())) {
                    BlockStorage ore = new BlockStorage(entry.getValue(), 5, 10, "pickaxe", 2);
                    storageBlockMap.put(entry.getValue(), registerBlock(ore, Reference.MODID, "storage_" + entry.getValue().getName().toLowerCase(), "%s Block", "storage", null, Base.instance.getCreativeTab(), new ItemBlockStorage(ore)));
                }
            }
        }

    }

    public static void init() {

    }


    public static void postInit() {
    }

    public static Block registerBlock(Block block, String key, String name) {
        return registerBlock(block, Reference.MODID, key, name, key, null, Base.instance.getCreativeTab(), new ItemBlock(block));
    }

    public static Block registerBlock(Block block, String key, String name, String texture) {
        return registerBlock(block, Reference.MODID, key, name, texture, null, Base.instance.getCreativeTab(), new ItemBlock(block));
    }

    public static Block registerBlock(Block block, String key, String name, String texture, Class tile) {
        return registerBlock(block, Reference.MODID, key, name, texture, tile, Base.instance.getCreativeTab(), new ItemBlock(block));
    }

    public static Block registerBlock(Block block, String key, String name, Class tile) {
        return registerBlock(block, Reference.MODID, key, name, tile, Base.instance.getCreativeTab());
    }

    public static Block registerBlock(Block block, String modid, String key, String name, Class tile, CreativeTabs tab) {
        return registerBlock(block, modid, key, name, key, tile, Base.instance.getCreativeTab(), new ItemBlock(block));
    }

    public static Block registerBlock(Block block, String modid, String key, String name, String texture, Class tile, CreativeTabs tab, ItemBlock itemBlock) {
        block.setUnlocalizedName(modid + ":" + key).setCreativeTab(tab);

        renderMap.put(texture, block);
        Block b = GameRegistry.register(block, new ResourceLocation(modid + ":" + key));
        GameRegistry.register(itemBlock, new ResourceLocation(modid + ":" + key));

        if (tile != null) {
            GameRegistry.registerTileEntity(tile, key);
        }
        return b;
    }
}
