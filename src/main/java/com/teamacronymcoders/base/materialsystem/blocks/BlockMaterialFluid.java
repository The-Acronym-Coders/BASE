package com.teamacronymcoders.base.materialsystem.blocks;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.teamacronymcoders.base.blocks.BlockFluidBase;
import com.teamacronymcoders.base.blocks.IHasBlockStateMapper;
import com.teamacronymcoders.base.client.models.generator.IHasGeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.GeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.IGeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.ModelType;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.materialsystem.partdata.MaterialPartData;
import com.teamacronymcoders.base.materialsystem.parts.Part;
import com.teamacronymcoders.base.util.files.templates.TemplateFile;
import com.teamacronymcoders.base.util.files.templates.TemplateManager;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import java.util.List;
import java.util.Map;

public class BlockMaterialFluid extends BlockFluidBase implements IHasBlockStateMapper, IHasGeneratedModel {
    private MaterialPart materialPart;

    public BlockMaterialFluid(MaterialPart materialPart) {
        super(materialPart.getUnlocalizedName(), createFluid(materialPart), Material.LAVA);
        this.materialPart = materialPart;
    }

    private static Fluid createFluid(MaterialPart materialPart) {
        Part part = materialPart.getPart();
        ResourceLocation texture = new ResourceLocation(part.getOwnerId(), "fluids/" + part.getShortUnlocalizedName());
        FluidMaterial fluid = new FluidMaterial(materialPart, texture);
        MaterialPartData data = materialPart.getData();
        if (data.containsDataPiece("density")) {
            fluid.setDensity(Integer.parseInt(data.getDataPiece("density")));
        }
        if (data.containsDataPiece("viscosity")) {
            fluid.setViscosity(Integer.parseInt(data.getDataPiece("viscosity")));
        }
        if (data.containsDataPiece("temperature")) {
            fluid.setTemperature(Integer.parseInt(data.getDataPiece("temperature")));
        }
        if (data.containsDataPiece("vaporize")) {
            fluid.setVaporize(Boolean.parseBoolean(data.getDataPiece("vaporize")));
        }
        FluidRegistry.registerFluid(fluid);
        FluidRegistry.addBucketForFluid(fluid);
        return fluid;
    }

    @Override
    public Block getBlock() {
        return this;
    }

    @Override
    public ResourceLocation getResourceLocation(IBlockState blockState) {
        return new ResourceLocation(this.materialPart.getMaterialUser().getId(), "/materials/" +
                this.materialPart.getUnlocalizedName());
    }

    @Override
    public String getVariant(IBlockState blockState) {
        return "normal";
    }

    @Override
    public List<IGeneratedModel> getGeneratedModels() {
        TemplateFile templateFile = TemplateManager.getTemplateFile("fluid");
        Map<String, String> replacements = Maps.newHashMap();
        replacements.put("FLUID", this.fluidName);
        templateFile.replaceContents(replacements);

        return Lists.newArrayList(new GeneratedModel("materials/" + this.materialPart.getUnlocalizedName(),
                ModelType.BLOCKSTATE, templateFile.getFileContents()));

    }
}
