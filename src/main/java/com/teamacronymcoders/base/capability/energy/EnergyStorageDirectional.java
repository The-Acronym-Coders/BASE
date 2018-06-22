package com.teamacronymcoders.base.capability.energy;

import net.minecraftforge.energy.IEnergyStorage;

public class EnergyStorageDirectional implements IEnergyStorage {
    private final IEnergyStorage energyStorage;
    private final boolean input;

    public EnergyStorageDirectional(IEnergyStorage energyStorage, boolean input) {
        this.energyStorage = energyStorage;
        this.input = input;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return canReceive() ? energyStorage.receiveEnergy(maxReceive, simulate) : 0;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return canExtract() ? energyStorage.extractEnergy(maxExtract, simulate) : 0;
    }

    @Override
    public int getEnergyStored() {
        return energyStorage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored() {
        return energyStorage.getMaxEnergyStored();
    }

    @Override
    public boolean canExtract() {
        return !input;
    }

    @Override
    public boolean canReceive() {
        return input;
    }
}
