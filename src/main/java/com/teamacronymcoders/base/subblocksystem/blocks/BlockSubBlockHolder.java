package com.teamacronymcoders.base.subblocksystem.blocks;

import com.teamacronymcoders.base.blocks.*;
import com.teamacronymcoders.base.client.models.generator.IHasGeneratedModel;
import com.teamacronymcoders.base.client.models.generator.generatedmodel.IGeneratedModel;
import com.teamacronymcoders.base.items.*;
import com.teamacronymcoders.base.subblocksystem.SubBlockSystem;
import com.teamacronymcoders.base.subblocksystem.items.ItemBlockSubBlockHolder;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;

import javax.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

public class BlockSubBlockHolder extends BlockBaseNoModel implements IHasBlockStateMapper, IHasBlockColor, IHasOreDict, IHasRecipe, IHasGeneratedModel {
    public static final PropertyInteger SUB_BLOCK_NUMBER = PropertyInteger.create("sub_block_number", 0, 15);
    private Map<Integer, ISubBlock> subBlocks;

    public BlockSubBlockHolder(int number, Map<Integer, ISubBlock> subBlocks) {
        super(Material.IRON, "sub_block_holder_" + number);
        this.setItemBlock(new ItemBlockSubBlockHolder(this));
        this.subBlocks = subBlocks;
        for (int x = 0; x < 16; x++) {
            this.getSubBlocks().putIfAbsent(x, SubBlockSystem.MISSING_SUB_BLOCK);
            this.getSubBlock(x).setBlock(this);
            this.getSubBlock(x).setMeta(x);
        }
    }

    @Override
    @Nonnull
    @SuppressWarnings("deprecation")
    public Material getMaterial(@Nonnull IBlockState state) {
        return this.getSubBlock(state).getMaterial();
    }

    @Override
    public List<ItemStack> getAllSubItems(List<ItemStack> itemStacks) {
        for (int x = 0; x < 16; x++) {
            if (getSubBlocks().get(x) != SubBlockSystem.MISSING_SUB_BLOCK) {
                itemStacks.add(new ItemStack(this.getItemBlock(), 1, x));
            }
        }
        return itemStacks;
    }

    @Override
    public void getSubBlocks(@Nullable CreativeTabs creativeTab, @Nonnull NonNullList<ItemStack> list) {
        for (Map.Entry<Integer, ISubBlock> subBlock : this.getSubBlocks().entrySet()) {
            if (subBlock.getValue().getCreativeTab() == creativeTab || creativeTab == CreativeTabs.SEARCH) {
                list.add(new ItemStack(this, 1, subBlock.getKey()));
            }
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public float getBlockHardness(@Nonnull IBlockState blockState, @Nonnull World worldIn, @Nonnull BlockPos pos) {
        return this.getSubBlock(blockState.getValue(SUB_BLOCK_NUMBER)).getHardness();
    }

    @Override
    @SuppressWarnings("deprecation")
    public float getExplosionResistance(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull Entity exploder, @Nonnull Explosion explosion) {
        return this.getSubBlock(world.getBlockState(pos).getValue(SUB_BLOCK_NUMBER)).getResistance();
    }

    @Override
    @Nonnull
    public String getHarvestTool(@Nonnull IBlockState state) {
        return this.getSubBlock(state.getValue(SUB_BLOCK_NUMBER)).getHarvestTool();
    }

    @Override
    public int getHarvestLevel(@Nonnull IBlockState state) {
        return this.getSubBlock(state.getValue(SUB_BLOCK_NUMBER)).getHarvestLevel();
    }

    @Override
    @ParametersAreNonnullByDefault
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        this.getSubBlock(state).getDrops(fortune, drops);
    }

    @Override
    public ResourceLocation getResourceLocation(IBlockState blockState) {
        return this.getSubBlock(blockState).getTextureLocation();
    }

    @Override
    @Nonnull
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    public ISubBlock getSubBlock(IBlockState blockState) {
        return this.getSubBlock(blockState.getValue(SUB_BLOCK_NUMBER));
    }

    public ISubBlock getSubBlock(int meta) {
        return getSubBlocks().get(meta);
    }

    @Override
    @Nonnull
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(SUB_BLOCK_NUMBER, meta);
    }

    @Override
    public int getMetaFromState(@Nonnull IBlockState state) {
        return state.getValue(SUB_BLOCK_NUMBER);
    }

    @Override
    @Nonnull
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, SUB_BLOCK_NUMBER);
    }

    @Override
    public int colorMultiplier(IBlockState state, @Nullable IBlockAccess world, @Nullable BlockPos pos, int tintIndex) {
        return this.getSubBlock(state.getValue(SUB_BLOCK_NUMBER)).getColor();
    }

    @Override
    public int damageDropped(@Nonnull IBlockState state) {
        return state.getValue(SUB_BLOCK_NUMBER);
    }

    public Map<Integer, ISubBlock> getSubBlocks() {
        return subBlocks;
    }

    @Nonnull
    @Override
    public Map<ItemStack, String> getOreDictNames(@Nonnull Map<ItemStack, String> names) {
        this.getSubBlocks().forEach((key, value) -> {
            String oreDict = value.getOreDict();
            if (oreDict != null && !oreDict.isEmpty()) {
                names.put(new ItemStack(this.getItemBlock(), 1, key), oreDict);
            }
        });
        return names;
    }

    @Override
    public List<IRecipe> getRecipes(List<IRecipe> recipes) {
        this.getSubBlocks().values().forEach(subBlock -> subBlock.setRecipes(recipes));
        return recipes;
    }

    @Override
    public List<IGeneratedModel> getGeneratedModels() {
        return this.getSubBlocks().values().stream().map(ISubBlock::getGeneratedModel).filter(Objects::nonNull).collect(Collectors.toList());
    }
}
