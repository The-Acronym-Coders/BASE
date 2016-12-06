package com.teamacronymcoders.base.blocks;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.teamacronymcoders.base.api.materials.MaterialType;
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
