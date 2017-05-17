package com.teamacronymcoders.base.materialsystem.parttype;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;

import javax.annotation.Nonnull;

public class ItemPartType extends PartType {
    public ItemPartType(IBaseMod mod) {
        super("Item", mod);
    }

    public void setup(@Nonnull MaterialPart materialPart) {
        this.getMaterialSystem().setupItem(materialPart);
    }
}
