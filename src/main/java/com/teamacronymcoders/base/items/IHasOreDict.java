package com.teamacronymcoders.base.items;

import net.minecraft.item.ItemStack;

import java.util.Map;

public interface IHasOreDict {
    Map<ItemStack, String> getOreDictNames(Map<ItemStack, String> names);
}
