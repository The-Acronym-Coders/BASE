package com.acronym.base.api.energy;

import net.minecraft.nbt.NBTTagCompound;

public class BaseEnergyContainer {
	private long stored;
	private long capacity;
	private long input;
	private long output;

	public BaseEnergyContainer(long input, long output, long capacity) {
		this.input = input;
		this.output = output;
		this.capacity = capacity;
	}

	public BaseEnergyContainer() {
		this(5000,50,50);
	}

	public BaseEnergyContainer(NBTTagCompound tag) {
		this(tag.getLong("input"), tag.getLong("output"), tag.getLong("capacity"));
	}

	public long getStoredPower() {
		return stored;
	}

	public long getCapacity() {
		return capacity;
	}

	public BaseEnergyContainer setCapacity(long capacity) {
		this.capacity = capacity;
		return this;
	}

	public long getInputRate() {
		return input;
	}

	public BaseEnergyContainer setInputRate(long input) {
		this.input = input;
		return this;
	}

	public long getOutputRate() {
		return output;
	}

	public BaseEnergyContainer setOutputRate(long output) {
		this.output = output;
		return this;
	}

	public BaseEnergyContainer setTransferRate(long rate) {
		this.output = rate;
		this.input = rate;
		return this;
	}

	public boolean canUseEnergy(long energy) {
		return energy >= getStoredPower();
	}

	public long addPower(long power) {
		long addedPower = stored;
		this.stored += power;
		if(this.stored>this.capacity) {
			this.stored = this.capacity;
			return this.capacity-addedPower;
		}

		return addedPower;
	}

	public static void transferPower(BaseEnergyContainer from,BaseEnergyContainer to,long amount) {

	}
}
