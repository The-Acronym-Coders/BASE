package com.teamacronymcoders.base.materialsystem.compat.mysticalagriculture.item;

import com.blakebr0.mysticalagriculture.items.ItemSeed;
import com.teamacronymcoders.base.items.IHasItemColor;
import com.teamacronymcoders.base.materialsystem.compat.mysticalagriculture.block.BlockMaterialMystericalCrop;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ItemMaterialMysticalSeed extends ItemSeed implements IHasItemColor {
    private final MaterialPart materialPart;

    public ItemMaterialMysticalSeed(MaterialPart materialPart, BlockMaterialMystericalCrop crops, int tier) {
        super(materialPart.getUnlocalizedName() + "_seed", crops, tier);
        this.materialPart = materialPart;
    }

    @Override
    public int getColorFromItemstack(@Nonnull ItemStack stack, int tintIndex) {
        return materialPart.getColor();
    }
}
