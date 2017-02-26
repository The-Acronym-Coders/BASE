package com.teamacronymcoders.base.guisystem;

import com.teamacronymcoders.base.util.ItemStackUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public enum GuiCarrier {
    ENTITY {
        @Override
        public IHasGui getHasGUI(EntityPlayer player, World world, BlockPos blockPos) {
            IHasGui hasGui = null;
            Entity entity = world.getEntityByID(blockPos.getX());
            if (entity instanceof IHasGui) {
                hasGui = (IHasGui) entity;
            }
            return hasGui;
        }
    },
    BLOCK {
        @Override
        public IHasGui getHasGUI(EntityPlayer player, World world, BlockPos blockPos) {
            IHasGui hasGui = null;

            Block block = world.getBlockState(blockPos).getBlock();
            if (block instanceof IHasGui) {
                hasGui = (IHasGui) block;
            }

            return hasGui;
        }
    },
    TileEntity {
        @Override
        public IHasGui getHasGUI(EntityPlayer player, World world, BlockPos blockPos) {
            IHasGui hasGui = null;

            TileEntity tileEntity = world.getTileEntity(blockPos);
            if (tileEntity instanceof IHasGui) {
                hasGui = (IHasGui) tileEntity;
            }

            return hasGui;
        }
    },
    ITEMSTACK {
        @Override
        public IHasGui getHasGUI(EntityPlayer player, World world, BlockPos blockPos) {
            IHasGui hasGui = null;
            ItemStack itemStack = player.getHeldItem(EnumHand.values()[blockPos.getX()]);
            if (ItemStackUtils.isItemInstanceOf(itemStack, IHasGui.class)) {
                hasGui = (IHasGui) itemStack.getItem();
            }
            return hasGui;
        }
    };

    public abstract IHasGui getHasGUI(EntityPlayer player, World world, BlockPos blockPos);
}
