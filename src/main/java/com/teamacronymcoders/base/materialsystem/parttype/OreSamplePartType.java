package com.teamacronymcoders.base.materialsystem.parttype;

import com.teamacronymcoders.base.materialsystem.MaterialUser;
import com.teamacronymcoders.base.materialsystem.blocks.SubBlockOreSamplePart;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;

import javax.annotation.Nonnull;

public class OreSamplePartType extends BlockPartType {
    public OreSamplePartType() {
        super("Ore Sample");
    }

    @Override
    public void setup(@Nonnull MaterialPart materialPart, @Nonnull MaterialUser materialUser) {
        registerSubBlock(materialPart, new SubBlockOreSamplePart(materialPart));
    }
}
