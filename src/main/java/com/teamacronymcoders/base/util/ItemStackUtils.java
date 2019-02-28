package com.teamacronymcoders.base.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemStackUtils {
    private ItemStackUtils() {
    }

    public static boolean isItemInstanceOf(ItemStack itemStack, @Nonnull Class itemClass) {
        return isValid(itemStack) && itemClass.isInstance(itemStack.getItem());
    }

    public static boolean doItemsMatch(ItemStack itemStack, Item item) {
        return isValid(itemStack) && itemStack.getItem() == item;
    }

    public static boolean isValid(@Nullable ItemStack itemStack) {
        return itemStack != null && !itemStack.isEmpty();
    }
}
