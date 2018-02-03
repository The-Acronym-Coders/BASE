package com.teamacronymcoders.base.items.itemblocks;

import com.teamacronymcoders.base.client.models.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemSubBlock<BLOCK extends Block & IHasModel> extends ItemBlockGeneric<BLOCK> {
    private String[] names;

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
