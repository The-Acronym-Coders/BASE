package com.teamacronymcoders.base.tileentities;

import net.minecraft.util.ITickable;

public abstract class TileEntitySlowTick extends TileEntityBase implements ITickable {
    private int tickRate = 20; // Default to once a second
    private int ticks = 0;

    public TileEntitySlowTick() {

    }

    public TileEntitySlowTick(int tickRate) {
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
