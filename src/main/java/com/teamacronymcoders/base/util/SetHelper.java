package com.teamacronymcoders.base.util;

import com.teamacronymcoders.base.blocks.BaseBlocks;
import com.teamacronymcoders.base.blocks.sets.wood.*;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Created by Jared.
 */
public class SetHelper {

    public static void registerWoodSet(String key, String name, String modid, MapColor mapColor) {
        BlockBaseLog logs = new BlockBaseLog();
        BlockBasePlanks planks = new BlockBasePlanks();
        BlockBaseButton button = new BlockBaseButton();
        BlockFence fence = new BlockFence(Material.WOOD, mapColor);
        BlockBaseFenceGate gate = new BlockBaseFenceGate(mapColor);
        BlockBaseSlab halfSlab = new BlockBaseSlab(Material.WOOD, false);
        BlockBaseSlab fullSlab = new BlockBaseSlab(Material.WOOD, true);
        try {
            BaseBlocks.registerBlock(logs, modid, key + "Logs", name + "Logs", null, CreativeTabs.BUILDING_BLOCKS);
            BaseBlocks.registerBlock(planks, modid, key + "Planks", name + "Planks", null, CreativeTabs.BUILDING_BLOCKS);
            BaseBlocks.registerBlock(button, modid, key + "Button", name + "Button", null, CreativeTabs.REDSTONE);
            BaseBlocks.registerBlock(fence, modid, key + "Fence", name + "Fence", null, CreativeTabs.REDSTONE);
            BaseBlocks.registerBlock(gate, modid, key + "Gate", name + "Gate", null, CreativeTabs.REDSTONE);
            BaseBlocks.registerBlock(halfSlab, modid, key + "HalfSlab", name + "HalfSlab", null, CreativeTabs.BUILDING_BLOCKS);
            BaseBlocks.registerBlock(fullSlab, modid, key + "FullSlab", name + "FullSlab", null, CreativeTabs.BUILDING_BLOCKS);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
