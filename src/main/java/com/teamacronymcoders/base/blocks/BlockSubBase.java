package com.teamacronymcoders.base.blocks;

import com.teamacronymcoders.base.items.ItemSubBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;

public class BlockSubBase extends BlockBase {
    String[] names;

    public BlockSubBase(Material mat, String[] names) {
        super(mat);
        this.names = names;
    }

    @Override
    public ItemBlock getItemBlock() {
        return new ItemSubBlock(this, names);
    }

    @Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }
}
