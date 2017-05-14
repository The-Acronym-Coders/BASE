package com.teamacronymcoders.base.subblocksystem.blocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public interface ISubBlock {
    String getName();

    String getUnLocalizedName();

    default String getLocalizedName() {
        //noinspection deprecation
        return I18n.translateToLocal(this.getUnLocalizedName());
    }

    ResourceLocation getTextureLocation();

    int getColor();

    int getHardness();

    int getResistance();

    int getHarvestLevel();

    @Nonnull
    String getHarvestTool();

    void getDrops(int fortune, List<ItemStack> itemStacks);

    void setRecipes(List<IRecipe> recipes);

    String getOreDict();

    @Nullable
    CreativeTabs getCreativeTab();

    void setItemStack(ItemStack itemStack);
}
