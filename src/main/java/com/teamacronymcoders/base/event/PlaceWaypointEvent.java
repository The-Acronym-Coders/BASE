package com.teamacronymcoders.base.event;

import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.Event;

public class PlaceWaypointEvent extends Event {
    private BlockPos pos;
    private int dimension;
    private String id;
    private String modId;
    private String name;
    
    public PlaceWaypointEvent(String modId, String id, String name, int dimension, BlockPos pos) {
        this.pos = pos;
        this.dimension = dimension;
        this.id = id;
        this.modId = modId;
        this.name = name;
    }
    
    public BlockPos getPos() {
        return pos;
    }
    
    public int getDimension() {
        return dimension;
    }
    
    public String getId() {
        return id;
    }
    
    public String getModId() {
        return modId;
    }
    
    public String getName() {
        return name;
    }
}
