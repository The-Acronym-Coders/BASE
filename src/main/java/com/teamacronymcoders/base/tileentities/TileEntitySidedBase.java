package com.teamacronymcoders.base.tileentities;

import com.teamacronymcoders.base.blocks.properties.SideType;
import com.teamacronymcoders.base.client.IBlockOverlayText;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;

public abstract class TileEntitySidedBase extends TileEntityBase implements IBlockOverlayText {
    private SideType[] sideTypes;
    private boolean isColorBlindActive;

    public TileEntitySidedBase() {
        super();
        sideTypes = new SideType[6];
        Arrays.fill(sideTypes, SideType.NONE);
        isColorBlindActive = false; // TODO
    }

    public void toggleSide(int side) {
        setSideType(side, this.getSideValue(side).next());
        updateBlock();
    }

    public void updateBlock() {
        IBlockState state = world.getBlockState(getPos());
        world.notifyBlockUpdate(pos,state, state,3);
        world.notifyNeighborsOfStateChange(pos, state.getBlock(), true);
        world.addBlockEvent(getPos(), this.getBlockType(), 0, 0);
    }

    public void setSideType(int side, SideType sideType) {
        this.sideTypes[side] = sideType;
    }

    public SideType getSideValue(int side) {
        return this.sideTypes[side];
    }

    @Override
    public void readFromDisk(NBTTagCompound data) {
        int[] array = data.getIntArray("sideTypes");
        if (this.sideTypes == null) {
            this.sideTypes = new SideType[6];
        }

        for (int i = 0; i < array.length; i++) {
            this.sideTypes[i] = SideType.values()[array[i]];
        }
    }

    @Override
    public NBTTagCompound writeToDisk(NBTTagCompound data) {
        int[] array = new int[6];
        for (int i = 0; i < this.sideTypes.length; i++) {
            array[i] = this.sideTypes[i].ordinal();
        }
        data.setIntArray("sideTypes", array);
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
}
