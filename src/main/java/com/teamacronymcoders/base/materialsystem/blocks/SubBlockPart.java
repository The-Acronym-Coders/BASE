package com.teamacronymcoders.base.materialsystem.blocks;

import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPartData;
import com.teamacronymcoders.base.subblocksystem.blocks.SubBlockBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SubBlockPart extends SubBlockBase {
    private MaterialPart materialPart;
    private CreativeTabs creativeTabs;

    private int hardness = 5;
    private int resistance = 30;
    private int harvestLevel = 1;
    private String harvestTool = "pickaxe";

    public SubBlockPart(MaterialPart materialPart, CreativeTabs creativeTab) {
        super(materialPart.getUnlocalizedName());
        this.materialPart = materialPart;
        MaterialPartData data = this.materialPart.getData();
        hardness = setField(data, "hardness", hardness);
        resistance = setField(data, "resistance", resistance);
        harvestLevel = setField(data, "harvestLevel", harvestLevel);

        if (data.containsDataPiece("harvestTool")) {
            harvestTool = data.getDataPiece("harvestTool");
        }
        this.creativeTabs = creativeTab;
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
    public String getOreDict() {
        return this.materialPart.getOreDictString();
    }

    @Nullable
    @Override
    public CreativeTabs getCreativeTab() {
        return creativeTabs;
    }

    @Override
    public ResourceLocation getTextureLocation() {
        return materialPart.getTextureLocation();
    }

    public MaterialPart getMaterialPart() {
        return materialPart;
    }
}
