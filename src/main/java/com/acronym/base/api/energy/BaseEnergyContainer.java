package com.acronym.base.api.energy;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public class BaseEnergyContainer implements INBTSerializable<NBTTagCompound> {
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

	public long givePower (long energy, boolean simulated) {

		final long acceptedEnergy = Math.min(this.capacity - this.stored, Math.min(this.input, energy));

		if (!simulated)
			this.stored += acceptedEnergy;

		return acceptedEnergy;
	}

	public long takePower (long energy, boolean simulated) {

		final long removedPower = Math.min(this.stored, Math.min(this.output, energy));

		if (!simulated)
			this.stored -= removedPower;

		return removedPower;
	}

	@Override
	public NBTTagCompound serializeNBT () {

		final NBTTagCompound dataTag = new NBTTagCompound();
		dataTag.setLong("StoredPower", this.stored);
		dataTag.setLong("Capacity", this.capacity);
		dataTag.setLong("Input", this.input);
		dataTag.setLong("Output", this.output);

		return dataTag;
	}

	@Override
	public void deserializeNBT (NBTTagCompound nbt) {

		this.stored = nbt.getLong("StoredPower");

		if (nbt.hasKey("Capacity"))
			this.capacity = nbt.getLong("Capacity");

		if (nbt.hasKey("Input"))
			this.input = nbt.getLong("Input");

		if (nbt.hasKey("Output"))
			this.output = nbt.getLong("Output");

		if (this.stored > this.capacity)
			this.stored = this.capacity;
	}


}
