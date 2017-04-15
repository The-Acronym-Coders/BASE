package com.teamacronymcoders.base.registrysystem.entity;

public class SpawnEgg {
    private int primaryColor;
    private int secondaryColor;

    public SpawnEgg(int primary, int secondary) {
        setPrimaryColor(primary);
        setSecondaryColor(secondary);
    }

    public int getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(int primaryColor) {
        this.primaryColor = primaryColor;
    }

    public int getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(int secondaryColor) {
        this.secondaryColor = secondaryColor;
    }
}
