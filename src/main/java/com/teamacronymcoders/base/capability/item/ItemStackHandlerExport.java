package com.teamacronymcoders.base.capability.item;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class ItemStackHandlerExport extends ItemStackHandler {

    public ItemStackHandlerExport() {
        this(1);
    }

    public ItemStackHandlerExport(int size) {
        super(size);
    }

    @Override
    @Nonnull
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        return stack;
    }
}
