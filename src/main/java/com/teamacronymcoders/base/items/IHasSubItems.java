package com.teamacronymcoders.base.items;

import net.minecraft.item.ItemStack;

import java.util.List;

public interface IHasSubItems extends IAmItem {
    default List<ItemStack> getAllSubItems(List<ItemStack> itemStacks) {
        itemStacks.add(new ItemStack(this.getItem(), 1, 0));
        return itemStacks;
    }
}
