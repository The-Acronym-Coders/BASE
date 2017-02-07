package com.teamacronymcoders.base.subblocksystem;

import com.teamacronymcoders.base.featuresystem.IFeature;

public class FeatureSubBlocks implements IFeature {
    private boolean active;

    @Override
    public void activate() {
        this.active = true;
    }

    @Override
    public boolean isActive() {
        return this.active;
    }
}
