package com.teamacronymcoders.base.items;

import java.util.List;

import javax.annotation.*;

import com.google.common.collect.Lists;
import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.IModAware;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ItemBaseNoModel extends Item implements IModAware, IHasSubItems {
    protected String texturePath;
    protected String name;

    private IBaseMod mod;

    boolean creativeTabSet = false;

    public ItemBaseNoModel(String name) {
        this("", name);
    }

    public ItemBaseNoModel(String texturePath, String name) {
        super();
        this.name = name;
        this.texturePath = texturePath;
        if (!texturePath.isEmpty() && !texturePath.endsWith("/")) {
            this.texturePath += "/";
        }
        this.setTranslationKey(name);
    }

    @Override
    @Nonnull
    public Item setCreativeTab(@Nonnull CreativeTabs tab) {
        if (!creativeTabSet) {
            super.setCreativeTab(tab);
            this.creativeTabSet = true;
        }
        return this;
    }

    @Override
    public IBaseMod getMod() {
        return mod;
    }

    @Override
    public void setMod(IBaseMod mod) {
        this.mod = mod;
    }

    @Override
    public Item getItem() {
        return this;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void getSubItems(@Nullable CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if (tab != null && tab == this.getCreativeTab() || tab == CreativeTabs.SEARCH) {
            subItems.addAll(this.getAllSubItems(Lists.newArrayList()));
        }
    }

    @Override
    public List<ItemStack> getAllSubItems(List<ItemStack> itemStacks) {
        itemStacks.add(new ItemStack(this, 1));
        return itemStacks;
    }
    
    // Elevate to public
 	@Override
 	public RayTraceResult rayTrace(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, boolean useLiquids) {
 		return super.rayTrace(worldIn, playerIn, useLiquids);
 	}
}
