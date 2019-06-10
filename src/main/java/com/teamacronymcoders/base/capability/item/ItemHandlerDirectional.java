package com.teamacronymcoders.base.capability.item;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;

public class ItemHandlerDirectional implements IItemHandler {
    private final IItemHandler itemHandler;
    private final boolean input;

    public ItemHandlerDirectional(IItemHandler itemHandler, boolean input) {
        this.itemHandler = itemHandler;
        this.input = input;
    }


    @Override
    public int getSlots() {
        return itemHandler.getSlots();
    }

    @Nonnull
    @Override
    public ItemStack getStackInSlot(int slot) {
        return itemHandler.getStackInSlot(slot);
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        return input ? itemHandler.insertItem(slot, stack, simulate) : stack;
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        return !input ? itemHandler.extractItem(slot, amount, simulate) : ItemStack.EMPTY;
    }

    @Override
    public int getSlotLimit(int slot) {
        return itemHandler.getSlotLimit(slot);
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return true;
    }
}
