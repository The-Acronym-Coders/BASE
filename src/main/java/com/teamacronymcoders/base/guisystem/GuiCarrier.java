package com.teamacronymcoders.base.guisystem;

import javax.annotation.Nullable;

import com.teamacronymcoders.base.multiblocksystem.MultiblockTileEntityBase;
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
    TILE_ENTITY {
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
    MULTIBLOCK {
        @Override
        public IHasGui getHasGUI(EntityPlayer player, World world, BlockPos blockPos) {
            IHasGui hasGui = null;

            TileEntity tileEntity = world.getTileEntity(blockPos);
            if (tileEntity instanceof MultiblockTileEntityBase) {
                MultiblockTileEntityBase<?> multiblockTileEntity = (MultiblockTileEntityBase<?>) tileEntity;
                if(multiblockTileEntity.getMultiblockController() instanceof IHasGui) {
                    hasGui = (IHasGui) multiblockTileEntity.getMultiblockController();
                }
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

    @Nullable
    public abstract IHasGui getHasGUI(EntityPlayer player, World world, BlockPos blockPos);
}
