package com.teamacronymcoders.base.util;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryHelper {

    /**
     * Adds an ItemStack to an inventory
     *
     * @param itemIn    ItemStack to add
     * @param inventory Inventory to add to
     * @param slotStart startSlot
     * @param slotEnd   endSlot
     * @return ItemStack
     */
    public static ItemStack addItemStackToInventory(ItemStack itemIn, IInventory inventory, int slotStart, int slotEnd) {
        return addItemStackToInventory(itemIn, inventory, slotStart, slotEnd, false);
    }

    /**
     * Checks if the output is full
     *
     * @param inventory Inventory to check
     * @param slotStart StartSlot
     * @param slotEnd   EndSlot
     * @return boolean showing weither the output is full
     */
    public static boolean isOutputFull(IInventory inventory, int slotStart, int slotEnd) {
        boolean full = true;

        for (int i = slotStart; i < slotEnd; i++) {
            full = !(inventory.getStackInSlot(i) == null && full);
        }
        return full;
    }

    /**
     * Adds an ItemStack to an inventory
     *
     * @param itemIn    ItemStack to add
     * @param inventory Inventory to add to
     * @param slotStart startSlot
     * @param slotEnd   endSlot
     * @param simulate  should the action actually be done?
     * @return Itemstack
     */
    public static ItemStack addItemStackToInventory(ItemStack itemIn, IInventory inventory, int slotStart, int slotEnd, boolean simulate) {
        if (itemIn == null) return null;
        ItemStack itemOut = itemIn.copy();

        for (int i = slotStart; i <= slotEnd; i++) {
            ItemStack slotItemStack = inventory.getStackInSlot(i) == null ? null : inventory.getStackInSlot(i).copy();

            if (itemOut == null) return null;
            if (slotItemStack == null) {
                if (!simulate) inventory.setInventorySlotContents(i, itemOut);
                return null;
            }

            if (!(ItemStack.areItemsEqual(itemOut, slotItemStack))) continue;

            if (slotItemStack.stackSize == slotItemStack.getMaxStackSize()) continue;

            if (itemOut.stackSize + slotItemStack.stackSize >= slotItemStack.getMaxStackSize()) {
                int sizeRemaining = slotItemStack.getMaxStackSize() - slotItemStack.stackSize;
                itemOut.stackSize = itemOut.stackSize - sizeRemaining;
                if (!simulate) slotItemStack.stackSize = slotItemStack.stackSize + sizeRemaining;
                if (!simulate) inventory.setInventorySlotContents(i, slotItemStack);
                if (itemOut.stackSize == 0) itemOut = null;
                continue;
            }

            slotItemStack.stackSize = slotItemStack.stackSize + itemOut.stackSize;
            itemOut = null;

            if (!simulate) inventory.setInventorySlotContents(i, slotItemStack);
            break;
        }

        if (itemOut != null)
            return itemOut;

        return null;
    }
}
