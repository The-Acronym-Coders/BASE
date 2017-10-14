package com.teamacronymcoders.base.multiblock.rectangular;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;

public enum PartPosition implements IStringSerializable {
    UNKNOWN(null, Type.UNKNOWN, Layer.UNKNOWN), 
    INTERIOR(null, Type.UNKNOWN, Layer.UNKNOWN), 
    FRAME_CORNER_NORTH_EAST_TOP(null, Type.FRAME, Layer.TOP),
    FRAME_CORNER_NORTH_WEST_TOP(null, Type.FRAME, Layer.TOP),
    FRAME_CORNER_SOUTH_EAST_TOP(null, Type.FRAME, Layer.TOP),
    FRAME_CORNER_SOUTH_WEST_TOP(null, Type.FRAME, Layer.TOP),
    FRAME_CORNER_NORTH_EAST_BOTTOM(null, Type.FRAME, Layer.BOTTOM),
    FRAME_CORNER_NORTH_WEST_BOTTOM(null, Type.FRAME, Layer.BOTTOM),
    FRAME_CORNER_SOUTH_EAST_BOTTOM(null, Type.FRAME, Layer.BOTTOM),
    FRAME_CORNER_SOUTH_WEST_BOTTOM(null, Type.FRAME, Layer.BOTTOM),
    FRAME_NORTH_TOP(null, Type.FRAME, Layer.TOP), 
    FRAME_EAST_TOP(null, Type.FRAME, Layer.TOP), 
    FRAME_WEST_TOP(null, Type.FRAME, Layer.TOP),
    FRAME_SOUTH_TOP(null, Type.FRAME, Layer.TOP), 
    FRAME_NORTH_BOTTOM(null, Type.FRAME, Layer.BOTTOM), 
    FRAME_EAST_BOTTOM(null, Type.FRAME, Layer.BOTTOM), 
    FRAME_WEST_BOTTOM(null, Type.FRAME, Layer.BOTTOM),
    FRAME_SOUTH_BOTTOM(null, Type.FRAME, Layer.BOTTOM), 
    FRAME_VERTICAL_NORTH_EAST(null, Type.FRAME, Layer.MIDDLE),
    FRAME_VERTICAL_SOUTH_EAST(null, Type.FRAME, Layer.MIDDLE),
    FRAME_VERTICAL_NORTH_WEST(null, Type.FRAME, Layer.MIDDLE),
    FRAME_VERTICAL_SOUTH_WEST(null, Type.FRAME, Layer.MIDDLE),
    TOP_FACE(EnumFacing.UP, Type.FACE, Layer.TOP), 
    BOTTOM_FACE(EnumFacing.DOWN, Type.FACE, Layer.BOTTOM), 
    NORTH_FACE(EnumFacing.NORTH, Type.FACE, Layer.MIDDLE),
    SOUTH_FACE(EnumFacing.SOUTH, Type.FACE, Layer.MIDDLE), 
    EAST_FACE(EnumFacing.EAST, Type.FACE, Layer.MIDDLE), 
    WEST_FACE(EnumFacing.WEST, Type.FACE, Layer.MIDDLE);

    public static enum Type {
        UNKNOWN, INTERIOR, FRAME, FACE
    }
    
    public static enum Layer {
        UNKNOWN, TOP, BOTTOM, MIDDLE 
    }

    public boolean isFace() {
        return this._type == Type.FACE;
    }

    public boolean isFrame() {
        return this._type == Type.FRAME;
    }

    public EnumFacing getFacing() {
        return this._facing;
    }

    public Type getType() {
        return this._type;
    }

    public static PropertyEnum<PartPosition> createProperty(String name) {
        return PropertyEnum.create(name, PartPosition.class);
    }

    @Override
    public String getName() {
        return this.name().toLowerCase();
    }

    PartPosition(EnumFacing facing, Type type, Layer layer) {
        this._facing = facing;
        this._type = type;
        this._layer = layer;
    }

    private EnumFacing _facing;
    private Type _type;
    private Layer _layer;
}
