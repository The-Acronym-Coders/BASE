package com.teamacronymcoders.base.subblocksystem.blocks;

import com.teamacronymcoders.base.client.models.generator.generatedmodel.IGeneratedModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.translation.I18n;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public interface ISubBlock {
    String getName();

    String getUnLocalizedName();

    default String getLocalizedName() {
        //noinspection deprecation
        return I18n.translateToLocal(this.getUnLocalizedName());
    }

    ResourceLocation getTextureLocation();

    int getColor();

    int getHardness();

    int getResistance();

    int getHarvestLevel();

    @Nonnull
    String getHarvestTool();

    void getDrops(int fortune, List<ItemStack> itemStacks);

    void setRecipes(List<IRecipe> recipes);

    String getOreDict();

    @Nullable
    CreativeTabs getCreativeTab();

    @Nullable
    IGeneratedModel getGeneratedModel();

    Material getMaterial();

    @Nonnull
    ItemStack getItemStack();
    
    @Nonnull
    IBlockState getBlockState();
    
    void setMeta(int x);
    
    void setBlock(Block block);

    boolean isSideSolid();

    boolean isTopSolid();

    boolean canPlaceTorchOnTop();

    BlockFaceShape getBlockFaceShape();

    AxisAlignedBB getBoundingBox();

    boolean isFullCube();

    boolean isOpaqueCube();

    boolean isPassable();

    boolean isFullBlock();

    int getLightOpacity();

    boolean canSilkHarvest();
}
