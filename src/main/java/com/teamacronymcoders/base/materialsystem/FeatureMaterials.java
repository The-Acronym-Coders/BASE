package com.teamacronymcoders.base.materialsystem;

import com.teamacronymcoders.base.featuresystem.IFeature;

public class FeatureMaterials implements IFeature {
    boolean active;

    @Override
    public void activate() {
        MaterialsSystem.setup();
        this.active = true;
    }

    @Override
    public void cleanUp() {
        MaterialsSystem.finishUp();
    }

    @Override
    public boolean isActive() {
        return active;
    }
}
