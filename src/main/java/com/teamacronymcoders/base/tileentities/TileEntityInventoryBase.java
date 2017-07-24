package com.teamacronymcoders.base.tileentities;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityInventoryBase extends TileEntityBase {
	private ItemStackHandler inventory;

	public TileEntityInventoryBase(int inventorySize) {
		this(new ItemStackHandler(inventorySize));
	}

	public TileEntityInventoryBase(ItemStackHandler inventory) {
		this.inventory = inventory;
	}

	@Override
	public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Nonnull
	public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return (T) inventory;
		}
		return super.getCapability(capability, facing);
	}

	@Override
	protected void readFromDisk(NBTTagCompound data) {
		inventory.deserializeNBT(data.getCompoundTag("Items"));
	}

	@Override
	protected NBTTagCompound writeToDisk(NBTTagCompound data) {
		data.setTag("Items", inventory.serializeNBT());
		return data;
	}
}
