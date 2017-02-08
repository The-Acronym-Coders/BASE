package com.teamacronymcoders.base.materialsystem.blocks;

import com.teamacronymcoders.base.materialsystem.MaterialPart;
import com.teamacronymcoders.base.subblocksystem.blocks.SubBlockBase;
import net.minecraft.util.ResourceLocation;

public class SubBlockPart extends SubBlockBase {
    private MaterialPart materialPart;

    public SubBlockPart(MaterialPart materialPart) {
        super(materialPart.getName());
        this.materialPart = materialPart;
    }

    public String getLocalizedName() {
        return this.materialPart.getName();
    }

    @Override
    public int getColor() {
        return materialPart.getColor();
    }

    @Override
    public ResourceLocation getTextureLocation() {
        return materialPart.getTextureLocation();
    }
}
