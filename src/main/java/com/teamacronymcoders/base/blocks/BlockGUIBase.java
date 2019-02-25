package com.teamacronymcoders.base.blocks;

import com.teamacronymcoders.base.guisystem.GuiOpener;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class BlockGUIBase<T extends TileEntity> extends BlockTEBase<T> {

	public BlockGUIBase(Material material, String name) {
		super(material, name);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!playerIn.isSneaking() && getTileEntity(worldIn, pos).isPresent()) {
			GuiOpener.openTileEntityGui(getMod(), playerIn, worldIn, pos);
			return true;
		}
		return false;
	}
}
