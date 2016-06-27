package com.acronym.base.api.materials.registries;

import com.acronym.base.api.materials.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jared on 6/27/2016.
 */
public class MaterialRegistry {

    private static List<Resource> materials = new ArrayList<>();

    public static boolean addMaterial(Resource resource) {
        if (getMaterials().contains(resource)) {
            return false;
        }

        return getMaterials().add(resource);
    }

    public static boolean removeMaterial(Resource resource) {
        if (!getMaterials().contains(resource)) {
            return false;
        }

        return getMaterials().remove(resource);
    }

    public static boolean isRegistered(Resource resource) {
        return getMaterials().contains(resource);
    }

    public static List<Resource> getMaterials() {
        return materials;
    }
}
