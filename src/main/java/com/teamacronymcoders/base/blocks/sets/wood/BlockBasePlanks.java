package com.teamacronymcoders.base.blocks.sets.wood;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Created by Jared.
 */
public class BlockBasePlanks extends Block {

    public BlockBasePlanks() {
        super(Material.WOOD);
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }
}
