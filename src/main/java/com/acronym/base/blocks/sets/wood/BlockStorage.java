package com.acronym.base.blocks.sets.wood;

import com.acronym.base.Base;
import com.acronym.base.api.materials.MaterialType;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;

public class BlockStorage extends Block {

    public MaterialType materialType;

    public BlockStorage(MaterialType materialType) {
        super(Material.IRON);
        this.materialType = materialType;
        this.setHardness(5F);
        this.setResistance(10F);
    }

    @Override
    public String getUnlocalizedName() {
        return "block.base.storage." + materialType.getName().toLowerCase();
    }


    @Override
    public String getLocalizedName() {
        if (materialType != null)
            return String.format("%s %s", materialType.getLocalizedName(), Base.languageHelper.none("base.part.storage"));

        return ChatFormatting.RED + Base.languageHelper.error("null_part");
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
