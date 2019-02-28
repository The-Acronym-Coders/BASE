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

    public static ItemStack mergeStacks(ItemStack original, ItemStack additional) {
        if (ItemStackUtils.canStacksMerge(original, additional)) {
            int spaceToAdd = original.getMaxStackSize() - original.getCount();
            if (spaceToAdd > 0) {
                int amountToAdd = Math.min(additional.getCount(), spaceToAdd);
                additional.shrink(amountToAdd);
                original.grow(amountToAdd);
            }
        }

        return additional;
    }

    public static boolean canStacksMerge(ItemStack original, ItemStack additional) {
        return ItemStack.areItemsEqual(original, additional)&&
                ItemStack.areItemStackTagsEqual(original, additional);
    }
}
