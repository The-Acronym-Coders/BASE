package com.acronym.base.api.materials;

import org.apache.commons.lang3.tuple.MutablePair;

import java.util.LinkedHashMap;
import java.util.Map;

//TODO why the fuck is stuff like this not documented at all!?!?!?!

public class MaterialRegistry {

    private static Map<MutablePair<String, Integer>, MaterialType> MATERIALS = new LinkedHashMap<>();
    private static int lastID = 0;

    public static boolean registerMaterial(String key, MaterialType materialType) {
        if (getMaterials().keySet().contains(key)) return false;

        getMaterials().put(new MutablePair<>(key, lastID++), materialType);
        return true;
    }

    public static boolean unregisterMaterial(String key) {
        MutablePair<String, Integer> pair = new MutablePair<>(key, getIDFromName(key));
        if (!getMaterials().keySet().contains(pair)) {
            return false;
        }
        getMaterials().remove(pair);
        return true;
    }

    public static boolean isRegistered(String key) {
        return getMaterials().keySet().contains(key);
    }

    public static Map<MutablePair<String, Integer>, MaterialType> getMaterials() {
        return MATERIALS;
    }

    public static MaterialType getFromID(int id) {
        for (Map.Entry<MutablePair<String, Integer>, MaterialType> ent : getMaterials().entrySet()) {
            if (ent.getKey().getRight() == id) {
                return ent.getValue();
            }
        }
        return null;
    }

    public static MaterialType getFromName(String name) {
        for (Map.Entry<MutablePair<String, Integer>, MaterialType> ent : getMaterials().entrySet()) {
            if (ent.getKey().getLeft().equalsIgnoreCase(name)) {
                return ent.getValue();
            }
        }
        return null;
    }

    public static int getIDFromName(String name) {
        for (Map.Entry<MutablePair<String, Integer>, MaterialType> ent : getMaterials().entrySet()) {
            if (ent.getKey().getLeft().equalsIgnoreCase(name)) {
                return ent.getKey().getRight();
            }
        }
        return -1;
    }

    public static String getNameFromID(int id) {
        for (Map.Entry<MutablePair<String, Integer>, MaterialType> ent : getMaterials().entrySet()) {
            if (ent.getKey().getRight() == id) {
                return ent.getKey().getLeft();
            }
        }
        return null;
    }
}