package com.teamacronymcoders.base.items.itemblocks;

import com.teamacronymcoders.base.client.models.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemBlockModel<T extends Block & IHasModel> extends ItemBlockGeneric<T> implements IHasModel{
    public ItemBlockModel(T block) {
        super(block);
    }

    @Override
    public Item getItem() {
        return this;
    }

    @Override
    public List<ItemStack> getAllSubItems(List<ItemStack> itemStacks) {
        return this.getActualBlock().getAllSubItems(itemStacks);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public List<ModelResourceLocation> getModelResourceLocations(List<ModelResourceLocation> models) {
        return this.getActualBlock().getModelResourceLocations(models);
    }
}
