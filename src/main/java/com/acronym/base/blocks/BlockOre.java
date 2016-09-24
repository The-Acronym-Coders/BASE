package com.acronym.base.blocks;

import com.acronym.base.api.materials.MaterialType;
import com.acronym.base.api.materials.MaterialType.EnumPartType;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;

public class BlockOre extends Block {

    public MaterialType materialType;

    public BlockOre(MaterialType materialType) {
        super(Material.ROCK);
        this.materialType = materialType;
        this.setHardness(3F);
        this.setResistance(5F);
    }

    @Override
    public String getUnlocalizedName() {
        return "block.base.ore." + materialType.getName().toLowerCase();
    }

    @Override
    public String getLocalizedName() {
        return String.format("%s %s", EnumPartType.ORE.getLocalizedName(), this.materialType.getLocalizedName());
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

}
