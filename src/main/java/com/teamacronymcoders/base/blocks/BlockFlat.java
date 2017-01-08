package com.teamacronymcoders.base.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public abstract class BlockFlat extends BlockSubBase {
    public static final AxisAlignedBB AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D);

    public BlockFlat(Material mat, String[] names) {
        super(mat, names);
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, @Nonnull BlockPos pos) {
        return super.canPlaceBlockAt(worldIn, pos) && this.canBlockStay(worldIn, pos);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
        this.checkForDrop(worldIn, pos, state);
    }

    public boolean checkForDrop(World worldIn, BlockPos pos, IBlockState state) {
        if (!this.canBlockStay(worldIn, pos)) {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockToAir(pos);
            return false;
        } else
            return true;
    }

    public abstract boolean canBlockStay(World worldIn, BlockPos pos);

    @Override
    @Nonnull
    @SuppressWarnings("deprecation")
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return AABB;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    @SuppressWarnings("deprecation")
    public boolean shouldSideBeRendered(IBlockState blockState, @Nonnull IBlockAccess blockAccess, @Nonnull BlockPos pos,
                                        EnumFacing side) {
        return side == EnumFacing.UP || (blockAccess.getBlockState(pos.offset(side)).getBlock() == this || super.shouldSideBeRendered(blockState, blockAccess, pos, side));
    }
}
