package com.teamacronymcoders.base.materialsystem.compat.tinkers;

import com.teamacronymcoders.base.materialsystem.MaterialException;
import com.teamacronymcoders.base.materialsystem.MaterialSystem;
import com.teamacronymcoders.base.materialsystem.compat.IMaterialCompat;
import com.teamacronymcoders.base.materialsystem.compat.MaterialCompat;
import com.teamacronymcoders.base.materialsystem.compat.tinkers.TinkersPartType;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.materialsystem.parts.Part;
import com.teamacronymcoders.base.materialsystem.parts.PartBuilder;
import com.teamacronymcoders.base.materialsystem.parttype.PartType;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import slimeknights.tconstruct.library.materials.Material;

@MaterialCompat("tconstruct")
public class TinkersMaterialCompat implements IMaterialCompat {
    private MaterialSystem materialSystem;

    @Override
    public void doCompat(MaterialSystem materialSystem) throws MaterialException {
        this.materialSystem = materialSystem;
        PartType partType = new TinkersPartType(materialSystem.getMod());
        materialSystem.registerPartType(partType);
        Part tinkers = new PartBuilder(materialSystem).setName("Tinkers").setPartType(partType).build();
        materialSystem.registerPart(tinkers);
    }
}
