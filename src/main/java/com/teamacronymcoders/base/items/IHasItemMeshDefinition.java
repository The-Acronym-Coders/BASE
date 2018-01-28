package com.teamacronymcoders.base.items;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public interface IHasItemMeshDefinition extends IHasSubItems {
    default ResourceLocation getResourceLocation(ItemStack itemStack) {
        return itemStack.getItem().getRegistryName();
    }

    default String getVariant(ItemStack itemStack) {
        return "inventory";
    }

    List<ResourceLocation> getAllVariants();
}
