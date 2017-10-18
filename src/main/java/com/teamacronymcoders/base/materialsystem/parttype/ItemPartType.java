package com.teamacronymcoders.base.materialsystem.parttype;

import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.collect.Lists;
import com.teamacronymcoders.base.materialsystem.MaterialUser;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;

import net.minecraft.item.ItemStack;

public class ItemPartType extends PartType {
    
    public static final String BURN_DATA_NAME = "burn";
    
    public ItemPartType() {
        super("Item", setupItemData());
    }
    
    private static List<PartDataPiece> setupItemData() {
        List<PartDataPiece> dataPieces = Lists.newArrayList();
        dataPieces.add(new PartDataPiece(BURN_DATA_NAME, false));
        return dataPieces;
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
