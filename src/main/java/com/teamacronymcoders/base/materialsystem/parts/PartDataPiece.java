package com.teamacronymcoders.base.materialsystem.parts;

public class PartDataPiece {
    private String name;
    private boolean required;

    public PartDataPiece(String name, boolean required) {
        this.name = name;
        this.required = required;
    }

    public String getName() {
        return name;
    }

    public boolean isRequired() {
        return required;
    }
}
