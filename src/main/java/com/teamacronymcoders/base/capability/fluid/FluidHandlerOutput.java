package com.teamacronymcoders.base.capability.fluid;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

import javax.annotation.Nullable;

public class FluidHandlerOutput implements IFluidHandler {
    public FluidTank fluidTank;

    public FluidHandlerOutput(FluidTank fluidTank) {
        this.fluidTank = fluidTank;
    }
    @Override
    public IFluidTankProperties[] getTankProperties() {
        return fluidTank.getTankProperties();
    }

    @Override
    public int fill(FluidStack resource, boolean doFill) {
        return 0;
    }

    @Nullable
    @Override
    public FluidStack drain(FluidStack resource, boolean doDrain) {
        return fluidTank.drain(resource, doDrain);
    }

    @Nullable
    @Override
    public FluidStack drain(int maxDrain, boolean doDrain) {
        return fluidTank.drain(maxDrain, doDrain);
    }
}
