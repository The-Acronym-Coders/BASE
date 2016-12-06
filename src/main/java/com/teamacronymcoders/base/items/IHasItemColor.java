package com.teamacronymcoders.base.items;

import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public interface IHasItemColor {
    int getColorFromItemstack(@Nonnull ItemStack stack, int tintIndex);
}
