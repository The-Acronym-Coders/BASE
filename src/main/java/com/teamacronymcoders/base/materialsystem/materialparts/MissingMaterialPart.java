package com.teamacronymcoders.base.materialsystem.materialparts;

import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.materialsystem.MaterialException;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.materialsystem.parts.Part;
import com.teamacronymcoders.base.materialsystem.parts.PartBuilder;
import com.teamacronymcoders.base.materialsystem.parts.PartType;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class MissingMaterialPart extends MaterialPart {
    public MissingMaterialPart() throws MaterialException {
        super(null, new PartBuilder().setName("Missing").setPartType(new PartType("Missing", part -> {})).createPart();
    }

    @Override
    public String getName() {
        return "Missing Part";
    }

    @Override
    public String getLocalizedName() {
        return TextFormatting.RED + Base.languageHelper.error("null_part");
    }

    @Override
    public boolean hasEffect() {
        return false;
    }

    @Override
    public ResourceLocation getTextureLocation() {
        return Blocks.BARRIER.getRegistryName();
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Blocks.BARRIER, 1);
    }

    @Override
    public int getColor() {
        return -1;
    }

    @Override
    public boolean matchesPartType(PartType partType) {
        return false;
    }
}
