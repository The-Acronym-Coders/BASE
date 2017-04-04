package com.teamacronymcoders.base.materialsystem.materials;

import com.teamacronymcoders.base.materialsystem.MaterialsSystem;

public class MaterialFactory {
    private MaterialFactory() {

    }

    public static Material createMaterial(String name) {
        Material material = new Material(name);
        MaterialsSystem.registerMaterial(material);
        return material;
    }
}
