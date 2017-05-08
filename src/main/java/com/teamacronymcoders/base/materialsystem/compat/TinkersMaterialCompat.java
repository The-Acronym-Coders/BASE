package com.teamacronymcoders.base.materialsystem.compat;

import com.teamacronymcoders.base.materialsystem.MaterialException;
import com.teamacronymcoders.base.materialsystem.MaterialSystem;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.materialsystem.parts.Part;
import com.teamacronymcoders.base.materialsystem.parts.PartBuilder;
import com.teamacronymcoders.base.materialsystem.parts.PartType;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.shared.TinkerFluids;

@MaterialCompat("tconstruct")
public class TinkersMaterialCompat implements IMaterialCompat {
    private MaterialSystem materialSystem;

    @Override
    public void doCompat(MaterialSystem materialSystem) throws MaterialException {
        this.materialSystem = materialSystem;
        PartType partType = new PartType("Tinkers", this::createTinkers);
        materialSystem.registerPartType(partType);
        Part tinkers = new PartBuilder(materialSystem).setName("Tinkers").setPartType(partType).build();
        materialSystem.registerPart(tinkers);
    }

    private void createTinkers(MaterialPart materialPart) {
        if (!FluidRegistry.isFluidRegistered(materialPart.getMaterial().getUnlocalizedName())) {
            try {
                materialSystem.registerPartsForMaterial(materialPart.getMaterial(), "fluid");
            } catch (MaterialException e) {
                materialSystem.getMod().getLogger().error("Could not register fluid for " + materialPart.getLocalizedName());
            }
        }
        Fluid fluid = FluidRegistry.getFluid(materialPart.getMaterial().getUnlocalizedName());
        if (fluid != null) {
            Material tinkerMaterial = new Material(materialPart.getUnlocalizedName(), materialPart.getColor());
            tinkerMaterial.addCommonItems(materialPart.getMaterial().getUnlocalizedName());
        } else {
            materialSystem.getMod().getLogger().error("Could not create Fluid for Tinker's Compat");
        }
    }
}
