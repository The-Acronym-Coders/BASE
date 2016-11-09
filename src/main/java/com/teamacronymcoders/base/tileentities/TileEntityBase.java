package com.teamacronymcoders.base.tileentities;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.util.Platform;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nullable;

/**
 * @author SkySom
 */
public abstract class TileEntityBase extends TileEntity {
    protected IBaseMod mod;

    public TileEntityBase() {
        this.mod = Platform.getCurrentMod();
    }

	/* Orginally inspired by ZeroCore */

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.readFromDisk(data);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        this.writeToDisk(data);
        return super.writeToNBT(data);
    }

    protected void readFromDisk(NBTTagCompound data) {
        this.mod.getLogger().devInfo("Read from Disk");
    }

    ;

    protected NBTTagCompound writeToDisk(NBTTagCompound data) {
        this.mod.getLogger().devInfo("Written to Disk");
        return data;
    }

    ;

    @Override
    public void handleUpdateTag(NBTTagCompound data) {
        super.readFromNBT(data);
        this.readFromUpdatePacket(data);
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        NBTTagCompound data = super.getUpdateTag();
        this.writeToUpdatePacket(data);
        return super.getUpdateTag();
    }

    @Override
    public final void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        this.readFromUpdatePacket(packet.getNbtCompound());
        super.onDataPacket(net, packet);
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound data = new NBTTagCompound();
        this.writeToUpdatePacket(data);
        return new SPacketUpdateTileEntity(this.getPos(), 0, data);
    }

    protected void readFromUpdatePacket(NBTTagCompound data) {
        // this.mod.getLogger().devInfo("Read from Packet");
    }

    ;

    protected NBTTagCompound writeToUpdatePacket(NBTTagCompound data) {
        // this.mod.getLogger().devInfo("Written to Packet");
        return data;
    }

    ;

    public void sendBlockUpdate() {
        if (!worldObj.isRemote)
            // this.worldObj.notifyBlockOfStateChange(this.getPos(), worldObj.getBlockState(pos).getBlock());
            this.worldObj.notifyBlockUpdate(getPos(), worldObj.getBlockState(pos), worldObj.getBlockState(pos), 3);
    }

}
