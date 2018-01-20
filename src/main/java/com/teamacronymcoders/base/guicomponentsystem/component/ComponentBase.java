package com.teamacronymcoders.base.guicomponentsystem.component;

public abstract class ComponentBase implements IComponent {
    private final int left;
    private final int top;
    private final int height;
    private final int width;

    public ComponentBase(int left, int top, int height, int width) {
        this.left = left;
        this.top = top;
        this.height = height;
        this.width = width;
    }

    @Override
    public int getLeft() {
        return left;
    }

    @Override
    public int getTop() {
        return top;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
