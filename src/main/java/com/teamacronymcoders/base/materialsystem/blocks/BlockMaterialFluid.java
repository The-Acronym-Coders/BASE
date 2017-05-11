package com.teamacronymcoders.base.materialsystem.blocks;

import com.teamacronymcoders.base.blocks.BlockFluidBase;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPartData;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class BlockMaterialFluid extends BlockFluidBase {
    public BlockMaterialFluid(MaterialPart materialPart) {
        super(materialPart.getUnlocalizedName(), createFluid(materialPart), Material.LAVA);
    }

    private static Fluid createFluid(MaterialPart materialPart) {
        FluidMaterial fluid = new FluidMaterial(materialPart, materialPart.getTextureLocation());
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
}
