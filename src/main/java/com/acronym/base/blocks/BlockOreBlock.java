package com.acronym.base.blocks;

import com.acronym.base.api.materials.MaterialType.EnumPartType;
import com.acronym.base.api.materials.MaterialType;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockRenderLayer;

public class BlockOreBlock extends Block {

	MaterialType materialType;

	public BlockOreBlock(MaterialType materialType) {
		super(Material.IRON);
		this.materialType = materialType;
		this.setHardness(3F);
		this.setResistance(5F);
	}

	@Override
	public String getUnlocalizedName() {
		return "block.base.block."+ materialType.getName().toLowerCase();
	}

	@Override
	public String getLocalizedName() {
		return String.format("%s %s", EnumPartType.BLOCK.getLocalizedName(), this.materialType.getLocalizedName());
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
}
