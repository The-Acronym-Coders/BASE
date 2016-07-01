package com.acronym.base.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public abstract class CommonProxy {

    public void registerRenderers() {
        //NO-OP
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

}
