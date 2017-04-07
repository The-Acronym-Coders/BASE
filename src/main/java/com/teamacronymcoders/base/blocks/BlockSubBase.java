package com.teamacronymcoders.base.blocks;

import com.teamacronymcoders.base.items.ItemSubBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;

import java.util.List;

public class BlockSubBase extends BlockBase {
    String[] names;

    public BlockSubBase(Material mat, String[] names) {
        super(mat);
        this.names = names;
        setItemBlock(new ItemSubBlock<>(this, names));
    }

    @Override
    public List<ItemStack> getAllSubItems(List<ItemStack> itemStacks) {
        for (int x = 0; x < names.length; x++) {
            itemStacks.add(new ItemStack(this, 1, x));
        }
        return itemStacks;
    }

    @Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }
}
