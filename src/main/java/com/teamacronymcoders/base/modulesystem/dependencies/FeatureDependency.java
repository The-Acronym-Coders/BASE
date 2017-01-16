package com.teamacronymcoders.base.modulesystem.dependencies;

import com.teamacronymcoders.base.featuresystem.FeatureHandler;
import com.teamacronymcoders.base.modulesystem.ModuleHandler;

public class FeatureDependency implements IDependency {
    private String featureName;

    public FeatureDependency(String featureName) {
        this.featureName = featureName;
    }

    @Override
    public boolean isSilent() {
        return true;
    }

    @Override
    public boolean isMet(ModuleHandler moduleHandler) {
        return FeatureHandler.isFeatureAtLeastRequested(this.featureName);
    }
}
