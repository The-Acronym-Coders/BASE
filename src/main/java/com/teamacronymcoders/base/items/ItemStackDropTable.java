package com.teamacronymcoders.base.items;

import java.util.Collections;
import java.util.List;

import net.minecraft.item.ItemStack;

public class ItemStackDropTable implements IDropTable {
    private ItemStack itemStack;
    
    public ItemStackDropTable(ItemStack itemStack) {
        this.itemStack = itemStack;
    }
    
    @Override
    public List<ItemStack> getDrops(int fortune) {
        return Collections.singletonList(itemStack.copy());
    }
}
