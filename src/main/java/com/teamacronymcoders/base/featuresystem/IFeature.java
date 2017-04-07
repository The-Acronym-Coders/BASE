package com.teamacronymcoders.base.featuresystem;

public interface IFeature {
    void activate();

    default void cleanUp() {

    }

    boolean isActive();
}
