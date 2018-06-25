package com.teamacronymcoders.base.capability.item;

import com.teamacronymcoders.base.capability.QueueFoundation;
import com.teamacronymcoders.base.util.ItemStackUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;

public class ItemStackQueue extends QueueFoundation<ItemStack> implements IItemHandler {
    public ItemStackQueue() {
        super(5);
    }

    @Override
    protected ItemStack addToBack(ItemStack value) {
        ItemStack remaining = value;
        if (this.getEndOfQueue().isPresent()) {
            remaining = ItemStackUtils.mergeStacks(this.getEndOfQueue().get(), value);
        }
        if (anyRemainingValue(remaining) && this.getLength() < this.getQueueSize()) {
            this.push(remaining);
            remaining = ItemStack.EMPTY;
        }
        return remaining;
    }

    @Override
    protected boolean anyRemainingValue(ItemStack value) {
        return !value.isEmpty();
    }

    @Override
    public NBTTagCompound serializeValue(ItemStack value) {
        return value.serializeNBT();
    }

    @Override
    public ItemStack deserializeValue(NBTTagCompound nbtTagCompound) {
        return new ItemStack(nbtTagCompound);
    }

    @Override
    public int getSlots() {
        return this.getQueueSize();
    }

    @Nonnull
    @Override
    public ItemStack getStackInSlot(int slot) {
        switch (slot) {
            case 0:
                return this.peek().orElse(ItemStack.EMPTY);
            case 1:
                return this.getEndOfQueue().orElse(ItemStack.EMPTY);
            default:
                throw new RuntimeException("Slot " + slot + " not in valid range - [0,1]");
        }
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        if (slot == 1) {
            if (simulate) {
                return this.getLength() < this.getQueueSize() ? ItemStack.EMPTY : stack;
            } else {
                return this.offer(stack).copy();
            }
        }
        return stack;
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        if (slot == 0) {
            if (simulate) {
                return this.peek()
                        .orElse(ItemStack.EMPTY);
            } else {
                return this.pull()
                        .orElse(ItemStack.EMPTY);
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public int getSlotLimit(int slot) {
        return getItemStack(slot).getMaxStackSize();
    }

    @Nonnull
    private ItemStack getItemStack(int slot) {
        if (slot < 0 || slot > 1) {
            throw new RuntimeException("Slot " + slot + " not in valid range - [0,1]");
        }
        return slot == 0 ? this.peek().orElse(ItemStack.EMPTY) : this.getEndOfQueue().orElse(ItemStack.EMPTY);
    }
}
