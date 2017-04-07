package com.teamacronymcoders.base.subblocksystem.blocks;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.Map;

public interface ISubBlock {
    String getName();

    String getLocalizedName();

    ResourceLocation getTextureLocation();

    int getColor();

    void setOreDict(Map<ItemStack, String> oreDict);
}
