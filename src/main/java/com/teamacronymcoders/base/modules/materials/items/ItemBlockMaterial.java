package com.teamacronymcoders.base.modules.materials.items;

import com.teamacronymcoders.base.api.materials.MaterialType;
import com.teamacronymcoders.base.items.ItemBlockGeneric;
import com.teamacronymcoders.base.modules.materials.blocks.BlockMaterial;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemBlockMaterial extends ItemBlockGeneric<BlockMaterial> {
    private MaterialType.EnumPartType partType;

    public ItemBlockMaterial(BlockMaterial block) {
        super(block);
        this.partType = block.getPartType();
    }

    @Override
    @Nonnull
    public String getItemStackDisplayName(@Nonnull ItemStack stack) {
        return this.getActualBlock().getLocalizedName();
    }

    @Override
    public List<String> getModelNames(List<String> modelNames) {
        modelNames.add(partType.getLowerCaseName());
        return modelNames;
    }

}
