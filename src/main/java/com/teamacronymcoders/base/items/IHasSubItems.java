package com.teamacronymcoders.base.items;

import net.minecraft.item.ItemStack;

import java.util.List;

public interface IHasSubItems {
    List<ItemStack> getAllSubItems(List<ItemStack> itemStacks);
}
