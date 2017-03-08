package com.teamacronymcoders.base.featuresystem;

import java.util.*;

public class FeatureHandler {
    private static Map<String, IFeature> features = new HashMap<>();
    private static Map<String, List<String>> modsRequesting = new HashMap<>();
    private static List<String> requests = new ArrayList<>();

    public static void registerFeature(String name, IFeature feature) {
        name = name.toLowerCase(Locale.US);
        features.put(name, feature);
        attemptActivate(name);
    }

    public static void requestFeature(String name, String modid) {
        name = name.toLowerCase(Locale.US);
        if (features.containsKey(name)) {
            activate(name);
        } else {
            if (!requests.contains(name)) {
                requests.add(name);
            }
        }
        modsRequesting.computeIfAbsent(name, value -> new ArrayList<>());
        modsRequesting.get(name).add(modid);
    }

    public static boolean didModRequestFeature(String name, String modid) {
        name = name.toLowerCase(Locale.US);
        return modsRequesting.get(name) != null && modsRequesting.get(name).contains(modid);
    }

    public static boolean isFeatureActivated(String name) {
        name = name.toLowerCase(Locale.US);
        IFeature feature = features.get(name);
        return feature != null && feature.isActive();
    }

    private static void attemptActivate(String name) {
        if (requests.contains(name)) {
            activate(name);
        }
    }

    private static void activate(String name) {
        IFeature feature = features.get(name);
        if (!feature.isActive()) {
            feature.activate();
        }
    }
}
