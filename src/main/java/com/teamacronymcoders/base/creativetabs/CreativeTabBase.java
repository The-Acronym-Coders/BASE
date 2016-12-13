package com.teamacronymcoders.base.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import javax.annotation.Nonnull;

public class CreativeTabBase extends CreativeTabs {
    public CreativeTabBase(String label) {
        super(label);
    }

    @Override
    @Nonnull
    public Item getTabIconItem() {
        return Items.IRON_INGOT;
    }
}
