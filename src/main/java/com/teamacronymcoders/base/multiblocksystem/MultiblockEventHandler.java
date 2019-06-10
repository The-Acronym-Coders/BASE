package com.teamacronymcoders.base.multiblocksystem;

import net.minecraft.client.Minecraft;
import net.minecraft.world.chunk.IChunk;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public final class MultiblockEventHandler {
    @SubscribeEvent
    public void onChunkLoad(final ChunkEvent.Load loadEvent) {
        IChunk chunk = loadEvent.getChunk();

        MultiblockRegistry.getInstance().onChunkLoaded(loadEvent.getWorld(), chunk.getPos().x, chunk.getPos().z);
    }

    @SubscribeEvent
    public void onWorldUnload(final WorldEvent.Unload unloadWorldEvent) {
        MultiblockRegistry.getInstance().onWorldUnloaded(unloadWorldEvent.getWorld());
    }

    @SubscribeEvent
    public void onWorldTick(final TickEvent.WorldTickEvent event) {
        if (TickEvent.Phase.START == event.phase) {
            MultiblockRegistry.getInstance().tickStart(event.world);
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onClientTick(final TickEvent.ClientTickEvent event) {
        if (TickEvent.Phase.START == event.phase) {
            MultiblockRegistry.getInstance().tickStart(Minecraft.getInstance().world);
        }
    }
}