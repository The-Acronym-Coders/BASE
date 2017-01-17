package com.teamacronymcoders.base.guisystem.target;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.teamacronymcoders.base.guisystem.IHasGui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@GuiTarget
public class GuiTileTarget extends GuiTargetBase<TileEntity> {

	private BlockPos pos;

	public GuiTileTarget() {
		super();
	}

	public GuiTileTarget(@Nonnull TileEntity te, @Nonnull BlockPos pos) {
		super(te);
		this.pos = pos;
	}

	@Override
	public String getName() {
		return "tile";
	}

	@Override
	@Nullable
	public IHasGui deserialize(@Nonnull EntityPlayer entityPlayer, @Nonnull World world,
			@Nonnull NBTTagCompound nbtTagCompound) {
		Long posLong = nbtTagCompound.getLong("blockPos");
		BlockPos pos = BlockPos.fromLong(posLong);
		TileEntity tileEntity = world.getTileEntity(pos);
		if(tileEntity instanceof IHasGui) {
			return (IHasGui) tileEntity;
		}
		else
			return null;
	}

	@Override
	@Nonnull
	public NBTTagCompound serialize(@Nonnull NBTTagCompound nbtTagCompound) {
		nbtTagCompound.setLong("blockPos", this.pos.toLong());
		return nbtTagCompound;
	}

}
