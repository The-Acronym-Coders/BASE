package com.teamacronymcoders.base.util;

import net.minecraft.block.BlockRailBase;
import net.minecraft.block.BlockRailBase.EnumRailDirection;
import net.minecraft.block.state.IBlockState;

public class BlockUtils {
    private BlockUtils() { }

    public static boolean isRailBlock(IBlockState blockState) {
        boolean isRailBlock;
        isRailBlock = blockState.getBlock() instanceof BlockRailBase;
        isRailBlock &= getRailDirection(blockState) != null;
        return isRailBlock;
    }

    public static EnumRailDirection getRailDirection(IBlockState blockState) {
        EnumRailDirection railDirection = null;
        if(blockState.getBlock() instanceof BlockRailBase) {
            BlockRailBase blockRailBase = (BlockRailBase) blockState.getBlock();
            railDirection = blockState.getValue(blockRailBase.getShapeProperty());
        }
        return railDirection;
    }
}
