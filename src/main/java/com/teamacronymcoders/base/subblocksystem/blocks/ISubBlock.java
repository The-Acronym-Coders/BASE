package com.teamacronymcoders.base.subblocksystem.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

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

    void getDrops(IBlockState blockState, int fortune, List<ItemStack> itemStacks);

    void setRecipes(List<IRecipe> recipes);

    void setOreDict(Block block, int number, Map<ItemStack, String> oreDict);

    @Nullable
    CreativeTabs getCreativeTab();
}
