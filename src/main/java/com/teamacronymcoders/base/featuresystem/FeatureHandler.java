package com.teamacronymcoders.base.featuresystem;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class FeatureHandler {
    private static Map<String, FeatureStatus> features = new HashMap<>();

    public static void requestFeature(String name) {
        name = name.toLowerCase(Locale.ROOT);
        if(!features.containsKey(name)) {
            features.put(name, FeatureStatus.REQUESTED);
        }
    }

    public static void requireFeature(String name) {
        name = name.toLowerCase(Locale.ROOT);
        features.put(name, FeatureStatus.REQUIRED);
    }

    public static FeatureStatus checkFeatureStatus(String name) {
        name = name.toLowerCase(Locale.ROOT);
        return features.get(name);
    }

    public static boolean isFeatureAtLeastRequested(String name) {
        name = name.toLowerCase(Locale.ROOT);
        return checkFeatureStatus(name) != FeatureStatus.NONE;
    }

    public static boolean isFeatureRequired(String name) {
        name = name.toLowerCase(Locale.ROOT);
        return checkFeatureStatus(name) == FeatureStatus.REQUIRED;
    }
}
