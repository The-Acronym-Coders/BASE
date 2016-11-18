package com.teamacronymcoders.base.guisystem.network;

import com.teamacronymcoders.base.guisystem.target.GuiTarget;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PacketOpenGui implements IMessage {
    private NBTTagCompound guiTarget;
    private NBTTagCompound context;

    public PacketOpenGui(GuiTarget guiTarget, NBTTagCompound context) {
        this.guiTarget = guiTarget.serialize(new NBTTagCompound());
        this.context = context;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.guiTarget = ByteBufUtils.readTag(buf);
        this.context = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, guiTarget);
        ByteBufUtils.writeTag(buf, context);
    }
}
