package com.teamacronymcoders.base.subblocksystem.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;

import java.util.List;
import java.util.Map;

import static com.teamacronymcoders.base.Reference.MODID;

public abstract class SubBlockBase implements ISubBlock {
    private String name;
    private ResourceLocation textureLocation;

    public SubBlockBase(String name) {
        this.name = name;
        this.textureLocation = new ResourceLocation(MODID, name);
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String getUnLocalizedName() {
        return "base.subblock." + name;
    }

    @Override
    public ResourceLocation getTextureLocation() {
        return this.textureLocation;
    }

    @Override
    public int getColor() {
        return -1;
    }

    @Override
    public void getDrops(IBlockState blockState, int fortune, List<ItemStack> itemStacks) {
        Block block = blockState.getBlock();
        itemStacks.add(new ItemStack(block, 1, block.getMetaFromState(blockState)));
    }

    @Override
    public void setRecipes(List<IRecipe> recipes) {

    }

    @Override
    public void setOreDict(Block block, int number, Map<ItemStack, String> oreDict) {

    }
}
