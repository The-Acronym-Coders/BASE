package com.teamacronymcoders.base.materialsystem.blocks;

import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.subblocksystem.blocks.SubBlockBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.Map;

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
    public void setOreDict(Map<ItemStack, String> oreDict) {
        materialPart.setOreDict(oreDict);
    }

    @Override
    public ResourceLocation getTextureLocation() {
        return materialPart.getTextureLocation();
    }
}
