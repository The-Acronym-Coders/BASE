package com.teamacronymcoders.base.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Optional;

public abstract class BlockTEBase<T extends TileEntity> extends BlockBase implements IHasTileEntity {
    public BlockTEBase(Material material, String name) {
        super(material, name);
    }

    @Override
    public void breakBlock(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
        super.breakBlock(world, pos, state);
        world.removeTileEntity(pos);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean eventReceived(IBlockState state, World world, BlockPos pos, int id, int param) {
        super.eventReceived(state, world, pos, id, param);
        TileEntity tileentity = world.getTileEntity(pos);
        return tileentity != null && tileentity.receiveClientEvent(id, param);
    }

    @SuppressWarnings("unchecked")
    public Optional<T> getTileEntity(IBlockAccess world, BlockPos pos) {
        TileEntity tileEntity = world.getTileEntity(pos);
        T actualTileEntity = null;
        if (tileEntity != null && tileEntity.getClass() == this.getTileEntityClass()) {
            actualTileEntity = (T) tileEntity;
        }
        return Optional.ofNullable(actualTileEntity);
    }

    @Override
    public boolean hasTileEntity(IBlockState blockState) {
        return true;
    }

    @Override
    @Nonnull
    public abstract TileEntity createTileEntity(@Nonnull World world, @Nonnull IBlockState blockState);
}
