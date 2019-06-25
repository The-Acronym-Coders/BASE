package com.teamacronymcoders.base.capability.fluid;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.fluids.FluidTank;

public class FluidTankSerializable extends FluidTank implements INBTSerializable<CompoundNBT> {
    public FluidTankSerializable(int capacity) {
        super(capacity);
    }

    @Override
    public CompoundNBT serializeNBT() {
        return super.writeToNBT(new CompoundNBT());
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        this.readFromNBT(nbt);
    }
}
