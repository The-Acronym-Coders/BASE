package com.teamacronymcoders.base.materialsystem.compat.tinkers;

import com.teamacronymcoders.base.materialsystem.MaterialException;
import com.teamacronymcoders.base.materialsystem.MaterialUser;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.materialsystem.parttype.PartType;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
//import slimeknights.tconstruct.library.materials.Material;

import javax.annotation.Nonnull;

public class TinkersPartType extends PartType {
    public TinkersPartType() {
        super("Tinkers");
    }

    public void setup(@Nonnull MaterialPart materialPart) {
        this.createTinkers(materialPart);
    }

    private void createTinkers(MaterialPart materialPart) {
        MaterialUser materialUser = materialPart.getMaterialUser();
        if (!FluidRegistry.isFluidRegistered(materialPart.getMaterial().getUnlocalizedName())) {
            try {
                materialUser.registerPartsForMaterial(materialPart.getMaterial(), "fluid");
            } catch (MaterialException e) {
                materialUser.getMod().getLogger().error("Could not register fluid for " + materialPart.getLocalizedName());
            }
        }
        Fluid fluid = FluidRegistry.getFluid(materialPart.getMaterial().getUnlocalizedName());
        if (fluid != null) {
            //Material tinkerMaterial = new Material(materialPart.getUnlocalizedName(), materialPart.getColor());
            //tinkerMaterial.addCommonItems(materialPart.getMaterial().getUnlocalizedName());
        } else {
            materialUser.getMod().getLogger().error("Could not create Fluid for Tinker's Compat");
        }
    }
}
