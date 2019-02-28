package com.teamacronymcoders.base.multiblocksystem;

import com.teamacronymcoders.base.Base;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import java.util.HashMap;

public final class MultiblockRegistry implements IMultiblockRegistry {

    private static MultiblockRegistry instance;

    public static MultiblockRegistry getInstance() {
        if (instance == null) {
            instance = new MultiblockRegistry();
        }
        return instance;
    }
    private HashMap<IWorld, MultiblockWorldRegistry> registries;

    private MultiblockRegistry() {
        this.registries = new HashMap<>(2);
        MinecraftForge.EVENT_BUS.register(new MultiblockEventHandler());
    }

    /**
     * Register a new part in the system. The part has been created either through user action or via a chunk loading.
     *
     * @param world The world into which this part is loading.
     * @param part  The part being loaded.
     */
    @Override
    public void onPartAdded(final World world, final IMultiblockPart part) {

        MultiblockWorldRegistry registry;

        if (this.registries.containsKey(world)) {

            registry = this.registries.get(world);

        } else {

            registry = new MultiblockWorldRegistry(world);
            this.registries.put(world, registry);
        }

        registry.onPartAdded(part);
    }

    /*
     * Private implementation
     */

    /**
     * Call to remove a part from world lists.
     *
     * @param world The world from which a multiblock part is being removed.
     * @param part  The part being removed.
     */
    @Override
    public void onPartRemovedFromWorld(final World world, final IMultiblockPart part) {

        if (this.registries.containsKey(world))
            this.registries.get(world).onPartRemovedFromWorld(part);
    }

    /**
     * Call to mark a controller as dead. It should only be marked as dead
     * when it has no connected parts. It will be removed after the next world tick.
     *
     * @param world      The world formerly containing the multiblock
     * @param controller The dead controller
     */
    @Override
    public void addDeadController(final World world, final MultiblockControllerBase controller) {

        if (this.registries.containsKey(world))
            this.registries.get(world).addDeadController(controller);
        else
            Base.instance.getLogger().warn(
                    "Controller %d in world %s marked as dead, but that world is not tracked! Controller is being ignored.",
                    controller.hashCode(), world);
    }

    /**
     * Call to mark a controller as dirty. Dirty means that parts have
     * been added or removed this tick.
     *
     * @param world      The world containing the multiblock
     * @param controller The dirty controller
     */
    @Override
    public void addDirtyController(final World world, final MultiblockControllerBase controller) {

        if (this.registries.containsKey(world)) {
            this.registries.get(world).addDirtyController(controller);
        } else {
            throw new IllegalArgumentException(
                    "Adding a dirty controller to a world that has no registered controllers!");
        }
    }

    /**
     * Called before Tile Entities are ticked in the world. Do bookkeeping here.
     *
     * @param world The world being ticked
     */
    protected void tickStart(final World world) {

        if (this.registries.containsKey(world)) {

            final MultiblockWorldRegistry registry = this.registries.get(world);

            registry.processMultiblockChanges();
            registry.tickStart();
        }
    }

    /**
     * Called when the world has finished loading a chunk.
     *
     * @param world  The world which has finished loading a chunk
     * @param chunkX The X coordinate of the chunk
     * @param chunkZ The Z coordinate of the chunk
     */
    protected void onChunkLoaded(final IWorld world, final int chunkX, final int chunkZ) {

        if (this.registries.containsKey(world)) {
            this.registries.get(world).onChunkLoaded(chunkX, chunkZ);
        }
    }

    /**
     * Called whenever a world is unloaded. Unload the relevant registry, if we have one.
     *
     * @param world The world being unloaded.
     */
    protected void onWorldUnloaded(final IWorld world) {
        if (this.registries.containsKey(world)) {
            this.registries.get(world).onWorldUnloaded();
            this.registries.remove(world);
        }
    }
}