package com.teamacronymcoders.base.materialsystem.parttype;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.materialsystem.blocks.SubBlockOrePart;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.IGeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.GeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.ModelType;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPartData;
import com.teamacronymcoders.base.util.files.templates.TemplateFile;
import com.teamacronymcoders.base.util.files.templates.TemplateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;

public class OrePartType extends BlockPartType {
    public OrePartType(IBaseMod mod) {
        super("Ore", mod);
    }

    public void setup(@Nonnull MaterialPart materialPart) {
        this.createOreSubBlocks(materialPart);
    }

    private void createOreSubBlocks(MaterialPart materialPart) {
        MaterialPartData data = materialPart.getData();
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
                MaterialPart variantMaterialPart = new MaterialPart(this.getMaterialSystem(), materialPart.getMaterial(), materialPart.getPart(), variantName);
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
                this.getSubBlockSystem().registerSubBlock(new SubBlockOrePart(variantMaterialPart, new ResourceLocation(variantName), this.getMaterialSystem()));
                this.getMaterialSystem().registerMaterialPart(variantMaterialPart);
            }
        } else {
            this.getSubBlockSystem().registerSubBlock(new SubBlockOrePart(materialPart, new ResourceLocation("stone"), this.getMaterialSystem()));
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
