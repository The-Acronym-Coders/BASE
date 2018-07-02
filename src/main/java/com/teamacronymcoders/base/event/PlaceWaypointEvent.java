package com.teamacronymcoders.base.event;

import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.Event;

public class PlaceWaypointEvent extends Event {
    private BlockPos pos;
    private int color;
    private int dim;
    private String name;
    
    public PlaceWaypointEvent(String name, int dim, BlockPos pos, int color) {
        this.pos = pos;
        this.dim = dim;
        this.name = name;
        this.color = color;
    }
    
    public BlockPos getPos() {
        return pos;
    }
    
    public int getColor() {
        return color;
    }
    
    public int getDim() {
        return dim;
    }
    
    public String getName() {
        return name;
    }
}
