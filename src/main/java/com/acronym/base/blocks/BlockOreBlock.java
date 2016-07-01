package com.acronym.base.blocks;

import com.acronym.base.api.materials.Material.EnumPartType;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockRenderLayer;

public class BlockOreBlock extends Block {
	com.acronym.base.api.materials.Material material;
	public BlockOreBlock(com.acronym.base.api.materials.Material material) {
		super(Material.IRON);
		this.material=material;
		this.setHardness(3F);
		this.setResistance(5F);
	}

	@Override
	public String getUnlocalizedName() {
		return "block.base.block."+material.getName().toLowerCase();
	}

	@Override
	public String getLocalizedName() {
		return String.format("%s %s", EnumPartType.BLOCK.getLocalizedName(), this.material.getLocalizedName());
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
}
