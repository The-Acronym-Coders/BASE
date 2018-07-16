package com.teamacronymcoders.base.items;

import java.util.List;

import net.minecraft.item.ItemStack;

public interface IDropTable {
    public List<ItemStack> getDrops(int fortune);
}
