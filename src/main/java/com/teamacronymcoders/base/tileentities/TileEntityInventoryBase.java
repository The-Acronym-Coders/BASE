package com.teamacronymcoders.base.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileEntityInventoryBase extends TileEntityBase {
    private ItemStackHandler inventory;

    public TileEntityInventoryBase(int inventorySize) {
        this(new ItemStackHandler(inventorySize));
    }

    public TileEntityInventoryBase(ItemStackHandler inventory) {
        this.inventory = inventory;
    }


    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        data.setTag("Items", inventory.serializeNBT());
        return super.writeToNBT(data);
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        inventory.deserializeNBT(data.getCompoundTag("Items"));
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Nonnull
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return (T) inventory;
        }
        return super.getCapability(capability, facing);
    }
}
