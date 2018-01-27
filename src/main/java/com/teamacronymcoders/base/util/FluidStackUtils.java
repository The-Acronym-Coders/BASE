package com.teamacronymcoders.base.util;

import net.minecraftforge.fluids.FluidStack;

public class FluidStackUtils {
    public static int moveFluid(FluidStack from, FluidStack to, int amount, int maxToAmount) {
        int amountMoved = 0;
        if (from.isFluidEqual(to)) {
            amountMoved = amount;
            if (amountMoved > from.amount) {
                amountMoved = from.amount;
            }

            int amountToCanAccept = maxToAmount - to.amount;
            if (amountToCanAccept <= 0) {
                amountMoved = 0;
            } else if (amountMoved > amountToCanAccept) {
                amountMoved = amountToCanAccept;
            }

            to.amount += amountMoved;
            from.amount -= amountMoved;
        }
        return amountMoved;
    }
}
