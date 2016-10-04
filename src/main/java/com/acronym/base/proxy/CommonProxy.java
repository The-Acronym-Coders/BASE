package com.acronym.base.proxy;

import com.acronym.base.reference.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class CommonProxy {

    public void registerRenderers() {
    }

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

    public void preInitBlocks() {

    }

    public void initBlockRenders() {
    }

    public void initItemRenders() {
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

    public void initEvents() {

    }
}
