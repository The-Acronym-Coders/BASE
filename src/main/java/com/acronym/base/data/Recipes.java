package com.acronym.base.data;

import com.acronym.base.api.materials.Resource;
import com.acronym.base.api.materials.registries.MaterialRegistry;

/**
 * Created by Jared on 6/27/2016.
 */
public class Recipes {

    public static void preInit() {

    }

    public static void init() {

    }

    public static void postInit() {
        MaterialRegistry.addMaterial(new Resource("copper"));

    }
}
