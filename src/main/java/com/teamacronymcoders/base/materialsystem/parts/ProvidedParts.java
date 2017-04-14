package com.teamacronymcoders.base.materialsystem.parts;

import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.materialsystem.MaterialException;
import com.teamacronymcoders.base.materialsystem.MaterialsSystem;
import com.teamacronymcoders.base.materialsystem.blocks.SubBlockPart;
import com.teamacronymcoders.base.materialsystem.materials.Material;
import com.teamacronymcoders.base.subblocksystem.SubBlockSystem;

public class ProvidedParts {
    public static void initPartsAndTypes() {
        PartType item = new PartType("Item", materialPart ->
                MaterialsSystem.setupItem());
        PartType block = new PartType("Block", materialPart ->
                SubBlockSystem.registerSubBlock(new SubBlockPart(materialPart)));
        MaterialsSystem.registerPartType(item);
        MaterialsSystem.registerPartType(block);

        registerPart(new PartBuilder().setName("Ingot").setPartType(item));
        registerPart(new PartBuilder().setName("Beam").setPartType(item));
        registerPart(new PartBuilder().setName("Gear").setPartType(item));
        registerPart(new PartBuilder().setName("Bolt").setPartType(item));
        registerPart(new PartBuilder().setName("Dust").setPartType(item));
        registerPart(new PartBuilder().setName("Plate").setPartType(item));
        registerPart(new PartBuilder().setName("Nugget").setPartType(item));
        registerPart(new PartBuilder().setName("Storage").setPartType(block));
        registerPart(new PartBuilder().setName("Ore").setPartType(block));
        registerPart(new PartBuilder().setName("Poor Ore").setPartType(block));
    }

    private static void registerPart(PartBuilder partBuilder) {
        try {
            MaterialsSystem.registerPart(partBuilder.createPart());
        } catch (MaterialException e) {
            Base.instance.getLogger().getLogger().error(e);
        }
    }
}
