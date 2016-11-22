package com.teamacronymcoders.base.guisystem.network;

import com.teamacronymcoders.base.guisystem.IHasGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketHandlerOpenGui implements IMessageHandler<PacketOpenGui, IMessage> {
    @Override
    public IMessage onMessage(PacketOpenGui message, MessageContext ctx) {
        Minecraft mc = Minecraft.getMinecraft();
        IHasGui hasGui = message.getTarget().deserialize(mc.thePlayer, mc.theWorld, message.getContext());
        if(hasGui != null) {
            Gui gui = hasGui.getGui(mc.thePlayer, mc.theWorld, message.getContext());
            if(gui != null) {
                FMLCommonHandler.instance().showGuiScreen(gui);
            }
        }
        return null;
    }
}
