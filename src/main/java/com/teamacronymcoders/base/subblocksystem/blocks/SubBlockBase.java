package com.teamacronymcoders.base.subblocksystem.blocks;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;

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

    public String getUnLocalizedName() {
        return "base.subblock." + name;
    }

    public ResourceLocation getTextureLocation() {
        return this.textureLocation;
    }

    public int getColor() {
        return -1;
    }

    @Override
    public void setRecipes(List<IRecipe> recipes) {

    }

    @Override
    public void setOreDict(Map<ItemStack, String> oreDict) {

    }
}
