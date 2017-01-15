package com.teamacronymcoders.base.tileentities;

import java.util.Arrays;

import com.teamacronymcoders.base.blocks.SideType;
import com.teamacronymcoders.base.client.IBlockOverlayText;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.RayTraceResult;

/**
 * @author SkySom
 */
public abstract class TileEntitySidedBase extends TileEntityBase implements IBlockOverlayText {
	private SideType[] sideConfig;
	private boolean isColorBlindActive;

	public TileEntitySidedBase() {
		super();
		sideConfig = new SideType[6];
		Arrays.fill(sideConfig, SideType.NONE);
		isColorBlindActive = false; // TODO
	}

	public void toggleSide(int side) {
		int type = sideConfig[side].ordinal();
		type++;
		if(type >= SideType.values().length || type < 0)
			type = 0;
		this.sideConfig[side] = SideType.values()[type];
		worldObj.addBlockEvent(this.getPos(), this.getBlockType(), 0, 0);
	}

	public void setSideConfig(int side, SideType sideType) {
		this.sideConfig[side] = sideType;
	}

	public SideType getSideValue(int side) {
		return this.sideConfig[side];
	}

	@Override
	public void readFromDisk(NBTTagCompound data) {
		int[] array = data.getIntArray("sideConfig");
		if(this.sideConfig == null)
			this.sideConfig = new SideType[6];
		if(array != null)
			for(int i = 0; i < array.length; i++)
				this.sideConfig[i] = SideType.values()[array[i]];
		super.readFromDisk(data);
	}

	@Override
	public NBTTagCompound writeToDisk(NBTTagCompound data) {
		int[] array = new int[6];
		for(int i = 0; i < this.sideConfig.length; i++)
			array[i] = this.sideConfig[i].ordinal();
		data.setIntArray("sideConfig", array);
		return super.writeToDisk(data);
	}

	@Override
	public boolean receiveClientEvent(int id, int arg) {
		if(id == 0) {
			this.worldObj.notifyBlockOfStateChange(this.getPos(), worldObj.getBlockState(pos).getBlock());
			return true;
		}
		return false;
	}

	@Override
	public String[] getOverlayText(EntityPlayer player, RayTraceResult rayTrace, boolean tool) {
		/*
		 * // if(tool && isColorBlindActive) {
		 * // SideType facing = sideConfig[rayTrace.sideHit.ordinal()];
		 * // SideType opposite = sideConfig[rayTrace.sideHit.getOpposite().ordinal()];
		 * // return new String[] {
		 * // mod.getBoilerplateProxy().translate("blockSide.facing") + ": "
		 * // + mod.getBoilerplateProxy().translate("sidetype." + facing.name().toLowerCase()),
		 * // mod.getBoilerplateProxy().translate("blockSide.opposite") + ": "
		 * + mod.getBoilerplateProxy().translate("sidetype." + opposite.name().toLowerCase())};
		 * // }
		 */ return null;
	}
}
