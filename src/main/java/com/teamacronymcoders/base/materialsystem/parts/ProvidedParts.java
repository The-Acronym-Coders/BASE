package com.teamacronymcoders.base.materialsystem.parts;

import com.google.common.collect.Lists;
import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.materialsystem.MaterialException;
import com.teamacronymcoders.base.materialsystem.MaterialsSystem;
import com.teamacronymcoders.base.materialsystem.blocks.SubBlockPart;
import com.teamacronymcoders.base.materialsystem.materials.Material;
import com.teamacronymcoders.base.subblocksystem.SubBlockSystem;

import java.util.List;

public class ProvidedParts {


    public static void initPartsAndTypes() {
        PartType item = new PartType("Item", MaterialsSystem::setupItem);
        PartType block = new PartType("Block", materialPart ->
                SubBlockSystem.registerSubBlock(new SubBlockPart(materialPart)));
        PartType ore = new PartType("Ore", materialPart ->
                SubBlockSystem.registerSubBlock(new SubBlockPart(materialPart)));
        MaterialsSystem.registerPartType(item);
        MaterialsSystem.registerPartType(block);
        MaterialsSystem.registerPartType(ore);

        registerPart(new PartBuilder().setName("Ingot").setPartType(item));
        registerPart(new PartBuilder().setName("Beam").setPartType(item));
        registerPart(new PartBuilder().setName("Gear").setPartType(item));
        registerPart(new PartBuilder().setName("Bolt").setPartType(item));
        registerPart(new PartBuilder().setName("Dust").setPartType(item));
        registerPart(new PartBuilder().setName("Plate").setPartType(item));
        registerPart(new PartBuilder().setName("Nugget").setPartType(item));


        List<PartDataPiece> blockDataPieces = Lists.newArrayList();
        blockDataPieces.add(new PartDataPiece("hardness", false));
        blockDataPieces.add(new PartDataPiece("resistance", false));
        blockDataPieces.add(new PartDataPiece("harvestLevel", false));
        blockDataPieces.add(new PartDataPiece("harvestTool", false));
        registerPart(new PartBuilder().setName("Storage").setPartType(block).setData(blockDataPieces));

        List<PartDataPiece> oreDataPieces = Lists.newArrayList();
        oreDataPieces.addAll(blockDataPieces);
        oreDataPieces.add(new PartDataPiece("variants", false));
        oreDataPieces.add(new PartDataPiece("dropType", false));
        registerPart(new PartBuilder().setName("Ore").setPartType(ore).setData(oreDataPieces));
        registerPart(new PartBuilder().setName("Poor Ore").setPartType(ore).setData(oreDataPieces));
        registerPart(new PartBuilder().setName("Dense Ore").setPartType(ore).setData(oreDataPieces));
    }

    private static void registerPart(PartBuilder partBuilder) {
        try {
            MaterialsSystem.registerPart(partBuilder.createPart());
        } catch (MaterialException e) {
            Base.instance.getLogger().getLogger().error(e);
        }
    }
}
