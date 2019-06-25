package com.teamacronymcoders.base.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

import javax.annotation.Nullable;

public interface IHasBlockColor extends IAmBlock {
    int colorMultiplier(BlockState state, @Nullable IWorldReader world, @Nullable BlockPos pos, int tintIndex);
}
