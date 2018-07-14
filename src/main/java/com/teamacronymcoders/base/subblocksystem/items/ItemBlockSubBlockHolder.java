package com.teamacronymcoders.base.subblocksystem.items;

import com.google.common.collect.Lists;
import com.teamacronymcoders.base.client.models.IHasModel;
import com.teamacronymcoders.base.items.IHasItemColor;
import com.teamacronymcoders.base.items.IHasItemMeshDefinition;
import com.teamacronymcoders.base.items.itemblocks.ItemBlockGeneric;
import com.teamacronymcoders.base.items.itemblocks.ItemBlockModel;
import com.teamacronymcoders.base.subblocksystem.blocks.BlockSubBlockHolder;
import com.teamacronymcoders.base.subblocksystem.blocks.ISubBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

public class ItemBlockSubBlockHolder extends ItemBlockGeneric<BlockSubBlockHolder> implements IHasItemColor, IHasItemMeshDefinition {
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

    @Override
    @Nonnull
    public CreativeTabs[] getCreativeTabs() {
        List<CreativeTabs> creativeTabsList = Lists.newArrayList();
        this.getActualBlock().getSubBlocks().values().forEach(subBlock -> {
            if (subBlock.getCreativeTab() != null && !creativeTabsList.contains(subBlock.getCreativeTab())) {
                creativeTabsList.add(subBlock.getCreativeTab());
            }
        });
        return creativeTabsList.toArray(new CreativeTabs[0]);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing,
                                      float hitX, float hitY, float hitZ) {
        IBlockState iblockstate = world.getBlockState(pos);
        Block block = iblockstate.getBlock();

        if (!block.isReplaceable(world, pos)) {
            pos = pos.offset(facing);
        }

        ItemStack itemstack = player.getHeldItem(hand);

        int i = this.getMetadata(itemstack.getMetadata());
        if (!itemstack.isEmpty() && player.canPlayerEdit(pos, facing, itemstack) &&
                this.getActualBlock().getSubBlock(i).canPlaceBlockAt(world, pos) &&
                world.mayPlace(this.block, pos, false, facing, null)) {

            IBlockState placementState = this.block.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, i, player, hand);

            if (placeBlockAt(itemstack, player, world, pos, facing, hitX, hitY, hitZ, placementState)) {
                placementState = world.getBlockState(pos);
                SoundType soundtype = placementState.getBlock().getSoundType(placementState, world, pos, player);
                world.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                itemstack.shrink(1);
            }

            return EnumActionResult.SUCCESS;
        } else {
            return EnumActionResult.FAIL;
        }
    }

    @Override
    public ResourceLocation getResourceLocation(ItemStack itemStack) {
        return this.getActualBlock().getSubBlock(itemStack.getMetadata()).getTextureLocation();
    }

    @Override
    public List<ResourceLocation> getAllVariants() {
        return this.getActualBlock().getSubBlocks().values().stream()
                .map(ISubBlock::getTextureLocation)
                .collect(Collectors.toList());
    }
}
