package com.teamacronymcoders.base.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

import javax.annotation.Nonnull;

public class ItemStackUtils {
    private ItemStackUtils() {
    }

    public static boolean isSmeltable(ItemStack itemStack) {
        return isValidItemStack(itemStack) && FurnaceRecipes.instance().getSmeltingResult(itemStack) != null;
    }

    public static boolean isItemInstanceOf(ItemStack itemStack, Class itemClass) {
        return isValidItemStack(itemStack) && itemClass != null && itemClass.isInstance(itemStack.getItem());
    }

    public static boolean doItemsMatch(ItemStack itemStack, Item item) {
        return isValidItemStack(itemStack) && itemStack.getItem() == item;
    }

    public static boolean isValidItemStack(ItemStack itemStack) {
        return itemStack != null;
    }

    @Nonnull
    public static String getModIdFromItemStack(@Nonnull ItemStack itemStack) {
        return itemStack.getItem().getRegistryName().getResourceDomain();
    }
}
