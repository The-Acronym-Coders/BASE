package com.teamacronymcoders.base.materialsystem.compat.mekanism.parttype;

import com.teamacronymcoders.base.materialsystem.MaterialUser;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.materialsystem.parttype.PartType;
import mekanism.api.gas.GasRegistry;
import mekanism.api.gas.OreGas;

import javax.annotation.Nonnull;
import java.util.Locale;

public class GasPartType extends PartType {
    public GasPartType() {
        super("OreGas");
    }

    @Override
    public void setup(@Nonnull MaterialPart materialPart, @Nonnull MaterialUser materialUser) {
        String materialName = materialPart.getMaterial().getOreDictSuffix();
        String materialNameLower = materialName.toLowerCase(Locale.US);
        int color = materialPart.getColor();
        OreGas clean = new OreGas("clean" + materialName, "oregas." + materialNameLower);
        clean.setVisible(false).setTint(color);

        OreGas dirty = new OreGas(materialName, "oregas." + materialName).setCleanGas(clean);
        dirty.setVisible(false).setTint(color);

        GasRegistry.register(clean);
        GasRegistry.register(dirty);
    }
}
