package com.teamacronymcoders.base.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReaderBase;

import javax.annotation.Nullable;

public interface IHasBlockColor extends IAmBlock {
    int colorMultiplier(IBlockState state, @Nullable IWorldReaderBase world, @Nullable BlockPos pos, int tintIndex);
}
