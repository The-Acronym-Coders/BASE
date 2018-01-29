package com.teamacronymcoders.base.capability.item;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class ItemStackHandlerImport extends ItemStackHandler {

    public ItemStackHandlerImport() {
        this(1);
    }

    public ItemStackHandlerImport(int size) {
        super(size);
    }

    @Override
    @Nonnull
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        return stack;
    }
}
