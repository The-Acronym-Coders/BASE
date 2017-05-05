package com.teamacronymcoders.base.materialsystem.blocks;

import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class FluidMaterial extends Fluid {
    private MaterialPart materialPart;
    private boolean vaporize = false;

    public FluidMaterial(MaterialPart materialPart, ResourceLocation texture) {
        super(materialPart.getUnlocalizedName(), texture, new ResourceLocation(texture.getResourceDomain(),
                texture.getResourcePath() + "_flowing"));
        this.materialPart = materialPart;
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
}
