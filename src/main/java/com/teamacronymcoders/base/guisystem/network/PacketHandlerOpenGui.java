package com.teamacronymcoders.base.guisystem.network;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketHandlerOpenGui implements IMessageHandler<PacketOpenGui, IMessage> {
    @Override
    public IMessage onMessage(PacketOpenGui message, MessageContext ctx) {
        //TODO: This packet handler
        return null;
    }
}
