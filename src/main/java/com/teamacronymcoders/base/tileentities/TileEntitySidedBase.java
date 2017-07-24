package com.teamacronymcoders.base.tileentities;

import java.util.Arrays;

import com.teamacronymcoders.base.blocks.properties.SideType;
import com.teamacronymcoders.base.client.IBlockOverlayText;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
		this.sideConfig[side] = this.sideConfig[side].next();
		this.getWorld().addBlockEvent(this.getPos(), this.getBlockType(), 0, 0);
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
		if(this.sideConfig == null) {
			this.sideConfig = new SideType[6];
		}

		for(int i = 0; i < array.length; i++) {
			this.sideConfig[i] = SideType.values()[array[i]];
		}
	}

	@Override
	public NBTTagCompound writeToDisk(NBTTagCompound data) {
		int[] array = new int[6];
		for(int i = 0; i < this.sideConfig.length; i++)
			array[i] = this.sideConfig[i].ordinal();
		data.setIntArray("sideConfig", array);
		return data;
	}

	@Override
	public boolean receiveClientEvent(int id, int arg) {
		if(id == 0) {
			this.getWorld().notifyNeighborsOfStateChange(this.getPos(), getWorld().getBlockState(pos).getBlock(), true);
			return true;
		}
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String[] getOverlayText(EntityPlayer player, RayTraceResult rayTrace, boolean tool) {

		if(tool && isColorBlindActive) {
			SideType facing = sideConfig[rayTrace.sideHit.ordinal()];
			SideType opposite = sideConfig[rayTrace.sideHit.getOpposite().ordinal()];
			return new String[] {
					I18n.format("base.blockSide.facing") + ": "
							+ I18n.format("base.sidetype." + facing.name().toLowerCase()),
					I18n.format("base.blockSide.opposite") + ": "
							+ I18n.format("base.sidetype." + opposite.name().toLowerCase())};
		}

		return null;
	}
}
