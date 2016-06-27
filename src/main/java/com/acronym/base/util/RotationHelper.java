package com.acronym.base.util;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;

public class RotationHelper {
    public static AxisAlignedBB rotateBB(AxisAlignedBB box, EnumFacing facing) {
        switch (facing) {
            case NORTH:
            case SOUTH:
                return new AxisAlignedBB(box.minZ, box.minY, box.minX, box.maxZ, box.maxY, box.maxX);
            case EAST:
            case WEST:
                return box;
            default:
                return box;
        }
    }
}
