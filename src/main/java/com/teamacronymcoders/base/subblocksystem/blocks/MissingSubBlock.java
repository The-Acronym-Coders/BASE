package com.teamacronymcoders.base.subblocksystem.blocks;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.Map;

public class MissingSubBlock implements ISubBlock {
    @Override
    public String getName() {
        return "Missing";
    }

    @Override
    public String getLocalizedName() {
        return "Missing";
    }

    @Override
    public ResourceLocation getTextureLocation() {
        return Blocks.BEDROCK.getRegistryName();
    }

    @Override
    public int getColor() {
        return -1;
    }

    @Override
    public void setOreDict(Map<ItemStack, String> oreDict) {

    }
}
