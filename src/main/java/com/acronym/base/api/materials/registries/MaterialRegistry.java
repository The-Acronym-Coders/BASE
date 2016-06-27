package com.acronym.base.api.materials.registries;

import com.acronym.base.api.materials.Material;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jared on 6/27/2016.
 */
public class MaterialRegistry {

    private static Map<String, Material> materials = new HashMap<>();

    public static boolean addMaterial(String key, Material material) {
        if (getMaterials().keySet().contains(key)) {
            return false;
        }

        getMaterials().put(key, material);
        return true;
    }

    public static boolean removeMaterial(String key) {
        if (!getMaterials().keySet().contains(key)){
            return false;
        }

        getMaterials().remove(key);
        return true;
    }

    public static boolean isRegistered(String key) {
        return getMaterials().keySet().contains(key);
    }

    public static Map<String, Material> getMaterials() {
        return materials;
    }
}
