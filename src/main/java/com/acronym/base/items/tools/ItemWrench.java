package com.acronym.base.items.tools;

import com.acronym.base.api.registries.WrenchRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemWrench extends Item {
	public ItemWrench() {
		this.setMaxStackSize(1);
		this.setUnlocalizedName("wrench");
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		Block block = worldIn.getBlockState(pos).getBlock();
		TileEntity tile = worldIn.getTileEntity(pos);
		if(WrenchRegistry.isWrenchable(block)) {
			worldIn.destroyBlock(pos,true);
			if(tile!=null)
				worldIn.removeTileEntity(pos);
		}
		return super.onItemUse(stack, playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}


}
