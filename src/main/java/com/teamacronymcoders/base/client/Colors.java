package com.teamacronymcoders.base.client;

import com.teamacronymcoders.base.blocks.IHasBlockColor;
import com.teamacronymcoders.base.items.IHasItemColor;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class Colors {
    public static void registerItemColor(Object object, IHasItemColor itemColor) {
        ItemColors itemColors = Minecraft.getInstance().getItemColors();
        if (object instanceof IItemProvider) {
            itemColors.register(itemColor::getColorFromItemStack, (IItemProvider) object);
        }
    }

    public static void registerBlockColor(IHasBlockColor blockColor) {
        Minecraft.getInstance().getBlockColors().register(blockColor::colorMultiplier, blockColor.getBlock());
    }
}
