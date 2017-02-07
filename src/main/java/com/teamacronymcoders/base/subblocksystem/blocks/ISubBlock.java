package com.teamacronymcoders.base.subblocksystem.blocks;

import net.minecraft.util.ResourceLocation;

public interface ISubBlock {
    String getName();

    String getLocalizedName();

    ResourceLocation getTextureLocation();

    int getColor();
}
