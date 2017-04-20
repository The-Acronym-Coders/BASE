package com.teamacronymcoders.base.subblocksystem.blocks;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;

public interface ISubBlock {
    String getName();

    String getUnLocalizedName();

    ResourceLocation getTextureLocation();

    int getColor();

    int getHardness();

    int getResistance();

    int getHarvestLevel();

    @Nonnull
    String getHarvestTool();

    void setRecipes(List<IRecipe> recipes);

    void setOreDict(Map<ItemStack, String> oreDict);
}
