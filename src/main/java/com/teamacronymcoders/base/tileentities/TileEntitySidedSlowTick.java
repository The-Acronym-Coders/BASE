package com.teamacronymcoders.base.tileentities;

import net.minecraft.util.ITickable;

public abstract class TileEntitySidedSlowTick extends TileEntitySidedBase implements ITickable {
    private int tickRate = 20; // Default to once a second
    private int ticks = 0;

    public TileEntitySidedSlowTick() {

    }

    public TileEntitySidedSlowTick(int tickRate) {
        this.tickRate = tickRate;
    }

    @Override
    public void update() {
        if (ticks == tickRate) {
            updateTile();
            ticks = 0;
        } else
            ticks++;
    }

    public void updateTile() {
        // NO-OP
    }
}
