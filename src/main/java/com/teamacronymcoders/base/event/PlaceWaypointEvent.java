package com.teamacronymcoders.base.event;

import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.Event;

public class PlaceWaypointEvent extends Event {
    private BlockPos pos;
    private int dim;
    private String name;
    
    public PlaceWaypointEvent(String name, int dim, BlockPos pos) {
        this.pos = pos;
        this.dim = dim;
        this.name = name;
    }
    
    public BlockPos getPos() {
        return pos;
    }
    
    public int getDim() {
        return dim;
    }
    
    public String getName() {
        return name;
    }
}
