package com.teamacronymcoders.base.materialsystem.parttype;

import com.google.common.collect.Lists;
import com.teamacronymcoders.base.materialsystem.MaterialUser;
import com.teamacronymcoders.base.materialsystem.blocks.SubBlockOrePart;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPartData;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.List;

public class OrePartType extends BlockPartType {
    public OrePartType() {
        super("Ore", setupOreData());
    }

    private static List<PartDataPiece> setupOreData() {
        List<PartDataPiece> oreDataPieces = Lists.newArrayList();
        oreDataPieces.add(new PartDataPiece("variants", false));
        oreDataPieces.add(new PartDataPiece("dropType", false));
        return oreDataPieces;
    }

    public void setup(@Nonnull MaterialPart materialPart) {
        this.createOreSubBlocks(materialPart);
    }

    private void createOreSubBlocks(MaterialPart materialPart) {
        MaterialPartData data = materialPart.getData();
        MaterialUser materialUser = materialPart.getMaterialUser();
        if (data.containsDataPiece("variants")) {
            String[] variantNames = data.getDataPiece("variants").split(",");
            int[] hardness = getArrayForField(data, "hardness");
            int[] resistance = getArrayForField(data, "resistance");
            int[] harvestLevel = getArrayForField(data, "harvestLevel");
            String[] harvestTool = null;
            String[] drops = null;

            if (data.containsDataPiece("harvestTool")) {
                harvestTool = data.getDataPiece("harvestTool").split(",");
            }

            if (data.containsDataPiece("drops")) {
                drops = data.getDataPiece("drops").split(",");
            }

            for (int i = 0; i < variantNames.length; i++) {
                String variantName = variantNames[i];
                data.addDataValue("variants", variantName);
                MaterialPart variantMaterialPart = new MaterialPart(materialPart.getMaterialUser(),
                        materialPart.getMaterial(), materialPart.getPart(), variantName);
                MaterialPartData variantData = variantMaterialPart.getData();
                trySetData(hardness, i, "hardness", variantData);
                trySetData(resistance, i, "resistance", variantData);
                trySetData(harvestLevel, i, "harvestTool", variantData);
                if (harvestTool != null && harvestTool.length > i) {
                    data.addDataValue("harvestTool", harvestTool[i]);
                }
                if (drops != null && drops.length > i) {
                    data.addDataValue("drops", drops[i]);
                }
                registerSubBlock(materialPart, new SubBlockOrePart(variantMaterialPart,
                        new ResourceLocation(variantName), materialUser));
                materialUser.registerMaterialPart(variantMaterialPart);
            }
        } else {
            registerSubBlock(materialPart, new SubBlockOrePart(materialPart, new ResourceLocation("stone"),
                    materialUser));
        }
    }

    private void trySetData(int[] numbers, int place, String fieldName, MaterialPartData data) {
        if (numbers != null && numbers.length > place) {
            data.addDataValue(fieldName, Integer.toString(numbers[place]));
        }
    }

    private int[] getArrayForField(MaterialPartData data, String fieldName) {
        int[] returned = null;
        if (data.containsDataPiece(fieldName)) {
            String[] stringPieces = data.getDataPiece(fieldName).split(",");
            returned = new int[stringPieces.length];
            for (int i = 0; i < stringPieces.length; i++) {
                returned[i] = Integer.parseInt(stringPieces[i]);
            }
        }
        return returned;
    }
}
