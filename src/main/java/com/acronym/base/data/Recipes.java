package com.acronym.base.data;

import com.acronym.base.api.materials.Material;
import com.acronym.base.api.registries.MaterialRegistry;

import java.lang.reflect.Field;

public class Recipes {

    public static void preInit() {

    }

    public static void init() {
        for (Materials material : Materials.values()) {
            Material mat = material.getMaterial();
            MaterialRegistry.registerMaterial(material.getName().toLowerCase(), mat);
        }
    }

    public static void postInit() {

    }
}
