package com.teamacronymcoders.base.materialsystem.blocks;

import com.teamacronymcoders.base.blocks.BlockFluidBase;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPartData;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class BlockMaterialFluid extends BlockFluidBase {
    public BlockMaterialFluid(MaterialPart materialPart) {
        super(materialPart.getUnlocalizedName(), createFluid(materialPart), Material.LAVA);
    }

    private static Fluid createFluid(MaterialPart materialPart) {
        ResourceLocation still = materialPart.getTextureLocation();
        Fluid fluid = new Fluid("fluid_" + materialPart.getUnlocalizedName(), still,
                new ResourceLocation(still.getResourceDomain(), still.getResourcePath() + "_flowing")) {
            public int getColor() {
                return materialPart.getColor();
            }
        };
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
        return fluid;
    }
}
