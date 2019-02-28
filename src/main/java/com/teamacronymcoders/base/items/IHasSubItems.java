package com.teamacronymcoders.base.items;

import com.google.common.collect.Lists;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;

import java.util.List;

public interface IHasSubItems extends IItemProvider {
    default List<ItemStack> getAllSubItems() {
        return Lists.newArrayList(new ItemStack(this.asItem(), 1));
    }
}
