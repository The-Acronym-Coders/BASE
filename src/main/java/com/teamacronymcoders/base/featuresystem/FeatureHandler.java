package com.teamacronymcoders.base.featuresystem;

import java.util.*;

public class FeatureHandler {
    private static Map<String, IFeature> features = new HashMap<>();
    private static List<String> requests = new ArrayList<>();

    public static void registerFeature(String name, IFeature feature) {
        name = name.toLowerCase(Locale.ROOT);
        features.put(name, feature);
        attemptActivate(name);
    }

    public static void requestFeature(String name) {
        name = name.toLowerCase(Locale.ROOT);
        if(features.containsKey(name)) {
            activate(name);
        } else {
            requests.add(name);
        }
    }

    private static void attemptActivate(String name) {
        if(requests.contains(name)) {
            activate(name);
        }
    }

    private static void activate(String name) {
        IFeature feature = features.get(name);
        if(!feature.isActive()) {
            feature.activate();
        }
    }
}
