package com.teamacronymcoders.base.proxies;

import com.teamacronymcoders.base.multiblock.IMultiblockRegistry;
import com.teamacronymcoders.base.multiblock.MultiblockEventHandler;
import com.teamacronymcoders.base.multiblock.MultiblockRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class ModCommonProxy {

    public World getClientWorld() {
        return null;
    }

    public boolean isClient() {
        return false;
    }

    public boolean isServer() {
        return true;
    }

    public EntityPlayer getClientPlayer() {
        return null;
    }

    /**
     * Translates a message
     *
     * @param label   prefix
     * @param message message
     * @return Translated String
     */
    public String translateMessage(String label, String message) {
        return String.format("%s.%s.%s", label, Reference.MODID, message);
    }

    // TODO

    public IMultiblockRegistry initMultiblockRegistry() {

        if (null == s_multiblockHandler)
            MinecraftForge.EVENT_BUS.register(s_multiblockHandler = new MultiblockEventHandler());

        return MultiblockRegistry.INSTANCE;
    }

    private static MultiblockEventHandler s_multiblockHandler = null;
}
