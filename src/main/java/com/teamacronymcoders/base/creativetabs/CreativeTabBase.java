package com.teamacronymcoders.base.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.function.Function;

public class CreativeTabBase extends CreativeTabs {
    private Function<Object, ItemStack> function;
    public CreativeTabBase(String label, Function<Object, ItemStack> function) {
        super(label);
        this.function = function;
    }

    public void setFunction(Function<Object, ItemStack> function) {
        this.function = function;
    }

    @SideOnly(Side.CLIENT)
    @Nonnull
    public ItemStack getIconItemStack() {
        return function.apply(null);
    }

    @Override
    @Nonnull
    public Item getTabIconItem() {
        return Items.STICK;
    }
}
