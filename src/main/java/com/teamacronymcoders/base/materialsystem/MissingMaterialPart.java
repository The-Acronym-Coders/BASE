package com.teamacronymcoders.base.materialsystem;

import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.materialsystem.parts.PartType;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class MissingMaterialPart extends MaterialPart {
    public MissingMaterialPart() {
        super(null, null);
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
    public ResourceLocation getRegistryName() {
        return new ResourceLocation("missing", "material_part");
    }

    @Override
    public boolean matchesPartType(PartType partType) {
        return false;
    }
}
