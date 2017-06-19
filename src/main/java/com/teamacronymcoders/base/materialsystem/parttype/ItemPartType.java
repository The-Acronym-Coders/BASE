package com.teamacronymcoders.base.materialsystem.parttype;

import com.teamacronymcoders.base.materialsystem.MaterialUser;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ItemPartType extends PartType {
    public ItemPartType() {
        super("Item");
    }

    @Override
    public void setup(@Nonnull MaterialPart materialPart) {
        materialPart.getMaterialUser().registerItemMaterialPart(materialPart);
    }

    @Override
    public ItemStack getItemStack(MaterialPart materialPart) {
        MaterialUser materialUser = materialPart.getMaterialUser();
        return new ItemStack(materialUser.getItemMaterialPart(), 1, materialUser.getMaterialPartId(materialPart));
    }
}
