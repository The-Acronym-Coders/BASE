package com.teamacronymcoders.base.capability.fluid;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.fluids.FluidTank;

public class FluidTankSerializable extends FluidTank implements INBTSerializable<NBTTagCompound> {
    public FluidTankSerializable(int capacity) {
        super(capacity);
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return super.writeToNBT(new NBTTagCompound());
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        this.readFromNBT(nbt);
    }
}
