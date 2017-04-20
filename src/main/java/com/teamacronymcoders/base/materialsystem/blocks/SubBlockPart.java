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
        super(materialPart.getName());
        this.materialPart = materialPart;
        MaterialPartData data = this.materialPart.getData();
        if (data.getDataPiece("hardness") instanceof Number) {
            hardness = ((Number) data.getDataPiece("hardness")).intValue();
        }
        if (data.getDataPiece("resistance") instanceof Number) {
            resistance = ((Number) data.getDataPiece("resistance")).intValue();
        }
        if (data.getDataPiece("harvestLevel") instanceof Number) {
            harvestLevel = ((Number) data.getDataPiece("harvestLevel")).intValue();
        }
        if (data.getDataPiece("harvestTool") instanceof String) {
            harvestTool = ((String) data.getDataPiece("harvestTool"));
        }
    }

    public String getUnLocalizedName() {
        return this.materialPart.getName();
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
}
