package com.teamacronymcoders.base.blocks;

import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.api.materials.MaterialType;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nullable;

public class BlockOre extends Block implements IHasBlockColor {

    public MaterialType materialType;

    public BlockOre(MaterialType materialType, float hardness, float resistance, String toolClass, int toolTier) {
        super(Material.ROCK);
        this.materialType = materialType;
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setHarvestLevel(toolClass, toolTier);
    }

    @Override
    public String getUnlocalizedName() {
        return "block.base.ore." + materialType.getName().toLowerCase();
    }


    @Override
    public String getLocalizedName() {
        if (materialType != null)
            return String.format("%s %s", materialType.getLocalizedName(), Base.languageHelper.none("base.part.ore"));

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

    @Override
    public int colorMultiplier(IBlockState state, @Nullable IBlockAccess world, @Nullable BlockPos pos, int tintIndex) {
        return materialType.getColour().getRGB();
    }
}
