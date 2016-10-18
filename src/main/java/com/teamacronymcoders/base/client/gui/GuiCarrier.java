package com.teamacronymcoders.base.client.gui;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.util.ItemStackUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
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

        public void openGui(IBaseMod mod, Entity entity, EntityPlayer player, World world) {
            player.openGui(mod, ENTITY.ordinal(), world, entity.getEntityId(), 0, 0);
        }
    },
    BLOCK {
        @Override
        public IHasGui getHasGUI(EntityPlayer player, World world, BlockPos blockPos) {
            IHasGui hasGui = null;

            Block block = world.getBlockState(blockPos).getBlock();
            if (block instanceof IHasGui) {
                hasGui = (IHasGui) block;
            } else {
                TileEntity tileEntity = world.getTileEntity(blockPos);
                if (tileEntity instanceof IHasGui) {
                    hasGui = (IHasGui) tileEntity;
                }
            }

            return hasGui;
        }

        public void openGui(IBaseMod mod, EntityPlayer player, World world, BlockPos blockPos) {
            player.openGui(mod, BLOCK.ordinal(), world, blockPos.getX(), blockPos.getY(), blockPos.getZ());
        }
    },
    ITEMSTACK {
        @Override
        public IHasGui getHasGUI(EntityPlayer player, World world, BlockPos blockPos) {
            IHasGui hasGui = null;
            ItemStack itemStack = player.getActiveItemStack();
            if (ItemStackUtils.isItemInstanceOf(itemStack, IHasGui.class)) {
                hasGui = (IHasGui) itemStack.getItem();
            }
            return hasGui;
        }

        public void openGui(IBaseMod mod, EntityPlayer player, World world, BlockPos blockPos) {
            player.openGui(mod, ITEMSTACK.ordinal(), world, blockPos.getX(), blockPos.getY(), blockPos.getZ());
        }
    };

    public abstract IHasGui getHasGUI(EntityPlayer player, World world, BlockPos blockPos);
}
