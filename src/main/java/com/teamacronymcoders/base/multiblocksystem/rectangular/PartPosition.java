package com.teamacronymcoders.base.multiblocksystem.rectangular;

import net.minecraft.state.EnumProperty;
import net.minecraft.util.Direction;
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
    TOP_FACE(Direction.UP, Type.FACE, Layer.TOP),
    BOTTOM_FACE(Direction.DOWN, Type.FACE, Layer.BOTTOM),
    NORTH_FACE(Direction.NORTH, Type.FACE, Layer.MIDDLE),
    SOUTH_FACE(Direction.SOUTH, Type.FACE, Layer.MIDDLE),
    EAST_FACE(Direction.EAST, Type.FACE, Layer.MIDDLE),
    WEST_FACE(Direction.WEST, Type.FACE, Layer.MIDDLE);

    private Direction _facing;
    private Type type;
    private Layer layer;

    PartPosition(Direction facing, Type type, Layer layer) {
        this._facing = facing;
        this.type = type;
        this.layer = layer;
    }

    public static EnumProperty<PartPosition> createProperty(String name) {
        return EnumProperty.create(name, PartPosition.class);
    }

    public boolean isFace() {
        return this.type == Type.FACE;
    }

    public boolean isFrame() {
        return this.type == Type.FRAME;
    }

    public Direction getFacing() {
        return this._facing;
    }

    public Type getType() {
        return this.type;
    }

    @Override
    public String getName() {
        return this.name().toLowerCase();
    }

    public enum Type {
        UNKNOWN, INTERIOR, FRAME, FACE
    }
    public static enum Layer {
        UNKNOWN, TOP, BOTTOM, MIDDLE
    }
}
