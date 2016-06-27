package com.acronym.base.util;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class TileHelper {
    @Nullable
    public static <T> T getTileEntity(IBlockAccess world, BlockPos blockPos, Class<T> tClass) {
        TileEntity tileEntity = world.getTileEntity(blockPos);
        return !tClass.isInstance(tileEntity) ? null : (T) tileEntity;
    }

    public static void DropItems(TileEntity tileEntity) {
        if (!(tileEntity instanceof IInventory))
            return;

        IInventory inventory = (IInventory) tileEntity;

        if (inventory != null)
            DropItems(tileEntity, 0, inventory.getSizeInventory() - 1);
    }

    public static void DropItems(TileEntity tileEntity, int min, int max) {
        if (!(tileEntity instanceof IInventory)) {
            return;
        }

        IInventory inventory = (IInventory) tileEntity;
        World world = tileEntity.getWorld();
        BlockPos blockPos = tileEntity.getPos();

        for (int i = min; i <= max; i++) {
            ItemStack itemStack = inventory.getStackInSlot(i);

            if (itemStack != null && itemStack.stackSize > 0) {
                Random rand = new Random();

                float dX = rand.nextFloat() * 0.8F + 0.1F;
                float dY = rand.nextFloat() * 0.8F + 0.1F;
                float dZ = rand.nextFloat() * 0.8F + 0.1F;

                EntityItem entityItem = new EntityItem(world, blockPos.getX() + dX, blockPos.getY() + dY, blockPos.getZ() + dZ, itemStack.copy());

                if (itemStack.hasTagCompound()) {
                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) itemStack.getTagCompound().copy());
                }

                float factor = 0.05F;
                entityItem.motionX = rand.nextGaussian() * factor;
                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                entityItem.motionZ = rand.nextGaussian() * factor;
                world.spawnEntityInWorld(entityItem);
                itemStack.stackSize = 0;
                inventory.setInventorySlotContents(i, null);
            }
        }

        inventory.markDirty();
    }
}