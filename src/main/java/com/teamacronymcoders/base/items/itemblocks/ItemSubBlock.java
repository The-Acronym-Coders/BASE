package com.teamacronymcoders.base.items.itemblocks;

import com.teamacronymcoders.base.client.models.IHasModel;
import com.teamacronymcoders.base.items.itemblocks.ItemBlockGeneric;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemSubBlock<BLOCK extends Block & IHasModel> extends ItemBlockGeneric<BLOCK> {
    String[] names;

    public ItemSubBlock(BLOCK block, String[] names) {
        super(block);
        this.names = names;
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int metadata) {
        return metadata;
    }

    @Override
    @Nonnull
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName() + "." + names[stack.getItemDamage()];
    }

    @Override
    public List<ItemStack> getAllSubItems(List<ItemStack> itemStacks) {
        return this.getActualBlock().getAllSubItems(itemStacks);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public List<ModelResourceLocation> getModelResourceLocations(List<ModelResourceLocation> models) {
        return this.getActualBlock().getModelResourceLocations(models);
    }
}
