package com.teamacronymcoders.base.proxies;

import com.teamacronymcoders.base.reference.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

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
}
