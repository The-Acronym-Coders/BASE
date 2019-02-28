package com.teamacronymcoders.base.blocks;

import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;

@FunctionalInterface
public interface IHasTileEntity {
    default String getTileName(ResourceLocation blockName) {
        return blockName.toString();
    }

    Class<? extends TileEntityType> getTileEntityClass();
}
