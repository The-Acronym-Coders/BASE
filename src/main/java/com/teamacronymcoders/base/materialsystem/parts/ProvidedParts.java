package com.teamacronymcoders.base.materialsystem.parts;

import com.google.common.collect.Lists;
import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.materialsystem.MaterialException;
import com.teamacronymcoders.base.materialsystem.MaterialSystem;
import com.teamacronymcoders.base.materialsystem.blocks.SubBlockPart;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.subblocksystem.SubBlockSystem;

import java.util.List;

public class ProvidedParts {
    public static void initPartsAndTypes(IBaseMod mod) {
        PartType item = new PartType("Item", MaterialSystem::setupItem);
        PartType block = new PartType("Block", materialPart ->
                mod.getSubBlockSystem().registerSubBlock(new SubBlockPart(materialPart)));
        PartType ore = new PartType("Ore", ProvidedParts::createOreSubBlocks);
        MaterialSystem.registerPartType(item);
        MaterialSystem.registerPartType(block);
        MaterialSystem.registerPartType(ore);

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
            MaterialSystem.registerPart(partBuilder.createPart());
        } catch (MaterialException e) {
            Base.instance.getLogger().getLogger().error(e);
        }
    }

    private static void createOreSubBlocks(MaterialPart materialPart) {
        Object variants = materialPart.getData().getDataPiece("variants");
        if (variants instanceof String) {

        } else {

        }
    }


}
