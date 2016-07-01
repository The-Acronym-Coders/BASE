package com.acronym.base.blocks;

import com.acronym.base.Base;
import com.acronym.base.api.registries.MaterialRegistry;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockRenderLayer;
import com.acronym.base.api.materials.Material.EnumPartType;

public class BlockOre extends Block {
	com.acronym.base.api.materials.Material material;
	public BlockOre(com.acronym.base.api.materials.Material material) {
		super(Material.ROCK);
		this.material=material;
		this.setHardness(3F);
		this.setResistance(5F);
	}

	@Override
	public String getUnlocalizedName() {
		return "block.base.ore."+material.getName().toLowerCase();
	}

	@Override
	public String getLocalizedName() {
		return String.format("%s %s", EnumPartType.ORE.getLocalizedName(), this.material.getLocalizedName());
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
}
