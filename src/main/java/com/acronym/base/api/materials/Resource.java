package com.acronym.base.api.materials;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

/**
 * Created by Jared on 4/30/2016.
 */
public class Resource<T> {

    private T stack;

    public Resource(T stack) {
        if (stack instanceof ItemStack || stack instanceof FluidStack || stack instanceof String) {
            this.stack = stack;
        } else {
            //NO-OP
        }
    }

    public boolean isItemStack() {
        return stack instanceof ItemStack;
    }

    public boolean isFluidStack() {
        return stack instanceof FluidStack;
    }

    public boolean isOreDict() {
        return stack instanceof String;
    }

    public ItemStack getItemStack() {
        return ((ItemStack) stack).copy();
    }

    public FluidStack getFluidStack() {
        return (FluidStack) stack;
    }

    public String getOreDict() {
        return (String) stack;
    }

    public <T> T getStack() {
        return (T) stack;
    }

}
