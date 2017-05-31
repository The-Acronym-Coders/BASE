package com.teamacronymcoders.base.materialsystem.parttype;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.materialsystem.blocks.SubBlockPart;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.subblocksystem.SubBlockSystem;

import javax.annotation.Nonnull;

public class BlockPartType extends PartType {
    private SubBlockSystem subBlockSystem;

    public BlockPartType(IBaseMod mod) {
        this("storage", mod);
    }

    public BlockPartType(String name, IBaseMod mod) {
        super(name, mod);
        this.subBlockSystem = mod.getSubBlockSystem();
    }

    public void setup(@Nonnull MaterialPart materialPart) {
        this.getSubBlockSystem().registerSubBlock(new SubBlockPart(materialPart, this.getMaterialSystem().materialCreativeTab));
    }

    protected SubBlockSystem getSubBlockSystem() {
        return this.subBlockSystem;
    }
}
