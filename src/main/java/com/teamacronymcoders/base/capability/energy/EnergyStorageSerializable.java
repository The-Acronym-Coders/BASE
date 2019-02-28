package com.teamacronymcoders.base.capability.energy;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.EnergyStorage;

public class EnergyStorageSerializable extends EnergyStorage implements INBTSerializable<NBTTagCompound> {
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
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        nbtTagCompound.setInt("capacity", this.capacity);
        nbtTagCompound.setInt("maxReceive", this.maxReceive);
        nbtTagCompound.setInt("maxExtract", this.maxExtract);
        nbtTagCompound.setInt("amount", this.energy);
        return nbtTagCompound;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        this.capacity = nbt.getInt("capacity");
        this.maxReceive = nbt.getInt("maxReceive");
        this.maxExtract = nbt.getInt("maxExtract");
        this.energy = nbt.getInt("amount");
    }
}
