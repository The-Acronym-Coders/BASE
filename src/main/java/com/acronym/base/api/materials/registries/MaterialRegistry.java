package com.acronym.base.api.materials.registries;

import com.acronym.base.api.materials.Material;
import org.apache.commons.lang3.tuple.MutablePair;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Jared on 6/27/2016.
 */
public class MaterialRegistry {

    private static Map<MutablePair<String, Integer>, Material> materials = new LinkedHashMap<>();
    private static int lastID = 0;

    public static boolean addMaterial(String key, Material material) {
        if (getMaterials().keySet().contains(key)) {
            return false;
        }

        getMaterials().put(new MutablePair<>(key, lastID++), material);
        return true;
    }

    public static boolean removeMaterial(String key) {
        if (!getMaterials().keySet().contains(key)) {
            return false;
        }

        getMaterials().remove(key);
        return true;
    }

    public static boolean isRegistered(String key) {
        return getMaterials().keySet().contains(key);
    }

    public static Map<MutablePair<String, Integer>, Material> getMaterials() {
        return materials;
    }

    public static Material getFromID(int id) {
        for (Map.Entry<MutablePair<String, Integer>, Material> ent : getMaterials().entrySet()) {
            if (ent.getKey().getRight() == id) {
                return ent.getValue();
            }
        }
        return null;
    }
}
