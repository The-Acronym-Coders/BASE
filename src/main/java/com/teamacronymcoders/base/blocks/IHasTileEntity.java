package com.teamacronymcoders.base.blocks;

import net.minecraft.tileentity.TileEntity;

@FunctionalInterface
public interface IHasTileEntity {
    Class<? extends TileEntity> getTileEntityClass();
}
