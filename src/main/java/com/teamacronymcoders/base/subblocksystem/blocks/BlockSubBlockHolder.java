package com.teamacronymcoders.base.subblocksystem.blocks;

import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.blocks.BlockBase;
import com.teamacronymcoders.base.items.ItemBlockGeneric;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;

public class BlockSubBlockHolder extends BlockBase {
    private static final PropertyInteger SUB_BLOCK_NUMBER = PropertyInteger.create("sub_block_number", 0, 15);
    private Map<Integer, ISubBlock> subBlocks;

    public BlockSubBlockHolder(int number, Map<Integer, ISubBlock> subBlocks) {
        super(Material.IRON, "sub_block_holder_" + number);
        this.setItemBlock(new ItemBlockGeneric<>(this));
        this.subBlocks = subBlocks;
        this.setCreativeTab(Base.instance.getCreativeTab());
    }


    @Override
    public List<ItemStack> getAllSubItems(List<ItemStack> itemStacks) {
        for(int x = 0; x < 16; x++) {
            if(subBlocks.get(x) != null) {
                itemStacks.add(new ItemStack(this, 1, x));
            }
        }
        return itemStacks;
    }

    @Override
    @Nonnull
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(SUB_BLOCK_NUMBER, meta);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(SUB_BLOCK_NUMBER);
    }

    @Override
    @Nonnull
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, SUB_BLOCK_NUMBER);
    }

}
