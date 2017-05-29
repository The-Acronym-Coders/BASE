package com.teamacronymcoders.base.materialsystem.blocks;

import com.google.common.collect.Maps;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.GeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.IGeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.ModelType;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPartData;
import com.teamacronymcoders.base.subblocksystem.blocks.SubBlockBase;
import com.teamacronymcoders.base.util.files.templates.TemplateFile;
import com.teamacronymcoders.base.util.files.templates.TemplateManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

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
        ResourceLocation location = materialPart.getTextureLocation();
        return new ResourceLocation(location.getResourceDomain(), this.getModelPrefix() + this.getUnLocalizedName());
    }

    @Override
    public IGeneratedModel getGeneratedModel() {
        TemplateFile templateFile = TemplateManager.getTemplateFile("block");
        Map<String, String> replacements = Maps.newHashMap();

        replacements.put("texture", "base:blocks/block");
        templateFile.replaceContents(replacements);

        return new GeneratedModel(this.getModelPrefix() + this.getUnLocalizedName(), ModelType.BLOCKSTATE,
                templateFile.getFileContents());
    }

    @Override
    protected String getModelPrefix() {
        return "materials/";
    }

    public MaterialPart getMaterialPart() {
        return materialPart;
    }
}
