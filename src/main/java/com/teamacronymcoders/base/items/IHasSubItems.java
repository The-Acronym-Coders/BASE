package com.teamacronymcoders.base.items;

import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;

import java.util.List;

public interface IHasSubItems extends IItemProvider {
    default List<ItemStack> getAllSubItems(List<ItemStack> itemStacks) {
        itemStacks.add(new ItemStack(this.asItem(), 1));
        return itemStacks;
    }
}
