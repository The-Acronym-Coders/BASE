package com.teamacronymcoders.base.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

public interface IHasBlockStateMapper extends IAmBlock {
    default ResourceLocation getResourceLocation(IBlockState blockState) {
        return getBlock().getRegistryName();
    }

    default String getVariant(IBlockState blockState) {
        return "normal";
    }
}
