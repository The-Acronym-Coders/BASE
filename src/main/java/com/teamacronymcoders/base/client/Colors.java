package com.teamacronymcoders.base.client;

import com.teamacronymcoders.base.blocks.IHasBlockColor;
import com.teamacronymcoders.base.items.IHasItemColor;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;

public class Colors {
    public static void registerItemColor(Object object, IHasItemColor itemColor) {
        ItemColors itemColors = Minecraft.getMinecraft().getItemColors();
        if (object instanceof Item) {
            itemColors.registerItemColorHandler(itemColor::getColorFromItemstack, (Item) object);
        } else if (object instanceof Block) {
            itemColors.registerItemColorHandler(itemColor::getColorFromItemstack, (Block) object);
        }
    }

    public static void registerBlockColor(IHasBlockColor blockColor) {
        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(blockColor::colorMultiplier, blockColor.getBlock());
    }
}
