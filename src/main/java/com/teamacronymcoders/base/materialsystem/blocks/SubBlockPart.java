package com.teamacronymcoders.base.materialsystem.blocks;

import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPartData;
import com.teamacronymcoders.base.subblocksystem.blocks.SubBlockBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.Map;

public class SubBlockPart extends SubBlockBase {
    private MaterialPart materialPart;

    private int hardness = 5;
    private int resistance = 30;
    private int harvestLevel = 1;
    private String harvestTool = "pickaxe";

    public SubBlockPart(MaterialPart materialPart) {
        super(materialPart.getUnlocalizedName());
        this.materialPart = materialPart;
        MaterialPartData data = this.materialPart.getData();
        hardness = setField(data, "hardness", hardness);
        resistance = setField(data, "resistance", resistance);
        harvestLevel = setField(data, "harvestLevel", harvestLevel);

        if (data.containsDataPiece("harvestTool")) {
            harvestTool = data.getDataPiece("harvestTool");
        }
    }

    private int setField(MaterialPartData data, String fieldName, int currentLevel) {
        if (data.containsDataPiece(fieldName)) {
            currentLevel = Integer.parseInt(data.getDataPiece(fieldName));
        }

        return currentLevel;
    }

    @Override
    public String getLocalizedName() {
        return this.materialPart.getLocalizedName();
    }

    @Override
    public String getUnLocalizedName() {
        return this.materialPart.getUnlocalizedName();
    }

    @Override
    public int getColor() {
        return materialPart.getColor();
    }

    @Override
    public int getHardness() {
        return hardness;
    }

    @Override
    public int getResistance() {
        return resistance;
    }

    @Override
    public int getHarvestLevel() {
        return harvestLevel;
    }

    @Nonnull
    @Override
    public String getHarvestTool() {
        return harvestTool;
    }

    @Override
    public void setOreDict(Map<ItemStack, String> oreDict) {
        materialPart.setOreDict(oreDict);
    }

    @Override
    public ResourceLocation getTextureLocation() {
        return materialPart.getTextureLocation();
    }

    public MaterialPart getMaterialPart() {
        return materialPart;
    }
}
