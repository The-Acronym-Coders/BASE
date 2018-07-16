package com.teamacronymcoders.base.items;

import com.google.common.collect.Lists;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemStackDropTable implements IDropTable {
    private List<ItemStack> itemStack;
    
    public ItemStackDropTable(ItemStack itemStack) {
        this.itemStack = Lists.newArrayList(itemStack);
    }
    
    @Override
    public List<ItemStack> getDrops(int fortune) {
        return itemStack;
    }
}
