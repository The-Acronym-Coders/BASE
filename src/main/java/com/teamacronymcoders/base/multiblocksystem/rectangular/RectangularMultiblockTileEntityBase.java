package com.teamacronymcoders.base.multiblocksystem.rectangular;

import com.teamacronymcoders.base.multiblocksystem.BlockFacings;
import com.teamacronymcoders.base.multiblocksystem.MultiblockControllerBase;
import com.teamacronymcoders.base.multiblocksystem.MultiblockTileEntityBase;
import com.teamacronymcoders.base.multiblocksystem.validation.IMultiblockValidator;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class RectangularMultiblockTileEntityBase<T extends RectangularMultiblockControllerBase>
        extends MultiblockTileEntityBase<T> {

    private PartPosition position;
    private BlockFacings outwardFacings;

    public RectangularMultiblockTileEntityBase(TileEntityType tileEntityType) {
        super(tileEntityType);
        position = PartPosition.UNKNOWN;
        outwardFacings = BlockFacings.NONE;
    }

    // Positional Data

    /**
     * Get the outward facing of the part in the formed multiblock
     *
     * @return the outward facing of the part. A face is "set" in the BlockFacings object if that face is facing outward
     */
    @Nonnull
    public BlockFacings getOutwardsDir() {

        return outwardFacings;
    }

    /**
     * Get the position of the part in the formed multiblock
     *
     * @return the position of the part
     */
    @Nonnull
    public PartPosition getPartPosition() {

        return position;
    }

    /**
     * Return the single direction this part is facing if the part is in one side of the multiblock
     *
     * @return the direction toward with the part is facing or null if the part is not in one side of the multiblock
     */
    @Nullable
    public EnumFacing getOutwardFacing() {

        EnumFacing facing = null != this.position ? this.position.getFacing() : null;

        if (null == facing) {

            BlockFacings out = this.getOutwardsDir();

            if (!out.none() && 1 == out.countFacesIf(true))
                facing = out.firstIf(true);
        }

        return facing;
    }

    /**
     * Return the single direction this part is facing based on it's position in the multiblock
     *
     * @return the direction toward with the part is facing or null if the part is not in one side of the multiblock
     */
    @Nullable
    public EnumFacing getOutwardFacingFromWorldPosition() {

        BlockFacings facings = null;
        MultiblockControllerBase controller = this.getMultiblockController();

        if (null != controller) {

            BlockPos position = this.getWorldPosition();
            BlockPos min = controller.getMinimumCoord();
            BlockPos max = controller.getMaximumCoord();
            int x = position.getX(), y = position.getY(), z = position.getZ();

            facings = BlockFacings.from(min.getY() == y, max.getY() == y, min.getZ() == z, max.getZ() == z,
                    min.getX() == x, max.getX() == x);
        }

        return null != facings && !facings.none() && 1 == facings.countFacesIf(true) ? facings.firstIf(true) : null;
    }

    // Handlers from MultiblockTileEntityBase

    @Override
    public void onAttached(MultiblockControllerBase newController) {
        super.onAttached(newController);
        recalculateOutwardsDirection(newController.getMinimumCoord(), newController.getMaximumCoord());
    }

    @Override
    public void onMachineAssembled(MultiblockControllerBase controller) {

        // Discover where I am on the reactor
        recalculateOutwardsDirection(controller.getMinimumCoord(), controller.getMaximumCoord());
    }

    @Override
    public void onMachineBroken() {
        position = PartPosition.UNKNOWN;
        outwardFacings = BlockFacings.NONE;
    }

    // Positional helpers
    public void recalculateOutwardsDirection(BlockPos minCoord, BlockPos maxCoord) {
        BlockPos myPosition = this.getPos();
        int myX = myPosition.getX();
        int myY = myPosition.getY();
        int myZ = myPosition.getZ();
        int facesMatching = 0;

        // witch direction are we facing?

        boolean downFacing = myY == minCoord.getY();
        boolean upFacing = myY == maxCoord.getY();
        boolean northFacing = myZ == minCoord.getZ();
        boolean southFacing = myZ == maxCoord.getZ();
        boolean westFacing = myX == minCoord.getX();
        boolean eastFacing = myX == maxCoord.getX();

        this.outwardFacings = BlockFacings.from(downFacing, upFacing, northFacing, southFacing, westFacing, eastFacing);

        // how many faces are facing outward?

        if (eastFacing || westFacing)
            ++facesMatching;

        if (upFacing || downFacing)
            ++facesMatching;

        if (southFacing || northFacing)
            ++facesMatching;

        // what is our position in the multiblock structure?

        if (facesMatching <= 0) {
            this.position = PartPosition.INTERIOR;
        }
        //TODO Clean up position logic based on new granularity
        else if (facesMatching >= 3) {
            if (upFacing) {
                if (southFacing) {
                    if (eastFacing) {
                        this.position = PartPosition.FRAME_CORNER_SOUTH_EAST_TOP;
                    } else if (westFacing) {
                        this.position = PartPosition.FRAME_CORNER_SOUTH_WEST_TOP;
                    }
                } else if (northFacing) {
                    if (eastFacing) {
                        this.position = PartPosition.FRAME_CORNER_NORTH_EAST_TOP;
                    } else if (westFacing) {
                        this.position = PartPosition.FRAME_CORNER_NORTH_WEST_TOP;
                    }
                }
            } else if (downFacing) {
                if (southFacing) {
                    if (eastFacing) {
                        this.position = PartPosition.FRAME_CORNER_SOUTH_EAST_BOTTOM;
                    } else if (westFacing) {
                        this.position = PartPosition.FRAME_CORNER_SOUTH_WEST_BOTTOM;
                    }
                } else if (northFacing) {
                    if (eastFacing) {
                        this.position = PartPosition.FRAME_CORNER_NORTH_EAST_BOTTOM;
                    } else if (westFacing) {
                        this.position = PartPosition.FRAME_CORNER_NORTH_WEST_BOTTOM;
                    }
                }
            }
        } else if (facesMatching == 2) {

            if (!eastFacing && !westFacing) {
                if (upFacing) {
                    if (northFacing) {
                        this.position = PartPosition.FRAME_NORTH_TOP;
                    } else if (southFacing) {
                        this.position = PartPosition.FRAME_SOUTH_TOP;
                    }
                } else if (downFacing) {
                    if (northFacing) {
                        this.position = PartPosition.FRAME_NORTH_BOTTOM;
                    } else if (southFacing) {
                        this.position = PartPosition.FRAME_SOUTH_BOTTOM;
                    }
                }
            } else if (!southFacing && !northFacing) {
                if (upFacing) {
                    if (eastFacing) {
                        this.position = PartPosition.FRAME_EAST_TOP;
                    } else if (westFacing) {
                        this.position = PartPosition.FRAME_WEST_TOP;
                    }
                } else if (downFacing) {
                    if (eastFacing) {
                        this.position = PartPosition.FRAME_EAST_BOTTOM;
                    } else if (westFacing) {
                        this.position = PartPosition.FRAME_WEST_BOTTOM;
                    }
                }
            } else {
                if (southFacing) {
                    if (eastFacing) {
                        this.position = PartPosition.FRAME_VERTICAL_SOUTH_EAST;
                    } else if (westFacing) {
                        this.position = PartPosition.FRAME_VERTICAL_SOUTH_WEST;
                    }
                } else if (northFacing) {
                    if (eastFacing) {
                        this.position = PartPosition.FRAME_VERTICAL_NORTH_EAST;
                    } else if (westFacing) {
                        this.position = PartPosition.FRAME_VERTICAL_NORTH_WEST;
                    }
                }
            }

        } else {

            // only 1 face matches

            if (eastFacing) {

                this.position = PartPosition.EAST_FACE;

            } else if (westFacing) {

                this.position = PartPosition.WEST_FACE;

            } else if (southFacing) {

                this.position = PartPosition.SOUTH_FACE;

            } else if (northFacing) {

                this.position = PartPosition.NORTH_FACE;

            } else if (upFacing) {

                this.position = PartPosition.TOP_FACE;

            } else {

                this.position = PartPosition.BOTTOM_FACE;
            }
        }
    }

    ///// Validation Helpers (IMultiblockPart)

    public abstract boolean isGoodForFrame(IMultiblockValidator validatorCallback);

    public abstract boolean isGoodForSides(IMultiblockValidator validatorCallback);

    public abstract boolean isGoodForTop(IMultiblockValidator validatorCallback);

    public abstract boolean isGoodForBottom(IMultiblockValidator validatorCallback);

    public abstract boolean isGoodForInterior(IMultiblockValidator validatorCallback);
}
