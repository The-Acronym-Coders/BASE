package com.teamacronymcoders.base.tileentities;

import com.teamacronymcoders.base.blocks.properties.SideType;
import com.teamacronymcoders.base.client.IBlockOverlayText;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;

public abstract class TileEntitySidedBase<CAP> extends TileEntityBase implements IBlockOverlayText {
    private SideType[] sideTypes;
    private boolean isColorBlindActive;

    public TileEntitySidedBase() {
        super();
        sideTypes = new SideType[6];
        Arrays.fill(sideTypes, SideType.NONE);
        isColorBlindActive = false; // TODO
    }

    @Override
    public void onLoad() {
        super.onLoad();
    }

    public void toggleSide(int side) {
        setSideType(side, this.getSideValue(side).next());
        updateBlock();
    }

    public void updateBlock() {
        IBlockState state = world.getBlockState(getPos());
        world.notifyBlockUpdate(pos, state, state, 3);
        world.notifyNeighborsOfStateChange(pos, state.getBlock(), true);
        if (!world.isRemote) {
            world.addBlockEvent(getPos(), this.getBlockType(), 0, 0);
        }
    }

    public void setSideType(int side, SideType sideType) {
        this.sideTypes[side] = sideType;
    }

    public SideType getSideValue(int side) {
        return this.sideTypes[side];
    }

    @Override
    public void readFromDisk(NBTTagCompound data) {
        setSideTypesFromNBT(data);
    }

    @Override
    public NBTTagCompound writeToDisk(NBTTagCompound data) {
        setSideTypesToNBT(data);
        return data;
    }

    @Override
    public boolean receiveClientEvent(int id, int arg) {
        if (id == 0) {
            this.getWorld().notifyNeighborsOfStateChange(this.getPos(), getWorld().getBlockState(pos).getBlock(), true);
            return true;
        }
        return false;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == this.getCapabilityType() || super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == this.getCapabilityType()) {
            if (facing != null) {
                SideType sideType = this.getSideValue(facing.getIndex());
                if (sideType == SideType.INPUT) {
                    return this.castCapability(this.getInputCapability());
                } else if (sideType == SideType.OUTPUT) {
                    return this.castCapability(this.getOutputCapability());
                }
            } else {
                return this.castCapability(this.getInternalCapability());
            }
        }
        return super.getCapability(capability, facing);
    }

    public abstract Capability<?> getCapabilityType();

    public abstract <T> T castCapability(CAP cap);

    public abstract CAP getInternalCapability();

    public abstract CAP getOutputCapability();

    public abstract CAP getInputCapability();

    @Override
    @SideOnly(Side.CLIENT)
    public String[] getOverlayText(EntityPlayer player, RayTraceResult rayTrace, boolean tool) {
        if (tool && isColorBlindActive) {
            SideType facing = sideTypes[rayTrace.sideHit.ordinal()];
            SideType opposite = sideTypes[rayTrace.sideHit.getOpposite().ordinal()];
            return new String[]{
                    I18n.format("base.block_side.facing") + ": "
                            + I18n.format("base.sidetype." + facing.name().toLowerCase()),
                    I18n.format("base.block_side.opposite") + ": "
                            + I18n.format("base.sidetype." + opposite.name().toLowerCase())};
        }

        return null;
    }

    public void setSideTypesToNBT(NBTTagCompound nbt) {
        int[] array = new int[6];
        for (int i = 0; i < this.sideTypes.length; i++) {
            array[i] = this.sideTypes[i].ordinal();
        }
        nbt.setIntArray("sideTypes", array);
    }

    public void setSideTypesFromNBT(NBTTagCompound nbt) {
        int[] array = nbt.getIntArray("sideTypes");
        if (this.sideTypes == null) {
            this.sideTypes = new SideType[6];
        }

        for (int i = 0; i < array.length; i++) {
            this.sideTypes[i] = SideType.values()[array[i]];
        }
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        setSideTypesToNBT(nbttagcompound);
        return new SPacketUpdateTileEntity(this.pos, 3, nbttagcompound);
    }

    @Nonnull
    @Override
    public NBTTagCompound getUpdateTag() {
        NBTTagCompound nbt = super.writeToNBT(new NBTTagCompound());
        setSideTypesToNBT(nbt);
        return nbt;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        setSideTypesFromNBT(pkt.getNbtCompound());
    }
}
