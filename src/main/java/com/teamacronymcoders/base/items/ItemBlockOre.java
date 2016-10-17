package com.teamacronymcoders.base.items;

import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.api.materials.MaterialType;
import com.teamacronymcoders.base.blocks.BlockOre;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * Created by Jared.
 */
public class ItemBlockOre extends ItemBlock{

    public ItemBlockOre(Block block) {
        super(block);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        MaterialType mat = ((BlockOre)block).materialType;
        if (mat != null)
            return String.format("%s %s", mat.getLocalizedName(), Base.languageHelper.none("base.part.ore"));

        return ChatFormatting.RED + Base.languageHelper.error("null_part");
    }
}
