package com.teamacronymcoders.base.items;

import java.util.List;

import net.minecraft.item.ItemStack;

@FunctionalInterface
public interface IDropTable {
    List<ItemStack> getDrops(int fortune);
}
