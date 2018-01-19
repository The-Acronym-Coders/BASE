package com.teamacronymcoders.base.materialsystem.blocks;

import com.google.common.collect.Maps;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.GeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.IGeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.ModelType;
import com.teamacronymcoders.base.materialsystem.MaterialSystem;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.materialsystem.parts.Part;
import com.teamacronymcoders.base.util.files.templates.TemplateFile;
import com.teamacronymcoders.base.util.files.templates.TemplateManager;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.Map;

public class SubBlockOreSamplePart extends SubBlockPart {
    public SubBlockOreSamplePart(MaterialPart materialPart) {
        super(materialPart, MaterialSystem.materialCreativeTab);
    }

    @Override
    public IGeneratedModel getGeneratedModel() {
        TemplateFile templateFile = TemplateManager.getTemplateFile("ore_sample_block");
        Map<String, String> replacements = Maps.newHashMap();

        Part part = this.getMaterialPart().getPart();
        replacements.put("texture", String.format("%s:blocks/%s", "minecraft", "gravel"));
        replacements.put("particle", String.format("%s:blocks/%s", "minecraft", "stone"));
        replacements.put("ore", String.format("%s:blocks/%s", part.getOwnerId(), part.getShortUnlocalizedName()));
        templateFile.replaceContents(replacements);

        return new GeneratedModel(this.getModelPrefix() + this.getUnLocalizedName(), ModelType.BLOCKSTATE,
                templateFile.getFileContents());
    }

    @Override
    public boolean isSideSolid() {
        return false;
    }

    @Override
    public boolean isTopSolid() {
        return false;
    }

    @Override
    public boolean canPlaceTorchOnTop() {
        return false;
    }

    @Override
    public BlockFaceShape getBlockFaceShape() {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    public AxisAlignedBB getBoundingBox() {
        return new AxisAlignedBB(0.2F, 0.0F, 0.2F, 0.8F, 0.25F, 0.8F);
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean isPassable() {
        return true;
    }

    @Override
    public boolean isFullBlock() {
        return false;
    }

    @Override
    public int getLightOpacity() {
        return 0;
    }
}

