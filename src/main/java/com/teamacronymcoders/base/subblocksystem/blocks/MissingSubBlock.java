package com.teamacronymcoders.base.subblocksystem.blocks;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;

public class MissingSubBlock implements ISubBlock {
    @Override
    public String getName() {
        return "Missing";
    }

    @Override
    public String getUnLocalizedName() {
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
    public int getHardness() {
        return 0;
    }

    @Override
    public int getResistance() {
        return 0;
    }

    @Override
    public int getHarvestLevel() {
        return 0;
    }

    @Override
    @Nonnull
    public String getHarvestTool() {
        return "";
    }

    @Override
    public void setRecipes(List<IRecipe> recipes) {

    }

    @Override
    public void setOreDict(Map<ItemStack, String> oreDict) {

    }
}
