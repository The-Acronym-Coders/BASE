package com.teamacronymcoders.base.tileentities;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author SkySom
 */
public abstract class TileEntityBase extends TileEntity {

	/* Orginally inspired by ZeroCore */

	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);
		this.readFromDisk(data);
	}

	@Override
	@Nonnull
	public NBTTagCompound writeToNBT(NBTTagCompound data) {
		this.writeToDisk(data);
		return super.writeToNBT(data);
	}

	protected void readFromDisk(NBTTagCompound data) {}

	protected NBTTagCompound writeToDisk(NBTTagCompound data) {
		return data;
	}

	@Override
	public void handleUpdateTag(@Nonnull NBTTagCompound data) {
		super.readFromNBT(data);
		this.readFromUpdatePacket(data);
	}

	@Override
	@Nonnull
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound data = super.getUpdateTag();
		this.writeToUpdatePacket(data);
		return super.getUpdateTag();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		this.readFromUpdatePacket(packet.getNbtCompound());
		super.onDataPacket(net, packet);
	}

	@Nullable
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound data = new NBTTagCompound();
		this.writeToUpdatePacket(data);
		return new SPacketUpdateTileEntity(this.getPos(), 0, data);
	}

	protected void readFromUpdatePacket(NBTTagCompound data) {}

	protected NBTTagCompound writeToUpdatePacket(NBTTagCompound data) {
		return data;
	}

	public void sendBlockUpdate() {
		if(!this.getWorld().isRemote) {
			this.getWorld().notifyBlockUpdate(getPos(), getWorld().getBlockState(pos), getWorld().getBlockState(pos),
					3);
		}
	}

}
