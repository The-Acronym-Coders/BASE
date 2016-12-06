package com.teamacronymcoders.base.modules.materials.items;

import com.teamacronymcoders.base.items.ItemBlockGeneric;
import com.teamacronymcoders.base.modules.materials.blocks.BlockMaterial;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ItemBlockMaterial extends ItemBlockGeneric<BlockMaterial> {
    public ItemBlockMaterial(BlockMaterial block) {
        super(block);
    }

    @Override
    @Nonnull
    public String getItemStackDisplayName(@Nonnull ItemStack stack) {
        return this.getActualBlock().getLocalizedName();
    }
}
