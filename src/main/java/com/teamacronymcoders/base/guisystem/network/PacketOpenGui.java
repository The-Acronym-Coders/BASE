package com.teamacronymcoders.base.guisystem.network;

import com.teamacronymcoders.base.BaseMods;
import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.guisystem.target.GuiTargetBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PacketOpenGui implements IMessage {
    private GuiTargetBase guiTarget;
    private NBTTagCompound context;
    private IBaseMod mod;

    public PacketOpenGui(IBaseMod mod, GuiTargetBase guiTarget, NBTTagCompound context) {
        this.mod = mod;
        this.guiTarget = guiTarget;
        this.context = context;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.mod = BaseMods.getBaseMod(ByteBufUtils.readUTF8String(buf));
        String guiTargetName = ByteBufUtils.readUTF8String(buf);
        this.guiTarget = mod.getGuiHandler().getGuiTarget(guiTargetName);
        this.context = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, mod.getID());
        ByteBufUtils.writeUTF8String(buf, guiTarget.getName());
        ByteBufUtils.writeTag(buf, guiTarget.serialize(new NBTTagCompound()));
        ByteBufUtils.writeTag(buf, context);
    }

    public GuiTargetBase getTarget() {
        return this.guiTarget;
    }

    public NBTTagCompound getContext() {
        return this.context;
    }
}
