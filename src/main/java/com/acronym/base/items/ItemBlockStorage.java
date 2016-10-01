package com.acronym.base.items;

import com.acronym.base.Base;
import com.acronym.base.api.materials.MaterialType;
import com.acronym.base.blocks.sets.wood.BlockStorage;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * Created by Jared.
 */
public class ItemBlockStorage extends ItemBlock{

    public ItemBlockStorage(Block block) {
        super(block);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        MaterialType mat = ((BlockStorage)block).materialType;
        if (mat != null)
            return String.format("%s %s", mat.getLocalizedName(), Base.languageHelper.none("base.part.storage"));

        return ChatFormatting.RED + Base.languageHelper.error("null_part");
    }
}
