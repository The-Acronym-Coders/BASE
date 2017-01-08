package com.teamacronymcoders.base.items;

import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.Map;

public interface IHasOreDict {
    @Nonnull
    Map<ItemStack, String> getOreDictNames(@Nonnull Map<ItemStack, String> names);
}
