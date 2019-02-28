package com.teamacronymcoders.base.multiblocksystem;

import com.teamacronymcoders.base.Base;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.*;

/**
 * Base logic class for Multiblock-connected tile entities. Most multiblock machines
 * should derive from this and implement their game logic in certain abstract methods.
 */
public abstract class MultiblockTileEntityBase<T extends MultiblockControllerBase> extends TileEntity
        implements IMultiblockPart {

    private MultiblockControllerBase controller;
    private boolean visited;
    // private boolean paused;
    private boolean saveMultiblockData;
    private NBTTagCompound cachedMultiblockData;

    public MultiblockTileEntityBase(TileEntityType<TileEntity> tileEntityType) {
        super(tileEntityType);
        controller = null;
        visited = false;
        saveMultiblockData = false;
        // paused = false;
        cachedMultiblockData = null;
    }

    // Multiblock Connection Base Logic
    @Override
    public Set<MultiblockControllerBase> attachToNeighbors() {
        Set<MultiblockControllerBase> controllers = null;
        MultiblockControllerBase bestController = null;

        // Look for a compatible controller in our neighboring parts.
        IMultiblockPart[] partsToCheck = getNeighboringParts();
        for (IMultiblockPart neighborPart : partsToCheck) {
            if (neighborPart.isConnected()) {
                MultiblockControllerBase candidate = neighborPart.getMultiblockController();
                if (!candidate.getClass().equals(this.getMultiblockControllerType())) {
                    // Skip multiblocks with incompatible types
                    continue;
                }

                if (controllers == null) {
                    controllers = new HashSet<>();
                    bestController = candidate;
                } else if (!controllers.contains(candidate) && candidate.shouldConsume(bestController)) {
                    bestController = candidate;
                }

                controllers.add(candidate);
            }
        }

        // If we've located a valid neighboring controller, attach to it.
        if (bestController != null) {
            // attachBlock will call onAttached, which will set the controller.
            this.controller = bestController;
            bestController.attachBlock(this);
        }

        return controllers;
    }

    @Override
    public void assertDetached() {
        if (this.controller != null) {
            BlockPos coord = this.getWorldPosition();

            Base.instance.getLogger().info(String.format(
                    "[assert] Part @ (%d, %d, %d) should be detached already, but detected that it was not. This is not a fatal error, and will be repaired, but is unusual.",
                    coord.getX(), coord.getY(), coord.getZ()));
            this.controller = null;
        }
    }

    @Override
    public void read(NBTTagCompound data) {
        // We can't directly initialize a multiblock controller yet, so we cache the data here until
        // we receive a validate() call, which creates the controller and hands off the cached data.
        if (data.hasKey("multiblockData")) {
            this.cachedMultiblockData = data.getCompound("multiblockData");
        }
    }

    @Override
    @Nonnull
    public NBTTagCompound write(NBTTagCompound data) {
        if (isMultiblockSaveDelegate() && isConnected()) {
            NBTTagCompound multiblockData = new NBTTagCompound();
            this.getMultiblockController().writeToDisk(multiblockData);
            data.setTag("multiblockData", multiblockData);
        }
        return data;
    }

    /**
     * Called when a block is removed by game actions, such as a player breaking the block
     * or the block being changed into another block.
     *
     * @see TileEntity#remove()
     */
    @Override
    public void remove() {
        super.remove();
        detachSelf(false);
    }

    /**
     * This is called when a block is being marked as valid by the chunk, but has not yet fully
     * been placed into the world's TileEntity cache. this.controllerWorld, xCoord, yCoord and zCoord have
     * been initialized, but any attempts to read data about the world can cause infinite loops -
     * if you call getTileEntity on this TileEntity's coordinate from within validate(), you will
     * blow your call stack.
     * TL;DR: Here there be dragons.
     *
     * @see net.minecraft.tileentity.TileEntity#validate()
     */
    @Override
    public void validate() {
        super.validate();
        MultiblockRegistry.getInstance().onPartAdded(this.getWorld(), this);
    }

    @Override
    public boolean hasMultiblockSaveData() {
        return this.cachedMultiblockData != null;
    }

    @Override
    public NBTTagCompound getMultiblockSaveData() {
        return this.cachedMultiblockData;
    }

    @Override
    public void onMultiblockDataAssimilated() {
        this.cachedMultiblockData = null;
    }

    @Override
    public abstract void onMachineAssembled(MultiblockControllerBase multiblockControllerBase);

    @Override
    public abstract void onMachineBroken();

    @Override
    public abstract void onMachineActivated();

    @Override
    public abstract void onMachineDeactivated();

    @Override
    public boolean isConnected() {
        return (controller != null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getMultiblockController() {
        return (T) controller;
    }

    @Override
    public void becomeMultiblockSaveDelegate() {
        this.saveMultiblockData = true;
    }

    @Override
    public void forfeitMultiblockSaveDelegate() {
        this.saveMultiblockData = false;
    }

    @Override
    public boolean isMultiblockSaveDelegate() {
        return this.saveMultiblockData;
    }

    @Override
    public void setUnvisited() {
        this.visited = false;
    }

    @Override
    public void setVisited() {
        this.visited = true;
    }

    @Override
    public boolean isVisited() {
        return this.visited;
    }

    @Override
    public void onAssimilated(MultiblockControllerBase newController) {
        assert (this.controller != newController);
        this.controller = newController;
    }

    @Override
    public void onAttached(MultiblockControllerBase newController) {
        this.controller = newController;
    }

    @Override
    public void onDetached(MultiblockControllerBase oldController) {
        this.controller = null;
    }

    @Override
    public abstract MultiblockControllerBase createNewMultiblock();

    @Override
    public IMultiblockPart[] getNeighboringParts() {
        List<IMultiblockPart> neighborParts = new ArrayList<>();
        BlockPos neighborPosition, partPosition = this.getWorldPosition();

        for (EnumFacing facing : EnumFacing.values()) {

            neighborPosition = partPosition.offset(facing);

            IWorld world = this.getWorld();
            if (world != null) {
                TileEntity tileEntity = world.getTileEntity(neighborPosition);
                if (tileEntity instanceof IMultiblockPart) {
                    neighborParts.add((IMultiblockPart) tileEntity);
                }
            }
        }

        return neighborParts.toArray(new IMultiblockPart[0]);
    }

    @Override
    public void onOrphaned(MultiblockControllerBase controller, int oldSize, int newSize) {
        this.markDirty();
        Optional.ofNullable(this.getWorld())
                .ifPresent(world -> world.markChunkDirty(this.getWorldPosition(), this));
    }

    @Override
    public BlockPos getWorldPosition() {
        return this.pos;
    }

    @Override
    public boolean isPartInvalid() {
        return this.isRemoved();
    }

    //// Helper functions for notifying neighboring blocks
    protected void notifyNeighborsOfBlockChange() {
        this.getOptionalWorld().ifPresent(world -> world.notifyNeighborsOfStateChange(this.getWorldPosition(),
                this.getBlockState().getBlock()));
    }

    private Optional<World> getOptionalWorld() {
        return Optional.ofNullable(this.getWorld());
    }

    ///// Private/Protected Logic Helpers
    /*
     * Detaches this block from its controller. Calls detachBlock() and clears the controller member.
     */
    protected void detachSelf(boolean chunkUnloading) {
        if (this.controller != null) {
            // Clean part out of controller
            this.controller.detachBlock(this, chunkUnloading);

            // The above should call onDetached, but, just in case...
            this.controller = null;
        }

        // Clean part out of lists in the registry
        MultiblockRegistry.getInstance().onPartRemovedFromWorld(getWorld(), this);
    }

    /**
     * IF the part is connected to a multiblock controller, marks the whole multiblock for a render update on the
     * client.
     * On the server, this does nothing
     */
    protected void markMultiblockForRenderUpdate() {
        MultiblockControllerBase controller = this.getMultiblockController();

        if (null != controller)
            controller.markMultiblockForRenderUpdate();
    }
}
