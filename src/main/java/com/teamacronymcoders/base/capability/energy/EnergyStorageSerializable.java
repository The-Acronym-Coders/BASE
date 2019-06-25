package com.teamacronymcoders.base.capability.energy;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.EnergyStorage;

public class EnergyStorageSerializable extends EnergyStorage implements INBTSerializable<CompoundNBT> {
    public EnergyStorageSerializable() {
        this(10000, 1000);
    }

    public EnergyStorageSerializable(int capacity, int maxIO) {
        this(capacity, maxIO, maxIO);
    }

    public EnergyStorageSerializable(int capacity, int maxReceive, int maxExtract) {
        super(capacity, maxReceive, maxExtract);
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbtTagCompound = new CompoundNBT();
        nbtTagCompound.putInt("capacity", this.capacity);
        nbtTagCompound.putInt("maxReceive", this.maxReceive);
        nbtTagCompound.putInt("maxExtract", this.maxExtract);
        nbtTagCompound.putInt("amount", this.energy);
        return nbtTagCompound;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        this.capacity = nbt.getInt("capacity");
        this.maxReceive = nbt.getInt("maxReceive");
        this.maxExtract = nbt.getInt("maxExtract");
        this.energy = nbt.getInt("amount");
    }
}
