package com.teamacronymcoders.base.items;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public interface IHasItemMeshDefinition extends IHasSubItems {
    default ResourceLocation getResourceLocation(ItemStack itemStack) {
        return itemStack.getItem().getRegistryName();
    }

    default String getVariant(ItemStack itemStack) {
        return "inventory";
    }
}
