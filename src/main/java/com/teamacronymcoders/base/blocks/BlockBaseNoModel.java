package com.teamacronymcoders.base.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.IModAware;
import com.teamacronymcoders.base.items.IHasOreDict;
import com.teamacronymcoders.base.items.IHasSubItems;
import com.teamacronymcoders.base.items.itemblocks.ItemBlockGeneric;

import net.minecraft.block.Block;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBaseNoModel extends Block implements IHasItemBlock, IHasSubItems, IModAware, IAmBlock, IHasOreDict {
    private IBaseMod mod;
    private boolean creativeTabSet = false;
    private ItemBlock itemBlock;
    private String name;

    public BlockBaseNoModel(Material material) {
        super(material);
        this.setHardness(1F);
    }

    public BlockBaseNoModel(Material mat, String name) {
        this(mat);
        this.name = name;
        this.setUnlocalizedName(name);
    }

    @Override
    public void breakBlock(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
        world.updateComparatorOutputLevel(pos, this);
        super.breakBlock(world, pos, state);
    }

    @Override
    public void onBlockAdded(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
        this.updateState(world, pos, state);
        super.onBlockAdded(world, pos, state);
    }

    @Override
    public void onNeighborChange(@Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull BlockPos neighborPos) {
        this.updateState(world, pos, world.getBlockState(neighborPos));
        super.onNeighborChange(world, pos, neighborPos);
    }

    protected void updateState(IBlockAccess world, BlockPos pos, IBlockState state) {

    }

    @Override
    @Nonnull
    public Block setCreativeTab(@Nonnull CreativeTabs tab) {
        if (!creativeTabSet) {
            super.setCreativeTab(tab);
            this.creativeTabSet = true;
        }
        return this;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean causesSuffocation(@Nonnull IBlockState state) {
        return state.getMaterial().blocksMovement() && state.isFullCube();
    }

    @Override
    @Nonnull
    @SuppressWarnings("deprecation")
    public EnumPushReaction getMobilityFlag(@Nonnull IBlockState state) {
        return state.getMaterial().getMobilityFlag();
    }

    public void setItemBlock(ItemBlock itemBlock) {
        this.itemBlock = itemBlock;
    }

    @Override
    public ItemBlock getItemBlock() {
        return itemBlock == null ? new ItemBlockGeneric<BlockBaseNoModel>(this) : itemBlock;
    }

    @Override
    public IBaseMod getMod() {
        return mod;
    }

    @Override
    public void setMod(IBaseMod mod) {
        this.mod = mod;
    }

    @Override
    public void getSubBlocks(@Nullable CreativeTabs creativeTab, @Nonnull NonNullList<ItemStack> list) {
        list.addAll(this.getAllSubItems(new ArrayList<>()));
    }

    @Override
    public List<ItemStack> getAllSubItems(List<ItemStack> itemStacks) {
        itemStacks.add(new ItemStack(this, 1));
        return itemStacks;
    }

    @Override
    public Item getItem() {
        return this.getItemBlock();
    }

    @Override
    public Block getBlock() {
        return this;
    }

    public String getName() {
        return name;
    }

    @Override
    public Map<ItemStack, String> getOreDictNames(Map<ItemStack, String> names) {
        return names;
    }
}
