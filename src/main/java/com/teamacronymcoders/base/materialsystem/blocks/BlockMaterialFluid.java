package com.teamacronymcoders.base.materialsystem.blocks;

import com.teamacronymcoders.base.blocks.BlockFluidBase;
import com.teamacronymcoders.base.blocks.IHasBlockStateMapper;
import com.teamacronymcoders.base.client.models.generator.IHasGeneratedModel;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.materialsystem.partdata.MaterialPartData;
import com.teamacronymcoders.base.materialsystem.parts.Part;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.FMLLog;

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
        FMLLog.warning(fluid.getName());
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
    public ResourceLocation getResourceLocation(IBlockState blockState) {
        return new ResourceLocation(this.materialPart.getMaterialUser().getId(), "materials/" +
                this.materialPart.getUnlocalizedName());
    }
}
