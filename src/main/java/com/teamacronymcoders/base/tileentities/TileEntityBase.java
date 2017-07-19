package com.teamacronymcoders.base.tileentities;

import javax.annotation.Nonnull;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * @author SkySom
 */
public abstract class TileEntityBase extends TileEntity {

	/* Orginally inspired by ZeroCore */

	@Override
	public void readFromNBT(NBTTagCompound data) {
		this.readFromDisk(data);
		super.readFromNBT(data);
	}

	@Override
	@Nonnull
	public NBTTagCompound writeToNBT(NBTTagCompound data) {
		this.writeToDisk(data);
		return super.writeToNBT(data);
	}

	protected abstract void readFromDisk(NBTTagCompound data);

	protected abstract NBTTagCompound writeToDisk(NBTTagCompound data);

	public void sendBlockUpdate() {
		if(!this.getWorld().isRemote) {
			this.getWorld().notifyBlockUpdate(getPos(), getWorld().getBlockState(pos), getWorld().getBlockState(pos),
					3);
		}
	}

}
