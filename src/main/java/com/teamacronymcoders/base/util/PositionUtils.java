package com.teamacronymcoders.base.util;

import com.teamacronymcoders.base.Base;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.lang3.tuple.Pair;

public class PositionUtils {

    private static String[] position_pairs = new String[]{"x_y", "x_z", "y_z", "y_x"};

    public static boolean arePositionsAlignedOnTwoAxes(BlockPos first, BlockPos second) {
        for (String pair : position_pairs) {
            String[] split = pair.split("_");
            if ((convertStringToPosVal(split[0], first) == convertStringToPosVal(split[0], second))
                    && (convertStringToPosVal(split[1], first) == convertStringToPosVal(split[1], second)))
                return true;
        }

        return false;
    }

    private static int convertStringToPosVal(String pos_name, BlockPos pos) {
        if (pos_name.equalsIgnoreCase("x"))
            return pos.getX();
        else if (pos_name.equalsIgnoreCase("y"))
            return pos.getY();
        else if (pos_name.equalsIgnoreCase("z"))
            return pos.getZ();
        else
            return 0;
    }

    // TODO Optional ignoring of 'soft blocks' like liquids and grass
    public static boolean isLOSClear(World world, BlockPos first, BlockPos second) {
        Iterator<BlockPos> positions = BlockPos.getAllInBox(first, second).iterator();
        while (positions.hasNext()) {
            BlockPos pos = positions.next();
            // Skip starting positions
            if (pos.equals(first) || pos.equals(second)) {
                Base.instance.getLogger().devInfo("Skipping " + pos.toString());
                continue;
            }
            Base.instance.getLogger().devInfo("Checking " + pos.toString());
            if (!world.isAirBlock(pos))
                return false;
        }
        return true;
    }

    public static void removeBlocksInArea(World world, BlockPos from, BlockPos to) {
        replaceBlocksIn(world, from, to, null);
    }

    public static void replaceBlocksIn(World world, BlockPos from, BlockPos to, IBlockState target) {
        Iterator<BlockPos> positions = BlockPos.getAllInBox(from, to).iterator();
        while (positions.hasNext()) {
            BlockPos pos = positions.next();

            if (pos.equals(from) || pos.equals(to))
                continue;

            if (target == null) {
                world.setBlockToAir(pos);
            } else {
                world.setBlockState(pos, target);
            }
        }
    }

    public static ArrayList<IBlockState> getBlocksOfTypeNearby(World world, BlockPos pos, IBlockState state) {
        ArrayList<IBlockState> blocks = new ArrayList<IBlockState>();
        for (EnumFacing element : EnumFacing.VALUES) {
            BlockPos off = pos.offset(element);
            if (world.getBlockState(off) == state)
                blocks.add(world.getBlockState(off));
        }
        return blocks;
    }

    public static int getDistanceBetweenPositions(BlockPos clicked_pos, BlockPos saved_pos) {
        return (int) Math.round(clicked_pos.getDistance(saved_pos.getX(), saved_pos.getY(), saved_pos.getZ()));
    }

    public static EnumFacing getFacingFromPositions(BlockPos from, BlockPos to) {
        int x = from.subtract(to).getX();
        int y = from.subtract(to).getY();
        int z = from.subtract(to).getZ();

        if (x > 0) {
            return EnumFacing.EAST;
        } else if (x < 0) {
            return EnumFacing.WEST;
        } else if (y < 0) {
            return EnumFacing.DOWN;
        } else if (y > 0) {
            return EnumFacing.UP;
        } else if (z < 0) {
            return EnumFacing.NORTH;
        } else if (z > 0) {
            return EnumFacing.SOUTH;
        } else {
            // This should never happen!
            return null;
        }
    }

	public static Pair<BlockPos, BlockPos> shrinkPositionCubeBy(BlockPos minimumCoord, BlockPos maximumCoord, int by) {
		int minX = minimumCoord.getX();
		int minY = minimumCoord.getY();
		int minZ = minimumCoord.getZ();
		int maxX = maximumCoord.getX();
		int maxY = maximumCoord.getY();
		int maxZ = maximumCoord.getZ();
	
		// TODO Can this be simplified. I can't quite visualise what I'm doing here.
		int interiorMinX = (int) Math.copySign(Math.abs(minX) + (Math.signum(minX) * by), minX);
		int interiorMinY = minY + by;// Y cannot be negative
		int interiorMinZ = (int) Math.copySign(Math.abs(minZ) + (Math.signum(minZ) * by), minZ);
	
		int interiorMaxX = (int) Math.copySign(Math.abs(maxX) - (Math.signum(maxX) * by), maxX);
		int interiorMaxY = maxY - by;
		int interiorMaxZ = (int) Math.copySign(Math.abs(maxZ) - (Math.signum(maxZ) * by), maxZ);
	
		BlockPos minimumInteriorPos = new BlockPos(interiorMinX, interiorMinY, interiorMinZ);
		BlockPos maximumInteriorPos = new BlockPos(interiorMaxX, interiorMaxY, interiorMaxZ);
	
		return Pair.of(minimumInteriorPos, maximumInteriorPos);
	}
}
