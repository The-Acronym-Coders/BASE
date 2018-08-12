package com.teamacronymcoders.base.materialsystem.blocks;

import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import org.apache.logging.log4j.util.Strings;

public class FluidMaterial extends Fluid {
    private MaterialPart materialPart;
    private boolean vaporize = false;

    public FluidMaterial(MaterialPart materialPart, ResourceLocation texture) {
        super(createFluidName(materialPart), texture, new ResourceLocation(texture.getNamespace(), texture.getPath() + "_flowing"));
        this.materialPart = materialPart;
    }

    private static String createFluidName(MaterialPart materialPart) {
        String name = materialPart.getMaterial().getUnlocalizedName();
        if (!Strings.isBlank(materialPart.getPart().getOreDictPrefix())) {
            name += "_" + materialPart.getPart().getOreDictPrefix();
        }
        return name;
    }

    @Override
    public int getColor() {
        return this.materialPart.getColor();
    }

    public void setVaporize(boolean vaporize) {
        this.vaporize = vaporize;
    }

    @Override
    public boolean doesVaporize(FluidStack fluidStack) {
        return this.vaporize;
    }

    @Override
    public String getLocalizedName(FluidStack stack) {
        return materialPart.getLocalizedName();
    }

    @Override
    public String getUnlocalizedName() {
        return materialPart.getPart().getUnlocalizedName();
    }
}
