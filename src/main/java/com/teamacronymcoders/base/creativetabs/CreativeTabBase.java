package com.teamacronymcoders.base.creativetabs;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CreativeTabBase extends CreativeTabs {
    private Supplier<ItemStack> function;

    public CreativeTabBase(String label, Supplier<ItemStack> function) {
        super(label);
        this.function = function;
    }

    public void setFunction(Supplier<ItemStack> function) {
        this.function = function;
    }

    @SideOnly(Side.CLIENT)
    @Nonnull
    public ItemStack getIconItemStack() {
        return function.get();
    }

    @Override
    @Nonnull
    public ItemStack createIcon() {
        return function.get();
    }
}
