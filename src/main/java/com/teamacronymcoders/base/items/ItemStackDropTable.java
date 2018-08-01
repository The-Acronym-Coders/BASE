package com.teamacronymcoders.base.items;

import net.minecraft.item.ItemStack;

import java.util.Collections;
import java.util.List;

public class ItemStackDropTable implements IDropTable {
    private List<ItemStack> itemStack;
    
    public ItemStackDropTable(ItemStack itemStack) {
        this.itemStack = Collections.singletonList(itemStack);
    }
    
    @Override
    public List<ItemStack> getDrops(int fortune) {
        return itemStack;
    }
}
