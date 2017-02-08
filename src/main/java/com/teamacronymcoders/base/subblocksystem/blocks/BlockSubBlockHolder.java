package com.teamacronymcoders.base.subblocksystem.blocks;

import com.teamacronymcoders.base.blocks.BlockBaseNoModel;
import com.teamacronymcoders.base.blocks.IHasBlockColor;
import com.teamacronymcoders.base.blocks.IHasBlockStateMapper;
import com.teamacronymcoders.base.items.ItemBlockGeneric;
import com.teamacronymcoders.base.subblocksystem.SubBlockSystem;
import com.teamacronymcoders.base.subblocksystem.items.ItemBlockSubBlockHolder;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class BlockSubBlockHolder extends BlockBaseNoModel implements IHasBlockStateMapper, IHasBlockColor {
    private static final PropertyInteger SUB_BLOCK_NUMBER = PropertyInteger.create("sub_block_number", 0, 15);
    private Map<Integer, ISubBlock> subBlocks;

    public BlockSubBlockHolder(int number, Map<Integer, ISubBlock> subBlocks) {
        super(Material.IRON, "sub_block_holder_" + number);
        this.setItemBlock(new ItemBlockGeneric<>(this));
        this.subBlocks = subBlocks;
        for(int x = 0; x < 16; x++) {
            this.getSubBlocks().computeIfAbsent(x, value -> SubBlockSystem.MISSING_SUB_BLOCK);
        }
        this.setItemBlock(new ItemBlockSubBlockHolder(this));
    }


    @Override
    public List<ItemStack> getAllSubItems(List<ItemStack> itemStacks) {
        for(int x = 0; x < 16; x++) {
            if(getSubBlocks().get(x) != SubBlockSystem.MISSING_SUB_BLOCK) {
                itemStacks.add(new ItemStack(this, 1, x));
            }
        }
        return itemStacks;
    }

    @Override
    public ResourceLocation getResourceLocation(IBlockState blockState) {
        return this.getSubBlock(blockState.getValue(SUB_BLOCK_NUMBER)).getTextureLocation();
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
    public int getMetaFromState(IBlockState state) {
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

    public Map<Integer, ISubBlock> getSubBlocks() {
        return subBlocks;
    }
}
