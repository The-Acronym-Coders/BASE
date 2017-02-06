package com.teamacronymcoders.base.materialsystem.parts;

import com.teamacronymcoders.base.materialsystem.MaterialsSystem;

public class ProvidedParts {
    public static void init() {
        MaterialsSystem.registerPart(new Part("Ingot", PartType.ITEM));
        MaterialsSystem.registerPart(new Part("Beam", PartType.ITEM));
        MaterialsSystem.registerPart(new Part("Gear", PartType.ITEM));
        MaterialsSystem.registerPart(new Part("Bolt", PartType.ITEM));
        MaterialsSystem.registerPart(new Part("Dust", PartType.ITEM));
        MaterialsSystem.registerPart(new Part("Plate", PartType.ITEM));
        MaterialsSystem.registerPart(new Part("Nugget", PartType.ITEM));

        MaterialsSystem.registerPart(new Part("Storage", PartType.BLOCK));

        MaterialsSystem.registerPart(new Part("Ore", PartType.ORE));
    }
}
