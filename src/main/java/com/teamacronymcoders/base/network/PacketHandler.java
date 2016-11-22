package com.teamacronymcoders.base.network;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

import static net.minecraftforge.fml.common.network.NetworkRegistry.INSTANCE;
import static net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

public class PacketHandler {
    private SimpleNetworkWrapper networkWrapper;
    private int id = -1;

    public PacketHandler(String modid) {
        networkWrapper = INSTANCE.newSimpleChannel(modid);
    }

    public <I extends IMessage, O extends IMessage> void registerPacket(
            Class<? extends IMessageHandler<I, O>> messageHandler, Class<I> requestMessageType, Side side) {
        networkWrapper.registerMessage(messageHandler, requestMessageType, ++id, side);
    }

    public void sendToAllAround(IMessage message, Entity entity) {
        TargetPoint targetPoint = new TargetPoint(entity.dimension, entity.posX, entity.posY, entity.posZ, 64);
        sendToAllAround(message, targetPoint);
    }

    public void sendToAllAround(IMessage message, BlockPos blockPos, int dimensionId) {
        TargetPoint targetPoint = new TargetPoint(dimensionId, blockPos.getX(), blockPos.getY(), blockPos.getZ(), 64);
        sendToAllAround(message, targetPoint);
    }

    public void sendToPlayer(IMessage message, EntityPlayerMP entityPlayer) {
        networkWrapper.sendTo(message, entityPlayer);
    }

    public void sendToAllAround(IMessage message, TargetPoint targetPoint) {
        networkWrapper.sendToAllAround(message, targetPoint);
    }

    public void sendToServer(IMessage message) {
        networkWrapper.sendToServer(message);
    }

    public void sendToDimension(IMessage message, int dimensionId) {
        networkWrapper.sendToDimension(message, dimensionId);
    }
}
