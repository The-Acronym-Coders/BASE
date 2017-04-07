package com.teamacronymcoders.base.materialsystem.parts;

import com.teamacronymcoders.base.materialsystem.MaterialsSystem;
import com.teamacronymcoders.base.materialsystem.blocks.SubBlockPart;
import com.teamacronymcoders.base.subblocksystem.SubBlockSystem;

public class ProvidedParts {
    public static final PartType ITEM = new PartType("Item");
    public static final PartType BLOCK = new PartType("Block", materialPart ->
            SubBlockSystem.registerSubBlock(new SubBlockPart(materialPart)));
    public static final PartType ORE = new PartType("ORE", materialPart -> {
    });

    public static final Part INGOT = new Part("Ingot", ITEM);
    public static final Part BEAM = new Part("Beam", ITEM);
    public static final Part GEAR = new Part("Gear", ITEM);
    public static final Part BOLT = new Part("Bolt", ITEM);
    public static final Part DUST = new Part("Dust", ITEM);
    public static final Part PLATE = new Part("Plate", ITEM);
    public static final Part NUGGET = new Part("Nugget", ITEM);
    public static final Part STORAGE = new Part("Storage", BLOCK);
    public static final Part ORE_BLOCK = new Part("Ore", ORE);

    public static void initPartTypes() {
        MaterialsSystem.registerPartType(ITEM);
        MaterialsSystem.registerPartType(BLOCK);
        MaterialsSystem.registerPartType(ORE);
    }

    public static void initParts() {
        MaterialsSystem.registerPart(INGOT);
        MaterialsSystem.registerPart(BEAM);
        MaterialsSystem.registerPart(GEAR);
        MaterialsSystem.registerPart(BOLT);
        MaterialsSystem.registerPart(DUST);
        MaterialsSystem.registerPart(PLATE);
        MaterialsSystem.registerPart(NUGGET);

        MaterialsSystem.registerPart(STORAGE);
        MaterialsSystem.registerPart(ORE_BLOCK);
    }


}
