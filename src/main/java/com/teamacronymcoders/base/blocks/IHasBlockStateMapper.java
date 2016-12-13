package com.teamacronymcoders.base.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

public interface IHasBlockStateMapper {
    ResourceLocation getResourceLocation(IBlockState blockState);

    default String getVariant(IBlockState blockState) {
        return "normal";
    }
}
