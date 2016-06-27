package com.acronym.base.data;

import com.acronym.base.api.materials.registries.MaterialRegistry;

/**
 * Created by Jared on 6/27/2016.
 */
public class Recipes {

    public static void preInit() {
        MaterialRegistry.addMaterial("Copper", Materials.copper);
        MaterialRegistry.addMaterial("Tin", Materials.tin);
    }

    public static void init() {

    }

    public static void postInit() {

    }
}
