package com.teamacronymcoders.base.materialsystem.compat.mekanism;

import com.teamacronymcoders.base.materialsystem.MaterialException;
import com.teamacronymcoders.base.materialsystem.MaterialSystem;
import com.teamacronymcoders.base.materialsystem.compat.IMaterialCompat;
import com.teamacronymcoders.base.materialsystem.compat.MaterialCompat;
import com.teamacronymcoders.base.materialsystem.compat.mekanism.parttype.GasPartType;
import com.teamacronymcoders.base.materialsystem.parts.PartBuilder;

@MaterialCompat("mekanism")
public class MekanismCompat implements IMaterialCompat {
    @Override
    public void doCompat() throws MaterialException {
        MaterialSystem.registerPart(new PartBuilder().setName("Ores").setPartType(new GasPartType()).build());
    }
}
