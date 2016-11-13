package com.teamacronymcoders.base.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ItemSubBlock extends ItemBlock {
    String[] names;

    public ItemSubBlock(Block block, String[] names) {
        super(block);
        this.names = names;
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int metadata) {
        return metadata;
    }

    @Override
    @Nonnull
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName() + "." + names[stack.getItemDamage()];
    }
}
