package com.teamacronymcoders.base.subblocksystem.items;

import com.google.common.collect.Lists;
import com.teamacronymcoders.base.items.IHasItemColor;
import com.teamacronymcoders.base.items.itemblocks.ItemBlockGeneric;
import com.teamacronymcoders.base.subblocksystem.blocks.BlockSubBlockHolder;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemBlockSubBlockHolder extends ItemBlockGeneric<BlockSubBlockHolder> implements IHasItemColor {
    public ItemBlockSubBlockHolder(BlockSubBlockHolder block) {
        super(block);
    }

    @Override
    public int getColorFromItemstack(@Nonnull ItemStack stack, int tintIndex) {
        return tintIndex == 0 ? this.getActualBlock().getSubBlock(stack.getMetadata()).getColor() : -1;
    }

    @Override
    @Nonnull
    public String getItemStackDisplayName(@Nonnull ItemStack stack) {
        return this.getActualBlock().getSubBlock(stack.getMetadata()).getLocalizedName();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public List<ModelResourceLocation> getModelResourceLocations(List<ModelResourceLocation> models) {
        this.getActualBlock().getSubBlocks().forEach((meta, subBlock) ->
                models.add(new ModelResourceLocation(subBlock.getTextureLocation(), "inventory")));
        return models;
    }

    @Override
    @Nonnull
    public CreativeTabs[] getCreativeTabs() {
        List<CreativeTabs> creativeTabsList = Lists.newArrayList();
        this.getActualBlock().getSubBlocks().values().forEach(subBlock -> {
            if (subBlock.getCreativeTab() != null && !creativeTabsList.contains(subBlock.getCreativeTab())) {
                creativeTabsList.add(subBlock.getCreativeTab());
            }
        });
        return creativeTabsList.toArray(new CreativeTabs[creativeTabsList.size()]);
    }
}
